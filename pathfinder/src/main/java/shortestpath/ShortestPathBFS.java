package shortestpath;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.adt.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.adt.Node;

import java.util.*;

public class ShortestPathBFS implements Algorithm {

    public ShortestPathBFS() {
    }

    @Override
    public List<Node> findShortestPath(Graph graph, int source, int target) {

        Node sourceNode = graph.getNode(source);
        Node targetNode = graph.getNode(target);

        if (sourceNode == null || targetNode == null) {
            return null;
        }

        Map<Node, Node> parentMap = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        Set<Node> visited = new HashSet<>();

        // initialize queue and set of visited nodes
        parentMap.put(sourceNode, null);
        queue.add(sourceNode);
        visited.add(sourceNode);

        // iterate through the queue
        while (!queue.isEmpty() && !graph.isEmpty()) {
            Node node = queue.poll();
            // break if target node is found
            if (node.equals(target)) {
                break;
            }
            // iterate through nodes that current node is connected to
            for (Node neighbor : graph.getAdjacencyNodes(node.ID())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parentMap.put(neighbor, node);
                    queue.add(neighbor);
                }
            }
        }

        // return null if target node is not found
        if (!visited.contains(targetNode)) {
            return null;
        }

        // get shortest path nodes
        List<Node> path = new ArrayList<>();
        Node node = targetNode;
        while (node != null) {
            path.add(0, node);
            node = parentMap.get(node);
        }

        return path;
    }
}