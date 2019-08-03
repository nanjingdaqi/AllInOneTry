package com.bytedance.ttgame.module.screenrecord

import android.app.Activity
import android.app.Application
import android.os.Bundle


data class DebugConfig(val quality: Int, val duration: Int)

data class KeyMoment(val timeStampUs: Long, val priority: Int)

data class MuxedVideoInfo(val firstTimeStampUs: Long, val durationTimeMill: Long)

// unit: s
data class CropInfo(val st: Long, val ed: Long, var outPath: String, val priority: Int)

data class DownloadedVideo(val vid: String, val aid: String, val url: String, val type: Int, val localPath: String)

class AudioAdapter : AudioSource {
    var observers = mutableListOf<AudioObserver>()

    override fun subscribe(observer: AudioObserver) {
        observers.add(observer)
    }

    override fun unsubscribe(observer: AudioObserver) {
        observers.remove(observer)
    }
}

open class EmptyActivityLifecycleCallbacks : Application.ActivityLifecycleCallbacks {
    override fun onActivityPaused(activity: Activity?) {}

    override fun onActivityResumed(activity: Activity?) {}

    override fun onActivityStarted(activity: Activity?) {}

    override fun onActivityDestroyed(activity: Activity?) {}

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {}

    override fun onActivityStopped(activity: Activity?) {}

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {}
}
