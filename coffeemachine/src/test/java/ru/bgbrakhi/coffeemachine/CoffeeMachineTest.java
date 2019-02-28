package ru.bgbrakhi.coffeemachine;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CoffeeMachineTest {
	private final PrintStream stdout = System.out;
	private final ByteArrayOutputStream out = new ByteArrayOutputStream();

	@Before
    public void loadOutput() {
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
        System.out.println("execute after method");
    }
	
	@Test
	public void calcTestisValid() {
		CoffeeMachine machine = new CoffeeMachine(new StubInput(new String[]{"100", "33"}), new int[]{1, 2, 5, 10, 20});
		machine.inputData();
		machine.doCalc();
		machine.printResult();
		assertThat(
				this.out.toString(),
				is(
						new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
								.add("Сдача : ")
								.add("   монеты по 20 руб : 3 шт.")
								.add("   монеты по 5 руб : 1 шт.")
								.add("   монеты по 2 руб : 1 шт.")
								.toString()
				)
		);
	}
}

