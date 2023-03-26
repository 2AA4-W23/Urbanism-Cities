package ca.mcmaster.cas.se2aa4.a3.island.dimensions;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DimensonsTest {

    private List<Structs.Vertex> vertices;

    @BeforeEach
    public void setUp() {
        // create some sample vertices for testing
        this.vertices = new ArrayList<>();
//        vertices.add(new Structs.Vertex(0, 0));
//        vertices.add(new Structs.Vertex(2, 1));
//        vertices.add(new Structs.Vertex(3, 2));
//        vertices.add(new Structs.Vertex(1, 3));
    }

    @Test
    public void testDimensionsWithEmptyList() {
        List<Structs.Vertex> emptyList = new ArrayList<>();
        Dimensons dimensions = new Dimensons(emptyList);
        assertEquals(0, dimensions.width());
        assertEquals(0, dimensions.height());
    }

    @Test
    public void testDimensionsWithNonEmptyList() {
        Dimensons dimensions = new Dimensons(this.vertices);
        assertEquals(3, dimensions.width());
        assertEquals(3, dimensions.height());
    }

}

