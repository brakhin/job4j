package ru.bgbrakhi.tracker;

import java.util.ArrayList;
import java.util.List;

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
	private final ITracker tracker;

	/**
	 * Конструктор, устанавливающий зависимости
	 * @param input - пользовательский ввод
	 * @param tracker - хранилище заявок
	 */
	public StartUI(Input input, ITracker tracker) {
		this.input = input;
		this.tracker = tracker;
	}

	/**
	 * Точка входа в приложение
	 */
	public static void main(String[] args) {
		new StartUI(new ValidateInput(new ConsoleInput()), new TrackerSQL(null)).init();
	}

	/**
	 * Реализация выбора действий
	 */
	public void init() {
		MenuTracker menu = new MenuTracker(this.input, this.tracker);
		menu.fillActions();
		int[] range = new int[menu.getActionsLentgh()];
		for (int i = 0; i < menu.getActionsLentgh(); i++) {
			range[i] = i;
		}
		do {
			menu.show();
			menu.select(input.ask("select:", range));
		} while (!"y".equals(this.input.ask("Exit?(y): ")));
	}

}
