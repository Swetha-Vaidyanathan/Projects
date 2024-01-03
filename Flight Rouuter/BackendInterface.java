// --== CS400 File Header Information ==--
// Name: Surbhi Yadav
// Email: yadav26@wisc.edu
// Group and Team: F34
// Group TA: Zhuoming Liu
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.io.FileNotFoundException;
import java.util.List;
public interface BackendInterface {
    /**
     * constructor for backend
     * @param routeGraph GraphADT object storing data from file
     */
    /*
    public IndividualBackendInterface(GraphADT<String, int> routeGraph) {
        this.routeGraph = routeGraph;
    }
    */

    /**
     * @param fileName name of the file to read
     * @return true if data is read in properly, false if not
     * @throws FileNotFoundException
     */
    public boolean readData(String fileName) throws FileNotFoundException;

    /**
     * uses ShortestPath class to find shortest path of the route
     * @param departureAirport original departure airport
     * @param arrivalAirport final arrival airport
     * @return the list of airports traveled to along path
     */
    public ShortestPath getShortestRoute(String departureAirport, String arrivalAirport);


    /**
     * @return String containing
     * number of airports(nodes) traveled to
     * number of flights(edges) traveled along
     * number of miles traveled along journey
     */
    public String routeInformation();

}

