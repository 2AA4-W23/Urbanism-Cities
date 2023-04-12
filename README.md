# Urbanism (Assignment #4)

- Author: Mankaran Rooprai

## How to install?

```
user A4 % mvn install clean package
```

It creates a jar file for the pathfinder subproject:

1. `pathfinder/pathfinder.jar` to generate the shortest path between two nodes
2. `island/island.jar` to generate islands

### Run the island generator with cities

```
user A4 % java -jar island/island.jar -i input.mesh -o island.mesh -s rectangle -b tropical -e hills -l 11 -a 5 -r 11 -soil wet -c 10
```

-c <arg> is the number of citieto be generated on the island. Note that roads cross lakes via bridges.

## Pathfinder

The Pathfinder project is a sub-project within this repository that provides an implementation of a graph-based algorithm to find the shortest path between two nodes in a graph, as well as a general-purpose Graph ADT that represents nodes and edges and an interface to define the public contract of "finding a path between two nodes."

## Rationale

The purpose of this project was to find the shortest path in the city between the hub and every other city. This produces a star network. Therefore, implementing an efficient algorithm for finding the shortest path between two nodes is a valuable addition to this repository. This project can be extended to provide a variety of pathfinding algorithms and be used in various applications that require pathfinding capabilities.

## Implementation

The Graph ADT is implemented using an adjacency list representation. Each node in the graph is represented by a unique identifier, and edges between nodes contain their respective nodes and a weight property. The graph also allows nodes hold attributes such as elevation and city names.

The pathfinder algorithm is an implementation of Dijkstra's algorithm, which is a popular algorithm for finding the shortest path between two nodes in a graph. The algorithm maintains a priority queue of nodes to visit and computes the shortest path to each node from the starting node. The algorithm terminates when the destination node is reached, or when there are no more nodes left to visit.

## Extending the Library
The Pathfinder project can be extended to include additional pathfinding algorithms between a source and target node, by implementing them as separate classes that implement the same pathfinder interface. Users can then choose the algorithm they want to use based on their specific requirements.

Additionally, the Graph ADT can be extended to include additional functionality such as adding or removing nodes and edges, querying attributes of nodes and edges, and more. This would allow the library to be used in a wider range of applications beyond pathfinding.

