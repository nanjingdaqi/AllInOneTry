package com.bytedance.ttgame.module.screenrecord

import android.annotation.TargetApi
import android.app.IntentService
import android.content.Intent
import android.os.Build
import android.util.Log

/**
 * 单拉一个进程，处理录屏后期耗时操作，或许录制过程也可以放在该进程。
 */
class WorkerService : IntentService("screenrecord") {

    companion object {
        const val CMD_TRANS_AUDIO = 1
        const val CMD_MUX = 2
        const val CMD_CROP = 3

        const val KEY_CLIENT_BUNDLE = "client_bundle"
        const val KEY_CLIENT = "client"
        const val KEY_CMD = "cmd"
        const val KEY_IN_AUDIO_PATH = "in_audio_path"
        const val KEY_OUT_AUDIO_PATH = "out_audio_path"
        const val KEY_IN_MP4_PATH = "in_mp4_path"
        const val KEY_OUT_MP4_PATH = "out_mp4_path"
        const val KEY_CROP_INFO = "crop_info"
    }

    lateinit var clientProxy: IClient

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    override fun onHandleIntent(intent: Intent?) {
        intent!!.run {
            Log.e("daqi", "Haha")
            clientProxy = IClient.Stub.asInterface(getBundleExtra(KEY_CLIENT_BUNDLE).getBinder(KEY_CLIENT))
            val cmd = getIntExtra(KEY_CMD, -1)
            when (cmd) {
                CMD_TRANS_AUDIO -> {
                    VideoEditorWorker.transAudio(inAudioPath = getStringExtra(KEY_IN_AUDIO_PATH),
                            outAudioPath = getStringExtra(KEY_OUT_AUDIO_PATH)).run {
                        sendResult(CMD_TRANS_AUDIO, this)
                    }
                }
                CMD_MUX -> {
                    VideoEditorWorker.mux(inVideoPath = getStringExtra(KEY_IN_MP4_PATH),
                            inAudioPath = getStringExtra(KEY_IN_AUDIO_PATH),
                            outPath = getStringExtra(KEY_OUT_MP4_PATH)).run {
                        sendResult(CMD_MUX, this)
                    }
                }
                CMD_CROP -> {
                    VideoEditorWorker.crop(inVideoPath = getStringExtra(KEY_IN_MP4_PATH),
                            cropInfos = getSerializableExtra(KEY_CROP_INFO) as List<CropInfo>).run {
                        sendResult(CMD_CROP, this)
                    }
                }
            }
        }
    }

    fun sendResult(cmd: Int, error: Int) {
        clientProxy.onResult(cmd, error)
    }
}