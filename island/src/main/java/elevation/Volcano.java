package elevation;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.enricher.Island;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.Biome;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.TileColor;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.util.StringUtil;

import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

public class Volcano implements Elevation{
    private RectangularShape island;
    private String biome;
    private Tile tile;
    private TileColor tileRaise;
    public Volcano(RectangularShape island, String biome, Tile tile){
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
                System.out.println("Here right after getting arctic.color: "+ colour);
                String[] colourArray = colour.split(",");
                colourArray[1] = String.valueOf(Math.min((int) (Integer.parseInt(colour.split(",")[1]) + Math.hypot(island.getCenterY()- tile.centroidY, island.getCenterX()- tile.centroidX)/5), 255));
                colourArray[0] = String.valueOf(Math.min((int) (Integer.parseInt(colour.split(",")[0]) + Math.hypot(island.getCenterY()- tile.centroidY, island.getCenterX()- tile.centroidX)/5), 255));                colour = String.join(",", colourArray);
                colour = String.join(",", colourArray);
                System.out.println("After converting colour to string: "+colour);
            } else if (biome.equals("Desert")) {
                colour = tileRaise.DESERT.color;
                System.out.println("Here right after getting arctic.color: "+ colour);
                String[] colourArray = colour.split(",");
                colourArray[1] = String.valueOf(Math.min((int) (Integer.parseInt(colour.split(",")[1]) + Math.hypot(island.getCenterY()- tile.centroidY, island.getCenterX()- tile.centroidX)/5), 255));
                colourArray[2] = String.valueOf(Math.min((int) (Integer.parseInt(colour.split(",")[2]) + Math.hypot(island.getCenterY()- tile.centroidY, island.getCenterX()- tile.centroidX)/5), 255));
                colour = String.join(",", colourArray);
                System.out.println("After converting colour to string: "+colour);
            } else if (biome.equals("Tropical")) {
                colour = tileRaise.TROPICAL.color;
                System.out.println("Here right after getting arctic.color: "+ colour);
                String[] colourArray = colour.split(",");
                colourArray[2] = String.valueOf(Math.min((int) (Integer.parseInt(colour.split(",")[2]) + Math.hypot(island.getCenterY()- tile.centroidY, island.getCenterX()- tile.centroidX)/5), 255));
                colourArray[0] = String.valueOf(Math.min((int) (Integer.parseInt(colour.split(",")[0]) + Math.hypot(island.getCenterY()- tile.centroidY, island.getCenterX()- tile.centroidX)/5), 255));                colour = String.join(",", colourArray);
                colour = String.join(",", colourArray);
                System.out.println("After converting colour to string: "+colour);
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
        int elevation = (int) (1000/Math.hypot(island.getCenterY()- tile.centroidY, island.getCenterX()- tile.centroidX));
        return elevation;
    }
}