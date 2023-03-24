package ca.mcmaster.cas.se2aa4.a3.island.terrain;

public class Beach extends Terrain implements Biome{

    public void addBeachPolygon(Integer op) {
        super.terrainPolygons.add(op);
    }

    @Override
    public String getColour() {
        return "Beach";
    }
}
