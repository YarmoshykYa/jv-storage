package core.basesyntax.impl;

import core.basesyntax.Storage;
import java.util.Objects;

public class StorageImpl<K, V> implements Storage<K, V> {
    private static int MAX_ITEMS_NUMBER = 10;
    private KeyValue<K, V>[] storage = new KeyValue[MAX_ITEMS_NUMBER];
    private int currSize = 0;

    private static class KeyValue<K, V> {
        private K key;
        private V value;

        private KeyValue(K key, V value) {
            this.key = key;
            this.value = value;
        }

        private K getKey() {
            return key;
        }

        private V getValue() {
            return value;
        }

        private void setKey(K key) {
            this.key = key;
        }

        private void setValue(V value) {
            this.value = value;
        }
    }

    @Override
    public void put(K key, V value) {
        for (int i = 0; i < currSize; i++) {
            if (Objects.isNull(storage[i].getKey()) && key == null) {
                storage[i] = new KeyValue<>(key, value);
                return;
            }
            if (!Objects.isNull(storage[i].getKey()) && storage[i].getKey().equals(key)) {
                storage[i].setValue(value);
                return;
            }
        }
        storage[currSize] = new KeyValue<>(key, value);
        currSize++;
    }

    @Override
    public V get(K key) {
        for (int i = 0; i < storage.length; i++) {
            if (key == null) {
                for (int j = 0; j < storage.length; j++) {
                    if (storage[j].getKey() == null) {
                        return storage[j].getValue();
                    }
                }
            }

            if (storage[i] != null && key.equals(storage[i].getKey())) {
                return storage[i].getValue();
            }
        }
        return null;
    }

    @Override
    public int size() {
        return currSize;
    }
}
