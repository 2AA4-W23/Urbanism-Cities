package ca.mcmaster.cas.se2aa4.a3.island.shapes;

import org.locationtech.jts.geom.Geometry;

import java.awt.geom.Ellipse2D;
import java.awt.geom.RectangularShape;
import java.io.Serializable;

public class Circle implements Shape {

    private int height;
    private int width;
    private int radius;
    public int centreX;
    public int centreY;

    public Circle(int height, int width, int radius) {
        this.height = height;
        this.width = width;
        this.radius = radius;
    }
    @Override
    public void calculateCenter() {
        this.centreX = this.width / 2;
        this.centreY = this.height / 2;
    }
    @Override
    public RectangularShape createSelf() {
        calculateCenter();
        return new Ellipse2D.Double(this.centreX - this.radius, this.centreY - this.radius, this.radius * 2, this.radius * 2);
    }

}
