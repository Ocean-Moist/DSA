package org.spellCheckerLab;

public class StringHashTable {
  private final int TABLE_SIZE;
  private final boolean isSimple;
  private final StringLinkedList[] arr;

  // The default Hash Table is not simple
  public StringHashTable() {
    this(false);
  }

  public StringHashTable(boolean isSimple) {
    if (isSimple) {
      this.TABLE_SIZE = 26;
      this.isSimple = isSimple;
    } else {
      this.TABLE_SIZE = 991547;
      this.isSimple = isSimple;
    }

    this.arr = new StringLinkedList[TABLE_SIZE];

    // initialize all linked lists
    for (int i = 0; i < TABLE_SIZE; i++) {
      this.arr[i] = new StringLinkedList();
    }
  }

  public static void main(String[] args) {}

  // Just hash the Strings by the first letter
  // Use this method to test that you are loading your
  // dictionary properly
  private int simpleHash(String data) {
    return data.charAt(0) - 'a';
  }

  // A better hash function for Strings
  private int hash(String data) {
    int hash = 7;
    for (int i = 0; i < data.length(); i++) {
      hash = hash * 31 + data.charAt(i);
      hash %= TABLE_SIZE;
    }
    return hash;
  }

  public boolean contains(String data) {
    int index;
    if (this.isSimple) {
      index = simpleHash(data);
    } else {
      index = hash(data);
    }

    return this.arr[index].search(data);
  }

  public void add(String data) {
    if (!contains(data)) {
      int index;
      if (this.isSimple) {
        index = simpleHash(data);
      } else {
        index = hash(data);
      }

      this.arr[index].insertFirst(data);
    }
  }

  public void remove(String data) {
    int index;
    if (this.isSimple) {
      index = simpleHash(data);
    } else {
      index = hash(data);
    }

    this.arr[index].deleteKey(data);
  }

  public void print() {
    System.out.println("INDEX | DATA");
    System.out.println("------------");
    for (int i = 0; i < TABLE_SIZE; i++) {
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

class StringLinkedList {
  // instance variables
  StringNode head;

  public StringLinkedList() {
    head = null;
  }

  public boolean isEmpty() {
    return (head == null);
  }

  // Search the linked list for the specified key
  // return true if key is found, false otherwise
  public boolean search(String key) {
    StringNode current = head;

    while (current != null) {
      if (current.data.equals(key)) {
        return true;
      }
      current = current.next;
    }

    return false;
  }

  public void insertFirst(String data) {
    StringNode newNode = new StringNode(data);
    newNode.next = head;
    head = newNode;
  }

  // Delete Node with specified key, returns whether
  // deletion was successful or not
  public boolean deleteKey(String key) {
    // Linked list is empty
    if (head == null) {
      return false;
    }
    // If deleting head, need to update head
    else if (head.data.equals(key)) {
      head = head.next;
      return true;
    }

    // Otherwise, we are not deleting head, we can proceed as usual
    StringNode prev = head;
    StringNode current = head.next;

    while (current != null) {
      // If we found the node, delete it
      if (current.data.equals(key)) {
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
    StringNode current = head;
    while (current != null) {
      str = str + current.data + " -> ";
      current = current.next;
    }
    str += "null";
    return str;

    // a string representation of your linked list
    // 2 -> 6 -> 17 -> 45
  }
}

class StringNode {
  String data;
  StringNode next;

  public StringNode(String data) {
    this.data = data;
    this.next = null;
  }

  public StringNode(String data, StringNode next) {
    this.data = data;
    this.next = next;
  }
}
