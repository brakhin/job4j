package ru.job4j.search;

import java.util.LinkedList;

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позиция определять по полю приоритет.
     * Для вставик использовать add(int index, E value)
     * @param task задача
     */
    public void put(Task task) {
        //TODO добавить вставку в связанный список.

        boolean processed = false;
        int index = 0;
        for (int i = 0; i < tasks.size(); i++) {
            index = i;
            if (task.getPriority() <= tasks.get(i).getPriority()) {
                tasks.add(index, task);
                processed = true;
                break;
            }
        }
        if (!processed) {
            tasks.addLast(task);
        }
    }



    public Task take() {
        return this.tasks.poll();
    }
}