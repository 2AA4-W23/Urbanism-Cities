package ca.mcmaster.cas.se2aa4.a2.generator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Random;
import java.util.List;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
//import ca.mcmaster.cas.se2aa4.a2.generator.Mesh;

public class DotGen {

    private final int width = 500;
    private final int height = 500;
    private final int square_size = 20;

    public Mesh generate() {
        List<Vertex> vertices = new ArrayList<>();
        List<Segment> segments = new ArrayList<>();
        HashSet<Vertex> verticesDup = new HashSet<>();
        ca.mcmaster.cas.se2aa4.a2.generator.Mesh mesh = new ca.mcmaster.cas.se2aa4.a2.generator.Mesh();
        int count = 0;

        // Create all the vertices
        for (int x = 0; x < width; x += square_size) {
            for (int y = 0; y < height; y += square_size) {
//                Vertex v1 = mesh.createVertex(x,y);
//                Vertex v2 = mesh.createVertex((x + square_size), y);
//                Vertex v3 = mesh.createVertex(x, (y + square_size ));
//                Vertex v4 = mesh.createVertex((x + square_size), (y + square_size ));
//
//                if (!vertices.contains(v1)) {
//                    vertices.add(v1);
//                    count++;
//                }
//                if (!vertices.contains(v2)) {
//                    vertices.add(v2);
//                    count++;
//                }
//                if (!vertices.contains(v3)) {
//                    vertices.add(v3);
//                    count++;
//                }
//                if (!vertices.contains(v4)) {
//                    vertices.add(v4);
//                    count++;
//                }


                vertices.add(mesh.createVertex(x,y));

                count++;
                vertices.add(mesh.createVertex((x + square_size), y));
                segments.add(mesh.createSegment(count, count-1));
                count++;
                vertices.add(mesh.createVertex(x, (y + square_size )));
                segments.add(mesh.createSegment(count, count-2));
                count++;
                vertices.add(mesh.createVertex((x + square_size), (y + square_size )));
                count++;
            }
        }

        // Distribute colors randomly. Vertices are immutable, need to enrich them
        List<Vertex> verticesWithColors = new ArrayList<>();
        Random bag = new Random();
        for (Vertex v : vertices) {
            int red = bag.nextInt(255);
            int green = bag.nextInt(255);
            int blue = bag.nextInt(255);
            String colorCode = red + "," + green + "," + blue;
            Property color = mesh.createProperty(colorCode);
            Vertex colored = mesh.createVertexColor(v,color);
            verticesWithColors.add(colored);
        }

        List<Segment> segmentsWithColors = new ArrayList<>();
        for (Segment s : segments) {


            int red = (Integer.valueOf(mesh.getValue(mesh.getProperty(verticesWithColors.get(mesh.getV1Idx(s)), 0)).split(",")[0]) + Integer.valueOf(mesh.getValue(mesh.getProperty(verticesWithColors.get(mesh.getV2Idx(s)), 0)).split(",")[0]))/2;
            int blue = (Integer.valueOf(mesh.getValue(mesh.getProperty(verticesWithColors.get(mesh.getV1Idx(s)), 0)).split(",")[1]) + Integer.valueOf(mesh.getValue(mesh.getProperty(verticesWithColors.get(mesh.getV2Idx(s)), 0)).split(",")[1]))/2;
            int green = (Integer.valueOf(mesh.getValue(mesh.getProperty(verticesWithColors.get(mesh.getV1Idx(s)), 0)).split(",")[2]) + Integer.valueOf(mesh.getValue(mesh.getProperty(verticesWithColors.get(mesh.getV2Idx(s)), 0)).split(",")[2]))/2;

            String colorCode = red + "," + green + "," + blue;
            Property color = mesh.createProperty(colorCode);
            Segment colored = mesh.createSegmentColor(s,color);

            segmentsWithColors.add(colored);
        }
        return mesh.generate(verticesWithColors, segmentsWithColors);

    }

}
