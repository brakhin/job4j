package ru.bgbrakhi.ood.store;

import java.util.Date;

import static ru.bgbrakhi.ood.store.DispatchDiapason.TYPE_WAREHOUSE;

public class Warehouse extends BasicStore {

    public Warehouse(BasicStore component) {
        super(component);
    }

    @Override
    public boolean analizeGoods(Date onDate, BasicStore store, Food food) {
        boolean result = false;
        Integer type = new DispatchDiapason().init(onDate).getType(food);
        if (type == TYPE_WAREHOUSE) {
            result = moveToStore(store, food);
        } else {
            result = component.analizeGoods(onDate, store, food);
        }
        return result;
    }

}
