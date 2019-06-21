package ru.bgbrakhi.multithread.bomberman;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class Board {
    private final int height;
    private final int width;
    private final AtomicReference<ReentrantLock[][]> board = new AtomicReference<ReentrantLock[][]>();
    private final AtomicReference<ReentrantLock> lastLock = new AtomicReference<ReentrantLock>();

    public Board(int height, int width) {
        this.board.set(new ReentrantLock[height][width]);
        this.height = height;
        this.width = width;
    }

    public boolean move(int line, int col) {
        ReentrantLock lock;
        boolean result = true;
        boolean moved = false;
        do {
            lock = this.board.get()[line][col];
            if (lastLock.get() != lock) {
                try {
                    if (lock.tryLock(500, MILLISECONDS)) {
                        lastLock.set(lock);
                        moved = true;
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    result = false;
                } finally {
                    lock.unlock();
                }
            }
        } while (result && !moved);
        return result;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
