package ru.bgbrakhi.coffeemachine;


public class CoffeeMachine {
	private Input input;
	private int[] result;
	private int value = 0;
	private int price = 0;
	
	// массив номиналов сдачи (МОНЕТЫ)
	private int[] coins = null;
	
	public CoffeeMachine(Input input, int[] coins) {
		this.input = input;
		this.coins = coins;

		result = changes(100, 33);

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
	
	// вывод результатов
	public void printResult() {
		System.out.println("Сдача : ");
		if (result == null) {
			System.out.println("нет.");
		} else {
			for (int i = this.coins.length - 1; i >= 0; i--) {
				if (this.result[i] > 0) {
					System.out.println(String.format("   монеты по %d руб : %d шт.", this.coins[i], this.result[i]));
				}
			}
		}
	}	

	// ввод даты
	public void inputData() {
		this.value = this.input.askint("Номинал купюры : ");
		this.price = this.input.askint("Цена кофе : ");
	}
	
	public void doCalc() {
		result = changes(value, price);
	}
	
	public static void main(String[] params) {
		CoffeeMachine machine = new CoffeeMachine(new ConsoleInput(), new int[]{1, 2, 5, 10, 20});
		machine.inputData();
		machine.doCalc();
		machine.printResult();
	}	
}	