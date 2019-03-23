package ru.bgbrakhi.list;

import org.junit.Before;
import org.junit.Test;
import ru.bgrakhi.list.SimpleStack;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleStackTest {

    SimpleStack<Integer> stack;

    @Before
    public void init() {
        stack = new SimpleStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
    }

    @Test
    public void testPoll() {
        assertThat(stack.poll(), is(3));
        assertThat(stack.poll(), is(2));
        assertThat(stack.poll(), is(1));
    }
}
