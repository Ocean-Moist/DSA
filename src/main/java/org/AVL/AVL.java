package org.AVL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AVL {
  Node root;

  public AVL() {
    this.root = null;
  }

  // Writing a height "getter" will help us avoid null pointer exceptions
  static int getHeight(Node current) {
    if (current == null) {
      return 0;
    } else {
      return current.height;
    }
  }

  // Get Balance factor of node current
  int balanceFactor(Node current) {
    if (current == null) return 0;

    return getHeight(current.left) - getHeight(current.right);
  }

  // We will use this image to guide our naming conventions
  //         A                                      B
  //        / \                                   /   \
  //       B   Ar         LL_Rotate (A)          C      A
  //      / \          - - - - - - - - ->      /  \    /  \
  //     C   Br                               Cl  Cr  Br  Ar
  //    / \
  //  Cl   Cr
  public Node LL_Rotate(Node A) {
    // REPLACE WITH YOUR CODE
    return null;
  }

  //      A                             C
  //     / \                          /  \
  //    B   Ar     LR_Rotate(A)     B      A
  //   / \       - - - - - - - ->  / \    / \
  // Bl   C                      Bl  Cl Cr  Ar
  //     / \
  //   Cl   Cr
  public Node LR_Rotate(Node A) {
    // REPLACE WITH YOUR CODE
    return null;
  }

  //   A                                B
  //  /  \                            /   \
  // Al   B       RR_Rotate(A)       A      C
  //     /  \   - - - - - - - ->    / \    / \
  //    Bl   C                     Al  Bl Cl  Cr
  //        / \
  //      Cl  Cr
  public Node RR_Rotate(Node A) {
    // REPLACE WITH YOUR CODE
    return null;
  }

  //    A                           C
  //   / \                         /  \
  // Al   B       RL_Rotate(A)   A      B
  //     / \    - - - - - - ->  / \    / \
  //    C   Br                Al  Cl  Cr  Br
  //   / \
  // Cl   Cr

  public Node RL_Rotate(Node A) {
    // REPLACE WITH YOUR CODE
    return null;
  }

  // Inserts into our tree, recursively
  // This is just a wrapper method
  public void insert(int data) {
    this.root = insertRec(this.root, data);
  }

  // Recursively inserts a node into the tree
  private Node insertRec(Node current, int data) {
    // 1) Normal BST insertion
    if (current == null) {
      current = new Node(data);
      return current;
    } else if (data == current.data) {
      return current;
    } else if (data < current.data) {
      current.left = insertRec(current.left, data);
    } else { // if (data > current.data)
      current.right = insertRec(current.right, data);
    }

    // 2) Make sure height is updated, if needed
    current.height = Math.max(getHeight(current.left), getHeight(current.right)) + 1;

    // 3) Check balance factor of current node to see if unbalanced
    int bf = balanceFactor(current);

    // 4) If unbalanced, perform necessary rotations
    if (bf > 1 || bf < -1) {
      // ADD YOUR CODE HERE
    }

    // 4) If balanced, return the original node
    return current;
  }

  // Deletes from our tree, recursively
  // This is just a wrapper method
  public void delete(int data) {
    this.root = deleteRec(this.root, data);
  }

  // Recursively deletes a node from the tree
  private Node deleteRec(Node current, int data) {
    // 1) Normal BST Deletion

    // Base case: current is null (the tree did not contain the node
    // we wanted to delete
    if (current == null) {
      return current;
    }

    // Recursive cases: we haven't found the node to delete yet,
    // so we search the appropriate subtree for it
    if (data < current.data) {
      current.left = deleteRec(current.left, data);
    } else if (data > current.data) {
      current.right = deleteRec(current.right, data);
    }

    // Else data == current.data, so we have found the node we want to delete.
    // To delete a node, we just need to return the new node (if any) that we want
    // the parent to have for where its deleted node used to be
    else {
      // Case 1: Node to be deleted is a leaf
      // We just need to return null here
      if (current.left == null && current.right == null) {
        return null;
      }
      // Case 2: Node to be deleted has only one child
      // We just need to return deleted node's child here
      else if (current.left == null) {
        return current.right;
      } else if (current.right == null) {
        return current.left;
      }
      // Case 3: Node to be deleted has two children
      // We need to do two things.
      // a) find the inorder successor and copy its value into the current node
      // b) delete the inorder successor
      else {
        // The inorder successor is the minimum value of the right subtree
        // a) Copy value of inorder successor into the current node
        current.data = minValue(current.right);

        // b) Delete the inorder successor
        current.right = deleteRec(current.right, current.data);
      }
    }

    // 2) Make sure height is updated, if needed
    current.height = Math.max(getHeight(current.left), getHeight(current.right)) + 1;

    // 3) Check balance factor of current node to see if unbalanced
    int bf = balanceFactor(current);

    // 4) If unbalanced, perform necessary rotations
    if (bf > 1 || bf < -1) {
      // ADD YOUR CODE HERE
    }

    // 4) If balanced, return the original node
    return current;
  }

  // ********************
  // "Normal" BST methods
  // ********************

  // Normal BST insert methods just for reference, and for use in testing
  public void BSTinsert(int data) {
    this.root = BSTinsertRec(this.root, data);
  }

  // Normal BST recursive method
  private Node BSTinsertRec(Node current, int data) {
    if (current == null) {
      current = new Node(data);
      return current;
    } else if (data == current.data) {
      return current;
    } else if (data < current.data) {
      current.left = BSTinsertRec(current.left, data);
    } else { // if (data > current.data)
      current.right = BSTinsertRec(current.right, data);
    }

    return current;
  }

  // Normal BST delete methods just for reference
  public void BSTdelete(int data) {
    this.root = BSTdeleteRec(this.root, data);
  }

  // Normal BST recursive method
  private Node BSTdeleteRec(Node current, int data) {
    // Base case: current is null (the tree did not contain the node
    // we wanted to delete
    if (current == null) {
      return current;
    }

    // Recursive cases: we haven't found the node to delete yet,
    // so we search the appropriate subtree for it
    if (data < current.data) {
      current.left = BSTdeleteRec(current.left, data);
    } else if (data > current.data) {
      current.right = BSTdeleteRec(current.right, data);
    }

    // Else data == current.data, so we have found the node we want to delete.
    // To delete a node, we just need to return the new node (if any) that we want
    // the parent to have for where its deleted node used to be
    else {
      // Case 1: Node to be deleted is a leaf
      // We just need to return null here
      if (current.left == null && current.right == null) {
        return null;
      }
      // Case 2: Node to be deleted has only one child
      // We just need to return deleted node's child here
      else if (current.left == null) {
        return current.right;
      } else if (current.right == null) {
        return current.left;
      }
      // Case 3: Node to be deleted has two children
      // We need to do two things.
      // 1) find the inorder successor and copy its value into the current node
      // 2) delete the inorder successor
      else {
        // The inorder successor is the minimum value of the right subtree
        // 1) Copy value of inorder successor into the current node
        current.data = minValue(current.right);

        // 2) Delete the inorder successor
        current.right = BSTdeleteRec(current.right, current.data);
      }
    }

    return current;
  }

  // Helper function used to find inorder successor
  private int minValue(Node current) {
    int minimum = current.data;

    // Keep checking for a left child, if there are no left children
    // we have reached the leftmost (smallest) node
    while (current.left != null) {
      minimum = current.left.data;
      current = current.left;
    }
    return minimum;
  }

  // Object methods that are just used for testing rotations about the root:
  // Test method
  public void LL_rot() {
    this.root = LL_Rotate(this.root);
  }

  // Test method
  public void LR_rot() {
    this.root = LR_Rotate(this.root);
  }

  // Test method
  public void RR_rot() {
    this.root = RR_Rotate(this.root);
  }

  // Test method
  public void RL_rot() {
    this.root = RL_Rotate(this.root);
  }

  public static void main(String[] args) {
    // --------------------------
    // Test 1: LL Rotation
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 1: LL Rotation");
    System.out.println("Expected:");
    System.out.println(
        "   6       \n"
            + "  / \\   \n"
            + " /   \\  \n"
            + " 4   8   \n"
            + "/ \\ / \\ \n"
            + "3 5 7 9 ");

    System.out.println("\nGot:");
    AVL AVL1 = new AVL();
    AVL1.BSTinsert(8);
    AVL1.BSTinsert(6);
    AVL1.BSTinsert(4);
    AVL1.BSTinsert(9);
    AVL1.BSTinsert(7);
    AVL1.BSTinsert(3);
    AVL1.BSTinsert(5);

    // This is what the tree looks like before rotation
    // BTreePrinter.printNode(AVL1.root);
    //        8
    //       / \
    //      /   \
    //     /     \
    //    /       \
    //    6       9
    //   / \
    //  /   \
    //  4   7
    // / \
    // 3 5

    // LL_rot() is a wrapper function that just calls LL_rotate with the root of the AVL tree
    AVL1.LL_rot();

    BTreePrinter.printNode(AVL1.root);
    //    6
    //   / \
    //  /   \
    //  4   8
    // / \ / \
    // 3 5 7 9

    // --------------------------
    // Test 1.5: LR Rotation
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 1.5: LR Rotation");
    System.out.println("Expected:");
    System.out.println(
        "   6       \n"
            + "  / \\   \n"
            + " /   \\  \n"
            + " 4   8   \n"
            + "/ \\ / \\ \n"
            + "3 5 7 9");

    System.out.println("\nGot:");
    AVL AVL2 = new AVL();
    AVL2.BSTinsert(8);
    AVL2.BSTinsert(4);
    AVL2.BSTinsert(6);
    AVL2.BSTinsert(9);
    AVL2.BSTinsert(3);
    AVL2.BSTinsert(5);
    AVL2.BSTinsert(7);

    // This is what the tree looks like prior to rotation
    // BTreePrinter.printNode(AVL2.root);
    //       8
    //      / \
    //     /   \
    //    /     \
    //   /       \
    //   4       9
    //  / \
    // /   \
    // 3   6
    //    / \
    //    5 7

    AVL2.LR_rot();

    BTreePrinter.printNode(AVL2.root);
    //    6
    //   / \
    //  /   \
    //  4   8
    // / \ / \
    // 3 5 7 9

    // --------------------------
    // Test 2: RR Rotation
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 2: RR Rotation");
    System.out.println("Expected:");
    System.out.println(
        "   6       \n"
            + "  / \\   \n"
            + " /   \\  \n"
            + " 4   8   \n"
            + "/ \\ / \\ \n"
            + "1 5 7 9");

    System.out.println("\nGot:");

    AVL AVL22 = new AVL();
    AVL22.BSTinsert(4);
    AVL22.BSTinsert(1);
    AVL22.BSTinsert(6);
    AVL22.BSTinsert(5);
    AVL22.BSTinsert(8);
    AVL22.BSTinsert(7);
    AVL22.BSTinsert(9);

    // This is what the tree looks like prior to rotation
    // BTreePrinter.printNode(AVL22.root);
    //       4
    //      / \
    //     /   \
    //    /     \
    //   /       \
    //   1       6
    //          / \
    //         /   \
    //         5   8
    //            / \
    //            7 9

    AVL22.RR_rot();

    BTreePrinter.printNode(AVL22.root);
    //    6
    //   / \
    //  /   \
    //  4   8
    // / \ / \
    // 3 5 7 9

    // --------------------------
    // Test 2.5: RL Rotation
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 2.5: RL Rotation");
    System.out.println("Expected:");
    System.out.println(
        "   4       \n"
            + "  / \\   \n"
            + " /   \\  \n"
            + " 2   6   \n"
            + "/ \\ / \\ \n"
            + "1 3 5 7 ");

    System.out.println("\nGot:");
    AVL AVL23 = new AVL();
    AVL23.BSTinsert(2);
    AVL23.BSTinsert(6);
    AVL23.BSTinsert(1);
    AVL23.BSTinsert(7);
    AVL23.BSTinsert(4);
    AVL23.BSTinsert(5);
    AVL23.BSTinsert(3);

    // This is what the tree looks like before rotation
    // BTreePrinter.printNode(AVL23.root);
    //       2
    //      / \
    //     /   \
    //    /     \
    //   /       \
    //   1       6
    //          / \
    //         /   \
    //         4   7
    //        / \
    //        3 5

    AVL23.RL_rot();

    BTreePrinter.printNode(AVL23.root);
    //    4
    //   / \
    //  /   \
    //  2   6
    // / \ / \
    // 1 3 5 7

    // --------------------------
    // Test 3: Insertion LL and LR Rotations
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 3: Insertion LL and LR Rotations");
    System.out.println("Expected:");
    System.out.println(" 5   \n" + "/ \\ \n" + "3 7 ");
    System.out.println();
    System.out.println(" 6   \n" + "/ \\ \n" + "4 8 ");

    System.out.println("\nGot:");

    // Insert, LL-rotation test
    AVL AVL3 = new AVL();
    AVL3.insert(7);
    AVL3.insert(5);
    AVL3.insert(3);
    BTreePrinter.printNode(AVL3.root);
    //  5
    // / \
    // 3 7

    // Insertion, LR-rotation test
    AVL AVL4 = new AVL();
    AVL4.insert(8);
    AVL4.insert(4);
    AVL4.insert(6);
    BTreePrinter.printNode(AVL4.root);
    //  6
    // / \
    // 4 8

    // --------------------------
    // Test 4: Insertion RR and RL Rotations
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 4: Insertion RR and RL Rotations");
    System.out.println("Expected:");
    System.out.println(" 4   \n" + "/ \\ \n" + "2 6 ");
    System.out.println();
    System.out.println(" 2   \n" + "/ \\ \n" + "1 4 ");

    System.out.println("\nGot:");

    // Insert, RR-rotation test
    AVL AVL31 = new AVL();
    AVL31.insert(2);
    AVL31.insert(4);
    AVL31.insert(6);
    BTreePrinter.printNode(AVL31.root);
    //  4
    // / \
    // 2 6

    // Insertion, RL-rotation test
    AVL AVL41 = new AVL();
    AVL41.insert(1);
    AVL41.insert(4);
    AVL41.insert(2);
    BTreePrinter.printNode(AVL41.root);
    //  2
    // / \
    // 1 4

    //        // --------------------------
    //        // Test 5: Deletion LL
    //        // --------------------------
    //        System.out.println("-------------------");
    //        System.out.println("Test 5: Deletion LL");
    //        System.out.println("Expected:");
    //        System.out.println("   6       \n" +
    //                "  / \\   \n" +
    //                " /   \\  \n" +
    //                " 5   8   \n" +
    //                "/   / \\ \n" +
    //                "4   7 9 ");
    //        System.out.println();
    //        System.out.println("   6       \n" +
    //                "  / \\   \n" +
    //                " /   \\  \n" +
    //                " 5   8   \n" +
    //                "/   / \\ \n" +
    //                "4   7 10 ");
    //
    //        System.out.println("\nGot:");
    //        // Delete a leaf, should do LL rotate
    //        AVL AVL5 = new AVL();
    //        AVL5.insert(8);
    //        AVL5.insert(6);
    //        AVL5.insert(9);
    //        AVL5.insert(5);
    //        AVL5.insert(7);
    //        AVL5.insert(10);
    //        AVL5.insert(4);
    //        // This is what the tree looks like prior to deletion
    //        // BTreePrinter.printNode(AVL5.root);
    //        //        8
    //        //       / \
    //        //      /   \
    //        //     /     \
    //        //    /       \
    //        //    6       9
    //        //   / \       \
    //        //  /   \       \
    //        //  5   7       10
    //        // /
    //        // 4
    //
    //        AVL5.delete(10);
    //        BTreePrinter.printNode(AVL5.root);
    //        //    6
    //        //   / \
    //        //  /   \
    //        //  5   8
    //        // /   / \
    //        // 4   7 9
    //
    //        // Delete a node with one child, should do LL rotate
    //        AVL AVL6 = new AVL();
    //        AVL6.insert(8);
    //        AVL6.insert(6);
    //        AVL6.insert(9);
    //        AVL6.insert(5);
    //        AVL6.insert(7);
    //        AVL6.insert(10);
    //        AVL6.insert(4);
    //        // This is what the tree looks like prior to deletion
    //        // BTreePrinter.printNode(AVL6.root);
    //        //        8
    //        //       / \
    //        //      /   \
    //        //     /     \
    //        //    /       \
    //        //    6       9
    //        //   / \       \
    //        //  /   \       \
    //        //  5   7       10
    //        // /
    //        // 4
    //
    //        AVL6.delete(9);
    //        BTreePrinter.printNode(AVL6.root);
    //        //    6
    //        //   / \
    //        //  /   \
    //        //  5   8
    //        // /   / \
    //        // 4   7 10
    //
    //        // --------------------------
    //        // Test 6: Deletion RR
    //        // --------------------------
    //        System.out.println("-------------------");
    //        System.out.println("Test 6: Deletion RR");
    //        System.out.println("Expected:");
    //        System.out.println("   6       \n" +
    //                "  / \\   \n" +
    //                " /   \\  \n" +
    //                " 3   7   \n" +
    //                "/ \\   \\ \n" +
    //                "2 5   8 ");
    //        System.out.println();
    //        System.out.println("   6       \n" +
    //                "  / \\   \n" +
    //                " /   \\  \n" +
    //                " 3   7   \n" +
    //                "/ \\   \\ \n" +
    //                "1 5   8 ");
    //
    //        System.out.println("\nGot:");
    //        // Delete a leaf, should do RR rotate
    //        AVL AVL51 = new AVL();
    //        AVL51.insert(3);
    //        AVL51.insert(6);
    //        AVL51.insert(2);
    //        AVL51.insert(7);
    //        AVL51.insert(5);
    //        AVL51.insert(1);
    //        AVL51.insert(8);
    //        // This is what the tree looks like prior to deletion
    //        // BTreePrinter.printNode(AVL51.root);
    //        //        3
    //        //       / \
    //        //      /   \
    //        //     /     \
    //        //    /       \
    //        //    2       6
    //        //   /       / \
    //        //  /       /   \
    //        //  1       5   7
    //        //               \
    //        //               8
    //
    //        AVL51.delete(1);
    //        BTreePrinter.printNode(AVL51.root);
    //        //    6
    //        //   / \
    //        //  /   \
    //        //  5   8
    //        // /   / \
    //        // 4   7 9
    //
    //        // Delete a node with one child, should do RR rotate
    //        // Delete a leaf, should do RR rotate
    //        AVL AVL61 = new AVL();
    //        AVL61.insert(3);
    //        AVL61.insert(6);
    //        AVL61.insert(2);
    //        AVL61.insert(7);
    //        AVL61.insert(5);
    //        AVL61.insert(1);
    //        AVL61.insert(8);
    //        // This is what the tree looks like prior to deletion
    //        // BTreePrinter.printNode(AVL61.root);
    //        //        3
    //        //       / \
    //        //      /   \
    //        //     /     \
    //        //    /       \
    //        //    2       6
    //        //   /       / \
    //        //  /       /   \
    //        //  1       5   7
    //        //               \
    //        //               8
    //
    //        AVL61.delete(2);
    //        BTreePrinter.printNode(AVL61.root);
    //        //    6
    //        //   / \
    //        //  /   \
    //        //  5   8
    //        // /   / \
    //        // 4   7 10
    //
    //
    //        // --------------------------
    //        // Test 7: Deletion LR
    //        // --------------------------
    //        System.out.println("-------------------");
    //        System.out.println("Test 7: Deletion LR");
    //        System.out.println("Expected:");
    //        System.out.println("   7       \n" +
    //                "  / \\   \n" +
    //                " /   \\  \n" +
    //                " 5   8   \n" +
    //                "/ \\   \\ \n" +
    //                "4 6   9 ");
    //
    //        System.out.println("\nGot:");
    //        // Delete a leaf, should do LR rotate
    //        AVL AVL7 = new AVL();
    //        AVL7.insert(8);
    //        AVL7.insert(5);
    //        AVL7.insert(9);
    //        AVL7.insert(4);
    //        AVL7.insert(7);
    //        AVL7.insert(10);
    //        AVL7.insert(6);
    //
    //        // This what the tree looks like prior to deletion
    //        // BTreePrinter.printNode(AVL7.root);
    //        //       8
    //        //      / \
    //        //     /   \
    //        //    /     \
    //        //   /       \
    //        //   5       9
    //        //  / \       \
    //        // /   \       \
    //        // 4   7       10
    //        //    /
    //        //    6
    //
    //        AVL7.delete(10);
    //        BTreePrinter.printNode(AVL7.root);
    //        //    7
    //        //   / \
    //        //  /   \
    //        //  5   8
    //        // / \   \
    //        // 4 6   9
    //
    //        // --------------------------
    //        // Test 8: Deletion with 2 RL rotations
    //        // --------------------------
    //        System.out.println("-------------------");
    //        System.out.println("Test 8: Deletion with 2 RL rotations");
    //        System.out.println("Expected:");
    //        System.out.println("       7               \n" +
    //                "      / \\       \n" +
    //                "     /   \\      \n" +
    //                "    /     \\     \n" +
    //                "   /       \\    \n" +
    //                "   5       10       \n" +
    //                "  / \\     / \\   \n" +
    //                " /   \\   /   \\  \n" +
    //                " 3   6   9   11   \n" +
    //                "/ \\     /     \\ \n" +
    //                "2 4     8     12 ");
    //
    //        // Delete a leaf, should rotate twice RL and then RL
    //        System.out.println("\nGot:");
    //        AVL AVL8 = new AVL();
    //        AVL8.insert(5);
    //
    //        AVL8.insert(2);
    //        AVL8.insert(10);
    //
    //        AVL8.insert(1);
    //        AVL8.insert(4);
    //        AVL8.insert(7);
    //        AVL8.insert(11);
    //
    //        AVL8.insert(3);
    //        AVL8.insert(6);
    //        AVL8.insert(9);
    //        AVL8.insert(12);
    //
    //        AVL8.insert(8);
    //
    //        // This is what the tree looks like prior to deletion
    //        // BTreePrinter.printNode(AVL8.root);
    //        //               5
    //        //              / \
    //        //             /   \
    //        //            /     \
    //        //           /       \
    //        //          /         \
    //        //         /           \
    //        //        /             \
    //        //       /               \
    //        //       2               10
    //        //      / \             / \
    //        //     /   \           /   \
    //        //    /     \         /     \
    //        //   /       \       /       \
    //        //   1       4       7       11
    //        //          /       / \       \
    //        //         /       /   \       \
    //        //         3       6   9       12
    //        //                    /
    //        //                    8
    //
    //        AVL8.delete(1);
    //        BTreePrinter.printNode(AVL8.root);
    //        //        7
    //        //       / \
    //        //      /   \
    //        //     /     \
    //        //    /       \
    //        //    5       10
    //        //   / \     / \
    //        //  /   \   /   \
    //        //  3   6   9   11
    //        // / \     /     \
    //        // 2 4     8     12
  }
}

class Node {
  int data;
  int height; // each node will keep track of
  // how tall its subtree is
  Node left;
  Node right;

  public Node(int data) {
    this.data = data;
    this.height = 1; // all nodes will start as leaves,
    // so they will have a default height of 1
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
    int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
    int firstSpaces = (int) Math.pow(2, (floor)) - 1;
    int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

    BTreePrinter.printWhitespaces(firstSpaces);

    List<Node> newNodes = new ArrayList<Node>();
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
    System.out.println("");

    for (int i = 1; i <= endgeLines; i++) {
      for (int j = 0; j < nodes.size(); j++) {
        BTreePrinter.printWhitespaces(firstSpaces - i);
        if (nodes.get(j) == null) {
          BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
          continue;
        }

        if (nodes.get(j).left != null) System.out.print("/");
        else BTreePrinter.printWhitespaces(1);

        BTreePrinter.printWhitespaces(i + i - 1);

        if (nodes.get(j).right != null) System.out.print("\\");
        else BTreePrinter.printWhitespaces(1);

        BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
      }

      System.out.println("");
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
