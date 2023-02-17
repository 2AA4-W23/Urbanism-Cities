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
        ca.mcmaster.cas.se2aa4.a2.generator.Mesh mesh = new ca.mcmaster.cas.se2aa4.a2.generator.Mesh();

        int count = 0;

        // Create all the vertices
        for (int x = 0; x < width; x += square_size) {
            for (int y = 0; y < height; y += square_size) {
                mesh.createVertex(x, y);
                mesh.createVertex((x + square_size), y);
                mesh.createVertex(x, (y + square_size));
                mesh.createVertex((x + square_size), (y + square_size));
            }

        }

        mesh.createSegments();

        System.out.println(mesh.vertices);

        // Distribute colors randomly. Vertices are immutable, need to enrich them
        // List<Vertex> verticesWithColors = new ArrayList<>();
        Random bag = new Random();
        for (Vertex v : mesh.vertices) {
            int red = bag.nextInt(255);
            int green = bag.nextInt(255);
            int blue = bag.nextInt(255);
            String colorCode = red + "," + green + "," + blue;
            // Property color = mesh.createProperty(colorCode);
            // Vertex colored = mesh.createVertexColor(v, color);
            mesh.createVertexColor(v, colorCode);
            // verticesWithColors.add(colored);
        }

        // List<Segment> segmentsWithColors = new ArrayList<>();
        for (Segment s : mesh.segments) {
            try {

                System.out.println(s);
                System.out.println(mesh.verticesColored.size());

                int reds = ((Integer
                        .valueOf(mesh.verticesColored.get(s.getV1Idx()).getProperties(0).getValue()
                                .split(",")[0]))
                        + (Integer
                                .valueOf(mesh.verticesColored.get(s.getV2Idx()).getProperties(0).getValue()
                                        .split(",")[0])))
                        / 2;

                int blues = ((Integer
                        .valueOf(mesh.verticesColored.get(s.getV1Idx()).getProperties(0).getValue()
                                .split(",")[1]))
                        + (Integer
                                .valueOf(mesh.verticesColored.get(s.getV2Idx()).getProperties(0).getValue()
                                        .split(",")[1])))
                        / 2;

                int greens = ((Integer
                        .valueOf(mesh.verticesColored.get(s.getV1Idx()).getProperties(0).getValue()
                                .split(",")[2]))
                        + (Integer
                                .valueOf(mesh.verticesColored.get(s.getV2Idx()).getProperties(0).getValue()
                                        .split(",")[2])))
                        / 2;
                String colorCode = reds + "," + greens + "," + blues;
                // Property color = mesh.createProperty(colorCode);
                // Segment colored = mesh.createSegmentColor(s, color);

                mesh.createSegmentColor(s, colorCode);
                // segmentsWithColors.add(colored);

            } catch (Exception e) {
                System.out.println("PROBLEM: " + e);
            }

            // int red = (Integer
            // .valueOf(mesh.getValue(mesh.getProperty(mesh.verticesColored.get(mesh.getV1Idx(s)),
            // 0))
            // .split(",")[0])
            // + Integer.valueOf(
            // mesh.getValue(mesh.getProperty(mesh.verticesColored.get(mesh.getV2Idx(s)),
            // 0))
            // .split(",")[0]))
            // / 2;
            // int blue = (Integer
            // .valueOf(mesh.getValue(mesh.getProperty(mesh.verticesColored.get(mesh.getV1Idx(s)),
            // 0))
            // .split(",")[1])
            // + Integer.valueOf(
            // mesh.getValue(mesh.getProperty(mesh.verticesColored.get(mesh.getV2Idx(s)),
            // 0))
            // .split(",")[1]))
            // / 2;
            // int green = (Integer
            // .valueOf(mesh.getValue(mesh.getProperty(mesh.verticesColored.get(mesh.getV1Idx(s)),
            // 0))
            // .split(",")[2])
            // + Integer.valueOf(
            // mesh.getValue(mesh.getProperty(mesh.verticesColored.get(mesh.getV2Idx(s)),
            // 0))
            // .split(",")[2]))
            // / 2;
        }
        return mesh.generate(mesh.verticesColored, mesh.segmentsColored);

    }

}
