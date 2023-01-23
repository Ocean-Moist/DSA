package org.dijkstra;

public class Pair<T, Integer> implements Comparable<Pair<T, Integer>> {
  public T first;
  public int second;

  public Pair(T first, int second) {
    this.first = first;
    this.second = second;
  }

  @Override
  public int compareTo(Pair<T, Integer> tt1Pair) {
    return this.second - tt1Pair.second;
  }
}
