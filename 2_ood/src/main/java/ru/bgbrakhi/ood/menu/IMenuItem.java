package ru.bgbrakhi.ood.menu;

import java.util.List;

public interface IMenuItem {
    void click();
    IMenuItem add(IMenuItem item);
    void print();
    void putParent(IMenuItem parent);
    int level();
    int id();
    List<IMenuItem> children();
}
