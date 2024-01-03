// --== CS400 File Header Information ==--
// Name: Surbhi Yadav
// Email: yadav26@wisc.edu
// Group and Team: F34
// Group TA: Zhuoming Liu
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Backend implements BackendInterface {

    // class fields
    private DijkstraGraph<String, Integer> routeGraph;
    private int total = 0;

    /**
     * main constructor
     *
     * @param routeGraph - the graph that holds the route information
     */
    public Backend(DijkstraGraph<String, Integer> routeGraph) {
        this.routeGraph = routeGraph;
    }

    /**
     * @param fileName name of the file to read
     * @return true if data is read in properly, false if not
     * @throws FileNotFoundException
     */
    @Override
    public boolean readData(String fileName){

        ArrayList<String> currLine;
        String airport1;
        String airport2;
        String miles;
        String line;

        try {
            Scanner scanner = new Scanner(new File(fileName));
            Scanner subScanner;
            scanner.nextLine();

            while (scanner.hasNextLine()) {

                line = scanner.nextLine();

                if (!line.contains("--")) {
                    break;
                }

                subScanner = new Scanner(line);

                currLine = new ArrayList<String>();
                while (subScanner.hasNext()) {
                    currLine.add(subScanner.next());
                }

                if (currLine.size() == 4) {
                    airport1 = currLine.get(0).trim().replace("\"", "");

                    airport2 = currLine.get(2).trim().replace("\"", "");

                    miles = currLine.get(3).trim().replace("[miles=", "").replace("];", "");

                    routeGraph.insertNode(airport1);
                    routeGraph.insertNode(airport2);
                    routeGraph.insertEdge(airport1, airport2, Integer.parseInt(miles));
                    routeGraph.insertEdge(airport2, airport1, Integer.parseInt(miles));
                    total += Integer.parseInt(miles);
                }

                subScanner.close();
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            return false;
        }

        return true;
    }

    @Override
    public ShortestPath getShortestRoute(String departureAirport, String arrivalAirport) {

        List<String> routes = new ArrayList<String>();
        List<Integer> miles = new ArrayList<Integer>();
        int totalMiles;

        routes = routeGraph.shortestPathData(departureAirport, arrivalAirport);

        // add to miles list
        for (int i = 0; i < routes.size() - 1; i++) {
            miles.add((Integer) routeGraph.getEdge(routes.get(i), routes.get(i + 1)));
        }

        totalMiles = (int) routeGraph.shortestPathCost(departureAirport, arrivalAirport);

        return new ShortestPath(routes, miles, totalMiles);
    }

    @Override
    public String routeInformation() {

        return "# of airports in the data set: " + routeGraph.getNodeCount() + "\n" + "# of flights in the data set: "
                + routeGraph.getEdgeCount() + "\n" + "Total # of miles in the data set: " + total + "\n";
    }
}