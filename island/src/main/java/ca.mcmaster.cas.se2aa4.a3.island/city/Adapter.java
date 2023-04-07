package ca.mcmaster.cas.se2aa4.a3.island.city;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.TileColor;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.adt.Edge;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.adt.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.adt.Node;
import shortestpath.ShortestPathBFS;

import java.util.*;

public class Adapter {

    public Adapter() {}

    public List<Structs.Segment> run(Structs.Mesh mesh, Map<City, Tile> cityTiles) {
        Graph graph = new Graph();
        graph = buildGraph(graph, mesh.getSegmentsList());
        Map<Integer, Boolean> cityVerticesIdx = calculateCityVertices(mesh.getPolygonsList(), mesh.getSegmentsList(), cityTiles);
        List<Structs.Segment> shortestPath = calculateStarNetwork(graph, cityVerticesIdx);
//        List<Structs.Vertex> coloredCities = colorCities(graph, cityVerticesIdx, mesh);
        return shortestPath;
    }

    private Map<Integer, Boolean> calculateCityVertices(List<Structs.Polygon> polygonsList, List<Structs.Segment> segments, Map<City, Tile> cities) {
        Map<Integer, Boolean> cityVertices = new HashMap<>();

        // go through each city, get a segment's vertex, and that is the city
        for (Map.Entry<City, Tile> kv : cities.entrySet()) {
            for (Integer s : polygonsList.get(kv.getValue().getID()).getSegmentIdxsList()) {
                int cityVertex = segments.get(s).getV1Idx();
                cityVertices.put(cityVertex, kv.getKey().isCapital());
                break;
            }
        }

        System.out.println("CITY VERTICES: " + cityVertices);

        return cityVertices;
    }

    private List<Structs.Segment> calculateStarNetwork(Graph graph, Map<Integer, Boolean> cityVertices) {

        List<Structs.Segment> roads = new ArrayList<>();
        TileColor t = null;
        Node hub = null;

        // makes a central hub node
        for (Map.Entry<Integer, Boolean> kv : cityVertices.entrySet()) {
            if (kv.getValue()) {
                hub = graph.getNode(kv.getKey());
                break;
            }
        }

        // iterates through the cities
        for (Integer c : cityVertices.keySet()) {
            // calculates the shortest path between the central hub node and the connecting city
            Node connectingCity = graph.getNode(c);
            List<Node> shortestPath = new ShortestPathBFS().findShortestPath(graph, hub, connectingCity);
            System.out.println("SHORTEST PATH: " + shortestPath);
            // add the node ID to the roads list
            for (int i = 0; i < shortestPath.size() - 1; i++) {
                Structs.Segment.Builder roadBuilder = Structs.Segment.newBuilder().setV1Idx(shortestPath.get(i).ID()).setV2Idx(shortestPath.get(i+1).ID());
                Structs.Property rgb = Structs.Property.newBuilder()
                        .setKey("rgb_color")
                        .setValue(t.ROAD.color)
                        .build();
                Structs.Property thickness = Structs.Property.newBuilder()
                        .setKey("thickness")
                        .setValue(String.valueOf(3f))
                        .build();
                roadBuilder.addProperties(rgb);
                roadBuilder.addProperties(thickness);
                Structs.Segment road = roadBuilder.build();
                roads.add(road);
            }

        }

        return roads;

    }

//    public List<Structs.Vertex> colorCities(Graph graph, Map<Integer, Boolean> cityVerticesIdx, Structs.Mesh mesh) {
//        for (Map.Entry<Integer, Boolean> kv : cityVerticesIdx.entrySet()) {
//            if (kv.getValue()) {
//                double x = mesh.getVerticesList().get(kv.getKey()).getX();
//                double y = mesh.getVerticesList().get(kv.getKey()).getY();
//                Structs.Vertex.Builder cityBuilder = Structs.Vertex.newBuilder().setX(kv);
//                Structs.Property rgb = Structs.Property.newBuilder()
//                        .setKey("rgb_color")
//                        .setValue(t.ROAD.color)
//                        .build();
//                Structs.Property thickness = Structs.Property.newBuilder()
//                        .setKey("thickness")
//                        .setValue(String.valueOf(3f))
//                        .build();
//            }
//        }
//    }


    private Graph buildGraph(Graph graph, List<Structs.Segment> segments) {

        // builds the graph using the segment's vertices' indices
        for (Structs.Segment s : segments) {

            int n1Idx = s.getV1Idx();
            int n2Idx = s.getV2Idx();

            Node n1 = graph.getNode(n1Idx);
            if (n1 == null) {
                n1 = new Node(n1Idx);
                graph.registerNode(n1);
            }

            Node n2 = graph.getNode(n2Idx);
            if (n2 == null) {
                n2 = new Node(n2Idx);
                graph.registerNode(n2);
            }

            Edge e = new Edge(n1, n2);
            graph.registerEdge(e);
            if (n1Idx == 2917) {
                System.out.println("adjacnency list 2917 (n1): " + graph.getAdjacencyNodes(n1));
            } else if (n2Idx == 2917) {
                System.out.println("adjacnency list 2917 (n2): " + graph.getAdjacencyNodes(n2));
            }
        }

        return graph;
    }
}