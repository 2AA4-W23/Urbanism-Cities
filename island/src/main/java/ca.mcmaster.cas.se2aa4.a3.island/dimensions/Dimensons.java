package ca.mcmaster.cas.se2aa4.a3.island.dimensions;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.List;

public class Dimensons {

    private List<Structs.Vertex> vertices;
    private static int height = 0;
    private static int width = 0;

    public Dimensons(List<Structs.Vertex> vertices) {
        this.vertices = vertices;
        calculateHeightWidth();
    }

    private void calculateHeightWidth() {

        int maxWidth = 0;
        int maxHeight = 0;

        for (int i = 0; i < this.vertices.size(); i++) {
            int x_coordinate = (int) this.vertices.get(i).getX();
            int y_coordinate = (int) this.vertices.get(i).getY();
            maxWidth = Math.max(x_coordinate, maxWidth);
            maxHeight = Math.max(y_coordinate, maxHeight);
        }

        this.width = maxWidth;
        this.height = maxHeight;

    }

    public int height() {
        return this.height;
    }

    public int width() {
        return this.width;
    }

}
