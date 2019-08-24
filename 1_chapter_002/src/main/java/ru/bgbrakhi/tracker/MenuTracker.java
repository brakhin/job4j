package ru.bgbrakhi.tracker;

import java.util.ArrayList;
import java.util.List;

class UpdateItem extends BaseAction {
	protected UpdateItem(int key, String name) {
		super(key, name);
	}

	public void execute(Input input, ITracker tracker) {
		String id = input.ask("Please, enter the item's id: ");
		String name = input.ask("Please, enter the item's name: ");
		String desc = input.ask("Please, enter the item's desc: ");
		Item item = new Item(name, desc);
		tracker.replace(id, item);
	}

}

class DeleteItem extends BaseAction {
	protected DeleteItem(int key, String name) {
		super(key, name);
	}

	public void execute(Input input, ITracker tracker) {
		String id = input.ask("Please, enter the item's id: ");
		tracker.delete(id);
	}
}

class FindItemById extends BaseAction  {
	protected FindItemById(int key, String name) {
		super(key, name);
	}
	public void execute(Input input, ITracker tracker) {
		String id = input.ask("Please, enter the item's id: ");
		Item item = tracker.findById(id);
		if (item != null) {
			item.show();
		}
	}
}

class FindItemsByName extends BaseAction {
	protected FindItemsByName(int key, String name) {
		super(key, name);
	}

	public void execute(Input input, ITracker tracker) {
		String name = input.ask("Please, enter the item's name: ");
		List<Item> items = tracker.findByName(name);
		if (items != null) {
			for (Item item : items) {
				item.show();
			}
		}
	}
}

public class MenuTracker {

	private Input input;
	private ITracker tracker;
	private List<UserAction> actions = new ArrayList<>();

	public MenuTracker(Input input, ITracker tracker) {
		this.input = input;
		this.tracker = tracker;
	}

	public int getActionsLentgh() {
		return actions.size();
	}

	public void fillActions() {
		this.actions.add(this.new AddItem(0, "Add item"));
		this.actions.add(new MenuTracker.ShowItems(1, "Show items"));
		this.actions.add(new UpdateItem(2, "Update item"));
		this.actions.add(new DeleteItem(3, "Delete item"));
		this.actions.add(new FindItemById(4, "Find item by id"));
		this.actions.add(new FindItemsByName(5, "Find items by name"));
	}

	public void select(int key) {
		this.actions.get(key).execute(this.input, this.tracker);
	}

	public void show() {
		for (UserAction action : this.actions) {
			if (action != null) {
				System.out.println(action.info());
			}
		}
	}

	private class AddItem extends BaseAction {
		public AddItem(int key, String name) {
			super(key, name);
		}

		public void execute(Input input, ITracker tracker) {
			String name = input.ask("Please, enter the task's name: ");
			String desc = input.ask("Please, enter the task's desc: ");
			tracker.add(new Item(name, desc));
		}
	}

	private static class ShowItems extends BaseAction {
		protected ShowItems(int key, String name) {
			super(key, name);
		}

		public void execute(Input input, ITracker tracker) {
			for (Item item : tracker.findAll()) {
				item.show();
			}
		}
	}

}