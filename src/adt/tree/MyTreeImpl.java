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
    public void insert(K key, T value) {
        if(root==null){
            root = new Node<K,T>(key,value);
        }
        else{
            root.insertNode(key,value);
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
