package com.bytedance.ttgame.module.screenrecord.api

import java.io.File

// 面向服务使用方的大回调
interface Listener {

    companion object {
        const val ERROR_NO = 0
        const val ERROR_IN_USE = 1
        const val ERROR_CREATE_VIDEO_ENCODER = 2
        const val ERROR_CREATE_AUDIO_ENCODER = 3
        const val ERROR_AUDIO_ENCODE_FAIL = 4
        const val ERROR_VIDEO_ENCODE_FAIL = 5
        const val ERROR_FINISH_FAIL = 6
        const val ERROR_ACTIVITY_PAUSED = 7
        const val ERROR_NO_AUDIO_FILE = 8
        const val ERROR_UPLOAD_FAIL = 9
        const val ERROR_DOWNLOAD_FAIL = 10
        const val ERROR_CROP_FAIL = 11
    }


    fun onSucc(videoFiles: List<File>)

    fun onFail(error: Int, exception: Throwable? = null)
}