package ru.bgrakhi.map;

import java.util.*;

public class CustomHashMap2<K, V> {

    private int modifycount = 0;

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

    Entry[] table;
    private int tableLength;

    public CustomHashMap2() {
        table = new Entry[tableLength];
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
        Entry[] newTable = new Entry[newTableLength];

        tableLength = newTableLength;

        Entry[] oldTable = table;
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

        result = (table[index] == null);
        if (result) {
            Entry<K, V> entry = new Entry<>(key, value);
            table[index] = entry;
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

        boolean result = setEntry(key, value, index);
        if (result) {
            modifycount++;
        }
        return result;
    }

    public V get(K key) {
        int index = getIndex(key);
        Entry<K, V> entry = table[index];
        return entry == null ? null : entry.getValue();
    }

    boolean delete(K key) {
        int index = getIndex(key);
        boolean result = (table[index] != null);
        System.arraycopy(table, index + 1, table, index, table.length - index - 1);
        table = Arrays.copyOf(table, table.length - 1);
        if (result) {
            modifycount++;
        }
        return result;
    }

    public Iterator<Entry> getIterator() {

        return new Iterator<Entry>() {
            int index;

            int oldmodcount = modifycount;

            @Override
            public boolean hasNext() {
                boolean result = false;
                for (int i = index; i < tableLength; i++) {
                    if (table[i] != null) {
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
                if (oldmodcount != modifycount) {
                    throw new IllegalCallerException();
                }
                return table[index++];
            }
        };
    }
}
