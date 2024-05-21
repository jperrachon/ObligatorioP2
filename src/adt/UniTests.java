package adt;

import adt.tree.MyTree;
import adt.tree.MyTreeImpl;

import java.util.Scanner;

public class UniTests {
    public static void main(String[] args) {
        MyTree<String, String> tree = new MyTreeImpl<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an operation:");
            System.out.println("1. Insert");
            System.out.println("2. Find");
            System.out.println("3. Delete");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline left-over

            switch (choice) {
                case 1:
                    System.out.println("Enter key:");
                    String insertKey = scanner.nextLine();
                    System.out.println("Enter value:");
                    String insertValue = scanner.nextLine();
                    System.out.println("Enter parent key (null if root):");
                    String parentKey = scanner.nextLine();
                    tree.insert(insertKey, insertValue, parentKey);
                    System.out.println("Inserted key-value pair into the tree.");
                    break;
                case 2:
                    System.out.println("Enter key:");
                    String findKey = scanner.nextLine();
                    String value = tree.find(findKey);
                    System.out.println("Found value: " + value);
                    break;
                case 3:
                    System.out.println("Enter key:");
                    String deleteKey = scanner.nextLine();
                    tree.delete(deleteKey);
                    System.out.println("Deleted key from the tree.");
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }
    }
}