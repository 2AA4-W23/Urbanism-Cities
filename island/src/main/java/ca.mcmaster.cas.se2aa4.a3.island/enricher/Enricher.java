package ca.mcmaster.cas.se2aa4.a3.island.enricher;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.ArrayList;
import java.util.List;

public class Enricher {

    public Structs.Mesh.Builder clone = Structs.Mesh.newBuilder();

    public Enricher(Structs.Mesh aMesh) {
        this.clone.addAllVertices(aMesh.getVerticesList());
        this.clone.addAllSegments(aMesh.getSegmentsList());
        this.clone.addAllPolygons(colorPolygons(aMesh));
    }

    private List<Structs.Polygon> colorPolygons(Structs.Mesh aMesh) {

        List<Structs.Polygon> plist = new ArrayList<>();

        for (Structs.Polygon poly : aMesh.getPolygonsList()) {

            Structs.Polygon.Builder pc = Structs.Polygon.newBuilder(poly);

            String color = "0,0,0";

            Structs.Property p = Structs.Property.newBuilder()
                    .setKey("rgb_color")
                    .setValue(color)
                    .build();
            pc.addProperties(p);

            plist.add(pc.build());

        }

        return plist;

    }

    public Structs.Mesh buildNewMesh() {
        return this.clone.build();
    }

}
