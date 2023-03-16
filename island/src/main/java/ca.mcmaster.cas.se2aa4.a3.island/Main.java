package ca.mcmaster.cas.se2aa4.a3.island;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.configuration.Configuration;
import ca.mcmaster.cas.se2aa4.a3.island.enricher.Enricher;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Configuration config = new Configuration(args);
        System.out.println(config.input() + ", " + config.output() + ", " + config.mode());
        Structs.Mesh aMesh = new MeshFactory().read(config.input());
        Enricher island = new Enricher(aMesh);
        Structs.Mesh newMesh = island.buildNewMesh();
        new MeshFactory().write(newMesh, config.export(Configuration.FILENAME));
    }
}