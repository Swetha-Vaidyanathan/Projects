// --== CS400 File Header Information ==--
// Name: Surbhi Yadav
// Email: yadav26@wisc.edu
// Group and Team: F34
// Group TA: Zhuoming Liu
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * This class extends the BaseGraph data structure with additional methods for
 * computing the total cost and list of node data along the shortest path
 * connecting a provided starting to ending nodes. This class makes use of
 * Dijkstra's shortest path algorithm.
 */
public class DijkstraGraph<NodeType, EdgeType extends Number> extends BaseGraph<NodeType, EdgeType>
        implements GraphADT<NodeType, EdgeType> {

    /**
     * While searching for the shortest path between two nodes, a SearchNode
     * contains data about one specific path between the start node and another node
     * in the graph. The final node in this path is stored in its node field. The
     * total cost of this path is stored in its cost field. And the predecessor
     * SearchNode within this path is referened by the predecessor field (this field
     * is null within the SearchNode containing the starting node in its node
     * field).
     *
     * SearchNodes are Comparable and are sorted by cost so that the lowest cost
     * SearchNode has the highest priority within a java.util.PriorityQueue.
     */
    protected class SearchNode implements Comparable<SearchNode> {
        public Node node;
        public double cost;
        public SearchNode predecessor;

        public SearchNode(Node node, double cost, SearchNode predecessor) {
            this.node = node;
            this.cost = cost;
            this.predecessor = predecessor;
        }

        public int compareTo(SearchNode other) {
            if (cost > other.cost)
                return +1;
            if (cost < other.cost)
                return -1;
            return 0;
        }
    }

    /**
     * Constructor that sets the map that the graph uses.
     *
     * @param map the map that the graph uses to map a data object to the node
     *            object it is stored in
     */
    public DijkstraGraph(MapADT<NodeType, Node> map) {
        super(map);
    }

    /**
     * This helper method creates a network of SearchNodes while computing the
     * shortest path between the provided start and end locations. The SearchNode
     * that is returned by this method is represents the end of the shortest path
     * that is found: it's cost is the cost of that shortest path, and the nodes
     * linked together through predecessor references represent all of the nodes
     * along that shortest path (ordered from end to start).
     *
     * @param start the data item in the starting node for the path
     * @param end   the data item in the destination node for the path
     * @return SearchNode for the final end node within the shortest path
     * @throws NoSuchElementException when no path from start to end is found or
     *                                when either start or end data do not
     *                                correspond to a graph node
     */
    protected SearchNode computeShortestPath(NodeType start, NodeType end) throws NoSuchElementException {
        // implement in step 5.3


        if (!this.containsNode(start) || !this.containsNode(end)) {
            throw new NoSuchElementException();

        }

        // sets up the placeholder map and the priority queue
        PlaceholderMap<NodeType, Node> placeholderMap = new PlaceholderMap<NodeType, Node>();
        PriorityQueue<SearchNode> priorityQueue = new PriorityQueue<SearchNode>();

        // initialize the start node
        SearchNode startNode = new SearchNode(new Node(start), 0, null);
        SearchNode currentNode;
        SearchNode nextNode;

        // add startNode
        priorityQueue.add(startNode);

        // while loop for dijkstra's algorithm, pseudocode presented in lecture
        while (!priorityQueue.isEmpty()) {
            currentNode = priorityQueue.remove();

            // if the last node has already been accessed, then the shortest path is done
            if (currentNode.node.data.equals(end)) {
                return currentNode;
            }

            // continuing, node hasn't been visited yet
            if (!placeholderMap.containsKey(currentNode.node.data)) {

                // adds the node to the list of visited notes
                placeholderMap.put(currentNode.node.data, currentNode.node);

                // goes through all the edges and adds successors to the queue (unvisited)
                for (Edge edge : nodes.get(currentNode.node.data).edgesLeaving) {
                    if (!placeholderMap.containsKey(edge.successor.data)) {

                        // fixes the total cost
                        nextNode = new SearchNode(edge.successor, currentNode.cost + edge.data.doubleValue(),
                                currentNode);
                        priorityQueue.add(nextNode);
                    }
                }
            }
        }



        // no node connection from the start to the end
        throw new NoSuchElementException();
    }

    /**
     * Returns the list of data values from nodes along the shortest path from the
     * node with the provided start value through the node with the provided end
     * value. This list of data values starts with the start value, ends with the
     * end value, and contains intermediary values in the order they are encountered
     * while traversing this shorteset path. This method uses Dijkstra's shortest
     * path algorithm to find this solution.
     *
     * @param start the data item in the starting node for the path
     * @param end   the data item in the destination node for the path
     * @return list of data item from node along this shortest path
     */
    public List<NodeType> shortestPathData(NodeType start, NodeType end) throws NoSuchElementException {
        // implement in step 5.4

        SearchNode totalPath = computeShortestPath(start, end);
        List<NodeType> listOfPath = new LinkedList<NodeType>();

        // adds the predecessor to the rest of the list
        while (totalPath != null) {
            listOfPath.add(totalPath.node.data);
            totalPath = totalPath.predecessor;
        }

        // corrects the order of the list
        Collections.reverse(listOfPath);

        return listOfPath;
    }

    /**
     * Returns the cost of the path (sum over edge weights) of the shortest path
     * freom the node containing the start data to the node containing the end data.
     * This method uses Dijkstra's shortest path algorithm to find this solution.
     *
     * @param start the data item in the starting node for the path
     * @param end   the data item in the destination node for the path
     * @return the cost of the shortest path between these nodes
     */
    public double shortestPathCost(NodeType start, NodeType end) throws NoSuchElementException {
        // implement in step 5.4

        SearchNode totalPath = computeShortestPath(start, end);

        return totalPath.cost;
    }

    // JUnit Tests for P23 Mid-Week Submission

    // Create a test that makes use of an example that you traced through in
    // lecture, and confirm that the results of your implementation match what you
    // previously computed by hand.
    @Test
    public void testShortestPath() {
        DijkstraGraph<String, Integer> datagraph = new DijkstraGraph<>(new PlaceholderMap<>());
        datagraph.insertNode("H");
        datagraph.insertNode("B");
        datagraph.insertNode("M");
        datagraph.insertNode("F");
        datagraph.insertNode("G");
        datagraph.insertNode("L");
        datagraph.insertNode("I");
        datagraph.insertNode("A");
        datagraph.insertNode("D");
        datagraph.insertNode("E");

        datagraph.insertEdge("H", "B", 6);
        datagraph.insertEdge("B", "M", 3);
        datagraph.insertEdge("M", "F", 4);
        datagraph.insertEdge("F", "G", 9);
        datagraph.insertEdge("G", "L", 7);
        datagraph.insertEdge("I", "L", 5);
        datagraph.insertEdge("D", "G", 2);
        datagraph.insertEdge("I", "D", 1);
        datagraph.insertEdge("H", "I", 2);
        datagraph.insertEdge("I", "H", 2);
        datagraph.insertEdge("D", "A", 7);
        datagraph.insertEdge("A", "H", 8);
        datagraph.insertEdge("A", "B", 1);
        datagraph.insertEdge("A", "M", 5);
        datagraph.insertEdge("M", "E", 3);

        ArrayList<String> pathData = new ArrayList<>();
        SearchNode current = (SearchNode) datagraph.computeShortestPath("D", "B");

        while (current != null) {
            pathData.add(0, (String) current.node.data);
            current = current.predecessor;
        }

        Assertions.assertEquals("[D, A, B]", pathData.toString());

    }

    // Create another test using the same graph as you did for the test above, but
    // check the cost and sequence of data along the shortest path between a
    // different start and end node.
    @Test
    public void testCheckCostAndSequence() {
        DijkstraGraph<String, Integer> datagraph = new DijkstraGraph<>(new PlaceholderMap<>());
        datagraph.insertNode("H");
        datagraph.insertNode("B");
        datagraph.insertNode("M");
        datagraph.insertNode("F");
        datagraph.insertNode("G");
        datagraph.insertNode("L");
        datagraph.insertNode("I");
        datagraph.insertNode("A");
        datagraph.insertNode("D");
        datagraph.insertNode("E");

        datagraph.insertEdge("H", "B", 6);
        datagraph.insertEdge("B", "M", 3);
        datagraph.insertEdge("M", "F", 4);
        datagraph.insertEdge("F", "G", 9);
        datagraph.insertEdge("G", "L", 7);
        datagraph.insertEdge("I", "L", 5);
        datagraph.insertEdge("D", "G", 2);
        datagraph.insertEdge("I", "D", 1);
        datagraph.insertEdge("H", "I", 2);
        datagraph.insertEdge("I", "H", 2);
        datagraph.insertEdge("D", "A", 7);
        datagraph.insertEdge("A", "H", 8);
        datagraph.insertEdge("A", "B", 1);
        datagraph.insertEdge("A", "M", 5);
        datagraph.insertEdge("M", "E", 3);

        SearchNode current = (SearchNode) datagraph.computeShortestPath("B", "F");

        Assertions.assertEquals(7, current.cost);

    }

    // Create a test that checks the behavior of your implementation when the nodes
    // that you are searching for a path between exist in the graph, but there is no
    // sequence of directed edges that connects them from the start to the end.
    @Test
    public void testCheckBehavior() {
        DijkstraGraph<String, Integer> datagraph = new DijkstraGraph<>(new PlaceholderMap<>());
        datagraph.insertNode("H");
        datagraph.insertNode("B");
        datagraph.insertNode("M");
        datagraph.insertNode("F");
        datagraph.insertNode("G");
        datagraph.insertNode("L");
        datagraph.insertNode("I");
        datagraph.insertNode("A");
        datagraph.insertNode("D");
        datagraph.insertNode("E");

        datagraph.insertEdge("H", "B", 6);
        datagraph.insertEdge("B", "M", 3);
        datagraph.insertEdge("M", "F", 4);
        datagraph.insertEdge("F", "G", 9);
        datagraph.insertEdge("G", "L", 7);
        datagraph.insertEdge("I", "L", 5);a
        datagraph.insertEdge("D", "G", 2);
        datagraph.insertEdge("I", "D", 1);
        datagraph.insertEdge("H", "I", 2);
        datagraph.insertEdge("I", "H", 2);
        datagraph.insertEdge("D", "A", 7);
        datagraph.insertEdge("A", "H", 8);
        datagraph.insertEdge("A", "B", 1);
        datagraph.insertEdge("A", "M", 5);
        datagraph.insertEdge("M", "E", 3);

        // must throw a NoSuchElementException, no connection between nodes
        Assertions.assertThrows(NoSuchElementException.class, () -> datagraph.computeShortestPath("G", "E"));
    }
}