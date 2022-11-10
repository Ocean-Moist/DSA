package org.hashTable;

public class LinearProbingHash {
  int capacity;
  int currentSize; // optional variable
  int[] arr;

  // Create hash table with a capacity of 10
  public LinearProbingHash() {
    this.capacity = 10;
    this.arr = new int[capacity];
    this.currentSize = 0;
  }

  // Create hash table with any capacity
  public LinearProbingHash(int capacity) {
    this.capacity = capacity;
    this.arr = new int[capacity];
    this.currentSize = 0;
  }

  // A simple hash function
  // It will just calculate the data value % capacity
  public int hash(int data) {
    return data % capacity;
  }

  // Inserts data into the hash table
  // Here are the main steps for insertion:
  // * If the table is full, first rehash the table
  // * Calculate hash value for the data
  // * Check if the appropriate index of the array is empty
  // * If not, keep checking the subsequent entries until you find an empty spot
  public void insert(int data) {
    if (currentSize == capacity) {
      rehash();
    }

    int hash = hash(data);
    while (arr[hash] != 0 && arr[hash] != -1) {
      hash = (hash + 1) % capacity;
    }

    arr[hash] = data;
    currentSize++;
  }

  private void rehash() {
    capacity *= 2;
    int[] newArr = new int[capacity];

    for (int j : arr) {
      if (j != 0 && j != -1) {
        int hash = hash(j);
        while (newArr[hash] != 0 && newArr[hash] != -1) {
          hash = (hash + 1) % capacity;
        }

        newArr[hash] = j;
      }
    }

    arr = newArr;
  }

  // Searches the hash table
  // Returns true if data is present and false otherwise
  public boolean search(int data) {
    int hash = hash(data);

    if (arr[hash] != data) {
      int i = hash + 1;
      while (arr[i] != 0) {
        if (arr[i] == data) {
          return true;
        }
        i = (i + 1) % capacity;
        if (i == hash) {
          return false;
        }
      }
      return false;
    } else {
      return true;
    }
  }
  // Deletes data from the hash table
  // Change the value for the respective entry to -1
  // Should just return if the data is not found
  public void delete(int data) {
    int index = hash(data);
    while (arr[index] != 0) {
      if (arr[index] == data) {
        arr[index] = -1;
        currentSize--;
        return;
      }
      index++;
      index %= capacity;
      if (index == hash(data)) {
        return;
      }
    }
  }

  // Prints out the table in a nice-ish format
  public void print() {
    System.out.println(this);
  }

  @Override
  public String toString() {
    StringBuilder x = new StringBuilder(100);
    x.append("INDEX | DATA\n");
    x.append("------------\n");

    for (int i = 0; i < capacity; i++) {
      StringBuilder index = new StringBuilder("" + i);
      StringBuilder data = new StringBuilder("" + this.arr[i]);
      int indexLen = index.length();
      int dataLen = data.length();

      // pad index spaces
      for (int j = 0; j < 5 - indexLen; j++) {
        index.insert(0, " ");
      }

      // pad data spaces
      for (int j = 0; j < 5 - dataLen; j++) {
        data.insert(0, " ");
      }

      x.append(index).append(" |").append(data).append("\n");
    }

    return x.toString();
  }

  public static void main(String[] args) {
    // --------------------------
    // Test 1: Basic Insertion
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 1: Basic Insertion");
    System.out.println("Expected:");
    System.out.println(
        "INDEX | DATA\n"
            + "------------\n"
            + "    0 |    0\n"
            + "    1 |   11\n"
            + "    2 |    0\n"
            + "    3 |    3\n"
            + "    4 |   34\n"
            + "    5 |    5\n"
            + "    6 |   26\n"
            + "    7 |    7\n"
            + "    8 |    0\n"
            + "    9 |    0");

    System.out.println("\nGot:");
    LinearProbingHash table1 = new LinearProbingHash();

    // Insert some values to the table
    table1.insert(5);
    table1.insert(7);
    table1.insert(3);
    table1.insert(11);
    table1.insert(26);
    table1.insert(34);
    table1.print();
    // INDEX | DATA
    // ------------
    //     0 |    0
    //     1 |   11
    //     2 |    0
    //     3 |    3
    //     4 |   34
    //     5 |    5
    //     6 |   26
    //     7 |    7
    //     8 |    0
    //     9 |    0

    // --------------------------
    // Test 2: Insertion w/ collisions
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 2: Insertion w/ collisions");
    System.out.println("Expected:");
    System.out.println(
        "INDEX | DATA\n"
            + "------------\n"
            + "    0 |    0\n"
            + "    1 |   11\n"
            + "    2 |   21\n"
            + "    3 |    3\n"
            + "    4 |   34\n"
            + "    5 |    5\n"
            + "    6 |   26\n"
            + "    7 |    7\n"
            + "    8 |   36\n"
            + "    9 |   49");
    System.out.println("\nGot:");

    // Insert some values whose hashed values are occupied
    // They should do linear probing and look for the next empty slot
    table1.insert(21);
    table1.insert(36);
    table1.insert(49);
    table1.print();
    // INDEX | DATA
    // ------------
    //     0 |    0
    //     1 |   11
    //     2 |   21
    //     3 |    3
    //     4 |   34
    //     5 |    5
    //     6 |   26
    //     7 |    7
    //     8 |   36
    //     9 |   49

    // --------------------------
    // Test 3: Insertion w/ wraparound
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 3: Insertion w/ wraparound");
    System.out.println("Expected:");
    System.out.println(
        "INDEX | DATA\n"
            + "------------\n"
            + "    0 |   31\n"
            + "    1 |   11\n"
            + "    2 |   21\n"
            + "    3 |    3\n"
            + "    4 |   34\n"
            + "    5 |    5\n"
            + "    6 |   26\n"
            + "    7 |    7\n"
            + "    8 |   36\n"
            + "    9 |   49");
    System.out.println("\nGot:");

    // Linear probing should wrap around the table
    table1.insert(31);
    table1.print();
    // INDEX | DATA
    // ------------
    //     0 |   31
    //     1 |   11
    //     2 |   21
    //     3 |    3
    //     4 |   34
    //     5 |    5
    //     6 |   26
    //     7 |    7
    //     8 |   36
    //     9 |   49

    // --------------------------
    // Test 4: Search
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 4: Search");
    System.out.println("Expected:");
    System.out.println("true");
    System.out.println("true");
    System.out.println("true");
    System.out.println("true");
    System.out.println("false");
    System.out.println("false");
    System.out.println("false");
    System.out.println("false");

    System.out.println("\nGot:");
    System.out.println(table1.search(31)); //  true
    System.out.println(table1.search(11)); //  true
    System.out.println(table1.search(21)); //  true
    System.out.println(table1.search(3)); //   true
    System.out.println(table1.search(131)); // false
    System.out.println(table1.search(226)); // false
    System.out.println(table1.search(2)); //   false
    System.out.println(table1.search(4)); //   false

    // --------------------------
    // Test 5: Deletion
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 5: Deletion");
    System.out.println("Expected:");
    System.out.println(
        "INDEX | DATA\n"
            + "------------\n"
            + "    0 |   31\n"
            + "    1 |   -1\n"
            + "    2 |   21\n"
            + "    3 |    3\n"
            + "    4 |   34\n"
            + "    5 |    5\n"
            + "    6 |   26\n"
            + "    7 |    7\n"
            + "    8 |   -1\n"
            + "    9 |   49");

    System.out.println("\nGot:");
    // Delete a number in the table that is located at its
    // hash value
    table1.delete(11);

    // Then delete a number that was inserted using
    // linear probing
    table1.delete(36);

    // Then delete a number that is not in the table
    table1.delete(100);
    table1.print();
    // INDEX | DATA
    // ------------
    //     0 |   31
    //     1 |   -1
    //     2 |   21
    //     3 |    3
    //     4 |   34
    //     5 |    5
    //     6 |   26
    //     7 |    7
    //     8 |   -1
    //     9 |   49

    // --------------------------
    // Test 6: Insertion and Search after Deletion
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 6: Insertion and Search after Deletion");
    System.out.println("Expected:");
    System.out.println("true");
    System.out.println("false");
    System.out.println();
    System.out.println(
        "INDEX | DATA\n"
            + "------------\n"
            + "    0 |   31\n"
            + "    1 |   11\n"
            + "    2 |   21\n"
            + "    3 |    3\n"
            + "    4 |   34\n"
            + "    5 |    5\n"
            + "    6 |   26\n"
            + "    7 |    7\n"
            + "    8 |   -1\n"
            + "    9 |   49\n");

    System.out.println("\nGot:");
    System.out.println(table1.search(31)); //  true
    System.out.println(table1.search(36)); //  false
    System.out.println();

    // When inserted after deletion, the -1 should be
    // able to be replaced
    table1.insert(11);
    table1.print();
    // INDEX | DATA
    // ------------
    //     0 |   31
    //     1 |   11
    //     2 |   21
    //     3 |    3
    //     4 |   34
    //     5 |    5
    //     6 |   26
    //     7 |    7
    //     8 |   -1
    //     9 |   49

    // --------------------------
    // Test 7: Insertion Rehashes
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 7: Insertion Rehashes");
    System.out.println("Expected:");
    System.out.println(
        "INDEX | DATA\n"
            + "------------\n"
            + "    0 |    0\n"
            + "    1 |   21\n"
            + "    2 |    0\n"
            + "    3 |    3\n"
            + "    4 |    0\n"
            + "    5 |    5\n"
            + "    6 |   26\n"
            + "    7 |    7\n"
            + "    8 |    8\n"
            + "    9 |   49\n"
            + "   10 |    0\n"
            + "   11 |   31\n"
            + "   12 |   11\n"
            + "   13 |   52\n"
            + "   14 |   34\n"
            + "   15 |    0\n"
            + "   16 |    0\n"
            + "   17 |    0\n"
            + "   18 |    0\n"
            + "   19 |    0");
    System.out.println();
    System.out.println("true");

    System.out.println("\nGot:");

    // Still room for this
    table1.insert(8);

    // This should cause a rehash
    table1.insert(52);
    table1.print();
    // INDEX | DATA
    // ------------
    //     0 |    0
    //     1 |   21
    //     2 |    0
    //     3 |    3
    //     4 |    0
    //     5 |    5
    //     6 |   26
    //     7 |    7
    //     8 |    8
    //     9 |   49
    //    10 |    0
    //    11 |   31
    //    12 |   11
    //    13 |   52
    //    14 |   34
    //    15 |    0
    //    16 |    0
    //    17 |    0
    //    18 |    0
    //    19 |    0

    // Search should still work
    System.out.println(table1.search(31)); //  true
  }
}
