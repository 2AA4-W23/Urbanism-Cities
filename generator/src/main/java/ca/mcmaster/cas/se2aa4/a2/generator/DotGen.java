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
        ca.mcmaster.cas.se2aa4.a2.generator.Mesh mesh = new ca.mcmaster.cas.se2aa4.a2.generator.Mesh();
        int count = 0;

        // Create all the vertices
        for (int x = 0; x < width; x += square_size) {
            for (int y = 0; y < height; y += square_size) {
                mesh.createVertex(x,y);
                vertices.add(Vertex.newBuilder().setX((double) x).setY((double) y).build());
                count++;
                vertices.add(Vertex.newBuilder().setX((double) x + square_size).setY((double) y).build());
                segments.add(Segment.newBuilder().setV1Idx(count).setV2Idx(count - 1).build());
                count++;
                vertices.add(Vertex.newBuilder().setX((double) x).setY((double) y + square_size).build());
                segments.add(Segment.newBuilder().setV1Idx(count).setV2Idx(count - 2).build());
                count++;
                vertices.add(Vertex.newBuilder().setX((double) x + square_size).setY((double) y + square_size).build());
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
            Property color = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
            Vertex colored = Vertex.newBuilder(v).addProperties(color).build();
            verticesWithColors.add(colored);
        }

        List<Segment> segmentsWithColors = new ArrayList<>();
        for (Segment s : segments) {
            int red = ((Integer
                    .valueOf(verticesWithColors.get(s.getV1Idx()).getProperties(0).getValue()
                            .split(",")[0]))
                    + (Integer
                            .valueOf(verticesWithColors.get(s.getV2Idx()).getProperties(0).getValue()
                                    .split(",")[0])))
                    / 2;
            int blue = ((Integer
                    .valueOf(verticesWithColors.get(s.getV1Idx()).getProperties(0).getValue()
                            .split(",")[1]))
                    + (Integer
                            .valueOf(verticesWithColors.get(s.getV2Idx()).getProperties(0).getValue()
                                    .split(",")[1])))
                    / 2;
            int green = ((Integer
                    .valueOf(verticesWithColors.get(s.getV1Idx()).getProperties(0).getValue()
                            .split(",")[2]))
                    + (Integer
                            .valueOf(verticesWithColors.get(s.getV2Idx()).getProperties(0).getValue()
                                    .split(",")[2])))
                    / 2;
            String colorCode = red + "," + green + "," + blue;
            Property color = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
            Segment colored = Segment.newBuilder(s).addProperties(color).build();
            segmentsWithColors.add(colored);
        }

        return Mesh.newBuilder().addAllVertices(verticesWithColors).addAllSegments(segments)
                .addAllSegments(segmentsWithColors).build();
    }

}
