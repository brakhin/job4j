package ru.bgrakhi.hotels;

import java.util.*;

public class Hotels {

    public int[] calcStars(int[] rates) throws Exception {
        int[] result = new int[rates.length];
        Map<Integer, Integer> data = new TreeMap<>();
        for (int value : rates) {
            data.put(value, 0);
        }
        int chainlen = (int) (data.size() / 5);
        Object[] keys = data.keySet().toArray();
        for (int i = 0; i < data.size(); i++) {
            data.put((Integer) keys[i], i / chainlen + 1);
        }
        int index = 0;
        for (int rate : rates) {
            result[index++] = data.get(rate);
        }
        return result;
    }
}
