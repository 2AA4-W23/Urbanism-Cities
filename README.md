# Urbanism (Assignment #4)

- Author: Mankaran Rooprai

## How to install?

```
user A4 % mvn install clean package
```

It creates a jar file for the pathfinder subproject:

1. `pathfinder/pathfinder.jar` to generate the shortest path between two nodes
2. `island/island.jar` to generate islands

### Run the pathfinder library

```
user A4 % java -jar pathfinder/pathfinder.jar
```

###Pathfinder

The Pathfinder project is a sub-project within this repository that provides an implementation of a graph-based algorithm to find the shortest path between two nodes in a graph. This project contains a Graph ADT that represents nodes and edges and an interface to define the public contract of "finding a path between two nodes."

##Rationale

Pathfinding is a fundamental problem in computer science and is essential in many applications such as navigation systems, network routing, and game AI. Therefore, implementing an efficient algorithm for finding the shortest path between two nodes is a valuable addition to this repository. This project can be extended to provide a variety of pathfinding algorithms and be used in various applications that require pathfinding capabilities.

##Implementation

The Graph ADT is implemented using an adjacency list representation. Each node in the graph is represented by a unique identifier, and edges between nodes are represented using their respective identifiers. The graph also allows nodes and edges to hold attributes such as elevation, city names, or any other relevant information.

The pathfinder3 algorithm is an implementation of Dijkstra's algorithm, which is a popular algorithm for finding the shortest path between two nodes in a graph. The algorithm maintains a priority queue of nodes to visit and computes the shortest path to each node from the starting node. The algorithm terminates when the destination node is reached, or when there are no more nodes left to visit.

##Extending the Library
The Pathfinder project can be extended to include additional pathfinding algorithms, such as A* or BFS, by implementing them as separate classes that implement the same interface as the pathfinder3 class. Users can then choose the algorithm they want to use based on their specific requirements.

Additionally, the Graph ADT can be extended to include additional functionality such as adding or removing nodes and edges, querying attributes of nodes and edges, and more. This would allow the library to be used in a wider range of applications beyond pathfinding.

