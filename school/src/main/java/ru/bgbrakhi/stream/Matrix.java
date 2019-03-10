package ru.bgbrakhi.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Matrix {
    private Integer[][] data = new Integer[3][3];

    public Matrix() {
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                data[i][j] = index++;
            }
        }
    }

    public List<Integer> toFlatList() {
        List<List<Integer>> list2d = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list2d.add(Arrays.asList(data[i]));
        }


        List<Integer> result = list2d.stream().flatMap(e -> e.stream()).collect(Collectors.toList());
        return result;
    }

}
