package org.randomizedAlgorithims;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Random;

public class MinimumCut {
  // Carries out Karger's Algorithm
  // Call the provided contractionAlgorithm method 100 times
  // returning the best result.
  public static int karger(WeighedGraph g) {
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < 100; i++) {
      int result = contractionAlgorithm(g);
      if (result < min) {
        min = result;
      }
    }
    return min;
  }

  // Returns the value of a cut
  // This method will work once your edgeContraction method works
  // No edits to this code are necessary
  public static int contractionAlgorithm(WeighedGraph g) {
    WeighedGraph g2 = g.duplicate();

    // As long as there are more than 2 nodes, choose two random nodes and combine them
    while (g2.getSize() > 2) {
      // Choose two random vertices, such that they aren't the same vertex
      Random r = new Random();
      String label1 = (String) g2.vertices.keySet().toArray()[r.nextInt(g2.getSize())];
      String label2 = (String) g2.vertices.keySet().toArray()[r.nextInt(g2.getSize())];
      while (label1.equals(label2)) {
        label2 = (String) g2.vertices.keySet().toArray()[r.nextInt(g2.getSize())];
      }

      // Combine them using the edgeContraction method
      g2.edgeContraction(label1, label2);
    }

    // When there are only two nodes left, return the value of the final edge
    return (g2.vertices.get((String) g2.vertices.keySet().toArray()[0])).edges.get(0).weight;
  }

  public static void main(String[] args) {
    // --------------------------
    // Test 1: Edge Contraction
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 1: Edge Contraction");
    System.out.println("Expected:");
    System.out.println(
        "Before contraction: \n"
            + "Vertex | Adjacent Vertices\n"
            + "--------------------------\n"
            + "A      | B: 14, C: 2\n"
            + "B      | A: 14, C: 5, E: 15\n"
            + "C      | A: 2, B: 5, D: 6\n"
            + "D      | C: 6, E: 11\n"
            + "E      | B: 15, D: 11\n"
            + "\n"
            + "After contraction: \n"
            + "Vertex | Adjacent Vertices\n"
            + "--------------------------\n"
            + "A      | BC: 16\n"
            + "BC     | A: 16, E: 15, D: 6\n"
            + "D      | E: 11, BC: 6\n"
            + "E      | D: 11, BC: 15");

    System.out.println("\nGot:");

    WeighedGraph graph = new WeighedGraph();
    graph.addVertex("A");
    graph.addVertex("B");
    graph.addVertex("C");
    graph.addVertex("D");
    graph.addVertex("E");
    graph.addEdge("A", "B", 14);
    graph.addEdge("A", "C", 2);
    graph.addEdge("B", "C", 5);
    graph.addEdge("B", "E", 15);
    graph.addEdge("C", "D", 6);
    graph.addEdge("D", "E", 11);
    System.out.println("Before contraction: ");
    graph.printGraph();
    // Vertex | Adjacent Vertices
    // --------------------------
    // A      | B: 14, C: 2
    // B      | A: 14, C: 5, E: 15
    // C      | A: 2, B: 5, D: 6
    // D      | C: 6, E: 11
    // E      | B: 15, D: 11

    WeighedGraph graph2 = graph.duplicate();

    graph.edgeContraction("B", "C");
    System.out.println();
    System.out.println("After contraction: ");
    graph.printGraph();
    // Vertex | Adjacent Vertices
    // --------------------------
    // A      | BC: 16
    // BC     | A: 16, E: 15, D: 6
    // D      | E: 11, BC: 6
    // E      | D: 11, BC: 15

    // --------------------------
    // Test 2: Karger's Algorithm
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 2: Karger's Algorithm");
    System.out.println("Expected:");
    System.out.println("13");

    System.out.println("\nGot:");

    // Test 2: Karger's Algorithm
    System.out.println(karger(graph2));
    // 13
  }
}

class WeighedGraph {
  HashMap<String, WeighedVertex> vertices;

  public WeighedGraph() {
    vertices = new HashMap<>();
  }

  // Carry out the edgeContraction operation
  // Creates a new node, combining the two given nodes, such that:
  // * The new node has a name that combines the two previous nodes (use addVertex)
  // * All edges that don’t involve our chosen vertices remain unchanged
  // * Any edges between our two vertices disappear (use removeEdge)
  // * Any edges from other vertices to any of our chosen two vertices are combined into a new edge
  //   (loop through the vertices' edges, and use addEdge)
  // * The original two nodes are deleted entirely (use removeVertex)
  public void edgeContraction(String label1, String label2) {
    WeighedVertex v1 = vertices.get(label1);
    WeighedVertex v2 = vertices.get(label2);

    // Create a new vertex to represent the contracted nodes
    String newLabel = label1 + label2;
    addVertex(newLabel);
    WeighedVertex vnew = vertices.get(newLabel);

    // Add all edges from v1 to vnew
    for (WeighedEdge e : v1.edges) {
      if (!e.destination.label.equals(label2)) {
        addEdge(newLabel, e.destination.label, e.weight);
      }
    }

    // Add all edges from v2 to vnew

  }

  // Gets the number of remaining vertices
  public int getSize() {
    return this.vertices.size();
  }

  public WeighedGraph duplicate() {
    WeighedGraph duplicate = new WeighedGraph();
    for (String label : vertices.keySet()) {
      duplicate.addVertex(label);
    }

    for (String label : vertices.keySet()) {
      WeighedVertex v = vertices.get(label);

      for (WeighedEdge e : v.edges) {
        duplicate.addEdge(e.source.label, e.destination.label, e.weight);
      }
    }

    return duplicate;
  }

  public void addVertex(String label) {
    // Check vertex doesn't already exist before adding it
    if (!vertices.containsKey(label)) {
      WeighedVertex v1 = new WeighedVertex(label);
      vertices.put(label, v1);
    }
  }

  public void addEdge(String label1, String label2, int weight) {
    // Check vertices exist before adding an edge between them
    // and that the edge does not already exist
    if (vertices.containsKey(label1)
        && vertices.containsKey(label2)
        && getEdge(label1, label2) == null) {
      WeighedVertex v1 = vertices.get(label1);
      WeighedVertex v2 = vertices.get(label2);

      v1.edges.add(new WeighedEdge(v1, v2, weight));
      v2.edges.add(new WeighedEdge(v2, v1, weight));
    }
  }

  public void removeVertex(String label) {
    // Check vertex exists before removing it
    if (vertices.containsKey(label)) {
      WeighedVertex v1 = vertices.get(label);

      // Remove all edges to this vertex
      for (WeighedEdge edge1 : v1.edges) {
        WeighedVertex v2 = edge1.destination;

        // Look through v2 edges for edge to this
        v2.edges.removeIf(edge2 -> edge2.destination.equals(v1));
      }

      v1.edges.clear();
      vertices.remove(label);
    }
  }

  public void removeEdge(String label1, String label2) {
    // Check vertices exist before removing an edge between them
    if (vertices.containsKey(label1) && vertices.containsKey(label2)) {
      WeighedVertex v1 = vertices.get(label1);
      WeighedVertex v2 = vertices.get(label2);

      v1.edges.removeIf(edge1 -> edge1.destination.equals(v2));
      v2.edges.removeIf(edge2 -> edge2.destination.equals(v1));
    }
  }

  public WeighedEdge getEdge(String label1, String label2) {
    WeighedVertex v1 = vertices.get(label1);
    WeighedVertex v2 = vertices.get(label2);

    for (WeighedEdge edge1 : v1.edges) {
      if (edge1.destination.equals(v2)) {
        return edge1;
      }
    }

    return null;
  }

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
      WeighedVertex v1 = vertices.get(str);

      for (int i = str.length(); i < leftLength; i++) {
        str += " ";
      }
      System.out.print(str + "| ");

      for (int i = 0; i < v1.edges.size() - 1; i++) {
        WeighedEdge edge1 = v1.edges.get(i);
        System.out.print(edge1.destination.label + ": " + edge1.weight + ", ");
      }

      if (!v1.edges.isEmpty()) {
        WeighedEdge edge1 = v1.edges.get(v1.edges.size() - 1);
        System.out.print(edge1.destination.label + ": " + edge1.weight);
      }

      System.out.println();
    }
  }
}

class WeighedVertex {
  String label;
  LinkedList<WeighedEdge> edges;

  public WeighedVertex(String label) {
    this.label = label;
    this.edges = new LinkedList<>();
  }
}

class WeighedEdge implements Comparable<WeighedEdge> {
  WeighedVertex source;
  WeighedVertex destination;
  int weight;

  public WeighedEdge(WeighedVertex source, WeighedVertex destination, int weight) {
    this.source = source;
    this.destination = destination;
    this.weight = weight;
  }

  public boolean equals(Object other) {
    if (other instanceof WeighedEdge) {
      WeighedEdge otherEdge = (WeighedEdge) other;
      return this.source.equals(otherEdge.source) && this.destination.equals(otherEdge.destination)
          || this.source.equals(otherEdge.destination) && this.destination.equals(otherEdge.source);
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
  public int compareTo(WeighedEdge o) {
    return this.weight - o.weight;
  }
}
