package ca.mcmaster.cas.se2aa4.a2.generator;

import java.io.IOException;
import java.util.*;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
//import ca.mcmaster.cas.se2aa4.a2.generator.Mesh;

public class DotGen {

        private final int width = 500;
        private final int height = 500;
        private final int square_size = 20;

        public Mesh generate() {
                ca.mcmaster.cas.se2aa4.a2.generator.Mesh mesh = new ca.mcmaster.cas.se2aa4.a2.generator.Mesh();

                // Create all the vertices
                for (int x = 0; x < width; x += square_size) {
                        for (int y = 0; y < height; y += square_size) {
                                mesh.createVertex(x, y);
                        }

                }

                // Create all segments
                for (int i = 0; i < mesh.vertices.size(); i++) {

                        if (!((i + 1) % 25 == 0)) {
                                // System.out.println("EHRKHEKRHE: " + i);
                                mesh.createSegment(i, i + 1);
                        }
                        if (i < 600) {
                                mesh.createSegment(i, i + 25);
                        }
                }

                // Create all polygons

                int rightSide = 49;

                for (int k = 0; k < mesh.segments.size(); k += 2) {

                        if (k < mesh.segments.size() - 73) {
                                if (k > 0 && k % 49 == 46) {
                                        mesh.createPolygon(k, k + 1, k + 49, k + 2);
                                        k += 1;
                                } else {
                                        mesh.createPolygon(k, k + 1, k + 49, k + 3);
                                }
                        } else if (k < mesh.segments.size() - 25) {
                                if (k % 49 == 46) {
                                        mesh.createPolygon(k, k + 1, k + rightSide, k + 2);
                                } else {
                                        mesh.createPolygon(k, k + 1, k + rightSide, k + 3);
                                        rightSide -= 1;
                                }

                        }

                }

                // Creating polygon neighbours
                int outerloop = 0;
                for (Polygon shape : mesh.polygons) {
                        mesh.createNeighbour(shape, outerloop);
                        outerloop++;
                }

                // Distribute colors randomly. Vertices are immutable, need to enrich them
                // List<Vertex> verticesWithColors = new ArrayList<>();
                Random bag = new Random();
                for (Vertex v : mesh.vertices) {
                        int red = bag.nextInt(255);
                        int green = bag.nextInt(255);
                        int blue = bag.nextInt(255);
                        String colorCode = red + "," + green + "," + blue;
                        mesh.createVertexColor(v, colorCode);
                }

                // List<Segment> segmentsWithColors = new ArrayList<>();
                for (Segment s : mesh.segments) {
                        try {
                                // System.out.println(s);
                                // System.out.println(mesh.verticesColored.size());

                                int reds = (int) Math.sqrt(((Integer.valueOf(mesh.verticesColored.get(s.getV1Idx()).getProperties(0).getValue().split(",")[0])) ^ 2 + (Integer.valueOf(mesh.verticesColored.get(s.getV2Idx()).getProperties(0).getValue().split(",")[0])) ^ 2) * 100);

                                int blues = (int) Math.sqrt(((Integer.valueOf(mesh.verticesColored.get(s.getV1Idx()).getProperties(0).getValue().split(",")[1])) ^ 2 + (Integer.valueOf(mesh.verticesColored.get(s.getV2Idx()).getProperties(0).getValue().split(",")[1])) ^ 2) * 100);

                                int greens = (int) Math.sqrt(((Integer.valueOf(mesh.verticesColored.get(s.getV1Idx()).getProperties(0).getValue().split(",")[2])) ^ 2 + (Integer.valueOf(mesh.verticesColored.get(s.getV2Idx()).getProperties(0).getValue().split(",")[2])) ^ 2) * 100);
                                String colorCode = reds + "," + greens + "," + blues;
                                // Property color = mesh.createProperty(colorCode);
                                // Segment colored = mesh.createSegmentColor(s, color);

                                mesh.createSegmentColor(s, colorCode);
                                // segmentsWithColors.add(colored);

                        } catch (Exception e) {
                                System.out.println("PROBLEM: " + e);
                        }
                }

                for (Polygon p : mesh.polygons) {

                        double centreV1_y = mesh.vertices.get(mesh.segments.get(p.getSegmentIdxsList().get(0)).getV1Idx()).getY();
                        double centreV2_y = mesh.vertices.get(mesh.segments.get(p.getSegmentIdxsList().get(0)).getV2Idx()).getY();
                        double centre2V1_x = mesh.vertices.get(mesh.segments.get(p.getSegmentIdxsList().get(1)).getV1Idx()).getX();
                        double centre2V2_x = mesh.vertices.get(mesh.segments.get(p.getSegmentIdxsList().get(1)).getV2Idx()).getX();

                        int red = bag.nextInt(255);
                        int green = bag.nextInt(255);
                        int blue = bag.nextInt(255);

                        String colorCode = red + "," + green + "," + blue;

                        mesh.createPolygonColor(p, colorCode);

                        double centroid_x = (centre2V1_x + centre2V2_x) / 2;
                        double centroid_y = (centreV1_y + centreV2_y) / 2;

                        mesh.createCentroid((int) centroid_x, (int) centroid_y);
                }

                for (Vertex c : mesh.centroids) {
                        String colorCode = 255 + "," + 0 + "," + 0;
                        mesh.createCentroidColor(c, colorCode);
                }

                mesh.setCentroidIdx();

                return mesh.generate(mesh.verticesColored, mesh.segmentsColored, mesh.polygonsColored, mesh.centroidsColored);

        }

}
