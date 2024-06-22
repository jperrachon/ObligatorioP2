package adt.hash;

import adt.linkedlist.MyList;

public interface MyHash<K extends Comparable<K>,V> {
    public void put(K key, V value);
    public V get(K key);
    public void remove(K key);
    public boolean contains(K key);
    public int size();
    public boolean isEmpty();
    public void clear();
    public void print();
    public MyList<K> keySet();


}
