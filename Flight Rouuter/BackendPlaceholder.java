// --== CS400 File Header Information ==--
// Name: <Swetha Vaidyanathan>
// Email: <vaidyanatha4@wisc.edu>
// Group and Team: <F34>
// Group TA: <Zhuoming Liu>
// Lecturer: <Gary Dahl>
// Notes to Grader: <optional extra notes>
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is BackendPlaceholder class that imitates the functioning of the backend class.
 */
public class BackendPlaceholder implements BackendInterface{
    //private GraphADT<String, Integer> routeGraph;

    // Constructor for the placeholder class
    //public BackendPlaceholder(GraphADT<String, Integer> routeGraph) {
      //  this.routeGraph = routeGraph;
    //}

    /**
     * This method reads data in the file specified by the user
     * @param fileName name of the file to read
     * @return
     */
    @Override
    public boolean readData(String fileName) {
        return true;
    }

    /**
     * This method gets the shortest Path based on the departure and the arrival airport.
     * @param departureAirport original departure airport
     * @param arrivalAirport final arrival airport
     * @return
     */
    @Override
    public ShortestPath getShortestRoute(String departureAirport, String arrivalAirport) {
        return null;
    }

    /**
     * This method provides the flight statistics.
     * @return a string message displaying the flight statistics.
     */

    @Override
    public String routeInformation() {
        return "Flight Statistics:";
    }
}
