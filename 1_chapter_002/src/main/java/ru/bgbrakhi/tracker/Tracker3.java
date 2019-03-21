package ru.bgbrakhi.tracker;


import java.lang.System;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static java.lang.System.arraycopy;

//3. static final field. Eager loading.

public class Tracker3 {
	private final static Tracker3 INSTANCE = new Tracker3();

	private Tracker tracker;

	public static Tracker3 getInstance() {
		return INSTANCE;
	}

	private int tag = new Random().nextInt();
	public int getTag() {
		return this.tag;
	}

	public Item add(Item item) {
		return tracker.add(item);
	}

	/**
	 * заменяет заявку в трекере
	 */
	public boolean replace(String id, Item item) {
		return tracker.replace(id, item);
	}

	/**
	 * удаляет заявку из трекера
	 * вначале определяется индекс найденной заявки
	 * затем массив Item[] схлопывается в найденной позиции и обрезается с хвоста
	 */
	public boolean delete(String id) {
		return tracker.delete(id);
	}

	/**
	 * выводит все заявки трекера
	 */
	public List<Item> findAll() {
		return tracker.findAll();
	}

	/**
	 * ищет заявки по имени
	 */
	public List<Item>  findByName(String key) {
		return tracker.findByName(key);
	}

	/**
	 * ищет заявки по Id
	 */
	public Item findById(String id) {
		return tracker.findById(id);
	}

}
