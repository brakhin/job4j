package ru.bgbrakhi.ood.store;

import java.util.Date;

import static ru.bgbrakhi.ood.store.DispatchDiapason.TYPE_SHOP;
import static ru.bgbrakhi.ood.store.DispatchDiapason.TYPE_SHOP_DISCOUNT;

public class Shop extends BasicStore {
    public Shop(BasicStore component) {
        super(component);
    }

    @Override
    public boolean analizeGoods(Date onDate, BasicStore store, Food food) {
        boolean result = false;
        Integer type = new DispatchDiapason().init(onDate).getType(food);
        if (type == TYPE_SHOP) {
            result = moveToStore(store, food);
        } else if (type == TYPE_SHOP_DISCOUNT) {
            food.setDiscount(0.20f);
            result = moveToStore(store, food);
        } else {
            result = component.analizeGoods(onDate, store, food);
        }
        return result;
    }

}
