package org.stacksqueues;

public class ArrayStack implements Stack {
  private int[] stack;
  private int head;
  private int capacity;

  // Our default ArrayStack will start with a size of 100
  public ArrayStack() {
    this.capacity = 100;
    this.stack = new int[capacity];
    this.head = -1;
  }

  // Pushes some data value to the top of the Stack
  public void push(int data) {
    head++;

    // Check to see if we need to add more space in our array
    if (head == capacity) {
      capacity *= 2;
      int[] newArr = new int[capacity];
      System.arraycopy(stack, 0, newArr, 0, stack.length);
      stack = newArr;
    }

    stack[head] = data;
  }

  // Pops some data value off the top of the Stack
  public int pop() {
    head--;
    return stack[head + 1];
  }

  // Returns the data value at the top of the stack without popping
  public int peek() {
    if (head == -1) {
      return -1;
    } else {
      return stack[head];
    }
  }

  // Checks whether or not the stack is empty
  public boolean isEmpty() {
    return (head != -1);
  }

  public static void main(String[] args) {
    Stack stack = new ArrayStack();
    stack.push(10);
    stack.push(5);
    stack.push(2);
    stack.push(7);

    System.out.println(stack.peek()); // 7

    while (stack.isEmpty()) {
      System.out.println(stack.pop());
    }
    // 7
    // 2
    // 5
    // 10

    for (int i = 0; i < 150; i++) {
      stack.push(i);
    }

    while (stack.isEmpty()) {
      System.out.println(stack.pop());
    }
  }
}
