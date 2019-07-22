package com.bytedance.ttgame.module.screenrecord

import android.os.Looper
import com.bytedance.retrofit2.Retrofit
import com.bytedance.retrofit2.http.GET
import com.bytedance.retrofit2.http.POST
import com.bytedance.retrofit2.http.Streaming
import com.bytedance.retrofit2.rxjava2.adapter.RxJava2CallAdapterFactory
import com.bytedance.ttgame.module.screenrecord.api.ServerException
import com.bytedance.ttgame.module.screenrecord.api.UploadException
import com.ss.ttuploader.TTVideoInfo
import com.ss.ttuploader.TTVideoUploader
import com.ss.ttuploader.TTVideoUploaderListener
import io.reactivex.Observable
import okhttp3.ResponseBody
import java.io.File
import java.io.FileOutputStream
import java.util.concurrent.TimeUnit

object VideoNet {

    val retrofit: Retrofit
    val request: Request
    var auth: String? = null

    init {
        // todo 参考notice模块创建retrofit
        retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        request = retrofit.create(Request::class.java)
    }

    // 上传视频云的vid给中台服务器, 返回query_id
    fun uploadVids(vids: List<String>, levels: List<Int>): Observable<String> {
        return request.uploadVids(vids, levels)
    }

    // todo: test
    fun queryProcessedVideos(queryId: String): Observable<ProcessedVideos> {
        return request.queryProcessedVideos(queryId)
                .repeatWhen {
                    it.delay(5, TimeUnit.SECONDS)
                }
                .takeUntil {
                    it.result == 0
                }
                .filter {
                    it.result == 0
                }
                .doOnNext {
                    if (it.result == 2) {
                        throw ServerException(2)
                    }
                }
    }

    // 上传视频到视频云
    fun uploadVideos(files: List<File>): Observable<List<String>> {
        return Observable.create { ob ->
            Looper.myLooper() ?: Looper.prepare()
            mutableListOf<String>().run {
                val outList = this
                files.forEach { file ->
                    TTVideoUploader().run {
                        // todo 配置视频云的域名
                        setVideoUploadDomain("i.snssdk.com")
                        setAuthorization(auth)
                        setPathName(file.absolutePath)
                        setListener(object : TTVideoUploaderListener {
                            override fun onLog(p0: Int, p1: Int, p2: String?) {}

                            override fun onNotify(what: Int, parameter: Long, info: TTVideoInfo?) {
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
                                        ob.onError(UploadException(info!!.mErrcode))
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
        return request.downloadFile(info.url)
                .map {
                    File(info.localPath).apply {
                        FileOutputStream(this).run {
                            val ios = it.byteStream()
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
                    info
                }
    }
}

interface Request {
    @POST()
    fun fetchAuth(): Observable<String>

    @POST()
    fun uploadVids(vids: List<String>, levels: List<Int>): Observable<String>

    @GET
    @Streaming
    fun downloadFile(url: String): Observable<ResponseBody>

    @GET
    fun queryProcessedVideos(queryId: String): Observable<ProcessedVideos>
}

