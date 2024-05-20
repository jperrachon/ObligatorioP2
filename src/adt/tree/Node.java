package adt.tree;


public class Node<K extends Comparable<K>,T>{
    private K key;
    private T value;
    private Node<K,T> leftChild;
    private Node<K,T> rightChild;

    public Node(K key, T value){
        this.key = key;
        this.value = value;
        this.leftChild = null;
        this.rightChild = null;
    }

    public K getKey(){
        return key;
    }

    public T getValue(){
        return value;
    }

    public Node<K,T> getLeftChild(){
        return leftChild;
    }

    public Node<K,T> getRightChild(){
        return rightChild;
    }

    public void setLeftChild(Node<K,T> leftChild){
        this.leftChild = leftChild;
    }

    public void setRightChild(Node<K,T> rightChild){
        this.rightChild = rightChild;
    }

    public void setValue(T value){
        this.value = value;
    }

    public Node<K,T> findNode(K key){
        if(key==this.key){
            return this;
        }
        else{
            if(this.rightChild!=null && key.compareTo(this.key)>=0){
                return this.rightChild.findNode(key);
            }
            else if(this.leftChild!=null && key.compareTo(this.key)<=0){
                return this.leftChild.findNode(key);
            }
        }
        return null;
    }
    public void deleteNode(K key){
        if(this.leftChild!=null && this.leftChild.getKey()==key){
            this.leftChild = null;
        }
        else if(this.rightChild!=null && this.rightChild.getKey()==key){
            this.rightChild = null;
        }
        else{
            if(this.rightChild!=null && key.compareTo(this.key)>=0){
                this.rightChild.deleteNode(key);
            }
            else if(this.leftChild!=null && key.compareTo(this.key)<=0){
                this.leftChild.deleteNode(key);
            }
        }
    }
}
