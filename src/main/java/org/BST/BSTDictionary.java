package org.BST;

import java.util.LinkedList;

/** Name: List Anyone You Collaborated With (if any): */
public class BSTDictionary {
  // The BST keeps track of its root node
  private DictionaryNode root;

  // At the beginning, there are no nodes
  public BSTDictionary() {
    this.root = null;
  }

  // Insert an element given its key
  public void dictionaryInsert(int key, String value) {
    DictionaryNode newNode = new DictionaryNode(key, value);

    if (root == null) {
      root = newNode;
      return;
    }

    DictionaryNode currentNode = root;
    DictionaryNode parentNode = null;

    while (currentNode != null) {
      parentNode = currentNode;

      if (key < currentNode.key) {
        currentNode = currentNode.left;
      } else if (key > currentNode.key) {
        currentNode = currentNode.right;
      } else {
        currentNode.value = value;
        return;
      }
    }

    if (key < parentNode.key) {
      parentNode.left = newNode;
    } else {
      parentNode.right = newNode;
    }
  }

  // Returns true if the key is present in the dictionary
  public boolean dictionaryContainsKey(int key) {
    DictionaryNode currentNode = root;

    while (currentNode != null) {
      if (key < currentNode.key) {
        currentNode = currentNode.left;
      } else if (key > currentNode.key) {
        currentNode = currentNode.right;
      } else {
        return true;
      }
    }

    return false;
  }

  // You likely will want to use this recursive method:
  private static boolean dictionaryContainsKeyRec(int key, DictionaryNode current) {
    if (current == null) {
      return false;
    }

    if (key < current.key) {
      return dictionaryContainsKeyRec(key, current.left);
    } else if (key > current.key) {
      return dictionaryContainsKeyRec(key, current.right);
    } else {
      return true;
    }
  }

  // Search for element given its key
  public String dictionaryGet(int key) {
    DictionaryNode currentNode = root;

    while (currentNode != null) {
      if (key < currentNode.key) {
        currentNode = currentNode.left;
      } else if (key > currentNode.key) {
        currentNode = currentNode.right;
      } else {
        return currentNode.value;
      }
    }

    return null;
  }

  // Remove an element given its key
  public String dictionaryRemove(int key) {
    return dictionaryRemoveRec(key, root).value;
  }

  // You likely will want to use this recursive method:
  private static DictionaryNode dictionaryRemoveRec(int key, DictionaryNode current) {
    if (current == null) {
      return null;
    }

    if (key < current.key) {
      current.left = dictionaryRemoveRec(key, current.left);
    } else if (key > current.key) {
      current.right = dictionaryRemoveRec(key, current.right);
    } else {
      if (current.left == null) {
        return current.right;
      } else if (current.right == null) {
        return current.left;
      }

      current.key = minValue(current.right);
      current.right = dictionaryRemoveRec(current.key, current.right);
    }

    return current;
  }

  // Returns the smallest value in the subtree
  // where current is the root
  private static int minValue(DictionaryNode current) {
    int minv = current.key;
    while (current.left != null) {
      minv = current.left.key;
      current = current.left;
    }
    return minv;
  }

  // This is a wrapper function for the inorder traversal
  public void inOrder() {
    System.out.println("Inorder:");
    inOrderTraversal(this.root);
  }

  // This is a wrapper function for the preorder traversal
  public void preOrder() {
    System.out.println("Preorder:");
    preOrderTraversal(this.root);
  }

  // This is a wrapper function for the postorder traversal
  public void postOrder() {
    System.out.println("Postorder:");
    postOrderTraversal(this.root);
  }

  // Carries out an inorder traversal:
  // Left, root, right
  public static void inOrderTraversal(DictionaryNode node) {
    // Base case: node is null, just return
    if (node == null) {
      return;
    }

    // Otherwise, we recur on left subtree
    inOrderTraversal(node.left);
    // Then print out current node data
    System.out.println(node.key);
    // And finally recur on right subtree
    inOrderTraversal(node.right);
  }

  // Carries out an preorder traversal:
  // Root, left right
  public static void preOrderTraversal(DictionaryNode node) {
    // Root
    System.out.println(node.key);

    // Left
    if (node.left != null) {
      preOrderTraversal(node.left);
    }

    // Right
    if (node.right != null) {
      preOrderTraversal(node.right);
    }
  }

  // Carries out a postorder traversal
  // Left, right, root
  public static void postOrderTraversal(DictionaryNode node) {
    // Left
    if (node.left != null) {
      postOrderTraversal(node.left);
    }

    // Right
    if (node.right != null) {
      postOrderTraversal(node.right);
    }

    // Root
    System.out.println(node.key);
  }

  // You can adjust the variable names to match yours
  // This assumes the dictionaryNode has the following instance variables:
  // int key;
  // String value;
  // DictionaryNode left;
  // DictionaryNode right;
  @Override
  public String toString() {
    LinkedList<Integer> keys = inOrderList();
    String str = "INDEX | DATA\n";
    str += "------------\n";
    for (int key : keys) {
      String index = "" + key;
      String data = " " + this.dictionaryGet(key);
      int indexLen = index.length();

      // pad index spaces
      for (int j = 0; j < 5 - indexLen; j++) {
        index = " " + index;
      }

      str += (index + " |" + data + "\n");
    }
    return str;
  }

  public LinkedList<Integer> inOrderList() {
    LinkedList<Integer> list = new LinkedList<>();
    inOrderListRec(root, list);
    return list;
  }

  public static void inOrderListRec(DictionaryNode node, LinkedList<Integer> list) {
    if (node == null) {
      return;
    }

    // Otherwise, we recur on left subtree
    inOrderListRec(node.left, list);
    // Then print out current node data
    list.add(node.key);
    // And finally recur on right subtree
    inOrderListRec(node.right, list);
  }

  public static void main(String[] args) {
    // --------------------------
    // Test 1: insert method
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 1: insert");
    System.out.println("Expected:");
    System.out.println(
        "INDEX | DATA\n"
            + "------------\n"
            + "    1 | Pineapple\n"
            + "    2 | Orange\n"
            + "    3 | Apple\n"
            + "    4 | Banana\n"
            + "   11 | Fig\n"
            + "   13 | Dragonfruit\n"
            + "   21 | Lime\n"
            + "   24 | Lemon\n");

    // Add some values to our dictionary
    BSTDictionary dict1 = new BSTDictionary();
    dict1.dictionaryInsert(3, "Apple");
    dict1.dictionaryInsert(2, "Orange");
    dict1.dictionaryInsert(4, "Banana");
    dict1.dictionaryInsert(11, "Fig");
    dict1.dictionaryInsert(13, "Dragonfruit");
    dict1.dictionaryInsert(1, "Pineapple");
    dict1.dictionaryInsert(24, "Lemon");
    dict1.dictionaryInsert(21, "Lime");
    System.out.println("\nGot:");
    System.out.println(dict1);

    // --------------------------
    // Test 2: containsKey method
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 2: containsKey");
    System.out.println("Expected:");
    System.out.println("true");
    System.out.println("true");
    System.out.println("true");
    System.out.println("false");
    System.out.println("false");
    System.out.println("false");

    // Add some values to our dictionary
    System.out.println("\nGot:");
    System.out.println(dict1.dictionaryContainsKey(3));
    System.out.println(dict1.dictionaryContainsKey(11));
    System.out.println(dict1.dictionaryContainsKey(24));
    System.out.println(dict1.dictionaryContainsKey(0));
    System.out.println(dict1.dictionaryContainsKey(5));
    System.out.println(dict1.dictionaryContainsKey(18));

    // --------------------------
    // Test 3: get method
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 3: get");
    System.out.println("Expected:");
    System.out.println("Apple");
    System.out.println("Fig");
    System.out.println("Lemon");
    System.out.println("null");
    System.out.println("null");
    System.out.println("null");

    // Add some values to our dictionary
    System.out.println("\nGot:");
    System.out.println(dict1.dictionaryGet(3));
    System.out.println(dict1.dictionaryGet(11));
    System.out.println(dict1.dictionaryGet(24));
    System.out.println(dict1.dictionaryGet(0));
    System.out.println(dict1.dictionaryGet(5));
    System.out.println(dict1.dictionaryGet(18));

    // --------------------------
    // Test 4: remove method
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 4: remove");
    System.out.println("Expected:");
    System.out.println(
        "INDEX | DATA\n"
            + "------------\n"
            + "    1 | Pineapple\n"
            + "    2 | Orange\n"
            + "    4 | Banana\n"
            + "   13 | Dragonfruit\n"
            + "   21 | Lime");

    // Add some values to our dictionary
    System.out.println("\nGot:");
    dict1.dictionaryRemove(3);
    dict1.dictionaryRemove(11);
    dict1.dictionaryRemove(24);
    dict1.dictionaryRemove(0);
    dict1.dictionaryRemove(5);
    dict1.dictionaryRemove(18);
    System.out.println(dict1);
  }
}
