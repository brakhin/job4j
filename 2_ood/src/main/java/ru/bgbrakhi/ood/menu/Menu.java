package ru.bgbrakhi.ood.menu;

import java.util.*;

public class Menu {
    private IMenuItem root;

    public Menu() {
        buildMenu();
    }

    private void buildMenu() {
        root = new MenuItem()
                .add(new MenuItem(1, "Item_1", this::menuHandler)
                         .add(new MenuItem("Group_Item_1")
                                        .add(new MenuItem(2, "Item_1_1", this::menuHandler))
                                        .add(new MenuItem(3, "Item_1_2", this::menuHandler))
                                        .add(new MenuItem("Group_Item_1_1")
                                                .add(new MenuItem(4, "Item_1_1_1", this::menuHandler))
                                                .add(new MenuItem(5, "Item_1_1_2", this::menuHandler))
                                        )
                                )
                )
                .add(new MenuItem(6, "Item_2", this::menuHandler))
                .add(new MenuItem(7, "Item_3", this::menuHandler));
    }

    private void menuHandler(Integer id) {
        System.out.println(String.format("Item #%d was clicked.", id));
    }

    public void print() {
        root.print();
    }

    public void clickItem(int id) {
        Queue<IMenuItem> queue = new LinkedList<>(Collections.singleton(root));
        while (!queue.isEmpty()) {
            IMenuItem item = queue.poll();
            if (item.id() == id) {
                item.click();
                break;
            } else {
                queue.addAll(item.children());
            }
        }
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.print();
        menu.clickItem(5);
    }
}
