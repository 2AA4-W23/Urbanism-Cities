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

    @Override
    public String gradient() {
        String colour = "";
        if (island.contains(tile.centroidX, tile.centroidY)) {
            if (biome.equals("Arctic")) {
                colour = tileRaise.ARCTIC.color;
            } else if (biome.equals("Desert")) {
                colour = tileRaise.DESERT.color;
            } else if (biome.equals("Tropical")) {
                colour = tileRaise.TROPICAL.color;
            }
        } else {
            colour = tileRaise.OCEAN.color;
        }

        return colour;
    }

    @Override
    public void temperature() {

    }

    @Override
    public int assignElevation() {

        int elevation = 0;

        if (island.contains(tile.centroidX, tile.centroidY)) {
            if (biome.equals("Arctic")) {
                elevation = 1000;
            } else if (biome.equals("Desert")) {
                elevation = 100;
            } else if (biome.equals("Tropical")) {
                elevation = 500;
            }
        }

        return elevation;
    }
}