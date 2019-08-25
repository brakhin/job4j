package ru.bgbrakhi.todolist.services;

import ru.bgbrakhi.todolist.models.Item;

import java.util.List;

public class Validator implements IStore {
    private final ItemStorage store = ItemStorage.getInstance();
    private final static Validator INSTANCE = new Validator();

    public static Validator getInstance() {
        return INSTANCE;
    }

    @Override
    public void addItem(Item item) {
        store.addItem(item);
    }

    @Override
    public void updateItem(Item item) {
        store.updateItem(item);
    }

    @Override
    public List<Item> getAll() {
        return store.getAll();
    }

    @Override
    public Item getItem(Long id) {
        return store.getItem(id);
    }
}
