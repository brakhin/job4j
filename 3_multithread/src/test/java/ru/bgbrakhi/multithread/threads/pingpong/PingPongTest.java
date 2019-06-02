package ru.bgbrakhi.multithread.threads.pingpong;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PingPongTest {

    @Test
    public void mainTest() throws InterruptedException {
        Rectangle rect = new Rectangle(50, 50, 10, 10);
        Thread run = new Thread(new RectangleMove(rect, 0));
        run.start();
        run.interrupt();
        run.join();
        assertThat(run.getState(), is(Thread.State.TERMINATED));
    }

    @Test
    public void stop() throws InterruptedException {
        Thread thread = new Thread(
                () ->  {
                    while (!Thread.currentThread().isInterrupted()) {
                        System.out.println("inside");
                    }
                    System.out.println("interrupted ? " + Thread.currentThread().isInterrupted());
                }
        );
        System.out.println(thread.getState() + " " + thread.isInterrupted());
        thread.start();
        System.out.println(thread.getState() + " " + thread.isInterrupted());
        thread.interrupt();
        System.out.println(thread.getState() + " " + thread.isInterrupted());
        thread.join();
        System.out.println(thread.getState() + " " + thread.isInterrupted());
        assertThat(thread.isInterrupted(), is(false));
    }
}