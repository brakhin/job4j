package ru.bgbrakhi.multithread.pull;

import java.util.ArrayList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads;
    private volatile SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();

    public ThreadPool() {
        threads = new ArrayList<>();
        init();
    }

    private void init() {
        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            threads.add(new Thread(
                            () -> {
                                tasks.poll().run();
                            }
                    )
            );
        }
    }

    public void work(Runnable job) {
        tasks.add(job);
    }

    public void shutdown() {
        for (int i = 0; i < threads.size(); i++) {
            Thread thread = threads.get(i);
            thread.interrupt();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        ThreadPool pool = new ThreadPool();
        pool.work(new Runnable() {
            @Override
            public void run() {
                System.out.println("Test");
            }
        });
        pool.shutdown();
    }

}