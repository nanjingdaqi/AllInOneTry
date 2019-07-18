package com.bytedance.ttgame.module.screenrecord

import android.app.Application
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.ss.android.vesdk.VESDK
import com.ss.android.vesdk.VEUtils
import java.util.concurrent.Executor
import java.util.concurrent.Executors

object VideoEditor {

    const val TAG = "VideoEditor"

    lateinit var workspaceDir: String
    lateinit var worker: Executor

    public fun init(app: Application, workspaceDir: String) {
        this.workspaceDir = workspaceDir
        VESDK.init(app, workspaceDir)
        worker = Executors.newSingleThreadExecutor {
            Thread(null, it, "VideoEditor")
        }
    }

    public fun mux(inVideoPath: String, inAudioPath: String, outPath: String, listener: MuxListener, handler: Handler = Handler(Looper.myLooper())) {
        worker.execute {
            VEUtils.mux(inVideoPath, inAudioPath, outPath).run {
                handler.post {
                    if (this == 0) {
                        listener.onFinish()
                    } else {
                        listener.onError(this)
                    }
                }
            }
        }
    }

    public fun crop(inVideoPath: String, cropInfos: List<CropInfo>,
                    listener: CropListener, handler: Handler = Handler(Looper.myLooper())) {
        worker.execute {
            cropInfos.run {
                val outPaths = ArrayList<String>(size)
                val stTimes = ArrayList<String>(size)
                val edTimes = ArrayList<String>(size)
                forEach {
                    outPaths.add(it.outPath)
                    stTimes.add((it.stMilli / 1000).run {
                        String.format("%d:%02d:%02d", this / 3600, (this % 3600) / 60, this % 60)
                    })
                    edTimes.add((it.edMilli / 1000).run {
                        String.format("%d:%02d:%02d", this / 3600, (this % 3600) / 60, this % 60)
                    })
                }
                Log.d(TAG, "crop video: st: $stTimes, ed: $edTimes")
                VEUtils.curVideo(inVideoPath, outPaths, stTimes, edTimes).run {
                    handler.post {
                        if (this == 0) {
                            listener.onFinish()
                        } else {
                            listener.onError(this)
                        }
                    }
                }
            }
        }
    }

    data class CropInfo(val stMilli: Int, val edMilli: Int, val outPath: String)

    interface CropListener {
        fun onFinish()
        fun onError(error: Int)
    }

    interface MuxListener {
        fun onFinish()
        fun onError(error: Int)
    }
}