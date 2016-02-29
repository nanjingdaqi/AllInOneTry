package me.ele.ecamera.utils;

import android.annotation.TargetApi;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Build;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class FocusCompleteSoundCompat {

    private static final int FOCUS_BEEP_VOLUME = 100;
    
    public static void play() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            android.media.MediaActionSound  sound = new android.media.MediaActionSound();
            sound.play(android.media.MediaActionSound.FOCUS_COMPLETE);
        } else {
            ToneGenerator focusToneGenerator = new ToneGenerator(AudioManager.STREAM_SYSTEM, FOCUS_BEEP_VOLUME);
            focusToneGenerator.startTone(ToneGenerator.TONE_PROP_BEEP2);
        }
    }

}
