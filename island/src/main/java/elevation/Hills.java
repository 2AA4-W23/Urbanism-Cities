package elevation;

import ca.mcmaster.cas.se2aa4.a3.island.terrain.Biome;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.Tile;

import java.awt.geom.RectangularShape;

public class Hills implements Elevation{
    @Override
    public String gradient() {
        return null;
    }

    @Override
    public void temperature() {

    }

    @Override
    public int assignElevation() {
        return 0;
    }
}
