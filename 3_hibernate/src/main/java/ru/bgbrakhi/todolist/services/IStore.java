package ru.bgbrakhi.todolist.services;

import ru.bgbrakhi.todolist.models.Item;

import java.util.List;

public interface IStore {
    void addItem(Item item);
    void updateItem(Item item);
    List<Item> getAll();
    Item getItem(Long id);
}
