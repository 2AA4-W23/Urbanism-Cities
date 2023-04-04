package ca.mcmaster.cas.se2aa4.a4.pathfinder.tests.shortestpath.tests;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.adt.Edge;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.adt.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.adt.Node;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import shortestpath.Algorithm;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AlgorithmTest {
    List<Node> testExpected = new ArrayList<>();
    List<Node> testResult;
    Structs.Vertex v0;
    Structs.Vertex v1;
    Structs.Vertex v2;
    Structs.Vertex v3;
    Structs.Vertex v4;
    Structs.Vertex v5;

    Node node0;
    Node node1;
    Node node2;
    Node node3;
    Node node4;
    Node node5;

    Edge edge0;
    Edge edge1;
    Edge edge2;
    Edge edge3;
    Edge edge4;
    Edge edge5;
    Edge edge6;

    Graph g;
    Algorithm a;

    @BeforeEach
    public void setup() {

        Structs.Vertex testVertex1  = Structs.Vertex.newBuilder().setX(40).setY(40).build();
        Structs.Vertex testVertex2  = Structs.Vertex.newBuilder().setX(30).setY(30).build();
        Structs.Vertex testVertex3  = Structs.Vertex.newBuilder().setX(50).setY(50).build();
        testExpected.add(new Node(testVertex1, 4));
        testExpected.add(new Node(testVertex2, 3));
        testExpected.add(new Node(testVertex3, 5));

        v0  = Structs.Vertex.newBuilder().setX(0).setY(0).build();
        v1  = Structs.Vertex.newBuilder().setX(10).setY(10).build();
        v2  = Structs.Vertex.newBuilder().setX(20).setY(20).build();
        v3  = Structs.Vertex.newBuilder().setX(30).setY(30).build();
        v4  = Structs.Vertex.newBuilder().setX(40).setY(40).build();
        v5  = Structs.Vertex.newBuilder().setX(50).setY(50).build();

        node0 = new Node(v0, 0);
        node1 = new Node(v1, 1);
        node2 = new Node(v2, 2);
        node3 = new Node(v3, 3);
        node4 = new Node(v4, 4);
        node5 = new Node(v5, 5);

        edge0 = new Edge(node0, node1);
        edge1 = new Edge(node1, node2);
        edge2 = new Edge(node1, node4);
        edge3 = new Edge(node2, node3);
        edge4 = new Edge(node2, node5);
        edge5 = new Edge(node4, node3);
        edge6 = new Edge(node3, node5);

        g = new Graph();

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

        a = new Algorithm();

        testResult = a.findShortestPath(g, node4, node5);
    }

    @Test
    @DisplayName("Tests shortest path between two nodes")
    public void findShortestPath() {
        assertEquals(testExpected, testResult);
    }

    @Test
    @DisplayName("Tests shortest path between two nodes but expected has an extra node")
    public void findShortestPathFalse() {
        Structs.Vertex testVertex4  = Structs.Vertex.newBuilder().setX(60).setY(60).build();
        testExpected.add(new Node(testVertex4, 6));
        assertNotEquals(testExpected, testResult);
    }

    // boundary condition testing (graph is null)
    @Test
    @DisplayName("Empty graph")
    public void emptyGraph() {
        g.clear();
        testResult = a.findShortestPath(g, node4, node5);
        assertNull(testResult);
    }

    // data inserted should be searchable right after insertion
    @Test
    @DisplayName("Node/edge inserted")
    public void dataInsertion() {
        Node node7 = new Node(Structs.Vertex.newBuilder().setX(70).setY(70).build(), 7);
        g.registerNode(node7);
        g.registerEdge(new Edge(node1, node7));
        testResult = a.findShortestPath(g, node1, node7);
        assertTrue(testResult.contains(node7));
    }

    // data removed should be apparent right after deletion
    @Test
    @DisplayName("Source node removed")
    public void removeSourceNode() {
        g.removeNode(node1);
        testResult = a.findShortestPath(g, node1, node2);
        assertNull(testResult);
    }

    // all paths between two nodes are removed so no path is existent
    @Test
    @DisplayName("Nonexistent path: Nodes in path removed")
    public void removeNodeInPath() {
        g.removeNode(node2);
        g.removeNode(node4);
        testResult = a.findShortestPath(g, node1, node3);
        assertNull(testResult);
    }

    // target node removed
    @Test
    @DisplayName("Target node removed")
    public void removeTargetNode() {
        g.removeNode(node5);
        testResult = a.findShortestPath(g, node1, node5);
        assertNull(testResult);
    }

    // check ordering of shortest path
    @Test
    @DisplayName("Test correct ordering")
    public void correctOrder() {
        boolean ordered = true;
        for (int i = 0; i < testResult.size(); i++) {
            if (!testResult.get(i).equals(testExpected.get(i)) || testExpected.size() != testResult.size()) {
                ordered = false;
            }
        }

        assertTrue(ordered);

    }

    // check what happens when incorrect ordering
    @Test
    @DisplayName("Test incorrect ordering")
    public void incorrectOrder() {
        boolean ordered = true;

        testExpected.remove(1);
        testExpected.add(new Node(Structs.Vertex.newBuilder().setX(30).setY(30).build(), 3));

        for (int i = 0; i < testResult.size(); i++) {
            if (!testResult.get(i).equals(testExpected.get(i)) || testExpected.size() != testResult.size()) {
                ordered = false;
            }
        }

        assertFalse(ordered);

    }

}