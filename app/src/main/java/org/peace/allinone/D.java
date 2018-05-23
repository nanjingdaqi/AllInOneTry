package org.peace.allinone;

import java.util.TreeMap;

public class D {

    public boolean var;

    public void foo() {
        new Thread() {
            @Override
            public void run() {
                var = false;
            }
        }.start();
    }
}
