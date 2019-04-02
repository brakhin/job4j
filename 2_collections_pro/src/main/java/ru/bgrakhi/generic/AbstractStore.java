package ru.bgrakhi.generic;

public abstract class AbstractStore<T extends Base>  implements Store<T> {
    public SimpleArray<T> data;

    protected AbstractStore(SimpleArray<T> data) {
        this.data = data;
    }

    public int idToIndex(String id) {
        int result = -1;
        for (int i = 0; i < data.length(); i++) {
            if (data.get(i).getId().equals(id)) {
                result = i;
                break;
            }
        }
        return result;
    }

    public boolean delete(String id) {
        boolean result = false;
        int index = idToIndex(id);
        if (index >= 0) {
            data.remove(index);
            result = true;
        }
        return result;
    }

    public boolean replace(String id, T model) {
        boolean result = false;
        int index = idToIndex(id);
        if (index >= 0) {
            data.set(model, index);
            result = true;
        }
        return result;
    }

    public T findById(String id) {
        int index = idToIndex(id);
        return index >= 0 ? data.get(index) : null;
    }

    public void add(T model) {
        data.add(model);
    }
}
