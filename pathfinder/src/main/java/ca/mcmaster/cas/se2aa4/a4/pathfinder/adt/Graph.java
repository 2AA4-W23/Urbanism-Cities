package ca.mcmaster.cas.se2aa4.a4.pathfinder.adt;

import java.util.*;

public class Graph implements Iterable<Edge> {

    private Map<Node, List<Node>> adjacencyList = new HashMap<>();
    private Set<Edge> edges = new HashSet<>();
    private Set<Node> nodes = new HashSet<>();
    private Map<Edge, Integer> edgeIdx;

    public Graph() {
    }

    public void registerEdge(Edge e) {
        Node[] contents = e.contents();
        this.adjacencyList.get(contents[0]).add(contents[1]);
        this.adjacencyList.putIfAbsent(contents[1], new ArrayList<>());
        this.adjacencyList.get(contents[1]).add(contents[0]);
        this.edges.add(e);
    }
    public void registerNode(Node n) {
        this.adjacencyList.putIfAbsent(n, new ArrayList<>());
        this.nodes.add(n);
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

    public void setEdgeIndices(Map<Edge, Integer> edgeIdx) {
        this.edgeIdx = edgeIdx;
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
