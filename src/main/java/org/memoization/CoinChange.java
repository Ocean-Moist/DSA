package org.memoization;

import java.util.HashMap;
import java.util.stream.IntStream;

public class CoinChange {
  // This HashMap will store the previously computed
  // values for the coin change problem
  HashMap<Integer, Integer> memo;

  // Constructor just creates an empty HashMap
  public CoinChange() {
    memo = new HashMap<>();
  }

  // The recursive method is given an int[] corresponding
  // to the available coin types, and an amount of money
  // to add up to
  public int change(int[] coins, int amount) {
    if (amount == 0) return 0;
    if (amount < 0) return -1;
    if (memo.containsKey(amount)) return memo.get(amount);

    int min = Integer.MAX_VALUE;

    for (int coin : coins) {
      int result = change(coins, amount - coin);

      if (result == -1) {
        continue;
      }
      min = Math.min(min, result + 1);
    }

    if (min != Integer.MAX_VALUE) {
      memo.put(amount, min);
    } else {
      memo.put(amount, -1);
    }

    return memo.get(amount);
  }

  // leetcode: https://leetcode.com/problems/climbing-stairs/description/
  class Solution {
    private HashMap<Integer, Integer> memo = new HashMap<>();

    public int climbStairsMemo(int n) {
      if (n == 1) return 1;
      if (n == 2) return 2;

      if (!memo.containsKey(n)) memo.put(n,climbStairsMemo(n-2) + climbStairsMemo(n-1));

      return memo.get(n);
    }

    public int climbStairsTab(int n) {
      if (n == 1) return 1;
      if (n == 2) return 2;

      int[] dp = new int[n+1];
      dp[1] = 1;
      dp[2] = 2;

      IntStream.rangeClosed(3, n).forEachOrdered(i -> dp[i] = dp[i - 1] + dp[i - 2]);

      return dp[n];
    }
  }


  public static void main(String[] args) {
    // Note: Test 1 should run smoothly even if you haven't implemented
    // memoization yet. So Test 1 could be a good way to test that your
    // recursive method is working. However, don't try to run Test 2
    // without implementing memoization!

    // --------------------------
    // Test 1: Small coin change tests
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 1: Small coin change tests");
    System.out.println("Expected:");
    System.out.println("4\n" + "7\n" + "4\n" + "7");

    System.out.println("\nGot:");

    CoinChange c1 = new CoinChange();
    System.out.println(c1.change(new int[] {1, 4, 5}, 18));
    // 4

    CoinChange c2 = new CoinChange();
    System.out.println(c2.change(new int[] {1, 4, 5}, 33));
    // 7

    CoinChange c3 = new CoinChange();
    System.out.println(c3.change(new int[] {1, 3, 5}, 16));
    // 4

    CoinChange c4 = new CoinChange();
    System.out.println(c4.change(new int[] {1, 3, 5}, 33));
    // 7

    // Warning: if you haven't memoized your code yet,
    // this is going to take a long, long time
    // --------------------------
    // Test 2: Large coin change tests
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 2: Large coin change tests");
    System.out.println("Expected:");
    System.out.println("118\n" + "258");

    System.out.println("\nGot:");

    System.out.println("Uncomment this test after memoization is working");

    CoinChange c5 = new CoinChange();
    System.out.println(c5.change(new int[] {1, 4, 5}, 588));

    CoinChange c6 = new CoinChange();
    System.out.println(c6.change(new int[] {1, 4, 5}, 1288));
    // 258

    // My computer tried running this without memoization, and
    // it did not complete in 20+ minutes
    // Getting stack overflow error here is possible as well
  }

  // This is an example of how memoization works
  public class Fibonacci {
    // This HashMap will store the previously computed
    // values for the Fibonacci sequence
    HashMap<Integer, Integer> memo;

    public Fibonacci() {
      memo = new HashMap<>();
    }

    public int fib(int n) {
      // Base case
      if (n == 0 || n == 1) {
        return n;
      }
      // If we've already calculated fib(n), return the
      // stored value
      else if (memo.containsKey(n)) {
        return memo.get(n);
      }
      // If not, calculate the value, store, and return it
      else {
        int num = fib(n - 1) + fib(n - 2);
        memo.put(n, num);
        return num;
      }
    }
  }
}
