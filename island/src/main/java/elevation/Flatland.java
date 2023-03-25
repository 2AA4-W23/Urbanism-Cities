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
            if (biome.equals("Arctic")) {
                colour = tileRaise.ARCTIC.color;
            } else if (biome.equals("Desert")) {
                colour = tileRaise.DESERT.color;
            } else if (biome.equals("Tropical")) {
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
    public void temperature() {

    }

    @Override
    public int assignElevation() {

        int elevation = 0;

        if (this.island.contains(tile.centroidX, tile.centroidY)) {
            elevation = 60;
        }

//        if (island.contains(tile.centroidX, tile.centroidY)) {
//            if (biome.equals("Arctic")) {
//                elevation = 1000;
//            } else if (biome.equals("Desert")) {
//                elevation = 100;
//            } else if (biome.equals("Tropical")) {
//                elevation = 500;
//            }
//        }

        return elevation;
    }
}