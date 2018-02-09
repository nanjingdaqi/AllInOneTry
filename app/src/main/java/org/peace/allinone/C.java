package org.peace.allinone;

/**
 * Created by daqi on 18-2-9.
 */

public class C {

    String member;

    void foo () {
        try {
            bar();
        } catch (Exception e) {
            if (member != null) {
                member.getBytes();
            }
        } finally {

        }
    }

    void bar() {

    }

    void foo2() {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e2) {
                try {
                    bar();
                } catch (Exception e) {
                    if (member != null) {
                        member.getBytes();
                    }
                } finally {
                    if (member != null) {
                        member.getBytes();
                    }
                }
            }
        });
    }

}
