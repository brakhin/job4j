package ru.job4j.array;

public class MatrixCheck {
    public boolean mono(boolean[][] data) {
        for (int i = 0; i < data.length - 1; i++) {
            if (data[i][i] != data[i + 1][i + 1]) {
                return false;
            }
        }
        for (int i = 0; i < data.length - 1; i++) {
            if (data[data.length - i - 1][i] != data[data.length - i - 2][i + 1]) {
                return false;
            }
        }

        return true;
    }
}