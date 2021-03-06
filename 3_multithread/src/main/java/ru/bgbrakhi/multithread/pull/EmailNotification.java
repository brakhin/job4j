package ru.bgbrakhi.multithread.pull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {
    private final ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public void emailTo(User user) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                send(String.format("Notification %s to email %s.", user.getUsername(), user.getEmail()),
                     String.format("Add a new event to %s", user.getUsername()),
                     user.getEmail()
                );
            }
        });
    }

    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void send(String suject, String body, String email) {

    }
}
