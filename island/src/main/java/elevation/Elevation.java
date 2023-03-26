package elevation;

import ca.mcmaster.cas.se2aa4.a3.island.terrain.Biome;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.Tile;

import java.awt.geom.RectangularShape;

public interface Elevation {
    public String gradient();

    public int assignElevation();

}
