package shortestpath;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.adt.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.adt.Node;

import java.util.*;

public class Dijkstra implements ShortestPathAlgorithm {

    public Dijkstra() {
    }

    // Implementing the Algorithm interface method to find the shortest path in the graph
    @Override
    public List<Node> findShortestPath(Graph graph, int startNodeId, int endNodeId) {

        // Get start and end node objects from graph using startNodeId and endNodeId
        Node startNode = graph.getNode(startNodeId);
        Node endNode = graph.getNode(endNodeId);

        // Check if start and end nodes exist in graph, if not return null
        if (startNode == null || endNode == null) {
            return null;
        }

        // Create data structures for distance, previous node, and priority queue for Dijkstra's algorithm
        Map<Node, Double> distances = new HashMap<>();
        Map<Node, Node> previousNodes = new HashMap<>();
        PriorityQueue<Node> queue = new PriorityQueue<>((n1, n2) -> (int) (distances.get(n1) - distances.get(n2)));

        // Initialize all nodes' distances to infinity and previous nodes to null
        for (Node node : graph.getNodes()) {
            distances.put(node, Double.MAX_VALUE);
            previousNodes.put(node, null);
        }

        // Set the distance of the start node to 0 and add it to the priority queue
        distances.put(startNode, 0.0);
        queue.offer(startNode);

        // Iterate through the priority queue until it is empty
        while (!queue.isEmpty()) {
            // Get the node with the smallest distance from the start node
            Node currentNode = queue.poll();

            // Break out of the loop if the current node is the end node
            if (currentNode.equals(endNode)) {
                break;
            }

            // Update the distance of the adjacent nodes if there is a shorter path to them
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

        // Build the shortest path from source to target
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