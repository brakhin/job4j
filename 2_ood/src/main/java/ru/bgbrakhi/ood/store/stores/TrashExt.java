package ru.bgbrakhi.ood.store.stores;

import ru.bgbrakhi.ood.store.food.Food;
import ru.bgbrakhi.ood.store.food.FoodExt;

public class TrashExt extends BasicStore {

    IStore component;

    public TrashExt(IStore component) {
        this.component = component;
    }

    @Override
    public void add(Food food) {
        if (((FoodExt) food).isCanReproduct()) {
            component.add(food);
        } else {
            super.add(food);
        }
    }
}
