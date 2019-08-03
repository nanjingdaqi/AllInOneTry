package com.bytedance.ttgame.module.screenrecord

import android.util.Log
import com.ss.android.vesdk.VEUtils
import io.reactivex.Observable
import java.io.File
import com.bytedance.ttgame.module.screenrecord.api.Error

object VideoEditor {

    const val TAG = "daqi-VideoEditor"

    fun transAudio(inAudioPath: String, outAudioPath: String): Observable<File> {
        return Observable.create { emmitter ->
            VEUtils.transCodeAudio(inAudioPath, outAudioPath, 2, 128 * 1000).run {
                if (this == 0) {
                    emmitter.onNext(File(outAudioPath))
                    emmitter.onComplete()
                } else {
                    Log.e(TAG, "trans audio fail: $this")
                    emmitter.onError(ScreenRecordException(Error.MUX_ERROR))
                }
            }
        }
    }

    // todo 做个优化，以视频为主，音频适配视频
    public fun mux(inVideoPath: String, inAudioPath: String, outPath: String): Observable<File> {
        return Observable.create { emitter ->
            VEUtils.mux(inVideoPath, inAudioPath, outPath).run {
                if (this == 0) {
                    emitter.onNext(File(outPath))
                    emitter.onComplete()
                } else {
                    val aF = File(inAudioPath)
                    Log.e(TAG, "mux fail: $this, audio exsits: ${aF.exists()}")
                    emitter.onError(ScreenRecordException(Error.MUX_ERROR))
                }
            }
        }
    }

    public fun crop(inVideoPath: String, cropInfos: List<CropInfo>): Observable<List<CropInfo>> {
        return Observable.create { emitter ->
            cropInfos.run {
                if (size == 0) {
                    Log.w(TAG, "no crop info, return directly")
                    emitter.onNext(cropInfos)
                    emitter.onComplete()
                    return@run
                }
                val outPaths = ArrayList<String>(size)
                val stTimes = ArrayList<String>(size)
                val durations = ArrayList<String>(size)
                forEach {
                    outPaths.add(it.outPath)
                    stTimes.add(it.st.run {
                        String.format("%d:%02d:%02d", this / 3600, (this % 3600) / 60, this % 60)
                    })
                    durations.add((it.ed - it.st).run {
                        String.format("%d:%02d:%02d", this / 3600, (this % 3600) / 60, this % 60)
                    })
                }
                // 代码注释写的有误，是时长
                VEUtils.curVideo(inVideoPath, outPaths, stTimes, durations).run {
                    if (this == 0) {
                        emitter.onNext(cropInfos)
                        emitter.onComplete()
                    } else {
                        Log.e(TAG, "crop fail: $this")
//                        emitter.onError(ScreenRecordException(Error.CLIP_ERROR))
//                        Files.copy(File(inVideoPath).toPath(), File(cropInfos[0].outPath).toPath())
                        cropInfos[0].outPath = inVideoPath
                        emitter.onComplete()
                    }
                }
            }
        }
    }
}