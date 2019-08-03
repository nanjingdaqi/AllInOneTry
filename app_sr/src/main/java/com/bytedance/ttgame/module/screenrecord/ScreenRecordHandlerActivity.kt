package com.bytedance.ttgame.module.screenrecord

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.media.projection.MediaProjectionManager
import android.os.Build
import android.os.Bundle
import org.greenrobot.eventbus.EventBus

class ScreenRecordHandlerActivity : Activity() {

    companion object {
        const val REQUEST_CODE = 1
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val projectionManager = getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager
        startActivityForResult(projectionManager.createScreenCaptureIntent(), REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            EventBus.getDefault().post(ActivityResultEvent(resultCode, data))
            finish()
        }
    }

    class ActivityResultEvent(val resultCode: Int, var data: Intent?) {}
}