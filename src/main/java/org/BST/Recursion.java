package org.BST;

/** Name: List Anyone You Collaborated With (if any): */
public class Recursion {
  // Factorial Example
  // The factorial (!) of an integer x is the product
  // of x and all positive integers less than x
  // Ex. factorial(5) = 5 * 4 * 3 * 2 * 1 => 120
  public static int factorial(int x) {
    // Base case: 1! = 1
    if (x == 1) {
      return 1;
    }
    // Recursive call: x! = x * (x-1)!
    else {
      return x * factorial(x - 1);
    }
  }

  // Find the n-th fibonacci term.
  // The first two terms are both equal to 1,
  // that is fib(1) => 1, and fib(2) => 1
  // All other terms are equal to the sum of the
  // previous two terms
  public static int fib(int n) {
    return n <= 2 ? 1 : fib(n - 1) + fib(n - 2);
  }

  // Adds up all the digits in a number
  // Recall that you can use % 10 to get the last digit
  public static int sumDigits(int num) {
    // REPLACE WITH YOUR CODE HERE
    if (num < 10) {
      return num;
    } else {
      return num % 10 + sumDigits(num / 10);
    }
  }

  public static void main(String[] args) {
    // --------------------------
    // Test 1: Fibonacci
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 1: Fibonacci");
    System.out.println("Expected:");
    System.out.println(2);
    System.out.println(3);
    System.out.println(5);
    System.out.println(8);
    System.out.println(13);
    System.out.println(21);
    System.out.println(34);

    System.out.println("\nGot:");
    for (int i = 3; i < 10; i++) {
      System.out.println(fib(i));
    }

    // --------------------------
    // Test 2: SumDigits
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 2: SumDigits");
    System.out.println("Expected:");
    System.out.println(7);
    System.out.println(10);
    System.out.println(26);

    System.out.println("\nGot:");
    System.out.println(sumDigits(16));
    System.out.println(sumDigits(1234));
    System.out.println(sumDigits(3146723));

    // --------------------------
    // Test 3: Recursive Search
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 3: Recursive Search");
    System.out.println("Expected:");
    System.out.println("true");
    System.out.println("true");
    System.out.println("true");
    System.out.println("true");
    System.out.println("false");

    // Search the list for some different values
    System.out.println("\nGot:");
    IntLinkedList list2 = new IntLinkedList();
    list2.insertLast(7);
    list2.insertLast(2);
    list2.insertLast(5);
    list2.insertLast(10);
    System.out.println(list2.recursiveSearch(10)); // true
    System.out.println(list2.recursiveSearch(2)); // true
    System.out.println(list2.recursiveSearch(7)); // true
    System.out.println(list2.recursiveSearch(5)); // true
    System.out.println(list2.recursiveSearch(13)); // false

    // --------------------------
    // Test 4: Recursive Print
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 4: Recursive Print");
    System.out.println("Expected:");
    System.out.println("7 -> 2 -> 5 -> 10 -> null");
    System.out.println("null");
    System.out.println("6 -> null");

    // Print out different lists
    System.out.println("\nGot:");
    list2.recursivePrint();
    IntLinkedList list3 = new IntLinkedList();
    list3.recursivePrint();
    list3.insertLast(6);
    list3.recursivePrint();

    // --------------------------
    // Test 5: Recursive Insert Val
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 5: Recursive Insert Val");
    System.out.println("Expected:");
    System.out.println("3 -> 4 -> 5 -> 6 -> 7 -> null");

    // Print out different lists
    System.out.println("\nGot:");
    IntLinkedList list4 = new IntLinkedList();
    list4.recursiveInsertVal(5);
    list4.recursiveInsertVal(3);
    list4.recursiveInsertVal(4);
    list4.recursiveInsertVal(7);
    list4.recursiveInsertVal(6);
    System.out.println(list4);

    // --------------------------
    // Test 6: Recursive Remove Val
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 6: Recursive Remove Val");
    System.out.println("Expected:");
    System.out.println("3 -> 4 -> 5 -> 7 -> null");
    System.out.println("3 -> 4 -> 5 -> null");
    System.out.println("4 -> 5 -> null");

    // Print out different lists
    System.out.println("\nGot:");
    list4.recursiveRemoveVal(6);
    System.out.println(list4);
    list4.recursiveRemoveVal(7);
    System.out.println(list4);
    list4.recursiveRemoveVal(3);
    System.out.println(list4);
  }
}

class IntLinkedList {
  // Each IntLinkedList keeps track of its own head node
  private IntNode head;

  // Constructor: The IntLinkedList starts empty, as the head is null
  public IntLinkedList() {
    head = null;
  }

  // Carries out search, recursively
  // This method will initially be given the head node
  public static boolean searchRec(int val, IntNode current) {
    // REPLACE WITH YOUR CODE HERE
    if (current == null) {
      return false;
    } else if (current.data == val) {
      return true;
    } else {
      return searchRec(val, current.next);
    }
  }

  // Prints out a linked list, recursively
  // Recall that System.out.print() doesn't add a new lin
  public static void printRec(IntNode current) {
    // REPLACE WITH YOUR CODE HERE
    if (current == null) {
      System.out.println("null");
    } else {
      System.out.print(current.data + " -> ");
      printRec(current.next);
    }
  }

  // This method assumes that we have a LinkedList that is **sorted**
  // in ascending order
  // This inserts a node with the given value into the correct
  // location in the sorted list
  public static IntNode insertValRec(int val, IntNode current) {
    // REPLACE WITH YOUR CODE HERE
    if (current == null) {
      return new IntNode(val, null);
    } else if (current.data > val) {
      return new IntNode(val, current);
    } else {
      current.next = insertValRec(val, current.next);
      return current;
    }
  }

  // Removes the given val from the LinkedList
  public static IntNode removeValRec(int val, IntNode current) {
    // REPLACE WITH YOUR CODE HERE
    if (current == null) {
      return null;
    } else if (current.data == val) {
      return current.next;
    } else {
      current.next = removeValRec(val, current.next);
      return current;
    }
  }

  // Returns true if val is present in the list, and false otherwise
  public boolean search(int val) {
    if (head == null) {
      return false;
    } else {
      IntNode current = head;

      while (current.next != null) {
        if (current.data == val) {
          return true;
        }
        current = current.next;
      }

      return (current.data == val);
    }
  }

  // *******
  // We will work on the remaining methods in later classes
  // *******

  // Wrapper method for the recursive search
  // Don't edit this method!
  public boolean recursiveSearch(int val) {
    return searchRec(val, head);
  }

  // Wrapper method for the recursive print
  // Don't edit this method!
  public void recursivePrint() {
    printRec(head);
  }

  // Wrapper method for the recursive insert last
  // Don't edit this method!
  public void recursiveInsertVal(int val) {
    head = insertValRec(val, head);
  }

  // Wrapper method for the recursive remove val
  // Don't edit this method!
  public void recursiveRemoveVal(int val) {
    head = removeValRec(val, head);
  }

  // Other LinkedLists methods that aren't very relevant

  // Inserts a new node at the front of the LinkedList (prepend)
  public void insertFirst(int data) {
    IntNode newNode = new IntNode(data);
    newNode.next = head;
    head = newNode;
  }

  // Inserts a new node at the back of the LinkedList (append)
  public void insertLast(int data) {
    if (head == null) {
      insertFirst(data);
    } else {
      IntNode current = head;
      while (current.next != null) {
        current = current.next;
      }

      IntNode newNode = new IntNode(data);
      current.next = newNode;
    }
  }

  // Removes the first node from the LinkedList, and returns its data value
  // Returns -1 if the list is empty
  public int removeFirst() {
    if (head == null) {
      return -1;
    } else {
      IntNode oldHead = head;
      head = head.next;
      return oldHead.data;
    }
  }

  // Removes the last node from the LinkedList, and returns its data value
  // Returns -1 if the list is empty
  public int removeLast() {
    if (head == null) {
      return -1;
    } else if (head.next == null) {
      return removeFirst();
    } else {
      IntNode prev = head;
      IntNode current = head.next;

      while (current.next != null) {
        prev = current;
        current = current.next;
      }

      prev.next = null;
      return current.data;
    }
  }

  // toString function that is called when LinkedList is printed
  // Allows us to print out the LinkedList in a nice format: 1 -> 2 -> 3 -> null
  @Override
  public String toString() {
    String str = "";
    IntNode current = head;
    while (current != null) {
      str = str + current.data + " -> ";
      current = current.next;
    }
    str += "null";
    return str;
  }
}

class IntNode {
  // The instance variables for an IntNode
  // Each IntNode will store a data value as well as
  // a next value
  int data;
  IntNode next;

  // The constructor for an IntNode
  // This makes an IntNode that stores the given data value
  public IntNode(int data, IntNode next) {
    this.data = data;
    this.next = next;
  }

  public IntNode(int data) {
    this(data, null);
  }

  // A method that prints out a Linked List, given a head IntNode
  public static void printList(IntNode head) {
    IntNode current = head;
    while (current != null) {
      System.out.print(current.data + " ");
      current = current.next;
    }
    System.out.println();
  }

  // The main method, where we will test our code!
  public static void main(String[] args) {
    // Code to make the first example list: 4 -> 2 -> null
    // Make the nodes
    IntNode head1 = new IntNode(4);
    // Make the connections
    head1.next = new IntNode(2);

    // Code to make the second example list: 3 -> 10 -> 2 -> null
    // Make the nodes
    IntNode head2 = new IntNode(3);
    IntNode X = new IntNode(10);
    IntNode Y = new IntNode(2);
    // Make the connections
    head2.next = X;
    X.next = Y;

    // Print our lists
    printList(head1); // 2
    printList(head2); // 3
  }
}
