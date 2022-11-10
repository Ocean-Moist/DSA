package org.challenegeProblems;

import java.util.function.BiFunction;
import java.util.function.Function;

public class ChallengeProblems {
  public static void challenge1() {
    // i know the sum of consec ints Sn is n(a_1+a_n)/2 where a_1 is the first int and a_n is the
    // last int
    BiFunction<Integer, Integer, Integer> sum = (a, n) -> n * (a + a * n) / 2;
    // so
    Function<Integer, Integer> sumWithMultiple = (n) -> sum.apply(1000, (int) Math.floor(1000 / n));

    int sumOfMultiples =
        sumWithMultiple.apply(3) + sumWithMultiple.apply(5) - sumWithMultiple.apply(15);
    System.out.println(sumOfMultiples);
  }

  public static void main(String[] args) {
    challenge1();
  }
}
