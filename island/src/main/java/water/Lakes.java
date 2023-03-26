package water;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.TileColor;

import java.util.List;

public class Lakes {

    List<Structs.Polygon> polylist;
    List<Tile> tileList;
    int numLakes;

    public Lakes(List<Structs.Polygon> polylist, List<Tile> tileList, int numLakes) {
        this.polylist = polylist;
        this.tileList = tileList;
        this.numLakes = numLakes;
    }

    public void setLakeTiles() {
        for (Tile t : this.tileList) {
            if (t.createLake(t, this.tileList)) {
                break;
            }
        }
    }

    public List<Tile> createLakes() {
        for (int i = 0; i < this.numLakes; i++) {
            this.setLakeTiles();
        }
        return this.tileList;
    }

}
