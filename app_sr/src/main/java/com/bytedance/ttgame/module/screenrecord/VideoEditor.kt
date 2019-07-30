package com.bytedance.ttgame.module.screenrecord

import android.util.Log
import com.bytedance.ttgame.module.screenrecord.api.CropException
import com.bytedance.ttgame.module.screenrecord.api.MuxException
import com.ss.android.vesdk.VEUtils
import io.reactivex.Observable
import java.io.File

object VideoEditor {

    const val TAG = "daqi-VideoEditor"

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

    public fun crop(inVideoPath: String, cropInfos: List<CropInfo>): Observable<List<CropInfo>> {
        return Observable.create { emitter ->
            cropInfos.run {
                val outPaths = ArrayList<String>(size)
                val stTimes = ArrayList<String>(size)
                val edTimes = ArrayList<String>(size)
                forEach {
                    outPaths.add(it.outPath)
                    stTimes.add((it.stMilli).run {
                        String.format("%d:%02d:%02d", this / 3600, (this % 3600) / 60, this % 60)
                    })
                    edTimes.add((it.edMilli - it.stMilli).run {
                        String.format("%d:%02d:%02d", this / 3600, (this % 3600) / 60, this % 60)
                    })
                }
                Log.w(TAG, "crop video: st: $stTimes, ed: $edTimes")
                VEUtils.curVideo(inVideoPath, outPaths, stTimes, edTimes).run {
                    if (this == 0) {
                        emitter.onNext(cropInfos)
                        emitter.onComplete()
                    } else {
                        Log.e(TAG, "crop video fail, code: $this")
                        emitter.onError(CropException(this))
                    }
                }
            }
        }
    }
}