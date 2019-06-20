package ru.bgbrakhi.multithread.pull;

import java.util.LinkedList;
import java.util.Queue;

public class SimpleBlockingQueue<K>  {

    private Queue<K> queue = new LinkedList();

    public synchronized void add(K e) {
        queue.add(e);
        notifyAll();
    }

    public synchronized K poll() {
        while (!Thread.currentThread().interrupted() && size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
        return queue.poll();
    }

    public synchronized Integer size() {
        return queue.size();
    }
}
