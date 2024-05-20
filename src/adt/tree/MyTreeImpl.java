package adt.tree;

public class MyTreeImpl<K extends Comparable<K>,T> implements MyTree<K,T>{
    private Node<K,T> root;

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
    public void insert(K key, T value, K parentKey) {
        if(root==null){
            root = new Node<>(key, value);
        }
        else{
            Node<K,T> parent = root.findNode(parentKey);
            if(parent!=null){
                if(parent.getLeftChild()==null){
                    parent.setLeftChild(new Node<K,T>(key,value));
                }
                else{
                    parent.setRightChild(new Node<K,T>(key,value));
                }
            }
        }
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
        }
    }


}
