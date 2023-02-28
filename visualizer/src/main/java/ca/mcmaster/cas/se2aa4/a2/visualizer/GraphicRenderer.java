package ca.mcmaster.cas.se2aa4.a2.visualizer;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;

import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.apache.batik.ext.awt.geom.Polygon2D;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.algorithm.Centroid;
import org.locationtech.jts.geom.MultiPoint;

public class GraphicRenderer {

        private static final int THICKNESS = 3;

        public void render(Mesh aMesh, Graphics2D canvas) {

                canvas.setColor(Color.BLACK);
                Stroke stroke = new BasicStroke(0.5f);
                canvas.setStroke(stroke);
                int countSegments = 0;

                for (Polygon p : aMesh.getPolygonsList()) {

                        // keep track of array values
                        int arrayCounter = 0;

                        // arrays for x and y coordinates
                        float[] arr1 = new float[100];
                        float[] arr2 = new float[100];

                        // match each polygon's segment index with its associated segment
                        while (arrayCounter < p.getSegmentIdxsList().size()) {
                                try {

                                        // store X coordinates
                                        arr1[arrayCounter] = (float) aMesh.getVerticesList()
                                                        .get((aMesh.getSegmentsList().get(countSegments)
                                                                        .getV1Idx()))
                                                        .getX();

                                        arr1[arrayCounter] = (float) aMesh.getVerticesList()
                                                        .get((aMesh.getSegmentsList().get(countSegments)
                                                                        .getV2Idx()))
                                                        .getX();

                                        // store Y coordinates
                                        arr2[arrayCounter] = (float) aMesh.getVerticesList()
                                                        .get((aMesh.getSegmentsList().get(countSegments)
                                                                        .getV1Idx()))
                                                        .getY();

                                        arr2[arrayCounter] = (float) aMesh.getVerticesList()
                                                        .get((aMesh.getSegmentsList().get(countSegments)
                                                                        .getV2Idx()))
                                                        .getY();

                                        countSegments++;
                                        arrayCounter++;
                                } catch (Exception e) {
                                        System.out.println(e);
                                }
                        }

                        // create the polygon using the x, y coordinates and the number of points per
                        // polygon

                        System.out.println("ARRAY 1: " + Arrays.toString(arr1));
                        System.out.println("ARRAY 2: " + Arrays.toString(arr2));

                        Polygon2D po = new Polygon2D(arr1, arr2, arrayCounter);

                        Random bag = new Random();

                        int red = bag.nextInt(255);
                        int green = bag.nextInt(255);
                        int blue = bag.nextInt(255);

                        // draw and fill the polygon
                        canvas.setColor(new Color(red, green, blue));
                        canvas.draw(po);
                        canvas.fill(po);

                        // // draw the polygon segments
                        // for (int i = 0; i < arrayCounter - 1; i++) {
                        // Line2D line = new Line2D.Double(arr1[i], arr2[i],
                        // arr1[i + 1], arr2[i + 1]);
                        // canvas.draw(line);
                        // canvas.fill(line);
                        // }
                }

        }

        private void generateRandom(Mesh aMesh, Graphics2D canvas) {
                for (Vertex v : aMesh.getVerticesList()) {
                        double centre_x = v.getX() - (THICKNESS / 2.0d);
                        double centre_y = v.getY() - (THICKNESS / 2.0d);
                        canvas.setColor(Color.BLACK);
                        Ellipse2D point = new Ellipse2D.Double(centre_x, centre_y, THICKNESS,
                                        THICKNESS);
                        canvas.fill(point);
                }
        }

        private Color extractColor(List<Property> properties) {
                String val = null;
                for (Property p : properties) {
                        if (p.getKey().equals("rgb_color")) {
                                // System.out.println(p.getValue());
                                val = p.getValue();
                        }
                }
                if (val == null)
                        return Color.BLACK;
                String[] raw = val.split(",");
                int red = Integer.parseInt(raw[0]);
                int green = Integer.parseInt(raw[1]);
                int blue = Integer.parseInt(raw[2]);
                return new Color(red, green, blue);
        }

}
