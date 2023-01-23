package org.dijkstra;

import java.util.*;

public class DijkstraGraph {
  HashMap<String, WeightedVertex> vertices;

  public DijkstraGraph() {
    vertices = new HashMap<>();
  }

  // Adds a vertex to the graph
  public void addVertex(String label) {
    // Check vertex doesn't already exist before adding it
    if (!vertices.containsKey(label)) {
      WeightedVertex v1 = new WeightedVertex(label);
      vertices.put(label, v1);
    }
  }

  // Adds an edge to the graph
  public void addEdge(String label1, String label2, int weight) {
    // Check vertices exist before adding an edge between them
    if (vertices.containsKey(label1) && vertices.containsKey(label2)) {
      WeightedVertex v1 = vertices.get(label1);
      WeightedVertex v2 = vertices.get(label2);

      v1.edges.add(new DijkstraEdge(v1, v2, weight));
      v2.edges.add(new DijkstraEdge(v2, v1, weight));
    }
  }

  // Removes a vertex from the graph
  public void removeVertex(String label) {
    // Check vertex exists before removing it
    if (vertices.containsKey(label)) {
      WeightedVertex v1 = vertices.get(label);

      // Remove all edges to this vertex
      for (DijkstraEdge edge1 : v1.edges) {
        WeightedVertex v2 = edge1.destination;

        // Look through v2 edges for edge to this
        v2.edges.removeIf(edge2 -> edge2.destination.equals(v1));
      }

      v1.edges.clear();
      vertices.remove(label);
    }
  }

  // Removes an edge from the graph
  public void removeEdge(String label1, String label2) {
    // Check vertices exist before removing an edge between them
    if (vertices.containsKey(label1) && vertices.containsKey(label2)) {
      WeightedVertex v1 = vertices.get(label1);
      WeightedVertex v2 = vertices.get(label2);

      v1.edges.removeIf(edge1 -> edge1.destination.equals(v2));

      v2.edges.removeIf(edge2 -> edge2.destination.equals(v1));
    }
  }

  // This method carries out Dijkstra's algorithm
  // The algorithm returns a HashMap for the distances to each node
  public HashMap<String, Integer> dijkstra(String source) {
    if (!vertices.containsKey(source)) {
      return null;
    }

    HashMap<String, Integer> distances = new HashMap<>();
    HashMap<String, String> previous = new HashMap<>();
    HashSet<String> visited = new HashSet<>();
    PriorityQueue<Pair<WeightedVertex, Integer>> unvisited = new PriorityQueue<>();

    distances.put(source, 0);
    unvisited.offer(new Pair<>(vertices.get(source), 0));

    while (!unvisited.isEmpty()) {
      WeightedVertex current = unvisited.poll().first;
      visited.add(current.label);

      for (DijkstraEdge edge : current.edges) {
        WeightedVertex destination = edge.destination;

        if (visited.contains(destination.label)) {
          continue;
        }
        int distance = distances.get(current.label) + edge.weight;

        if (!distances.containsKey(destination.label)
            || distance < distances.get(destination.label)) {
          distances.put(destination.label, distance);
          previous.put(destination.label, current.label);
          unvisited.add(new Pair<>(destination, distance));
        }
      }
    }

    return distances;
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
      WeightedVertex v1 = vertices.get(str);

      for (int i = str.length(); i < leftLength; i++) {
        str += " ";
      }
      System.out.print(str + "| ");

      for (int i = 0; i < v1.edges.size() - 1; i++) {
        DijkstraEdge edge1 = v1.edges.get(i);
        System.out.print(edge1.destination.label + ": " + edge1.weight + ", ");
      }

      if (!v1.edges.isEmpty()) {
        DijkstraEdge edge1 = v1.edges.get(v1.edges.size() - 1);
        System.out.print(edge1.destination.label + ": " + edge1.weight);
      }

      System.out.println();
    }
  }

  public static void main(String[] args) {
    // --------------------------
    // Test 1: Dijkstra's Algorithm
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 1: Dijkstra's Algorithm");
    System.out.println("Expected:");
    System.out.println("{A=0, B=3, C=4, D=6, E=8, F=7, G=11, H=13}");

    System.out.println("\nGot:");

    DijkstraGraph g1 = new DijkstraGraph();
    g1.addVertex("A");
    g1.addVertex("B");
    g1.addVertex("C");
    g1.addVertex("D");
    g1.addVertex("E");
    g1.addVertex("F");
    g1.addVertex("G");
    g1.addVertex("H");

    g1.addEdge("A", "B", 3);
    g1.addEdge("A", "C", 5);
    g1.addEdge("A", "D", 8);
    g1.addEdge("B", "C", 1);
    g1.addEdge("B", "F", 4);
    g1.addEdge("C", "D", 2);
    g1.addEdge("C", "F", 6);
    g1.addEdge("D", "E", 3);
    g1.addEdge("D", "G", 6);
    g1.addEdge("E", "F", 1);
    g1.addEdge("E", "G", 3);
    g1.addEdge("E", "H", 6);
    g1.addEdge("F", "H", 11);
    g1.addEdge("G", "H", 2);

    //        g1.printGraph();
    //        // Vertex | Adjacent Vertices
    //        // --------------------------
    //        // A      | B: 3, C: 5, D: 8
    //        // B      | A: 3, C: 1, F: 4
    //        // C      | A: 5, B: 1, D: 2, F: 6
    //        // D      | A: 8, C: 2, E: 3, G: 6
    //        // E      | D: 3, F: 1, G: 3, H: 6
    //        // F      | B: 4, C: 6, E: 1, H: 11
    //        // G      | D: 6, E: 3, H: 2
    //        // H      | E: 6, F: 11, G: 2

    // TEST 1: DIJKSTRA'S ALGORITHM
    HashMap<String, Integer> distances = g1.dijkstra("A");
    System.out.println(distances);
    // {A=0, B=3, C=4, D=6, E=8, F=7, G=11, H=13}
  }

  class WeightedVertex {
    String label;
    LinkedList<DijkstraEdge> edges;

    public WeightedVertex(String label) {
      this.label = label;
      this.edges = new LinkedList<>();
    }
  }

  class DijkstraEdge implements Comparable<DijkstraEdge> {
    WeightedVertex source;
    WeightedVertex destination;
    int weight;

    public DijkstraEdge(WeightedVertex source, WeightedVertex destination, int weight) {
      this.source = source;
      this.destination = destination;
      this.weight = weight;
    }

    public boolean equals(Object other) {
      if (other instanceof DijkstraEdge) {
        DijkstraEdge otherEdge = (DijkstraEdge) other;
        return this.source.equals(otherEdge.source)
                && this.destination.equals(otherEdge.destination)
            || this.source.equals(otherEdge.destination)
                && this.destination.equals(otherEdge.source);
      }
      return false;
    }

    public int hashCode() {
      return this.source.hashCode() + this.destination.hashCode();
    }

    @Override
    public String toString() {
      return source.label + "->" + destination.label;
    }

    @Override
    public int compareTo(DijkstraEdge o) {
      return this.weight - o.weight;
    }
  }
}
