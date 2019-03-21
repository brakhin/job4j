package ru.job4j.array;

public class Turn {
    public int[] turn(int[] array) {
        for (int i = 0; i < (int) (array.length / 2); i++) {
            int value = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = value;
        }
        return array;
    }
}