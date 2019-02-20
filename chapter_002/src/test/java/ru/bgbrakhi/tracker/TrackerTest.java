package ru.bgbrakhi.tracker;

import org.junit.Test;
import ru.bgbrakhi.models.Item;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TrackerTest {

	@Test
	public void whenAddNewThenTrackerHasTheSameItem() {
		Tracker tracker = new Tracker();
		Item item = new Item("name", "desc");
		tracker.add(item);
		assertThat(tracker.findAll()[0], is(item));
	}	
	
	@Test
	public void whenReplaceThenReturnNewName() {
		Tracker tracker = new Tracker();
		Item itemOld = new Item("name_old", "desc");
		Item itemNew = new Item("name_new", "desc");
		tracker.add(itemOld);
		tracker.replace(itemOld.getId(), itemNew);
		assertThat(tracker.findById(itemOld.getId()).getName(), is(itemNew.getName()));
	}	

	@Test
	public void ifDeleteThenNoItem() {
		Tracker tracker = new Tracker();
		Item item1 = new Item("name_1", "desc");
		Item item2 = new Item("name_2", "desc");
		tracker.add(item1);
		tracker.add(item2);
		tracker.delete(item1.getId());
		assertThat(tracker.findById(item1.getId()) == null ? 1 : 0, is(1));
	}	
	
}
