package ru.bgbrakhi.ood.store;

import com.sun.istack.Pool;
import ru.bgbrakhi.ood.store.food.Food;
import ru.bgbrakhi.ood.store.stores.*;

import java.util.ArrayList;
import ru.bgbrakhi.ood.store.food.Food;
import ru.bgbrakhi.ood.store.stores.BasicStore;
import ru.bgbrakhi.ood.store.stores.WarehouseExt;

import java.util.Date;
import java.util.List;

public class ConrolQuality {

    protected List<BasicStore> warehouse;
    protected BasicStore shop;
    protected BasicStore trash;
//    protected BasicStore pool;

    public ConrolQuality(List<BasicStore> warehouse) {
        this.warehouse = warehouse;
        this.shop = new Shop();
        this.trash = new Trash();
//        this.pool = new Pool();
    }

    public BasicStore getShop() {
        return shop;
    }

    public BasicStore getTrash() {
        return trash;
    }

    protected boolean moveToStore(BasicStore from, BasicStore to, Food food) {
        boolean result = false;
        if (from != to) {
            from.remove(food);
            to.add(food);
            result = true;
        }
        return result;
    }

    public void analizeStore(Date curDate, BasicStore store) {
        int index = 0;
        int warehouseIndex = 0;
        while (index < store.size()) {
            Food food = store.get(index);
            float expPercent = food.getExpirePercent(curDate);
            if (expPercent <= 0.25) {
                warehouseIndex = (warehouseIndex + 1) % warehouse.size();
                if (!moveToStore(store, warehouse.get(warehouseIndex), food)) {
                    index++;
                }
            } else if (expPercent > 0.25 && expPercent <= 0.75) {
                if (!moveToStore(store, shop, food)) {
                    index++;
                }
            } else if (expPercent > 0.75 && expPercent <= 1) {
                food.setDiscount(0.20f);
                if (!moveToStore(store, shop, food)) {
                    index++;
                }
            } else {
                if (!moveToStore(store, trash, food)) {
                    index++;
                }
            }
        }
    }

    public void resort(Date curDate) {
        List<Food> expiredFood;
        List<Food> validFood = new ArrayList<>();
        for (int i = 0; i < warehouse.size(); i++) {
            BasicStore warehouse_ = warehouse.get(i);
            expiredFood = ((WarehouseExt) warehouse_).getComponent().expired(curDate);
            trash.addAll(expiredFood);
            validFood.addAll(((WarehouseExt) warehouse_).getComponent().getData());
            ((WarehouseExt) warehouse_).getComponent().clear();
            do {
                expiredFood = warehouse_.expired(curDate);
                trash.addAll(expiredFood);
                validFood.addAll(warehouse_.getData());
                warehouse_.clear();
                warehouse_ = ((WarehouseExt) warehouse_).getWarehouseRes();
            } while (warehouse != null);
        }
        expiredFood = shop.expired(curDate);
        trash.addAll(expiredFood);
        validFood.addAll(shop.getData());
        shop.clear();
        BasicStore store = new BasicStore();
        store.addAll(validFood);
        analizeStore(curDate, store);
    }
}
