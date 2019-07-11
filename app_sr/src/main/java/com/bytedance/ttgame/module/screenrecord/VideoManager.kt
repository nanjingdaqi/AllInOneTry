package com.bytedance.ttgame.module.screenrecord

import android.app.Application
import android.os.Handler
import android.os.Looper
import com.ss.android.vesdk.VEEditor
import com.ss.android.vesdk.VEListener
import com.ss.android.vesdk.VESDK
import com.ss.android.vesdk.VEVideoEncodeSettings
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

object VideoManager {

    const val TAG = "VideoManager"

    lateinit var workspaceDir: String

    public fun init(app: Application, workspaceDir: String) {
        this.workspaceDir = workspaceDir
        VESDK.init(app, workspaceDir)
    }

    public fun crop(inVideoPath: String, cropInfos: List<CropInfo>,
                    listener: CropListener, listenerHandler: Handler = Handler(Looper.getMainLooper())) {
        Executors.newFixedThreadPool(1) { Thread(null, it, "Video-Crop") }.execute {
            var index = 1
            cropInfos.forEach {
                val latch = CountDownLatch(1)
                var errorCode = 0
                VEEditor(workspaceDir).run {
                    init2(arrayOf(inVideoPath),
                            arrayOf(it.stMilli).toIntArray(), arrayOf(it.edMilli).toIntArray(), null,
                            null, null, null,
                            VEEditor.VIDEO_RATIO.VIDEO_OUT_RATIO_ORIGINAL)
                    prepare()
                    compile(it.outPath,
                            null,
                            VEVideoEncodeSettings.Builder(VEVideoEncodeSettings.USAGE_COMPILE).build(),
                            // compile listener 会在主线程得到回调
                            object : VEListener.VEEditorCompileListener {
                                override fun onCompileDone() {
                                    errorCode = 0
                                    latch.countDown()
                                }

                                override fun onCompileProgress(p0: Float) {

                                }

                                override fun onCompileError(error: Int, p1: Int, p2: Float, msg: String?) {
                                    errorCode = error
                                    latch.countDown()
                                }

                            })
                    latch.await()
                    destroy()
                    if (errorCode != 0) {
                        listenerHandler.post {
                            listener.onError(errorCode)
                        }
                        return@execute
                    }
                }
                listenerHandler.post {
                    listener.onProgress(index++, cropInfos.size)
                }
            }
            listenerHandler.post {
                listener.onFinish()
            }
        }
    }

    data class CropInfo(val stMilli: Int, val edMilli: Int, val outPath: String)

    interface CropListener {
        fun onFinish()
        fun onProgress(finishedCount: Int, totalCount: Int)
        fun onError(errorCode: Int)
    }
}