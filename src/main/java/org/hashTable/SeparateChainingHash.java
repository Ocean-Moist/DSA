package org.hashTable;

import static java.util.Spliterators.spliterator;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

/** Name: List Anyone You Collaborated With (if any): Playboi Carti, Ken Carson */
public class SeparateChainingHash {
  IntLinkedList[] arr;
  private int capacity;
  private int size;

  // The constructor should initialize all of the instance variables
  // The default capacity should be 10
  // You will need to create a new IntLinkedList for each entry in arr
  public SeparateChainingHash() {
    this.capacity = 10;

    arr = new IntLinkedList[capacity];
    for (int i = 0; i < capacity; i++) {
      arr[i] = new IntLinkedList();
    }
  }

  public static void main(String[] args) {
    // --------------------------
    // Test 1: Insertion
    // -------------------------
    System.out.println("-------------------");
    System.out.println("Test 1: Insertion");
    System.out.println("Expected:");
    System.out.println(
        "INDEX | DATA\n"
            + "------------\n"
            + "    0 | null\n"
            + "    1 | 11 -> null\n"
            + "    2 | null\n"
            + "    3 | 3 -> null\n"
            + "    4 | 34 -> null\n"
            + "    5 | 5 -> null\n"
            + "    6 | 26 -> null\n"
            + "    7 | 7 -> null\n"
            + "    8 | null\n"
            + "    9 | null");
    System.out.println();
    System.out.println(
        "INDEX | DATA\n"
            + "------------\n"
            + "    0 | null\n"
            + "    1 | 11 -> 21 -> null\n"
            + "    2 | null\n"
            + "    3 | 3 -> null\n"
            + "    4 | 34 -> null\n"
            + "    5 | 5 -> null\n"
            + "    6 | 26 -> 36 -> null\n"
            + "    7 | 7 -> null\n"
            + "    8 | null\n"
            + "    9 | 49 -> null");

    System.out.println("\nGot:");

    SeparateChainingHash table1 = new SeparateChainingHash();
    // Insert some values to the table
    table1.insert(5);
    table1.insert(7);
    table1.insert(3);
    table1.insert(11);
    table1.insert(26);
    table1.insert(34);
    table1.print();
    // INDEX | DATA
    // ------------
    //     0 | null
    //     1 | 11 -> null
    //     2 | null
    //     3 | 3 -> null
    //     4 | 34 -> null
    //     5 | 5 -> null
    //     6 | 26 -> null
    //     7 | 7 -> null
    //     8 | null
    //     9 | null

    table1.insert(21);
    table1.insert(36);
    table1.insert(49);
    table1.print();
    // INDEX | DATA
    // ------------
    //     0 | null
    //     1 | 11 -> 21 -> null
    //     2 | null
    //     3 | 3 -> null
    //     4 | 34 -> null
    //     5 | 5 -> null
    //     6 | 26 -> 36 -> null
    //     7 | 7 -> null
    //     8 | null
    //     9 | 49 -> null

    // --------------------------
    // Test 2: Search
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 2: Search");

    System.out.println();

    System.out.println("Expected:");
    System.out.println("true");

    System.out.println();

    System.out.println("Got:");
    System.out.println(table1.search(11));

    System.out.println();

    // --------------------------
    // Test 3: Deletion
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 3: Deletion");

    System.out.println();

    // WRITE SOME OF YOUR OWN CODE TO TEST DELETION
    // Delete 11, 36, and 3 in order for your output
    // to match the output for Test 4

    System.out.println("Expected:");
    System.out.println(
        "INDEX | DATA\n"
            + "------------\n"
            + "    0 | null\n"
            + "    1 | 21 -> null\n"
            + "    2 | null\n"
            + "    3 | null\n"
            + "    4 | 34 -> null\n"
            + "    5 | 5 -> null\n"
            + "    6 | 26 -> null\n"
            + "    7 | 7 -> null\n"
            + "    8 | null\n"
            + "    9 | 49 -> null");

    System.out.println();

    System.out.println("Got:");
    table1.delete(11);
    table1.delete(36);
    table1.delete(3);
    table1.print();
    // INDEX | DATA
    // ------------
    //     0 | null
    //     1 | 21 -> null
    //     2 | null
    //     3 | null
    //     4 | 34 -> null
    //     5 | 5 -> null
    //     6 | 26 -> null
    //     7 | 7 -> null
    //     8 | null
    //     9 | 49 -> null

    // --------------------------
    // Test 4: Resize
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 4: Resize");
    System.out.println("Expected:");
    System.out.println(
        "INDEX | DATA\n"
            + "------------\n"
            + "    0 | 20 -> null\n"
            + "    1 | 21 -> null\n"
            + "    2 | 42 -> null\n"
            + "    3 | null\n"
            + "    4 | 44 -> null\n"
            + "    5 | 5 -> null\n"
            + "    6 | 26 -> null\n"
            + "    7 | 7 -> null\n"
            + "    8 | null\n"
            + "    9 | 49 -> null\n"
            + "   10 | null\n"
            + "   11 | null\n"
            + "   12 | null\n"
            + "   13 | 53 -> null\n"
            + "   14 | 34 -> null\n"
            + "   15 | null\n"
            + "   16 | 16 -> null\n"
            + "   17 | null\n"
            + "   18 | null\n"
            + "   19 | null");

    System.out.println("\nGot:");

    // Right now the load factor is 1, if we add
    // 5 more elements, then we expect our insert()
    // method to call the resize() method
    table1.insert(42);
    table1.insert(16);
    table1.insert(53);
    table1.insert(44);
    table1.insert(20);

    // Table should be size 20 now
    table1.print();
    // INDEX | DATA
    // ------------
    //     0 | 20 -> null
    //     1 | 21 -> null
    //     2 | 42 -> null
    //     3 | null
    //     4 | 44 -> null
    //     5 | 5 -> null
    //     6 | 26 -> null
    //     7 | 7 -> null
    //     8 | null
    //     9 | 49 -> null
    //    10 | null
    //    11 | null
    //    12 | null
    //    13 | 53 -> null
    //    14 | 34 -> null
    //    15 | null
    //    16 | 16 -> null
    //    17 | null
    //    18 | null
    //    19 | null
  }

  // A simple hash function
  // It will just calculate the data value % capacity
  public int hash(int data) {
    return data % capacity;
  }

  // Searches for the data value in our hash table
  // This code has been provided for you
  public boolean search(int data) {
    return (this.arr[hash(data)].search(data));
  }

  // Inserts the value in our hash table
  // If the loading factor gets too high (> 1.0),
  // should call the resize method
  public void insert(int data) {
    // Insert the data into the linked list
    this.arr[hash(data)].insert(data);

    // Increment the size
    this.size++;

    // Calculate the load factor
    double loadFactor = (double) this.size / this.capacity;

    // If the load factor is greater than 1.0
    // then we need to resize
    if (loadFactor > 1.0) {
      resize();
    }
  }

  // Deletes a value from our array
  public void delete(int data) {
    // Delete the data from the linked list
    this.arr[hash(data)].delete(data);

    // Decrement the size
    this.size--;
  }

  // Creates a new array double the size of the old one
  // You will need to:
  // * Update the capacity
  // * Create a new array of linked lists
  // * Loop through all of the values in the old array
  //   and re-insert them into the new array
  public void resize() {
    // solve with streams and without for loops or while loops with no comments

    this.capacity = this.capacity * 2;
    IntLinkedList[] newArr =
        IntStream.range(0, this.capacity)
            .mapToObj(i -> new IntLinkedList())
            .toArray(IntLinkedList[]::new);

    for (IntLinkedList list : this.arr) {
      IntNode node = list.head;
      while (node != null) {
        newArr[hash(node.data)].insert(node.data);
        node = node.next;
      }
    }

    this.arr = newArr;

    //    arr =
    //        Arrays.stream(arr)
    //            .filter(Objects::nonNull)
    //            .flatMapToInt(IntLinkedList::stream)
    //            .collect(
    //                () -> new IntLinkedList[capacity],
    //                (a, i) -> a[hash(i)].insert(i),
    //                (a, b) -> {
    //                  for (int i = 0; i < b.length; i++) {
    //                    a[i].append(b[i]);
    //                  }
    //                }); this does not work :(
  }

  public void print() {
    System.out.println("INDEX | DATA");
    System.out.println("------------");
    for (int i = 0; i < capacity; i++) {
      String index = "" + i;
      String data = " " + this.arr[i];
      int indexLen = index.length();

      // pad index spaces
      for (int j = 0; j < 5 - indexLen; j++) {
        index = " " + index;
      }

      System.out.println(index + " |" + data);
    }
    System.out.println();
  }
}

class IntLinkedList {
  // instance variables
  IntNode head;

  public IntLinkedList() {
    head = null;
  }

  public IntLinkedList(IntNode node) {
    head = node;
  }

  public boolean isEmpty() {
    return (head == null);
  }

  // Search the linked list for the specified key
  // return true if key is found, false otherwise
  public boolean search(int key) {
    IntNode current = head;

    while (current != null) {
      if (current.data == key) {
        return true;
      }
      current = current.next;
    }

    return false;
  }

  // Insert value to tail of the link list
  public void insert(int data) {
    // make a new Node
    IntNode newNode = new IntNode(data);

    // Edge case: list is empty
    if (head == null) {
      head = newNode;
    } else {
      // find the tail
      IntNode current = head;
      while (current.next != null) {
        current = current.next;
      }

      // point tail to new node
      current.next = newNode;
    }
  }

  // Delete Node with specified key, returns whether
  // deletion was successful or not
  public boolean delete(int key) {
    // Linked list is empty
    if (head == null) {
      return false;
    }
    // If deleting head, need to update head
    else if (head.data == key) {
      head = head.next;
      return true;
    }

    // Otherwise, we are not deleting head, we can proceed as usual
    IntNode prev = head;
    IntNode current = head.next;

    while (current != null) {
      // If we found the node, delete it
      if (current.data == key) {
        prev.next = current.next;
        return true;
      } else {
        prev = current;
        current = current.next;
      }
    }

    // Else we didn't find the node
    return false;
  }

  // toString function that is called when LinkedList is printed
  public String toString() {
    String str = "";
    IntNode current = head;
    while (current != null) {
      str = str + current.data + " -> ";
      current = current.next;
    }
    str += "null";
    return str;

    // a string representation of your linked list
    // 2 -> 6 -> 17 -> 45
  }

  public IntStream stream() {
    return StreamSupport.stream(spliterator(), false).mapToInt(i -> i);
  }

  private Spliterator<Integer> spliterator() {
    return new IntLinkedListSpliterator(this, -1, 0);
  }

  public void append(IntLinkedList intLinkedList) {
    this.stream().forEach(intLinkedList::insert);
  }
}

class IntNode {
  int data;
  IntNode next;

  public IntNode(int data) {
    this.data = data;
    this.next = null;
  }

  public IntNode(int data, IntNode next) {
    this.data = data;
    this.next = next;
  }
}
