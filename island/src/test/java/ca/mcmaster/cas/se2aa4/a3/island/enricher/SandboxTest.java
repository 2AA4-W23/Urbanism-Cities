package ca.mcmaster.cas.se2aa4.a3.island.enricher;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class SandboxTest {

    private Sandbox sandbox;

    @BeforeEach
    void setUp() {
        // add vertices and segments to meshBuilder
        Structs.Mesh mesh = Structs.Mesh.newBuilder().build();
        sandbox = new Sandbox(mesh, "Volcano");
    }

    @Test
    void testProcess() {
        // test the process method
        sandbox.process();
        assertNotNull(sandbox.aMesh);
    }

    @Test
    void testBuildNewMesh() {
        // test the buildNewMesh method
        Structs.Mesh newMesh = sandbox.buildNewMesh();
        assertNotNull(newMesh);
    }
}



