// Define a class named Node
package ca.mcmaster.cas.se2aa4.a4.pathfinder.adt;

public class Node {

    // Instance variables for the Node class
    private final int ID; // Unique identifier for the node
    private String cityName; // Name of the city represented by the node
    private int elevation; // Elevation of the city represented by the node

    // Constructor for the Node class that only takes an ID as parameter
    public Node (int ID) {
        this.ID = ID;
    }

    // Constructor for the Node class that takes an ID, cityName and elevation as parameters
    public Node (int ID, String cityName, int elevation) {
        this.ID = ID;
        this.cityName = cityName;
        this.elevation = elevation;
    }

    // Returns the ID of the node
    public int ID() {
        return this.ID;
    }

    // Returns the name of the city represented by the node
    public String cityName() {
        return this.cityName;
    }

    // Returns the elevation of the city represented by the node
    public int elevation() {
        return this.elevation;
    }

    // Compares two Node objects to see if they have the same ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node that = (Node) o;
        return this.ID == that.ID;
    }

    // Returns a String representation of the Node object
    @Override
    public String toString() {
        return "[" + "ID: " + this.ID() + ", " + this.cityName() + ", Elevation: " + this.elevation() + "]";
    }
}