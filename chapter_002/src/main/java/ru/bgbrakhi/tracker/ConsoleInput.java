package ru.bgbrakhi.tracker;

import java.util.*;

public class ConsoleInput implements Input {
	private Scanner scanner = new Scanner(System.in);
	public String ask(String question) {
		System.out.print(question);
		return scanner.nextLine();
	}

	@Override
	public int ask(String question, int[] range) {
		int key = Integer.valueOf(this.ask(question));
		boolean exists = false;
		for (int value : range) {
			if (value == key) {
				exists = true;
				break;
			}
		}
		return exists ? key : -1;
	}
}
