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

    GeometryFactory geometryFactory = new GeometryFactory();
    public VoronoiDiagramBuilder diagram = new VoronoiDiagramBuilder();

    public List<Vertex> centroidsVornoid = new ArrayList<>();

    ArrayList<Vertex> vertexVornoid = new ArrayList<>();
    ArrayList<ArrayList<Vertex>> allVertexVornoid = new ArrayList<ArrayList<Vertex>>();

    ArrayList<Segment> segmentVornoidIndex = new ArrayList<>();
    ArrayList<ArrayList<Segment>> allsegmentVornoidIndex = new ArrayList<ArrayList<Segment>>();

    ArrayList<Polygon> polygon = new ArrayList<>();

    public Geometry voronoiDiagram;

    int segmentcounter = 0;

    int polycounter = 0;

    public float[][] arr1 = new float[600][100];
    public float[][] arr2 = new float[600][100];
    public float[] arr3 = new float[600];

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

    // public void lloydRelax() {
    // for (int k = 0; k < 20; k++) {
    // for (Polygon p : polygons) {

    // centroidsVornoid.add(Vertex.newBuilder()
    // .setX(voronoiDiagram.getGeometryN(polycounter).getCentroid()
    // .getX())
    // .setY(voronoiDiagram.getGeometryN(polycounter).getCentroid()
    // .getY())
    // .build());

    // // store x and y coordinates of current polygon
    // float[] xpositions = new float[100];
    // float[] ypositions = new float[100];

    // // iterate through the voronoi diagram polygons and add the x and y
    // coordinates
    // // to the arrays
    // int positioncounter = 0;
    // for (Coordinate pID : voronoiDiagram.getGeometryN(polycounter)
    // .getCoordinates()) {
    // xpositions[positioncounter] = (float) pID.getX();
    // ypositions[positioncounter] = (float) pID.getY();

    // Coordinate[] coords = new Coordinate[] {
    // new Coordinate(pID.getX(), pID.getY())
    // };
    // GeometryFactory factory = new GeometryFactory();
    // LinearRing linearRing = new GeometryFactory().createLinearRing(coords);
    // org.locationtech.jts.geom.Polygon polygon = factory.createPolygon(linearRing,
    // null);

    // vornoidPolygons.add(polygon);

    // positioncounter++;
    // }

    // if (k == 19) {
    // arr1[polycounter] = xpositions;
    // arr2[polycounter] = ypositions;
    // arr3[polycounter] = positioncounter;
    // }

    // polycounter++;
    // }
    // polycounter = 0;
    // }
    // }

    // public void createVornoidPolygons() {
    // for (float[] i : arr1) {
    // for (float c : i) {
    // }
    // }
    // }

    public Structs.Mesh generate(List<Vertex> vertices, List<Segment> segments,
            List<Polygon> polygons) {

        Structs.Mesh mesh = Structs.Mesh.newBuilder().addAllVertices(vertices)
                .addAllSegments(segments).addAllPolygons(polygons)
                .build();
        return mesh;
    }

}