package ca.mcmaster.cas.se2aa4.a3.island.city;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class City {
    List<Structs.Polygon> polylist;
    List<Tile> tileList;
    int numCities;

    boolean isCapital = false;

    static List<City> cityList = new ArrayList<>();

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
        setCapital();
        return this.tileList;
    }

    private void setCityTile(Tile tile) {
        tile.createCity();
        this.cityList.add(this);
    }

    private void setCapital() {
        Random rand = new Random();
        int index = rand.nextInt(this.cityList.size());
        this.cityList.get(index).isCapital = true;
    }

}
