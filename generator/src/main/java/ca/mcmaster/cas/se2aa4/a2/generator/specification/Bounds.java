package ca.mcmaster.cas.se2aa4.a2.generator.specification;

import ca.mcmaster.cas.se2aa4.a2.generator.shapes.Circle;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Bounds {

    private List<Ellipse2D> bounds = new ArrayList<>();
    private List<Set<Integer>> boundsSet = new ArrayList<>();
    private String color = "";
    boolean add = true;

    public void setBounds(Circle c) {
        Ellipse2D e = c.createCircle();
        this.bounds.add(e);
        this.boundsSet.add(new HashSet<>());
    }

    public String checkBounds(double centroid_x, double centroid_y, int numPolygon, Structs.Polygon poly) {

        this.add = true;

        if (bounds.get(0).contains(centroid_x, centroid_y)) {
            boundsSet.get(0).add(numPolygon);
            this.color="129,173,125";
        } else if (bounds.get(1).contains(centroid_x, centroid_y)) {
            boundsSet.get(1).add(numPolygon);
            this.color = "235,223,167";
            this.add = false;
        } else if (bounds.get(2).contains(centroid_x, centroid_y)) {
            boundsSet.get(2).add(numPolygon);
            this.color = "54,141,255";
        }

        return this.color;

    }

    public List<Structs.Polygon.Builder> beachTile(Structs.Mesh aMesh) {

        List<Structs.Polygon.Builder> pcList = new ArrayList<>();

        for (int l : boundsSet.get(1)) {
            Structs.Polygon.Builder pc = Structs.Polygon.newBuilder(aMesh.getPolygons(l));
            for (int n : aMesh.getPolygons(l).getNeighborIdxsList()) {
                if (boundsSet.get(0).contains(n) || boundsSet.get(2).contains(n)) {
                    this.color = "245,243,152";
                    break;
                }
                this.color = "235,223,167";
            }
            Structs.Property p = Structs.Property.newBuilder()
                    .setKey("rgb_color")
                    .setValue(this.color)
                    .build();

            pc.addProperties(p);
            pcList.add(pc);
        }

        return pcList;

    }

    public boolean add() {
        return this.add;
    }

}
