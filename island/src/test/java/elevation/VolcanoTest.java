package elevation;

import ca.mcmaster.cas.se2aa4.a3.island.terrain.Tile;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


class VolcanoTest {

    private Volcano volcano;

    @BeforeEach
    public void setUp() {
        Rectangle2D.Double island = new Rectangle2D.Double(0, 0, 100, 100);
        String biome = "Arctic";
        Tile tile = new Tile(biome, "volcano", island, 5,5, Arrays.asList(1, 2, 3), 1,1);
        this.volcano = new Volcano(island, biome, tile);
    }

    @Test
    public void testGradientArctic() {
        this.volcano.biome = "Arctic";
        String r = "41";
        String g = "176";
        String b = "196";
        String result = this.volcano.gradient();
        String[] resultValues = result.split(",");
        int resultR = Integer.parseInt(resultValues[0]);
        int resultG = Integer.parseInt(resultValues[1]);
        int resultB = Integer.parseInt(resultValues[2]);
        int expectedR = Integer.parseInt(r);
        int expectedG = Integer.parseInt(g);
        int expectedB = Integer.parseInt(b);

        assertTrue(resultR > expectedR);
        assertTrue(resultG > expectedG);
        assertTrue(resultB == expectedB);
    }

    @Test
    public void testGradientDesert() {
        this.volcano.biome = "Desert";
        String r = "179";
        String g = "145";
        String b = "46";
        String result = this.volcano.gradient();
        String[] resultValues = result.split(",");
        int resultR = Integer.parseInt(resultValues[0]);
        int resultG = Integer.parseInt(resultValues[1]);
        int resultB = Integer.parseInt(resultValues[2]);
        int expectedR = Integer.parseInt(r);
        int expectedG = Integer.parseInt(g);
        int expectedB = Integer.parseInt(b);
        assertTrue(resultR == expectedR);
        assertTrue(resultG > expectedG);
        assertTrue(resultB > expectedB);
    }

    @Test
    public void testGradientTropical() {
        this.volcano.biome = "Tropical";
        String r = "92";
        String g = "138";
        String b = "59";
        String result = this.volcano.gradient();
        String[] resultValues = result.split(",");
        int resultR = Integer.parseInt(resultValues[0]);
        int resultG = Integer.parseInt(resultValues[1]);
        int resultB = Integer.parseInt(resultValues[2]);
        int expectedR = Integer.parseInt(r);
        int expectedG = Integer.parseInt(g);
        int expectedB = Integer.parseInt(b);
        assertTrue(resultR > expectedR);
        assertTrue(resultG == expectedG);
        assertTrue(resultB > expectedB);
    }


}