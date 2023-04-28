package org.pig;

public class Pig {
  // Keeps rolling dice until the given target is hit or exceeded
  // * Returns the total amount roll, or 0 if busted (a 1 was rolled)
  public static int rollUntilTarget(int target) {
    // YOUR CODE HERE
    double random = Math.random();
    int roll = (int) (random * 6) + 1;
    int total = 0;
    while (roll != 1) {
      total += roll;
      if (total >= target) {
        return total;
      }
      random = Math.random();
      roll = (int) (random * 6) + 1;
    }

    return 0;
  }

  // Returns the probability of rolling and hitting the given target
  // * This method should carry out at least 10000 trials
  // * You should use rollUntilTarget() in this method
  public static double successRate(int target) {
    // YOUR CODE HERE
    int numSuccess = 0;
    for (int i = 0; i < 10000; i++) {
      if (rollUntilTarget(target) != 0) {
        numSuccess++;
      }
    }
    return ((double) numSuccess) / 10000;
  }

  // Returns the number of rounds it takes to reach at least 100 points starting from
  // 0 points and rolling for the given target for each round
  // * In each round, the player tries to roll until they hit the given target
  // You should use rollUntilTarget() in this method
  public static int numRoundsTo100(int target) {
    // YOUR CODE HERE
    int total = 0;
    int rounds = 0;
    while (total < 100) {
      total += rollUntilTarget(target);
      rounds++;
    }
    return rounds;
  }

  // Returns the average number of rounds it takes to reach at least 100 points starting
  // from 0 points and rolling for the given target for each round
  // * This method should carry out at least 10000 trials
  // * You should use numRoundsTo100() in this method
  public static double avgNumRoundsTo100(int target) {
    // YOUR CODE HERE
    int total = 0;
    for (int i = 0; i < 10000; i++) {
      total += numRoundsTo100(target);
    }
    return ((double) total) / 10000;
  }

  // Play out the rest of the match, with player 1 and player 2 starting with player1start
  // and player2start points, respectively.
  // * In each round, both players will be given an opportunity to roll. Each player will
  // aim to roll for their given target values
  // * The game ends when either player has accumulated at least 100 points after their turn
  // * The method returns the difference of player 1's score and player 2's score
  // * Note: you should use rollUntilTarget() to simulate a round. This might mean that a player
  // scores much more than 100 points, but that is okay.
  public static int playMatch(
      int player1Start, int player2Start, int player1Target, int player2Target) {
    // YOUR CODE HERE
    int player1Score = player1Start;
    int player2Score = player2Start;
    while (player1Score < 100 && player2Score < 100) {
      player1Score += rollUntilTarget(player1Target);
      player2Score += rollUntilTarget(player2Target);
    }
    return player1Score - player2Score;
  }

  // Plays out multiple matches, with the two players given their own starting and target values
  // * Returns an integer array of size 3 containing the total number of wins, losses, and ties
  public static int[] playSet(
      int player1Start, int player2Start, int player1Target, int player2Target) {
    // YOUR CODE HERE
    int[] results = new int[3];
    for (int i = 0; i < 10000; i++) {
      int result = playMatch(player1Start, player2Start, player1Target, player2Target);
      if (result > 0) {
        results[0]++;
      } else if (result < 0) {
        results[1]++;
      } else {
        results[2]++;
      }
    }
    return results;
  }

  // Given the results of a set (that is, an array of integers containing
  // the number of wins, losses, and ties, in that order), print out the results
  public static void printSet(int[] setResults) {
    System.out.println("W: " + setResults[0] + ", L:" + setResults[1] + ", T:" + setResults[2]);
  }

  public static void main(String[] args) {
    // --------------------------
    // Test 1: rollUntilTarget
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 1: rollUntilTarget");
    System.out.println("Expected:");
    System.out.println("Results may vary, but might look something like:");
    System.out.println(
        "11\n" + "10\n" + "0\n" + "0\n" + "11\n" + "13\n" + "0\n" + "10\n" + "13\n" + "0");

    System.out.println("\nGot:");

    // See the results of trying to roll for a target of 10, ten times
    for (int i = 0; i < 10; i++) {
      System.out.println(rollUntilTarget(10));
    }

    // --------------------------
    // Test 2: successRate
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 2: successRate");
    System.out.println("Expected:");
    System.out.println("Results may vary, but might look something like:");
    System.out.println(
        "1: 0.837\n"
            + "2: 0.8349\n"
            + "3: 0.812\n"
            + "4: 0.7779\n"
            + "5: 0.7416\n"
            + "6: 0.7129\n"
            + "7: 0.6685\n"
            + "8: 0.6407\n"
            + "9: 0.6168\n"
            + "10: 0.594...");

    System.out.println("\nGot:");

    // See the success rate for targets of 1-30
    for (int i = 1; i <= 30; i++) {
      System.out.println(i + ": " + successRate(i));
    }

    // --------------------------
    // Test 3: numRoundsTo100
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 3: numRoundsTo100");
    System.out.println("Expected:");
    System.out.println("Results may vary, but might look something like:");
    System.out.println("21.626\n" + "15.7131");

    System.out.println("\nGot:");

    // See the average number of rounds with a target of 5 and 10
    System.out.println(numRoundsTo100(5));
    System.out.println(numRoundsTo100(10));

    // --------------------------
    // Test 4: avgNumRoundsTo100
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 4: avgNumRoundsTo100");
    System.out.println("Expected:");
    System.out.println("Results may vary, but might look something like:");
    System.out.println(
        "1: 30.5712 rounds on average\n"
            + "2: 30.4605 rounds on average\n"
            + "3: 26.6001 rounds on average\n"
            + "4: 23.8077 rounds on average\n"
            + "5: 21.3675 rounds on average\n"
            + "6: 19.2866 rounds on average\n"
            + "7: 17.4287 rounds on average\n"
            + "8: 16.7079 rounds on average\n"
            + "9: 15.9638 rounds on average\n"
            + "10: 15.3299 rounds on average"
            + "...");

    System.out.println("\nGot:");

    // See the average number of rounds to hit 100, with targets
    // between 1 and 30.
    for (int i = 1; i <= 30; i++) {
      double x = avgNumRoundsTo100(i);
      System.out.println(i + ": " + x + " rounds on average");
    }

    // --------------------------
    // Test 5: playMatch
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 5: playMatch");
    System.out.println("Expected:");
    System.out.println("Results may vary, but might look something like:");
    System.out.println("54\n" + "87\n" + "-100");

    System.out.println("\nGot:");

    // See the results of a game where the first player should probably win
    System.out.println(playMatch(0, 0, 20, 5));
    // See the results of a game where the first player should definitely win
    System.out.println(playMatch(90, 0, 5, 5));
    // See the results of a game where the first player will definitely lose
    System.out.println(playMatch(0, 90, 5, 5));

    // --------------------------
    // Test 6: playSet
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 6: playSet");
    System.out.println("Expected:");
    System.out.println("Results may vary, but might look something like:");
    System.out.println(
        "W: 5011, L:4905, T:83\n" + "W: 2455, L:7487, T:57\n" + "W: 319, L:9663, T:17");

    System.out.println("\nGot:");
    // See the results for a set where we expect players to be evenly matched
    printSet(playSet(0, 0, 10, 10));
    // See the results for a set where player2 has a slight lead
    printSet(playSet(0, 20, 10, 10));
    // See the results for a set where player2 has a large lead
    printSet(playSet(0, 50, 10, 10));
  }
}
