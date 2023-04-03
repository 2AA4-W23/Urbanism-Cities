package ca.mcmaster.cas.se2aa4.a4.pathfinder.adt;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

public class Node {

    private final Structs.Vertex vertex;
    private final int ID;
    private String cityName;
    private int elevation;
    private String cityType;

    public Node (Structs.Vertex vertex, int ID) {
        this.vertex = vertex;
        this.ID = ID;
    }

    public int ID() {
        return this.ID;
    }
    public double x() {
        return this.vertex.getX();
    }

    public double y() {
        return this.vertex.getY();
    }

    public String cityName() {
        return this.cityName;
    }

    public int elevation() {
        return this.elevation;
    }

    public String cityType() {
        return this.cityType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node that = (Node) o;
        return this.vertex.equals(that.vertex);
    }

    @Override
    public String toString() {
        return "[(" + x() +
                ", " + y() +
                ')' + ", " + this.ID() + ", " + this.cityName() + ", City Type: " + this.cityType() + ", Elevation: " + this.elevation() + "]";
    }
}
