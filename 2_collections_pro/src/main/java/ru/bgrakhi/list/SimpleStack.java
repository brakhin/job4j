package ru.bgrakhi.list;

import java.util.LinkedHashMap;

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
}
