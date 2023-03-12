package ca.mcmaster.cas.se2aa4.a2.generator.adt;

import ca.mcmaster.cas.se2aa4.a2.generator.neighborhoud.DelaunayNeighbourhood;
import ca.mcmaster.cas.se2aa4.a2.generator.neighborhoud.Neighborhood;
import ca.mcmaster.cas.se2aa4.a2.generator.shapes.Circle;

import java.awt.geom.Ellipse2D;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class Mesh implements Iterable<Polygon> {

    private Set<Polygon> polygons;
    private int width, height;

    public Mesh(int width, int height) {
        this.width = width;
        this.height = height;
        this.polygons = new HashSet<>();
    }

    public void register(Polygon p) {
        this.polygons.add(p.crop(width, height));
    }

    @Override
    public Iterator<Polygon> iterator() {
        return this.polygons.iterator();
    }

    public void populateNeighbours(Neighborhood neighbourhood) {
        neighbourhood.build(this.polygons);
        for(Polygon p: this) {
            for(Polygon n: neighbourhood.neighbors(p)) {
                p.registerAsNeighbour(n);
            }
        }
    }

    public Ellipse2D createBounds() {
        int min = 200;
        int max = 500;
        int radius = (int)(Math.random()*(max-min+1)+min);
        Circle innerBounds = new Circle(this.height, this.width, radius);
        return innerBounds.createCircle();
    }

    @Override
    public String toString() {
        return "Mesh(" +width+"x"+height+","+polygons+")";
    }
}
