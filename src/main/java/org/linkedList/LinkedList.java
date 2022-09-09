package org.linkedList;

import java.util.Optional;

public class LinkedList<T> {
  // Each TLinkedList keeps track of its own head node
  private Node<T> head;

  // Constructor: The TLinkedList starts empty, as the head is null
  public LinkedList() {
    head = null;
  }

  // toString function that is called when LinkedList is prTed
  // Allows us to prT out the LinkedList in a nice format: 1 -> 2 -> 3 -> null
  public String toString() {
    String str = "";
    Node current = head;
    while (current != null)
    {
      str = str + current.data + " -> ";
      current = current.next;
    }
    str +=  "null";
    return str;
  }

  // Inserts a new node at the front of the LinkedList (prepend)
  public void insertFirst(T data) {
    Node<T> newNode = new Node<>(data);
    newNode.next = head;
    head = newNode;
  }

  // Inserts a new node at the back of the LinkedList (append)
  public void insertLast(T data) {
    Node<T> newNode = new Node<>(data);
    if (head == null) {
      head = newNode;
    } else {
      Node current = head;
      while (current.next != null) {
        current = current.next;
      }
      current.next = newNode;
    }
  }

  // Returns true if val is present in the list, and false otherwise
  public boolean search(T val) {
    // YOUR CODE HERE
    for (Node current = head; current != null; current = current.next) {
      if (current.data == val) {
        return true;
      }
    }
    return false;
  }

  // Removes the first node from the LinkedList, and returns its data value
  // Returns -1 if the list is empty
  public Optional<T> removeFirst() {
    // YOUR CODE HERE
    if (head == null) {
      return Optional.empty();
    }
    T data = head.data;
    head = head.next;
    return Optional.of(data);
  }

  // Removes the last node from the LinkedList, and returns its data value
  // Returns -1 if the list is empty

  public T removeLast() {
    if (head == null ) {
      return null;
    }
    if (head.next == null) {
      T data = head.data;
      head = null;
      return data;
    }
    for (Node current = head; true; current = current.next) {
      if (current.next.next == null) {
        T data = (T) current.next.data;
        current.next = null;
        return data;
      }
    }
  }

  // Removes all instances of val from the TLinkedList
  public void removeAll(T val) {
    Node<? extends T> prev = null;
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

  // Reverses the TLinkedList
  public void reverse() {
    // YOUR CODE HERE
    Node prev = null;
    Node curr = head;
    Node next = null;

    while (curr != null) {
      next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
    }
    head = prev;
  }

  // Checks if the TLinkedList contains a cycle
  public boolean hasCycle() {
    Node slow = head;
    Node fast = head;

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
    // tests
    LinkedList<Integer> list = new LinkedList<>();
    list.insertFirst(1);
    list.insertFirst(2);
    list.insertFirst(3);
    list.insertFirst(4);

    System.out.println(list);

    list.reverse();
    System.out.println(list);
  }
}