package org.spellCheckerLab;

public class Trie {
  TrieNode root;

  public Trie() {
    root = new TrieNode();
  }

  public static void main(String[] args) {}

  // Inserts a String into the Trie
  // Code is provided
  public void add(String str) {
    TrieNode node = root;
    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      if (node.children[c - 'a'] == null) {
        node.children[c - 'a'] = new TrieNode();
      }
      node = node.children[c - 'a'];
    }
    node.isWord = true;
  }

  // Prints out the Trie
  // Code is provided
  public void print() {
    printRec(this.root, "");
  }

  // Recursive code that actually prints out the Trie
  // Code is provided
  public void printRec(TrieNode current, String str) {
    if (current == null) {
      return;
    } else if (current.isWord) {
      System.out.println(str);
    }

    for (int i = 0; i < 26; i++) {
      TrieNode child = current.children[i];
      char c1 = (char) ('a' + i);
      printRec(child, str + c1);
    }
  }

  // Should iterate through the str, and then print out
  // all of the Strings that start with str
  public void autoComplete(String str) {
    TrieNode node = root;
    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      if (node.children[c - 'a'] == null) {
        System.out.println("No words found");
        return;
      }
      node = node.children[c - 'a'];
    }
    printRec(node, str);
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
