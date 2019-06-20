package ru.bgbrakhi.multithread.nonblocking;

import org.junit.Assert;
import org.junit.Test;
import java.util.concurrent.atomic.AtomicReference;
import static org.hamcrest.CoreMatchers.is;

public class NonBlockingCashTest {

    @Test
    public void ifSecondThreadModifyThenThrowExeption() {
        AtomicReference<Exception> ex = new AtomicReference<>();
        NonBlockingCash cash = new NonBlockingCash();
        cash.add(new Base(1));
        cash.add(new Base(2));
        cash.add(new Base(3));
        Thread thread1 = new Thread(
                () -> {
                    Base base = new Base(2);
                    try {
                        cash.update(base);
                    } catch (OptimisticException e) {
                        ex.set(e);
                    }
                }
        );
        Thread thread2 = new Thread(
                () -> {
                    Base base = new Base(2);
                    try {
                        cash.update(base);
                    } catch (OptimisticException e) {
                        ex.set(e);
                    }
                }
        );
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertThat(ex.get().getMessage(), is("Throw Exception in Thread"));
    }
}