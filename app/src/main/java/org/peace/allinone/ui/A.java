package org.peace.allinone.ui;

/**
 * Created by daqi on 17-6-2.
 */

public class A {
    private static int i = 0;

    public void foo() {
        synchronized (this) {
            i++;
        }
    }
}
