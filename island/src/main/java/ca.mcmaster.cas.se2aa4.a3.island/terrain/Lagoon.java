package ca.mcmaster.cas.se2aa4.a3.island.terrain;

import java.awt.geom.Ellipse2D;

public class  Lagoon extends Terrain {

    Ellipse2D lagoonBounds;

    public Lagoon(Ellipse2D e) {
        this.lagoonBounds = e;
    }

    public void addLagoonPolygon(Integer op) {
        super.terrainPolygons.add(op);
    }

    public boolean contains(double centroid_x, double centroid_y) {
        if (lagoonBounds.contains(centroid_x, centroid_y)) {
            return true;
        }
        return false;
    }

}
