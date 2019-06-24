package ru.bgbrakhi.multithread.deadlock;

import java.util.concurrent.CountDownLatch;

public class Deadlock {
    private final CountDownLatch cdl = new CountDownLatch(1);

    private final static Monitor monitor1 = new Monitor();
    private final static Monitor monitor2 = new Monitor();

    public void start() {
        new Thread(new Runnable() {
            public void run() {
                synchronized (monitor1) {
                    try {
                        cdl.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    monitor2.method1();
                }
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                synchronized (monitor2) {
                    cdl.countDown();
                    monitor1.method1();
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        Deadlock dl = new Deadlock();
        dl.start();
    }
}
