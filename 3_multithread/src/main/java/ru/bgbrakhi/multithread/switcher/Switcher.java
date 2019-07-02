package ru.bgbrakhi.multithread.switcher;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Switcher {
    private final StringBuilder buff = new StringBuilder();
    private final CyclicBarrier barrier = new CyclicBarrier(2);

    public void start() {

        new Thread(new Runnable() {
            public void run() {
                int counter = 0;
                while (!Thread.currentThread().isInterrupted()) {
                    buff.append(1);
                    counter++;
                    if (counter == 10) {
                        try {
                            barrier.await();
                            counter = 0;
                            barrier.await();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                int counter = 0;
                try {
                    barrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }

                while (!Thread.currentThread().isInterrupted()) {
                    buff.append(2);
                    counter++;
                    if (counter == 10) {
                        try {
                            barrier.await();
                            counter = 0;
                            barrier.await();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        Switcher sw = new Switcher();
        sw.start();
    }
}
