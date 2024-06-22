package adt.tree;

import adt.linkedlist.MyList;

public class MyTreeImpl<K extends Comparable<K>,T> implements MyTree<K,T>{
    private Node<K,T> root;
    private int size;
    private Node<K,T> current;



    public MyTreeImpl(){
        this.root = null;
    }

    @Override
    public T find(K key) {
        if(root==null){
            return null;
        }
        else{
            Node<K,T> value = root.findNode(key);
            if(value!=null){
                return value.getValue();
            }
            else{
                return null;
            }
        }
    }
    @Override
    public void insert(K key, T value) {
        if(root==null){
            root = new Node<K,T>(key,value);
        }
        else{
            root.insertNode(key,value);
        }
        size++;
    }

    @Override
    public void delete(K key) {
        if(root!=null){
            if(root.getKey()==key){
                root = null;
            }
            else{
                root.deleteNode(key);
            }
            size--;
        }
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public T getNext() {
        //returns the next element in the tree preorder
        if(current==null){
            current = root;
            return current.getValue();
        }
        else{
            if(current.getLeftChild()!=null && !current.getLeftChild().getVisited()){
                current = current.getLeftChild();
                return current.getValue();
            }
            else if(current.getRightChild()!=null && !current.getRightChild().getVisited()){
                current = current.getRightChild();
                return current.getValue();
            }
            else{
                Node<K,T> parent = current;
                while(parent!=null && (parent.getRightChild()==null || parent.getRightChild().getVisited())){
                    parent.setVisited(true);
                    parent = parent.getParent();
                }
                if(parent==null){
                    root.resetVisited();
                    return null;
                }
                else{
                    current = parent.getRightChild();
                    return current.getValue();
                }
            }

        }

    }




}
