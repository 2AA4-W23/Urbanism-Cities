package ca.mcmaster.cas.se2aa4.a3.island.terrain;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import elevation.Volcano;
import org.locationtech.jts.geom.Geometry;

import java.awt.geom.RectangularShape;

public class Tile {
    private int elevation;
    private String biome = "";
    private int temperature;
    private RectangularShape shape;
    public double centroidX;
    public double centroidY;

    String color = "";

    public Tile(String biome, String elevation, RectangularShape islandshape, double centroidX, double centroidY) {
        this.biome = biome;
        this.shape  = islandshape;
        this.centroidX = centroidX;
        this.centroidY = centroidY;

        if (elevation.equals("Volcano")) {
            System.out.println("Herer");
            this.volcanizer();
        } else if (elevation.equals("Flatland")) {
//            this.flatter();
        } else {
            System.out.println("HERERERERE");
//            this.hiller();
        }

    }

    private void volcanizer() {
        Volcano volcano = new Volcano(this.shape, this.biome, this);
        this.color = volcano.gradient();
        this.elevation = volcano.assignElevation();
    }

    public String getColor() {
        return this.color;
    }

    public int getElevation() {
        return this.elevation;
    }

}
