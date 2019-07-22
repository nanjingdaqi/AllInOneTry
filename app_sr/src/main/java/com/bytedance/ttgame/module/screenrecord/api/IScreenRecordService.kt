package com.bytedance.ttgame.module.screenrecord.api

import android.app.Activity
import android.content.Intent

interface IScreenRecordService {

    fun init(activity: Activity): Int

    fun prepare(activity: Activity, requestCode: Int, audioSampleRate: Int)

    fun onActivityResult(resultCode: Int, data: Intent)

    fun startScreenRecord(userConfig: UserConfig): Int

    fun onAudioBuffer(buffer: FloatArray)

    fun markKeyMoment(priority: Int, addToConcatenatedVideo: Boolean)

    fun stopScreenRecord()

    fun setAuth(auth: String)

    fun injectAudio(filePath: String)

    fun shareVideo(localPath: String)
}