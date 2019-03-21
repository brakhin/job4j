package ru.bgbrakhi.coffeemachine;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CoffeeMachineTest {
	private final PrintStream stdout = System.out;
	private final ByteArrayOutputStream out = new ByteArrayOutputStream();

	@Test
	public void calcTestisValid() {
		CoffeeMachine machine = new CoffeeMachine(new int[]{1, 2, 5, 10, 20});
		machine.doCalc(100, 33);
		assertThat(machine.getResult(), is("Сдача : 1 * 2 руб. + 1 * 5 руб. + 3 * 20 руб."));
	}
}

