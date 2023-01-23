package org.bestFirstSearch;

import java.util.*;

public class BestFirstSearchGraph {
  HashMap<String, GridVertex> vertices;

  public BestFirstSearchGraph() {
    vertices = new HashMap<>();
  }

  // Adds a vertex to the graph
  public void addVertex(String label, int x, int y) {
    // Check vertex doesn't already exist before adding it
    if (!vertices.containsKey(label)) {
      GridVertex v1 = new GridVertex(label, x, y);
      vertices.put(label, v1);
    }
  }

  // Adds an edge to the graph
  public void addEdge(String label1, String label2, int weight) {
    // Check vertices exist before adding an edge between them
    if (vertices.containsKey(label1) && vertices.containsKey(label2)) {
      GridVertex v1 = vertices.get(label1);
      GridVertex v2 = vertices.get(label2);

      v1.edges.add(new WeightedEdge(v1, v2, weight));
      v2.edges.add(new WeightedEdge(v2, v1, weight));
    }
  }

  // Removes a vertex from the graph
  public void removeVertex(String label) {
    // Check vertex exists before removing it
    if (vertices.containsKey(label)) {
      GridVertex v1 = vertices.get(label);

      // Remove all edges to this vertex
      for (WeightedEdge edge1 : v1.edges) {
        GridVertex v2 = edge1.destination;

        // Look through v2 edges for edge to this
        for (WeightedEdge edge2 : v2.edges) {
          if (edge2.destination.equals(v1)) {
            v2.edges.remove(edge2);
          }
        }
      }

      v1.edges.clear();
      vertices.remove(label);
    }
  }

  // Removes an edge from the graph
  public void removeEdge(String label1, String label2) {
    // Check vertices exist before removing an edge between them
    if (vertices.containsKey(label1) && vertices.containsKey(label2)) {
      GridVertex v1 = vertices.get(label1);
      GridVertex v2 = vertices.get(label2);

      for (WeightedEdge edge1 : v1.edges) {
        if (edge1.destination.equals(v2)) {
          v1.edges.remove(edge1);
        }
      }

      for (WeightedEdge edge2 : v2.edges) {
        if (edge2.destination.equals(v1)) {
          v2.edges.remove(edge2);
        }
      }
    }
  }

  // Manhattan Distance Heuristic
  // Given the labels corresponding to two nodes
  // calculates the Manhattan Distance between the nodes
  public int manhattanDistance(String label1, String label2) {
    GridVertex v1 = vertices.get(label1);
    GridVertex v2 = vertices.get(label2);
    return Math.abs(v1.x - v2.x) + Math.abs(v1.y - v2.y);
  }

  // Priority Queue Check
  // Given a priority queue and a label, checks to see
  // if a PQNode with that label shows is present in the queue
  public boolean queueContainsLabel(PriorityQueue<PQNode> queue, String label) {
    for (PQNode n : queue) {
      if (n.label.equals(label)) {
        return true;
      }
    }
    return false;
  }

  // Carries out bestFirstSearch from the source to the destination
  // Returns the length of the path
  // To aid in seeing what path your algorithm follows, it is
  // recommended to print out the labels of vertices you visit, as
  // well as the distances hashSet at the end
  public int bestFirstSearch(String source, String destination) {
    PriorityQueue<PQNode> frontier = new PriorityQueue<>();
    HashSet<String> explored = new HashSet<>();
    HashMap<String, Integer> distances = new HashMap<>();

    frontier.add(new PQNode(source, manhattanDistance(source, destination)));
    distances.put(source, 0);

    while (!frontier.isEmpty()) {
      PQNode node = frontier.poll();
      explored.add(node.label);

      if (node.label.equals(destination)) {
        return distances.get(node.label);
      }

      GridVertex v1 = vertices.get(node.label);

      for (WeightedEdge edge : v1.edges) {
        GridVertex v2 = edge.destination;

        if (explored.contains(v2.label)) continue;
        if (!queueContainsLabel(frontier, v2.label))
          frontier.add(new PQNode(v2.label, manhattanDistance(v2.label, destination)));
        else if (distances.get(v1.label) + edge.weight >= distances.get(v2.label)) {
          continue;
        }
        distances.put(v2.label, distances.get(v1.label) + edge.weight);
      }
    }

    return -1;
  }

  // Prints out the graph
  public void printGraph() {
    int longest = 7;
    for (String str : vertices.keySet()) {
      longest = Math.max(longest, str.length() + 1);
    }

    String line = "Vertex ";
    for (int i = 7; i < longest; i++) line += " ";
    int leftLength = line.length();
    line += "| Adjacent Vertices";
    System.out.println(line);

    for (int i = 0; i < line.length(); i++) {
      System.out.print("-");
    }
    System.out.println();

    for (String str : vertices.keySet()) {
      GridVertex v1 = vertices.get(str);

      for (int i = str.length(); i < leftLength; i++) {
        str += " ";
      }
      System.out.print(str + "| ");

      for (int i = 0; i < v1.edges.size() - 1; i++) {
        WeightedEdge edge1 = v1.edges.get(i);
        System.out.print(edge1.destination.label + ": " + edge1.weight + ", ");
      }

      if (!v1.edges.isEmpty()) {
        WeightedEdge edge1 = v1.edges.get(v1.edges.size() - 1);
        System.out.print(edge1.destination.label + ": " + edge1.weight);
      }

      System.out.println();
    }
  }

  public static void main(String[] args) {
    // --------------------------
    // Test 1: Best First Search
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 1: Best First Search");
    System.out.println("Expected:");
    // Expected order of node visits
    System.out.println("A\n" + "F\n" + "G\n" + "K\n" + "J\n" + "O\n" + "P\n" + "L");
    // Expected final distances hashmap
    System.out.println(
        "{A=0, B=1, C=5, E=1, F=4, G=6, H=9, I=19, J=10, K=12, L=16, N=11, O=13, P=14}");

    // Expected final distance:
    System.out.println("17");

    System.out.println("\nGot:");
    BestFirstSearchGraph g3 = new BestFirstSearchGraph();
    g3.addVertex("A", 0, 0);
    g3.addVertex("B", 1, 0);
    g3.addVertex("C", 2, 0);
    g3.addVertex("D", 5, 0);
    g3.addVertex("E", 0, 1);
    g3.addVertex("F", 2, 1);
    g3.addVertex("G", 2, 2);
    g3.addVertex("H", 4, 2);
    g3.addVertex("I", 5, 2);
    g3.addVertex("J", 2, 5);
    g3.addVertex("K", 4, 5);
    g3.addVertex("L", 5, 5);
    g3.addVertex("M", 0, 6);
    g3.addVertex("N", 2, 6);
    g3.addVertex("O", 3, 6);
    g3.addVertex("P", 4, 6);
    g3.addVertex("Q", 5, 6);

    g3.addEdge("A", "B", 1);
    g3.addEdge("A", "E", 1);
    g3.addEdge("A", "F", 4);
    g3.addEdge("B", "C", 1);
    g3.addEdge("C", "D", 3);
    g3.addEdge("C", "F", 1);
    g3.addEdge("D", "I", 2);
    g3.addEdge("E", "M", 5);
    g3.addEdge("F", "G", 2);
    g3.addEdge("G", "H", 3);
    g3.addEdge("G", "J", 4);
    g3.addEdge("G", "K", 6);
    g3.addEdge("H", "I", 2);
    g3.addEdge("H", "K", 3);
    g3.addEdge("I", "L", 3);
    g3.addEdge("J", "K", 3);
    g3.addEdge("J", "N", 1);
    g3.addEdge("J", "O", 3);
    g3.addEdge("L", "P", 2);
    g3.addEdge("L", "Q", 1);
    g3.addEdge("M", "N", 2);
    g3.addEdge("N", "O", 1);
    g3.addEdge("O", "P", 1);

    System.out.println(g3.bestFirstSearch("A", "Q"));
  }

  class GridVertex {
    final String label;
    final LinkedList<WeightedEdge> edges;
    final int x;
    final int y;

    public GridVertex(String label, int x, int y) {
      this.label = label;
      this.edges = new LinkedList<>();
      this.x = x;
      this.y = y;
    }
  }

  class WeightedEdge implements Comparable<WeightedEdge> {
    final GridVertex source;
    final GridVertex destination;
    final int weight;

    public WeightedEdge(GridVertex source, GridVertex destination, int weight) {
      this.source = source;
      this.destination = destination;
      this.weight = weight;
    }

    public boolean equals(Object other) {
      if (other instanceof WeightedEdge otherEdge) {
        return this.source.equals(otherEdge.source)
                && this.destination.equals(otherEdge.destination)
            || this.source.equals(otherEdge.destination)
                && this.destination.equals(otherEdge.source);
      }
      return false;
    }

    @Override
    public int hashCode() {
      return Objects.hash(source, destination);
    }

    @Override
    public String toString() {
      return source.label + "->" + destination.label;
    }

    @Override
    public int compareTo(WeightedEdge o) {
      return this.weight - o.weight;
    }
  }

  class PQNode implements Comparable<PQNode> {
    final String label;
    final int weight;

    public PQNode(String label, int weight) {
      this.label = label;
      this.weight = weight;
    }

    @Override
    public int compareTo(PQNode o) {
      return this.weight - o.weight;
    }
  }
}
