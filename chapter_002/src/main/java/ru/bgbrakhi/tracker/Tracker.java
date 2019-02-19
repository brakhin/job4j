package ru.bgbrakhi.tracker;

import java.lang.System;
import java.util.Arrays;

import static java.lang.System.arraycopy;

public class Tracker {
	private Item[] items = new Item[100];
	
	/**
	* Позиция последней заявки
	*/
	private int position = 0;
	
	/**
	* добавляет заявку в трекер
	*/
	public Item add(Item item) {
		items[position++] = item;	
		return item;
	}
	
	/**
	* заменяет заявку в трекере
	*/
	public boolean replace(String id, Item item) {
		for (int i = 0; i < position; i++) {
			if (items[i].getId().equals(id)) {
				item.setId(items[i].getId());
				items[i] = item;
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
		int pos = -1;
		for (int i = 0; i < position; i++) {
			if (items[i].getId().equals(id)) {
				pos = i;
				break;
			}
		}
		Item[] dest = new Item[position - 1];
		arraycopy(items, 0, dest, 0, pos);
		arraycopy(items, pos + 1, dest, pos, position - pos - 1);
		position--;
		items = dest;
		return true;	
	}
	
	/**
	* выводит все заявки трекера
	*/
	public Item[] findAll() {
		return Arrays.copyOf(items, position);
	}
	
	/**
	* ищет заявки по имени
	*/
	public Item[] findByName(String key) {
		Item[] result = new Item[position];
		int len = 0;
		for (int i = 0; i < position; i++) {
			if (items[i].getName().equals(key)) {
				result[len++] = items[i];				
			}
		}
		return Arrays.copyOf(result, len);
	}
	
	/**
	* ищет заявки по Id
	*/
	public Item findById(String id) {
		Item result = null;
		for (int i = 0; i < position; i++) {
			if (items[i].getId().equals(id)) {
				result = items[i];
				break;
			}
		}
		return result;
	}
}
