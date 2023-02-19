package ca.mcmaster.cas.se2aa4.a2.visualizer;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;

import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.List;

public class GraphicRenderer {

    private static final int THICKNESS = 3;

    public void render(Mesh aMesh, Graphics2D canvas) {
        canvas.setColor(Color.BLACK);
        Stroke stroke = new BasicStroke(0.5f);
        canvas.setStroke(stroke);
        for (Vertex v : aMesh.getVerticesList()) {
            double centre_x = v.getX() - (THICKNESS / 2.0d);
            double centre_y = v.getY() - (THICKNESS / 2.0d);
            Color old = canvas.getColor();
            canvas.setColor(extractColor(v.getPropertiesList()));
            Ellipse2D point = new Ellipse2D.Double(centre_x, centre_y, THICKNESS,
                    THICKNESS);
            canvas.fill(point);
            canvas.setColor(old);
        }

        // for (Segment s : aMesh.getSegmentsList()) {
        // double centre_x = aMesh.getVerticesList().get(s.getV1Idx()).getX();
        // double centre_y = aMesh.getVerticesList().get(s.getV1Idx()).getY();
        // double centre2_x = aMesh.getVerticesList().get(s.getV2Idx()).getX();
        // double centre2_y = aMesh.getVerticesList().get(s.getV2Idx()).getY();
        // Color old = canvas.getColor();
        // canvas.setColor(extractColor(s.getPropertiesList()));
        // Line2D line = new Line2D.Double(centre_x, centre_y, centre2_x, centre2_y);
        // canvas.draw(line);
        // canvas.fill(line);
        // canvas.setColor(old);
        // }

        System.out.println("ZZZZZZZZZZZZZZZZZZZZZZ: " + aMesh.getPolygonsCount());
        System.out.println("hdkfjhsfhsif: " + aMesh.getPolygonsList());

        for (Polygon p : aMesh.getPolygonsList()) {
            if (p.getSegmentIdxsList().get(3) < 626) {
                double centre_x = aMesh.getVerticesList().get(p.getSegmentIdxsList().get(0)).getX();
                double centre_y = aMesh.getVerticesList().get(p.getSegmentIdxsList().get(0)).getY();
                double centre2_x = aMesh.getVerticesList().get(p.getSegmentIdxsList().get(1)).getX();
                double centre2_y = aMesh.getVerticesList().get(p.getSegmentIdxsList().get(1)).getY();
                double centre3_x = aMesh.getVerticesList().get(p.getSegmentIdxsList().get(2)).getX();
                double centre3_y = aMesh.getVerticesList().get(p.getSegmentIdxsList().get(2)).getY();
                double centre4_x = aMesh.getVerticesList().get(p.getSegmentIdxsList().get(3)).getX();
                double centre4_y = aMesh.getVerticesList().get(p.getSegmentIdxsList().get(3)).getY();
                Color old = canvas.getColor();
                canvas.setColor(extractColor(p.getPropertiesList()));
                Line2D line = new Line2D.Double(centre_x, centre_y, centre4_x, centre4_y);
                Line2D line2 = new Line2D.Double(centre_x, centre_y, centre3_x, centre3_y);
                // Line2D line3 = new Line2D.Double(centre3_x, centre3_y, centre4_x, centre4_y);
                // Line2D line4 = new Line2D.Double(centre2_x, centre2_y, centre4_x, centre4_y);
                canvas.draw(line);
                canvas.draw(line2);
                // canvas.draw(line3);
                // canvas.draw(line4);
                canvas.fill(line);
                canvas.fill(line2);
                // canvas.fill(line3);
                // canvas.fill(line4);
                canvas.setColor(old);
            }
        }

    }

    private Color extractColor(List<Property> properties) {
        String val = null;
        for (Property p : properties) {
            if (p.getKey().equals("rgb_color")) {
                System.out.println(p.getValue());
                val = p.getValue();
            }
        }
        if (val == null)
            return Color.BLACK;
        String[] raw = val.split(",");
        int red = Integer.parseInt(raw[0]);
        int green = Integer.parseInt(raw[1]);
        int blue = Integer.parseInt(raw[2]);
        return new Color(red, green, blue);
    }

}
