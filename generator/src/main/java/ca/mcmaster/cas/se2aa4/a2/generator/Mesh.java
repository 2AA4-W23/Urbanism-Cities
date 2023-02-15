package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
//import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;

import java.util.ArrayList;
import java.util.List;

public class Mesh  {
    private int width = 500;
    private int height = 500;
    private int square_size = 20;

    List<Vertex> vertices = new ArrayList<>();
    List<Segment> segments = new ArrayList<>();
    int count = 0;


    public double getX(Vertex v) {
        double valuex = v.getX();
        return valuex;
    }

    public double getY(Vertex v) {
        double valuey = v.getY();
        return valuey;
    }

    public void setX(Vertex v, double x) {
        v.newBuilderForType().setY(x);
    }

    public void setY(Vertex v, double y) {
        v.newBuilderForType().setY(y);
    }


    public Vertex createVertex(int x, int y) {
        Vertex v = Vertex.newBuilder().setX((double) x).setY((double) y).build();
        return v;
    }

    public Segment createSegment(int vertindex1, int vertindex2) {
        Segment s = Segment.newBuilder().setV1Idx(vertindex1).setV2Idx(vertindex2).build();
        return s;
    }
    public void createSegment() {
        for (int x = 0; x < width; x += square_size) {
            for (int y = 0; y < height; y += square_size) {
                vertices.add(Vertex.newBuilder().setX((double) x).setY((double) y).build());
                vertices.add(Vertex.newBuilder().setX((double) x).setY((double) y).build());
            }
        }
    }


    public Structs.Mesh generate(ArrayList<Vertex> verticesWithColors, ArrayList<Segment> segmentsWithColors) {
        Structs.Mesh mesh = Structs.Mesh.newBuilder().addAllVertices(verticesWithColors).addAllSegments(segments).addAllSegments(segmentsWithColors).build();
        return mesh;
    }
}