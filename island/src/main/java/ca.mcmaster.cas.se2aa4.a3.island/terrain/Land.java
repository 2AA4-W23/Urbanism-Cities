package ca.mcmaster.cas.se2aa4.a3.island.terrain;

import java.awt.geom.Ellipse2D;

public class Land extends Terrain {

    Ellipse2D landBounds;

    public Land(Ellipse2D e) {
        this.landBounds = e;
    }

    public void addLandPolygon(Integer op) {
        super.terrainPolygons.add(op);
    }

    public boolean contains(double centroid_x, double centroid_y) {
        if (landBounds.contains(centroid_x, centroid_y)) {
            return true;
        }
        return false;
    }

}
