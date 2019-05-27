package ru.bgbrakhi.ood.store.stores;

import ru.bgbrakhi.ood.store.food.Food;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BasicStore implements IStore {
    protected List<Food> data;

    public BasicStore() {
        data = new ArrayList<>();
    }


    public Food get(int index) {
        Food result = null;
        if (index < data.size()) {
            result = data.get(index);
        }
        return result;
    }

    public Object[] asArray() {
        return data.toArray();
    }

    @Override
    public void remove(Food food) {
        data.remove(food);
    }

    @Override
    public void add(Food food) {
        data.add(food);
    }



    @Override
    public List<Food> expired(Date curDate) {
        List<Food> result = new ArrayList<>();
        float expPercent;
        for (Food food : data) {
            expPercent = food.getExpirePercent(curDate);
            if (expPercent > 1) {
                remove(food);
                result.add(food);
            }
        }
        return result;
    }

    @Override
    public void addAll(List<Food> list) {
        data.addAll(list);
    }

    @Override
    public void clear() {
        data.clear();
    }

    public List<Food> getData() {
        return data;
    }

    public int size() {
        return data.size();
    }

}
