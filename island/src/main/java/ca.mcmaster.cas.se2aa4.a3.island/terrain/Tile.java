package ca.mcmaster.cas.se2aa4.a3.island.terrain;

import elevation.Flatland;
import elevation.Hills;
import elevation.Volcano;
import whitaker.WhitakerDiagram;

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

    private WhitakerDiagram WD = new WhitakerDiagram();

    String color = "";

    public Tile(String biome, String elevation, RectangularShape islandshape, double centroidX, double centroidY, List<Integer> neighbours, int ID) {
        this.biome = biome;
        this.shape  = islandshape;
        this.centroidX = centroidX;
        this.centroidY = centroidY;
        this.neighbours = neighbours;
        this.ID = ID;

        if (this.shape.contains(this.centroidX, this.centroidY)) {
            this.isIsland = true;
        }

    }

    public void volcanizer() {
        Volcano volcano = new Volcano(this.shape, this.biome, this);
        this.elevation = volcano.assignElevation();
        //System.out.println("The humidity of the tile in the volcanizer is: "+this.humidity);

//        if (!this.color.equals(this.tileColor.OCEAN.color)) {
//            this.isIsland = true;
//        }
        if (!this.isLake) {
            this.biome = this.WD.determineSubBiome(this.elevation, this.humidity, this.biome);
            volcano.setBiome(this.biome);
            this.color = volcano.gradient();
        }
    }

    public void flatlander() {
        Flatland flatland = new Flatland(this.shape, this.biome, this);
        this.elevation = flatland.assignElevation();

        if (!this.isLake) {
            this.biome = this.WD.determineSubBiome(this.elevation, this.humidity, this.biome);
            flatland.setBiome(this.biome);
            this.color = flatland.gradient();
        }
    }

    public void hiller() {
        Hills hills = new Hills(this.shape, this.biome, this);
        this.elevation = hills.assignElevation();

        if (!this.isLake) {
            this.biome = this.WD.determineSubBiome(this.elevation, this.humidity, this.biome);
            hills.setBiome(this.biome);
            this.color = hills.gradient();
        }
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
            System.out.println("Humidity of og lake tile: "+this.humidity);
            for (Integer neighbourTileIdx : tile.getNeighbours()) {
                if (tileList.get(neighbourTileIdx).isIsland() && !tileList.get(neighbourTileIdx).isLake()) {
                    System.out.println("LAKE TILE: "+tileList.get(neighbourTileIdx).humidity);
                    tileList.get(neighbourTileIdx).isLake = true;
                    tileList.get(neighbourTileIdx).humidity += 20;
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
            System.out.println("ENTERTED FOR LOOP");
            if (tileList.get(i).isIsland()) {
                System.out.println("IS ISLAND HEHEHE");
                tileList.get(i).addHumidity();
                System.out.println("Humidity in setHumidty when creating lakes: " + tileList.get(i).humidity);
            }
        }
    }

    private void addHumidity() {
        this.humidity += 10;
    }

    public int getHumidity() {
        return this.humidity;
    }

    public int getElevation() {
        return this.elevation;
    }
}