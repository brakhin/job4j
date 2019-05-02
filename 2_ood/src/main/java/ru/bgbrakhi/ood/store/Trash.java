package ru.bgbrakhi.ood.store;

public class Trash extends BasicStore {
    public Trash(BasicStore component) {
        super(component);
    }

    @Override
    public boolean analizeGoods(BasicStore store, float expPercent, Food food) {
        return moveToStore(store, food);
    }
}
