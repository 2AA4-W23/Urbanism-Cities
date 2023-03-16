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

    private Options options() {
        Options options = new Options();
        options.addOption(new Option(INPUT, true, "Input mesh file name"));
        options.addOption(new Option(FILENAME, true, "Output file name"));
        options.addOption(new Option(MODE, MODE_LONG,true, "Mode"));
//        options.addOption(new Option(SHAPE, true, "Shape"));
        return options;
    }

}
