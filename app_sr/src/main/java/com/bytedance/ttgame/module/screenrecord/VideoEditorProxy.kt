package com.bytedance.ttgame.module.screenrecord

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
import io.reactivex.Observable
import java.io.File
import com.bytedance.ttgame.module.screenrecord.api.Error
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class VideoEditorProxy(val app: Application) : IClient.Stub() {

    companion object {
        const val TAG = "daqi-VideoEditor"
        const val TIMEOUT = 60L // s
    }

    var waitLatch = CountDownLatch(1)
    var resultCmd: Int = -1
    var resultError: Int = -1

    override fun onResult(cmd: Int, error: Int) {
        resultCmd = cmd
        resultError = error
        waitLatch.countDown()
        waitLatch = CountDownLatch(1)
    }

    fun transAudio(inAudioPath: String, outAudioPath: String): Observable<File> {
        return Observable.create { emmitter ->
            Intent(app, WorkerService::class.java).apply {
                Bundle().apply {
                    putBinder(WorkerService.KEY_CLIENT, this@VideoEditorProxy)
                    putExtra(WorkerService.KEY_CLIENT_BUNDLE, this)
                }
                putExtra(WorkerService.KEY_CMD, WorkerService.CMD_TRANS_AUDIO)
                putExtra(WorkerService.KEY_IN_AUDIO_PATH, inAudioPath)
                putExtra(WorkerService.KEY_OUT_AUDIO_PATH, outAudioPath)
            }.run {
                app.startService(this)
            }
            val timeout = !waitLatch.await(TIMEOUT, TimeUnit.SECONDS)
            if (timeout) {
                Log.e(TAG, "trans audio timeout: $this")
                emmitter.onError(ScreenRecordException(Error.MUX_ERROR))
            } else {
                if (resultError == 0) {
                    emmitter.onNext(File(outAudioPath))
                    emmitter.onComplete()
                } else {
                    Log.e(TAG, "trans audio fail: $resultError")
                    emmitter.onError(ScreenRecordException(Error.MUX_ERROR))
                }
            }
        }
    }

    // todo 做个优化，以视频为主，音频适配视频
    public fun mux(inVideoPath: String, inAudioPath: String, outPath: String): Observable<File> {
        return Observable.create { emitter ->
            Intent(app, WorkerService::class.java).apply {
                Bundle().apply {
                    putBinder(WorkerService.KEY_CLIENT, this@VideoEditorProxy)
                    putExtra(WorkerService.KEY_CLIENT_BUNDLE, this)
                }
                putExtra(WorkerService.KEY_CMD, WorkerService.CMD_MUX)
                putExtra(WorkerService.KEY_IN_MP4_PATH, inVideoPath)
                putExtra(WorkerService.KEY_IN_AUDIO_PATH, inAudioPath)
                putExtra(WorkerService.KEY_OUT_MP4_PATH, outPath)
            }.run {
                app.startService(this)
            }
            val timeout = !waitLatch.await(TIMEOUT, TimeUnit.SECONDS)
            if (timeout) {
                Log.e(TAG, "mux timeout.")
            } else {
                if (resultError == 0) {
                    emitter.onNext(File(outPath))
                    emitter.onComplete()
                } else {
                    Log.e(TAG, "mux fail $resultError")
                    emitter.onError(ScreenRecordException(Error.MUX_ERROR))
                }
            }
        }
    }

    public fun crop(inVideoPath: String, cropInfos: ArrayList<CropInfo>): Observable<List<CropInfo>> {
        return Observable.create { emitter ->
            Intent(app, WorkerService::class.java).apply {
                Bundle().apply {
                    putBinder(WorkerService.KEY_CLIENT, this@VideoEditorProxy)
                    putExtra(WorkerService.KEY_CLIENT_BUNDLE, this)
                }
                putExtra(WorkerService.KEY_CMD, WorkerService.CMD_CROP)
                putExtra(WorkerService.KEY_IN_MP4_PATH, inVideoPath)
                putExtra(WorkerService.KEY_CROP_INFO, cropInfos)
            }.run {
                app.startService(this)
            }
            val timeout = !waitLatch.await(TIMEOUT, TimeUnit.SECONDS)
            if (timeout) {
                Log.e(TAG, "crop video timeout")
                emitter.onError(ScreenRecordException(Error.MUX_ERROR))
            } else {
                if (resultError == 0) {
                    emitter.onNext(cropInfos)
                    emitter.onComplete()
                } else {
                    emitter.onError(ScreenRecordException(Error.MUX_ERROR))
                }
            }
        }
    }
}