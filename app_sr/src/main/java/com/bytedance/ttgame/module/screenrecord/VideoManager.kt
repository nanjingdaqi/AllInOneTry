package com.bytedance.ttgame.module.screenrecord

import android.app.Application
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.ss.android.vesdk.VEEditor
import com.ss.android.vesdk.VEListener
import com.ss.android.vesdk.VESDK
import com.ss.android.vesdk.VEVideoEncodeSettings
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger

object VideoManager {

    const val TAG = "VideoManager"

    lateinit var workspaceDir: String

    public fun init(app: Application, workspaceDir: String) {
        this.workspaceDir = workspaceDir
        VESDK.init(app, workspaceDir)
    }

    public fun crop(inVideoPath: String, cropInfos: List<CropInfo>,
                    listener: CropListener, listenerHandler: Handler = Handler(Looper.getMainLooper())) {
        if (cropInfos.isEmpty()) return

        Executors.newFixedThreadPool(1) {
            Thread(null, it, "Video-Crop-Controller")
        }.execute {
            val outLatch = CountDownLatch(cropInfos.size)
            var index = 1
            val workers = Executors.newCachedThreadPool(ThreadFactory())
            val errorCrops = mutableListOf<CropError>()

            cropInfos.forEach { crop ->
                workers.execute {
                    Log.w("daqi", "running crop $crop")
                    VEEditor(workspaceDir).run {
                        val latch = CountDownLatch(1)
                        init2(arrayOf(inVideoPath),
                                arrayOf(crop.stMilli).toIntArray(), arrayOf(crop.edMilli).toIntArray(), null,
                                null, null, null,
                                VEEditor.VIDEO_RATIO.VIDEO_OUT_RATIO_ORIGINAL)
                        prepare()
                        compile(crop.outPath,
                                null,
                                VEVideoEncodeSettings.Builder(VEVideoEncodeSettings.USAGE_COMPILE).build(),
                                // compile listener 会在主线程得到回调
                                object : VEListener.VEEditorCompileListener {
                                    override fun onCompileDone() {
                                        Log.w("daqi", "compile done, $crop")
                                        latch.countDown()
                                    }

                                    override fun onCompileProgress(p0: Float) {

                                    }

                                    override fun onCompileError(error: Int, p1: Int, p2: Float, msg: String?) {
                                        Log.w("daqi", "compile error, $crop")
                                        errorCrops.add(CropError(error, crop))
                                        latch.countDown()
                                    }

                                })
                        latch.await()
                        Log.w("daqi", "crop destroying, $crop")
                        destroy()
                        Log.w("daqi", "crop destroying succ, $crop")
                        listenerHandler.post {
                            listener.onProgress(index++, cropInfos.size)
                        }
                        outLatch.countDown()
                    }
                }
            }

            outLatch.await()
            Log.w("daqi", "crop finish")
            listenerHandler.post {
                if (errorCrops.isEmpty()) listener.onFinish() else listener.onError(errorCrops)
            }
        }
    }

    data class CropInfo(val stMilli: Int, val edMilli: Int, val outPath: String)

    interface CropListener {
        fun onFinish()
        fun onProgress(finishedCount: Int, totalCount: Int)
        fun onError(errors: List<CropError>)
    }

    data class CropError(val errorCode: Int, val cropInfo: CropInfo)

    private class ThreadFactory : java.util.concurrent.ThreadFactory {
        private val poolNumber = AtomicInteger(1)

        override fun newThread(r: Runnable?): Thread =
                Thread(null, r, "Video-Crop-Worker-${poolNumber.getAndIncrement()}")

    }
}