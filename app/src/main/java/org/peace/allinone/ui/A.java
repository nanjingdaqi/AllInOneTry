package org.peace.allinone.ui;

import android.util.Log;
import me.ele.base.utils.AppLogger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daqi on 17-7-28.
 */

public class A {

    static {
        AppLogger.e("A Cl_Init");
    }

    public static int b = 200;

    public int aa = 0;

    public static int exp() {
        try {
            int b = 0 / 0;
            return b;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return -1;
        }
    }

    public static void flow() {
        if (A.class != null) {
            foo(1, 2);
        } else {
            exp();
        }
        A.foo();
    }

    public static int foo(int a, int b) {
        int l = 10;
        if (System.currentTimeMillis() > 1) {
            throw new RuntimeException("here");
        }
        return a + b + l;
    }

    public static void foo() {
        int i = 10;
        Integer j = 10;
        List<Integer> list = new ArrayList<>();
        list.add(i);
        list.add(j);
        i++;
        j++;
        Log.e("daqi", "i: " + i + ", j: " + j);
        Log.e("daqi", "list: " + list.get(0) + ", " + list.get(1));
    }
}
