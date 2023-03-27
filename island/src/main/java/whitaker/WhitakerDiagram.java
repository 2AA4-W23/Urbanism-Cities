package whitaker;

import ca.mcmaster.cas.se2aa4.a3.island.terrain.Arctic;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.Beach;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.Biome;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.Desert;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.Glacier;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.Muddy;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.Rocky;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.Savannah;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.Taiga;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.Tropical;

public class WhitakerDiagram {

    private Biome subBiome;

    public WhitakerDiagram() {
    }

    public String determineSubBiome(int elevation, int humidity, String biome) {

        if (biome.equals("arctic")) {
            if (elevation >= 60 && humidity >= 10) { //highest elevation and water
                this.subBiome = new Glacier();
            } else if (elevation >= 60 && humidity < 10) { //highest elevation and no water
                this.subBiome = new Arctic();
            } else if (elevation >= 30 || humidity >= 10) { //not highest elevation and water makes a taiga forest
                this.subBiome = new Taiga();
            } else { // not highest elevation and no water makes it rocky
                this.subBiome = new Rocky();
            }
        } else if (biome.equals("desert")) {
            if (humidity >= 10) { //highest elevation and water
                this.subBiome = new Taiga();
            } else if (elevation >= 60) { //highest elevation and no water
                this.subBiome = new Desert();
            } else if (elevation >= 30) { //not highest elevation and water makes a taiga forest
                this.subBiome = new Savannah();
            } else { // not highest elevation and no water makes it rocky
                this.subBiome = new Beach();
            }
        } else if (biome.equals("tropical")) {
            if (humidity >= 10) {
                this.subBiome = new Muddy();
            } else if (elevation >= 60) { //highest elevation and water
                this.subBiome = new Rocky();
            } else if (elevation >= 30) { //highest elevation and no water
                this.subBiome = new Tropical();
            } else if (elevation >= 0) { //not highest elevation and water makes a taiga forest
                this.subBiome = new Beach();
            } else { // not highest elevation and no water makes it rocky
                this.subBiome = new Beach();
            }
        }

        return this.subBiome.getColour();
    }
}
