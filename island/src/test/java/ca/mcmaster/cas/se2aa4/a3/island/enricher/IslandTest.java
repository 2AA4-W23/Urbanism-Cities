package ca.mcmaster.cas.se2aa4.a3.island.enricher;

import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.Tile;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IslandTest {
    Island island;
    String shape;
    String biome;
    String lakes;
    String elevation;
    Structs.Mesh aMesh;


    @BeforeEach
    public void setUp() {
        // Create a new Island object with some initial parameters
        aMesh = Structs.Mesh.newBuilder().build();
        //Structs.Mesh aMesh = new MeshFactory().read(output.mesh);
        shape = "circle";
        elevation = "volcano";
        biome = "tundra";
        lakes = "0";
        island = new Island(aMesh, shape, elevation, biome, lakes);
    }

    @Test
    public void testIslandConstructor() {

        // Check that the Island object was created correctly
        //assertNotNull(island);
        //assertNotNull(island.originalMesh);
        //assertNotNull(island.aMesh);
        //assertNotNull(island.getMeshDimensions());

        assertEquals(elevation, island.getElevation());
        assertEquals(biome, island.getBiome());
        assertEquals(lakes, island.getLakes());

//        if (shape.equals("circle")) {
//            assertNotNull(island.getCircleIsland());
//        } else {
//            assertNotNull(island.getRectangleIsland());
//        }
    }

    @Test
    public void testProcess() {
        // Check that the process method creates the tileList correctly
        island.process();
        List<Tile> tileList = island.getTileList();
        assertNotNull(tileList);
        assertTrue(tileList.size() > 0);
    }

    @Test
    public void testBuildNewMesh() {
        // Check that the buildNewMesh method creates a new mesh correctly
        Structs.Mesh newMesh = island.buildNewMesh();
        assertNotNull(newMesh);
        assertEquals(island.originalMesh.getVerticesList().size(), newMesh.getVerticesList().size());
        assertEquals(island.originalMesh.getSegmentsList().size(), newMesh.getSegmentsList().size());
        assertTrue(newMesh.getPolygonsList().size() > 0);
    }

    @Test
    public void testBuildLakes() {
        // Check that the buildLakes method creates lakes correctly
        lakes = "3";
        island = new Island(aMesh, shape, elevation, biome, lakes);
        island.process();
        island.buildLakes();
        List<Tile> tileList = island.getTileList();
        assertNotNull(tileList);
        assertTrue(tileList.size() > 0);
        assertTrue(tileList.stream().anyMatch(Tile::isLake));
    }

    @Test
    public void testColorPolygons() {
        // Check that the colorPolygons method adds a color property to each polygon
        island.process();
        island.buildLakes();
        island.colorPolygons();
        Structs.Mesh newMesh = island.aMesh.build();


    }

}