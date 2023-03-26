package ca.mcmaster.cas.se2aa4.a3.island.terrain;

import org.junit.jupiter.api.Test;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TileTest {

    @Test
    public void testVolcanizer() {
        String biome = "ocean";
        String elevation = "Volcano";
        Rectangle2D islandshape = new Rectangle2D.Double(0, 0, 10, 10);
        double centroidX = 5.0;
        double centroidY = 5.0;
        List<Integer> neighbours = new ArrayList<>();
        int ID = 0;


        Tile tile = new Tile(biome, elevation, islandshape, centroidX, centroidY, neighbours, ID, 0);

        tile.volcanizer();

        assertEquals(2147483647, tile.elevation);
        assertEquals("ocean", tile.biome);

    }

//    @Test
//    public void testFlatlander() {
//        String biome = "plains";
//        String elevation = "Flat Land";
//        Rectangle2D islandshape = new Rectangle2D.Double(0, 0, 10, 10);
//        double centroidX = 5.0;
//        double centroidY = 5.0;
//        List<Integer> neighbours = new ArrayList<>();
//        int ID = 0;
//        int centroidIdx = 0;
//
//        Tile tile = new Tile(biome, elevation, islandshape, centroidX, centroidY, neighbours, ID, centroidIdx);
//
//        tile.flatlander();
//
//        assertEquals(0, tile.elevation);
//        assertEquals("plains", tile.biome);
//
//    }
//
//    @Test
//    public void testHiller() {
//        String biome = "forest";
//        String elevation = "Hills";
//        Rectangle2D islandshape = new Rectangle2D.Double(0, 0, 10, 10);
//        double centroidX = 5.0;
//        double centroidY = 5.0;
//        List<Integer> neighbours = new ArrayList<>();
//        int ID = 0;
//        int centroidIdx = 0;
//
//        Tile tile = new Tile(biome, elevation, islandshape, centroidX, centroidY, neighbours, ID, centroidIdx);
//
//        tile.hiller();
//
//        assertEquals(0, tile.elevation);
//        assertEquals("forest", tile.biome);
//
//    }
}

