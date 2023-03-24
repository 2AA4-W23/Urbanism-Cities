package water;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.TileColor;

import java.util.List;

public class Lakes {

    List<Structs.Polygon> polylist;
    List<Tile> tileList;
    int numLakes;
    TileColor lakeColor;

    public Lakes(List<Structs.Polygon> polylist, List<Tile> tileList, int numLakes) {
        this.polylist = polylist;
        this.tileList = tileList;
        this.numLakes = numLakes;
    }

    private void setLakeTiles() {
        System.out.println("SET LAKES");
        for (Tile t : this.tileList) {
            System.out.println("THERE ARE TILES!");
            if (t.createLake(t, this.tileList)) {
                System.out.println("maed at least 1 lake");
                break;
            }
        }
    }

    public List<Tile> createLakes() {
        System.out.println("NGNGNG");
        for (int i = 0; i < this.numLakes; i++) {
            this.setLakeTiles();
        }
        return this.tileList;
    }

}
