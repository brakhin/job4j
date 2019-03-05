package ru.job4j.list;

import java.util.Comparator;


public class ListCompare implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
        int result = 0;
        for (int i = 0; i < Math.max(left.length(), right.length()); i++) {
            if (i > left.length() - 1) {
                result = -1;
                break;
            }
            if (i > right.length() - 1) {
                result = 1;
                break;
            }
            char charleft = left.charAt(i);
            char charright = right.charAt(i);

            int compareres = Character.compare(charleft, charright);
            if (compareres != 0) {
                result = compareres;
                break;
            }
        }
        return result;
    }
}