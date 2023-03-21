package ca.mcmaster.cas.se2aa4.a3.island.shapes;

import org.locationtech.jts.geom.Geometry;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.io.Serializable;

public interface Shape {
    public RectangularShape createSelf();

    public void calculateCenter();
}
