package ca.mcmaster.cas.se2aa4.a3.island.shapes;

import ca.mcmaster.cas.se2aa4.a3.island.dimensions.Dimensons;

import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.io.Serializable;

public class Rectangle implements Shape{

    private int height;
    private int width;
    private int distFromCentre;
    public int centreX;
    public int centreY;

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
    public RectangularShape createSelf() {
        calculateCenter();
        return new Rectangle2D.Double(this.centreX - this.distFromCentre*2, this.centreY - this.distFromCentre*1.5, this.distFromCentre * 4, this.distFromCentre * 3);
    }
}
