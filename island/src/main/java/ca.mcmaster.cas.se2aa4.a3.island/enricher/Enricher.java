package ca.mcmaster.cas.se2aa4.a3.island.enricher;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.List;

public interface Enricher {
    void process();
    Structs.Mesh buildNewMesh();
}
