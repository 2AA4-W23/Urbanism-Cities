package water;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.TileColor;

import java.util.List;

public class Aquifier {
    List<Structs.Polygon> polylist;
    List<Tile> tileList;
    int numAquifiers;

    public Aquifier(List<Structs.Polygon> polylist, List<Tile> tileList, int numAquifiers) {
        this.polylist = polylist;
        this.tileList = tileList;
        this.numAquifiers = numAquifiers;
    }

    private void setAquifierTiles() {
        for (Tile t : this.tileList) {
            if (t.createAquifier(t, this.tileList)) {
                break;
            }
        }
    }

    public List<Tile> createAquifiers() {
        for (int i = 0; i < this.numAquifiers; i++) {
            this.setAquifierTiles();
        }
        return this.tileList;
    }

}
