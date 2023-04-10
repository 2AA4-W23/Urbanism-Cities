package shortestpath;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.adt.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.adt.Node;

import java.util.*;

public class Dijkstra implements Algorithm {

    public Dijkstra() {
    }

    @Override
    public List<Node> findShortestPath(Graph graph, int startNodeId, int endNodeId) {
        Node startNode = graph.getNode(startNodeId);
        Node endNode = graph.getNode(endNodeId);

        if (startNode == null || endNode == null) {
            return null;
        }

        Map<Node, Double> distances = new HashMap<>();
        Map<Node, Node> previousNodes = new HashMap<>();
        PriorityQueue<Node> queue = new PriorityQueue<>((n1, n2) -> (int) (distances.get(n1) - distances.get(n2)));

        for (Node node : graph.getNodes()) {
            distances.put(node, Double.MAX_VALUE);
            previousNodes.put(node, null);
        }
        distances.put(startNode, 0.0);
        queue.offer(startNode);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            if (currentNode.equals(endNode)) {
                break;
            }

            double currentDistance = distances.get(currentNode);
            List<Node> adjacentNodes = graph.getAdjacencyNodes(currentNode.ID());
            for (Node adjacentNode : adjacentNodes) {
                double edgeWeight = graph.getEdgeWeight(currentNode, adjacentNode);
                double newDistance = currentDistance + edgeWeight;

                if (newDistance < distances.get(adjacentNode)) {
                    distances.put(adjacentNode, newDistance);
                    previousNodes.put(adjacentNode, currentNode);
                    queue.offer(adjacentNode);
                }
            }
        }

        // Check if there is no path between startNode and endNode
        if (distances.get(endNode) == Double.MAX_VALUE) {
            return null;
        }

        List<Node> shortestPath = new ArrayList<>();
        Node currentNode = endNode;
        while (previousNodes.get(currentNode) != null) {
            shortestPath.add(currentNode);
            currentNode = previousNodes.get(currentNode);
        }
        shortestPath.add(startNode);
        Collections.reverse(shortestPath);

        return shortestPath;
    }

}