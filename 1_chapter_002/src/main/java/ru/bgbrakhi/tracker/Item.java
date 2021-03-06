package ru.bgbrakhi.tracker;

import java.lang.System;
import java.util.Random;

public class Item {
	private String id;
	
	private String name;
	private String desc;
	private long created;

	public String getComments() {
		return comments != null && comments.length > 0 ? comments[0] : "";
	}

	private String[] comments;


	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public String getDesc() {
		return this.desc;
	}

	public long getCreated() {
		return this.created;
	}

	public Item(String name, String desc) {
		this.name = name;
		this.desc = desc;
		this.created = System.currentTimeMillis();
		setRandomId();
	}		

	public Item() {
		 setRandomId();
	}
	
	public void setRandomId() {
		Random random = new Random();
		this.id = String.valueOf(random.nextInt());
	}	
	
	public void show() {
		System.out.println(String.format("%s(%s)", getName(), getDesc()));
	}
	
}