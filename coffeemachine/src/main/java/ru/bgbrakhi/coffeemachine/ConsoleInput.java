package ru.bgbrakhi.coffeemachine;


import java.util.*;

public class ConsoleInput implements Input {
	private Scanner scanner = new Scanner(System.in);

	public String ask(String question) {
		System.out.print(question);
		return scanner.nextLine();
	}

	public int askint(String question) {
		int result = 0;
		boolean valid = true;
		do {
			valid = true;
			try {
				result = Integer.valueOf(ask(question));
			} catch (NumberFormatException e) {
				System.out.println("Некорректный ввод, введите число !");
				valid = false;
				result = 0;
			}
		} while(!valid);
		return result;
	}
}
