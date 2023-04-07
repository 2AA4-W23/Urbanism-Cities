package ca.mcmaster.cas.se2aa4.a3.island.city;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.Tile;

import java.util.*;

public class CityBuilder {

    public CityBuilder() {

    }

    public Map<City, Tile> run(String cities, List<Tile> tileList) {
        Map<City, Tile> cityTiles = cityBuilder(cities, tileList);
        return cityTiles;
    }

    private Map<City, Tile> cityBuilder(String cities, List<Tile> tileList) {
        int counter = 0;
        int tileIdx = 0;
        Map<City, Tile> cityMap = new HashMap<>();

        while (counter < Integer.parseInt(cities) && tileIdx < tileList.size()) {
            if (tileList.get(tileIdx).canMakeCity()) {
                City city = new City();
                cityMap.put(city, tileList.get(tileIdx));
                counter++;
            }
            tileIdx++;
        }

        setCapitalCity(cityMap);

        return cityMap;

    }

    private void setCapitalCity(Map<City, Tile> cityMap) {
        Random rand = new Random();
        int capitalIdx = rand.nextInt(cityMap.size());
        int index = 0;
        for (Map.Entry<City, Tile> kv : cityMap.entrySet()) {
            if (index == capitalIdx) {
                kv.getKey().capitalCity();
                break;
            }
            index++;
        }
    }

}