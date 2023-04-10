package ca.mcmaster.cas.se2aa4.a4.pathfinder.adt;

import java.util.*;

public class Graph implements Iterable<Node> {

    private Map<Node, List<Edge>> adjacencyList = new HashMap<>();
    private Set<Edge> edges = new HashSet<>();
    private Set<Node> nodes = new HashSet<>();

    public Graph() {
    }

    public void registerEdge(int n1, int n2, double weight) {
        Node node1 = getNode(n1);
        Node node2 = getNode(n2);

        if (node1 == null || node2 == null) {
            throw new IllegalArgumentException("Invalid node ID");
        }

        Edge e = new Edge(node1, node2, weight);
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
        this.adjacencyList.get(contents[0]).add(e);
        this.adjacencyList.get(contents[1]).add(e);
        this.edges.add(e);
    }

    public void registerNode(int nodeId) {
        Node n = getNode(nodeId);
        if (n == null) {
            n = new Node(nodeId);
            this.adjacencyList.putIfAbsent(n, new ArrayList<>());
            this.nodes.add(n);
        }
    }

    public void removeNode(int nodeIdx) {
        Node nodeToRemove = getNode(nodeIdx);

        if (nodeToRemove == null) {
            return; // Node does not exist in graph, so do nothing
        }

        // Remove the node from the nodes set
        this.nodes.remove(nodeToRemove);

        // Remove the node from the adjacency list and its corresponding edges
        List<Edge> edgesToRemove = new ArrayList<>();
        List<Edge> edgesToCheck = this.adjacencyList.remove(nodeToRemove);
        if (edgesToCheck != null) {
            for (Edge edge : edgesToCheck) {
                Node[] contents = edge.contents();
                Node neighbor = contents[0].equals(nodeToRemove) ? contents[1] : contents[0];
                this.adjacencyList.get(neighbor).remove(edge);
                edgesToRemove.add(edge);
            }
            this.edges.removeAll(edgesToRemove);
        }
    }

    public List<Node> getAdjacencyNodes(int nodeIdx) {
        Node node = getNode(nodeIdx);

        if (node == null) {
            return new ArrayList<Node>(); // Node does not exist in graph, so return empty list
        }

        List<Node> adjacencyNodes = new ArrayList<>();
        if (this.adjacencyList.containsKey(node)) {
            List<Edge> adjacencyEdges = this.adjacencyList.get(node);
            for (Edge edge : adjacencyEdges) {
                if (edge.contents()[1].equals(node)) {
                    adjacencyNodes.add(edge.contents()[0]);
                } else if (edge.contents()[0].equals(node)) {
                    adjacencyNodes.add(edge.contents()[1]);
                }
            }
        }
        return adjacencyNodes;
    }

    public Node getNode(int nodeIdx) {
        for (Node node : this.nodes) {
            if (node.ID() == nodeIdx) {
                return node;
            }
        }
        return null;
    }

    public double getEdgeWeight(Node n1, Node n2) {
        for (Edge edge : this.edges) {
            Node[] contents = edge.contents();
            if ((contents[0].equals(n1) && contents[1].equals(n2))
                    || (contents[0].equals(n2) && contents[1].equals(n1))) {
                return edge.weight();
            }
        }
        // Edge not found, so return a default weight of -1 or throw an exception
        return -1;
    }

    public void printAdjacencyList() {
        for (Map.Entry<Node, List<Edge>> entry : this.adjacencyList.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public void clear() {
        this.adjacencyList.clear();
        this.edges.clear();
        this.nodes.clear();
    }

    public boolean isEmpty() {
        return this.adjacencyList.isEmpty();
    }

    public int size() {
        return this.adjacencyList.size();
    }

    public List<Node> getNodes() {
        return List.copyOf(this.nodes);
    }

    @Override
    public Iterator<Node> iterator() {
        return this.nodes.iterator();
    }

    @Override
    public String toString() {
        return "Graph(" + "Edges: " + this.edges + ")";
    }
}
