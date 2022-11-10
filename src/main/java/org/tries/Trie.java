package org.tries;

public class Trie {
  TrieNode root;

  // Each Trie starts with a root node
  // All children are currently null
  public Trie() {
    root = new TrieNode();
  }

  // Inserts a String into the Trie
  public void add(String str) {
    TrieNode current = root;
    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      if (current.children[c - 'a'] == null) { // if child null create new node
        current.children[c - 'a'] = new TrieNode();
      }
      current = current.children[c - 'a']; // move to next node
    }
    current.isWord = true; // mark last node as word
  }

  // Searches the Trie for a String
  public boolean contains(String str) {
    TrieNode current = root;
    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      if (current.children[c - 'a'] == null) { // if child null return false
        return false;
      }
      current = current.children[c - 'a']; // move to next node
    }
    return current.isWord; // return if last node is word
  }

  // "Deletes" a String from the Trie
  // In actuality, we will just unmark the String as a word
  public void remove(String str) {
    TrieNode current = root;
    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      if (current.children[c - 'a'] == null) { // if child null return false
        return;
      }
      current = current.children[c - 'a']; // move to next node
    }
    current.isWord = false; // unmark last node as word
  }

  // Calls the printRec method
  public void print() {
    printRec(this.root, "");
  }

  // Recursively prints out the Trie
  // String str will keep track of the "word so far"
  // Should print out any words
  // Should call the printRec method on all 26 children
  public void printRec(TrieNode current, String str) {
    if (current.isWord) { // base case
      System.out.println(str);
    }
    for (int i = 0; i < 26; i++) { // because 26 letters in alphabet
      if (current.children[i] != null) {
        printRec(current.children[i], str + (char) (i + 'a')); // call printRec on all children
      }
    }
  }

  public static void main(String[] args) {
    // --------------------------
    // Test 1: Insertion (add)
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 1: Insertion (add)");
    System.out.println("Expected:");
    System.out.println("hi\n" + "his\n" + "hiss\n" + "history\n" + "world\n");

    System.out.println("\nGot:");

    Trie test = new Trie();

    test.add("hi");
    test.add("world");
    test.add("his");
    test.add("history");
    test.add("hiss");

    test.print();
    System.out.println();
    // hi
    // his
    // hiss
    // history
    // world

    // --------------------------
    // Test 2: Deletion (remove)
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 2: Deletion (remove)");
    System.out.println("Expected:");
    System.out.println("hi\n" + "hiss\n" + "history\n" + "world\n");

    System.out.println("\nGot:");
    test.remove("his");
    test.print();
    System.out.println();
    // hi
    // hiss
    // history
    // world

    // --------------------------
    // Test 3: Search (contains) and other tests
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 3: Search (contains) and other tests");
    System.out.println("Expected:");
    System.out.println("false\n" + "true\n" + "false\n" + "false");

    System.out.println("\nGot:");
    Trie trie1 = new Trie();
    System.out.println(trie1.contains("card"));
    // false

    trie1.add("card");
    System.out.println(trie1.contains("card"));
    // true
    System.out.println(trie1.contains("car"));
    // false

    trie1.remove("card");
    System.out.println(trie1.contains("card"));
    // false
  }
}

// Each TrieNode keeps track of if it is the end
// of a word, as well as an array of 26 possible children
class TrieNode {
  TrieNode[] children;
  boolean isWord;

  public TrieNode() {
    children = new TrieNode[26];
    isWord = false;
  }
}
