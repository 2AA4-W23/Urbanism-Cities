package ca.mcmaster.cas.se2aa4.a3.island.shapes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.geom.Ellipse2D;
import java.awt.geom.RectangularShape;

import static org.junit.jupiter.api.Assertions.*;

public class CircleTest {

    private Circle circle;

    @BeforeEach
    public void setUp() {
        // create a new circle for each test method
        this.circle = new Circle(10, 10, 5);
    }

    @Test
    public void testCalculateCenter() {
        circle.calculateCenter();
        assertEquals(5, circle.centreX);
        assertEquals(5, circle.centreY);
    }

    @Test
    public void testCreateSelf() {
        RectangularShape shape = circle.createSelf();
        assertTrue(shape instanceof Ellipse2D);
        assertEquals(0, shape.getMinX(), 0.001);
        assertEquals(0, shape.getMinY(), 0.001);
        assertEquals(10, shape.getMaxX(), 0.001);
        assertEquals(10, shape.getMaxY(), 0.001);
    }
}
