package ru.bgbrakghi.job4j.bombermen.switcher;

public class StringBuff {

    private final StringBuilder builder = new StringBuilder();

    public synchronized void addValue(int value) {
        builder.append(value);
    }

    public synchronized String getString() {
        return builder.toString();
    }
}
