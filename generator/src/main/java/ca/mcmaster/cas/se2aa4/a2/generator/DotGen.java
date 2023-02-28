package ca.mcmaster.cas.se2aa4.a2.generator;

import java.io.IOException;
import java.util.*;

import ca.VornoidGen;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.triangulate.*;

public class DotGen {

        private int width = 500;
        private int height = 500;

        public Mesh generate() {
                ca.mcmaster.cas.se2aa4.a2.generator.Mesh mesh = new ca.mcmaster.cas.se2aa4.a2.generator.Mesh();
                VornoidGen vornoid = new VornoidGen();
                List<Vertex> randomPoints = new ArrayList<>();

                for (int i = 0; i < 576; i++) {
                        randomPoints = vornoid.generateRandomPoints(width, height);
                }
                vornoid.generateVoronoidSites(randomPoints);

                List<Vertex> vornoidVertices = vornoid.generateVornoidVertices();
                List<Segment> vornoidSegments = vornoid.generateVornoidSegments();
                List<Polygon> vornoidPolygons = vornoid.generateVornoidPolygons();

                // // Creating polygon neighbours
                // int outerloop = 0;
                // for (Polygon shape : mesh.polygons) {
                // mesh.createNeighbour(shape, outerloop);
                // outerloop++;
                // }

                return vornoid.generate(vornoidVertices, vornoidSegments, vornoidPolygons);

        }

}
