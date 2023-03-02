package ca;

import java.io.IOException;
import java.util.*;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;

import org.locationtech.jts.geom.*;
import org.locationtech.jts.triangulate.*;

public class VornoidGen extends ca.mcmaster.cas.se2aa4.a2.generator.Mesh {

    Collection<Coordinate> sites = new ArrayList<>();

    List<Vertex> randomPoints = new ArrayList<>();

    List<Vertex> vornoidVertices = new ArrayList<>();
    List<Segment> vornoidSegments = new ArrayList<>();
    List<Polygon> vornoidPolygons = new ArrayList<>();

    public List<Vertex> generateRandomPoints(int width, int height) {
        Random rand = new Random();
        double x = rand.nextDouble() * width;
        double y = rand.nextDouble() * height;
        randomPoints.add(Vertex.newBuilder().setX((double) x).setY((double) y).build());

        return randomPoints;

    }

    // generate vornoid sites
    public void generateVoronoidSites(List<Vertex> randomPoints) {

        // reset sites each call
        sites.clear();

        // add a coordinate from the points list to the sites collection each time
        for (Vertex rand : randomPoints) {
            sites.add(new Coordinate(rand.getX(), rand.getY()));
        }

        // set the diagram sites and get the diagram
        diagram.setSites(sites);
        voronoiDiagram = diagram.getDiagram(new GeometryFactory());

    }

    // generate vertices for vornoid diagram
    public List<Vertex> generateVornoidVertices() {
        for (int numPolygon = 0; numPolygon < voronoiDiagram.getNumGeometries(); numPolygon++) {

            // get each polygon's coordinates
            Coordinate[] coord = voronoiDiagram.getGeometryN(numPolygon).getCoordinates();

            // iterate through the polygon's coordinates and create vertex's from them
            for (Coordinate c : coord) {
                vornoidVertices.add(Vertex.newBuilder().setX(c.getX()).setY(c.getY()).build());
            }

        }
        System.out.println();
        System.out.println("Voronoi Vertices: " + vornoidVertices);
        System.out.println("SIZE: " + vornoidVertices.size());
        System.out.println();

        return vornoidVertices;

    }

    // generate segments for vornoid diagram
    public List<Segment> generateVornoidSegments() {

        int counter = 0;

        // iterate through each voronoi polygon and get coordinates for each polygon
        for (int numPolygon = 0; numPolygon < voronoiDiagram.getNumGeometries(); numPolygon++) {

            // get each polygon's coordinates
            Coordinate[] coord = voronoiDiagram.getGeometryN(numPolygon).getCoordinates();

            // iterate through the number of coordinates in the current polygon and create
            // segments from them
            for (int i = 0; i < coord.length - 1; i++) {
                vornoidSegments.add(Segment.newBuilder().setV1Idx(counter).setV2Idx(counter + 1).build());
                counter++;
            }

            // connect last vertex to beginning vertex to close polygon
            vornoidSegments.add(Segment.newBuilder().setV1Idx(counter).setV2Idx(counter - (coord.length - 1)).build());

        }

        // System.out.println("SEGMENTS: " + vornoidSegments);

        return vornoidSegments;

    }

    // generate polygons for vornoid diagram
    public List<Polygon> generateVornoidPolygons() {

        int counter = 0;

        // iterate through each voronoi polygon and get coordinates for each polygon
        for (int numPolygon = 0; numPolygon < voronoiDiagram.getNumGeometries(); numPolygon++) {

            // get each polygon's coordinates
            Coordinate[] coord = voronoiDiagram.getGeometryN(numPolygon).getCoordinates();

            // add the current polygon's first segment
            Polygon tempPolygon = Polygon.newBuilder().addSegmentIdxs(counter).build();
            counter++;

            // add segment indexes to the polygon based on how many segments it has

            for (int i = 0; i < coord.length - 1; i++) {
                tempPolygon = Polygon.newBuilder(tempPolygon).addSegmentIdxs(counter).build();
                counter++;
            }

            // for (int i = counter - coord.length + 1; i < counter - 1; i++) {
            // // add a segment to the temp polygon each time
            // tempPolygon = Polygon.newBuilder(tempPolygon).addSegmentIdxs(i + 1).build();
            // }

            // add the polygon to a polygons list
            vornoidPolygons.add(tempPolygon);

        }

        return vornoidPolygons;

    }

    public void generateVornoidNeighbor() {
        for (int poly = 0; poly < vornoidPolygons.size(); poly++) {
            createNeighbour(vornoidPolygons.get(poly), poly);
        }
    }

    @Override
    public Mesh generate(List<Vertex> vornoidVertices, List<Segment> vornoidSegments,
            List<Polygon> vornoidPolygons) {
        return super.generate(vornoidVertices, vornoidSegments, vornoidPolygons);
    }

}
