package ru.bgbrakhi.multithread.wait;


public class Consumer implements Runnable {
    private Integer value = 0;
    private SimpleBlockingQueue<Integer> queue;

    public Consumer(SimpleBlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().interrupted()) {
            try {
                Integer value = queue.poll();
                if (value < 0) {
                    break;
                }
                System.out.print(String.format("Next value is %d", value));
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}
