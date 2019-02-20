package ru.bgbrakhi.tracker;


import ru.bgbrakhi.models.*;

public class StartUI {
	/** 
	* Строковые константы для пунктов меню
	*/
	private static final String MENU_ADD = "0";
	private static final String MENU_SHOW_ALL = "1";
	private static final String MENU_EDIT = "2";
	private static final String MENU_DELETE = "3";
	private static final String MENU_FIND_ITEM_BY_ID = "4";
	private static final String MENU_FIND_ITEMS_BY_NAME = "5";
	private static final String MENU_EXIT = "6";

	/**
	* Ввод пользовательских данных
	*/
	private final Input input;

	/**
	* Хранилище заявок
	*/
	private final Tracker tracker;
	
	/**
	* Конструктор, устанавливающий зависимости
	* @param input - пользовательский ввод
	* @param tracker - хранилище заявок
	*/
	public StartUI(Input input, Tracker tracker) {
		this.input = input;
		this.tracker = tracker;
	}
	
	/**
	* Точка входа в приложение
	*/
	public static void main(String[] args) {
		new StartUI(new ConsoleInput(), new Tracker()).init();
	}
	
	/**
	* Реализация выбора действий
	*/
	public void init() {
		MenuTracker menu = new MenuTracker(this.input, this.tracker);
		menu.fillActions();
		do {
			menu.show();
			int key = Integer.valueOf(input.ask("Select : "));
			menu.select(key);			
		} while (!"y".equals(this.input.ask("Exit? y/n")));
	}
	
	private void createItem() {
		System.out.println("--- Добавление новой заявки ---");
		String name = input.ask("Имя заявки");
		String desc = input.ask("Описание заявки");
		Item item = new Item(name, desc);
		tracker.add(item);
		System.out.println("--- Заявка с Id " + item.getId() + " добавлена ---");	
	}
	
	private void showMenu() {
		System.out.println("Выберите действие : ");
		System.out.println("   0. Add new Item");
		System.out.println("   1. Show all items");
		System.out.println("   2. Edit item");
		System.out.println("   3. Delete item");
		System.out.println("   4. Find item by Id");
		System.out.println("   5. Find items by name");
		System.out.println("   6. Exit Program");
	}
}
