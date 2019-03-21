package ru.job4j.array;

import java.util.Arrays;

public class ArrayDuplicate {
    public String[] remove(String[] array) {
        int validLen = array.length;

        for (int i = 0; i < validLen -  1; i++) {
            for (int j = i + 1; j < validLen; j++) {
                if (array[i].equals(array[j])) {
                    // сдвиг влево
                    for (int k = j; k < validLen - 1; k++) {
                        array[k] = array[k + 1];
                    }
                    // уменьшение валидной жлинны массива
                    validLen--;
                }
            }
        }
        return Arrays.copyOf(array, validLen);
    }
}
