package shortestpath;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.adt.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.adt.Node;

import java.util.List;

public interface Algorithm {
    public List<Node> findShortestPath(Graph graph, Node source, Node target);
}