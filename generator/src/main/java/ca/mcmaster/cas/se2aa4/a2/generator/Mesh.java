package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.operation.overlay.PolygonBuilder;
import org.locationtech.jts.triangulate.VoronoiDiagramBuilder;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.algorithm.Centroid;

import java.util.*;

public class Mesh {

    List<Vertex> vertices = new ArrayList<>();
    List<Vertex> verticesColored = new ArrayList<>();

    List<Segment> segments = new ArrayList<>();
    List<Segment> segmentsColored = new ArrayList<>();

    List<Polygon> polygons = new ArrayList<>();
    List<Polygon> polygonsColored = new ArrayList<>();

    List<Vertex> centroids = new ArrayList<>();
    List<Vertex> centroidsColored = new ArrayList<>();

    public List<Vertex> randomPoints = new ArrayList<>();
    Collection<Coordinate> sites = new ArrayList<>();

    GeometryFactory geometryFactory = new GeometryFactory();
    public VoronoiDiagramBuilder diagram = new VoronoiDiagramBuilder();

    public List<Vertex> centroidsVornoid = new ArrayList<>();

    public Geometry voronoiDiagram;

    List<org.locationtech.jts.geom.Polygon> vornoidPolygons = new ArrayList<>();

    int segmentcounter = 0;

    int polycounter = 0;

    public float[][] arr1 = new float[600][100];
    public float[][] arr2 = new float[600][100];
    public float[] arr3 = new float[600];

    public double getX(Vertex v) {
        double valuex = v.getX();
        return valuex;
    }

    public double getY(Vertex v) {
        double valuey = v.getY();
        return valuey;
    }

    // public int getV1Idx(Segment s) {
    // return s.getV1Idx();
    // }

    // public int getV2Idx(Segment s) {
    // return s.getV2Idx();
    // }

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

    public void createVertex(int x, int y) {
        vertices.add(Vertex.newBuilder().setX((double) x).setY((double) y).build());
    }

    public void createSegment(int vertindex1, int vertindex2) {
        segments.add(Segment.newBuilder().setV1Idx(vertindex1).setV2Idx(vertindex2).build());
    }

    public void createPolygon(int segment1, int segment2, int segment3, int segment4) {
        polygons.add(Polygon.newBuilder().addSegmentIdxs(segment1).addSegmentIdxs(segment2).addSegmentIdxs(segment3)
                .addSegmentIdxs(segment4)
                .build());
    }

    public void createCentroid(int centroid_x, int centroid_y) {
        centroids.add(Vertex.newBuilder().setX((double) centroid_x).setY((double) centroid_y).build());
    }

    public void createNeighbour(Polygon shape, int outerloop) {
        int loopingpolyid = 0;
        List<Integer> neighbours = new ArrayList<>();
        List<Integer> oldsegments;

        for (Polygon shape2 : polygons) {
            if (!shape2.equals(shape)) { // if the two polygons are not the same
                if (!Collections.disjoint(shape2.getSegmentIdxsList(), shape.getSegmentIdxsList())) { // if they have at
                                                                                                      // least one
                                                                                                      // matching
                                                                                                      // segment index
                    neighbours.add(loopingpolyid); // Then shape2 is shape's neighbour
                }
            }
            loopingpolyid++;
        }

        oldsegments = new ArrayList<Integer>(shape.getSegmentIdxsList()); // get current shape's segments
        polygons.set(outerloop,
                Polygon.newBuilder().addAllSegmentIdxs(oldsegments).addAllNeighborIdxs(neighbours).build()); // re-add
                                                                                                             // the
                                                                                                             // shape
                                                                                                             // but with
                                                                                                             // neighbours
    }

    public Property createProperty(String colorCode) {
        Property p = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
        return p;
    }

    public void createVertexColor(Vertex v, String colorCode) {
        verticesColored.add(Vertex.newBuilder(v)
                .addProperties(Property.newBuilder().setKey("rgb_color").setValue(colorCode).build()).build());
    }

    public void createSegmentColor(Segment s, String colorCode) {
        segmentsColored.add(Segment.newBuilder(s)
                .addProperties(Property.newBuilder().setKey("rgb_color").setValue(colorCode).build()).build());
    }

    public void createPolygonColor(Polygon p, String colorCode) {
        polygonsColored
                .add(Polygon.newBuilder(p)
                        .addProperties(Property.newBuilder().setKey("rgb_color").setValue(colorCode).build())
                        .build());
    }

    public void createCentroidColor(Vertex c, String colorCode) {
        centroidsColored.add(Vertex.newBuilder(c)
                .addProperties(Property.newBuilder().setKey("rgb_color").setValue(colorCode).build()).build());
    }

    public void setCentroidIdx() {
        int counter = 0;

        for (Polygon p : polygonsColored) {
            polygonsColored.set(counter, Polygon.newBuilder(p).setCentroidIdx(counter + 625).build());
            ++counter;
        }

        // System.out.println("UPDATED: " + polygonsColored);

    }

    public void generateRandomPoints(int width, int height) {
        Random rand = new Random();
        double x = rand.nextDouble() * width;
        double y = rand.nextDouble() * height;
        randomPoints.add(Vertex.newBuilder().setX((double) x).setY((double) y).build());
    }

    public void generateVoronoid(List<Vertex> points) {
        sites.clear();
        for (Vertex rand : points) {
            sites.add(new Coordinate(rand.getX(), rand.getY()));
        }
        diagram.setSites(sites);
        voronoiDiagram = diagram.getDiagram(new GeometryFactory());

        for (int vd = 0; vd < voronoiDiagram.getNumGeometries(); vd++) {
            ArrayList<Integer> vertexIDs = new ArrayList<>(); //List of all vertex IDs that make up a shape
            for (Coordinate vOID: voronoiDiagram.getGeometryN(vd).getCoordinates()) { //for all points that make up a shape
                int randlooper = 0;
                for (Vertex rand : randomPoints) { //finds vertexID for that point of the shape
                    if (rand.getX() == vOID.getX() && rand.getY() == vOID.getY()) {
                        vertexIDs.add(randlooper);
                    }
                    randlooper++;
                }

            }
            centroids.add(Vertex.newBuilder().setX(Centroid.getCentroid(voronoiDiagram.getGeometryN(vd)).getX()).setY(Centroid.getCentroid(voronoiDiagram.getGeometryN(vd)).getY()).build());
            //vertexIDs now contains the IDs of each vertex that makes up the shape

            List<Integer> segIDs = new ArrayList<>();
            for (int i = 0; i < vertexIDs.size() - 1 ; i++) { //for every vertexID of the shape
                segments.add(Segment.newBuilder().setV1Idx(vertexIDs.get(i)).setV2Idx(vertexIDs.get(i+1)).build()); //making segments of the shape
                segIDs.add(segmentcounter); //keeping track of the IDs of the segment just created
                segmentcounter++;
            }
            // all segments for the shape are now built, and their IDs are stored in segIDs

            polygons.add(Polygon.newBuilder().addAllSegmentIdxs(segIDs).build());

        }

        for (int poly = 0; poly < polygons.size(); poly++) {
            createNeighbour(polygons.get(poly), poly);
        }

        // System.out.println("Printing Voronoi Polygons " + voronoiDiagram);
    }

    public void randomBasedVoronoi() {
        for (Polygon pol : polygonsColored) {
            generateRandomPoints(480, 480);
        }
        // generate voronoid based on the random points
        generateVoronoid(randomPoints);
    }

    public void getCentroidCoordinates(Polygon p) {

    }

    public void lloydRelax() {
        for (int k = 0; k < 20; k++) {
            for (Polygon p : polygons) {

                centroidsVornoid.add(Vertex.newBuilder()
                        .setX(voronoiDiagram.getGeometryN(polycounter).getCentroid()
                                .getX())
                        .setY(voronoiDiagram.getGeometryN(polycounter).getCentroid()
                                .getY())
                        .build());

                // store x and y coordinates of current polygon
                float[] xpositions = new float[100];
                float[] ypositions = new float[100];

                // iterate through the voronoi diagram polygons and add the x and y coordinates
                // to the arrays
                int positioncounter = 0;
                for (Coordinate pID : voronoiDiagram.getGeometryN(polycounter)
                        .getCoordinates()) {
                    xpositions[positioncounter] = (float) pID.getX();
                    ypositions[positioncounter] = (float) pID.getY();

                    Coordinate[] coords = new Coordinate[] {
                            new Coordinate(pID.getX(), pID.getY())
                    };
                    GeometryFactory factory = new GeometryFactory();
                    LinearRing linearRing = new GeometryFactory().createLinearRing(coords);
                    org.locationtech.jts.geom.Polygon polygon = factory.createPolygon(linearRing, null);

                    vornoidPolygons.add(polygon);

                    positioncounter++;
                }

                if (k == 19) {
                    arr1[polycounter] = xpositions;
                    arr2[polycounter] = ypositions;
                    arr3[polycounter] = positioncounter;
                }

                polycounter++;
            }
            polycounter = 0;
        }
    }

    public void createVornoidPolygons() {
        for (float[] i : arr1) {
            for (float c : i) {
            }
        }
    }

    public Structs.Mesh generate(List<Vertex> verticesWithColors, List<Segment> segmentsWithColors,
            List<Polygon> polygonsColored, List<Vertex> centroidsColored) {
        // System.out.println("SIZE: " + vertices.size());
        // System.out.println("SIZE SEGMENTS: " + segments.size());
        // System.out.println("SIZE POLYGONS: " + polygons.size());

        Structs.Mesh mesh = Structs.Mesh.newBuilder().addAllVertices(verticesWithColors)
                .addAllSegments(segmentsWithColors).addAllPolygons(polygonsColored).addAllVertices(centroidsColored)
                .build();
        return mesh;
    }

    public Structs.Mesh generate(List<Vertex> verticesWithColors, List<Segment> segmentsWithColors,
                                 List<Polygon> polygonsColored) {
        // System.out.println("SIZE: " + vertices.size());
        // System.out.println("SIZE SEGMENTS: " + segments.size());
        // System.out.println("SIZE POLYGONS: " + polygons.size());

        Structs.Mesh mesh = Structs.Mesh.newBuilder().addAllVertices(verticesWithColors)
                .addAllSegments(segmentsWithColors).addAllPolygons(polygonsColored)
                .build();
        return mesh;
    }

}