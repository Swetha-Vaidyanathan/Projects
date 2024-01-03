// --== CS400 File Header Information ==--
// Name: <Swetha Vaidyanathan>
// Email: <vaidyanatha4@wisc.edu>
// Group and Team: <F34>
// Group TA: <Zhuoming Liu>
// Lecturer: <Gary Dahl>
// Notes to Grader: <optional extra notes>

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class provides with junit tests that checks the functionality
 * of the frontend class using the TextUITester class.
 */
public class FrontendDeveloperTests {

    /**
     * This test checks the constructor of the frontend class and checks if the instances are passed correctly.
     */
    @Test
    public void testFrontendInitialization() {
        try {
            //Instances of scanner and backend are initiated.
            Scanner scanner = new Scanner(System.in);
            PlaceholderMap map = new PlaceholderMap();
            Frontend frontend = new Frontend(new Backend(new DijkstraGraph<>(map)), scanner);
            //Makes sure the constructor is passed and the frontend instance is not null.
            Assertions.assertNotNull(frontend, "Initialization of Frontend instance failed.");
        } catch (Exception e) {
            Assertions.fail("Unexpected exception: " + e.getMessage());
        }
    }

    /**
     * This test checks the exit method of the frontend class and uses TestUITester for programInput
     * and checks if the output contains the expected message.
     */
    @Test
    public void testExitMethod() {
        // Create a new TextUITester object with input "4\n"
        TextUITester tester = new TextUITester("4\n");

        Scanner scanner = new Scanner(System.in);
        PlaceholderMap map = new PlaceholderMap();
        Frontend frontend = new Frontend(new Backend(new DijkstraGraph<>(map)), scanner);
        frontend.start();  // This will invoke the main menu and exit immediately

        // Capture the output after the exit method
        String output = tester.checkOutput();
        System.out.println(output);

        // Check if exit message was printed
        assertTrue(output.contains("Exiting Flight Router. Goodbye!"));
    }

    /**
     * This tester checks the loadFile method of the frontend and uses the TextUITester class to take in
     * programInput and check of the output contains the expected message.
     */

    @Test
    public void testValidFileLoading() {
        // Creates instance of TextUITester, backend, and Scanner.
        TextUITester tester = new TextUITester("1\nC:\\Users\\SWETHA\\IdeaProjects\\p2\\src\\flights.dot\n");
        Scanner scanner = new Scanner(System.in);
        PlaceholderMap map = new PlaceholderMap();
        Frontend frontend = new Frontend(new Backend(new DijkstraGraph<>(map)), scanner);
        scanner.next();
        try {
            // Checks for a valid file
            frontend.loadFile(scanner.next());
            String output = tester.checkOutput();
            assertTrue(output.contains("File loaded successfully"));
        } catch (FileNotFoundException e) {
            Assertions.fail("Unexpected exception: " + e.getMessage());
        }

    }

    /**
     * The test checks the displayStats() method of the frontend and uses TextUITester class to take in
     * programInput and checks if the output contains the expected message.
     */
    @Test
    public void testDisplayStats() {
        //Instances of TextUITester, Scanner and backend is initiated.
        TextUITester tester = new TextUITester("2\n");
        Scanner scanner = new Scanner(System.in);
        PlaceholderMap map = new PlaceholderMap();
        Frontend frontend = new Frontend(new Backend(new DijkstraGraph<>(map)), scanner);
        frontend.displayStats();
        //stores the output for thr given programInput.
        String output = tester.checkOutput();
        //System.out.println(output);
        assertTrue(output.contains("Flight Statistics:"));
    }

    /**
     * This test checks the shortestPath() method of frontend and uses TestUITester class to take in
     * programInput and checks if the output contains the expected message.
     */

    @Test
    public void testShortestPath() throws FileNotFoundException {
        //Instances of TextUITester, Scanner and backend is initiated.
        TextUITester tester = new TextUITester("1\n./src/flights.dot\n");
        Scanner scnr = new Scanner(System.in);
        PlaceholderMap map = new PlaceholderMap();
        Frontend frontend = new Frontend(new Backend(new DijkstraGraph<>(map)), scnr);
        scnr.next();

            frontend.loadFile(scnr.next());
            frontend.shortestPath("ORD", "RNO");
            String output = tester.checkOutput();
        System.out.println(output);
            String expected = "The Shortest Path is: ";
            assertTrue(output.contains(expected));
        }


    /**
     * This test checks the integration of frontend with backend
     * and calls the backend in the frontend instance and checks the
     * routeInformation() method.
     */

    @Test
    public void integrationTest1() {
        // Create an instance of your backend
        Scanner scanner = new Scanner(System.in);
        PlaceholderMap map = new PlaceholderMap();
        Frontend frontend = new Frontend(new Backend(new DijkstraGraph<>(map)), scanner);

        // Simulate user input (e.g., selecting the display stats option)
        TextUITester tester = new TextUITester("2\n");

        // Execute the displayStats method
        frontend.displayStats();

        // Check the output or perform assertions as needed
        String output = tester.checkOutput();
        assertTrue(output.contains("# of airports in the data set: "));
    }

    /**
     * This test checks the integration of frontend with backend
     * and calls the backend in the frontend instance and checks the readData() method.
     */

    @Test
    public void integrationTest2() {
        // Create an instance of DijkstraGraph and Backend
        DijkstraGraph<String, Integer> graph = new DijkstraGraph<>(new PlaceholderMap<>());
        Backend backend = new Backend(graph);
        // Define start and destination airports
        String startAirport = "ORD";
        String destinationAirport = "SFO";
        try {
            backend.readData("./src/flights.dot");
            // Get the shortest path
            ShortestPath shortestPath = backend.getShortestRoute(startAirport, destinationAirport);
            // Define expected results
            String expectedRoute = "[ORD, OAK, SFO]";
            // Assert the results
            assert (shortestPath.getRoute().toString().contains(expectedRoute));
            // catch any exceptions
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
