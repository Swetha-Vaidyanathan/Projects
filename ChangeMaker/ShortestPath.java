// --== CS400 File Header Information ==--
// Name: Surbhi Yadav
// Email: yadav26@wisc.edu
// Group and Team: F34
// Group TA: Zhuoming Liu
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.util.List;
public class ShortestPath implements ShortestPathInterface{

    List<String> routes;
    List<Integer> miles;
    int totalMiles;

    public ShortestPath(List<String> routes, List<Integer> miles, int totalMiles) {
        this.routes = routes;
        this.miles = miles;
        this.totalMiles = totalMiles;
    }

    @Override
    public List<String> getRoute() {
        return routes;
    }

    @Override
    public List<Integer> getSegmentMiles() {
        return miles;
    }

    @Override
    public int getTotalMiles() {
        return totalMiles;
    }

}
