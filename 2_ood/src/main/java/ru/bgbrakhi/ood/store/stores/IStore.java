package ru.bgbrakhi.ood.store.stores;

import ru.bgbrakhi.ood.store.food.Food;

import java.util.Date;
import java.util.List;

public interface IStore {
    void remove(Food food);
    void add(Food food);
    List<Food> expired(Date curDate);
    void addAll(List<Food> list);
    void clear();
}
