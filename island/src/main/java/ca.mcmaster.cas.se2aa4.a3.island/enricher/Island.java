package ca.mcmaster.cas.se2aa4.a3.island.enricher;

import ca.mcmaster.cas.se2aa4.a3.island.shapes.Circle;
import ca.mcmaster.cas.se2aa4.a3.island.shapes.Rectangle;
import ca.mcmaster.cas.se2aa4.a3.island.shapes.Shape;
import ca.mcmaster.cas.se2aa4.a3.island.dimensions.Dimensons;

import org.locationtech.jts.geom.Geometry;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Island {
    private Circle circleIsland;
    private Rectangle rectangleIsland;
    private Dimensons d;

    public Island(String shape) {

        if (shape.equals("circle")) {
            this.circleIsland = new Circle(this.d.height(), this.d.width(), 250);
        } else {
            this.rectangleIsland = new Rectangle(this.d.height(), this.d.width(), 250);
            Rectangle2D r = (Rectangle2D) this.rectangleIsland.createSelf();
            r.c
        }
    }

}
