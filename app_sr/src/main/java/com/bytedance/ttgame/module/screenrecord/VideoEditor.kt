package com.bytedance.ttgame.module.screenrecord

import android.app.Application
import android.util.Log
import com.ss.android.vesdk.VESDK
import com.ss.android.vesdk.VEUtils
import io.reactivex.Observable
import java.io.File
import java.util.concurrent.Executor
import java.util.concurrent.Executors

object VideoEditor {

    const val TAG = "daqi-VideoEditor"

    lateinit var workspaceDir: String
    lateinit var worker: Executor

    public fun init(app: Application, workspaceDir: String) {
        this.workspaceDir = workspaceDir
        VESDK.init(app, workspaceDir)
        worker = Executors.newSingleThreadExecutor {
            Thread(null, it, "VideoEditor")
        }
    }

    public fun mux(inVideoPath: String, inAudioPath: String, outPath: String): Observable<File> {
        return Observable.create { emitter ->
            VEUtils.mux(inVideoPath, inAudioPath, outPath).run {
                Log.w(TAG, "mux result: $this")
                if (this == 0) {
                    emitter.onNext(File(outPath))
                    emitter.onComplete()
                } else {
                    emitter.onError(MuxException(this))
                }
            }
        }
    }

    public fun crop(inVideoPath: String, cropInfos: List<CropInfo>): Observable<List<File>> {
        return Observable.create { emitter ->
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
                    if (this == 0) {
                        outPaths.map {
                            File(it)
                        }.run {
                            emitter.onNext(this)
                            emitter.onComplete()
                        }
                    } else {
                        emitter.onError(CropException(this))
                    }
                }
            }
        }
    }

    data class CropInfo(val stMilli: Int, val edMilli: Int, val outPath: String)

    class MuxException(val error: Int) : Exception()
    class CropException(val error: Int) : Exception()
}