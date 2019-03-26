package ru.bgrakhi.list;

/**
 * Класс SimpleArrayList.
 */
public class SimpleArrayList<E> {

    private int size;
    private Node<E> first;

    /**
     * Метод вставляет в начало списка данные.
     */
    public void add(E data) {
        Node<E> newLink = new Node<>(data);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    /**
     * Реализовать метод удаления первого элемент в списке.
     */
    public E delete() {
        E result = first.data;
        this.first = this.first.next;
        this.size--;
        return result;
    }

    /**
     * Метод получения элемента по индексу.
     */
    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }

    /**
     * Метод получения размера коллекции.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Класс предназначен для хранения данных.
     */
    private static class Node<E> {

        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }

    public Node<E> getFirst() {
        return this.first;
    }

    public boolean hasCycle(Node<E> first) {
        boolean result = false;
        Node<E> node = first;

        while (true) {
            if (node.next == null) {
                result = false;
                break;
            }
            if (node.next == node) {
                result = true;
                break;
            }
            node = node.next;
        }
        return result;
    }
}