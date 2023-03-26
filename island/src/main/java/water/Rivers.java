package water;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.TileColor;

import java.util.ArrayList;
import java.util.List;

public class Rivers {

    List<Structs.Polygon> polylist;
    List<Tile> tileList;
    int numRivers;
    static List<Structs.Segment> riverSegments = new ArrayList<>();

    public Rivers(List<Structs.Polygon> polylist, List<Tile> tileList, int numRivers) {
        this.polylist = polylist;
        this.tileList = tileList;
        this.numRivers = numRivers;
    }

    public List<Structs.Segment> createRivers() {
        int counter = 0;
        int tileIdx = 0;
        while (counter < this.numRivers && tileIdx < this.tileList.size()) {
            if (this.tileList.get(tileIdx).createRiver(this.tileList.get(tileIdx), this.tileList)) {
                this.riverSegments.addAll(this.tileList.get(tileIdx).getRiverSegments());
                counter++;
            }
            tileIdx++;
        }
        return this.riverSegments;
    }

}