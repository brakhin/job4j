package ru.bgbrakhi.coffeemachine;


import java.util.StringJoiner;

public class CoffeeMachine {
//	private Input input;
	private int[] result;

	// массив номиналов сдачи (МОНЕТЫ)
	private int[] coins = null;
	
	public CoffeeMachine(int[] coins) {
		this.coins = coins;
	}
	
	// вычисление набора монет для сдачи
	private int[] changes(int value, int price) {
		int[] result = new int[coins.length];
		int remainedsum = value - price;
		int index = coins.length - 1;
		while (remainedsum > 0) {
			int coinamount = remainedsum / coins[index];
			result[index] = coinamount;
			remainedsum -= coinamount * coins[index];
			index--;
		}
		return result;
	}

	public String getResult() {
		StringJoiner builder = new StringJoiner(" + ", "Сдача : ", "");
		for (int i = 0; i < result.length; i++) {
			if (result[i] > 0) {
				builder.add(String.format("%d * %d руб.", result[i], coins[i]));
			}
		}
		return builder.toString();
	}

	public void doCalc(int value, int price) {
		result = changes(value, price);
	}
	
}