package ca.mcmaster.cas.se2aa4.a2.generator.export.enricher;

import ca.mcmaster.cas.se2aa4.a2.generator.adt.Mesh;
import ca.mcmaster.cas.se2aa4.a2.generator.shapes.Circle;
import ca.mcmaster.cas.se2aa4.a2.generator.specification.HeightWidth;
import ca.mcmaster.cas.se2aa4.a2.generator.terrain.Lagoon;
import ca.mcmaster.cas.se2aa4.a2.generator.terrain.Land;
import ca.mcmaster.cas.se2aa4.a2.generator.terrain.Ocean;
import ca.mcmaster.cas.se2aa4.a2.generator.terrain.Water;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomEnricher implements Enricher {

    private final Random bag = new Random();
    private final float threshold;

    public RandomEnricher(float threshold) {
        this.threshold = threshold;
    }

    @Override
    public Structs.Mesh process(Structs.Mesh aMesh) {

        int numPolygon = 0;

        HeightWidth meshDimensions = new HeightWidth();
        Circle oc = new Circle(meshDimensions.height(), meshDimensions.width(), 1500);
        Ellipse2D ocean = oc.createCircle();
        Circle lan = new Circle(meshDimensions.height(), meshDimensions.width(), 500);
        Ellipse2D land = lan.createCircle();
        Circle lag = new Circle(meshDimensions.height(), meshDimensions.width(), 250);
        Ellipse2D lagoon = lag.createCircle();

//        Ocean o = new Ocean(new Circle(meshDimensions.height(), meshDimensions.width(), 1500));
//        Lagoon gh = new Lagoon(new Circle(meshDimensions.height(), meshDimensions.width(), 250));
//        Land fk = new Land(new Circle(meshDimensions.height(), meshDimensions.width(), 500));

        String color = "";

        Set<Integer> oceanIndex = new HashSet<>();
        Set<Integer> landIndex = new HashSet<>();
        Set<Integer> lagoonIndex = new HashSet<>();

        Structs.Mesh.Builder clone = Structs.Mesh.newBuilder();
        clone.addAllVertices(aMesh.getVerticesList());
        clone.addAllSegments(aMesh.getSegmentsList());
        for(Structs.Polygon poly: aMesh.getPolygonsList()) {
            boolean add = true;
            float level = bag.nextFloat();
            Structs.Polygon.Builder pc = Structs.Polygon.newBuilder(poly);
            if (level < threshold){

                double centroid_x = aMesh.getVerticesList().get(poly.getCentroidIdx()).getX();
                double centroid_y = aMesh.getVerticesList().get(poly.getCentroidIdx()).getY();

                if (lagoon.contains(centroid_x, centroid_y)) {
                    lagoonIndex.add(numPolygon);
                    color="129,173,125";
                } else if (land.contains(centroid_x, centroid_y)) {
                    landIndex.add(numPolygon);
                    color = "235,223,167";
                    add = false;
                } else if (ocean.contains(centroid_x, centroid_y)) {
                    oceanIndex.add(numPolygon);
                    color = "54,141,255";
                }

//                if (ocean.contains(centroid_x, centroid_y)) {
//                    color = "54,141,255";
//                    if (land.contains(centroid_x, centroid_y)) {
//                        color = "235,223,167";
//                        if (lagoon.contains(centroid_x, centroid_y)) {
//                            color="129,173,125";
//                        }
//                    }
//                } else {
//                    color = bag.nextInt(255)+","+bag.nextInt(255)+","+bag.nextInt(255);
//                }

                Structs.Property p = Structs.Property.newBuilder()
                        .setKey("rgb_color")
                        .setValue(color)
                        .build();
                pc.addProperties(p);
            }
            if (add) {
                clone.addPolygons(pc);
            }
            numPolygon++;
        }

        for (int l : landIndex) {
            Structs.Polygon.Builder pc = Structs.Polygon.newBuilder(aMesh.getPolygons(l));
            for (int n : aMesh.getPolygonsList().get(l).getNeighborIdxsList()) {
                if (oceanIndex.contains(n) || lagoonIndex.contains(n)) {
                    color = "245,243,152";
                    break;
                }
                color = "235,223,167";
            }
            Structs.Property p = Structs.Property.newBuilder()
                    .setKey("rgb_color")
                    .setValue(color)
                    .build();
            pc.addProperties(p);
            clone.addPolygons(pc);
        }

        return clone.build();
    }
}
