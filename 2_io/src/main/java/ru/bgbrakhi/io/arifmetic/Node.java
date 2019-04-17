package ru.bgbrakhi.io.arifmetic;

import java.util.ArrayList;
import java.util.List;

/**
* Узел дерева возможных выражений
* @param parent - рожительский узел
* @param chilren - дочерние узлы
* @param data - символ операции
* @param completed - признак завершенности цепочки
*/
public class Node {
    Node parent;
    List<Node> children = new ArrayList<Node>();
    String data;
    boolean completed = false;

    public Node(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (1 == 1 || completed) {
            Node node = this;
            while (!node.data.isEmpty()) {
                builder.append(node.data);
                node = node.parent;
            }
        }
        return builder.reverse().toString().trim();
    }

    /**
     * Определяет валидность цепочки от корневого узла, заканчивающейся на текущем узле :
     * после невозможны моперации и число открывающих скобок равно числу закрывающих скобок
     * @return валидность цепочки, заканчивающейся на текущем узле
     */

    public boolean isValid() {
        boolean result = false;
        if (completed) {
            String str = toString();
            result = operationCount("(") == operationCount(")");
        }
        return result;
    }

    /**
     * Вычисляет количство повторения заданно операции в цепочке
     * @param operation - операция
     * @return количство повторения заданно операции в цепочке
     */

    public int operationCount(String operation) {
        int result = 0;
        Node node = this;
        while (!node.data.isEmpty()) {
            if (operation.equals(node.data)) {
                result ++;
            }
            node = node.parent;
        }
        return result;
    }

}
