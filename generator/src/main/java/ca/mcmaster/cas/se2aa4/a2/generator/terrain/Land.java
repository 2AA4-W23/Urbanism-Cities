package ca.mcmaster.cas.se2aa4.a2.generator.terrain;

import ca.mcmaster.cas.se2aa4.a2.generator.shapes.Circle;
import ca.mcmaster.cas.se2aa4.a2.generator.specification.HeightWidth;

public class Land {

    private int height, width;

    public Land(Circle c) {
        HeightWidth hw = new HeightWidth();
        this.height = hw.height();
        this.width = hw.width();
    }

}
