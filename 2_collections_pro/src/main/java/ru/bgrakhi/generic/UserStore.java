package ru.bgrakhi.generic;

public class UserStore extends AbstractStore<User> implements Store<User> {

    protected UserStore(SimpleArray<User> data) {
        super(data);
    }

    @Override
    public void add(User model) {
        data.add(model);
    }

    @Override
    public boolean replace(String id, User model) {
        boolean result = false;
        int index = idToIndex(id);
        if (index >= 0) {
            data.set(model, index);
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        return this.delete(id);
    }

    @Override
    public User findById(String id) {
        int index = idToIndex(id);
        return index >= 0 ? data.get(index) : null;
    }
}
