package ru.bgbrakhi.tracker;


import java.lang.System;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.arraycopy;

public class Tracker {
	List<Item> items = new ArrayList<>();
	
	/**
	* Позиция последней заявки
	*/
	private int position = 0;
	
	/**
	* добавляет заявку в трекер
	*/
	public Item add(Item item) {
		items.set(position++, item);
		return item;
	}
	
	/**
	* заменяет заявку в трекере
	*/
	public boolean replace(String id, Item item) {
		for (int i = 0; i < position; i++) {
			if (items.get(i).getId().equals(id)) {
				item.setId(items.get(i).getId());
				items.set(i, item);
				return true;
			}	
		}	
		return false;
	}

	/**
	* удаляет заявку из трекера
	* вначале определяется индекс найденной заявки
	* затем массив Item[] схлопывается в найденной позиции и обрезается с хвоста
	*/
	public boolean delete(String id) {
		boolean result = false;
		int pos = -1;
		for (int i = 0; i < position; i++) {
			if (items.get(i).getId().equals(id)) {
				pos = i;
				System.arraycopy(items, pos + 1, items, pos, items.size() - pos - 1);
				position--;
				result = true;
				break;
			}
		}
		return result;
	}
	
	/**
	* выводит все заявки трекера
	*/
	public List<Item> findAll() {
		return items;
	}
	
	/**
	* ищет заявки по имени
	*/
	public List<Item> findByName(String key) {
		List<Item> result = new ArrayList<>();

		int len = 0;
		for (int i = 0; i < position; i++) {
			if (items.get(i).getName().equals(key)) {
				result.set(len++, items.get(i));
			}
		}
		return result;
	}
	
	/**
	* ищет заявки по Id
	*/
	public Item findById(String id) {
		Item result = null;
		for (int i = 0; i < position; i++) {
			if (items.get(i).getId().equals(id)) {
				result = items.get(i);
				break;
			}
		}
		return result;
	}
}
