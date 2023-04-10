package ca.mcmaster.cas.se2aa4.a4.pathfinder.tests.shortestpath.tests;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.adt.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.adt.Node;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import shortestpath.Dijkstra;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AlgorithmTest {
    List<Node> testExpected = new ArrayList<>();
    List<Node> testResult;

    Graph g;
    Dijkstra a;

    @BeforeEach
    public void setup() {
        testExpected.add(new Node(4));
        testExpected.add(new Node(3));
        testExpected.add(new Node(5));

        g = new Graph();

        g.registerNode(0);
        g.registerNode(1);
        g.registerNode(2);
        g.registerNode(3);
        g.registerNode(4);
        g.registerNode(5);

        g.registerEdge(0,1,0);
        g.registerEdge(1,2,0);
        g.registerEdge(1,4,0);
        g.registerEdge(2,3,0);
        g.registerEdge(2,5,0);
        g.registerEdge(4,3,0);
        g.registerEdge(3,5,0);

        a = new Dijkstra();
        testResult = a.findShortestPath(g, 4, 5);
    }

    @Test
    @DisplayName("Tests shortest path between two nodes")
    public void findShortestPath() {
        assertEquals(testExpected, testResult);
    }

    @Test
    @DisplayName("Tests shortest path between two nodes but expected has an extra node")
    public void findShortestPathFalse() {
        testExpected.add(new Node(6));
        assertNotEquals(testExpected, testResult);
    }

    // boundary condition testing (graph is null)
    @Test
    @DisplayName("Empty graph")
    public void emptyGraph() {
        g.clear();
        testResult = a.findShortestPath(g, 4, 5);
        assertNull(testResult);
    }

    // data inserted should be searchable right after insertion
    @Test
    @DisplayName("Node/edge inserted")
    public void dataInsertion() {
        //Node node7 = new Node(7);
        g.registerNode(7);
        g.registerEdge(1,7,0);
        testResult = a.findShortestPath(g, 1,7);
        assertTrue(testResult.contains(g.getNode(7)));
    }

    // data removed should be apparent right after deletion
    @Test
    @DisplayName("Source node removed")
    public void removeSourceNode() {
        g.removeNode(1);
        testResult = a.findShortestPath(g, 1, 2);
        assertNull(testResult);
    }

    // all paths between two nodes are removed so no path is existent
    @Test
    @DisplayName("Nonexistent path: Nodes in path removed")
    public void removeNodeInPath() {
        g.removeNode(2);
        g.removeNode(4);
        testResult = a.findShortestPath(g, 1, 3);
        assertNull(testResult);
    }

    // target node removed
    @Test
    @DisplayName("Target node removed")
    public void removeTargetNode() {
        g.removeNode(5);
        testResult = a.findShortestPath(g, 1, 5);
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
//
//    // check what happens when incorrect ordering
    @Test
    @DisplayName("Test incorrect ordering")
    public void incorrectOrder() {
        boolean ordered = true;

        testExpected.remove(1);
        testExpected.add(new Node(3));

        for (int i = 0; i < testResult.size(); i++) {
            if (!testResult.get(i).equals(testExpected.get(i)) || testExpected.size() != testResult.size()) {
                ordered = false;
            }
        }

        assertFalse(ordered);

    }

}