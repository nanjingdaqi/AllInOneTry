package com.bytedance.ttgame.module.screenrecord.api

import android.app.Activity

interface IScreenRecordService {

    fun isSupported(activity: Activity): Boolean

    fun prepare(activity: Activity, audioSampleRate: Int): Boolean

    fun setCallback(listener: ScreenRecordCallback)

    fun startScreenRecord(config: Config): Boolean

    fun stopScreenRecord(fragModifyCount: Int, albumFragCount: Int)

    fun onAudioBuffer(buffer: FloatArray)

    fun markKeyMoment(priority: Int)

    fun shareVideo(localPath: String, aid: String)
}