package ru.bgbrakhi.ood.store;

import java.util.Date;

public class Drink extends Food {
    public Drink(String name, Date expireDate, Date createDate, float price) {
        super(name, expireDate, createDate, price);
    }
}
