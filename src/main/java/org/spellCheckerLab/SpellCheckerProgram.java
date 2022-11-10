package org.spellCheckerLab;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Scanner;

public class SpellCheckerProgram {
  // This runs the interactive program that users
  // can use to run our code
  public static void main(String[] args) throws IOException {
    // Get stuff set up
    SpellChecker speller = new SpellChecker();
    speller.load("src/main/java/org/spellCheckerLab/dictionaries/words.txt");
    Scanner scan = new Scanner(System.in);
    HashSet<String> commands = new HashSet<String>();
    commands.add("spellcheck");
    commands.add("autocomplete");
    commands.add("exit");
    commands.add("suggest");
    while (true) { // Keep repeating until the user wants to exit
      String command = "";
      // Determine which functionality the user wants to carry out
      System.out.println(
          "What functionality would you like to run? (spellcheck/autocomplete/suggest/exit)");
      command = scan.nextLine();
      String finalCommand = command;
      Optional<String> commandOpt =
          commands.stream()
              .filter(c -> SpellChecker.magnerFischer(finalCommand, c) <= 2)
              .findFirst();
      if (commandOpt.isPresent()) {
        switch (commandOpt.get()) {
          case "spellcheck" -> {
            System.out.println("What file would you like to spellcheck?");
            String s = scan.nextLine();
            speller.check(s);
          }
          case "autocomplete" -> {
            System.out.println("What would you like to autocomplete?");
            String a = scan.nextLine();
            speller.autocomplete(a);
          }
          case "suggest" -> {
            System.out.println("What would you like to suggest?");
            String suggest = scan.nextLine();
            speller.suggest(suggest);
          }
          case "exit" -> System.exit(0);
        }
        // REST OF YOUR CODE HERE
      }
    }
  }
}
