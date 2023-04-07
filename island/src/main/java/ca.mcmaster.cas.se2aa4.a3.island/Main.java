package ca.mcmaster.cas.se2aa4.a3.island;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.configuration.Configuration;
import ca.mcmaster.cas.se2aa4.a3.island.enricher.Island;
import ca.mcmaster.cas.se2aa4.a3.island.enricher.Sandbox;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {

    private static final String DICTIONARY_FILE_NAME = "dictionary.ser";
    private static Map<String, Structs.Mesh> outputFiles = new HashMap<>();

    private static void saveDictionary(Map<String, Structs.Mesh> dictionary) {
        try {
            FileOutputStream fileOut = new FileOutputStream(DICTIONARY_FILE_NAME);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(dictionary);
            out.close();
            fileOut.close();
            //System.out.println("Dictionary saved to " + DICTIONARY_FILE_NAME);
        } catch (IOException e) {
            //System.err.println("Error saving dictionary to file: " + e.getMessage());
        }
    }

    private static void loadDictionary() {
        try {
            FileInputStream fileIn = new FileInputStream(DICTIONARY_FILE_NAME);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            outputFiles = (Map<String, Structs.Mesh>) in.readObject();
            in.close();
            fileIn.close();
            //System.out.println("Dictionary loaded from " + DICTIONARY_FILE_NAME);
        } catch (IOException e) {
            //System.err.println("Error loading dictionary from file: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            //System.err.println("Error loading dictionary from file: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        loadDictionary();
        Configuration config = new Configuration(args);
        Structs.Mesh aMesh = new MeshFactory().read(config.input());
        Structs.Mesh island;
        if(config.export().containsKey(Configuration.MODE)) {
            island = new Sandbox(aMesh).buildNewMesh();
        } else {
            Random rand = new Random();
            int seed = rand.nextInt(1000000 - 1 + 1) + 1;

            if (outputFiles.containsKey(config.seed())) {
                island = outputFiles.get(String.valueOf(config.seed()));
            } else {
                island = new Island(aMesh, config.shape(), config.elevation(), config.biome(), config.lakes(), config.aquifiers(), config.rivers(), config.soil(), config.cities()).buildNewMesh();
                outputFiles.put(String.valueOf(seed), island);
                saveDictionary(outputFiles);
                System.out.println("SEED FOR REGENERATION: " + seed);
                System.out.println("SEGMENT 0: " + aMesh.getSegmentsList().get(0));
            }
        }
        new MeshFactory().write(island, config.export(Configuration.FILENAME));
    }
}