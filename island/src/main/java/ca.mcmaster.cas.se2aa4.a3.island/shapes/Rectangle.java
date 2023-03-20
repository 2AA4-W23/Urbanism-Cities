package ca.mcmaster.cas.se2aa4.a3.island.shapes;

import ca.mcmaster.cas.se2aa4.a3.island.dimensions.Dimensons;

import java.awt.geom.Rectangle2D;
import java.io.Serializable;

public class Rectangle implements Shape{

    private int height, width, distFromCentre, centreX, centreY;

    public Rectangle(int height, int width, int distFromCentre) {
        this.height = height;
        this.width = width;
        this.distFromCentre = distFromCentre;
    }

    @Override
    public void calculateCenter() {
        this.centreX = this.width / 2;
        this.centreY = this.height / 2;
    }

    @Override
    public Serializable createSelf() {
        return new Rectangle2D.Double(this.centreX - this.distFromCentre, this.centreY - this.distFromCentre, this.distFromCentre * 2, this.distFromCentre * 2);
    }
}
