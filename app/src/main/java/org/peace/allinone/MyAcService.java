package org.peace.allinone;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import me.ele.base.utils.AppLogger;

/**
 * Created by daqi on 17-12-30.
 */

public class MyAcService extends AccessibilityService {

    final String T = "peace";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(T, "onCreate");
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.d(T, "event: " + AccessibilityEvent.eventTypeToString(event.getEventType()));
        AccessibilityNodeInfo nodeInfo = event.getSource();
        Log.d(T, "node_info: " + nodeInfo);
//        if (nodeInfo != null) {
//            nodeInfo.performAction(AccessibilityNodeInfo.ACTION_ACCESSIBILITY_FOCUS);
//        }
    }

    @Override
    public void onInterrupt() {

    }
}
