package org.rnaLab;

public class Palindrome {

  // Part 1: Longest Continuous Palindrome
  // Some of the code outline has been provided
  public static int palindrome(String x) {
    // You'll likely want to keep track of the longest substring you see
    int max = 1;

    // Make the table
    int n = x.length();
    boolean[][] table = new boolean[n][n];

    // Base cases, fill in base case diagonals
    for (int i = 0; i < n; i++) {
      table[i][i] = true;
    }

    // Looping through the rest of the diagonals
    // Note that k is the length of substring along the current diagonal
    // We loop through i, and then calculate j based on k and i
    for (int k = 3; k <= n; k++) {
      for (int i = 0; i <= n - k; i++) {
        // We calculate j based on i and k
        int j = i + k - 1;

        if (x.charAt(i) == x.charAt(j) && table[i + 1][j - 1]) {
          table[i][j] = true;
          max = k;
        }
      }
    }

    return max;
  }

  // Part 2: Palindrome Completion
  //
  public static int complete(String str) {
    // how many letters to make a palindrome
    int n = str.length();
    int[][] table = new int[n][n];

    // Base cases
    for (int i = 0; i < n; i++) {
      table[i][i] = 0;
    }

    for (int i = 2; i <= n; i++) {
      for (int j = 0; j <= n - i; j++) {
        int k = j + i - 1;

        if (str.charAt(j) == str.charAt(k)) {
          table[j][k] = table[j + 1][k - 1];
        } else {
          table[j][k] = Math.min(table[j + 1][k], table[j][k - 1]) + 1;
        }
      }
    }

    return table[0][n - 1];
  }

  public static void main(String[] args) {
    // --------------------------
    // Test 1: Longest Continuous Palindrome
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 1: Longest Continuous Palindrome");
    System.out.println("Expected:");
    System.out.println("5\n" + "7");

    System.out.println("\nGot:");
    System.out.println(palindrome("BABBCCCB")); // 5
    System.out.println(palindrome("helloasdfasdfracecarjkljkl")); // 7

    // --------------------------
    // Test 2: Palindrome Completion
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 2: Palindrome Completion");
    System.out.println("Expected:");
    System.out.println("3\n" + "4\n" + "2\n" + "2\n" + "5\n");

    System.out.println("\nGot:");
    System.out.println(complete("race")); // 3 to get to racecar
    System.out.println(complete("abcde")); // 4 to get to abcdedcba
    System.out.println(complete("mda")); // 2 to get to madam
    System.out.println(complete("tcoat")); // 2 to get to tacocat
    System.out.println(complete("amsdfudksv")); // 5 to get to vamskdfufdksmav
  }
}
