package ca.mcmaster.cas.se2aa4.a3.island.terrain;

import elevation.Flatland;
import elevation.Hills;
import elevation.Volcano;

import java.awt.geom.RectangularShape;
import java.util.List;

public class Tile {

    private int humidity;
    private int elevation;
    private String biome = "";
    private int temperature;
    private RectangularShape shape;
    public double centroidX;
    public double centroidY;
    private final int ID;

    private boolean isAquifier = false;
    private List<Integer> neighbours;

    private boolean isIsland = false;

    private boolean isLake = false;
    private TileColor tileColor;

    String color = "";

    public Tile(String biome, String elevation, RectangularShape islandshape, double centroidX, double centroidY, List<Integer> neighbours, int ID) {
        this.biome = biome;
        this.shape  = islandshape;
        this.centroidX = centroidX;
        this.centroidY = centroidY;
        this.neighbours = neighbours;
        this.ID = ID;

        if (elevation.equals("Volcano")) {
            this.volcanizer();
        } else if (elevation.equals("Flatland")) {
            this.flatlander();
        } else if (elevation.equals("Hills")) {
            this.hiller();
        }

    }

    private void volcanizer() {
        Volcano volcano = new Volcano(this.shape, this.biome, this);
        this.color = volcano.gradient();
        if (!this.color.equals(this.tileColor.OCEAN.color)) {
            this.isIsland = true;
        }
        this.elevation = volcano.assignElevation();
    }

    private void flatlander() {
        Flatland flatland = new Flatland(this.shape, this.biome, this);
        this.color = flatland.gradient();
        if (!this.color.equals(this.tileColor.OCEAN.color)) {
            this.isIsland = true;
        }
        this.elevation = flatland.assignElevation();
    }

    private void hiller() {
        Hills hills = new Hills(this.shape, this.biome, this);
        this.color = hills.gradient();
        if (!this.color.equals(this.tileColor.OCEAN.color)) {
            this.isIsland = true;
        }
        this.elevation = hills.assignElevation();
    }

    public String getColor() {
        return this.color;
    }

    private boolean isIsland() {
        return this.isIsland;
    }
    private boolean isLake() {
        return this.isLake;
    }

    private boolean isAquifier() {
        return this.isAquifier;
    }

    public List<Integer> getNeighbours() {
        return this.neighbours;
    }

    public int getID() {
        return this.ID;
    }

    public boolean createLake(Tile tile, List<Tile> tileList) {
        if (tile.isIsland() && !tile.isLake()) {
            this.isLake = true;
            this.humidity += 40;
            this.color = this.tileColor.LAKE.color;
            for (Integer neighbourTileIdx : tile.getNeighbours()) {
                if (tileList.get(neighbourTileIdx).isIsland() && !tileList.get(neighbourTileIdx).isLake()) {
                    tileList.get(neighbourTileIdx).isLake = true;
                    tileList.get(neighbourTileIdx).humidity += 10;
                    tileList.get(neighbourTileIdx).color = this.tileColor.LAKE.color;
                    this.setHumidity(tileList, tileList.get(neighbourTileIdx));
                }
            }
            return true;
        }
        return false;
    }

    public boolean createAquifier(Tile tile, List<Tile> tileList) {
        if (tile.isIsland() && !tile.isAquifier()) {
            this.isAquifier = true;
            this.humidity += 20;
            for (Integer neighbourTileIdx : tile.getNeighbours()) {
                if (tileList.get(neighbourTileIdx).isIsland()) {
                    tileList.get(neighbourTileIdx).addHumidity();
                }
            }
            return true;
        }
        return false;
    }

    private void setHumidity(List<Tile> tileList, Tile tile) {
        for (Integer i : tile.getNeighbours()) {
            if (tileList.get(i).isIsland()) {
                tileList.get(i).addHumidity();
            }
        }
    }

    private void addHumidity() {
        this.humidity += 10;
    }

}