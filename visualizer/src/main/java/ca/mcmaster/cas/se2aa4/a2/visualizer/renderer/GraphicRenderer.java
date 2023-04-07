package ca.mcmaster.cas.se2aa4.a2.visualizer.renderer;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.visualizer.renderer.properties.ColorProperty;
import ca.mcmaster.cas.se2aa4.a2.visualizer.renderer.properties.SizeProperty;
import ca.mcmaster.cas.se2aa4.a2.visualizer.renderer.properties.ThicknessProperty;

import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.util.Iterator;
import java.util.Optional;

public class GraphicRenderer implements Renderer {

    private static final int THICKNESS = 3;
    public void render(Mesh aMesh, Graphics2D canvas) {
        canvas.setColor(Color.BLACK);
        Stroke stroke = new BasicStroke(0.2f);
        canvas.setStroke(stroke);
        drawPolygons(aMesh,canvas);
        drawSegments(aMesh,canvas);
        drawVertices(aMesh,canvas);
    }

    private void drawPolygons(Mesh aMesh, Graphics2D canvas) {
        for(Structs.Polygon p: aMesh.getPolygonsList()){
            drawAPolygon(p, aMesh, canvas);
        }
    }

    private void drawAPolygon(Structs.Polygon p, Mesh aMesh, Graphics2D canvas) {
        Hull hull = new Hull();
        for(Integer segmentIdx: p.getSegmentIdxsList()) {
            hull.add(aMesh.getSegments(segmentIdx), aMesh);
        }
        Path2D path = new Path2D.Float();
        Iterator<Vertex> vertices = hull.iterator();
        Vertex current = vertices.next();
        path.moveTo(current.getX(), current.getY());
        while (vertices.hasNext()) {
            current = vertices.next();
            path.lineTo(current.getX(), current.getY());
        }
        path.closePath();
        canvas.draw(path);
        Optional<Color> fill = new ColorProperty().extract(p.getPropertiesList());

        if(fill.isPresent()) {
            Color old = canvas.getColor();
            canvas.setColor(fill.get());
            canvas.fill(path);
            canvas.setColor(old);
        }
    }

    private void drawSegments(Mesh aMesh, Graphics2D canvas) {
        Optional<Color> fill;
        Optional<String> thickness;
        for (Structs.Segment s : aMesh.getSegmentsList()) {
            Path2D segment = new Path2D.Float(); // Initialize the Path2D here
            fill = new ColorProperty().extract(s.getPropertiesList());
            if (fill.isPresent()) {
                canvas.setColor(fill.get());
                segment.moveTo(aMesh.getVertices(s.getV1Idx()).getX(), aMesh.getVertices(s.getV1Idx()).getY());
                segment.lineTo(aMesh.getVertices(s.getV2Idx()).getX(), aMesh.getVertices(s.getV2Idx()).getY());
                segment.closePath();
                thickness = new ThicknessProperty().extract(s.getPropertiesList());
                canvas.setStroke(new BasicStroke(Float.parseFloat(thickness.get())));
                canvas.draw(segment);
            }
        }
    }

    private void drawVertices(Mesh aMesh, Graphics2D canvas) {
        Optional<Color> fill;
        Optional<String> size;
        Ellipse2D city = new Ellipse2D.Float();
        for (Structs.Vertex v : aMesh.getVerticesList()) {
            fill = new ColorProperty().extract(v.getPropertiesList());
            if (fill.isPresent()) {
                canvas.setColor(fill.get());
                size = new SizeProperty().extract(v.getPropertiesList());
                int sizeInt = Integer.parseInt(size.get());
                city.setFrame(v.getX() - sizeInt/2, v.getY() - sizeInt/2, sizeInt, sizeInt);
                canvas.fill(city);
            }

        }
    }

}
