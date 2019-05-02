package ru.bgbrakhi.ood.store;

import java.util.Date;
import java.util.Objects;

public class Food {
    private String name;
    private Date expireDate;
    private Date createDate;
    private float price;
    private float discount;

    public Food(String name, Date expireDate, Date createDate, float price) {
        this.name = name;
        this.expireDate = expireDate;
        this.createDate = createDate;
        this.price = price;
    }

    public float getExpirePercent(Date curDate) {
        Date now = new Date();
        return (float) (curDate.getTime() - createDate.getTime()) / (expireDate.getTime() - createDate.getTime());
    }

    public void setDiscount(float value) {
        this.discount = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Food food = (Food) o;
        return Float.compare(food.price, price) == 0
                && Float.compare(food.discount, discount) == 0
                && Objects.equals(name, food.name)
                && Objects.equals(expireDate, food.expireDate)
                && Objects.equals(createDate, food.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, expireDate, createDate, price, discount);
    }

    public float getPrice() {
        return price * (1 - discount);
    }
}
