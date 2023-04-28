package org.dynamicProg;

import java.util.Arrays;
import java.util.stream.IntStream;

public class SubsetSum {
  // Given an array of integers and an int sum, determine if some subset
  // of those integers adds up to exactly sum

  // For example, if our input array is [1, 1, 4], the possible sums which would
  // return true are 0, 1, 2, 4, 5, 6. Any other sum value would return false.
  public static boolean subsetSum(int[] arr, int sum) {
    int[][] dp = new int[arr.length + 1][sum + 1];
    for (int i = 0; i < dp.length; i++) {
      dp[i][0] = 1;
    }
    for (int i = 1; i < dp.length; i++) {
      for (int j = 1; j < dp[0].length; j++) {
        if (j - arr[i - 1] >= 0) {
          dp[i][j] = dp[i - 1][j] + dp[i - 1][j - arr[i - 1]];
        } else {
          dp[i][j] = dp[i - 1][j];
        }
      }
    }
    return dp[arr.length][sum] > 0;
  }

  // Given some items with both weight and value, as well as a max capacity
  // determine the most value we can fit in the knapsack

  // Note that the weight and value of each item is stored in same index of the relevant array
  // (e.g. weights[2] is the weight of item 2 and values[2] is that same item's value)
  public static int knapsack(int[] weights, int[] values, int capacity) {
    int[][] dp = new int[weights.length + 1][capacity + 1];
    for (int i = 1; i < dp.length; i++) {
      for (int j = 1; j < dp[0].length; j++) {
        if (j - weights[i - 1] >= 0) {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i - 1]] + values[i - 1]);
        } else {
          dp[i][j] = dp[i - 1][j];
        }
      }
    }
    return dp[weights.length][capacity];

    // branch and bound
    //
    //    int[] sortedWeights =
    //        Arrays.stream(weights).boxed().sorted((a, b) -> b - a).mapToInt(i -> i).toArray();
    //    int[] sortedVals =
    //        Arrays.stream(values).boxed().sorted((a, b) -> b - a).mapToInt(i -> i).toArray();
    //
    //    Queue<Map.Entry<Integer, Integer>> items = new LinkedList<>();
    //    items.add(Map.entry(0, 0));
    //
    //    while (!items.isEmpty()) {
    //      Map.Entry<Integer, Integer> item = items.remove();
    //
    //      if (item.getKey() == sortedWeights.length) {
    //        best = Math.max(best, item.getValue());
    //      }
    //    }
    //
    //    return best;
  }

  public int minFallingPathSum(int[][] matrix) {
    int n = matrix.length;
    int[][] dp = new int[n + 1][n + 2];

    IntStream.range(0, dp.length)
        .forEachOrdered(
            i -> {
              dp[i][0] = Integer.MAX_VALUE;
              dp[i][dp[0].length - 1] = Integer.MAX_VALUE;
            });

    for (int i = n - 1; i >= 0; i--) {
      for (int j = 0; j < n; j++) {
        int[] moves = new int[] {dp[i + 1][j], dp[i + 1][j + 1], dp[i + 1][j + 2]};
        dp[i][j + 1] = matrix[i][j] + Arrays.stream(moves).min().getAsInt();
      }
    }

    return Arrays.stream(dp[0]).min().getAsInt();
  }

  public static void main(String[] args) {
    // --------------------------
    // Test 1: Basic Subset Sum
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 1: Basic Subset Sum");
    System.out.println("Expected:");
    System.out.println("true\n" + "true\n" + "true\n" + "false");

    System.out.println("\nGot:");

    // Does {1, 1, 3, 7} have a subset sum of 5?
    int[] arr1 = {1, 1, 3, 7};
    System.out.println(subsetSum(arr1, 5));
    // true

    // Does {1, 1, 3, 7} have a subset sum of 11?
    System.out.println(subsetSum(arr1, 11));
    // true

    // Does {1, 1, 3, 7} have a subset sum of 12?
    System.out.println(subsetSum(arr1, 12));
    // true

    // Does {1, 1, 3, 7} have a subset sum of 6?
    System.out.println(subsetSum(arr1, 6));
    // false

    // --------------------------
    // Test 2: More Subset Sum
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 2: More Subset Sum");
    System.out.println("Expected:");
    System.out.println("true\n" + "true\n" + "true\n" + "false");

    System.out.println("\nGot:");

    // Does {1, 1, 5, 6, 8, 10, 12, 14, 16} have a subset sum of 11?
    int[] arr2 = {1, 1, 5, 6, 8, 10, 12, 14, 16};
    System.out.println(subsetSum(arr2, 11));
    // true

    // Does {1, 1, 5, 6, 8, 10, 12, 14, 16} have a subset sum of 28?
    System.out.println(subsetSum(arr2, 28));
    // true

    // Does {1, 1, 5, 6, 8, 10, 12, 14, 16} have a subset sum of 32?
    System.out.println(subsetSum(arr2, 32));
    // true

    // Does {1, 1, 5, 6, 8, 10, 12, 14, 16} have a subset sum of 68?
    System.out.println(subsetSum(arr1, 68));
    // true

    // --------------------------
    // Test 3: Basic Knapsack
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 3: Basic Knapsack");
    System.out.println("Expected:");
    System.out.println("160\n" + "290\n" + "200");

    System.out.println("\nGot:");

    // Basic Tests
    int[] weights1 = {5, 10, 25};
    int[] values1 = {70, 90, 140};
    System.out.println(knapsack(weights1, values1, 25));
    // 160

    int[] weights2 = {5, 10, 20};
    int[] values2 = {150, 60, 140};
    System.out.println(knapsack(weights2, values2, 30));
    // 290

    int[] weights3 = {5, 20, 10};
    int[] values3 = {50, 140, 60};
    System.out.println(knapsack(weights3, values3, 30));
    // 200

    // --------------------------
    // Test 4: Advanced Knapsack
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 4: Advanced Knapsack");
    System.out.println("Expected:");
    System.out.println("117");

    System.out.println("\nGot:");

    // More advanced test
    int[] weights4 = {85, 26, 48, 21, 22, 95, 43, 45, 55, 52};
    int[] values4 = {79, 32, 47, 18, 26, 85, 33, 40, 45, 59};
    System.out.println(knapsack(weights4, values4, 101));
    // 117
  }
}
