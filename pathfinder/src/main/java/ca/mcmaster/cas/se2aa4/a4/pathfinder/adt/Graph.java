package ca.mcmaster.cas.se2aa4.a4.pathfinder.adt;

import java.util.*;

public class Graph implements Iterable<Node> {

    // Store the adjacency list, edges, and nodes as instance variables
    private Map<Node, List<Edge>> adjacencyList = new HashMap<>();
    private Set<Edge> edges = new HashSet<>();
    private Set<Node> nodes = new HashSet<>();

    public Graph() {
    }

    // Register an edge with the given node IDs and weight
    public void registerEdge(int n1, int n2, double weight) {
        // Get the corresponding nodes
        Node node1 = getNode(n1);
        Node node2 = getNode(n2);

        // Throw an exception if either node is invalid
        if (node1 == null || node2 == null) {
            throw new IllegalArgumentException("Invalid node ID");
        }

        // Create the edge and add it to the edges set if it doesn't already exist
        Edge e = new Edge(node1, node2, weight);
        if (edges.contains(e)) {
            return;
        }

        // Add the nodes to the adjacency list if they don't already exist
        Node[] contents = e.contents();
        if (!this.adjacencyList.containsKey(contents[0])) {
            this.adjacencyList.put(contents[0], new ArrayList<>());
        }
        if (!this.adjacencyList.containsKey(contents[1])) {
            this.adjacencyList.put(contents[1], new ArrayList<>());
        }

        // Add the edge to the adjacency list and edges set
        this.adjacencyList.get(contents[0]).add(e);
        this.adjacencyList.get(contents[1]).add(e);
        this.edges.add(e);
    }

    // Register a node with the given ID
    public void registerNode(int nodeId) {
        // Get the node with the given ID if it already exists
        Node n = getNode(nodeId);

        // Add the node to the nodes set and adjacency list if it doesn't already exist
        if (n == null) {
            n = new Node(nodeId);
            this.adjacencyList.putIfAbsent(n, new ArrayList<>());
            this.nodes.add(n);
        }
    }

    // Remove the node with the given ID from the graph
    public void removeNode(int nodeIdx) {
        // Get the node to remove
        Node nodeToRemove = getNode(nodeIdx);

        // If the node doesn't exist in the graph, do nothing
        if (nodeToRemove == null) {
            return;
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
        // Gets the node with the given index
        Node node = getNode(nodeIdx);

        if (node == null) {
            // If the node does not exist, return an empty list
            return new ArrayList<Node>();
        }

        // Get the nodes that are adjacent to the given node
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
        // Find the node with the given index and return it
        for (Node node : this.nodes) {
            if (node.ID() == nodeIdx) {
                return node;
            }
        }
        // If the node is not found, return null
        return null;
    }

    public double getEdgeWeight(Node n1, Node n2) {
        // Get the weight of the edge connecting the two given nodes
        for (Edge edge : this.edges) {
            Node[] contents = edge.contents();
            if ((contents[0].equals(n1) && contents[1].equals(n2))
                    || (contents[0].equals(n2) && contents[1].equals(n1))) {
                return edge.weight();
            }
        }
        // If the edge is not found, return -1 as the default weight or throw an exception
        return -1;
    }

    public void printAdjacencyList() {
        // Prints the adjacency list of the graph
        for (Map.Entry<Node, List<Edge>> entry : this.adjacencyList.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public void clear() {
        // Clears the graph by removing all nodes, edges, and adjacency lists
        this.adjacencyList.clear();
        this.edges.clear();
        this.nodes.clear();
    }

    public boolean isEmpty() {
        // Returns true if the graph is empty (i.e., has no nodes)
        return this.adjacencyList.isEmpty();
    }

    public int size() {
        // Returns the number of nodes in the graph
        return this.adjacencyList.size();
    }

    public List<Node> getNodes() {
        // Returns a copy of the list of nodes in the graph
        return List.copyOf(this.nodes);
    }

    @Override
    public Iterator<Node> iterator() {
        // Allows iteration over the nodes in the graph using a foreach loop
        return this.nodes.iterator();
    }

    @Override
    public String toString() {
        // Returns a string representation of the graph, showing the edges it contains
        return "Graph(" + "Edges: " + this.edges + ")";
    }
}