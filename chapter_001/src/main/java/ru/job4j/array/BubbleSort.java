package ru.job4j.array;

public class BubbleSort {

    public int[] sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[i]) {
                    int value = array[i];
                    array[i] = array[j];
                    array[j] = value;
                }
            }
        }
        return array;
    }
}
