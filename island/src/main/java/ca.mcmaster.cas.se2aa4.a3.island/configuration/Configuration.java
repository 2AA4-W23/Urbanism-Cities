package ca.mcmaster.cas.se2aa4.a3.island.configuration;

import org.apache.commons.cli.*;

import java.util.HashMap;
import java.util.Map;

public class Configuration {

    public static final String INPUT = "i";
    public static final String FILENAME = "o";
    public static final String MODE = "m";
    public static final String MODE_LONG = "mode";

    public static final String SHAPE = "s";
    public static final String SHAPE_LONG = "shape";

    public static final String ELEVATION = "e";
    public static final String ELEVATION_LONG = "elevation";

    public static final String LAKES = "l";
    public static final String LAKES_LONG = "altitude";

    public static final String RIVERS = "r";
    public static final String RIVERS_LONG = "rivers";

    public static final String AQUIFIERS = "a";
    public static final String AQUIFIERS_LONG = "aquifiers";

    public static final String SOIL = "d";
    public static final String SOIL_LONG = "soil";

    public static final String BIOMES = "b";
    public static final String BIOMES_LONG = "biomes";

    public static final String SEED = "g";
    public static final String SEED_LONG = "seed";

    private CommandLine cli;

    public Configuration(String[] args) {
        try {
            this.cli = parser().parse(options(), args);
        } catch (ParseException pe) {
            throw new IllegalArgumentException(pe);
        }
    }

    public Map<String, String> export() {
        Map<String, String> result = new HashMap<>();
        for(Option o: cli.getOptions()){
            result.put(o.getOpt(), o.getValue(""));
        }
        return result;
    }

    public String export(String key) {
        return cli.getOptionValue(key);
    }

    private CommandLineParser parser() {
        return new DefaultParser();
    }

    public String input() {
        return this.cli.getOptionValue(INPUT);
    }
    public String mode() {
        return this.cli.getOptionValue(MODE);
    }

    public String output() {
        return this.cli.getOptionValue(FILENAME, "output.mesh");
    }

    public String elevation() {
        return this.cli.getOptionValue(ELEVATION, "0");
    }

    public String shape() {
        return this.cli.getOptionValue(SHAPE, "0");
    }
    public String biome() {
        return this.cli.getOptionValue(BIOMES, "Land");
    }
    public String lakes() {
        return this.cli.getOptionValue(LAKES, "4");
    }
    public String aquifiers() {
        return this.cli.getOptionValue(AQUIFIERS, "4");
    }
    public String rivers() {
        return this.cli.getOptionValue(RIVERS, "4");
    }
    private Options options() {
        Options options = new Options();
        options.addOption(new Option(INPUT, true, "Input mesh file name"));
        options.addOption(new Option(FILENAME, true, "Output file name"));
        options.addOption(new Option(MODE, MODE_LONG,false, "Mode"));
        options.addOption(new Option(SHAPE, SHAPE_LONG, true, "Shape"));
        options.addOption(new Option(ELEVATION, ELEVATION_LONG,true, "Elevation"));
        options.addOption(new Option(LAKES, LAKES_LONG, true, "Lakes"));
        options.addOption(new Option(RIVERS, RIVERS_LONG, true, "Rivers"));
        options.addOption(new Option(AQUIFIERS, AQUIFIERS_LONG, true, "Aquifiers"));
        options.addOption(new Option(SOIL, SOIL_LONG, false, "Soil"));
        options.addOption(new Option(BIOMES, BIOMES_LONG, true, "Biomes"));
        options.addOption(new Option(SEED, SEED_LONG, false, "Seed"));
        return options;
    }

}