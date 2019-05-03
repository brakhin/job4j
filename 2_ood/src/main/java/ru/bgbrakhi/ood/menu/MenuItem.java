package ru.bgbrakhi.ood.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MenuItem implements IMenuItem {
    private int id = 0;
    private String desc;
    private Consumer action = null;
    private IMenuItem parent = null;

    private List<IMenuItem> children = new ArrayList<>();

    public MenuItem(int id, String desc, Consumer<Integer> action) {
        this.id = id;
        this.desc = desc;
        this.action = action;
    }

    public MenuItem(String desc) {
        this.desc = desc;
    }

    public MenuItem() {
    }

    @Override
    public void click() {
        if (action != null) {
            action.accept(id);
        }
    }

    @Override
    public IMenuItem add(IMenuItem item) {
        item.putParent(this);
        children.add(item);
        return this;
    }

    @Override
    public void print() {
        if (this.desc != null && !this.desc.isEmpty()) {
            System.out.println(String.format("%s%s", "-".repeat(level()), this.id == 0 ? this.desc.toUpperCase() : this.desc));
        }
        children.forEach(iMenuItem -> iMenuItem.print());
    }

    @Override
    public void putParent(IMenuItem parent) {
        this.parent = parent;
    }

    @Override
    public int level() {
        return parent == null ? -1 : parent.level() + 1;
    }

    @Override
    public int id() {
        return this.id;
    }

    @Override
    public List<IMenuItem> children() {
        return this.children;
    }
}
