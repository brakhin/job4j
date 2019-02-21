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

	@Override
	public int ask(String question, int[] range) {
		return Integer.valueOf(this.ask(question));
//		throw new UnsupportedOperationException("Unsupported operation");
	}
}


