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

    public int getV1Idx(Segment s) {
        return s.getV1Idx();
    }

    public int getV2Idx(Segment s) {
        return s.getV2Idx();
    }

    public void setX(Vertex v, double x) {
        v.newBuilderForType().setY(x);
    }

    public void setY(Vertex v, double y) {
        v.newBuilderForType().setY(y);
    }

    public Property getProperty(Vertex v, int index) {
        return v.getProperties(index);
    }

    public Segment getSegment(Segment s) {
        return s;
    }

    public String getValue(Property p) {
        return p.getValue();
    }

    public Vertex createVertex(int x, int y) {

        Vertex v = Vertex.newBuilder().setX((double) x).setY((double) y).build();
        return v;
    }

    public Segment createSegment(int vertindex1, int vertindex2) {
        Segment s = Segment.newBuilder().setV1Idx(vertindex1).setV2Idx(vertindex2).build();
        return s;
    }

    public Property createProperty(String colorCode) {
        Property p = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
        return p;
    }

    public Vertex createVertexColor(Vertex v, Property color) {
        Vertex vc = Vertex.newBuilder(v).addProperties(color).build();
        return vc;
    }

    public Segment createSegmentColor(Segment s, Property color) {
        Segment sc = Segment.newBuilder(s).addProperties(color).build();
        return sc;
    }

    public Structs.Mesh generate(List<Vertex> verticesWithColors, List<Segment> segmentsWithColors) {
        Structs.Mesh mesh = Structs.Mesh.newBuilder().addAllVertices(verticesWithColors).addAllSegments(segments).addAllSegments(segmentsWithColors).build();
        return mesh;
    }
}