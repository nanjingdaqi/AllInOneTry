package org.peace.allinone.ui;

/**
 * Created by daqi on 17-7-28.
 */

public class A {

    public static int foo(int a, int b) {
        int l = 10;
        if (System.currentTimeMillis() > 1) {
            throw new RuntimeException("here");
        }
        return a + b + l;
    }
}
