package ca.mcmaster.cas.se2aa4.a3.island.dimensions;

import ca.mcmaster.cas.se2aa4.a3.island.shapes.Circle;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.Lagoon;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.Ocean;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.TileColor;
import org.junit.jupiter.api.Test;
import org.junit.Assert;
import org.junit.Before;


import java.awt.geom.Ellipse2D;

import static org.junit.jupiter.api.Assertions.*;

public class BoundsTest {

    @Test
    public void testSetOceanBounds() {
        Bounds bounds = new Bounds();
        Circle circle = new Circle(0, 0, 5);
        bounds.setOceanBounds(circle);
        assertNotNull(bounds.ocean);
    }

    @Test
    public void testSetLandBounds() {
        Bounds bounds = new Bounds();
        Circle circle = new Circle(0, 0, 5);
        bounds.setLandBounds(circle);
        assertNotNull(bounds.land);
    }

    @Test
    public void testSetLagoonBounds() {
        Bounds bounds = new Bounds();
        Circle circle = new Circle(0, 0, 5);
        bounds.setLagoonBounds(circle);
        assertNotNull(bounds.lagoon);
    }




}



