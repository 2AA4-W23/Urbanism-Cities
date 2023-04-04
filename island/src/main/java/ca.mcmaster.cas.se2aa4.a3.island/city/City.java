package ca.mcmaster.cas.se2aa4.a3.island.city;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.Tile;

import java.util.List;

public class City {
    List<Structs.Polygon> polylist;
    List<Tile> tileList;
    int numCities;
    public City(List<Structs.Polygon> polyList, List<Tile> tileList, int numCities) {
        this.polylist = polyList;
        this.tileList = tileList;
        this.numCities = numCities;
    }

    public List<Tile> createCities() {
        int counter = 0;
        int tileIdx = 0;
        while (counter < this.numCities && tileIdx < this.tileList.size()) {
            if (this.tileList.get(tileIdx).canMakeCity()) {
                setCityTile(this.tileList.get(tileIdx));
                counter++;
            }
            tileIdx++;
        }
        return this.tileList;
    }

    private void setCityTile(Tile tile) {
        tile.createCity();
    }

}
