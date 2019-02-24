package ru.bgbrakhi.tracker;


import java.lang.System;
import java.util.Arrays;
import java.util.Random;

import static java.lang.System.arraycopy;

// 2. static field. Lazy loading.

public class Tracker2 {

	private static Tracker2 instance;

	private Tracker tracker;

	private Tracker2() {
		tracker = new Tracker();
	}

	public static Tracker2 getInstance() {
		if (instance == null) {
			synchronized (Tracker2.class) {
				instance = new Tracker2();
			}	
		} 
		return instance;
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
	public Item[] findAll() {
		return tracker.findAll();
	}
	
	/**
	* ищет заявки по имени
	*/
	public Item[] findByName(String key) {
		return tracker.findByName(key);
	}
	
	/**
	* ищет заявки по Id
	*/
	public Item findById(String id) {
		return tracker.findById(id);
	}
}
