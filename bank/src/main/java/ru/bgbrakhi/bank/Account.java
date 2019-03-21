package ru.bgbrakhi.bank;

public class Account {

    private double value = 0d;
    String requisites = "";

    public Account(double value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    @Override
    public boolean equals(Object o) {
        return (o != null && getClass() == o.getClass() && this.requisites.equals(((Account) o).requisites));
    }

    @Override
    public int hashCode() {
        return this.requisites.hashCode();
    }

    public void incBalance(double amount) {
        value += amount;
    }

    public void decBalance(double amount) {
        value -= amount;
    }
}
