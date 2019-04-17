package ru.bgbrakhi.io.arifmetic;

import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.Expression;

import java.util.*;

/**
 * Analizer - объект для определения равенства комбенации чисел с помощью арифметических операций и скобок
 * заранее заданному числу
 * @param values - массив значений
 * @param value - заданное значение
 */

public class Analizer {
    private int[] values;
    private int value;

    /**
     * @param initialValues - массив комбинируемых значений БЕЗ учета порядка следования элементов
     * @param value - заданное итоговое значение
     * @return true, если комбинация задана
     */

    public boolean canBeEqualToValue(int[] initialValues, int value) {
        boolean result = false;
        this.value = value;
        int[] data = Arrays.copyOf(initialValues, initialValues.length);
        int i = 0;
        do {
            int t = data[i];
            data[i] = data[i + 1];
            data[i + 1] = t;
            i = (i + 1) % (data.length - 1);
            String str = getFirstValidVariable(data);
            if (!str.isEmpty()) {
                result = true;
                break;
            }
        } while (!Arrays.equals(initialValues, data));
        return result;
    }

    /**
     * Выводит арифметическая запись первого выражения если такое надено, в противном случае - пустая строка
     * вся фича - в объекте Expression - он вычисляет занчение по записи выражения после подстановки значений параметров
     * @param values - массив комбинируемых значений С учетом порядка следования элементов
     * @return арифметическая запись первого выражения если такое надено, в противном случае - пустая строка
     */

    private String getFirstValidVariable(int[] values) {
        this.values = values;
        String resStr = "";
        ExpressionTree tree = new ExpressionTree(values.length);
        for (Iterator it = tree.iterator(); it.hasNext();) {
            Node node = (Node) it.next();
            String str;
            String tmp = node.toString();
            if (node.isValid()) {
                str = setVariables(tmp);
                Map<String, Double> map = new HashMap<>();
                for (int i = 0; i < values.length; i++) {
                    map.put("a" + i, (double) values[i]);
                }
                Expression expr = new ExpressionBuilder(str)
                        .variables(map.keySet())
                        .build()
                        .setVariables(map);
                try {
                    if ((int) expr.evaluate() == this.value) {
                        resStr = setValues(tmp);
                        break;
                    }
                } catch (ArithmeticException e) {
                    e.printStackTrace();
                }
            }
        }
        return resStr;
    }

    /**
     * Подставляет в обезлиенное выражение переменные a0, a1, ...
     * @param vt - шаблон обезличенного выражения, например (v-v)*(v+v)
     * @return выражение с уникальными переменными, например (a0-a1)*(a2+a3)
     */

    private String setVariables(String vt) {
        int index = 0;
        String str = vt;
        int i;
        while ((i = str.indexOf("v")) != -1) {
            str = new StringBuilder(str.substring(0, i))
                    .append("a" + index++)
                    .append(str.substring(i + 1))
                    .toString();
        }
        return str;
    }

    /**
     * Подставляет в обезлиенное выражение числовые значения из массива values
     * @param vt - шаблон обезличенного выражения, например (v-v)*(v+v)
     * @return выражение с числовыми значениями, например (6-4)*(2+3)
     */
    private String setValues(String vt) {
        int index = 0;
        String str = vt;
        int i;
        while ((i = str.indexOf("v")) != -1) {
            str = new StringBuilder(str.substring(0, i))
                    .append(values[index++])
                    .append(str.substring(i + 1))
                    .toString();
        }
        return str;
    }

}
