package ru.bgbrakhi.list;

import org.junit.Before;
import org.junit.Test;
import ru.bgrakhi.list.BiStackQueue;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BiStackQueueTest {

    BiStackQueue<Integer> queue;

    @Before
    public void init() {
        queue = new BiStackQueue<>();
        queue.push(1);
        queue.push(2);
        queue.push(3);
    }

    @Test
    public void testPoll() {
        assertThat(queue.poll(), is(1));
        assertThat(queue.poll(), is(2));
        assertThat(queue.poll(), is(3));
        queue.push(4);
        queue.push(5);
        assertThat(queue.poll(), is(4));
        assertThat(queue.poll(), is(5));
    }
}
