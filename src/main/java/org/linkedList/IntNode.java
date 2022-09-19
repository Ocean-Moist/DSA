package org.linkedList;

public class IntNode {
  // The instance variables for an IntNode
  // Each IntNode will store a data value as well as
  // a next value
  int data;
  IntNode next;

  // The constructor for an IntNode
  // This makes an IntNode that stores the given data value
  public IntNode(int data) {
    this.data = data;
    this.next = null;
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
