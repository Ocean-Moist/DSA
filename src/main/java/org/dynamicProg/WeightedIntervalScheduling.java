package org.dynamicProg;

public class WeightedIntervalScheduling {
  // Intervals are assumed to be sorted by finishing time
  WeightedInterval[] intervals;

  public WeightedIntervalScheduling(WeightedInterval[] intervals) {
    this.intervals = intervals;
  }

  // Return the index of the last (rightmost) interval that finishes
  // before the interval with index ind
  // The method should return -1 if there is no such interval
  // You'll likely want to use this method in your OPT!
  public int latestNonOverlapping(int ind) {
    for (int i = ind - 1; i >= 0; i--) {
      if (intervals[i].finishingTime <= intervals[ind].startingTime) {
        return i;
      }
    }
    return -1;
  }

  // Returns an int corresponding the maximum combined weight of
  // non-overlapping intervals
  public int getOptimalSolution() {
    int[] opt = new int[intervals.length];
    opt[0] = intervals[0].value;

    for (int i = 1; i < intervals.length; i++) {
      int lastNonOverlapping = latestNonOverlapping(i);

      if (lastNonOverlapping == -1) {
        opt[i] = Math.max(intervals[i].value, opt[i - 1]);
      } else {
        opt[i] = Math.max(intervals[i].value + opt[lastNonOverlapping], opt[i - 1]);
      }
    }

    return opt[opt.length - 1];
  }

  public static void main(String[] args) {
    // --------------------------
    // Test 1: Latest Nonoverlapping Tests
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 1: Latest Nonoverlapping Tests");
    System.out.println("Expected:");
    System.out.println("-1\n" + "-1\n" + "0\n" + "-1\n" + "2\n" + "2");

    System.out.println("\nGot:");

    // Create some WeightedIntervals
    WeightedInterval w0 = new WeightedInterval(0, 3, 2);
    WeightedInterval w1 = new WeightedInterval(1, 5, 4);
    WeightedInterval w2 = new WeightedInterval(4, 6, 4);
    WeightedInterval w3 = new WeightedInterval(2, 9, 7);
    WeightedInterval w4 = new WeightedInterval(7, 10, 2);
    WeightedInterval w5 = new WeightedInterval(8, 11, 1);

    WeightedInterval[] intervals1 = {w0, w1, w2, w3, w4, w5};

    WeightedIntervalScheduling w = new WeightedIntervalScheduling(intervals1);

    for (int i = 0; i < intervals1.length; i++) {
      System.out.println(w.latestNonOverlapping(i));
    }

    // --------------------------
    // Test 2: Optimal Solution
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 2: Optimal Solution");
    System.out.println("Expected:");
    System.out.println("8");

    System.out.println("\nGot:");

    System.out.println(w.getOptimalSolution());

    // --------------------------
    // Test 3: Optimal Solution (More Complex)
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 3: Optimal Solution (More Complex)");
    System.out.println("Expected:");
    System.out.println("24");

    System.out.println("\nGot:");

    // Create some WeightedIntervals
    WeightedInterval i0 = new WeightedInterval(0, 3, 2);
    WeightedInterval i1 = new WeightedInterval(1, 5, 4);
    WeightedInterval i2 = new WeightedInterval(4, 6, 4);
    WeightedInterval i3 = new WeightedInterval(2, 9, 7);
    WeightedInterval i4 = new WeightedInterval(7, 10, 2);
    WeightedInterval i5 = new WeightedInterval(8, 11, 1);
    WeightedInterval i6 = new WeightedInterval(12, 13, 7);
    WeightedInterval i7 = new WeightedInterval(11, 15, 3);
    WeightedInterval i8 = new WeightedInterval(13, 17, 1);
    WeightedInterval i9 = new WeightedInterval(16, 20, 5);
    WeightedInterval i10 = new WeightedInterval(17, 22, 6);
    WeightedInterval i11 = new WeightedInterval(25, 26, 2);

    WeightedInterval[] intervals2 = {i0, i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11};

    WeightedIntervalScheduling w22 = new WeightedIntervalScheduling(intervals2);
    System.out.println(w22.getOptimalSolution());
  }
}

class WeightedInterval {
  int startingTime;
  int finishingTime;
  int value;

  public WeightedInterval(int startingTime, int finishingTime, int value) {
    this.startingTime = startingTime;
    this.finishingTime = finishingTime;
    this.value = value;
  }
}
