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
import ca.mcmaster.cas.se2aa4.a2.generator.Mesh.*;
import org.apache.batik.ext.awt.geom.Polygon2D;
import org.locationtech.jts.geom.Coordinate;

public class GraphicRenderer {

        private static final int THICKNESS = 3;
        private String[] args;
        List<Vertex> centroidsVornoid = new ArrayList<>();

        public GraphicRenderer(String[] args) {
                this.args = args;
        }

        public void render(Mesh aMesh, Graphics2D canvas) {
                ca.mcmaster.cas.se2aa4.a2.generator.Mesh m = new ca.mcmaster.cas.se2aa4.a2.generator.Mesh();
                for (Polygon pol : aMesh.getPolygonsList()) {
                        m.generateRandomPoints(480, 480);
                }
                m.generateVoronoid();
                canvas.setColor(Color.BLACK);
                Stroke stroke = new BasicStroke(0.5f);
                canvas.setStroke(stroke);

                System.out.println("ZZZZZZZZZZZZZZZZZZZZZZ: " + aMesh.getPolygonsCount());
                System.out.println("hdkfjhsfhsif: " + aMesh.getPolygonsList());
                System.out.println("ARGS: " + args[0].toString());
                System.out.println("ARGS: " + args[1].toString());
                System.out.println("ARGS SIZE: " + args.length);
                System.out.println(Arrays.toString(args));

                if (args.length == 3 && args[2].equals("-X")) {

                        generateVertices(aMesh, canvas);

                        for (Polygon p : aMesh.getPolygonsList()) {
                                System.out.println("dfdfd: " + p.getSegmentIdxsList());
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

                        // List<Integer> vertices = new ArrayList();
                        int polycounter = 0;
                        for (Polygon p : aMesh.getPolygonsList()) {
                                // for (int segID : p.getSegmentIdxsList()) {//for every segment, get the vertex
                                // IDs.
                                // int vID = aMesh.getSegmentsList().get(segID).getV1Idx();
                                // vertices.add(vID);
                                // vID = aMesh.getSegmentsList().get(segID).getV2Idx();
                                // vertices.add(vID);
                                //
                                // }
                                // int firstV = vertices.get(0);
                                // int finalV = vertices.get(0);
                                // for (int vertID : vertices) {
                                // firstV = Math.min(firstV, vertID); //Find first vertexID
                                // }
                                //
                                // for (int vertID : vertices) {
                                // finalV = Math.max(finalV, vertID); //Find last vertexID
                                // }

                                float[] xpositions = new float[100];
                                float[] ypositions = new float[100];
                                int positioncounter = 0;
                                for (Coordinate pID : m.voronoiDiagram.getGeometryN(polycounter).getCoordinates()) {
                                        xpositions[positioncounter] = (float) pID.getX();
                                        ypositions[positioncounter] = (float) pID.getY();
                                        positioncounter++;
                                }

                                // double ULVertex_y = aMesh.getVerticesList().get(firstV).getY();
                                // double ULVertex_x = aMesh.getVerticesList().get(firstV).getX();
                                // double LRVertex_y = aMesh.getVerticesList().get(finalV).getY();
                                // double LRVertex_x = aMesh.getVerticesList().get(finalV).getX();

                                Color old = canvas.getColor();
                                canvas.setColor(extractColor(p.getPropertiesList()));

                                Polygon2D po = new Polygon2D(xpositions, ypositions, positioncounter);

                                float centroid_x = 0;
                                float centroid_y = 0;

                                for (int i = 0; i < positioncounter; i++) {
                                        centroid_x += xpositions[i];
                                        centroid_y += ypositions[i];
                                }

                                centroid_x = centroid_x / positioncounter;
                                centroid_y = centroid_y / positioncounter;

                                System.out.println("CENTRoIDS: " + centroidsVornoid);

                                centroidsVornoid.add(Vertex.newBuilder().setX(centroid_x).setY(centroid_y).build());

                                // java.awt.geom.Rectangle2D polygon = new Rectangle2D.Double(ULVertex_x,
                                // ULVertex_y, LRVertex_x - ULVertex_x, LRVertex_y - ULVertex_y);
                                // canvas.draw(polygon);
                                // canvas.fill(polygon);
                                canvas.draw(po);
                                canvas.fill(po);
                                canvas.setColor(old);
                                // vertices.clear();
                                polycounter++;
                        }

                        // for (Segment s : aMesh.getSegmentsList()) {
                        // double centre_x = aMesh.getVerticesList().get(s.getV1Idx()).getX();
                        // double centre_y = aMesh.getVerticesList().get(s.getV1Idx()).getY();
                        // double centre2_x = aMesh.getVerticesList().get(s.getV2Idx()).getX();
                        // double centre2_y = aMesh.getVerticesList().get(s.getV2Idx()).getY();
                        // Color old = canvas.getColor();
                        // canvas.setColor(extractColor(s.getPropertiesList()));
                        // Line2D line = new Line2D.Double(centre_x, centre_y, centre2_x, centre2_y);
                        // canvas.draw(line);
                        // canvas.fill(line);
                        // canvas.setColor(old);
                        // }

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
                for (Vertex v : centroidsVornoid) {
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
                                System.out.println(p.getValue());
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
