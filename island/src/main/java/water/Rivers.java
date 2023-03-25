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
    TileColor lakeColor;
    static List<Structs.Segment> riverSegments = new ArrayList<>();
    static int counter = 0;

    public Rivers(List<Structs.Polygon> polylist, List<Tile> tileList, int numRivers) {
        this.polylist = polylist;
        this.tileList = tileList;
        this.numRivers = numRivers;
    }

    public List<Structs.Segment> createRivers() {
        int counter = 0;
        int tileIdx = 0;
        while (counter < this.numRivers && tileIdx < this.tileList.size()) {
            System.out.println("NUM TILE: " + tileIdx);
            System.out.println("NUM RIVERS: " + counter);
            if (this.tileList.get(tileIdx).createRiver(this.tileList.get(tileIdx), this.tileList)) {
                System.out.println("CREATED A RIVER!");
                this.riverSegments.addAll(this.tileList.get(tileIdx).getRiverSegments());
                counter++;
            }
            tileIdx++;
        }
        System.out.println("NUM RIVERS COUNTED: " + counter);
//        System.out.println("ALL RIVER SEGMENTS: " + this.riverSegments);
        return this.riverSegments;
    }

}