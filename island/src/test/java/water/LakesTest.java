package water;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.TileColor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LakesTest {

    private List<Structs.Polygon> polylist;
    private List<Tile> tileList;
    private int numLakes;
    private TileColor lakeColor;

    @BeforeEach
    void setUp() {
        this.polylist = new ArrayList<>();
        this.tileList = new ArrayList<>();
        this.numLakes = 2;
        this.lakeColor = TileColor.LAKE;
    }

    @Test
    void testCreateLakes() {
        Lakes lakes = new Lakes(this.polylist, this.tileList, this.numLakes);
        List<Tile> result = lakes.createLakes();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(0, result.size());
    }

    @Test
    void testSetLakeTiles() {
        Lakes lakes = new Lakes(this.polylist, this.tileList, this.numLakes);
        lakes.setLakeTiles();
        Assertions.assertNotNull(lakes);
    }

}