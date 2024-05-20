package adt.tree;

public interface MyTree<K,T> {
    public T find(K key);

    public void insert(K key, T value, K parentKey);

    public void delete(K key);


}
