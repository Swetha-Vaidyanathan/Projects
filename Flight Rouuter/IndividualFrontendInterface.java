import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.List;
/**
 * This Interface represents the frontend interface of the project
 * Flight Router. This provides with different methods to run and display the main loop.
 */
public interface IndividualFrontendInterface {
    /*
    public FrontendInterface(Backend backend, Scanner scanner){
        this.backend = backend;
        this.scanner = scanner;
    }
     */

    /**
     * This method starts the main command loop which the backend will access later.
     */
    public void start();

    /**
     * This menu displays the main menu to the user and lets them access the commands
     * required to them.
     */
    public void displayMainMenu();

    /**
     * This method loads the file provided by the user and loads the contents of it.
     * @throws FileNotFoundException if the file provided by the user is not found.
     */
    public void loadFile(String fileName) throws FileNotFoundException;

    /**
     * This method displays the statistics dataset that includes the number of airports,
     * thu number of edges, and the total number of miles.
     */
    public void displayStatistics();

    /**
     * This method displays the shortest route taken from the start to the destination inputted
     * by the user. This displays all the airports, distance and the total number of miles.
     * @param startDestination
     * @param finalDestination
     */
    public void shortestPath(String startDestination, String finalDestination);

    /**
     * This method exits out of the main command loop
     */
    public void exit();
}
