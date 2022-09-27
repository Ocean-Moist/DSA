package org.BST;

// Each node will have a data value as well
// as possibly a left and right child
class DictionaryNode {
  int key;
  String value;
  DictionaryNode left;
  DictionaryNode right;

  public DictionaryNode(int key, String value) {
    this.key = key;
    this.value = value;
    this.left = null;
    this.right = null;
  }
}
