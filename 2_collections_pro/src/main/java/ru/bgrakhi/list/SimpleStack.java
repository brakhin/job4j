package ru.bgrakhi.list;

import java.util.Iterator;

public class SimpleStack<T> {

    private LinkedContainer<T> container;

    public SimpleStack() {
        container = new LinkedContainer<>();
    }

    public <T> T poll() {
        return (T) container.delete();
    }

    public void push(T value) {
        container.add(value);
    }

    public Iterator<T> getIterator() {
        return container.iterator();
    }

    public int size() {
        return container.size();
    }
}
