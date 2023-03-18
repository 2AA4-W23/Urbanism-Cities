package ca.mcmaster.cas.se2aa4.a3.island.dimensions;

import ca.mcmaster.cas.se2aa4.a3.island.shapes.Circle;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.Lagoon;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.Land;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.Ocean;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.Beach;

import java.util.ArrayList;
import java.util.List;

public class Bounds {

    private Land land;
    private Ocean ocean;
    private Lagoon lagoon;
    private Beach beach;
    private Tile tileType;
    private String color = "";
    boolean add = true;

    public void setOceanBounds(Circle c) {
        ocean = new Ocean(c.createCircle());
    }

    public void setLandBounds(Circle c) {
        land = new Land(c.createCircle());
    }

    public void setLagoonBounds(Circle c) {
        lagoon = new Lagoon(c.createCircle());
    }

    public String checkBoundsForColor(double centroid_x, double centroid_y, int numPolygon, Structs.Polygon poly) {

        this.add = true;

        if (lagoon.contains(centroid_x, centroid_y)) {
            this.lagoon.addLagoonPolygon(numPolygon);
            this.color = tileType.LAGOON.color;
        } else if (land.contains(centroid_x, centroid_y)) {
            this.land.addLandPolygon(numPolygon);
            this.add = false;
        } else if (ocean.contains(centroid_x, centroid_y)) {
            this.ocean.addOceanPolygon(numPolygon);
            this.color = tileType.OCEAN.color;
        }

        return this.color;

    }

    public List<Structs.Polygon.Builder> checkIfBeachTile(Structs.Mesh aMesh) {

        List<Structs.Polygon.Builder> pcList = new ArrayList<>();
        this.beach = new Beach();

        for (int l : land.terrainPolygons) {
            Structs.Polygon.Builder pc = Structs.Polygon.newBuilder(aMesh.getPolygons(l));
            for (int n : aMesh.getPolygons(l).getNeighborIdxsList()) {
                if (lagoon.terrainPolygons.contains(n) || ocean.terrainPolygons.contains(n)) {
                    this.color = tileType.BEACH.color;
                    this.beach.addBeachPolygon(l);
                    break;
                }
                this.color = tileType.LAND.color;
            }
            Structs.Property p = Structs.Property.newBuilder()
                    .setKey("rgb_color")
                    .setValue(this.color)
                    .build();

            pc.addProperties(p);

            pcList.add(pc);
        }

        return pcList;

    }

    public boolean add() {
        return this.add;
    }

}
