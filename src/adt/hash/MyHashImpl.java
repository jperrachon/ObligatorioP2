package adt.hash;

import adt.linkedlist.MyLinkedListImpl;
import adt.linkedlist.MyList;

public class MyHashImpl<K extends Comparable<K>, V> implements MyHash<K,V> {
    private HNode<K, V>[] table;
    private static final int INITIAL_CAPACITY = 1000;
    private int size;

    public MyHashImpl() {
        table = new HNode[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public void put(K key, V value) {
        if (size == table.length) {
            HNode<K, V>[] oldTable = table;
            table = new HNode[table.length * 2];
            size = 0;
            for (HNode<K, V> node : oldTable) {
                if (node != null) {
                    put(node.getKey(), node.getValue());
                }
            }
        }

        int index = hash(key);
        while (table[index] != null && table[index].getKey().compareTo(key) != 0) {
            index = (index + 1) % table.length;
        }

        if (table[index] == null) {
            size++;
        }

        table[index] = new HNode<>(key, value);
    }

    @Override
    public V get(K key) {
        int index = hash(key);
        while (table[index] != null && table[index].getKey().compareTo(key) != 0) {
            index = (index + 1) % table.length;
        }

        if (table[index] == null) {
            return null;
        }

        return table[index].getValue();
    }

    @Override
    public void remove(K key) {
        int index = hash(key);
        while (table[index] != null && table[index].getKey().compareTo(key) != 0) {
            index = (index + 1) % table.length;
        }

        if (table[index] != null) {
            table[index] = null;
            size--;
        }
    }

    @Override
    public boolean contains(K key) {
        int index = hash(key);
        while (table[index] != null && table[index].getKey().compareTo(key) != 0) {
            index = (index + 1) % table.length;
        }

        return table[index] != null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        table = new HNode[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public void print() {
        for (HNode<K, V> node : table) {
            if (node != null) {
                System.out.println("Key: " + node.getKey() + ", Value: " + node.getValue());
            }
        }
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    @Override
    public MyList<K> keySet() {
        MyList<K> keys = new MyLinkedListImpl<>();
        for (HNode<K, V> node : table) {
            if (node != null) {
                keys.add(node.getKey());
            }
        }
        return keys;
    }
}