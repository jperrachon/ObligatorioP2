package adt.hash;

public class HNode<K extends Comparable<K>,V> {
    private K key;
    private V value;


    public HNode(K key, V value){
        this.key = key;
        this.value = value;
    }

    public K getKey(){
        return key;
    }

    public V getValue(){
        return value;
    }

    public void setValue(V value){
        this.value = value;
    }


}
