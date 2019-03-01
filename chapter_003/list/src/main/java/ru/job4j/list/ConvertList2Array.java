package ru.job4j.list;

import java.util.List;

public class ConvertList2Array {
    public int[][] toArray(List<Integer> list, int rows) {

        int len = list.size();
        int cells = len % rows == 0 ? len / rows : len / rows + 1;
        int[][] array = new int[rows][cells];

        int index = 0;
        for (int row = 0; row < rows; row ++) {
            for (int cell = 0; cell < cells; cell ++) {
                array[row][cell] = index < len ? list.get(index) : 0;
                index++;
            }
        }
        return array;
    }
}