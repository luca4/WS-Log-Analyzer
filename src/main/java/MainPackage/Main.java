package MainPackage;

import GUI.GUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class Main {

    public static void main(String[] args){

        //Sanitize raw file
        JarRunner runner = new JarRunner("./Datasets/data.txt");
        try {
            runner.run();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("Jawk file not found!");
            return;
        }

        //Start in advance the analyzer service, in order to give it time to load (as declared in the library's doc)
        UserAgentAnalyzer.getInstance();

        //Read sanitized file
        LogReader logReader = new LogReader(new File("sanitized.txt"));
        logReader.start();

        //Compute statistics
        StatCalculator statsCalculator = new StatCalculator();
        BasicStats stats = statsCalculator.calculate();

        KMeansExecutor KMeansExecutor = new KMeansExecutor("GeoLite2-City.mmdb");
        HashMap<Coordinate, Integer> points = KMeansExecutor.calculate();

        //Visualize results
        new GUI(stats, points);
    }
}