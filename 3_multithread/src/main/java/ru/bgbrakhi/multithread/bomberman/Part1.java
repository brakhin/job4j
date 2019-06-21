package ru.bgbrakhi.multithread.bomberman;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class Part1 {
    private final int width = 4;
    private final int height = 4;
    private final ReentrantLock[][] board = new ReentrantLock[height][width];
    private final ExecutorService pool = Executors.newFixedThreadPool(2);
    private final ConcurrentHashMap<ReentrantLock, ReentrantLock> lockers = new ConcurrentHashMap<ReentrantLock, ReentrantLock>();
    private final Hero hero = new Hero(new Board(height, width));

    public void start() {
        Runnable runnable = new Runnable() {
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    if (!hero.move()) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        };
        pool.submit(runnable);
        pool.submit(runnable);
    }

    public void shotdown() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
