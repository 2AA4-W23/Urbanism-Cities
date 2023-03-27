package elevation;

import ca.mcmaster.cas.se2aa4.a3.island.terrain.Biome;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.TileColor;

import java.awt.geom.RectangularShape;

public class Flatland implements Elevation{

    private RectangularShape island;
    private String biome;
    private Tile tile;
    private TileColor tileRaise;

    public Flatland(RectangularShape island, String biome, Tile tile){
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
        if (island.contains(tile.centroidX, tile.centroidY)) {
            if (biome.equals("arctic")) {
                colour = tileRaise.ARCTIC.color;
            } else if (biome.equals("desert")) {
                colour = tileRaise.DESERT.color;
            } else if (biome.equals("tropical")) {
                colour = tileRaise.TROPICAL.color;
            }  else if (biome.equals("Rocky")) {
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

        if (this.island.contains(tile.centroidX, tile.centroidY)) {
            elevation = 60;
        }

        return elevation;
    }
}