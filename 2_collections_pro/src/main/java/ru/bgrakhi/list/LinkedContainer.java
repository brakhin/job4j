package ru.bgrakhi.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedContainer<E> implements Iterable<E> {

    private Node<E> first;
    private int modCount = 0;

    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }

    public void add(E value) {
        Node<E> node = new Node<>(value);
        node.next = first;
        first = node;
    }

    public E get(int index) {
        Node<E> result = first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private int expectedModCount = modCount;
            Node<E> node = first;

            @Override
            public boolean hasNext() {
                return node.next != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else if (expectedModCount < modCount)
                    throw new ConcurrentModificationException();
                node = node.next;
                return (E) node.data;
            }
        };
    }
}
