package com.bytedance.ttgame.module.screenrecord

class UploadException(val error: Long): Exception()

class MuxException(val error: Int) : Exception()

class CropException(val error: Int) : Exception()

class ServerException(val error: Int): Exception()
