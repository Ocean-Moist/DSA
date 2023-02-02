package org.AStar;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class AStarSearchGraph {

  HashMap<String, GridVertex> vertices;

  public AStarSearchGraph() {
    vertices = new HashMap<>();
  }

  public static void main(String[] args) {
    //        // --------------------------
    //        // Test 0: Dijkstra's Algorithm
    //        // --------------------------
    //        System.out.println("-------------------");
    //        System.out.println("Test 0: Dijkstra's Algorithm");
    //        System.out.println("Expected:");
    //        System.out.println("Visiting A\n" +
    //                "{A=0, B=1, E=3}\n" +
    //                "Visiting B\n" +
    //                "{A=0, B=1, C=2, E=3, F=2}\n" +
    //                "Visiting C\n" +
    //                "{A=0, B=1, C=2, D=3, E=3, F=2, G=3}\n" +
    //                "Visiting F\n" +
    //                "{A=0, B=1, C=2, D=3, E=3, F=2, G=3, J=4}\n" +
    //                "Visiting G\n" +
    //                "{A=0, B=1, C=2, D=3, E=3, F=2, G=3, H=4, J=4, K=4}\n" +
    //                "Visiting E\n" +
    //                "{A=0, B=1, C=2, D=3, E=3, F=2, G=3, H=4, I=7, J=4, K=4}\n" +
    //                "Visiting D\n" +
    //                "{A=0, B=1, C=2, D=3, E=3, F=2, G=3, H=4, I=7, J=4, K=4}\n" +
    //                "Visiting J\n" +
    //                "{A=0, B=1, C=2, D=3, E=3, F=2, G=3, H=4, I=7, J=4, K=4, N=5}\n" +
    //                "Visiting H\n" +
    //                "{A=0, B=1, C=2, D=3, E=3, F=2, G=3, H=4, I=7, J=4, K=4, L=5, N=5}\n" +
    //                "Visiting K\n" +
    //                "{A=0, B=1, C=2, D=3, E=3, F=2, G=3, H=4, I=7, J=4, K=4, L=5, N=5, O=5}\n" +
    //                "Visiting L\n" +
    //                "{A=0, B=1, C=2, D=3, E=3, F=2, G=3, H=4, I=7, J=4, K=4, L=5, N=5, O=5,
    // P=8}\n" +
    //                "Visiting O\n" +
    //                "{A=0, B=1, C=2, D=3, E=3, F=2, G=3, H=4, I=7, J=4, K=4, L=5, N=5, O=5,
    // P=8}\n" +
    //                "Visiting N\n" +
    //                "{A=0, B=1, C=2, D=3, E=3, F=2, G=3, H=4, I=7, J=4, K=4, L=5, M=7, N=5, O=5,
    // P=8}\n" +
    //                "Visiting I\n" +
    //                "{A=0, B=1, C=2, D=3, E=3, F=2, G=3, H=4, I=7, J=4, K=4, L=5, M=7, N=5, O=5,
    // P=8}\n" +
    //                "Visiting M\n" +
    //                "7");
    //
    //        System.out.println("\nGot:");
    AStarSearchGraph g2 = new AStarSearchGraph();
    g2.addVertex("A", 0, 0);
    g2.addVertex("B", 0, 1);
    g2.addVertex("C", 0, 2);
    g2.addVertex("D", 0, 3);
    g2.addVertex("E", 1, 0);
    g2.addVertex("F", 1, 1);
    g2.addVertex("G", 1, 2);
    g2.addVertex("H", 1, 3);
    g2.addVertex("I", 2, 0);
    g2.addVertex("J", 2, 1);
    g2.addVertex("K", 2, 2);
    g2.addVertex("L", 2, 3);
    g2.addVertex("M", 3, 0);
    g2.addVertex("N", 3, 1);
    g2.addVertex("O", 3, 2);
    g2.addVertex("P", 3, 3);
    g2.addEdge("A", "B", 1);
    g2.addEdge("B", "C", 1);
    g2.addEdge("C", "D", 1);
    g2.addEdge("E", "F", 3);
    g2.addEdge("F", "G", 1);
    g2.addEdge("G", "H", 1);
    g2.addEdge("I", "J", 3);
    g2.addEdge("J", "K", 1);
    g2.addEdge("K", "L", 1);
    g2.addEdge("M", "N", 2);
    g2.addEdge("N", "O", 1);
    g2.addEdge("O", "P", 3);
    g2.addEdge("A", "E", 3);
    g2.addEdge("B", "F", 1);
    g2.addEdge("C", "G", 1);
    g2.addEdge("D", "H", 1);
    g2.addEdge("E", "I", 4);
    g2.addEdge("F", "J", 2);
    g2.addEdge("G", "K", 1);
    g2.addEdge("H", "L", 1);
    g2.addEdge("I", "M", 3);
    g2.addEdge("J", "N", 1);
    g2.addEdge("K", "O", 1);
    g2.addEdge("L", "P", 3);
    //
    //        System.out.println(g2.priorityQueueDijkstra("A", "M"));

    // --------------------------
    // Test 1: A* Search Algorithm
    // --------------------------
    System.out.println("-------------------");
    System.out.println("Test 1: A* Search Algorithm");
    System.out.println("Expected:");
    System.out.println(
        "Got:\n"
            + "Visiting A\n"
            + "{A=0, B=1, E=3}\n"
            + "Visiting B\n"
            + "{A=0, B=1, C=2, E=3, F=2}\n"
            + "Visiting E\n"
            + "{A=0, B=1, C=2, E=3, F=2, I=7}\n"
            + "Visiting F\n"
            + "{A=0, B=1, C=2, E=3, F=2, G=3, I=7, J=4}\n"
            + "Visiting J\n"
            + "{A=0, B=1, C=2, E=3, F=2, G=3, I=7, J=4, K=5, N=5}\n"
            + "Visiting N\n"
            + "{A=0, B=1, C=2, E=3, F=2, G=3, I=7, J=4, K=5, M=7, N=5, O=6}\n"
            + "Visiting C\n"
            + "{A=0, B=1, C=2, D=3, E=3, F=2, G=3, I=7, J=4, K=5, M=7, N=5, O=6}\n"
            + "Visiting M\n"
            + "7");

    System.out.println("\nGot:");
    System.out.println(g2.aStar("A", "M"));
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

  // Helper method that changes weight of a node in the queue by removing old node and adding new
  // node
  public void changeWeight(PriorityQueue<PQNode> queue, String label, int newWeight) {
    queue.removeIf(n -> n.label.equals(label));
    queue.add(new PQNode(label, newWeight));
  }

  public int priorityQueueDijkstra(String source, String destination) {
    // Create visitedAndDone hashSet
    HashSet<String> visitedAndDone = new HashSet<>();

    // Create priority queue and add source node
    PriorityQueue<PQNode> queue = new PriorityQueue<>();
    queue.add(new PQNode(source, 0));

    // Create distances HashMap and add source to distance map
    HashMap<String, Integer> distances = new HashMap<>();
    distances.put(source, 0);

    // Do Dijkstra's algorithm
    while (!queue.isEmpty()) {
      // Find minimum distance vertex
      PQNode minDistance = queue.poll();
      String label1 = minDistance.label;
      GridVertex v1 = vertices.get(label1);

      // For debugging, let's print out the node we visit
      System.out.println("Visiting " + label1);

      // Return immediately if we have dequeued destination node
      if (label1.equals(destination)) {
        return distances.get(label1);
      }

      // Otherwise, continue on
      // Add min vertex to visitedAndDone set
      visitedAndDone.add(label1);

      // Update distance of vertex neighbor; loop through all neighbors
      for (WeightedEdge edge1 : v1.edges) {
        String neighborLabel = edge1.destination.label;

        // If you don't like enhanced for loops, you could
        // use the following instead:
        //            for (int i = 0; i < v1.edges.size(); i++) {
        //                WeightedEdge edge1 = v1.edges.get(i);
        //                String neighborLabel = edge1.destination.label;
        //                ...
        //                ...

        // If neighbor has not been visited yet, we need to add
        if (!visitedAndDone.contains(neighborLabel)) {
          // The new (possibly shorter) distance is the distance to label1
          // plus the weight of the edge between label1 and its neighbor
          int newDistance = distances.get(label1) + edge1.weight;

          // The old distance value is either infinity OR the current distance
          // value in the distances HashMap
          int oldDistance = Integer.MAX_VALUE;
          if (distances.containsKey(neighborLabel)) {
            oldDistance = distances.get(neighborLabel);
          }

          // If the new distance is shorter, we need to update both the map and
          // the priority queue
          if (newDistance < oldDistance) {
            distances.put(neighborLabel, newDistance);

            // If neighborLabel isn't in the queue yet, add it to the queue
            if (queueContainsLabel(queue, neighborLabel)) {
              queue.offer(new PQNode(neighborLabel, newDistance));
            }
            // if it already is in the priority queue, update its priority
            else {
              changeWeight(queue, neighborLabel, newDistance);
            }
          }
        }
      }
      // For debugging, let's print out the distances map as we go
      System.out.println(distances);
    }

    return -1;
  }

  // Manhattan Distance Heuristic
  // Given the labels corresponding to two nodes
  // calculates the Manhattan Distance between the nodes
  public int manhattanDistance(String label1, String label2) {
    GridVertex v1 = vertices.get(label1);
    GridVertex v2 = vertices.get(label2);
    return Math.abs(v1.x - v2.x) + Math.abs(v1.y - v2.y);
  }

  public int aStar(String source, String destination) {
    // Create visitedAndDone hashSet
    HashSet<String> visitedAndDone = new HashSet<>();

    // Create priority queue and add source node
    PriorityQueue<PQNode> queue = new PriorityQueue<>();
    queue.add(new PQNode(source, 0));

    // Create distances HashMap and add source to distance map
    HashMap<String, Integer> distances = new HashMap<>();
    distances.put(source, 0);

    // Do Dijkstra's algorithm
    while (!queue.isEmpty()) {
      // Find minimum distance vertex
      PQNode minDistance = queue.poll();
      String label1 = minDistance.label;
      GridVertex v1 = vertices.get(label1);

      // For debugging, let's print out the node we visit
      System.out.println("Visiting " + label1);

      // Return immediately if we have dequeued destination node
      if (label1.equals(destination)) {
        return distances.get(label1);
      }

      // Otherwise, continue on
      // Add min vertex to visitedAndDone set
      visitedAndDone.add(label1);

      // Update distance of vertex neighbor; loop through all neighbors
      for (WeightedEdge edge1 : v1.edges) {
        String neighborLabel = edge1.destination.label;

        // If neighbor has not been visited yet, we need to add
        if (!visitedAndDone.contains(neighborLabel)) {
          // The new (possibly shorter) distance is the distance to label1
          // plus the weight of the edge between label1 and its neighbor
          int newDistance = distances.get(label1) + edge1.weight;

          // The old distance value is either infinity OR the current distance
          // value in the distances HashMap
          int oldDistance = Integer.MAX_VALUE;
          if (distances.containsKey(neighborLabel)) {
            oldDistance = distances.get(neighborLabel);
          }

          // If the new distance is shorter, we need to update both the map and
          // the priority queue
          if (newDistance < oldDistance) {
            distances.put(neighborLabel, newDistance);

            // If neighborLabel isn't in the queue yet, add it to the queue
            if (queueContainsLabel(queue, neighborLabel)) {
              queue.offer(
                  new PQNode(
                      neighborLabel, newDistance + manhattanDistance(neighborLabel, destination)));
            }
            // if it already is in the priority queue, update its priority
            else {
              changeWeight(
                  queue,
                  neighborLabel,
                  newDistance + manhattanDistance(neighborLabel, destination));
            }
          }
        }
      }
      // For debugging, let's print out the distances map as we go
      System.out.println(distances);
    }
    return -1;
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

  class GridVertex {
    String label;
    LinkedList<WeightedEdge> edges;
    int x;
    int y;

    public GridVertex(String label, int x, int y) {
      this.label = label;
      this.edges = new LinkedList<>();
      this.x = x;
      this.y = y;
    }
  }

  class WeightedEdge implements Comparable<WeightedEdge> {
    GridVertex source;
    GridVertex destination;
    int weight;

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

    public int hashCode() {
      return this.source.hashCode() + this.destination.hashCode();
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
    String label;
    int weight;

    public PQNode(String label, int weight) {
      this.label = label;
      this.weight = weight;
    }

    @Override
    public int compareTo(PQNode o) {
      return this.weight - o.weight;
    }

    @Override
    public String toString() {
      return label + ": " + weight;
    }
  }
}
