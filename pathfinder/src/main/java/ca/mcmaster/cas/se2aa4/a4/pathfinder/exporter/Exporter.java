package ca.mcmaster.cas.se2aa4.a4.pathfinder.exporter;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.adt.Edge;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.adt.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.adt.Node;

import java.util.*;

public class Exporter {

    public Exporter() {}

    public Graph run(List<Structs.Vertex> vertices, List<Structs.Segment> segments) {
        Graph graph = new Graph();
        graph = buildGraph(graph, vertices, segments);
        return graph;
    }

    private Graph buildGraph(Graph graph, List<Structs.Vertex> vertices, List<Structs.Segment> segments) {

        Map<Edge, Integer> edgeIdx = new HashMap<Edge, Integer>();

        int counter = 0;
        for (Structs.Segment s : segments) {
            Structs.Vertex v1 = vertices.get(s.getV1Idx());
            Structs.Vertex v2 = vertices.get(s.getV2Idx());
            Node n1 = new Node(v1, s.getV1Idx());
            Node n2 = new Node(v2, s.getV2Idx());
            graph.registerNode(n1);
            graph.registerNode(n2);
            Edge e = new Edge(n1, n2);
            graph.registerEdge(e);

            edgeIdx.put(e, counter++);
        }

        graph.setEdgeIndices(edgeIdx);

        return graph;
    }
}
