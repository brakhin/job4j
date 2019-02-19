package ru.bgbrakhi.models;

public class Square implements Shape {
	@Override
	public String draw() {
		StringBuilder pic = ne StringBuilder();
		pic.append("+++++");
		pic.append("+   +");
		pic.append("+++++");
		return pic.toString();
	}
}