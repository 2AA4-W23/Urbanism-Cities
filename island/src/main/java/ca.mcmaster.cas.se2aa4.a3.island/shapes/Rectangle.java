package ca.mcmaster.cas.se2aa4.a3.island.shapes;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Rectangle extends Shape {

    private int height, width, centreX, centreY;

    public Rectangle(int height, int width) {
        this.height = height;
        this.width = width;
    }

    private void calculateCenter() {
        this.centreX = this.width / 3;
        this.centreY = this.height / 4;
    }

    public Rectangle2D createRectangle() {
        calculateCenter();
        return new Rectangle2D.Double(this.centreX, this.centreY, 500,500);
    }

}
