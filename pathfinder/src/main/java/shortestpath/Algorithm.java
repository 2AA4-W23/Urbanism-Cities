package shortestpath;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.adt.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.adt.Node;

import java.util.*;

public class Algorithm implements Traversal {

    public Algorithm() {
    }

    @Override
    public List<Node> findShortestPath(Graph graph, Node source, Node target) {
        Map<Node, Node> parentMap = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        Set<Node> visited = new HashSet<>();

        // initialize queue and set of visited nodes
        parentMap.put(source, null);
        queue.add(source);
        visited.add(source);

        // iterate through the queue
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            // break if target node is found
            if (node.equals(target)) {
                break;
            }
            // iterate throiugh nodes that current node is connected to
            for (Node neighbor : graph.getAdjacencyNodes(node)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parentMap.put(neighbor, node);
                    queue.add(neighbor);
                }
            }
        }

        // return null if target node is not found
        if (!visited.contains(target)) {
            return null;
        }

        // get shortest path nodes
        List<Node> path = new ArrayList<>();
        Node node = target;
        while (node != null) {
            path.add(0, node);
            node = parentMap.get(node);
        }

        return path;
    }
}
