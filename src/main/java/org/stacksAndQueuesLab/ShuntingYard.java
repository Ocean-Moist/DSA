package org.stacksAndQueuesLab;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class ShuntingYard {
  // Returns true if the input String is "+", "-", "*", "/", or "^"; returns false otherwise
  // You can use isOperator to test if a String is an operator or not
  // e.g. isOperator("+") => true
  //      isOperator("5") => false
  public static boolean isOperator(String op) {
    return op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/") || op.equals("^");
  }

  // Returns true if the input String is an integer; returns false otherwise
  // You can use isNumeric to test if a String is a number or not
  // e.g. isNumeric("+") => false
  //      isNumeric("5") => true
  public static boolean isNumeric(String str) {
    try {
      Integer.parseInt(str);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  // Returns the precedence level of the operator; a higher int
  // represents a higher precedence level
  //
  // Precedence rankings:
  // 0: left paren
  // 1: add/sub
  // 2: multiply/divide
  // 3: exponent
  public static int getPrecedence(String str) {
    return switch (str) {
      case "(" -> 0;
      case "+", "-" -> 1;
      case "*", "/" -> 2;
      default -> // if (str.equals("^")) {
      3;
    };
  }

  // Returns true if the precedence of the *top* argument is
  // greater than or equal to the precedence of the *op* argument
  public static boolean precedenceCompare(String op, String top) {
    return getPrecedence(top) >= getPrecedence(op);
  }

  // Takes in a string containing a math expression in infix format and outputs a
  // queue containing the tokens in postfix format
  // To be completed in **Part 1**
  public static Queue<String> convert(String infix) {
    // We need to create two data structures: a queue for our output, and a stack for our operators
    Queue<String> outputQueue = new ArrayDeque<>();
    Deque<String> opStack = new ArrayDeque<>();

    String[] arr = infix.split(" ");
    for (String s : arr) {
      if (isNumeric(s)) {
        outputQueue.add(s);
      } else if (isOperator(s)) {
        while (!opStack.isEmpty() && precedenceCompare(s, opStack.peek())) {
          outputQueue.add(opStack.pop());
        }
        opStack.push(s);
      } else if (s.equals("(")) {
        opStack.push(s);
      } else if (s.equals(")")) {
        while (!opStack.peek().equals("(")) {
          outputQueue.add(opStack.pop());
        }
        opStack.pop();
      }
    }
    while (!opStack.isEmpty()) {
      outputQueue.add(opStack.pop());
    }

    return outputQueue;
  }

  // Takes in a queue of tokens in postfix format, and evaluates the math expression
  // Returns the integer answer
  // To be completed in **Part 2**
  public static int evaluate(Queue<String> inputQueue) {
    Deque<Integer> stack = new ArrayDeque<>();

    while (!inputQueue.isEmpty()) {
      String s = inputQueue.remove();
      if (isNumeric(s)) {
        stack.push(Integer.parseInt(s));
      } else if (isOperator(s)) {
        int a = stack.pop();
        int b = stack.pop();
        int c =
            switch (s) {
              case "+" -> b + a;
              case "-" -> b - a;
              case "*" -> b * a;
              case "/" -> b / a;
              default -> (int) Math.pow(b, a);
            };
        stack.push(c);
      }
    }

    return stack.pop();
  }

  public static void main(String[] args) {
    // --------------------------
    // Test 1: Case 1 and Case 2 (No Parentheses)
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 1: SYA Case 1 and Case 2 (No Parentheses)");
    System.out.println("Expected:");
    System.out.println("[3, 4, 2, *, +, 1, -]");
    System.out.println("[3, 4, 2, *, +, 1, 5, *, -, 6, 2, /, +, 3, +]");
    System.out.println("[1, 2, 3, *, +, 10, 5, /, -, 2, +]");
    System.out.println("[1, 2, 3, 2, ^, *, +]");

    System.out.println("\nGot:");
    // Example 1: 3 + 4 * 2 - 1
    // Postfix: [3, 4, 2, *, +, 1, -]
    System.out.println(convert("3 + 4 * 2 - 1"));

    // Example 2: 3 + 4 * 2 - 1 * 5 + 6 / 2 + 3
    // Postfix: [3, 4, 2, *, +, 1, 5, *, -, 6, 2, /, +, 3, +]
    System.out.println(convert("3 + 4 * 2 - 1 * 5 + 6 / 2 + 3"));

    // Example 3: 1 + 2 * 3 - 10 / 5 + 2
    // Postfix: [1, 2, 3, *, +, 10, 5, /, -, 2, +]
    System.out.println(convert("1 + 2 * 3 - 10 / 5 + 2"));

    // Example 4: 1 + 2 * 3 ^ 2
    // Postfix: [1, 2, 3, 2, ^, *, +]
    System.out.println(convert("1 + 2 * 3 ^ 2"));

    // --------------------------
    // Test 2: All Cases (With Parentheses)
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 2: SYA All Cases (With Parentheses)");
    System.out.println("Expected:");
    System.out.println("[5, 2, 1, +, *, 3, /, 6, 5, -, 3, *, +]");
    System.out.println("[2, 3, ^, 2, 4, *, /, 10, +]");

    System.out.println("\nGot:");
    // Example 5: 5 * ( 2 + 1 )  / 3 + ( 6 - 5 ) * 3
    // Postfix: [5, 2, 1, +, *, 3, /, 6, 5, -, 3, *, +]
    System.out.println(convert("5 * ( 2 + 1 ) / 3 + ( 6 - 5 ) * 3"));

    // Example 6: 2 ^ 3 / (2 * 4) + 10
    // Postfix: [2, 3, ^, 2, 4, *, /, 10, +]
    System.out.println(convert("2 ^ 3 / ( 2 * 4 ) + 10"));

    // --------------------------
    // Test 3: Evaluates Correctly + - / *
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 3: Evaluates Correctly + - / *");
    System.out.println("Expected:");
    System.out.println("10");
    System.out.println("12");
    System.out.println("7");

    System.out.println("\nGot:");
    // Example 1: 3 + 4 * 2 - 1
    // Postfix: [3, 4, 2, *, +, 1, -]
    // Result: 10
    System.out.println(evaluate(convert("3 + 4 * 2 - 1")));

    // Example 2: 3 + 4 * 2 - 1 * 5 + 6 / 2 + 3
    // Postfix: [3, 4, 2, *, +, 1, 5, *, -, 6, 2, /, +, 3, +]
    // Result: 12
    System.out.println(evaluate(convert("3 + 4 * 2 - 1 * 5 + 6 / 2 + 3")));

    // Example 3: 1 + 2 * 3 - 10 / 5 + 2
    // Postfix: [1, 2, 3, *, +, 10, 5, /, -, 2, +]
    // Result: 7
    System.out.println(evaluate(convert("1 + 2 * 3 - 10 / 5 + 2")));

    // --------------------------
    // Test 4: Evaluates Everything Correctly
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 4: Evaluates Everything Correctly");
    System.out.println("Expected:");
    System.out.println("19");
    System.out.println("8");
    System.out.println("11");

    System.out.println("\nGot:");
    // Example 4: 1 + 2 * 3 ^ 2
    // Postfix: [1, 2, 3, 2, ^, *, +]
    // Result: 19
    System.out.println(evaluate(convert("1 + 2 * 3 ^ 2")));

    // Example 5: 5 * ( 2 + 1 )  / 3 + ( 6 - 5 ) * 3
    // Postfix: [5, 2, 1, +, *, 3, /, 6, 5, -, 3, *, +]
    // Result: 8
    System.out.println(evaluate(convert("5 * ( 2 + 1 ) / 3 + ( 6 - 5 ) * 3")));

    // Example 6: 2 ^ 3 / (2 * 4) + 10
    // Postfix: [2, 3, ^, 2, 4, *, /, 10, +]
    // Result: 11
    System.out.println(evaluate(convert("2 ^ 3 / ( 2 * 4 ) + 10")));
  }
}
