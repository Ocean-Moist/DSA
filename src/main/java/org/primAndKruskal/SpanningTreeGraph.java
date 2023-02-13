package org.primAndKruskal;

import java.util.*;

public class SpanningTreeGraph {
  HashMap<String, WeightedVertex> vertices;

  public SpanningTreeGraph() {
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

      v1.edges.add(new WeightedEdge(v1, v2, weight));
      v2.edges.add(new WeightedEdge(v2, v1, weight));
    }
  }

  // Adds an edge to the graph, given an edge
  public void addEdge(WeightedEdge e) {
    addEdge(e.source.label, e.destination.label, e.weight);
  }

  // Removes a vertex from the graph
  public void removeVertex(String label) {
    // Check vertex exists before removing it
    if (vertices.containsKey(label)) {
      WeightedVertex v1 = vertices.get(label);

      // Remove all edges to this vertex
      for (WeightedEdge edge1 : v1.edges) {
        WeightedVertex v2 = edge1.destination;

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
      WeightedVertex v1 = vertices.get(label1);
      WeightedVertex v2 = vertices.get(label2);

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

  // Remove an edge from the graph, given an edge
  public void removeEdge(WeightedEdge e) {
    removeEdge(e.source.label, e.destination.label);
  }

  // Creates a graph with just the nodes from original graph
  public SpanningTreeGraph createEmptyGraph() {
    SpanningTreeGraph tree = new SpanningTreeGraph();
    for (String label : vertices.keySet()) {
      tree.addVertex(label);
    }
    return tree;
  }

  // Prim's can start at any note in the graph, but we'll
  // just provide a specific starting node so that the
  // output is more consistent
  public SpanningTreeGraph prim(String source) {
    SpanningTreeGraph tree = createEmptyGraph();

    HashSet<WeightedVertex> explored = new HashSet<>();
    PriorityQueue<WeightedEdge> queue = new PriorityQueue<>();

    explored.add(vertices.get(source));
    queue.addAll(vertices.get(source).edges);

    while (!queue.isEmpty()) {
      WeightedEdge e = queue.poll();
      WeightedVertex v1 = e.source;
      WeightedVertex v2 = e.destination;

      if (explored.contains(v1) && explored.contains(v2)) {
        continue;
      }

      tree.addEdge(e);
      if (!explored.contains(v1)) {
        explored.add(v1);
        queue.addAll(v1.edges);
      }

      if (!explored.contains(v2)) {
        explored.add(v2);
        queue.addAll(v2.edges);
      }
    }

    return tree;
  }

  // Checks if there is a cycle in the graph, from the given starting node
  public boolean hasCycle(String source) {
    HashSet<WeightedVertex> explored = new HashSet<>();

    Deque<WeightedVertex> stack = new LinkedList<>();
    HashMap<WeightedVertex, WeightedVertex> parent = new HashMap<>();

    parent.put(vertices.get(source), null);
    stack.push(vertices.get(source));

    while (!stack.isEmpty()) {
      WeightedVertex v = stack.pop();

      if (!explored.contains(v)) {
        explored.add(v);
        for (WeightedEdge v2 : v.edges) {
          WeightedVertex n = v2.destination;

          // Check if it's not the parent AND we've already been there
          // If so, we've found a cycle!
          if (n.equals(parent.get(v))) {
            continue;
          } else if (explored.contains(n)) {
            // System.out.println("We've already visited: " + n.label);
            return true;
          }

          stack.push(n);
          parent.put(n, v);
        }
      }
    }

    return false;
  }

  // Returns a list of all edges in the graph
  public LinkedList<WeightedEdge> getAllEdges() {
    LinkedList<WeightedEdge> list = new LinkedList<>();

    // Go through all edges in the graph
    for (String label1 : vertices.keySet()) {
      WeightedVertex v1 = vertices.get(label1);

      for (WeightedEdge e : v1.edges) {
        WeightedVertex v2 = e.destination;
        String label2 = v2.label;

        // Only add if edge (or reverse edge) is not already present
        boolean alreadyPresent = false;
        for (WeightedEdge w : list) {
          if ((w.source.label.equals(label1) && w.destination.label.equals(label2))
              || (w.source.label.equals(label2) && w.destination.label.equals(label1))) {
            alreadyPresent = true;
            break;
          }
        }

        if (!alreadyPresent) {
          list.add(e);
        }
      }
    }

    return list;
  }

  // Kruskal doesn't have any starting node, so there are no inputs
  public SpanningTreeGraph kruskal() {
    SpanningTreeGraph tree = createEmptyGraph();

    // Sort edges by weight
    LinkedList<WeightedEdge> edges = getAllEdges();
    Collections.sort(edges);

    while (!edges.isEmpty()) {
      WeightedEdge e = edges.poll();
      tree.addEdge(e);

      // Check if adding this edge will create a cycle
      if (tree.hasCycle(e.source.label)) {
        tree.removeEdge(e);
      }
    }

    return tree;
  }

  // brute force traveling
  public SpanningTreeGraph tsp() {
    HashSet<WeightedVertex> explored = new HashSet<>();
    SpanningTreeGraph tree = createEmptyGraph();
    WeightedVertex start = vertices.get("A");
    WeightedVertex current = start;
    explored.add(start);
    int count = 0;

    while (count < vertices.size() - 1) {
      // find the min 6edge
      WeightedEdge min = null;
      for (WeightedEdge e : current.edges) {
        if (explored.contains(e.destination)) {
          continue;
        }
        if (min == null || e.weight < min.weight) {
          min = e;
        }
      }

      // add the min edge
      explored.add(min.destination);
      tree.addEdge(min);
      current = min.destination;
      count++;
    }

    WeightedEdge min = null;
    for (WeightedEdge e : current.edges) {
      if (e.destination.equals(start)) {
        if (min == null || e.weight < min.weight) {
          min = e;
        }
      }
    }
    tree.addEdge(min);

    return tree;
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
    // Test 1: Prim's Algorithm (Graph 1)
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 1: Prim's Algorithm (Graph 1)");
    System.out.println("Expected:");
    System.out.println(
        "Vertex | Adjacent Vertices\n"
            + "--------------------------\n"
            + "A      | B: 3\n"
            + "B      | A: 3, C: 1\n"
            + "C      | B: 1, D: 2\n"
            + "D      | C: 2, E: 3\n"
            + "E      | D: 3, F: 1, G: 3\n"
            + "F      | E: 1\n"
            + "G      | E: 3, H: 2\n"
            + "H      | G: 2");

    System.out.println("\nGot:");

    SpanningTreeGraph g1 = new SpanningTreeGraph();
    g1.addVertex("A");
    g1.addVertex("B");
    g1.addVertex("C");
    g1.addVertex("D");
    g1.addVertex("E");
    g1.addVertex("F");
    g1.addVertex("G");
    g1.addVertex("H");

    g1.addEdge("A", "B", 3);
    g1.addEdge("A", "C", 4);
    g1.addEdge("A", "D", 7);
    g1.addEdge("B", "C", 1);
    g1.addEdge("B", "F", 5);
    g1.addEdge("C", "D", 2);
    g1.addEdge("C", "F", 6);
    g1.addEdge("D", "E", 3);
    g1.addEdge("D", "G", 6);
    g1.addEdge("E", "F", 1);
    g1.addEdge("E", "G", 3);
    g1.addEdge("E", "H", 4);
    g1.addEdge("F", "H", 8);
    g1.addEdge("G", "H", 2);

    //        g1.printGraph();
    //        // Vertex | Adjacent Vertices
    //        // --------------------------
    //        // A      | B: 3, C: 4, D: 7
    //        // B      | A: 3, C: 1, F: 5
    //        // C      | A: 4, B: 1, D: 2, F: 6
    //        // D      | A: 7, C: 2, E: 3, G: 6
    //        // E      | D: 3, F: 1, G: 3, H: 4
    //        // F      | B: 5, C: 6, E: 1, H: 8
    //        // G      | D: 6, E: 3, H: 2
    //        // H      | E: 4, F: 8, G: 2

    SpanningTreeGraph tree = g1.prim("A");
    tree.printGraph();

    // --------------------------
    // Test 2: Prim's Algorithm (Graph 2)
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 2: Prim's Algorithm (Graph 2)");
    System.out.println("Expected:");
    System.out.println(
        "Vertex | Adjacent Vertices\n"
            + "--------------------------\n"
            + "A      | B: 2\n"
            + "B      | A: 2, F: 4, E: 5\n"
            + "C      | D: 4\n"
            + "D      | E: 3, C: 4\n"
            + "E      | B: 5, D: 3\n"
            + "F      | B: 4");

    System.out.println("\nGot:");

    SpanningTreeGraph g2 = new SpanningTreeGraph();
    g2.addVertex("A");
    g2.addVertex("B");
    g2.addVertex("C");
    g2.addVertex("D");
    g2.addVertex("E");
    g2.addVertex("F");

    g2.addEdge("A", "B", 2);
    g2.addEdge("A", "F", 7);
    g2.addEdge("B", "C", 8);
    g2.addEdge("B", "E", 5);
    g2.addEdge("B", "F", 4);
    g2.addEdge("C", "D", 4);
    g2.addEdge("C", "E", 5);
    g2.addEdge("D", "E", 3);
    g2.addEdge("E", "F", 5);

    SpanningTreeGraph tree2 = g2.prim("A");
    tree2.printGraph();

    // --------------------------
    // Test 3: Kruskal's Algorithm (Graph 1)
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 3: Kruskal's Algorithm (Graph 1)");
    System.out.println("Expected:");
    System.out.println(
        "Vertex | Adjacent Vertices\n"
            + "--------------------------\n"
            + "A      | B: 3\n"
            + "B      | C: 1, A: 3\n"
            + "C      | B: 1, D: 2\n"
            + "D      | C: 2, E: 3\n"
            + "E      | F: 1, G: 3, D: 3\n"
            + "F      | E: 1\n"
            + "G      | H: 2, E: 3\n"
            + "H      | G: 2");

    System.out.println("\nGot:");

    SpanningTreeGraph tree3 = g1.kruskal();
    tree3.printGraph();

    // --------------------------
    // Test 4: Kruskal's Algorithm (Graph 2)
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 4: Kruskal's Algorithm (Graph 2)");
    System.out.println("Expected:");
    System.out.println(
        "Vertex | Adjacent Vertices\n"
            + "--------------------------\n"
            + "A      | B: 2\n"
            + "B      | A: 2, F: 4\n"
            + "C      | D: 4\n"
            + "D      | E: 3, C: 4\n"
            + "E      | D: 3, F: 5\n"
            + "F      | B: 4, E: 5");

    System.out.println("\nGot:");

    SpanningTreeGraph tree4 = g2.kruskal();
    tree4.printGraph();
  }

  class WeightedVertex {
    String label;
    LinkedList<WeightedEdge> edges;

    public WeightedVertex(String label) {
      this.label = label;
      this.edges = new LinkedList<>();
    }
  }

  class WeightedEdge implements Comparable<WeightedEdge> {
    WeightedVertex source;
    WeightedVertex destination;
    int weight;

    public WeightedEdge(WeightedVertex source, WeightedVertex destination, int weight) {
      this.source = source;
      this.destination = destination;
      this.weight = weight;
    }

    public boolean equals(Object other) {
      if (other instanceof WeightedEdge) {
        WeightedEdge otherEdge = (WeightedEdge) other;
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
      return source.label + "->" + destination.label + ": " + weight;
    }

    @Override
    public int compareTo(WeightedEdge o) {
      return this.weight - o.weight;
    }
  }
}
