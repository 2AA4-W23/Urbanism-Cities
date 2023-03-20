package ca.mcmaster.cas.se2aa4.a3.island.enricher;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.dimensions.Bounds;
import ca.mcmaster.cas.se2aa4.a3.island.dimensions.Dimensons;
import ca.mcmaster.cas.se2aa4.a3.island.shapes.Circle;

import java.util.List;

public class Sandbox {

    public Structs.Mesh.Builder aMesh = Structs.Mesh.newBuilder();
    private Dimensons meshDimensions;
    private int height;
    private int width;

    public Sandbox(Structs.Mesh aMesh, String elevation) {
        this.aMesh.addAllVertices(aMesh.getVerticesList());
        this.aMesh.addAllSegments(aMesh.getSegmentsList());
        this.aMesh.addAllPolygons(colorPolygons(aMesh));
    }

    private List<Structs.Polygon> colorPolygons(Structs.Mesh aMesh) {

        int numPolygon = 0;

        this.meshDimensions = new Dimensons(aMesh.getVerticesList());

        this.height = this.meshDimensions.height();
        this.width = this.meshDimensions.width();

        String color = "";

        Structs.Mesh.Builder clone = Structs.Mesh.newBuilder();
        clone.addAllVertices(aMesh.getVerticesList());
        clone.addAllSegments(aMesh.getSegmentsList());

        Bounds b = new Bounds();
        b.setLagoonBounds(new Circle(this.height, this.width, 250));
        b.setLandBounds(new Circle(this.height, this.width, 500));
        b.setOceanBounds(new Circle(this.height, this.width, 1500));

        for (Structs.Polygon poly: aMesh.getPolygonsList()) {
            Structs.Polygon.Builder pc = Structs.Polygon.newBuilder(poly);

            double centroid_x = aMesh.getVerticesList().get(poly.getCentroidIdx()).getX();
            double centroid_y = aMesh.getVerticesList().get(poly.getCentroidIdx()).getY();

            color = b.checkBoundsForColor(centroid_x, centroid_y, numPolygon, poly);

            Structs.Property p = Structs.Property.newBuilder()
                    .setKey("rgb_color")
                    .setValue(color)
                    .build();
            pc.addProperties(p);
            if (b.add()) {
                clone.addPolygons(pc);
            }
            numPolygon++;
        }

        List<Structs.Polygon.Builder> beachTiles = b.checkIfBeachTile(aMesh);

        for (Structs.Polygon.Builder p : beachTiles) {
            clone.addPolygons(p);
        }

        return clone.getPolygonsList();

    }

    public Structs.Mesh buildNewMesh() {
        return this.aMesh.build();
    }

}