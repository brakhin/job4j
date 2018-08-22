package ru.job4j.loop;

/**
 * @author Boris Brakhin (mailto:brakhin@mail.ru)
 * @version $Id$
 * @since 19.08.2018
 */public class Factorial {
    /**
     * Факториал числа
     * @param value число, факториал которого вычисляется
     * @return
     */
    public int calc(int value) {
        int result = 1;
        for (int i = 1; i <= value; i++) {
           result *= i;
        }
        return result;
    }
}
