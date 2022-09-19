package org.stacksqueues;

public class ArrayQueue implements Queue {
  // You will need some instance variables here
  // However, it will be up to you to come up with these instance variables
  // Look at the ArrayStack we did in class for inspiration
  private int tail;
  private final int capacity = 100;
  private Integer[] elements = new Integer[capacity];

  // Constructor
  public ArrayQueue() {
    // YOUR CODE HERE
    tail = 0;
  }

  // Enqueues a value to the end of the queuea
  @Override
  public void enqueue(int data) {
    if (tail == elements.length) {
      Integer[] newElements = new Integer[elements.length * 2];
      System.arraycopy(elements, 0, newElements, 0, elements.length);
      elements = newElements;
    }
    elements[tail] = data;
    tail++;
  }

  // Dequeues a value from the front of the queue
  @Override
  public int dequeue() {
    Integer[] newElements = new Integer[elements.length - 1];
    int front = 0;
    int result = elements[front];
    System.arraycopy(elements, 1, newElements, 0, elements.length - 1);
    elements = newElements;
    tail--;
    return result;
  }

  // Returns the data value at the top of the queue without dequeuing
  @Override
  public int peek() {
    // YOUR CODE HERE
    return elements[0];
  }

  // Checks whether or not the queue is empty
  @Override
  public boolean isEmpty() {
    // YOUR CODE HERE
    return elements[0] == null;
  }

  public static void main(String[] args) {
    // --------------------------
    // Test 1: Basic Queue Functionality
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 1: Basic Queue Functionality");
    System.out.println("Expected:");
    System.out.println("10");
    System.out.println("10");
    System.out.println("10");
    System.out.println("5");

    System.out.println("\nGot:");
    // Add 10 and 5 to queue and peek twice
    Queue queue = new ArrayQueue();
    queue.enqueue(10);
    System.out.println(queue.peek()); // 10
    queue.enqueue(5);
    System.out.println(queue.peek()); // 10

    // Remove 10 from the queue and peek again
    System.out.println(queue.dequeue()); // 10
    System.out.println(queue.peek()); // 5

    // --------------------------
    // Test 2: More Queue Testing
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 2: More Queue Testing");
    System.out.println("Expected:");
    System.out.println("10");
    System.out.println("5");
    System.out.println("2");
    System.out.println("7");

    System.out.println("\nGot:");
    // Add 10, 5, 2 and 7
    queue = new ArrayQueue();
    queue.enqueue(10);
    queue.enqueue(5);
    queue.enqueue(2);
    queue.enqueue(7);

    // Dequeue the queue completely
    while (!queue.isEmpty()) {
      System.out.println(queue.dequeue());
    }
    // 10
    // 5
    // 2
    // 7

    // --------------------------
    // Test 3: Larger Queue
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 3: Larger");
    System.out.println("Expected:");
    System.out.println("This test should print out all numbers between 0 and 199");

    System.out.println("\nGot:");
    for (int i = 0; i < 200; i++) {
      queue.enqueue(i);
    }

    while (!queue.isEmpty()) {
      System.out.println(queue.dequeue());
    }
  }
}
