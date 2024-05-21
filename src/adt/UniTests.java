package adt;

import adt.hash.MyHash;
import adt.hash.MyHashImpl;
import adt.linkedlist.MyLinkedListImpl;
import adt.linkedlist.MyList;
import adt.queue.EmptyQueueException;
import adt.queue.MyQueue;
import adt.stack.EmptyStackException;
import adt.stack.MyStack;
import adt.tree.MyTree;
import adt.tree.MyTreeImpl;

import java.util.Scanner;

public class UniTests {
    public static void main(String[] args) throws EmptyQueueException, EmptyStackException {
        MyTree<Integer, String> tree = new MyTreeImpl<>();
        MyHash<Integer, String> hash = new MyHashImpl<>();
        MyStack<String> stack = new MyLinkedListImpl<>();
        MyList<String> list = new MyLinkedListImpl<>();
        MyQueue<String> queue = new MyLinkedListImpl<>();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            //create unitests menu
            System.out.println("1. Tree");
            System.out.println("2. Hash");
            System.out.println("3. Stack");
            System.out.println("4. List");
            System.out.println("5. Queue");
            System.out.println("6. Exit");
            System.out.println("Choose an option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Tree");
                    //menu for tree
                    System.out.println("1. Insert");
                    System.out.println("2. Search");
                    System.out.println("3. Delete");
                    System.out.println("4. Exit");
                    System.out.println("Choose an option: ");
                    int treeOption = scanner.nextInt();
                    switch (treeOption) {
                        case 1:
                            System.out.println("Insert");
                            System.out.println("Enter key: ");
                            int key = Integer.parseInt(scanner.next());
                            System.out.println("Enter value: ");
                            String value = scanner.next();

                            tree.insert(key, value);
                            break;
                        case 2:
                            System.out.println("Search");
                            System.out.println("Enter key: ");
                            int keySearch = Integer.parseInt(scanner.next());
                            System.out.println(tree.find(keySearch));
                            break;
                        case 3:
                            System.out.println("Delete");
                            System.out.println("Enter key: ");
                            int keyDelete = Integer.parseInt(scanner.next());
                            tree.delete(keyDelete);
                            break;

                        case 4:
                            break;

                    }
                    break;
                case 2:
                    System.out.println("Hash");
                    //menu for hash
                    System.out.println("1. Put");
                    System.out.println("2. Get");
                    System.out.println("3. Remove");
                    System.out.println("4. Exit");
                    System.out.println("Choose an option: ");
                    int hashOption = scanner.nextInt();
                    switch (hashOption) {
                        case 1:
                            System.out.println("Put");
                            System.out.println("Enter key: ");
                            int key = Integer.parseInt(scanner.next());
                            System.out.println("Enter value: ");
                            String value = scanner.next();

                            hash.put(key, value);
                            break;
                        case 2:
                            System.out.println("Get");
                            System.out.println("Enter key: ");
                            int keySearch = Integer.parseInt(scanner.next());
                            System.out.println(hash.get(keySearch));
                            break;
                        case 3:
                            System.out.println("Remove");
                            System.out.println("Enter key: ");
                            int keyDelete = Integer.parseInt(scanner.next());
                            hash.remove(keyDelete);
                            break;

                        case 4:
                            break;

                    }
                    break;
                case 3:
                    System.out.println("Stack");
                    //menu for stack
                    System.out.println("1. Push");
                    System.out.println("2. Pop");
                    System.out.println("3. Peek");
                    System.out.println("4. Exit");
                    System.out.println("Choose an option: ");
                    int stackOption = scanner.nextInt();
                    switch (stackOption) {
                        case 1:
                            System.out.println("Push");
                            System.out.println("Enter value: ");
                            String value = scanner.next();

                            stack.push(value);
                            break;
                        case 2:
                            System.out.println("Pop");
                            System.out.println(stack.pop());
                            break;
                        case 3:
                            System.out.println("Peek");
                            System.out.println(stack.peek());
                            break;

                        case 4:
                            break;

                    }
                    break;
                case 4:
                    System.out.println("List");
                    //menu for list
                    System.out.println("1. Add");
                    System.out.println("2. Remove");
                    System.out.println("3. Size");
                    System.out.println("4. Exit");
                    System.out.println("Choose an option: ");
                    int listOption = scanner.nextInt();
                    switch (listOption) {
                        case 1:
                            System.out.println("Add");
                            System.out.println("Enter value: ");
                            String value = scanner.next();

                            list.add(value);
                            break;
                        case 2:
                            System.out.println("Remove");
                            System.out.println("Enter value: ");
                            String value2 = scanner.next();
                            list.remove(value2);
                            break;
                        case 3:
                            System.out.println("Size");
                            System.out.println(list.size());
                            break;

                        case 4:
                            break;

                    }
                    break;
                case 5:
                    System.out.println("Queue");
                    //menu for queue
                    System.out.println("1. Enqueue");
                    System.out.println("2. Dequeue");
                    System.out.println("3. Size");
                    System.out.println("4. Exit");
                    System.out.println("Choose an option: ");
                    int queueOption = scanner.nextInt();
                    switch (queueOption) {
                        case 1:
                            System.out.println("Enqueue");
                            System.out.println("Enter value: ");
                            String value = scanner.next();

                            queue.enqueue(value);
                            break;
                        case 2:
                            System.out.println("Dequeue");
                            System.out.println(queue.dequeue());
                            break;
                        case 3:
                            System.out.println("Size");
                            System.out.println(queue.size());
                            break;

                        case 4:
                            break;

                    }
                    break;
                case 6:
                    System.exit(0);
                    break;


            }


        }
    }
}