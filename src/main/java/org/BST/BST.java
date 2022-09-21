package org.BST;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Name: List Anyone You Collaborated With (if any): */
public class BST {
  // The org.BST.BST keeps track of its root node
  private Node root;

  // At the beginning, there are no nodes
  public BST() {
    this.root = null;
  }

  // Recursively searches the org.BST.BST for val
  private static boolean searchRec(int val, Node current) {
    if (current == null) {
      return false;
    } else if (current.data == val) {
      return true;
    } else if (val < current.data) {
      return searchRec(val, current.left);
    } else {
      return searchRec(val, current.right);
    }
  }

  // Recursively inserts a value into the org.BST.BST
  public static Node insertRec(int val, Node current) {
    // REPLACE WITH YOUR CODE HERE
    if (current == null) {
      return new Node(val);
    } else if (val < current.data) {
      current.left = insertRec(val, current.left);
    } else {
      current.right = insertRec(val, current.right);
    }
    return current;
  }

  // Deletes val from the org.BST.BST
  private static Node deleteRec(int val, Node current) {
    if (current == null) {
      return null;
    }
    if (current.data != val) {
      if (current.data > val) {
        current.left = deleteRec(val, current.left);
      } else {
        current.right = deleteRec(val, current.right);
      }
    } else {
      if (current.left == null && current.right == null) {
        return null;
      } else {
        if (current.left != null) {
          if (current.right == null) {
            return current.left;
          } else {
            Node temp = current.right;
            while (temp.left != null) {
              temp = temp.left;
            }
            current.data = temp.data;
            current.right = deleteRec(temp.data, current.right);
          }
        } else {
          return current.right;
        }
      }
    }
    return current;
  }

  // Returns the smallest value in the subtree
  // where current is the root
  private static int minValue(Node current) {
    while (current.left != null) {
      current = current.left;
    }
    return current.data;
  }

  // Carries out an inorder traversal:
  // Left, root, right
  public static void inOrderTraversal(Node node) {
    // REPLACE WITH YOUR CODE HERE
    if (node != null) {
      inOrderTraversal(node.left);
      System.out.println(node.data);
      inOrderTraversal(node.right);
    }
  }

  // Carries out an preorder traversal:
  // Root, left right
  public static void preOrderTraversal(Node node) {
    // REPLACE WITH YOUR CODE HERE
    if (node != null) {
      System.out.println(node.data);
      preOrderTraversal(node.left);
      preOrderTraversal(node.right);
    }
  }

  // Carries out a postorder traversal
  // Left, right, root
  public static void postOrderTraversal(Node node) {
    // REPLACE WITH YOUR CODE HERE
    if (node != null) {
      postOrderTraversal(node.left);
      postOrderTraversal(node.right);
      System.out.println(node.data);
    }
  }

  public static void main(String[] args) {
    // --------------------------
    // Test 1: (Non-recursive) Search
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 1: (Non-recursive) Search");
    System.out.println("Expected:");
    System.out.println("false");
    System.out.println("true");
    System.out.println("false");
    System.out.println("true");
    System.out.println("false");
    System.out.println("true");
    System.out.println("true");
    System.out.println("false");

    System.out.println("\nGot:");
    // We'll create a tree and link it up manually
    // as we don't have insert implemented yet
    BST bst = new BST();
    Node node10 = new Node(10);
    Node node2 = new Node(2);
    Node node5 = new Node(5);
    Node node7 = new Node(7);
    Node node3 = new Node(3);
    Node node6 = new Node(6);
    Node node11 = new Node(11);
    Node node13 = new Node(13);
    Node node12 = new Node(12);
    bst.root = node7;
    node7.left = node3;
    node3.left = node2;
    node3.right = node6;
    node6.left = node5;
    node7.right = node10;
    node10.right = node12;
    node12.left = node11;
    node12.right = node13;

    // Search for 0, 2, 4, 6, 8, 12, and 14
    for (int i = 0; i < 15; i += 2) {
      System.out.println(bst.search(i));
    }
    // false
    // true
    // false
    // true
    // false
    // true
    // true
    // false

    // --------------------------
    // Test 2: Recursive Search
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 2: Recursive Search");
    System.out.println("Expected:");
    System.out.println("false");
    System.out.println("true");
    System.out.println("false");
    System.out.println("true");
    System.out.println("false");
    System.out.println("true");
    System.out.println("true");
    System.out.println("false");

    System.out.println("\nGot:");
    // Search for 0, 2, 4, 6, 8, 12, and 14
    for (int i = 0; i < 15; i += 2) {
      System.out.println(bst.recursiveSearch(i));
    }
    // false
    // true
    // false
    // true
    // false
    // true
    // true
    // false

    // --------------------------
    // Test 3: (Non-recursive) Insert
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 3: (Non-recursive) Insert");
    System.out.println("Expected:");
    System.out.println("    4");
    System.out.println("   / \\");
    System.out.println("  /   \\");
    System.out.println("  3   7");
    System.out.println("       \\");
    System.out.println("       8");

    System.out.println("\nGot:");
    // Insert 4, 7, 3, and then 8
    BST integerBST = new BST();
    integerBST.insert(4);
    integerBST.insert(7);
    integerBST.insert(3);
    integerBST.insert(8);
    BTreePrinter.printNode(integerBST.root);
    //    4
    //   / \
    //  /   \
    //  3   7
    //       \
    //       8

    // --------------------------
    // Test 4: Recursive Insert
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 4: Recursive Insert");
    System.out.println("Expected:");
    System.out.println("    4");
    System.out.println("   / \\");
    System.out.println("  /   \\");
    System.out.println("  3   7");
    System.out.println("       \\");
    System.out.println("       8");

    System.out.println("\nGot:");
    // Insert 4, 7, 3, and then 8
    BST integerBST2 = new BST();
    integerBST2.recursiveInsert(4);
    integerBST2.recursiveInsert(7);
    integerBST2.recursiveInsert(3);
    integerBST2.recursiveInsert(8);
    BTreePrinter.printNode(integerBST2.root);
    //    4
    //   / \
    //  /   \
    //  3   7
    //       \
    //       8

    // --------------------------
    // Test 5.0: Inorder Traversal
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 5.0: Inorder Traversal");
    System.out.println("Expected:");
    System.out.println("3");
    System.out.println("4");
    System.out.println("7");
    System.out.println("8");

    System.out.println("\nGot:");
    inOrderTraversal(integerBST.root);
    // 3
    // 4
    // 7
    // 8

    // --------------------------
    // Test 5.1: preorder Traversal
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 5.1: preorder Traversal");
    System.out.println("Expected:");
    System.out.println("4");
    System.out.println("3");
    System.out.println("7");
    System.out.println("8");

    System.out.println("\nGot:");
    preOrderTraversal(integerBST.root);
    // 4
    // 3
    // 7
    // 8

    // --------------------------
    // Test 5.2: postorder Traversal
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 5.2: postorder Traversal");
    System.out.println("Expected:");
    System.out.println("3");
    System.out.println("8");
    System.out.println("7");
    System.out.println("4");

    System.out.println("\nGot:");
    postOrderTraversal(integerBST.root);
    // 3
    // 8
    // 7
    // 4

    // --------------------------
    // Test 6: Delete a leaf
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 6: Delete a leaf");
    System.out.println("Expected:");
    System.out.println("    4");
    System.out.println("   / \\");
    System.out.println("  /   \\");
    System.out.println("  3   7");
    System.out.println("       \\");
    System.out.println("       8");

    System.out.println("\nGot:");
    // Insert a 9 node
    integerBST.insert(9);
    //         BTreePrinter.printNode(integerBST.root);
    //        4
    //       / \
    //      /   \
    //     /     \
    //    /       \
    //    3       7
    //             \
    //              \
    //              8
    //               \
    //               9

    // Delete the new 9 node
    integerBST.recursiveDelete(9);
    BTreePrinter.printNode(integerBST.root);
    //    4
    //   / \
    //  /   \
    //  3   7
    //       \
    //       8

    // --------------------------
    // Test 7: Delete a node with 1 child
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 7: Delete a node with 1 child");
    System.out.println("Expected:");
    System.out.println("    4");
    System.out.println("   / \\");
    System.out.println("  /   \\");
    System.out.println("  3   7");
    System.out.println("       \\");
    System.out.println("       9");

    System.out.println("\nGot:");
    // Insert the 9 node
    integerBST.insert(9);
    //        BTreePrinter.printNode(integerBST.root);
    //        4
    //       / \
    //      /   \
    //     /     \
    //    /       \
    //    3       7
    //             \
    //              \
    //              8
    //               \
    //               9

    // Delete the 8 node
    integerBST.recursiveDelete(8);
    BTreePrinter.printNode(integerBST.root);
    //    4
    //   / \
    //  /   \
    //  3   7
    //       \
    //       9

    // --------------------------
    // Test 8: Delete a node with 2 children
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 8: Delete a node with 2 children");
    System.out.println("Expected:");
    System.out.println("    4");
    System.out.println("   / \\");
    System.out.println("  /   \\");
    System.out.println("  3   9");
    System.out.println("     /");
    System.out.println("     6");

    System.out.println("\nGot:");

    // Add a 6 node
    integerBST.insert(6);
    //        BTreePrinter.printNode(integerBST.root);
    //    4
    //   / \
    //  /   \
    //  3   7
    //     / \
    //     6 9

    // Delete the 7 node
    integerBST.recursiveDelete(7);
    BTreePrinter.printNode(integerBST.root);
    //
    //    4
    //   / \
    //  /   \
    //  3   9
    //     /
    //     6

  }

  // Searches the org.BST.BST for the given value
  public boolean search(int val) {
    Node current = root;
    while (current != null) {
      if (current.data == val) {
        return true;
      } else if (current.data > val) {
        current = current.left;
      } else {
        current = current.right;
      }
    }
    return false;
  }

  // Wrapper method for recursive search
  // Don't edit this method
  private boolean recursiveSearch(int val) {
    return searchRec(val, root);
  }

  // Inserts a value into the org.BST.BST
  private void insert(int val) {
    if (root == null) {
      root = new Node(val);
    } else {
      Node current = root;
      while (true) {
        if (current.data > val) {
          if (current.left == null) {
            current.left = new Node(val);
            break;
          } else {
            current = current.left;
          }
        } else {
          if (current.right == null) {
            current.right = new Node(val);
            break;
          } else {
            current = current.right;
          }
        }
      }
    }
  }

  // Wrapper method for recursive insertion
  // Don't edit this method
  private void recursiveInsert(int val) {
    root = insertRec(val, root);
  }

  // Wrapper method that just calls deleteRec
  // Don't edit this method
  public void recursiveDelete(int val) {
    root = deleteRec(val, root);
  }

  // This is a wrapper function for the inorder traversal
  // Don't edit this method
  public void inOrder() {
    System.out.println("Inorder:");
    inOrderTraversal(this.root);
  }

  // This is a wrapper function for the preorder traversal
  // Don't edit this method
  public void preOrder() {
    System.out.println("Preorder:");
    preOrderTraversal(this.root);
  }

  // This is a wrapper function for the postorder traversal
  // Don't edit this method
  public void postOrder() {
    System.out.println("Postorder:");
    postOrderTraversal(this.root);
  }
}

// Each node will have a data value as well
// as possibly a left and right child
class Node {
  int data;
  Node left;
  Node right;

  public Node(int data) {
    this.data = data;
    this.left = null;
    this.right = null;
  }
}

// Print binary tree in a helpful way
// from: https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram
class BTreePrinter {
  public static <T extends Comparable<?>> void printNode(Node root) {
    int maxLevel = BTreePrinter.maxLevel(root);

    printNodeInternal(Collections.singletonList(root), 1, maxLevel);
  }

  private static <T extends Comparable<?>> void printNodeInternal(
      List<Node> nodes, int level, int maxLevel) {
    if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes)) return;

    int floor = maxLevel - level;
    int endgeLines = (int) Math.pow(2, Math.max(floor - 1, 0));
    int firstSpaces = (int) Math.pow(2, floor) - 1;
    int betweenSpaces = (int) Math.pow(2, floor + 1) - 1;

    BTreePrinter.printWhitespaces(firstSpaces);

    List<Node> newNodes = new ArrayList<>();
    for (Node node : nodes) {
      if (node != null) {
        System.out.print(node.data);
        newNodes.add(node.left);
        newNodes.add(node.right);
      } else {
        newNodes.add(null);
        newNodes.add(null);
        System.out.print(" ");
      }

      BTreePrinter.printWhitespaces(betweenSpaces);
    }
    System.out.println();

    for (int i = 1; i <= endgeLines; i++) {
      for (Node node : nodes) {
        BTreePrinter.printWhitespaces(firstSpaces - i);
        if (node == null) {
          BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
          continue;
        }

        if (node.left != null) System.out.print("/");
        else BTreePrinter.printWhitespaces(1);

        BTreePrinter.printWhitespaces(i + i - 1);

        if (node.right != null) System.out.print("\\");
        else BTreePrinter.printWhitespaces(1);

        BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
      }

      System.out.println();
    }

    printNodeInternal(newNodes, level + 1, maxLevel);
  }

  private static void printWhitespaces(int count) {
    for (int i = 0; i < count; i++) System.out.print(" ");
  }

  private static <T extends Comparable<?>> int maxLevel(Node node) {
    if (node == null) return 0;

    return Math.max(BTreePrinter.maxLevel(node.left), BTreePrinter.maxLevel(node.right)) + 1;
  }

  private static <T> boolean isAllElementsNull(List<T> list) {
    for (Object object : list) {
      if (object != null) return false;
    }

    return true;
  }
}
