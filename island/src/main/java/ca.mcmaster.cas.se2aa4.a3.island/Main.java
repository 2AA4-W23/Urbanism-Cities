package ca.mcmaster.cas.se2aa4.a3.island;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.configuration.Configuration;
import ca.mcmaster.cas.se2aa4.a3.island.enricher.MeshEnricher;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Configuration config = new Configuration(args);
        Structs.Mesh aMesh = new MeshFactory().read(config.input());
        Structs.Mesh island = new MeshEnricher(aMesh, config.elevation()).buildNewMesh();
        if(config.export().containsKey(Configuration.MODE)) {
            island = new MeshEnricher(aMesh, config.elevation()).buildNewMesh();
        }
        new MeshFactory().write(island, config.export(Configuration.FILENAME));
    }
}