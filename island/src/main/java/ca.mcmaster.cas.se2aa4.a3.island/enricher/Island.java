package ca.mcmaster.cas.se2aa4.a3.island.enricher;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.dimensions.Bounds;
import ca.mcmaster.cas.se2aa4.a3.island.shapes.Circle;
import ca.mcmaster.cas.se2aa4.a3.island.shapes.Rectangle;
import ca.mcmaster.cas.se2aa4.a3.island.shapes.Shape;
import ca.mcmaster.cas.se2aa4.a3.island.dimensions.Dimensons;

import ca.mcmaster.cas.se2aa4.a3.island.terrain.Tile;
import org.locationtech.jts.geom.Geometry;
import water.Lakes;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class Island implements Enricher {
    private Ellipse2D circleIsland;

    public Structs.Mesh originalMesh = null;

    private Rectangle2D rectangleIsland;
    public Structs.Mesh.Builder aMesh = Structs.Mesh.newBuilder();
    private Dimensons meshDimensions;
    private int height;
    private int width;
    private String elevation;
    private String biome;
    private List<Tile> tileList = new ArrayList<>();

    private String lakes;

    public Island(Structs.Mesh aMesh, String shape, String elevation, String biome, String lakes) {
        this.originalMesh = aMesh;
        this.aMesh.addAllVertices(aMesh.getVerticesList());
        this.aMesh.addAllSegments(aMesh.getSegmentsList());
        this.meshDimensions = new Dimensons(aMesh.getVerticesList());
        this.elevation = elevation;
        this.biome = biome;
        this.lakes = lakes;

        if (shape.equals("circle")) {
            this.circleIsland = (Ellipse2D) new Circle(this.meshDimensions.height(), this.meshDimensions.width(), this.meshDimensions.width()/4).createSelf();
        } else {
            this.rectangleIsland = (Rectangle2D) new Rectangle(this.meshDimensions.height(), this.meshDimensions.width(), 250).createSelf();
        }
    }

    private void colorPolygons() {
        String color = "";
        for (Tile tile : this.tileList) {
            Structs.Polygon.Builder pc = Structs.Polygon.newBuilder(this.originalMesh.getPolygonsList().get(tile.getID()));
            color = tile.getColor();

            Structs.Property p = Structs.Property.newBuilder()
                    .setKey("rgb_color")
                    .setValue(color)
                    .build();
            pc.addProperties(p);
            this.aMesh.addPolygons(pc);
        }
    }

    @Override
    public void process() {
        int numPolygon = 0;

        for (Structs.Polygon poly: this.originalMesh.getPolygonsList()) {

            double centroid_x = this.aMesh.getVerticesList().get(poly.getCentroidIdx()).getX();
            double centroid_y = this.aMesh.getVerticesList().get(poly.getCentroidIdx()).getY();

            Tile tile = new Tile(this.biome, this.elevation, this.circleIsland, centroid_x, centroid_y, poly.getNeighborIdxsList(), numPolygon);
            this.tileList.add(tile);

            ++numPolygon;
        }
    }

    @Override
    public Structs.Mesh buildNewMesh() {
        this.process();
        this.buildLakes();
        this.colorPolygons();
        return this.aMesh.build();
    }


    private void buildLakes() {
        if (Integer.parseInt(this.lakes) > 0) {
            Lakes lakes = new Lakes(this.originalMesh.getPolygonsList(), this.tileList, Integer.parseInt(this.lakes));
            this.tileList = lakes.createLakes();
        }
    }

}