package adt;

import adt.hash.MyHash;
import adt.hash.MyHashImpl;
import adt.linkedlist.MyLinkedListImpl;
import adt.linkedlist.MyList;
import adt.queue.MyQueue;
import adt.stack.MyStack;
import adt.tree.MyTree;
import adt.tree.MyTreeImpl;

import java.util.Scanner;

public class UniTests {
    public static void main(String[] args) {
        MyTree<String, String> tree = new MyTreeImpl<>();
        MyHash<String, String> hash = new MyHashImpl<>();
        MyStack<String> stack = new MyLinkedListImpl<>();
        MyList<String> list = new MyLinkedListImpl<>();
        MyQueue<String> queue = new MyLinkedListImpl<>();

        Scanner scanner = new Scanner(System.in);

        while(true){
            //create unitests menu
            System.out.println("1. Tree");
            System.out.println("2. Hash");
            System.out.println("3. Stack");
            System.out.println("4. List");
            System.out.println("5. Queue");
            System.out.println("6. Exit");
            System.out.println("Choose an option: ");
            int option = scanner.nextInt();

            switch(option){
                case 1:
                    System.out.println("Tree");
                    //menu for tree
                    System.out.println("1. Insert");
                    System.out.println("2. Search");
                    System.out.println("3. Delete");
                    System.out.println("4. Print");
                    System.out.println("Choose an option: ");
                    int treeOption = scanner.nextInt();
                    switch(treeOption){
                        case 1:
                            System.out.println("Insert");
                            System.out.println("Enter key: ");
                            String key = scanner.next();
                            System.out.println("Enter value: ");
                            String value = scanner.next();
                            tree.insert(key, value);
                            break;
                        case 2:
                            System.out.println("Search");
                            System.out.println("Enter key: ");
                            String keySearch = scanner.next();
                            System.out.println(tree.search(keySearch));
                            break;
                        case 3:
                            System.out.println("Delete");
                            System.out.println("Enter key: ");
                            String keyDelete = scanner.next();
                            tree.delete(keyDelete);
                            break;
                        case 4:
                            System.out.println("Print");
                            tree.print();
                            break;
                    }

        }



    }
}