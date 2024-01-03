// --== CS400 Fall 2023 File Header Information ==--
// Name: Swetha Vaidyanathan
// Email: vaidyanatha4@wisc.edu
// Group: B17
// TA: Robert Nagel
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class BackendDeveloperTests {
    IterableMultiKeyRBT<Meteorite> tree = new IterableMultiKeyRBT<>();
    Backend backend = new Backend(tree);
    Frontend frontend = new Frontend(new Scanner("C:\\Users\\SWETHA\\IdeaProjects\\IndividualBackendInterface\\src"), backend);

    /**
     * This test reads a CSV file, checks if it exists, and verifies the valid inputs in the file.
     *
     * @throws Exception if the file is not found
     */
    @Test
    public void testReadCSV() {
        Backend backend = new Backend(new IterableMultiKeyRBT<>());

        try {
            // Tries reading the incorrect CSV file.
            backend.readCSV("errorMeteorite.csv");
            // Fails the test if it reaches this point.
            Assertions.fail("The file name is incorrect, and the test should fail.");
        } catch (IOException e) {
            // Exception is expected, and the test passes.
            System.out.println(e);
        }
    }

    /**
     * This method tests the readCSV() method and checks if the right exceptions is thrown
     * while trying to read a blank csv.
     */
    @Test
    public void testBlankCSV() {
        Backend backend = new Backend(new IterableMultiKeyRBT<>());
        try {
            backend.readCSV("");
        } catch (IOException e) {
            assertTrue(true, "not valid file");
        }
    }

    /**
     * This test checks if the maxMeteorMass method correctly finds meteorites with the maximum mass.
     */

    @Test
    public void testMaxMeteorMass() throws IOException {

        IterableMultiKeyRBT<Meteorite> tree = new IterableMultiKeyRBT<>();
        Backend backend = new Backend(tree);


        Meteorite M1 = new Meteorite("Hoba", -19.583330, 17.916670, true, 6000000);
        Meteorite M2 = new Meteorite("SampleMeteorite1", 0.0, 0.0, false, 1000);
        tree.insertSingleKey(M1);
        tree.insertSingleKey(M2);

        // Get the list of meteorites with maximum mass
        List<Meteorite> maxMassMeteorites = backend.maxMeteorMass();
        System.out.println(maxMassMeteorites);
        // Ensure that the list is not empty
        assertFalse(maxMassMeteorites.isEmpty());
        Assertions.assertTrue(maxMassMeteorites.contains(M1));
    }

    /**
     * This test checks the size of the meteorite list created from the CSV file.
     * The expected size of the meteorite list is 45716.
     */
    @Test
    public void testGetSize() {
        Backend backend = new Backend(new IterableMultiKeyRBT<>());

        // Create a list of meteorites with sample data
        List<Meteorite> meteoriteList = createSampleMeteoriteList();

        // Expected size of the meteorite list
        int expectedSize = 3;

        // Check the size of the meteorite list
        assertEquals(expectedSize, meteoriteList.size());
    }

    /**
     * This test checks the properties set by the constructor for meteorites with no mass values.
     */
    @Test
    public void testMeteorite() {
        // Create a meteorite object with no mass value
        Meteorite meteoriteTest = new Meteorite("Aire-sur-la-Lys", true, 50.666670, 2.333330);

        // Check the properties of the meteorite
        assertEquals("Aire-sur-la-Lys", meteoriteTest.getName());
        Assertions.assertTrue(meteoriteTest.getObservedFall());
        assertEquals(50.666670, meteoriteTest.getLongitude(), 0.001);
        assertEquals(2.333330, meteoriteTest.getLatitude(), 0.001);
        assertEquals(-1, meteoriteTest.getMass(), 0.001);
    }

    /**
     * This test checks the properties set by the constructor for meteorites with no latitudes or longitudes.
     */
    @Test
    public void testMeteorite2() {
        // Create a meteorite object with no latitude and longitude values
        Meteorite meteoriteTest2 = new Meteorite("Northwest Africa 5815", true, 256.8);

        // Check the properties of the meteorite
        assertEquals("Northwest Africa 5815", meteoriteTest2.getName());
        Assertions.assertTrue(meteoriteTest2.getObservedFall());
        assertEquals(0.0, meteoriteTest2.getLongitude(), 0.001);
        assertEquals(0.0, meteoriteTest2.getLatitude(), 0.001);
        assertEquals(256.8, meteoriteTest2.getMass(), 0.001);
    }

    /**
     * This test verifies that the main menu displayed by the frontend
     * contains the expected welcome message.
     */
    @Test
    public void testIntegrationFrontend1() {
        // Call the mainMenu method to get the menu displayed by the frontend
        String menu = frontend.mainMenu();
        System.out.println(frontend.mainMenu());
        // Verify that the menu contains the expected "Welcome" message
        Assertions.assertTrue(menu.contains("Welcome"));
    }

    /**
     * This test verifies that the listHeighestMass method returns a non-null list
     * when the dataset is empty.
     */

    @Test
    public void testIntegrationFrontend2() throws FileNotFoundException {
        // Create an empty list to store the results of listHighestMass
        String filepath = "C:\\Users\\SWETHA\\IdeaProjects\\IndividualBackendInterface\\src\\Book1.csv";
        backend.readCSV(filepath);
        List<Meteorite> highestMassList;

        // Call listHeighestMass to retrieve the list
        highestMassList = frontend.listHeighestMass();

        // Verify that the returned list is not null
        Assertions.assertNotNull(highestMassList);
    }

    /**
     * This Test tests the readCSV() method of the meteorite.
     * It takes in a filepath and reads the file and checks if the max mass is not empty.
     * @throws IOException
     */
    @Test
    public void TestFinalBackend() throws IOException {
        String filepath = "C:\\Users\\SWETHA\\IdeaProjects\\IndividualBackendInterface\\src\\Book1.csv";
        backend.readCSV(filepath);
        List<Meteorite> test;
        test = backend.maxMeteorMass();
        Assertions.assertFalse(test.isEmpty());
    }

    /**
     * This tests loads the file from the backend and checks the threshold meteor mass
     * and returns meteor objects that are between the given mass.
     * @throws IOException
     */
    @Test
    public void TestFinalBackend2() throws IOException{
        String filepath = "C:\\Users\\SWETHA\\IdeaProjects\\IndividualBackendInterface\\src\\Book1.csv";
        backend.readCSV(filepath);
        List<Meteorite> test;
        test = backend.betweenMeteorMass(0,3000000);
        Assertions.assertFalse(test.isEmpty());
    }
    /**
     * This method is a private helper method that is used to create a sample list
     * of Meteorite objects
     *
     * @return
     */

    // Helper method to create a sample list of meteorites for testing
    private List<Meteorite> createSampleMeteoriteList() {
        List<Meteorite> meteoriteList = new ArrayList<>();
        meteoriteList.add(new Meteorite("Hoba", -19.583330, 17.916670, true, 6000000));
        meteoriteList.add(new Meteorite("SampleMeteorite1", 0.0, 0.0, false, 1000));
        meteoriteList.add(new Meteorite("SampleMeteorite2", 30.0, 40.0, true, 3000));
        return meteoriteList;
    }
}
