package ca.mcmaster.cas.se2aa4.a3.island.terrain;

import java.awt.geom.Ellipse2D;

public class Ocean extends Terrain {

    Ellipse2D oceanBounds;

    public Ocean(Ellipse2D e) {
        this.oceanBounds = e;
    }

    public void addOceanPolygon(Integer op) {
        super.terrainPolygons.add(op);
    }

    public boolean contains(double centroid_x, double centroid_y) {
        if (oceanBounds.contains(centroid_x, centroid_y)) {
            return true;
        }
        return false;
    }

}
