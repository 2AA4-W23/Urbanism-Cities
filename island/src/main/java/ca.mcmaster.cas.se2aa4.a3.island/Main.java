package ca.mcmaster.cas.se2aa4.a3.island;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.configuration.Configuration;
import ca.mcmaster.cas.se2aa4.a3.island.enricher.Island;
import ca.mcmaster.cas.se2aa4.a3.island.enricher.Sandbox;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Configuration config = new Configuration(args);
        Structs.Mesh aMesh = new MeshFactory().read(config.input());
        // the two below commands will be called once the biome situation is figured out
        //Buildable specification = SpecificationFactory.create(config);
        //IslandCreator theIsland = specification.build();
        //Island island = new Island(config.shape());
        Structs.Mesh island = new Sandbox(aMesh, config.elevation()).buildNewMesh();
        if(config.export().containsKey(Configuration.MODE)) {
            island = new Sandbox(aMesh, config.elevation()).buildNewMesh();
        }
        new MeshFactory().write(island, config.export(Configuration.FILENAME));
    }
}