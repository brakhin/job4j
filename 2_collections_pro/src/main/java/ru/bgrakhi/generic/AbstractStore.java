package ru.bgrakhi.generic;

public abstract class AbstractStore<T extends Base> {
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
}