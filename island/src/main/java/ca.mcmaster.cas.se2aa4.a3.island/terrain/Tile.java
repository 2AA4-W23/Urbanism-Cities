package ca.mcmaster.cas.se2aa4.a3.island.terrain;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import elevation.Flatland;
import elevation.Hills;
import elevation.Volcano;
import whitaker.WhitakerDiagram;

import java.awt.geom.RectangularShape;
import java.util.ArrayList;
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

    private List<Structs.Segment> riverSegments = new ArrayList<>();
    private int centroidIdx;
    private boolean isAquifier = false;
    private List<Integer> neighbours;

    private boolean isIsland = false;

    private boolean isLake = false;

    private boolean isRiver = false;
    private TileColor tileColor;

    private WhitakerDiagram WD = new WhitakerDiagram();

    String color = "";

    public Tile(String biome, String elevation, RectangularShape islandshape, double centroidX, double centroidY, List<Integer> neighbours, int ID, int centroidIdx) {
        this.biome = biome;
        this.shape  = islandshape;
        this.centroidX = centroidX;
        this.centroidY = centroidY;
        this.neighbours = neighbours;
        this.ID = ID;
        this.centroidIdx = centroidIdx;

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

    public List<Structs.Segment> getRiverSegments() {
        return this.riverSegments;
    }

    private int getCentroidIdx() {
        return this.centroidIdx;
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

    public boolean createRiver(Tile tile, List<Tile> tileList) {

        Tile tempTile = tile;
        boolean isRiverFull = false;

        while (true) {
            if (tempTile.isIsland() && !tempTile.isLake()) {
                Structs.Segment s;
                this.isRiver = true;
                int lowestElevationIdx = tempTile.getID();
                int lowestElevation = tempTile.getElevation();
                for (Integer neighbourTileIdx : tempTile.getNeighbours()) {
                    int tempLowestElevation = lowestElevation;
                    lowestElevation = Math.min(tileList.get(neighbourTileIdx).getElevation(), lowestElevation);
                    if (lowestElevation < tempLowestElevation) {
                        lowestElevationIdx = neighbourTileIdx;
                    }
                }
                System.out.println(lowestElevationIdx != tempTile.getID());
                if (lowestElevationIdx != tempTile.getID()) {
                    System.out.println("I CAN MAKE RIVER!");
                    Structs.Segment.Builder sc = Structs.Segment.newBuilder().setV1Idx(tileList.get(tempTile.getID()).getCentroidIdx()).setV2Idx(tileList.get(lowestElevationIdx).getCentroidIdx());
                    Structs.Property rgb = Structs.Property.newBuilder()
                            .setKey("rgb_color")
                            .setValue(tileColor.RIVER.color)
                            .build();
                    Structs.Property thickness = Structs.Property.newBuilder()
                            .setKey("thickness")
                            .setValue("3")
                            .build();
                    sc.addProperties(rgb);
                    sc.addProperties(thickness);
                    s = sc.build();
                    this.riverSegments.add(s);
                    this.setHumidity(tileList, tileList.get(lowestElevation));
                    this.endorheicLake(tileList, lowestElevationIdx);
                } else {
                    System.out.println("BROKE");
                    break;
                }
                tempTile = tileList.get(lowestElevationIdx);
            } else {
                break;
            }
        }

        if (!this.riverSegments.isEmpty()) {
            System.out.println("RIVER SEGMENTS R NOT EMPTY: " + this.riverSegments);
            isRiverFull = true;
        }

        return isRiverFull;

    }

    private boolean endorheicLake(List<Tile> tileList, int lowestElevationIdx) {
        if (tileList.get(lowestElevationIdx).isIsland() && !tileList.get(lowestElevationIdx).isLake()) {
            for (Integer i : tileList.get(lowestElevationIdx).getNeighbours()) {
                if (!tileList.get(i).isIsland()) {
                    return false;
                }
            }
            tileList.get(lowestElevationIdx).isLake = true;
            tileList.get(lowestElevationIdx).color = this.tileColor.RIVER.color;
        }
        return true;
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

    public int getHumidity() {
        return this.humidity;
    }

    public int getElevation() {
        return this.elevation;
    }
}