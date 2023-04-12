package ca.mcmaster.cas.se2aa4.a4.pathfinder.adt;

// Import the Java utility package for data structures and other useful functions
import java.util.*;

// Define a class named Edge
public class Edge {

    // Instance variables for the Edge class
    private final List<Node> nodes; // A list of nodes connected by the edge
    private double weight; // The weight of the edge

    // Constructor for the Edge class
    public Edge(Node n1, Node n2, double weight) {
        // Initialize the list of nodes
        this.nodes = new ArrayList<>();
        // Add the two nodes passed as parameters to the list
        this.nodes.add(n1);
        this.nodes.add(n2);
        // Set the weight of the edge to the value passed as a parameter
        this.weight = weight;
    }

    // Returns an array of nodes connected by the edge
    public Node[] contents() {
        return this.nodes.toArray(new Node[]{});
    }

    // Returns the weight of the edge
    public double weight() {
        return this.weight;
    }

    // Compares two Edge objects to see if they are equal
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge that = (Edge) o;
        // Compares the nodes and weight of two Edge objects to see if they are the same
        return this.nodes.containsAll(that.nodes) && (this.weight == that.weight);
    }

    // Returns a hash code value for the Edge object
    @Override
    public int hashCode() {
        return Objects.hash(nodes);
    }

    // Returns a String representation of the Edge object
    @Override
    public String toString() {
        return "(" + this.nodes.get(0) +
                ", " + this.nodes.get(1) + ", weight: " + weight +
                ')';
    }

}