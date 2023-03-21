package ca.mcmaster.cas.se2aa4.a3.island.enricher;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.dimensions.Bounds;
import ca.mcmaster.cas.se2aa4.a3.island.shapes.Circle;
import ca.mcmaster.cas.se2aa4.a3.island.shapes.Rectangle;
import ca.mcmaster.cas.se2aa4.a3.island.shapes.Shape;
import ca.mcmaster.cas.se2aa4.a3.island.dimensions.Dimensons;

import org.locationtech.jts.geom.Geometry;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

public class Island implements Enricher {
    private Circle circleIsland;
    private Rectangle rectangleIsland;
    public Structs.Mesh.Builder aMesh = Structs.Mesh.newBuilder();
    private Dimensons meshDimensions;
    private int height;
    private int width;

    public Island(Structs.Mesh aMesh, String shape) {

        this.aMesh.addAllVertices(aMesh.getVerticesList());
        this.aMesh.addAllSegments(aMesh.getSegmentsList());
        this.aMesh.addAllPolygons(process(aMesh));

        if (shape.equals("circle")) {
            this.circleIsland = new Circle(this.meshDimensions.height(), this.meshDimensions.width(), 250);
        } else {
            this.rectangleIsland = new Rectangle(this.meshDimensions.height(), this.meshDimensions.width(), 250);
            Rectangle2D r = (Rectangle2D) this.rectangleIsland.createSelf();
        }
    }

    @Override
    public List<Structs.Polygon> process(Structs.Mesh aMesh) {

        return null;

    }

    @Override
    public Structs.Mesh buildNewMesh() {
        return null;
    }


}
