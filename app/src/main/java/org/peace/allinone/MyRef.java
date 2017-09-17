package org.peace.allinone;

import me.ele.base.utils.AppLogger;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * Created by daqi on 17-9-17.
 */

public class MyRef<T> extends WeakReference<T> {

    public MyRef(T referent) {
        super(referent);
    }

    public MyRef(T referent, ReferenceQueue<? super T> q) {
        super(referent, q);
    }

    @Override
    public boolean enqueue() {
        AppLogger.e("enqueue");
        return super.enqueue();
    }
}
