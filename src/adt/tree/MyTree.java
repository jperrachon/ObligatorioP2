package adt.tree;

import adt.linkedlist.MyList;

public interface MyTree<K,T> {
    public T find(K key);

    public void insert(K key, T value);

    public void delete(K key);

    int size();

    public T getNext();


}
