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
        String biome = "arctic";
        String elevation = "volcano";
        Rectangle2D islandshape = new Rectangle2D.Double(0, 0, 10, 10);
        double centroidX = 5.0;
        double centroidY = 5.0;
        List<Integer> neighbours = new ArrayList<>();
        int ID = 0;


        Tile tile = new Tile(biome, islandshape, centroidX, centroidY, neighbours, ID, 0, "dry");

        tile.volcanizer();

        assertEquals(2147483647, tile.elevation);
        assertEquals("arctic", tile.biome);

    }

}

