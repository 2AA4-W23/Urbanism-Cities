package ca.mcmaster.cas.se2aa4.a2.generator.terrain;

import ca.mcmaster.cas.se2aa4.a2.generator.shapes.Circle;
import ca.mcmaster.cas.se2aa4.a2.generator.specification.Dimensons;

public class Land {

    private int height, width;

    public Land(Circle c) {
        Dimensons hw = new Dimensons();
        this.height = hw.height();
        this.width = hw.width();
    }

}
