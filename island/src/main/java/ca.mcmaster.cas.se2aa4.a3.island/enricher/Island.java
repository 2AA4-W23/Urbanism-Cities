package ca.mcmaster.cas.se2aa4.a3.island.enricher;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.city.Adapter;
import ca.mcmaster.cas.se2aa4.a3.island.city.City;
import ca.mcmaster.cas.se2aa4.a3.island.city.CityBuilder;
import ca.mcmaster.cas.se2aa4.a3.island.shapes.Circle;
import ca.mcmaster.cas.se2aa4.a3.island.shapes.Rectangle;
import ca.mcmaster.cas.se2aa4.a3.island.dimensions.Dimensons;

import ca.mcmaster.cas.se2aa4.a3.island.terrain.Tile;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.adt.Graph;
import shortestpath.ShortestPathBFS;
import water.Aquifier;
import water.Lakes;
import water.Rivers;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.util.*;

public class Island implements Enricher {

    public Structs.Mesh originalMesh = null;

    private RectangularShape shapeIsland;

    public Structs.Mesh.Builder aMesh = Structs.Mesh.newBuilder();
    private Dimensons meshDimensions;
    public String elevation;
    public String biome;
    private String aquifiers;
    public List<Tile> tileList = new ArrayList<>();
    private List<Structs.Segment> riverSegments = new ArrayList<>();
    public String lakes;
    private String rivers;
    private String soil;
    private String cities;

    public Island(Structs.Mesh aMesh, String shape, String elevation, String biome, String lakes, String aquifiers, String rivers, String soil, String cities) {
        this.originalMesh = aMesh;
        this.aMesh.addAllVertices(aMesh.getVerticesList());
        this.aMesh.addAllSegments(aMesh.getSegmentsList());
        this.meshDimensions = new Dimensons(aMesh.getVerticesList());
        this.elevation = elevation;
        this.biome = biome;
        this.lakes = lakes;
        this.aquifiers = aquifiers;
        this.rivers = rivers;
        this.soil = soil;
        this.cities = cities;

        if (shape.equals("circle")) {
            this.shapeIsland = (Ellipse2D) new Circle(this.meshDimensions.height(), this.meshDimensions.width(), this.meshDimensions.width()/4).createSelf();
        } else if (shape.equals("rectangle")){
            this.shapeIsland = (Rectangle2D) new Rectangle(this.meshDimensions.height(), this.meshDimensions.width(), 250).createSelf();
        }
    }

    public void colorPolygons() {
        String color = "";
        for (Tile tile : this.tileList) {
            Structs.Polygon.Builder pc = Structs.Polygon.newBuilder(this.originalMesh.getPolygonsList().get(tile.getID()));
            color = tile.getColor();

            Structs.Property p = Structs.Property.newBuilder()
                    .setKey("rgb_color")
                    .setValue(color)
                    .build();
            pc.addProperties(p);
            this.aMesh.addPolygons(pc);
        }
    }

    @Override
    public void process() {
        int numPolygon = 0;

        for (Structs.Polygon poly: this.originalMesh.getPolygonsList()) {

            double centroid_x = this.aMesh.getVerticesList().get(poly.getCentroidIdx()).getX();
            double centroid_y = this.aMesh.getVerticesList().get(poly.getCentroidIdx()).getY();

            Tile tile = new Tile(this.biome, this.shapeIsland, centroid_x, centroid_y, poly.getNeighborIdxsList(), numPolygon, poly.getCentroidIdx(), this.soil);
            this.tileList.add(tile);

            ++numPolygon;
        }
    }

    @Override
    public Structs.Mesh buildNewMesh() {
        this.process();
        this.buildLakes();
        this.buildAquifier();
        this.elevateIsland();
        this.buildRivers();
        this.buildCities();
        this.colorPolygons();
        return this.aMesh.build();
    }

    private void elevateIsland() {
        for (Tile t : this.tileList) {
            if (this.elevation.equals("volcano")) {
                t.volcanizer();
            } else if (this.elevation.equals("flatland")) {
                t.flatlander();
            } else if (this.elevation.equals("hills")) {
                t.hiller();
            }
        }
    }


    public void buildLakes() {
        if (Integer.parseInt(this.lakes) > 0) {
            Lakes lakes = new Lakes(this.originalMesh.getPolygonsList(), this.tileList, Integer.parseInt(this.lakes));
            this.tileList = lakes.createLakes();
        }
    }

    private void buildAquifier() {
        if (Integer.parseInt(this.aquifiers) > 0) {
            Aquifier aquifier = new Aquifier(this.originalMesh.getPolygonsList(), this.tileList, Integer.parseInt(this.aquifiers));
            this.tileList = aquifier.createAquifiers();
        }
    }

    private void buildRivers() {
        if (Integer.parseInt(this.rivers) > 0) {
            Rivers river = new Rivers(this.originalMesh.getPolygonsList(), this.tileList, Integer.parseInt(this.rivers));
            this.riverSegments = river.createRivers();
        }
        this.aMesh.addAllSegments(this.riverSegments);
    }

    private void buildCities() {
        if (Integer.parseInt(this.cities) > 0) {
            Map<City, Tile> cityTiles = new CityBuilder().run(this.cities, this.tileList);
            Adapter a = new Adapter();
            List<Structs.Segment> shortestPath = a.run(this.originalMesh, cityTiles, this.tileList);
            List<Structs.Vertex> cityColors = a.cityColors();
            this.aMesh.addAllSegments(shortestPath);
            this.aMesh.addAllVertices(cityColors);
        }
    }

}