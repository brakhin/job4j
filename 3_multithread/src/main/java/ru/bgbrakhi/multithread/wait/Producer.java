package ru.bgbrakhi.multithread.wait;

public class Producer implements Runnable {
    private Integer value = 0;
    SimpleBlockingQueue<Integer> queue;

    public Producer(SimpleBlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().interrupted()) {
            try {
                if (value > 5) {
                    queue.offer(-1);
                    break;
                }
                queue.offer(value++);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}
