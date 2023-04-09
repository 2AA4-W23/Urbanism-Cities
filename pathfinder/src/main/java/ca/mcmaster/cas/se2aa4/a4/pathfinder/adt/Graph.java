package ca.mcmaster.cas.se2aa4.a4.pathfinder.adt;

import java.util.*;

public class Graph implements Iterable<Edge> {

    private Map<Node, List<Node>> adjacencyList = new HashMap<>();
    private Set<Edge> edges = new HashSet<>();
    private Set<Node> nodes = new HashSet<>();

    public Graph() {
    }

    public void registerEdge(Edge e) {
        // Check if edge already exists
        for (Edge existingEdge : edges) {
            if (existingEdge.equals(e)) {
                return; // Edge already exists, so don't add it again
            }
        }

        Node[] contents = e.contents();
        // Add nodes to adjacency list if they don't exist
        if (!this.adjacencyList.containsKey(contents[0])) {
            this.adjacencyList.put(contents[0], new ArrayList<>());
        }
        if (!this.adjacencyList.containsKey(contents[1])) {
            this.adjacencyList.put(contents[1], new ArrayList<>());
        }
        // Add edge to adjacency list and edges list
        this.adjacencyList.get(contents[0]).add(contents[1]);
        this.adjacencyList.get(contents[1]).add(contents[0]);
        this.edges.add(e);
    }
    public void registerNode(Node n) {
        if (!this.adjacencyList.containsKey(n)) {
            this.adjacencyList.putIfAbsent(n, new ArrayList<>());
            this.nodes.add(n);
        }
    }

    public void removeNode(Node node) {
        // Remove the node from the nodes set
        this.nodes.remove(node);

        // Remove the node from the adjacency list and its corresponding edges
        List<Node> neighbors = this.adjacencyList.remove(node);
        for (Node neighbor : neighbors) {
            this.adjacencyList.get(neighbor).remove(node);
            this.edges.remove(new Edge(node, neighbor));
        }
    }

    public List<Node> getAdjacencyNodes(Node node) {
        if (this.adjacencyList.containsKey(node)) {
            return this.adjacencyList.get(node);
        } else {
            return new ArrayList<Node>();
        }
    }

    public Node getNode(int nodeIdx) {
        for (Map.Entry<Node, List<Node>> kv : this.adjacencyList.entrySet()) {
            if (nodeIdx == kv.getKey().ID()) {
                return kv.getKey();
            }
        }
        return null;
    }

    public int printAdjacencyList() {
        for (Map.Entry<Node, List<Node>> entry : this.adjacencyList.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        return 0;
    }

    public void clear() {
        this.adjacencyList.clear();
    }

    public boolean isEmpty() {
        return this.adjacencyList.isEmpty();
    }

    public int size() {
        return this.adjacencyList.size();
    }

    @Override
    public Iterator<Edge> iterator() {
        return this.edges.iterator();
    }

    @Override
    public String toString() {
        return "Graph(" + "Edges: " + this.edges + ")";
    }
}
