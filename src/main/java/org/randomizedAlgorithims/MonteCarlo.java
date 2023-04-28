package org.randomizedAlgorithims;

import java.util.Random;
import java.util.function.DoubleFunction;

public class MonteCarlo {
  // Runs one trial of Pepys (i.e. rolls numDice dice and returns
  // true if at least numSix 6's where rolled)
  // This is example code that has been provided for you
  public static boolean pepysTrial(int numDice, int numSix) {
    Random r = new Random();
    int countSix = 0;
    for (int i = 0; i < numDice; i++) {
      int roll = r.nextInt(6) + 1;

      if (roll == 1) {
        countSix++;
      }
    }

    return (countSix >= numSix);
  }

  // Runs numTrials trials of Pepys
  // This is example code that has been provided for you
  public static double pepysSimulation(int numTrials, int numDice, int numSix) {
    int numSuccess = 0;
    for (int i = 0; i < numTrials; i++) {
      if (pepysTrial(numDice, numSix)) {
        numSuccess++;
      }
    }
    return ((double) numSuccess) / numTrials;
  }

  // Calculate pi using Monte Carlo simulation
  // "Throw" a dart by randoming generating an x and y coordinate
  // Then, check to see if the coordinate is inside the circle x^2 + y^2 = 1
  public static double calculatePi(int numTrials) {
    double inCircle = 0;
    double notInCircle = 0;
    DoubleFunction<Double> circle = (double x) -> Math.sqrt(1 - Math.pow(x, 2));

    for (int i = 0; i < numTrials; i++) {
      double x = Math.random();
      double y = Math.random();

      if (y <= circle.apply(x)) {
        inCircle++;
      } else {
        notInCircle++;
      }
    }

    return 4 * (inCircle / (inCircle + notInCircle));
  }

  // Calculates the probability of winning the car
  // Swap is a boolean that represents whether we are
  // choosing to swap after a goat is revealed, or not

  // Hint, each time, make an array of booleans where one is
  // randomly true (where true represents the car)
  // Assume the person always chooses door 0, and code the swap
  // based off of that
  public static double monty(int numTrials, boolean swap) {
    double won = 0;
    double lost = 0;

    for (int i = 0; i < numTrials; i++) {
      boolean[] doors = new boolean[3];
      doors[0] = true;

      for (int j = 1; j < doors.length; j++) {
        doors[j] = false;
      }

      int doorWithCar = (int) (Math.random() * 3);
      doors[doorWithCar] = true;

      int doorWithGoat = (int) (Math.random() * 3);
      while (doorWithGoat == doorWithCar) {
        doorWithGoat = (int) (Math.random() * 3);
      }

      if (swap) {
        if (doorWithCar == 0) {
          lost++;
        } else {
          won++;
        }
      } else if (doorWithCar == 0) {
        won++;
      } else {
        lost++;
      }
    }

    return won / (won + lost);
  }

  // buffons needle

  public static void main(String[] args) {
    // --------------------------
    // Test 0: Pepys Problem
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 0: Pepys Problem");
    System.out.println("Expected:");
    System.out.println("~0.67\n" + "~0.62\n" + "~0.59");

    System.out.println("\nGot:");

    System.out.println(pepysSimulation(10000, 6, 1));
    // ~0.67
    System.out.println(pepysSimulation(10000, 12, 2));
    // ~0.62
    System.out.println(pepysSimulation(10000, 18, 3));
    // ~0.59

    // --------------------------
    // Test 1: Calculate Pi
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 1: Calculate Pi");
    System.out.println("Expected:");
    System.out.println("~3.14");

    System.out.println("\nGot:");
    System.out.println("Estimate of pi is " + calculatePi(100000000));

    // --------------------------
    // Test 2: Monty Hall
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 2: Monty Hall");
    System.out.println("Expected:");
    System.out.println(
        "One of these strategies should be significantly better than the other\n"
            + "They should add up to roughly 1.0");

    System.out.println("\nGot:");
    System.out.println(monty(10000, false));
    System.out.println(monty(10000, true));

    System.out.println("-------------------");
    System.out.println("Test 3: Buffon's Needle");
    System.out.println("Expected:");
    System.out.println(Math.PI);

    //    System.out.println("\nGot:");
    //    System.out.println(buffonsNeedle(1000000));

    // One of these strategies should be significantly better than the other
    // They should add up to roughly 1.0
  }
}
