/**

 The Algorithm interface defines the common behavior for shortest path algorithms.
 */
package shortestpath;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.adt.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.adt.Node;

import java.util.List;

public interface ShortestPathAlgorithm {
    /**
     * Finds the shortest path in the given graph from the source node to the target node.
     *
     * @param graph the graph in which to search for the shortest path
     * @param source the ID of the starting node of the path
     * @param target the ID of the destination node of the path
     * @return the list of nodes that make up the shortest path from source to target
     */
    public List<Node> findShortestPath(Graph graph, int source, int target);
}