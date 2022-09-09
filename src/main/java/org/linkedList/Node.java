package org.linkedList;

public class Node<T> {
  // The instance variables for an Node
  // Each Node will store a data value as well as
  // a next value
  T data;
  Node next;

  // The constructor for an Node
  // This makes an Node that stores the given data value
  public Node(T data) {
    this.data = data;
    this.next = null;
  }

  // A method that prTs out a Linked List, given a head Node
  public static void printList(Node head) {
    Node current = head;
    while (current != null) {
      System.out.println(current.data + " ");
      current = current.next;
    }
    System.out.println();
  }

  // The main method, where we will test our code!
  public static void main(String[] args) {
    // Code to make the first example list: 4 -> 2 -> null
    // Make the nodes
    Node head1 = new Node(4);
    Node A = new Node(2);
    // Make the connections
    head1.next = A;

    // Code to make the second example list: 3 -> 10 -> 2 -> null
    // Make the nodes
    Node head2 = new Node(3);
    Node X = new Node(10);
    Node Y = new Node(2);
    // Make the connections
    head2.next = X;
    X.next = Y;

    // PrT our lists
    printList(head1); // 2
    printList(head2); // 3
  }
}
