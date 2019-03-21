package ru.job4j.loop;

/**
 * @author Boris Brakhin (mailto:brakhin@mail.ru)
 * @version $Id$
 * @since 19.08.2018
 */public class Counter {
    /**
     * Суммма четных чисел в диапазоне.
     * @param start начало диапазона.
     * @param finish конец диапазона.
     * @return
     */
    public int add(int start, int finish) {
        int sum = 0;
        for (int i = start; i <= finish; i++) {
           if (i % 2 == 0) {
               sum += i;
           }
        }
        return sum;
    }
}
