package ru.bgrakhi.list;

import java.util.Iterator;

public class BiStackQueue<T> {

    SimpleStack<T> stack1;
    SimpleStack<T> stack2;

    public BiStackQueue() {
        stack1 = new SimpleStack<>();
        stack2 = new SimpleStack<>();
    }

    public void push(T value) {
        stack1.push(value);
    }

    public T poll() {
        T result = null;

        // если stack1 не пуст - зеркалируем порядок элементов, перемещая их в stack2
        while (stack1.size() > 0) {
            stack2.push(stack1.poll());
        }
        result = stack2.poll();
        return result;
    }
}
