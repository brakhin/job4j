package ru.job4j.list;

import java.util.List;

public class ConvertList2Array {
    public int[][] toArray(List<Integer> list, int rows) {

        int len = list.size();
        int cols = len % rows == 0 ? len / rows : len / rows + 1;
        int[][] array = new int[rows][cols];

        int row = 0;
        int col = 0;
        for (Integer element : list) {
            array[row][col] = element;
            col = (col + 1) % cols;
            if (col == 0) {
                row++;
            }
        }
        return array;
    }
}