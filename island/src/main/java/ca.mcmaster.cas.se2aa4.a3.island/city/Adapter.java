package ca.mcmaster.cas.se2aa4.a3.island.city;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.TileColor;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.adt.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.adt.Node;
import shortestpath.Dijkstra;

import java.util.*;

public class Adapter {

    List<Structs.Vertex> coloredCities = new ArrayList<>();

    public Adapter() {}

    public List<Structs.Segment> run(Structs.Mesh mesh, Map<City, Tile> cityTiles, List<Tile> tileList) {
        Graph graph = new Graph();
        graph = buildGraph(graph, mesh, tileList);
        Map<Integer, Boolean> cityVerticesIdx = calculateCityVertices(mesh.getPolygonsList(), mesh.getSegmentsList(), cityTiles);
        List<Structs.Segment> shortestPath = calculateStarNetwork(graph, cityVerticesIdx, mesh, cityTiles);
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

        return cityVertices;
    }

    private List<Structs.Segment> calculateStarNetwork(Graph graph, Map<Integer, Boolean> cityVertices, Structs.Mesh mesh, Map<City, Tile> cityTiles) {

        List<Structs.Segment> roads = new ArrayList<>();
        TileColor t = null;
        int hub = 0;

        // makes a central hub node
        for (Map.Entry<Integer, Boolean> kv : cityVertices.entrySet()) {
            if (kv.getValue()) {
                hub = kv.getKey();
                break;
            }
        }

        // iterates through the cities
        for (Integer c : cityVertices.keySet()) {
            // calculates the shortest path between the central hub node and the connecting city
            int connectingCity = c;
            List<Node> shortestPath = new Dijkstra().findShortestPath(graph, hub, connectingCity);
            // add the node ID to the roads list
            for (int i = 0; i < shortestPath.size() - 1; i++) {
                Structs.Segment.Builder roadBuilder = Structs.Segment.newBuilder().setV1Idx(shortestPath.get(i).ID()).setV2Idx(shortestPath.get(i+1).ID());
                Structs.Property rgb = Structs.Property.newBuilder()
                        .setKey("rgb_color")
                        .setValue(t.ROAD.color)
                        .build();
                Structs.Property thickness = Structs.Property.newBuilder()
                        .setKey("thickness")
                        .setValue(String.valueOf(3))
                        .build();
                roadBuilder.addProperties(rgb);
                roadBuilder.addProperties(thickness);
                Structs.Segment road = roadBuilder.build();
                roads.add(road);
            }

        }

        this.coloredCities = colorCities(cityVertices, mesh, cityTiles);

        return roads;

    }

    public List<Structs.Vertex> cityColors() {
        return this.coloredCities;
    }

    private List<Structs.Vertex> colorCities(Map<Integer, Boolean> cityVerticesIdx, Structs.Mesh mesh, Map<City, Tile> cityTiles) {
        List<Structs.Vertex> cities = new ArrayList<>();
        TileColor t = null;
        CitySize c = null;
        String citySize = "";

        List<String> sizeMappings = new ArrayList<>();

        for (Map.Entry<City, Tile> kv : cityTiles.entrySet()) {
            if (kv.getKey().citySize().equals(c.LARGE.name())) {
                citySize = c.LARGE.size;
            } else {
                citySize = c.SMALL.size;
            }
            sizeMappings.add(citySize);
        }


        int index = 0;
        for (Map.Entry<Integer, Boolean> kv : cityVerticesIdx.entrySet()) {
            double x = mesh.getVerticesList().get(kv.getKey()).getX();
            double y = mesh.getVerticesList().get(kv.getKey()).getY();
            Structs.Vertex.Builder cityBuilder = Structs.Vertex.newBuilder().setX(x).setY(y);

            String colorValue = kv.getValue() ? t.CAPITAL.color : t.CITY.color;
            String sizeValue = sizeMappings.get(index);

            Structs.Property rgb = Structs.Property.newBuilder()
                    .setKey("rgb_color")
                    .setValue(colorValue)
                    .build();
            Structs.Property size = Structs.Property.newBuilder()
                    .setKey("size")
                    .setValue(String.valueOf(sizeValue))
                    .build();
            cityBuilder.addProperties(rgb);
            cityBuilder.addProperties(size);
            Structs.Vertex city = cityBuilder.build();
            cities.add(city);

            index++;
        }

        return cities;

    }


    private Graph buildGraph(Graph graph, Structs.Mesh mesh, List<Tile> tileList) {

        List<Integer> segmentIdx = new ArrayList<>();
        List<Structs.Segment> segmentsList = new ArrayList<>();

        for (Tile t : tileList) {
            if (t.isIsland()) {
                segmentIdx.addAll(mesh.getPolygonsList().get(t.getID()).getSegmentIdxsList());
            }
        }

        for (Integer s : segmentIdx) {
            segmentsList.add(mesh.getSegments(s));
        }

        // builds the graph using the segment's vertices' indices
        for (Structs.Segment s : segmentsList) {

            graph.registerNode(s.getV1Idx());
            graph.registerNode(s.getV2Idx());

            double v1xcoordinate = mesh.getVertices(s.getV1Idx()).getX();
            double v1ycoordinate = mesh.getVertices(s.getV1Idx()).getY();
            double v2xcoordinate = mesh.getVertices(s.getV2Idx()).getX();
            double v2ycoordinate = mesh.getVertices(s.getV2Idx()).getY();

            // calculate length of segment
            double y = Math.abs(v2ycoordinate - v1ycoordinate);
            double x = Math.abs(v2xcoordinate - v1xcoordinate);

            double distance = Math.hypot(y, x);

            graph.registerEdge(s.getV1Idx(), s.getV2Idx(), distance);

        }

        return graph;
    }
}