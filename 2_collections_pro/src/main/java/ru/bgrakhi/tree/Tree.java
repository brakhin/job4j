package ru.bgrakhi.tree;

import java.util.*;
import java.util.stream.Collectors;

public class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    private Node<E> root;

    public Tree(E rootVal) {
        this.root = new Node<>(rootVal);
    }

    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> parentNode = findBy(parent);
        Optional<Node<E>> childNode = findBy(child);
        final boolean[] result = {false};
        parentNode.ifPresent(n -> {
            n.add((childNode.isPresent() ? childNode.get() : new Node<E>(child)));
            result[0] = true;
        });
        return result[0];
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> result = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(root);
        while (!data.isEmpty()) {
            Node<E> element = data.poll();
            if (element.eqValue(value)) {
                result = Optional.of(element);
                break;
            }
            for (Node child : element.leaves()) {
                data.offer(child);
            }
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            Queue<Node<E>> data = new LinkedList<>(Collections.singleton(root));

            private void addNodeToQueue(Node<E> node)  {
                data.offer(node);
                for (Node child : node.leaves()) {
                    addNodeToQueue(child);
                }
            }

            @Override
            public boolean hasNext() {
                return data.isEmpty();
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) data.poll();
            }
        };
    }

    public boolean isBinary() {
        boolean result = true;
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(root);
        while (!data.isEmpty()) {
            Node<E> element = data.poll();
            if (element.leaves().size() > 2) {
                result = false;
                break;
            }
            for (Node child : element.leaves()) {
                data.offer(child);
            }
        }
        return result;
    }
}
