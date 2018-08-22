package ru.job4j.array;

/**
 * Класс Square
 */
public class Square {
    /**
     * @param bound lkbyyf vfccbdf
     * @return массив квадратов его индексов
     */
    public int[] calculate(int bound) {
        int[] rst = new int[bound];
        for (int i = 0; i < bound; i++) {
            rst[i] = (i + 1) * (i + 1);
        }
        return rst;
    }
}