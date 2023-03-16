package ca.mcmaster.cas.se2aa4.a3.island.shapes;

import java.awt.geom.Ellipse2D;

public class Circle extends Shape {

    private int height, width, radius, centreX, centreY;

    public Circle(int height, int width, int radius) {
        this.height = height;
        this.width = width;
        this.radius = radius;
    }

    private void calculateCenter() {
        this.centreX = this.width / 2;
        this.centreY = this.height / 2;
    }

    public Ellipse2D createCircle() {
        calculateCenter();
        return new Ellipse2D.Double(this.centreX - this.radius, this.centreY - this.radius, this.radius * 2, this.radius * 2);
    }

}
