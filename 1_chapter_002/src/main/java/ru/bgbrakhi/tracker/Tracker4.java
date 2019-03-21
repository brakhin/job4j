package ru.bgbrakhi.tracker;


import java.lang.System;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static java.lang.System.arraycopy;


public class Tracker4 {

	private Tracker tracker;

	private static final class Handler {
		private static final Tracker4 INSTANCE = new Tracker4();
	}	
	
	public static Tracker4 getInstance() {
		return Handler.INSTANCE;
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
