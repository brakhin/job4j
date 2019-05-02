package ru.bgbrakhi.ood.store;

import java.util.Date;

public class ConrolQuality {

    private BasicStore pool;

    public ConrolQuality(BasicStore pool) {
        this.pool = pool;
    }

    public void analizeStore(Date curDate) {
        int index = 0;
        while (index < pool.size()) {
            Food food = pool.getFood(index);
            float expPercent = food.getExpirePercent(curDate);
            if (pool.component != null) {
                if (!pool.component.analizeGoods(pool, expPercent, food)) {
                    index++;
                }
            }
        }
    }
}

