package ru.bgbrakhi.coffeemachine;

public class StubInput extends ConsoleInput {
	/**
	* инкремент авоответа
	*/
	private int position = 0;

	/**
	* автоответы для тестирования
	*/
	private String[] value;
	
	public StubInput(String[] value) {
		this.value = value;	
	}

	@Override
	public String ask(String question) {
		return value[position++];
	}
}


