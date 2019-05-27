package ru.bgbrakhi.ood.store.food;

import java.util.Date;

public class MeatExt extends FoodExt {
    public MeatExt(String name, Date expireDate, Date createDate, float price) {
        super(name, expireDate, createDate, price);
        canReproduct = true;

    }
}
