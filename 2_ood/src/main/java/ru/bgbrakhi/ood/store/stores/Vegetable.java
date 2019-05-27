package ru.bgbrakhi.ood.store.stores;

import ru.bgbrakhi.ood.store.food.FoodExt;

import java.util.Date;

public class Vegetable extends FoodExt {

    public Vegetable(String name, Date expireDate, Date createDate, float price) {
        super(name, expireDate, createDate, price);
        needLowTemp = true;
    }
}
