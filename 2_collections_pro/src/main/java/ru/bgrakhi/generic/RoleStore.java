package ru.bgrakhi.generic;


public class RoleStore extends AbstractStore<Role> implements Store<Role> {

    protected RoleStore(SimpleArray<Role> data) {
        super(data);
    }

    @Override
    public void add(Role model) {
        data.add(model);
    }

    @Override
    public boolean replace(String id, Role model) {
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
    public Role findById(String id) {
        int index = idToIndex(id);
        return index >= 0 ? data.get(index) : null;
    }
}
