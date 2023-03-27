package elevation;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.enricher.Island;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.Biome;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.TileColor;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.util.StringUtil;
import whitaker.WhitakerDiagram;

import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

public class Volcano implements Elevation{
    private RectangularShape island;
    public String biome;
    private Tile tile;
    private TileColor tileRaise;
    public Volcano(RectangularShape island, String biome, Tile tile){
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
                String[] colourArray = colour.split(",");
                colourArray[1] = String.valueOf(Math.min((int) (Integer.parseInt(colour.split(",")[1]) + Math.hypot(island.getCenterY()- tile.centroidY, island.getCenterX()- tile.centroidX)/5), 255));
                colourArray[0] = String.valueOf(Math.min((int) (Integer.parseInt(colour.split(",")[0]) + Math.hypot(island.getCenterY()- tile.centroidY, island.getCenterX()- tile.centroidX)/5), 255));                colour = String.join(",", colourArray);
                colour = String.join(",", colourArray);
            } else if (biome.equals("desert")) {
                colour = tileRaise.DESERT.color;
                String[] colourArray = colour.split(",");
                colourArray[1] = String.valueOf(Math.min((int) (Integer.parseInt(colour.split(",")[1]) + Math.hypot(island.getCenterY()- tile.centroidY, island.getCenterX()- tile.centroidX)/5), 255));
                colourArray[2] = String.valueOf(Math.min((int) (Integer.parseInt(colour.split(",")[2]) + Math.hypot(island.getCenterY()- tile.centroidY, island.getCenterX()- tile.centroidX)/5), 255));
                colour = String.join(",", colourArray);
            } else if (biome.equals("tropical")) {
                colour = tileRaise.TROPICAL.color;
                String[] colourArray = colour.split(",");
                colourArray[2] = String.valueOf(Math.min((int) (Integer.parseInt(colour.split(",")[2]) + Math.hypot(island.getCenterY()- tile.centroidY, island.getCenterX()- tile.centroidX)/10), 255));
                colourArray[0] = String.valueOf(Math.min((int) (Integer.parseInt(colour.split(",")[0]) + Math.hypot(island.getCenterY()- tile.centroidY, island.getCenterX()- tile.centroidX)/10), 255));                colour = String.join(",", colourArray);
                colour = String.join(",", colourArray);
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
        int baselevation = 10000;

        int elevation = (int) (baselevation/Math.hypot(island.getCenterY()- tile.centroidY, island.getCenterX()- tile.centroidX));
        return elevation;
    }
}