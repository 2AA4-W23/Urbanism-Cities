package ca.mcmaster.cas.se2aa4.a3.island;


import ca.mcmaster.cas.se2aa4.a3.island.dimensions.BoundsTest;
import ca.mcmaster.cas.se2aa4.a3.island.dimensions.Dimensons;
import ca.mcmaster.cas.se2aa4.a3.island.enricher.IslandTest;
import ca.mcmaster.cas.se2aa4.a3.island.enricher.Sandbox;
import ca.mcmaster.cas.se2aa4.a3.island.enricher.SandboxTest;
import ca.mcmaster.cas.se2aa4.a3.island.shapes.CircleTest;
import ca.mcmaster.cas.se2aa4.a3.island.shapes.RectangleTest;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.TileTest;
import elevation.Volcano;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        IslandTest.class,
        SandboxTest.class,
        BoundsTest.class,
        Dimensons.class,
        CircleTest.class,
        RectangleTest.class,
        TileTest.class,
        Volcano.class

})

public class TestSuite {
}
