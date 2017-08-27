package org.peace.allinone.ui;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daqi on 17-7-28.
 */

public class A {

    public int aa = 0;

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
