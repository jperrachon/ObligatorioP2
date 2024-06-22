package adt.linkedlist;


import adt.queue.*;
import adt.stack.*;

import java.util.Comparator;
import java.util.Objects;


public class MyLinkedListImpl<T> implements MyList<T>, MyQueue<T>, MyStack<T>{

    private Node<T> first;

    private Node<T> last;

    public MyLinkedListImpl() {
        this.first = null;
        this.last = null;
    }

    public Node<T> getFirst() {
        return first;
    }

    public Node<T> getLast() {
        return last;
    }


    @Override
    public void add(T value) {
        addToTheEnd(value);
    }

    @Override
    public MyList<T> subList(int fromIndex, int toIndex) {
        MyList<T> subList = new MyLinkedListImpl<>();
        Node<T> temp = this.first;
        int index = 0;
        while (temp != null && index < fromIndex) {
            temp = temp.getNext();
            index++;
        }
        while (temp != null && index < toIndex) {
            subList.add(temp.getValue());
            temp = temp.getNext();
            index++;
        }
        return subList;
    }

    @Override
    public void sort(Comparator<T> comparator) {
        Node<T> temp = this.first;
        while (temp != null) {
            Node<T> next = temp.getNext();
            while (next != null) {
                if (comparator.compare(temp.getValue(), next.getValue()) > 0) {
                    T tempValue = temp.getValue();
                    temp.setValue(next.getValue());
                    next.setValue(tempValue);
                }
                next = next.getNext();
            }
            temp = temp.getNext();
        }
    }

    @Override
    public void modify(int position, T value) {
        int tempPosition = 0;
        Node<T> temp = this.first;

        // Se busca el nodo que corresponde con la posicion
        while (temp != null && tempPosition != position) {

            temp = temp.getNext();
            tempPosition++;

        }
        // si se encontro la posicion se modifica el valor
        // en caso que se haya llegado al final y no se llego a la posicion se ignora
        if (tempPosition == position) {
            temp.setValue(value);

        }
    }

    private void addToBeginning(T value) {
        if (value != null) {

            Node<T> elementToAdd = new Node<>(value);

            if (this.first == null) { // si la lista es vacia

                this.first = elementToAdd;
                this.last = elementToAdd;

            } else { // en caso de no ser vacia se agrega al comienzo

                elementToAdd.setNext(this.first);
                this.first = elementToAdd;
            }

        } else {
            // si el elemento es vacio se ignora
        }
    }

    private void addToTheEnd(T value) {
        if (value != null) {

            Node<T> elementToAdd = new Node<>(value);

            if (this.first == null) { // si la lista es vacia

                this.first = elementToAdd;
                this.last = elementToAdd;

            } else { // en caso de no ser vacia se agrega al final

                this.last.setNext(elementToAdd);
                this.last = elementToAdd;
            }

        } else {
            // si el elemento es vacio se ignora
        }
    }


    @Override
    public T get(int position) {
        T valueToReturn = null;
        int tempPosition = 0;
        Node<T> temp = this.first;

        // Se busca el nodo que corresponde con la posicion
        while (temp != null && tempPosition != position) {

            temp = temp.getNext();
            tempPosition++;

        }

        // si se encontro la posicion se retorna el valor
        // en caso que se haya llegado al final y no se llego a la posicion se retorna null
        if (tempPosition == position) {

            valueToReturn = Objects.requireNonNull(temp).getValue();

        }

        return valueToReturn;
    }

    @Override
    public boolean contains(T value) {
        boolean contains = false;
        Node<T> temp = this.first;


        while (temp != null && !temp.getValue().equals(value)) {

            temp = temp.getNext();

        }

        if (temp != null) { // Si no se llego al final de la lista, se encontro el valor

            contains = true;

        }

        return contains;
    }

    @Override
    public void remove(T value) {
        Node<T> beforeSearchValue = null;
        Node<T> searchValue = this.first;

        // Busco el elemento a eliminar teniendo en cuenta mantener una referencia al elemento anterior
        while (searchValue != null && !searchValue.getValue().equals(value)) {

            beforeSearchValue = searchValue;
            searchValue = searchValue.getNext();

        }

        if (searchValue != null) { // si encontre el elemento a eliminar

            // Verifico si es el primer valor (caso borde) y no es el ultimo
            if (searchValue == this.first && searchValue != this.last) {

                Node<T> temp = this.first;
                this.first = this.first.getNext(); // salteo el primero

                temp.setNext(null); // quito referencia del elemento eliminado al siguiente.

                // Verifico si es el primer valor (caso borde) y no el primero
            } else if (searchValue == this.last && searchValue != this.first) {

                beforeSearchValue.setNext(null);
                this.last = beforeSearchValue;

                // Si es el primer valor y el ultimo (lista de un solo valor)
            } else if (searchValue == this.last) {

                this.first = null;
                this.last = null;

            } else { // resto de los casos

                beforeSearchValue.setNext(searchValue.getNext());
                searchValue.setNext(null);

            }

        }

    }

    private T removeLast() { // esta operación remueve el último elemento y retorna el elemento eliminado
        T valueToRemove = null;

        if (this.last != null) {
            valueToRemove = this.last.getValue();

            remove(valueToRemove);
        }

        return valueToRemove;
    }

    @Override
    public int size() {
        int size = 0;

        Node<T> temp = this.first;

        while (temp != null) {

            temp = temp.getNext();
            size++;

        }

        return size;
    }

    @Override
    public int indexOf(T value) {
        int index = -1;
        Node<T> temp = this.first;
        int tempIndex = 0;

        while (temp != null && index == -1) {
            if (temp.getValue().equals(value)) {
                index = tempIndex;
            }
            temp = temp.getNext();
            tempIndex++;
        }

        return index;
    }

    // Operaciones particulares a Queue

    @Override
    public void enqueue(T value) {
        addToBeginning(value);
    }

    @Override
    public T dequeue() throws EmptyQueueException {
        if (this.last == null) { // si la queue esta vacia

            throw new EmptyQueueException();
        }

        return removeLast();
    }

    // Operaciones particulares a Stack

    @Override
    public void push(T value) {
        addToTheEnd(value);
    }

    @Override
    public T pop() throws EmptyStackException {
        if (this.last == null) { // si la pila esta vacia

            throw new EmptyStackException();
        }

        return removeLast();
    }

    @Override
    public T peek() {
        T valueToReturn = null;

        if (this.last != null) {
            valueToReturn = this.last.getValue();
        }


        return valueToReturn;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> temp = this.first;
        while (temp != null) {
            sb.append(temp.getValue()).append(" ");
            temp = temp.getNext();
        }
        return sb.toString();
    }







}
