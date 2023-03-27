package elevation;

import ca.mcmaster.cas.se2aa4.a3.island.terrain.Biome;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.TileColor;

import java.awt.geom.RectangularShape;
import java.util.Random;

public class Hills implements Elevation{

    private RectangularShape island;
    private String biome;
    private Tile tile;
    private TileColor tileRaise;
    private String[] arcticColors = {"222,247,250", "196,248,255", "176,238,247", "129,236,252", "13,223,255", "41,176,196"};
    private String[] desertColors = {"235,215,155", "224,201,130", "219,193,114", "204,176,94", "189,160,75", "179,145,46"};
    private String[] tropicalColors = {"201,232,179", "172,212,144", "151,194,120", "129,176,95", "110,158,76", "92,138,59"};

    private int random;

    public Hills(RectangularShape island, String biome, Tile tile){
        this.island = island;
        this.biome = biome;
        this.tile = tile;
    }

    public void setBiome(String biome) {
        this.biome = biome;
    }

    @Override
    public String gradient() {
        String colour = "";

        int max = 5;
        int min = 0;

        Random bag = new Random();

        this.random = bag.nextInt(max - min + 1) + min;

        if (island.contains(tile.centroidX, tile.centroidY)) {
            if (biome.equals("arctic")) {
                colour = arcticColors[this.random];
            } else if (biome.equals("desert")) {
                colour = desertColors[this.random];
            } else if (biome.equals("tropical")) {
                colour = tropicalColors[this.random];
            } else if (biome.equals("Rocky")) {
                colour = tileRaise.ROCKY.color;
            } else if (biome.equals("Muddy")) {
                colour = tileRaise.MUDDY.color;
            } else if (biome.equals("Savannah")) {
                colour = tileRaise.SAVANNAH.color;
            } else if (biome.equals("Taiga")) {
                colour = tileRaise.TAIGA.color;
            } else if (biome.equals("Beach")) {
                colour = tileRaise.BEACH.color;
            } else if (biome.equals("Glacier")) {
                colour = tileRaise.GLACIER.color;
            }
        } else {
            colour = tileRaise.OCEAN.color;
        }

        return colour;
    }

    @Override
    public int assignElevation() {

        int elevation = 0;

        int max = 5;
        int min = 0;

        Random bag = new Random();

        this.random = bag.nextInt(max - min + 1) + min;

        if (island.contains(tile.centroidX, tile.centroidY)) {
            if (this.random == 5) {
                elevation = 60;
            } else if (this.random == 4) {
                elevation = 60;
            } else if (this.random == 3) {
                elevation = 40;
            } else if (this.random == 2) {
                elevation = 40;
            } else if (this.random == 1) {
                elevation = 20;
            } else if (this.random == 0) {
                elevation = 20;
            }
        }

        return elevation;
    }
}