package org.stacksqueues;

public interface Stack {
  // Pushes some data value to the top of the Stack
  void push(int data);

  // Pops some data value off the top of the Stack
  int pop();

  // Returns the data value at the top of the stack without popping
  int peek();

  // Checks whether the stack is empty
  boolean isEmpty();
}
