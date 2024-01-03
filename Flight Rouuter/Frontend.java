// --== CS400 File Header Information ==--
// Name: <Swetha Vaidyanathan>
// Email: <vaidyanatha4@wisc.edu>
// Group and Team: <F34>
// Group TA: <Zhuoming Liu>
// Lecturer: <Gary Dahl>
// Notes to Grader: <optional extra notes>

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * This class implements the FrontendInterface and provides a user interface for
 * the flight destination app.
 */
public class Frontend implements FrontendInterface {
    //This is a scanner instance for the frontend.
    Scanner scanner;
    //This is a backend instance for the frontend.
    Backend backend;
    //This stores the loopState of the mainMenu.
    private Boolean loopState;

    /**
     * This is a constructor for the frontend and takes 2 arguments.
     *
     * @param backend
     * @param scanner
     */
    public Frontend(Backend backend, Scanner scanner) {
        this.scanner = scanner;
        this.backend = backend;
    }

    /**
     * This method starts the main command loop which the backend will access later.
     */
    @Override
    public void start() {
        //sets the loopState to true
        loopState = true;
        //runs the mainMenu while the loopState is true
        while (loopState) {
            mainMenu();
        }
    }

    /**
     * This menu displays the main menu to the user and lets them access the commands
     * required to them.
     */
    @Override
    public void mainMenu() {
        System.out.println("Welcome to the Flight Router App! Here is the main menu");
        System.out.println("1. Load File");
        System.out.println("2. Display Stats");
        System.out.println("3. Find Shortest Path");
        System.out.println("4. Exit");


        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character
        //Takes in user input and does the commands accordingly.
        switch (choice) {
            case 1:
                System.out.print("Enter file name: ");
                String fileName = scanner.nextLine().trim();
                try {
                    loadFile(fileName);
                } catch (FileNotFoundException e) {
                    System.out.println("File not found.");
                }
                break;
            case 2:
                displayStats();
                break;
            case 3:
                System.out.print("Enter start airport: ");
                String startAirport = scanner.nextLine();
                System.out.print("Enter destination airport: ");
                String destinationAirport = scanner.nextLine();
                try {
                    List<String> path = shortestPath(startAirport, destinationAirport);
                    System.out.println("Shortest path: " + path);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid input. " + e.getMessage());
                }
                break;
            case 4:
                exit();
                break;
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 4.");
        }

        if (choice != 4) {
            loopState = true;
        } else {
            loopState = false;
        }
    }

    /**
     * This method takes in input from the user and loads the file specified by them.
     *
     * @param fileName The name of the file to load.
     * @throws FileNotFoundException
     */
    @Override
    public void loadFile(String fileName) throws FileNotFoundException {
        if (fileName == null || fileName.trim().isEmpty()) {
            throw new IllegalArgumentException("File name cannot be null or empty");
        }
        if(backend.readData(fileName)) {
            System.out.println("File loaded successfully");
        }
        else {
            throw new FileNotFoundException("The file provided is not found");
        }
    }


    /**
     * This method displays the statistics dataset that includes the number of airports,
     * the number of edges, and the total number of miles.
     */
    @Override
    public void displayStats() {
        //Calls the display statistics method.
        System.out.println("Flight Statistics:");
        System.out.println(backend.routeInformation());
    }

    /**
     * This method displays the shortest route taken from the start to the destination inputted
     * by the user. This displays all the airports, distance and the total number of miles.
     *
     * @param startAirport
     * @param destinationAirport
     */
    @Override
    public List<String> shortestPath(String startAirport, String destinationAirport) throws IllegalArgumentException {
        System.out.println("The Shortest Path is: ");

        // Get the ShortestPath object from the backend
        ShortestPath shortestPath = backend.getShortestRoute(startAirport, destinationAirport);

        // Print the airports
        for (String airport : shortestPath.getRoute()) {
             System.out.println(airport);
        }

        // Return the list of airports
        return shortestPath.getRoute();
    }

    /**
     * This method exits out of the main command loop
     */
    @Override
    public void exit() {
        System.out.println("Exiting Flight Router. Goodbye!");
        loopState = false;
    }

    /**
     * This is a main method for the frontend class that starts the app by starting the start() method.
     *
     * @param args
     */
    public static void main(String[] args) {
        PlaceholderMap map = new PlaceholderMap();
       Frontend frontend = new Frontend(new Backend(new DijkstraGraph<>(map)), new Scanner(System.in));
       frontend.start();
    }
}
