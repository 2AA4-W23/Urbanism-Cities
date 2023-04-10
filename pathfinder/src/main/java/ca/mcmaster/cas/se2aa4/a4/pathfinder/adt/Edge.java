package ca.mcmaster.cas.se2aa4.a4.pathfinder.adt;

import java.util.*;

public class Edge {

    private final List<Node> nodes;
    private double weight;

    public Edge(Node n1, Node n2, double weight) {
        this.nodes = new ArrayList<>();
        this.nodes.add(n1);
        this.nodes.add(n2);
        this.weight = weight;
    }

    public Node[] contents() {
        return this.nodes.toArray(new Node[]{});
    }

    public double weight() {
        return this.weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge that = (Edge) o;
        return this.nodes.containsAll(that.nodes) && (this.weight == that.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodes);
    }

    @Override
    public String toString() {
        return "(" + this.nodes.get(0) +
                ", " + this.nodes.get(1) +
                ')';
    }

}
