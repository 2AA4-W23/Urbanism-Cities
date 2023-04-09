package ca.mcmaster.cas.se2aa4.a4.pathfinder.adt;

import java.util.*;

public class Edge {

    private final List<Node> nodes;
    private int weight;

    public Edge(Node n1, Node n2) {
        this.nodes = new ArrayList<>();
        this.nodes.add(n1);
        this.nodes.add(n2);
    }

    public Edge(int weight, Node n1, Node n2) {
        this.nodes = new ArrayList<>();
        this.nodes.add(n1);
        this.nodes.add(n2);
        this.weight = weight;
    }

    public Node[] contents() {
        return this.nodes.toArray(new Node[]{});
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge that = (Edge) o;
        return this.nodes.containsAll(that.nodes);
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
