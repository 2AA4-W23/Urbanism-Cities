package ca.mcmaster.cas.se2aa4.a3.island.shapes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

import static org.junit.jupiter.api.Assertions.*;

public class RectangleTest {

    private Rectangle rectangle;

    @BeforeEach
    public void setUp() {
        // create a new rectangle for each test method
        this.rectangle = new Rectangle(10, 20, 5);
    }

    @Test
    public void testCalculateCenter() {
        rectangle.calculateCenter();
        assertEquals(10, rectangle.centreX);
        assertEquals(5, rectangle.centreY);
    }

    @Test
    public void testCreateSelf() {
        RectangularShape shape = rectangle.createSelf();
        assertTrue(shape instanceof Rectangle2D);
        assertEquals(5, shape.getMinX(), 0.001);
        assertEquals(0, shape.getMinY(), 0.001);
        assertEquals(15, shape.getMaxX(), 0.001);
        assertEquals(10, shape.getMaxY(), 0.001);
    }

}