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

    public static final String CITIES = "c";
    public static final String CITIES_LONG = "cities";

    public static final String HELP = "help";

    private CommandLine cli;

    public Configuration(String[] args) {
        try {
            this.cli = parser().parse(options(), args);
            if (cli.hasOption(HELP)) {
                help();
            }
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

    private void help() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("java -jar island.jar", options());
        System.exit(0);
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
        return this.cli.getOptionValue(LAKES, "0");
    }
    public String aquifiers() {
        return this.cli.getOptionValue(AQUIFIERS, "0");
    }
    public String rivers() {
        return this.cli.getOptionValue(RIVERS, "0");
    }
    public String seed() {
        return this.cli.getOptionValue(SEED, "0");
    }
    public String soil() {
        return this.cli.getOptionValue(SOIL, "dry");
    }
    private Options options() {
        Options options = new Options();
        options.addOption(new Option(INPUT, true, "Input mesh file name"));
        options.addOption(new Option(FILENAME, true, "Output file name"));
        options.addOption(new Option(MODE, MODE_LONG,true, "Mode"));
        options.addOption(new Option(SHAPE, SHAPE_LONG, true, "Shape"));
        options.addOption(new Option(ELEVATION, ELEVATION_LONG,true, "Elevation"));
        options.addOption(new Option(LAKES, LAKES_LONG, true, "Lakes"));
        options.addOption(new Option(RIVERS, RIVERS_LONG, true, "Rivers"));
        options.addOption(new Option(AQUIFIERS, AQUIFIERS_LONG, true, "Aquifiers"));
        options.addOption(new Option(SOIL, SOIL_LONG, true, "Soil"));
        options.addOption(new Option(BIOMES, BIOMES_LONG, true, "Biomes"));
        options.addOption(new Option(SEED, SEED_LONG, true, "Seed"));
        options.addOption(new Option(CITIES, CITIES_LONG, true, "Cities"));
        options.addOption(new Option(HELP, false, "print help message"));
        return options;
    }

}