package ca.mcmaster.cas.se2aa4.a2.generator.export.enricher;

import ca.mcmaster.cas.se2aa4.a2.generator.shapes.Circle;
import ca.mcmaster.cas.se2aa4.a2.generator.specification.Bounds;
import ca.mcmaster.cas.se2aa4.a2.generator.specification.Dimensons;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.List;
import java.util.Random;

public class RandomEnricher implements Enricher {

    private final Random bag = new Random();
    private final float threshold;

    public RandomEnricher(float threshold) {
        this.threshold = threshold;
    }

    @Override
    public Structs.Mesh process(Structs.Mesh aMesh) {

        int numPolygon = 0;

        Dimensons meshDimensions = new Dimensons();

        String color = "";

        Structs.Mesh.Builder clone = Structs.Mesh.newBuilder();
        clone.addAllVertices(aMesh.getVerticesList());
        clone.addAllSegments(aMesh.getSegmentsList());

        Bounds b = new Bounds();
        b.setLagoonBounds(new Circle(meshDimensions.height(), meshDimensions.width(), 250));
        b.setLandBounds(new Circle(meshDimensions.height(), meshDimensions.width(), 500));
        b.setOceanBounds(new Circle(meshDimensions.height(), meshDimensions.width(), 1500));

        for (Structs.Polygon poly: aMesh.getPolygonsList()) {
            float level = bag.nextFloat();
            Structs.Polygon.Builder pc = Structs.Polygon.newBuilder(poly);
            if (level < threshold) {

                double centroid_x = aMesh.getVerticesList().get(poly.getCentroidIdx()).getX();
                double centroid_y = aMesh.getVerticesList().get(poly.getCentroidIdx()).getY();

                color = b.checkBoundsForColor(centroid_x, centroid_y, numPolygon, poly);

                Structs.Property p = Structs.Property.newBuilder()
                        .setKey("rgb_color")
                        .setValue(color)
                        .build();
                pc.addProperties(p);
            }
            if (b.add()) {
                clone.addPolygons(pc);
            }
            numPolygon++;
        }

        List<Structs.Polygon.Builder> beachTiles = b.checkIfBeachTile(aMesh);

        for (Structs.Polygon.Builder p : beachTiles) {
            clone.addPolygons(p);
        }

        return clone.build();
    }
}
