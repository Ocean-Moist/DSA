package org.rnaLab;

import java.util.Arrays;

public class RNA {
  // Returns true if the given characters are a matching pair
  // (i.e. one of 'A'+'U', 'U'+'A', 'C'+'G', or 'G'+'C')
  public static boolean isPair(char b1, char b2) {
    return ((b1 == 'A') && (b2 == 'U'))
        || ((b1 == 'U') && (b2 == 'A'))
        || ((b1 == 'C') && (b2 == 'G'))
        || ((b1 == 'G') && (b2 == 'C'));
  }

  // Given a sequence of base pairs (e.g. "ACCGGUAGU"),
  // finds the number of base pairs that can match while maintaining
  // A goes to U and C goes to G
  // the following constraints:
  // * No sharp turns: each pair must be separated by at least 4 intervening bases; (i < j - 4)
  // * A pair must be either A and U or C and G
  // * A single base can only appear in at most one pair
  // * Non-crossing condition: if (i, j) and (k, l) are both pairs, we cannot have i < k < j < l
  // consider GAAAAACGAAAAAC
  public static int numberBasePairs(String sequence) {
    int n = sequence.length();
    int[][] table = new int[n][n];
    boolean t = false;
    int y = 0;

    for (int k = 5; k < n; k++) {
      for (int i = 0; i < n - k; i++) {
        int j = i + k;

        if (t
            && isPair(sequence.charAt(j), sequence.charAt(y))
            && i == 0
            && j == n - 1
            && table[i + 1][j] == table[i][j - 1]) {
          table[i][j] = Math.max(table[i + 1][j], table[i][j - 1]) + 1;
        } else if (isPair(sequence.charAt(i), sequence.charAt(j))) {
          if (!t) {
            t = true;
            y = j;
          }
          table[i][j] = table[i + 1][j - 1] + 1;
        } else {
          table[i][j] = Math.max(table[i + 1][j], table[i][j - 1]);
        }
      }
    }

    // print tbale
    for (int[] i : table) {
      System.out.println(Arrays.toString(i));
    }

    return table[0][n - 1];
  }

  public static void main(String[] args) {
    // --------------------------
    // Test 1: RNA Structure
    // --------------------------
    //    System.out.println("-------------------");
    //    System.out.println("Test 1: Longest Continuous Palindrome");
    //    System.out.println("Expected: 2");
    //    System.out.println("2\n" + "5\n" + "15\n" + "14\n" + "1\n" + "2\n");
    //    //
    //    System.out.println(numberBasePairs("ACCGGUAGU")); // 2
    //    System.out.println(numberBasePairs("ACAUGAUGGCCAUGU")); // 5
    //    System.out.println(numberBasePairs("ACACACACACACACAAAUUUGUGUGUGUGUGUGU")); // 15
    //    System.out.println(numberBasePairs("CAGAUCGGCGAUACGAGCAUAGCAAUGCUAAGCGAGCUUAGCUGCA")); //
    // 14
    // test crossing over
    System.out.println(numberBasePairs("AAAAGAAAAAGCAAAAACAAAAAA")); // 1
    System.out.println(numberBasePairs("AAAAGAAAAACGAAAAACAAAAAA")); // 2
  }
}
