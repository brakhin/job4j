package ru.bgbrakhi.tracker;


import java.lang.System;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.arraycopy;

public class Tracker {
	private List<Item> items = new ArrayList<>();
	
	/**
	* добавляет заявку в трекер
	*/
	public Item add(Item item) {
		items.add(item);
		return item;
	}
	
	/**
	* заменяет заявку в трекере
	*/
	public boolean replace(String id, Item item) {
		for (int i = 0; i < items.size(); i++) {
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
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getId().equals(id)) {
				result = (items.remove(i) != null);
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
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getName().equals(key)) {
				result.add(items.get(i));
			}
		}
		return result;
	}
	
	/**
	* ищет заявки по Id
	*/
	public Item findById(String id) {
		Item result = null;
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getId().equals(id)) {
				result = items.get(i);
				break;
			}
		}
		return result;
	}
}
