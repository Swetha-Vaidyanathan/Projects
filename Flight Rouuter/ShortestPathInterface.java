import java.util.List;

/**
 * This interface stores the results of a shortest path search
 * @authors: Ari, Darren, Surbhi
 */
public interface ShortestPathInterface {

    //public String departureAirport;  //airport of original departure
    //public String arrivalAirport;  //airport of final arrival
    
    /*
    public ShortestPathInterface() {
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport
    }
    */

    /**
     * @return list of airports traveled to along the route
     */
    public List<String> getRoute();

    /**
     * @return list of miles traveled along each segment of the route
     */
    public List<Integer> getSegmentMiles();

    /**
     * @return total miles traveled along entire journey
     */
    public int getTotalMiles();
}
