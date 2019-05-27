package ru.bgbrakhi.ood.store.food;

import java.util.Date;

public class FoodExt extends Food {
    protected boolean canReproduct = false;
    protected boolean needLowTemp = false;

    public FoodExt(String name, Date expireDate, Date createDate, float price) {
        super(name, expireDate, createDate, price);
    }

    public boolean isCanReproduct() {
        return canReproduct;
    }

    public boolean isNeedLowTemp() {
        return needLowTemp;
    }
}
