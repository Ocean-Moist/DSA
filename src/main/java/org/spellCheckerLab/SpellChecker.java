package org.spellCheckerLab;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class SpellChecker {
  final StringHashTable dictionary;
  private final Trie words;

  // The "normal" version of the SpellChecker uses a Hash Table
  // of size 991547
  public SpellChecker() {
    dictionary = new StringHashTable();
    words = new Trie();
  }

  // The "simple" version of the SpellChecker utilizes a Hash Table
  // of size 26, where the Hash Function is just the first letter of
  // the String
  // You should use the "simple" version when you are debugging
  public SpellChecker(boolean isSimple) {
    dictionary = new StringHashTable(isSimple);
    words = new Trie();
  }

  // Loads the dictionary file given by the String file
  // into the dictionary
  public void load(String file) throws FileNotFoundException {
    Scanner scan = new Scanner(new java.io.File(file));
    while (scan.hasNext()) {
      String word = scan.next().replaceAll("[^a-zA-Z]", "").toLowerCase();
      dictionary.add(word);
      words.add(word);
    }
  }

  // Checks to see if the given word is present
  // in the dictionary
  // Returns true if the word is present
  // Only need one line of code!
  public boolean checkWord(String word) {
    // YOUR CODE HERE
    return dictionary.contains(word);
  }

  // Given the name of a file, looks through each word in the file
  // and prints out all mispelled words
  // Punctuation should be trimmed from the beginning and ends of each
  // word before checking the spelling
  public void check(String file) throws FileNotFoundException {
    Scanner scan = new Scanner(new java.io.File(file));
    while (scan.hasNext()) {
      String word = scan.next();
      // trim leading and trailing non alphabet characters and ignore punctuation in the middle
      word = word.replaceAll("^[^a-zA-Z]+", "").replaceAll("[^a-zA-Z]+$", "");
      if (!checkWord(word)) {
        System.out.println(word);
      }
    }
  }

  // Given some String prefix, prints out to the user possible words
  // that start with that prefix
  // The code is already provided; it will simply call the Trie method
  public void autocomplete(String prefix) {
    words.autoComplete(prefix);
  }

  // Given a String word, comes up with possible words that the
  // user might've meant
  // This method should make the following possible suggestions
  // 1) Every word you get by swapping one letter for another
  //    Ex. "thsi" -> "this"
  // 2) Every word you get by adding one letter to the String
  //    Ex. "tis" -> "this"
  // 3) Every word you get by omitting one letter
  //    Ex. "thibs" -> "this"
  // 4) Every word you get by substituting one letter for another
  //    Ex. "thia" -> "this"
  public void suggest(String word) {
    // YOUR CODE HERE
    // 1) Every word you get by swapping one letter for another
    //    Ex. "thsi" -> "this"
    for (int i = 0; i < word.length(); i++) {
      for (int j = 0; j < 26; j++) {
        char c = (char) ('a' + j);
        String newWord = word.substring(0, i) + c + word.substring(i + 1);
        if (checkWord(newWord)) {
          System.out.println(newWord);
        }
      }
    }

    // 2) Every word you get by adding one letter to the String
    //    Ex. "tis" -> "this"
    for (int i = 0; i < word.length() + 1; i++) {
      for (int j = 0; j < 26; j++) {
        char c = (char) ('a' + j);
        String newWord = word.substring(0, i) + c + word.substring(i);
        if (checkWord(newWord)) {
          System.out.println(newWord);
        }
      }
    }

    // 3) Every word you get by omitting one letter
    //    Ex. "thibs" -> "this"
    for (int i = 0; i < word.length(); i++) {
      String newWord = word.substring(0, i) + word.substring(i + 1);
      if (checkWord(newWord)) {
        System.out.println(newWord);
      }
    }

    // 4) Every word you get by substituting one letter for another
    //    Ex. "thia" -> "this"
    for (int i = 0; i < word.length(); i++) {
      for (int j = 0; j < 26; j++) {
        char c = (char) ('a' + j);
        String newWord = word.substring(0, i) + c + word.substring(i + 1);
        if (checkWord(newWord)) {
          System.out.println(newWord);
        }
      }
    }
  }

  public static int magnerFischer(String word, String s) {
    int[][] d = new int[word.length() + 1][s.length() + 1];
    for (int i = 0; i <= word.length(); i++) {
      d[i][0] = i;
    }
    for (int j = 0; j <= s.length(); j++) {
      d[0][j] = j;
    }
    for (int j = 1; j <= s.length(); j++) {
      for (int i = 1; i <= word.length(); i++) {
        if (word.charAt(i - 1) == s.charAt(j - 1)) {
          d[i][j] = d[i - 1][j - 1];
        } else {
          d[i][j] = Math.min(d[i - 1][j] + 1, Math.min(d[i][j - 1] + 1, d[i - 1][j - 1] + 1));
        }
      }
    }
    return d[word.length()][s.length()];
  }

  private String swap(String word, int i, int j) {
    char[] chars = word.toCharArray();
    char temp = chars[i];
    chars[i] = chars[j];
    chars[j] = temp;
    return new String(chars);
  }
}
