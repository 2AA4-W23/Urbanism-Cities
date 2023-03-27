package ca.mcmaster.cas.se2aa4.a3.island.enricher;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.dimensions.Bounds;
import ca.mcmaster.cas.se2aa4.a3.island.dimensions.Dimensons;
import ca.mcmaster.cas.se2aa4.a3.island.shapes.Circle;
import ca.mcmaster.cas.se2aa4.a3.island.shapes.Rectangle;

import java.util.List;

public class Sandbox implements Enricher {

    public Structs.Mesh.Builder aMesh = Structs.Mesh.newBuilder();
    public Structs.Mesh originalMesh = null;
    private Dimensons meshDimensions;
    private int height;
    private int width;

    public Sandbox(Structs.Mesh aMesh) {
        this.originalMesh = aMesh;
        this.aMesh.addAllVertices(aMesh.getVerticesList());
        this.aMesh.addAllSegments(aMesh.getSegmentsList());
    }

    @Override
    public void process() {

        int numPolygon = 0;

        this.meshDimensions = new Dimensons(aMesh.getVerticesList());

        this.height = this.meshDimensions.height();
        this.width = this.meshDimensions.width();

        String color = "";

        Bounds b = new Bounds();
        b.setLagoonBounds(new Circle(this.height, this.width, 250));
        b.setLandBounds(new Circle(this.height, this.width, 500));
        b.setOceanBounds(new Circle(this.height, this.width, 1500));

        for (Structs.Polygon poly: originalMesh.getPolygonsList()) {
            Structs.Polygon.Builder pc = Structs.Polygon.newBuilder(poly);

            double centroid_x = this.aMesh.getVerticesList().get(poly.getCentroidIdx()).getX();
            double centroid_y = this.aMesh.getVerticesList().get(poly.getCentroidIdx()).getY();

            color = b.checkBoundsForColor(centroid_x, centroid_y, numPolygon, poly);



            Structs.Property p = Structs.Property.newBuilder()
                    .setKey("rgb_color")
                    .setValue(color)
                    .build();
            pc.addProperties(p);
            if (b.add()) {
                this.aMesh.addPolygons(pc);
            }
            numPolygon++;
        }

        List<Structs.Polygon.Builder> beachTiles = b.checkIfBeachTile(this.originalMesh);

        for (Structs.Polygon.Builder p : beachTiles) {
            this.aMesh.addPolygons(p);
        }
    }

    @Override
    public Structs.Mesh buildNewMesh() {
        this.process();
        return this.aMesh.build();
    }

}