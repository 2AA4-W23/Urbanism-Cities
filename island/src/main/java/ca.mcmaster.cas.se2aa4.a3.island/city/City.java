package ca.mcmaster.cas.se2aa4.a3.island.city;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.terrain.Tile;

import java.util.*;

public class City {
    boolean isCapital = false;
    String citySize = "";

    public City(String citySize) {
        this.citySize = citySize;
    }

    public String citySize() {
        return this.citySize;
    }

    public boolean isCapital() {
        return this.isCapital;
    }

    public void capitalCity() {
        if (!this.isCapital) {
            this.isCapital = true;
        }
    }

}