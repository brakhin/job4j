package ru.bgbrakhi.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.bgbrakhi.models.Item;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StartUITest {

	private final PrintStream stdout = System.out;
	private final ByteArrayOutputStream out = new ByteArrayOutputStream();

	@Test
	public void whenUserAddItemthenTrackerContainsItemWithSuchName() {
		Tracker tracker = new Tracker();
		Input input = new StubInput(new String[]{"0", "test name", "desc", "y"});
		new StartUI(input, tracker).init();
		assertThat(tracker.findAll()[0].getName(), is("test name"));
	}
	
	@Test
	public void whenUpdateItemThenTrackerHasUpdatedItem() {
		Tracker tracker = new Tracker();
		Item item = tracker.add(new Item("test name", "desc"));
		Input input = new StubInput(new String[]{"2", item.getId(), "test replace", "заменили заявку", "y"});
		new StartUI(input, tracker).init();
		assertThat(tracker.findById(item.getId()).getName(), is("test replace"));
	}
	
	@Test
	public void whenItemDeletedThenTrackerDontContainsIt() {
		Tracker tracker = new Tracker();
		tracker.add(new Item("name_1", "desc_1"));
		Item item = tracker.add(new Item("name_2", "desc_2"));
		tracker.add(new Item("name_3", "desc_3"));
		Input input = new StubInput(new String[]{"3", item.getId(), "y"});
		new StartUI(input, tracker).init();
		assertThat(tracker.findById(item.getId()) == null ? 1 : 0, is(1));
	}
	
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
	public void showAllItemsTest() {
		Tracker tracker = new Tracker();
		tracker.add(new Item("name_1", "desc_1"));
		tracker.add(new Item("name_2", "desc_2"));
		tracker.add(new Item("name_3", "desc_3"));
		Input input = new StubInput(new String[]{"1", "y"});
		new StartUI(input, tracker).init();
		assertThat(
			this.out.toString(),
			is(
				new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
						.add("0. Add the new item")
						.add("1. Show all iems")
						.add("2. Edit item")
						.add("3. Delete item")
						.add("4. Find item by id")
						.add("5. Find items by name")
						.add("name_1(desc_1)")
						.add("name_2(desc_2)")
						.add("name_3(desc_3)")
                    .toString()
			)
		);
	}

	@Test
	public void findItemByIdTest() {
		Tracker tracker = new Tracker();
		tracker.add(new Item("name_1", "desc_1"));
		Item item = tracker.add(new Item("name_2", "desc_2"));
		tracker.add(new Item("name_3", "desc_3"));
		Input input = new StubInput(new String[]{"4", item.getId(), "y"});
		new StartUI(input, tracker).init();
		assertThat(
			this.out.toString(),
			is(
					new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
							.add("0. Add the new item")
							.add("1. Show all iems")
							.add("2. Edit item")
							.add("3. Delete item")
							.add("4. Find item by id")
							.add("5. Find items by name")
							.add("name_2(desc_2)")
							.toString()
			)
		);
	}

	@Test
	public void findItemsByNameTest() {
		Tracker tracker = new Tracker();
		tracker.add(new Item("name_1", "desc_1"));
		tracker.add(new Item("name_1", "desc_2"));
		tracker.add(new Item("name_3", "desc_3"));
		Input input = new StubInput(new String[]{"5", "name_1", "y"});
		new StartUI(input, tracker).init();
		assertThat(
			this.out.toString(),
			is(
				new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
						.add("0. Add the new item")
						.add("1. Show all iems")
						.add("2. Edit item")
						.add("3. Delete item")
						.add("4. Find item by id")
						.add("5. Find items by name")
						.add("name_1(desc_1)")
						.add("name_1(desc_2)")
                    .toString()
			)
		);
	}

}

