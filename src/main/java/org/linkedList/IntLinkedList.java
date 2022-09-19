package org.linkedList;

import org.stacksqueues.Stack;

public class IntLinkedList implements Stack {
  // Each IntLinkedList keeps track of its own head node
  private IntNode head;

  // Constructor: The IntLinkedList starts empty, as the head is null
  public IntLinkedList() {
    head = null;
  }

  // toString function that is called when LinkedList is printed
  // Allows us to print out the LinkedList in a nice format: 1 -> 2 -> 3 -> null
  public String toString() {
    StringBuilder str = new StringBuilder();
    IntNode current = head;
    while (current != null) {
      str.append(current.data).append(" -> ");
      current = current.next;
    }
    str.append("null");
    return str.toString();
  }

  // Inserts a new node at the front of the LinkedList (prepend)
  public void insertFirst(int data) {
    IntNode newNode = new IntNode(data);
    newNode.next = head;
    head = newNode;
  }

  // Inserts a new node at the back of the LinkedList (append)
  public void insertLast(int data) {
    IntNode newNode = new IntNode(data);
    if (head == null) {
      head = newNode;
    } else {
      IntNode current = head;
      while (current.next != null) {
        current = current.next;
      }
      current.next = newNode;
    }
  }

  // Returns true if val is present in the list, and false otherwise
  public boolean search(int val) {
    // YOUR CODE HERE
    for (IntNode current = head; current != null; current = current.next) {
      if (current.data == val) {
        return true;
      }
    }
    return false;
  }

  // Removes the first node from the LinkedList, and returns its data value
  // Returns -1 if the list is empty
  public int removeFirst() {
    // YOUR CODE HERE
    if (head == null) {
      return -1;
    }
    int data = head.data;
    head = head.next;
    return data;
  }

  // Removes the last node from the LinkedList, and returns its data value
  // Returns -1 if the list is empty

  public int removeLast() {
    if (head == null) {
      return -1;
    }
    if (head.next == null) {
      int data = head.data;
      head = null;
      return data;
    }
    for (IntNode current = head; true; current = current.next) {
      if (current.next.next == null) {
        int data = current.next.data;
        current.next = null;
        return data;
      }
    }
  }

  // Removes all instances of val from the IntLinkedList
  public void removeAll(int val) {
    IntNode prev = null;
    var curr = head;

    while (curr != null) { // while we have not reached the end of the list
      if (curr.data == val) { // if the current node is the one we want to remove
        if (prev != null) { // if the current node is not the head
          prev.next = curr.next; // skip over the current node
        } else {
          head = curr.next; // if the current node is the head, make the next node the head
        }
      } else { // if the current node is not the one we want to remove
        prev = curr; // update the previous node
      }
      curr = curr.next; // update the current node
    }
  }

  // Reverses the IntLinkedList
  public void reverse() {
    // YOUR CODE HERE
    IntNode prev = null;
    IntNode curr = head;
    IntNode next;

    while (curr != null) {
      next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
    }
    head = prev;
  }

  // Checks if the IntLinkedList contains a cycle
  public boolean hasCycle() {
    IntNode slow = head;
    IntNode fast = head;

    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
      if (slow == fast) {
        return true;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    // --------------------------
    // Test 1: insertFirst method
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 1: insertFirst");
    System.out.println("Expected:");
    System.out.println("4 -> 2 -> 3 -> null");

    // Make a list containing 4 -> 2 -> 3 -> null
    IntLinkedList list1 = new IntLinkedList();
    list1.insertFirst(3);
    list1.insertFirst(2);
    list1.insertFirst(4);
    System.out.println("\nGot:");
    System.out.println(list1); // 4 -> 2 -> 3 -> null

    // --------------------------
    // Test 2: insertLast method
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 2: insertLast");
    System.out.println("Expected:");
    System.out.println("7 -> 2 -> 5 -> 10 -> null");

    // Make a list containing 7 -> 2 -> 5 -> 10 -> null
    IntLinkedList list2 = new IntLinkedList();
    list2.insertLast(7);
    list2.insertLast(2);
    list2.insertLast(5);
    list2.insertLast(10);
    System.out.println("\nGot:");
    System.out.println(list2); // 7 -> 2 -> 5 -> 10 -> null

    // --------------------------
    // Test 3: search method
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 3: search");
    System.out.println("Expected:");
    System.out.println("true");
    System.out.println("true");
    System.out.println("true");
    System.out.println("true");
    System.out.println("false");

    // Search the list for some different values
    System.out.println("\nGot:");
    System.out.println(list2.search(7)); // true
    System.out.println(list2.search(2)); // true
    System.out.println(list2.search(5)); // true
    System.out.println(list2.search(10)); // true
    System.out.println(list2.search(13)); // false

    // --------------------------
    // Test 4: removeFirst method
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 4: removeFirst");
    System.out.println("Expected:");
    System.out.println("2 -> 3 -> null");
    System.out.println("2 -> 5 -> 10 -> null");

    // Remove some values from the lists
    System.out.println("\nGot:");
    // Remove the first value from the list: 4 -> 2 -> 3 -> null
    list1.removeFirst();
    System.out.println(list1); // 2 -> 3 -> null

    // Remove the first value from the list: 7 -> 2 -> 5 -> 10 -> null
    list2.removeFirst();
    System.out.println(list2); // 2 -> 5 -> 10 -> null

    // --------------------------
    // Test 5: removeLast method
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 5: removeLast");
    System.out.println("Expected:");
    System.out.println("2 -> null");
    System.out.println("null");

    // Remove some values from the lists
    System.out.println("\nGot:");
    // Remove the last value from the list: 2 -> 3 -> null
    list1.removeLast();
    System.out.println(list1); // 2 -> null
    // Remove the last value from the list: 2 -> 3 -> null
    list1.removeLast();
    System.out.println(list1); // null

    // --------------------------
    // Test 6: removeAll method
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 6: removeAll");
    System.out.println("Expected:");
    System.out.println("1 -> 2 -> 3 -> 4 -> 5 -> null");
    System.out.println("5 -> 3 -> 1 -> 2 -> 0 -> null");
    System.out.println("null");

    // Remove all 6's from the list: 1 -> 2 -> 6 -> 3 -> 4 -> 6 -> 5 -> 6 -> 6 -> null
    System.out.println("\nGot:");
    IntLinkedList list3 = new IntLinkedList();
    list3.insertLast(1);
    list3.insertLast(2);
    list3.insertLast(6);
    list3.insertLast(3);
    list3.insertLast(4);
    list3.insertLast(6);
    list3.insertLast(5);
    list3.insertLast(6);
    list3.removeAll(6);
    System.out.println(list3); // 1 -> 2 -> 3 -> 4 -> 5 -> null

    // Remove all 6's from the list: 6 -> 5 -> 3 -> 6 -> 1 -> 2 -> 6 -> 0 -> null
    list3 = new IntLinkedList();
    list3.insertLast(6);
    list3.insertLast(5);
    list3.insertLast(3);
    list3.insertLast(6);
    list3.insertLast(1);
    list3.insertLast(2);
    list3.insertLast(6);
    list3.insertLast(0);
    list3.removeAll(6);
    System.out.println(list3); // 5 -> 3 -> 1 -> 2 -> 0 -> null

    // Remove all 6's from the list: 6 -> 6 -> 6 -> 6 -> 6 -> null
    list3 = new IntLinkedList();
    list3.insertLast(6);
    list3.insertLast(6);
    list3.insertLast(6);
    list3.insertLast(6);
    list3.insertLast(6);
    list3.removeAll(6);
    System.out.println(list3); // null

    // --------------------------
    // Test 7: reverse
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 7: reverse");
    System.out.println("Expected:");
    System.out.println("4 -> 2 -> 3 -> null");
    System.out.println("7 -> 3 -> 2 -> 4 -> null");

    // Reverse the list: 3 -> 2 -> 4 -> null
    System.out.println("\nGot:");
    IntLinkedList list4 = new IntLinkedList();
    list4.insertLast(3);
    list4.insertLast(2);
    list4.insertLast(4);
    list4.reverse();
    System.out.println(list4); // 4 -> 2 -> 3 -> null

    // Reverse the list: 4 -> 2 -> 3 -> 7 -> null
    list4.insertLast(7);
    list4.reverse();
    System.out.println(list4); // 7 -> 3 -> 2 -> 4 -> null

    // --------------------------
    // Test8: hasCycle
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 8: hasCycle");
    System.out.println("Expected:");
    System.out.println("true");
    System.out.println("true");
    System.out.println("false");

    // Check a list with a cycle: 3 -> 2 -> 4 --|
    //                            ^-------------|
    System.out.println("\nGot:");
    IntLinkedList list5 = new IntLinkedList();
    list5.insertLast(3);
    list5.insertLast(2);
    list5.insertLast(4);
    list5.head.next.next.next = list5.head;
    System.out.println(list5.hasCycle()); // true

    // Check a list with a cycle: 3 -> 2 -> 4 -> 5 --|
    //                                 ^-------------|
    list5 = new IntLinkedList();
    list5.insertLast(3);
    list5.insertLast(2);
    list5.insertLast(4);
    list5.insertLast(5);
    list5.head.next.next.next.next = list5.head.next;
    System.out.println(list5.hasCycle()); // true

    // Check a list without a cycle: 3 -> 2 -> 4 -> 5 -> null
    list5 = new IntLinkedList();
    list5.insertLast(3);
    list5.insertLast(2);
    list5.insertLast(4);
    list5.insertLast(5);
    System.out.println(list5.hasCycle()); // false
  }

  @Override
  public void push(int data) {
    insertFirst(data);
  }

  @Override
  public int pop() {
    return removeFirst();
  }

  @Override
  public int peek() {
    return head.data;
  }

  @Override
  public boolean isEmpty() {
    return head != null;
  }
}
