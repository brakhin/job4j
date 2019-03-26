package ru.bgrakhi.map;

import java.util.*;

public class CustomHashMap2<K, V> {

    public static class Entry<K_, V_> {
        private K_ key;
        private V_ value;

        public Entry(K_ key, V_ value) {
            this.key = key;
            this.value = value;
        }

        public K_ getKey() {
            return key;
        }

        public V_ getValue() {
            return value;
        }
    }

    List<Entry> table;
    private int tableLength;

    public CustomHashMap2() {
        table = new ArrayList<>();
        reboundTable(tableLength);
    }

    static int indexFor(int h, int length) {
        return h & (length - 1);
    }

    static final int hash(Object key) {
        int h = key.hashCode();
        return (key == null) ? 0 : (h) ^ (h >>> 16);
    }

    private void reboundTable(int newTableLength) {
        List<Entry> newTable = new ArrayList<>();

        for (int i = 0; i < newTableLength; i++) {
            newTable.add(null);
        }
        tableLength = newTableLength;

        List<Entry> oldTable = table;
        table = newTable;

        for (Entry<K, V> entry: oldTable) {
            if (entry != null) {
                int index = getIndex(entry.getKey());
                setEntry(entry.getKey(), entry.getValue(), index);
            }
        }
    }

    public boolean setEntry(K key, V value, int index) {
        boolean result = false;

        result = (table.get(index) == null);
        if (result) {
            Entry<K, V> entry = new Entry<>(key, value);
            table.set(index, entry);
        }
        return result;
    }

    private int getIndex(K key) {
        return key == null ? 0 : indexFor(hash(key), tableLength);
    }

    public boolean insert(K key, V value) {
        int index = getIndex(key);

        if (index >= tableLength) {
            reboundTable(tableLength * 2);
            index = getIndex(key);
        }
        return setEntry(key, value, index);
    }

    public V get(K key) {
        int index = getIndex(key);
        Entry<K, V> entry = table.get(index);
        return entry == null ? null : entry.getValue();
    }

    boolean delete(K key) {
        int index = getIndex(key);
        boolean result = (table.get(index) != null);
        table.remove(index);
        return result;
    }

    public Iterator<Entry> getIterator() {

        return new Iterator<Entry>() {
            private int index;

            @Override
            public boolean hasNext() {
                boolean result = false;
                for (int i = index; i < tableLength; i++) {
                    if (table.get(i) != null) {
                        result = true;
                        index = i;
                        break;
                    }
                }
                return result;
            }

            @Override
            public Entry next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table.get(index++);
            }
        };
    }
}
