package org.stacksqueues;

public interface Queue {
  // Enqueues a value to the end of the queue
  void enqueue(int data);

  // Dequeues a value from the front of the queue
  int dequeue();

  // Returns the data value at the front of the queue without dequeuing
  int peek();

  // Checks whether the queue is empty
  boolean isEmpty();
}
