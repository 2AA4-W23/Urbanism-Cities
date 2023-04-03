package ca.mcmaster.cas.se2aa4.a4.pathfinder;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.adt.Edge;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.adt.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.adt.Node;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.exporter.Exporter;
import shortestpath.Algorithm;

import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Structs.Vertex v0 = Structs.Vertex.newBuilder().setX(0).setY(0).build();
        Structs.Vertex v1 = Structs.Vertex.newBuilder().setX(10).setY(10).build();
        Structs.Vertex v2 = Structs.Vertex.newBuilder().setX(20).setY(20).build();
        Structs.Vertex v3 = Structs.Vertex.newBuilder().setX(30).setY(30).build();
        Structs.Vertex v4 = Structs.Vertex.newBuilder().setX(40).setY(40).build();
        Structs.Vertex v5 = Structs.Vertex.newBuilder().setX(50).setY(50).build();

        Node node0 = new Node(v0, 0);
        Node node1 = new Node(v1, 1);
        Node node2 = new Node(v2, 2);
        Node node3 = new Node(v3, 3);
        Node node4 = new Node(v4, 4);
        Node node5 = new Node(v5, 5);

        Edge edge0 = new Edge(node0, node1);
        Edge edge1 = new Edge(node1, node2);
        Edge edge2 = new Edge(node1, node4);
        Edge edge3 = new Edge(node2, node3);
        Edge edge4 = new Edge(node2, node5);
        Edge edge5 = new Edge(node4, node3);
        Edge edge6 = new Edge(node3, node5);

        Graph g = new Graph();

        g.registerNode(node0);
        g.registerNode(node1);
        g.registerNode(node2);
        g.registerNode(node3);
        g.registerNode(node4);
        g.registerNode(node5);

        g.registerEdge(edge0);
        g.registerEdge(edge1);
        g.registerEdge(edge2);
        g.registerEdge(edge3);
        g.registerEdge(edge4);
        g.registerEdge(edge5);
        g.registerEdge(edge6);

        Algorithm a = new Algorithm();
        List<Node> yo = a.findShortestPath(g, node4, node5);
        //System.out.println(yo);
        g.printAdjacencyList();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(yo.toArray().toString());

        //Graph graph = new Exporter().run(aMesh.verticesList, aMesh.segmentsList);
    }
}