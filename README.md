# Terrain Generator (Assignment #3)

- Author: Mankaran Rooprai, Mujtaba Zaidi, Amaan Khakiani

## How to install?
## NOTE: TESTS COMPILE/EXECUTE WHEN THE BELOW COMMAND IS CALLED. THEY DO NOT WORK SEPARATELY - I.E. YOU CANNOT CALL TESTSUITE SEPARATELY.

```
user A3 % mvn install clean package
```

It creates three jars:

1. `generator/generator.jar` to generate meshes
2. `island/island.jar` to generate islands
3. `visualizer/visualizer.jar` to visualize such meshes as SVG files

### Generate an irregular mesh

```
user A3 % java -jar generator/generator.jar -k irregular -h 1080 -w 1920 -p 1000 -s 20 -o input.mesh
```

One can run the generator with `-help` as option to see the different command line arguments that are available

### Visualizing an island, (sandbox mode)

```
user A3 % java -jar island/island.jar -i input.mesh -o lagoon.mesh -mode lagoon 
```

### Visualizing an island with various parameters, (island mode). Examples of execution.

```
user A3 % java -jar island/island.jar -i input.mesh -o island.mesh -s circle -b arctic -e hills -l 11 -a 5 -r 11 -soil dry
user A3 % java -jar island/island.jar -i input.mesh -o island.mesh -s rectangle -b arctic -e flatland -l 11 -a 5 -r 11 -soil wet
user A3 % java -jar island/island.jar -i input.mesh -o island.mesh -s circle -b desert -e hills -l 11 -a 5 -r 11 -soil dry
user A3 % java -jar island/island.jar -i input.mesh -o island.mesh -s circle -b arctic -e volcano -l 11 -a 5 -r 11 -soil dry
user A3 % java -jar island/island.jar -i input.mesh -o island.mesh -s rectangle -b desert -e flatland -l 11 -a 5 -r 11 -soil dry
user A3 % java -jar island/island.jar -i input.mesh -o island.mesh -s circle -b tropical -e hills -l 11 -a 5 -r 11 -soil wet
user A3 % java -jar island/island.jar -i input.mesh -o island.mesh -s rectangle -b tropical -e flatland -l 11 -a 5 -r 11 -soil wet
```

### Command line arguments that can be used with java -jar island/island.jar (not for sandbox mode)

```
-i        <arg>      Input mesh filename
-o        <arg>      Output mesh filename
-s        <arg>      Shape (circle, rectangle)
-b        <arg>      Biome type (arctic, desert, circle)
-e        <arg>      Elevation (volcano, hills, flatland)
-l        <arg>      Number of lakes
-a        <arg>      Number of aquifiers
-r        <arg>      Number of rivers
-soil     <arg>      Type of soil (wet, dry)
-seed     <arg>      Seed to regenerate island
```

### Seed regeneration. Each time an island is produced (not including sandbox mode), it generates a seed which the user can call to regenerate the exact same island. In the example below, the seed of the island to be regenerated is 232258.

```
user A3 % java -jar island/island.jar -i input.mesh -o island.mesh -seed 232258
```

### Command to run visualizer subproject once island mesh is generated:
```
java -jar visualizer/visualizer.jar -i island.mesh -o island.svg
```

| Feature ID | Feature Description | Feature Implementer | Week to be Implemented |
| :-:  | ---       | :-:     | :-:       |
| F01 | Create Shape - abstract class which shapes extend to (rectangle, circle, etc.). Implement command line argument “--shape" | Mankaran | Week 2 |
| F02 | Create TerrainType class to implement water and land tiles | Mankaran | Week 2
| F03 | Implement circle composed of land only | Mankaran | Week 2
| F04 | Implement inner circle which should be lagoon water (create a water interface to differentiate between ocean water and lagoon)| Mankaran | Week 2
| F05 | Ensure tiles between inner and outer circle are land tiles | Mankaran | Week 2
| F06 | Implement beach tiles which are ones touching the water at the edge of the inner circle (lagoon) and the edge of the outer circle (ocean)| Mankaran | Week 2
| F07 | Create the elevation interface. Implement “--altitude” CLA. | Amaan | Week 3
| F08 | Lakes contain humidity and implements elevation and water. Land depends on its nearby lake’s humidity. Implement “--lakes” CLA. | Mankaran | Week 3
| F09 | Implement rivers that start at a vertex and follow edges until they reach the lowest possible point or the ocean. Rivers implement elevation. | Mankaran | Week 3
| F10 | For rivers, if the lowest point is a tile, this forms and endorheic lake. | Mankaran | Week 3
| F11 | Rivers bring humidity to lands around them. Land depends on its nearby river’s humidity | Mankaran | Week 3
| F12 | Implement emergence of two rivers to become one. Each river has a discharge level. | Mankaran | Week 3
| F13 | Combine moisture (humidity) that combined river brings to its surrounding land. | Mankaran | Week 3
| F14 | Aquifers are underground tiles. Implements water. Implement “--aquifers” CLA. | Mujtaba | Week 3
| F15 | Aquifers bring moisture (humidity) to surrounding tiles. | Mujtaba | Week 3
| F16 | Soil absorbs humidity from nearby water sources (lakes, rivers, aquifers). Implement “--soil” CLA. | Mujtaba | Week 3
| F17 | The soil absorption depends on the distance from that soil tile to the nearest source of water. Soil depends on lakes, rivers, aquifers. | Mujtaba | Week 3
| F18 | Compute the amount of remaining water from a nearby source of water that the soil tile uses. | Mujtaba | Week 3
| F19 | Implement biomes, which depend on whitaker diagrams. | Mujtaba | Week 4
| F20 | Biomes depend on temperature → temperature uses elevation to determine coldness/hotness. | Mujtaba | Week 4
| F21 | Biome is assigned to each tile, represented by different colours. | Mujtaba | Week 4
| F22 | Implement whitaker diagram: depends on humidity, elevation, temperature. Implement “--biomes” CLA. | Mujtaba | Week 4
| F23 | Biome depends on whitaker diagram. | Mujtaba | Week 4
| F24 | Each tile depends on biome. | Mujtaba | Week 4
| F25 | Implement a seed for each island generated (similar to a hash value). Implement “--seed” CLA. | Mankaran | Week 4
| F26 | Island seed will be stored so the user can regenerate the island. | Mankaran | Week 4
| F27 | Test the Island class | Amaan | Week 4
| F28 | Test the Sandbox class | Amaan | Week 4
| F29 | Test the Waters like rivers and lakes class | Amaan | Week 4
| F30 | Test the Bounds and Dimensions class | Amaan | Week 4
| F31 | Test the Shapes classes like circle and rectangles | Amaan | Week 4
| F32 | Test the Sandbox class | Amaan | Week 4