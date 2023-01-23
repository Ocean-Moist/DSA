package org.graph;

import java.util.*;

public class Graph {
  HashMap<String, Vertex> vertices;

  public Graph() {
    this.vertices = new HashMap<>();
  }

  // Creates a new vertex with the given label
  // and adds it to the graph
  public void addVertex(String label) {
    vertices.put(label, new Vertex(label));
  }

  // Adds an edge between the vertices with the given
  // labels to the graph
  public void addEdge(String label1, String label2) {
    Vertex v1 = vertices.get(label1);
    Vertex v2 = vertices.get(label2);
    v1.neighbors.add(v2);
    v2.neighbors.add(v1);
  }

  // Checks to see if there is an edges between the
  // vertices with the given labels
  public boolean hasEdge(String label1, String label2) {
    Vertex v1 = vertices.get(label1);
    Vertex v2 = vertices.get(label2);
    return v1.neighbors.contains(v2);
  }

  // Removes the given vertex and all of its edges from
  // the graph
  public void removeVertex(String label) {
    // O(n)
    Vertex v = vertices.get(label);
    for (Vertex neighbor : v.neighbors) {
      neighbor.neighbors.remove(v);
    }
  }

  // Removes the edge between the given vertices
  public void removeEdge(String label1, String label2) {
    Vertex v1 = vertices.get(label1);
    Vertex v2 = vertices.get(label2);
    v1.neighbors.remove(v2);
    v2.neighbors.remove(v1);
  }

  private boolean depthFirstSearch(String s, String t) {
    Vertex start = vertices.get(s);
    Vertex end = vertices.get(t);
    HashSet<Vertex> visited = new HashSet<>();
    return depthFirstSearchTR(start, end, visited);
  }

  private boolean depthFirstSearchTR(Vertex start, Vertex end, HashSet<Vertex> visited) {
    if (visited.contains(start)) {
      return false;
    }
    visited.add(start);
    if (start == end) {
      return true;
    }
    for (Vertex neighbor : start.neighbors) {
      if (depthFirstSearchTR(neighbor, end, visited)) {
        return true;
      }
    }
    return false;
  }

  public boolean breadthFirstSearch(String s, String t) {
    // create hashset of discovered nodes, add s to hashset
    HashSet<String> discovered = new HashSet<>();
    discovered.add(s);
    // create queue of vertices, add s to queue
    Deque<Vertex> queue = new ArrayDeque<>();
    queue.add(vertices.get(s));

    // while queue is not empty
    while (!queue.isEmpty()) {
      // dequeue vertex
      Vertex v = queue.remove();
      // if vertex is t, return true
      if (v.label.equals(t)) {
        return true;
      }
      // for each neighbor of vertex
      for (Vertex neighbor : v.neighbors) {
        // if neighbor is not in discovered
        if (!discovered.contains(neighbor.label)) {
          // add neighbor to discovered
          discovered.add(neighbor.label);
          // enqueue neighbor
          queue.add(neighbor);
        }
      }
    }
    // return false
    return false;
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
      Vertex v1 = vertices.get(str);

      for (int i = str.length(); i < leftLength; i++) {
        str += " ";
      }
      System.out.print(str + "| ");

      for (int i = 0; i < v1.neighbors.size() - 1; i++) {
        System.out.print(v1.neighbors.get(i).label + ", ");
      }

      if (!v1.neighbors.isEmpty()) {
        System.out.print(v1.neighbors.get(v1.neighbors.size() - 1).label);
      }

      System.out.println();
    }
  }

  public static void main(String[] args) {
    // --------------------------
    // Test 1: Add Vertices
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 1: Add Vertices");
    System.out.println("Expected:");
    System.out.println(
        "Vertex        | Adjacent Vertices\n"
            + "---------------------------------\n"
            + "San Francisco | \n"
            + "New York      | \n"
            + "Chicago       | \n"
            + "London        | ");

    System.out.println("\nGot:");

    Graph graph = new Graph();

    graph.addVertex("London");
    graph.addVertex("New York");
    graph.addVertex("San Francisco");
    graph.addVertex("Chicago");
    graph.printGraph();
    System.out.println();

    // --------------------------
    // Test 2: Add Edges
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 2: Add Edges");
    System.out.println("Expected:");
    System.out.println(
        "Vertex        | Adjacent Vertices\n"
            + "---------------------------------\n"
            + "San Francisco | New York, Chicago\n"
            + "New York      | London, Chicago, San Francisco\n"
            + "Chicago       | New York, San Francisco\n"
            + "London        | New York");

    System.out.println("\nGot:");

    graph.addEdge("London", "New York");
    graph.addEdge("New York", "Chicago");
    graph.addEdge("San Francisco", "New York");
    graph.addEdge("San Francisco", "Chicago");
    graph.printGraph();
    System.out.println();

    // --------------------------
    // Test 3: Has Edges
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 3: Has Edges");
    System.out.println("Expected:");
    System.out.println("true");
    System.out.println("true");
    System.out.println("false");

    System.out.println("\nGot:");

    System.out.println(graph.hasEdge("London", "New York"));
    System.out.println(graph.hasEdge("New York", "Chicago"));
    System.out.println(graph.hasEdge("London", "San Francisco"));

    // --------------------------
    // Test 4: Remove edge
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 4: Remove edge");
    System.out.println("Expected:");
    System.out.println(
        "Vertex        | Adjacent Vertices\n"
            + "---------------------------------\n"
            + "San Francisco | New York, Chicago\n"
            + "New York      | London, San Francisco\n"
            + "Chicago       | San Francisco\n"
            + "London        | New York");

    System.out.println("\nGot:");
    graph.removeEdge("New York", "Chicago");
    graph.printGraph();
    System.out.println();

    // --------------------------
    // Test 5: Remove vertex
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 5: Remove vertex");
    System.out.println("Expected:");
    System.out.println(
        "Vertex        | Adjacent Vertices\n"
            + "---------------------------------\n"
            + "San Francisco | New York, Chicago\n"
            + "New York      | San Francisco\n"
            + "Chicago       | San Francisco");

    System.out.println("\nGot:");
    graph.removeVertex("London");
    graph.printGraph();
    System.out.println();
    // --------------------------
    // Test 6: Depth First Search
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 6: Depth First Search");
    System.out.println("Expected:");
    System.out.println("true");
    System.out.println("false");

    System.out.println("\nGot:");
    System.out.println(graph.depthFirstSearch("San Francisco", "New York"));
    System.out.println(graph.depthFirstSearch("San Francisco", "London"));
    System.out.println();

    // --------------------------
    // Test 8: BFS True
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 8: BFS True");
    System.out.println("Expected:");
    System.out.println("A\n" + "B\n" + "C\n" + "true");

    System.out.println("\nGot:");

    // graph3 is the same as graph3, but the
    // neighbor lists are reversed

    Graph graph3 = new Graph();
    graph3.addVertex("A");
    graph3.addVertex("B");
    graph3.addVertex("C");
    graph3.addVertex("D");
    graph3.addVertex("E");
    graph3.addVertex("F");
    graph3.addVertex("G");
    graph3.addVertex("H");
    graph3.addVertex("I");
    graph3.addEdge("A", "B");
    graph3.addEdge("A", "C");
    graph3.addEdge("A", "D");
    graph3.addEdge("D", "H");
    graph3.addEdge("D", "I");
    graph3.addEdge("C", "G");
    graph3.addEdge("B", "E");
    graph3.addEdge("B", "F");

    System.out.println(graph3.breadthFirstSearch("A", "G"));
    System.out.println();

    // --------------------------
    // Test 9: BFS False
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 9: BFS False");
    System.out.println("Expected:");
    System.out.println(
        "A\n" + "B\n" + "C\n" + "D\n" + "E\n" + "F\n" + "G\n" + "H\n" + "I\n" + "false");

    System.out.println("\nGot:");

    System.out.println(graph3.breadthFirstSearch("A", "Z"));
    System.out.println();
  }
}

class Vertex {
  String label;
  LinkedList<Vertex> neighbors;

  public Vertex(String label) {
    this.label = label;
    this.neighbors = new LinkedList<>();
  }
}
