package com.bytedance.ttgame.module.screenrecord

import android.app.Activity
import android.app.Application
import android.os.Bundle


data class DebugConfig(val quality: Int, val duration: Int)

data class KeyMoment(val timeStampUs: Long, val priority: Int, val addToConcatenatedVideo: Boolean)

data class MuxedVideoInfo(val firstTimeStampUs: Long, val durationTimeMill: Long)

data class CropInfo(val stMilli: Long, val edMilli: Long, val outPath: String, val priority: Int, val toBeConcatenated: Boolean)

data class ProcessedVideos(val result: Int, val vids: List<String>, val urls: List<String>, val finalVid: String, val finalUrl: String)

data class DownloadedVideo(val vid: String, val url: String, val localPath: String)

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
