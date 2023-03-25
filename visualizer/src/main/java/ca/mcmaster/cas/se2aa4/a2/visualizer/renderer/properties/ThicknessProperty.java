package ca.mcmaster.cas.se2aa4.a2.visualizer.renderer.properties;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;

import java.util.List;
import java.util.Optional;

public class ThicknessProperty implements PropertyAccess<String> {
    @Override
    public Optional<String> extract(List<Property> props) {
        String value = new Reader(props).get("thickness");
        if (value == null)
            return Optional.empty();
        String thickness = value;
        return Optional.of(thickness);
    }
}
