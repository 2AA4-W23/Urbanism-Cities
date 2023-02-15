package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
//import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;

import java.util.ArrayList;
import java.util.List;

public class Mesh extends DotGen {
    private int width = 500;
    private int height = 500;
    private int square_size = 20;

    List<Vertex> vertices = new ArrayList<>();
    List<Segment> segments = new ArrayList<>();
    int count = 0;

    public void createSegment() {
        for (int x = 0; x < width; x += square_size) {
            for (int y = 0; y < height; y += square_size) {
                vertices.add(Vertex.newBuilder().setX((double) x).setY((double) y).build());
                vertices.add(Vertex.newBuilder().setX((double) x).setY((double) y).build());
            }
        }
    }
}