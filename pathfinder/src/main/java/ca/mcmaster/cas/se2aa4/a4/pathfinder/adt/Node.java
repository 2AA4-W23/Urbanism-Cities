package ca.mcmaster.cas.se2aa4.a4.pathfinder.adt;

public class Node {
    private final int ID;
    private String cityName;
    private int elevation;
    private String cityType;

    public Node (int ID) {
        this.ID = ID;
    }

    public int ID() {
        return this.ID;
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
        return this.ID == that.ID;
    }

    @Override
    public String toString() {
        return "[" + this.ID() + ", " + this.cityName() + ", City Type: " + this.cityType() + ", Elevation: " + this.elevation() + "]";
    }
}
