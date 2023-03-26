package water;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.TileColor;
import org.junit.jupiter.api.Test;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RiversTest {

    @Test
    public void testCreateRivers() {
        // create some test data
        List<Structs.Polygon> polylist = new ArrayList<>();
        List<Tile> tileList = new ArrayList<>();
        String biome = "ocean";
        String elevation = "Volcano";
        Rectangle2D islandshape = new Rectangle2D.Double(0, 0, 10, 10);
        double centroidX = 5.0;
        double centroidY = 5.0;
        List<Integer> neighbours = new ArrayList<>();
        int ID = 0;
        tileList.add(new Tile(biome,elevation,islandshape,centroidX,centroidY,neighbours,ID,0));
        tileList.add(new Tile(biome,elevation,islandshape,centroidX,centroidY,neighbours,ID,0));
        int numRivers = 2;

        // create a new Rivers object
        Rivers rivers = new Rivers(polylist, tileList, numRivers);

        // call the createRivers method and check the result
        List<Structs.Segment> riverSegments = rivers.createRivers();
        assertEquals(numRivers, riverSegments.size());
    }

}