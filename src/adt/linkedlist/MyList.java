package adt.linkedlist;

public interface MyList<T> {

    void add(T value);

    T get(int position);

    boolean contains(T value);

    void remove(T value);

    int size();

    MyList<T> subList(int fromIndex, int toIndex);

    void sort(java.util.Comparator<T> comparator);

    Node<T> getFirst();

    Node<T> getLast();

    void modify(int position, T value);


}
