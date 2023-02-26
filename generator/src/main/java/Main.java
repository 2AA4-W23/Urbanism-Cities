import ca.mcmaster.cas.se2aa4.a2.generator.DotGen;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import java.io.IOException;
import java.io.PrintWriter;

public class Main {

    // Determines square grid or irregular mesh
    private static boolean grid = false;

    // Options for user
    private static final Option ARG_GRID = new Option("g", "grid", false, "Produce a square grid");
    private static final Option ARG_IRREGULAR = new Option("i", "irregular", false, "Produce an irregular mesh");

    // Help menu
    private static void printHelp(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        PrintWriter pw = new PrintWriter(System.out);
        pw.println("Mesh command line");
        pw.println("If no value is entered, program will create an irregular mesh on default.");
        pw.println();
        formatter.printUsage(pw, 100, "java -jar generator.jar sample.mesh [options] input1");
        formatter.printOptions(pw, 100, options, 2, 5);
        pw.println("");
        pw.close();
    }

    public static void main(String[] args) throws IOException {

        CommandLineParser clp = new DefaultParser();

        Options options = new Options();
        options.addOption(ARG_GRID);
        options.addOption(ARG_IRREGULAR);

        try {
            CommandLine cl = clp.parse(options, args);

            if (cl.hasOption(ARG_GRID.getLongOpt())) {
                grid = true;
            } else if (cl.hasOption(ARG_IRREGULAR.getLongOpt())) {
                grid = false;
            } else if (cl.equals("-h") || cl.equals("--help")) {
                printHelp(options);
            } else {
                printHelp(options);
            }
        } catch (Exception e) {
            printHelp(options);
        }

        DotGen generator = new DotGen(grid);
        Mesh myMesh = generator.generate();
        MeshFactory factory = new MeshFactory();
        factory.write(myMesh, args[0]);
    }

}
