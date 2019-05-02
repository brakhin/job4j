package ru.bgbrakhi.ood.store;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class BasicStore {
    private List<Food> data;
    protected BasicStore component;

    public BasicStore(BasicStore component) {
        this.component = component;
        data = new ArrayList<>();
    }

    public void remove(Food food) {
        data.remove(food);
    }

    public void add(Food food) {
        data.add(food);
    }

    public int size() {
        return data.size();
    }

    public Food getFood(int index) {
        Food result = null;
        if (index < data.size()) {
            result = data.get(index);
        }
        return result;
    }

    public Object[] asArray() {
        return data.toArray();
    }

    public boolean moveToStore(BasicStore from, Food food) {
        boolean result = false;
        if (from != this) {
            from.remove(food);
            add(food);
            result = true;
        }
        return result;
    }

    public abstract boolean analizeGoods(Date onDate, BasicStore store, Food food);
}
