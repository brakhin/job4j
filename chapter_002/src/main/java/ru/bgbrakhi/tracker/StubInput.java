package ru.bgbrakhi.tracker;

public class StubInput implements Input {
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


