package ru.bgbrakhi.models;

public class Paint {
	public void draw(Shape shape) {
		System.out.println(shape.draw());
	}
	
	public static void main(String[] args) {
		Shape triangle = new Triangle();
		Shape square = new Square();
		Paint paint = new Paint();
		paint.draw(triangle);
		paint.draw(square);
	}
}