package com.bytedance.ttgame.module.screenrecord

import android.util.Log
import com.bytedance.frameworks.baselib.network.http.retrofit.SsHttpExecutor
import com.bytedance.retrofit2.Retrofit
import com.bytedance.retrofit2.http.AddCommonParam
import com.bytedance.retrofit2.http.Field
import com.bytedance.retrofit2.http.FieldMap
import com.bytedance.retrofit2.http.FormUrlEncoded
import com.bytedance.retrofit2.http.GET
import com.bytedance.retrofit2.http.POST
import com.bytedance.retrofit2.http.Streaming
import com.bytedance.retrofit2.http.Url
import com.bytedance.retrofit2.mime.TypedInput
import com.bytedance.retrofit2.rxjava2.adapter.RxJava2CallAdapterFactory
import com.bytedance.ttgame.framework.module.network.IRetrofit
import com.bytedance.ttgame.framework.module.network.IRetrofitService
import com.bytedance.ttgame.framework.module.spi.ServiceManager
import com.bytedance.ttgame.module.screenrecord.api.Error
//import com.bytedance.ttgame.sdk.module.core.internal.ChannelConstants
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.ss.ttuploader.TTVideoInfo
import com.ss.ttuploader.TTVideoUploader
import com.ss.ttuploader.TTVideoUploaderListener
import io.reactivex.Observable
import java.io.File
import java.io.FileOutputStream
import java.util.concurrent.TimeUnit

class VideoNet(val videoManager: VideoManager) {

    companion object {
        const val QUERY_LOOP_COUNT = 60
        const val TAG = "VideoNet"

        val retrofit: IRetrofit
        val downloadRetrofit: Retrofit
        val request: Request
        val downloadRequest: DownloadRequest

        // todo do not merge from AllInOneTry
        init {
            retrofit = ServiceManager.get()
                    .getService(IRetrofitService::class.java)
                    .createNewRetrofit("")
            downloadRetrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .setEndpoint("")
                    .httpExecutor(SsHttpExecutor())
                    .client { com.bytedance.ttnet.retrofit.SsRetrofitClient() }
                    .build()
            request = retrofit.create(Request::class.java)
            downloadRequest = downloadRetrofit.create(DownloadRequest::class.java)
        }
    }

    var fragModifyCount = 1
    var albumFragCount = 1

    // 上传视频云的vid给中台服务器, 返回query_id
    fun uploadVids(vids: List<String>, levels: List<Int>): Observable<String> {
        mutableListOf<VidInfo>().apply {
            for (i in 0 until vids.size) {
                add(VidInfo(vids[i], levels[i]))
            }
        }.run {
            val videoInfos = this
            mutableMapOf<String, String>().apply {
                // todo
                this["app_id"] = "1781"
                this["sdk_open_id"] = "123"
                this["server_id"] = videoManager.userConfig.serverId.toString()
                this["role_id"] = videoManager.userConfig.roleId ?: ""
                this["part_count"] = fragModifyCount.toString()
                this["collection_count"] = albumFragCount.toString()
                this["video_infos"] = Gson().toJson(videoInfos)
            }.run {
                return request.uploadVids(this).flatMap {
                    if (it.code == 0 && it.data != null && it.data.retCode == 1) {
                        Observable.just(it.data.uploadId)
                    } else {
                        Observable.error(ScreenRecordException(Error.UPLOAD_TO_SERVER_CONVERT_ERROR))
                    }
                }
            }
        }
    }

    fun queryProcessedVideos(queryId: String): Observable<List<ProcessedVideos>> {
        return request.queryProcessedVideos(queryId).run {
            var count = 0
            var fail = false
            var succ = false
            repeatWhen {
                it.delay(2, TimeUnit.SECONDS)
            }.takeUntil {
                fail || succ
            }.filter {
                count++
                if (it.data?.videos == null) {
                    fail = true
                } else {
                    val videos = it.data.videos
                    if (videos.all { it.retCode == 100 }) {
                        succ = true
                    }
                    if (videos.any { it.retCode == -1 } || videos.any { it.retCode == -100 } || count >= QUERY_LOOP_COUNT) {
                        fail = true
                    }
                }
                fail || succ
            }.flatMap {
                if (fail) {
                    val error = if (it.data?.videos == null) {
                        Log.e(TAG, "No videos replied.")
                        Error.UPLOAD_TO_SERVER_CONVERT_ERROR
                    } else {
                        val first = it.data.videos.firstOrNull { it.retCode == -1 || it.retCode == -100 }
                        if (first != null) {
                            Log.e(TAG, "Find one video convert fail: ${first.retCode}")
                            Error.UPLOAD_TO_SERVER_CONVERT_ERROR
                        } else {
                            Error.UPLOAD_TO_SERVER_TIME_OUT
                        }
                    }
                    Observable.error(ScreenRecordException(error))
                } else {
                    Observable.just(it.data!!.videos)
                }
            }
        }
    }

    // 上传视频到视频云
    fun uploadVideos(files: List<File>): Observable<List<String>> {
        return Observable.create { ob ->
            mutableListOf<String>().run {
                val outList = this
                // todo
                files.take(1).forEach { file ->
                    TTVideoUploader().run {
                        // todo 配置视频云的域名
                        setVideoUploadDomain("i.snssdk.com")
                        setAuthorization("HMAC-SHA1:1.0:1565422205:62cdccb19d964abaa74a3007d45cfd20:jmyoWN62gdDXQkbl60ENMo0VOC0=")
                        setPathName(file.absolutePath)
                        setUserKey("62cdccb19d964abaa74a3007d45cfd20")
                        setListener(object : TTVideoUploaderListener {
                            override fun onLog(p0: Int, p1: Int, p2: String?) {
                                Log.w("daqi", "onLog ${p0}, ${p1}, ${p2}")
                            }

                            override fun onNotify(what: Int, parameter: Long, info: TTVideoInfo?) {
                                Log.w("daqi", "onNotify what: ${what}")
                                when (what) {
                                    TTVideoUploader.MsgIsComplete -> {
                                        stop()
                                        add(info!!.mVideoId)
                                        if (size == files.size) {
                                            ob.onNext(outList)
                                            ob.onComplete()
                                        }
                                    }
                                    TTVideoUploader.MsgIsFail -> {
                                        when (info!!.mErrcode) {
                                            10401L -> ob.onError(ScreenRecordException(Error.UPLOAD_TO_VCLOUD_AUTH_INVALID))
                                            10404L -> ob.onError(ScreenRecordException(Error.UPLOAD_TO_VCLOUD_TOO_FREQUENTLY))
                                            else -> {
                                                Log.e(TAG, "Upload to VCloud fail: ${info.mErrcode}")
                                                ob.onError(ScreenRecordException(Error.UPLOAD_TO_VCLOUD_ERROR))
                                            }
                                        }
                                    }
                                }
                            }
                        })
                        start()
                    }
                }
            }
        }
    }

    // 下载视频
    fun download(info: DownloadedVideo): Observable<DownloadedVideo> {
        return downloadRequest.downloadFile(info.url)
                .flatMap {
                    File(info.localPath).run {
                        FileOutputStream(this).run {
                            val ios = it.`in`()
                            val buffer = ByteArray(4096)
                            while (true) {
                                val sz = ios.read(buffer)
                                if (sz <= 0) {
                                    break
                                }
                                write(buffer, 0, sz)
                            }
                            flush()
                            close()
                        }
                    }
                    Observable.just(info)
                }
    }
}

interface Request {
    @POST("/gsdk/video_record/fragments")
    @FormUrlEncoded
    fun uploadVids(@FieldMap params: Map<String, String>, @AddCommonParam commonParam: Boolean = true): Observable<UploadVidResponse>

    @POST("/gsdk/video_record/status")
    @FormUrlEncoded
    fun queryProcessedVideos(@Field("upload_id") queryId: String): Observable<ProcessedVideosResponse>
}

interface DownloadRequest {
    @GET
    @Streaming
    fun downloadFile(@Url url: String): Observable<TypedInput>
}

data class VidInfo(val vid: String, val level: Int)

data class UploadVidResponse(val code: Int,
                             val message: String,
                             val data: UploadVidResponseData?)

data class UploadVidResponseData(@SerializedName("upload_id") val uploadId: String,
                                 @SerializedName("ret_code") val retCode: Int)

data class ProcessedVideosResponse(val code: Int,
                                   val message: String,
                                   val data: ProcessedVideosData?)

data class ProcessedVideosData(@SerializedName("trans_ret") val videos: List<ProcessedVideos>)

data class ProcessedVideos(@SerializedName("ret_code") val retCode: Int,
                           val aid: String,
                           val vid: String,
                           val url: String,
                           @SerializedName("video_type") val type: Int)
