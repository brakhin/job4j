package ru.bgbrakhi.ood.store.stores;

import ru.bgbrakhi.ood.store.food.Food;
import ru.bgbrakhi.ood.store.food.FoodExt;

public class WarehouseExt extends BasicStore {

    protected IStore component;

    protected WarehouseExt warehouseRes = null;

    public WarehouseExt(IStore component) {
        this.component = component;
    }

    public BasicStore getComponent() {
        return (BasicStore) this.component;
    }

    public WarehouseExt getWarehouseRes() {
        return warehouseRes;
    }

    private boolean hasStorePlace() {
        return true;
    }

    @Override
    public void add(Food food) {
        if (!hasStorePlace() && warehouseRes == null) {
            warehouseRes = new WarehouseExt(component);
            warehouseRes.add(food);
        } else {
            if (((FoodExt) food).isNeedLowTemp()) {
                component.add(food);
            } else {
                super.add(food);
            }
        }
    }
}
