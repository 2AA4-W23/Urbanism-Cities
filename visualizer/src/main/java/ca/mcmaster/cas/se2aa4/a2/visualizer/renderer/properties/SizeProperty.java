package ca.mcmaster.cas.se2aa4.a2.visualizer.renderer.properties;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.List;
import java.util.Optional;

public class SizeProperty implements PropertyAccess<String> {
    @Override
    public Optional<String> extract(List<Structs.Property> props) {
        String value = new Reader(props).get("size");
        if (value == null)
            return Optional.empty();
        String size = value;
        return Optional.of(size);
    }
}