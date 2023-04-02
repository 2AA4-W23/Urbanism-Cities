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

    public List<Node> getAdjacencyNodes(Node node) {
        return this.adjacencyList.get(node);
    }

    public int printAdjacencyList() {
        for (Map.Entry<Node, List<Node>> entry : this.adjacencyList.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        return 0;
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
