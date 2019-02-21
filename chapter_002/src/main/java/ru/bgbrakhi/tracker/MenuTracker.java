package ru.bgbrakhi.tracker;

import java.util.ArrayList;
import java.util.List;

class UpdateItem implements UserAction {
		public int key() {
			return 2;
		}	

		public void execute(Input input, Tracker tracker) {
			String id = input.ask("Please, enter the item's id: ");
			String name = input.ask("Please, enter the item's name: ");
			String desc = input.ask("Please, enter the item's desc: ");
			Item item = new Item(name, desc);
			tracker.replace(id, item);
		}
		
		public String info() {
			return String.format("%s. %s", this.key(), "Edit item");
		}	
}

class DeleteItem implements UserAction {
		public int key() {
			return 3;
		}	

		public void execute(Input input, Tracker tracker) {
			String id = input.ask("Please, enter the item's id: ");
			tracker.delete(id);
		}
		
		public String info() {
			return String.format("%s. %s", this.key(), "Delete item");
		}	
}

class FindItemById implements UserAction {
		public int key() {
			return 4;
		}	

		public void execute(Input input, Tracker tracker) {
			String id = input.ask("Please, enter the item's id: ");
			Item item = tracker.findById(id);
			if (item != null) {
				item.show();
			}	
		}
		
		public String info() {
			return String.format("%s. %s", this.key(), "Find item by id");
		}	
}

class FindItemsByName implements UserAction {
		public int key() {
			return 5;
		}	

		public void execute(Input input, Tracker tracker) {
			String name = input.ask("Please, enter the item's name: ");
			Item[] items = tracker.findByName(name);
			if (items != null) {
				for (Item item : items) {
					item.show();
				}
			}	
		}
		
		public String info() {
			return String.format("%s. %s", this.key(), "Find items by name");
		}	
}

public class MenuTracker {

	private Input input;
	private Tracker tracker;
	private List<UserAction> actions = new ArrayList<>();
	
	public MenuTracker(Input input, Tracker tracker) {
		this.input = input;
		this.tracker = tracker;
	}	

	public int getActionsLentgh() {
		return actions.size();
	}

	public void fillActions() {
		this.actions.add(this.new AddItem());
		this.actions.add(new MenuTracker.ShowItems());
		this.actions.add(new UpdateItem());
		this.actions.add(new DeleteItem());
		this.actions.add(new FindItemById());
		this.actions.add(new FindItemsByName());
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
	
	private class AddItem implements UserAction {
		public int key() {
			return 0;
		}	

		public void execute(Input input, Tracker tracker) {
			String name = input.ask("Please, enter the task's name: ");
			String desc = input.ask("Please, enter the task's desc: ");
			tracker.add(new Item(name, desc));
		}
		
		public String info() {
			return String.format("%s. %s", this.key(), "Add the new item");
		}	
	}	

	private static class ShowItems implements UserAction {
		public int key() {
			return 1;
		}	

		public void execute(Input input, Tracker tracker) {
			for (Item item : tracker.findAll()) {
				item.show();
			}	
		}
		
		public String info() {
			return String.format("%s. %s", this.key(), "Show all iems");
		}	
			
	}	

}