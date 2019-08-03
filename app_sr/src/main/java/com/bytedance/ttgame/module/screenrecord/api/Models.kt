package com.bytedance.ttgame.module.screenrecord.api

enum class Quality {
    LOW, MEDIUM, HIGH
}

enum class AudioEngineType {
    DEFAULT, // 无声音
    UNITY,
    WWISE
}

// unit: s
data class Config(var quality: Quality?,
                  var durationBefore: Int,
                  var durationAfter: Int,
                  var serverId: Int,
                  var roleId: String?,
                  var audioEngineType: AudioEngineType,
                  var auth: String?,
                  var audioPath: String?)

enum class Error {
    NO_ERROR,
    EXIT_OR_OUT_OF_MEMORY,
    HAS_START_NEW_RECORD,
    RECORD_ERROR,
    NO_HIGHLIGHT,
    MUX_ERROR,
    CLIP_ERROR,
    UPLOAD_TO_VCLOUD_ERROR,
    UPLOAD_TO_VCLOUD_AUTH_INVALID,
    UPLOAD_TO_VCLOUD_TOO_FREQUENTLY,
    UPLOAD_TO_SERVER_NETWORK_ERROR,
    UPLOAD_TO_SERVER_CONVERT_ERROR,
    UPLOAD_TO_SERVER_TIME_OUT,
    UPLOAD_TO_SERVER_DOWNLOAD_ERROR,
    OTHER_ERROR
}

enum class VideoType {
    DEFAULT, ORIGIN_SINGLE_CLIP, MODIFIED_SINGLE_CLIP, ALBUM
}

enum class ConvertRetCode(val code: Int) {
    DEFAULT(0), UPLOAD_SUCC(1), UPLOAD_FAIL(-1), CONVERTING(2), CONVERT_SUCC(100), CONVERT_FAIL(-100);

    companion object {
        fun fromCode(code: Int): ConvertRetCode {
            return values().find { it.code == code } ?: DEFAULT
        }
    }
}

data class ConvertResult(val aliasId: String,
                         val videoId: String,
                         val videoUrl: String,
                         val videoPath: String,
                         val videoType: VideoType)

data class Result(val succ: Int,
                  val error: Error,
                  var errorMsg: String?,
                  var videos: Array<ConvertResult>?)

// 面向服务使用方的大回调
interface ScreenRecordCallback {
    fun onSuccess(result: Result)

    fun onFail(result: Result)
}
