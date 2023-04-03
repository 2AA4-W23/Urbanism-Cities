package ca.mcmaster.cas.se2aa4.a4.pathfinder.tests.shortestpath;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.tests.shortestpath.testsuite.TestSuite;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import junit.runner.Version;

public class Main {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestSuite.class);

        System.out.println("FAILURES: " + result.getFailures());
        System.out.println("IS TEST SUITE SUCCESSFUL?: " + result.wasSuccessful());
        System.out.println("NUMBER OF TESTS: " + result.getRunCount());
        System.out.println("VERSION: " + Version.id());
    }
}
