package ru.bgbrakhi.io.arifmetic;

import java.util.*;

/**
 * Дерево возожных выражений с итератором
 * Обозначения :
 * v : переменная
 * "/*+-" : арифметические операции
 * "()" : скобки
 */

public class ExpressionTree implements Iterable<Node>{
    private static final String operations = "v+-*/()";

    private final Node root;
    private int length;

    public ExpressionTree(int length) {
        this.length = length;
        root =  new Node("");
    }

    private void calcChilds(Queue<Node> queue, Node root) {
        int added = 0;
        Node node;
        for (int i = 0; i < operations.length(); i++) {
            String operation = operations.substring(i, i + 1);
            if (validOperation(operation, root)) {
                added++;
                node = new Node(operation);
                node.parent = root;
                root.children.add(node);
                queue.add(node);
            }
            if (i == operations.length() - 1 && added == 0) {
                root.completed = true;
            }
        }
    }

    /**
     * Валидатор допустимости операции в заданном узле
     * @param operation
     * @param root
     * @return
     */
    private boolean validOperation(String operation, Node root) {
        boolean result = true;

        switch (operation) {
            case "+" :
                result = Arrays.asList("v", ")").contains(root.data) && (root.operationCount("v") < length) ;
                break;
            case "-" :
                result = Arrays.asList("v", ")").contains(root.data) && (root.operationCount("v") < length) ;
                break;
            case "*" :
                result = Arrays.asList("v", ")").contains(root.data) && (root.operationCount("v") < length) ;
                break;
            case "/" :
                result = Arrays.asList("v", ")").contains(root.data) && (root.operationCount("v") < length) ;
                break;
            case "(" :
                result = root.data.isEmpty()
                        ||
                        Arrays.asList("+", "-", "*", "/", "(").contains(root.data)
                        &&
                          root.operationCount("(") < length - 2
                        &&
                        (root.operationCount("v") < length - 1);
                break;
            case ")" :
                result = Arrays.asList("v").contains(root.data) && root.operationCount(")") < root.operationCount("(");
                break;
            case "v" :
                result = root.data.isEmpty() || Arrays.asList("+", "-", "*", "/", "(").contains(root.data) && (root.operationCount("v") < length);
                break;
            default :
        }
        return result;
    }

    @Override
    public Iterator<Node> iterator() {
        return new Iterator<Node>() {
            Queue<Node> data = new LinkedList<>(Collections.singleton(root));

            @Override
            public boolean hasNext() {
                return !data.isEmpty();
            }

            @Override
            public Node next() {
                Node node = data.poll();
                calcChilds(data, node);
                return node;
            }
        };
    }
}
