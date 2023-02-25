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
import org.apache.batik.ext.awt.geom.Polygon2D;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.algorithm.Centroid;
import org.locationtech.jts.geom.MultiPoint;

public class GraphicRenderer {

        private static final int THICKNESS = 3;
        private String[] args;

        public GraphicRenderer(String[] args) {
                this.args = args;
        }

        public void render(Mesh aMesh, Graphics2D canvas) {
                canvas.setColor(Color.BLACK);
                Stroke stroke = new BasicStroke(0.5f);
                canvas.setStroke(stroke);

                if (args.length == 3 && args[2].equals("-X")) {

                        generateVertices(aMesh, canvas);

                        for (Polygon p : aMesh.getPolygonsList()) {
                                // System.out.println("dfdfd: " + p.getSegmentIdxsList());
                                double centreV1_x = aMesh.getVerticesList()
                                                .get(aMesh.getSegmentsList().get(p.getSegmentIdxsList().get(0))
                                                                .getV1Idx())
                                                .getX();
                                double centreV1_y = aMesh.getVerticesList()
                                                .get(aMesh.getSegmentsList().get(p.getSegmentIdxsList().get(0))
                                                                .getV1Idx())
                                                .getY();
                                double centreV2_x = aMesh.getVerticesList()
                                                .get(aMesh.getSegmentsList().get(p.getSegmentIdxsList().get(0))
                                                                .getV2Idx())
                                                .getX();
                                double centreV2_y = aMesh.getVerticesList()
                                                .get(aMesh.getSegmentsList().get(p.getSegmentIdxsList().get(0))
                                                                .getV2Idx())
                                                .getY();
                                double centre2V1_x = aMesh.getVerticesList()
                                                .get(aMesh.getSegmentsList().get(p.getSegmentIdxsList().get(1))
                                                                .getV1Idx())
                                                .getX();
                                double centre2V1_y = aMesh.getVerticesList()
                                                .get(aMesh.getSegmentsList().get(p.getSegmentIdxsList().get(1))
                                                                .getV1Idx())
                                                .getY();
                                double centre2V2_x = aMesh.getVerticesList()
                                                .get(aMesh.getSegmentsList().get(p.getSegmentIdxsList().get(1))
                                                                .getV2Idx())
                                                .getX();
                                double centre2V2_y = aMesh.getVerticesList()
                                                .get(aMesh.getSegmentsList().get(p.getSegmentIdxsList().get(1))
                                                                .getV2Idx())
                                                .getY();
                                double centre3V1_x = aMesh.getVerticesList()
                                                .get(aMesh.getSegmentsList().get(p.getSegmentIdxsList().get(2))
                                                                .getV1Idx())
                                                .getX();
                                double centre3V1_y = aMesh.getVerticesList()
                                                .get(aMesh.getSegmentsList().get(p.getSegmentIdxsList().get(2))
                                                                .getV1Idx())
                                                .getY();
                                double centre3V2_x = aMesh.getVerticesList()
                                                .get(aMesh.getSegmentsList().get(p.getSegmentIdxsList().get(2))
                                                                .getV2Idx())
                                                .getX();
                                double centre3V2_y = aMesh.getVerticesList()
                                                .get(aMesh.getSegmentsList().get(p.getSegmentIdxsList().get(2))
                                                                .getV2Idx())
                                                .getY();
                                double centre4V1_x = aMesh.getVerticesList()
                                                .get(aMesh.getSegmentsList().get(p.getSegmentIdxsList().get(3))
                                                                .getV1Idx())
                                                .getX();
                                double centre4V1_y = aMesh.getVerticesList()
                                                .get(aMesh.getSegmentsList().get(p.getSegmentIdxsList().get(3))
                                                                .getV1Idx())
                                                .getY();
                                double centre4V2_x = aMesh.getVerticesList()
                                                .get(aMesh.getSegmentsList().get(p.getSegmentIdxsList().get(3))
                                                                .getV2Idx())
                                                .getX();
                                double centre4V2_y = aMesh.getVerticesList()
                                                .get(aMesh.getSegmentsList().get(p.getSegmentIdxsList().get(3))
                                                                .getV2Idx())
                                                .getY();

                                double current_centroid_x = aMesh.getVerticesList().get(p.getCentroidIdx()).getX();
                                double current_centroid_y = aMesh.getVerticesList().get(p.getCentroidIdx()).getY();

                                Color old = canvas.getColor();
                                canvas.setColor(extractColor(p.getPropertiesList()));

                                for (int n : p.getNeighborIdxsList()) {
                                        double neighbor_centroid_x = aMesh.getVerticesList()
                                                        .get(aMesh.getPolygonsList().get(n).getCentroidIdx()).getX();
                                        double neighbor_centroid_y = aMesh.getVerticesList()
                                                        .get(aMesh.getPolygonsList().get(n).getCentroidIdx()).getY();
                                        Line2D neighbor_line = new Line2D.Double(current_centroid_x, current_centroid_y,
                                                        neighbor_centroid_x,
                                                        neighbor_centroid_y);
                                        canvas.setColor(Color.GRAY);
                                        canvas.draw(neighbor_line);
                                        canvas.fill(neighbor_line);
                                }

                                canvas.setColor(Color.BLACK);
                                Line2D line = new Line2D.Double(centreV1_x, centreV1_y, centreV2_x, centreV2_y);
                                Line2D line2 = new Line2D.Double(centreV1_x, centreV1_y, centre2V2_x, centre2V2_y);
                                Line2D line3 = new Line2D.Double(centreV2_x, centreV2_y, centre4V2_x, centre4V2_y);
                                Line2D line4 = new Line2D.Double(centre2V2_x, centre2V2_y, centre4V2_x, centre4V2_y);
                                canvas.draw(line);
                                canvas.draw(line2);
                                canvas.draw(line3);
                                canvas.draw(line4);
                                canvas.fill(line);
                                canvas.fill(line2);
                                canvas.fill(line3);
                                canvas.fill(line4);
                                canvas.setColor(Color.BLACK);
                        }
                } else {

                        int counter = 0;

                        for (Polygon p : aMesh.getPolygonsList()) {
                                Color old = canvas.getColor();
                                canvas.setColor(extractColor(p.getPropertiesList()));

                                Polygon2D po = new Polygon2D(.arr1[counter], m.arr2[counter], (int) m.arr3[counter]);

                                canvas.draw(po);
                                canvas.fill(po);
                                canvas.setColor(old);
                                for (int i = 0; i < m.arr3[counter] - 1; i++) {
                                        Line2D line = new Line2D.Double(m.arr1[counter][i], m.arr2[counter][i],
                                                        m.arr1[counter][i + 1], m.arr2[counter][i + 1]);
                                        canvas.draw(line);
                                        canvas.fill(line);
                                }
                                counter++;
                        }

                        generateRandom(aMesh, canvas, m);

                }

        }

        private void generateVertices(Mesh aMesh, Graphics2D canvas) {
                for (Vertex v : aMesh.getVerticesList()) {
                        double centre_x = v.getX() - (THICKNESS / 2.0d);
                        double centre_y = v.getY() - (THICKNESS / 2.0d);
                        if (args.length == 3 && args[2].equals("-X") && aMesh.getVerticesList().indexOf(v) < 625) {
                                canvas.setColor(Color.BLACK);
                        } else {
                                Color old = canvas.getColor();
                                canvas.setColor(extractColor(v.getPropertiesList()));
                                // canvas.setColor(old);
                        }
                        Ellipse2D point = new Ellipse2D.Double(centre_x, centre_y, THICKNESS,
                                        THICKNESS);
                        canvas.fill(point);

                }
        }

        private void generateRandom(Mesh aMesh, Graphics2D canvas, ca.mcmaster.cas.se2aa4.a2.generator.Mesh m) {
                for (Vertex v : m.centroidsVornoid) {
                        double centre_x = v.getX() - (THICKNESS / 2.0d);
                        double centre_y = v.getY() - (THICKNESS / 2.0d);
                        if (args.length == 3 && args[2].equals("-X") && aMesh.getVerticesList().indexOf(v) < 625) {
                                canvas.setColor(Color.BLACK);
                        } else {
                                Color old = canvas.getColor();
                                canvas.setColor(extractColor(v.getPropertiesList()));
                                // canvas.setColor(old);
                        }
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
