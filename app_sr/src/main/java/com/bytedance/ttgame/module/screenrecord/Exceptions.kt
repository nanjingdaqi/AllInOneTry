package com.bytedance.ttgame.module.screenrecord

import com.bytedance.ttgame.module.screenrecord.api.Error

class ScreenRecordException(val error: Error) : Exception()

