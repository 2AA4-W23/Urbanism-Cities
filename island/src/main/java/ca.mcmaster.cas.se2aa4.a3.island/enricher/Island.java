package ca.mcmaster.cas.se2aa4.a3.island.enricher;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.dimensions.Bounds;
import ca.mcmaster.cas.se2aa4.a3.island.shapes.Circle;
import ca.mcmaster.cas.se2aa4.a3.island.shapes.Rectangle;
import ca.mcmaster.cas.se2aa4.a3.island.shapes.Shape;
import ca.mcmaster.cas.se2aa4.a3.island.dimensions.Dimensons;

import ca.mcmaster.cas.se2aa4.a3.island.terrain.Tile;
import org.locationtech.jts.geom.Geometry;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
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

    public Island(Structs.Mesh aMesh, String shape, String elevation, String biome) {

        this.originalMesh = aMesh;
        this.aMesh.addAllVertices(aMesh.getVerticesList());
        this.aMesh.addAllSegments(aMesh.getSegmentsList());
        this.meshDimensions = new Dimensons(aMesh.getVerticesList());

        this.elevation = elevation;
        this.biome = biome;

        if (shape.equals("circle")) {
            this.circleIsland = (Ellipse2D) new Circle(this.meshDimensions.height(), this.meshDimensions.width(), this.meshDimensions.width()/4).createSelf();
        } else {
            this.rectangleIsland = (Rectangle2D) new Rectangle(this.meshDimensions.height(), this.meshDimensions.width(), 250).createSelf();
        }
        this.aMesh.addAllPolygons(process());
    }

    @Override
    public List<Structs.Polygon> process() {

        String color = "";

        Structs.Mesh.Builder clone = Structs.Mesh.newBuilder();
        clone.addAllVertices(aMesh.getVerticesList());
        clone.addAllSegments(aMesh.getSegmentsList());

        for (Structs.Polygon poly: originalMesh.getPolygonsList()) {

            Structs.Polygon.Builder pc = Structs.Polygon.newBuilder(poly);

            double centroid_x = aMesh.getVerticesList().get(poly.getCentroidIdx()).getX();
            double centroid_y = aMesh.getVerticesList().get(poly.getCentroidIdx()).getY();

            Tile tile = new Tile(this.biome, this.elevation, this.circleIsland, centroid_x, centroid_y);

            color = tile.getColor();

            System.out.println(color);


            Structs.Property p = Structs.Property.newBuilder()
                    .setKey("rgb_color")
                    .setValue(color)
                    .build();
            pc.addProperties(p);
            clone.addPolygons(pc);
        }

        return clone.getPolygonsList();

    }

    @Override
    public Structs.Mesh buildNewMesh() {
        return this.aMesh.build();
    }


}
