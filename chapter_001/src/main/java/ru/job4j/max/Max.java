package ru.job4j.max;

public class Max {

    public int max(int a, int b) {
        return a > b ? a : b;
    }

    public int max(int a, int b, int c) {
        return max(a, max(b, c));
    }
}
