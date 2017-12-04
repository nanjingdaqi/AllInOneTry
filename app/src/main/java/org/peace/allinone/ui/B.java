package org.peace.allinone.ui;

/**
 * Created by daqi on 17-12-3.
 */

public class B {

    public synchronized void foo() {

    }

    public synchronized static void foo2() {

    }

    int i = 0;
    final Object obj = new Object();

    public void foo3() {
        synchronized (obj) {
            i++;
        }
    }

}
