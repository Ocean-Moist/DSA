package org.hashTable;

import java.util.Spliterator;
import java.util.function.Consumer;

// shamelessly stolen from java.util.LinkedList
public class IntLinkedListSpliterator implements Spliterator<Integer> {
  private static final int BATCH_UNIT = 1024; // batch array size increment
  private static final int MAX_BATCH = 1 << 25; // max batch array size;
  final IntLinkedList list;
  IntNode current;
  int est;
  int expectedModCount;
  int batch;

  public IntLinkedListSpliterator(IntLinkedList list, int est, int expectedModCount) {
    this.list = list;
    this.est = est;
    this.expectedModCount = expectedModCount;
  }

  @Override
  public boolean tryAdvance(Consumer<? super Integer> consumer) {
    if (est <= 0) {
      return false;
    }
    if (current == null) {
      current = list.head;
    } else {
      current = current.next;
    }
    consumer.accept(current.data);
    est--;
    return true;
  }

  @Override
  public Spliterator<Integer> trySplit() {
    IntNode h;
    if (est <= 1 || (h = current) == null) {
      return null;
    }
    int n = batch + BATCH_UNIT;
    if (n > est) {
      n = est;
    }
    if (n > MAX_BATCH) {
      n = MAX_BATCH;
    }
    int[] a = new int[n];
    int j = 0;
    do {
      a[j] = h.data;
      h = h.next;
    } while (++j < n && h != null);
    current = h;
    batch = j;
    est -= j;
    return new IntArraySpliterator(a, 0, j, Spliterator.ORDERED);
  }

  @Override
  public long estimateSize() {
    return est;
  }

  @Override
  public int characteristics() {
    return Spliterator.ORDERED | Spliterator.SIZED | Spliterator.SUBSIZED;
  }

  // stolen from java.util.ArrayList
  private static class IntArraySpliterator implements Spliterator<Integer> {
    private final int[] array;
    private int index;
    private final int fence;
    private final int characteristics;

    public IntArraySpliterator(int[] a, int i, int j, int ordered) {
      array = a;
      index = i;
      fence = j;
      characteristics = ordered;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Integer> consumer) {
      if (index >= fence) {
        return false;
      }
      consumer.accept(array[index++]);
      return true;
    }

    @Override
    public Spliterator<Integer> trySplit() {
      int lo = index, mid = (lo + fence) >>> 1;
      return (lo >= mid) ? null : new IntArraySpliterator(array, lo, index = mid, characteristics);
    }

    @Override
    public long estimateSize() {
      return fence - index;
    }

    @Override
    public int characteristics() {
      return characteristics;
    }
  }
}
