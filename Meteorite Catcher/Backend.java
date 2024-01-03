// --== CS400 Fall 2023 File Header Information ==--
// Name: Swetha Vaidyanathan
// Email: vaidyanatha4@wisc.edu
// Group: B17
// TA: Robert Nagel
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.io.*;
import java.util.*;

public class Backend implements BackendInterface {
    protected IterableMultiKeyRBT<Meteorite> collection;

    public Backend(IterableMultiKeyRBT<Meteorite> collection) {
        this.collection = collection;
    }

    /**
     * This method reads the CSV data from the file and stores it in the collection.
     *
     * @param filepath
     * @throws IOException is thrown when the file is empty or has invalid data.
     */
    public void readCSV(String filepath) throws FileNotFoundException {

        String fileName = filepath;
        String line = "";
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine(); // Skip the header line

            while ((line = bufferedReader.readLine()) != null) {
                String[] columns = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                String name = columns[0];
                boolean fall = columns[5].equals("Fell");
                double longitude = Double.parseDouble(columns[8]);
                double latitude = Double.parseDouble(columns[7]);
                double mass = Double.parseDouble(columns[4]);

                // Create the Meteorite object from the extracted information
                Meteorite newMeteorite = new Meteorite(name, fall, longitude, latitude, mass);
                collection.insertSingleKey(newMeteorite);
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } catch (IOException e) {

        }
    }

    /**
     * This method returns the meteorite object with the maximum mass.
     *
     * @return a list of meteorites with the maximum mass
     */
    public List<Meteorite> maxMeteorMass() {
        double maxMass = 0;
        List<Meteorite> maxMassMeteorites = new ArrayList<>();
        Iterator<Meteorite> iterator = collection.iterator();

        while (iterator.hasNext()) {
            Meteorite meteorite = iterator.next();
            double mass = meteorite.getMass();

            if (mass > maxMass) {
                maxMass = mass;
                maxMassMeteorites.clear();
                maxMassMeteorites.add(meteorite);
            } else if (mass == maxMass) {
                maxMassMeteorites.add(meteorite);
            }
        }

        System.out.println(maxMassMeteorites);
        return maxMassMeteorites;
    }

    /**
     * This method returns a list of meteorites with mass within the specified range.
     *
     * @param lowest  the minimum mass
     * @param highest the maximum mass
     * @return a list of meteorites within the specified mass range
     */
    public List<Meteorite> betweenMeteorMass(double lowest, double highest) {
        List<Meteorite> meteoriteRange = new ArrayList<>();
        Iterator<Meteorite> iterator = collection.iterator();

        while (iterator.hasNext()) {
            Meteorite meteorite = iterator.next();
            double mass = meteorite.getMass();

            if (mass >= lowest && mass <= highest) {
                meteoriteRange.add(meteorite);
            }
        }

        System.out.println(meteoriteRange);
        return meteoriteRange;
    }

    /**
     * This main method runs the frontend of the object which gets the backend instance of the object
     * and runs the app.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        IterableMultiKeyRBT<Meteorite> collection = new IterableMultiKeyRBT<>();
        Scanner scnr = new Scanner(System.in);
        Backend backend = new Backend(collection); // Instantiate your Backend with IterableMultiKeyRBT
        // backend.readCSV("C:\\Users\\SWETHA\\IdeaProjects\\IndividualBackendInterface\\src\\meteorites.csv");
        Frontend frontend = new Frontend(scnr, backend); // Instantiate your Frontend

        // Start the main command loop
        try {
            frontend.commandLoop();
        } catch (IOException e) {
            System.err.println("An error occurred during the command loop: " + e.getMessage());
        }
    }
}
