package ru.bgbrakhi.multithread.monitor;

public class Count {
    private int value;

    public void increment() {
        this.value++;
    }

    public int get() {
        return this.value;
    }
}
