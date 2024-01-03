import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class PlaceholderClassBD<T extends Comparable<T>> implements IterableMultiKeySortedCollectionInterface<T> {
public PlaceholderClassBD(){

    }
    private ArrayList<T> list = new ArrayList<>();

    /**
     * This method inserts the kry into the tree such that it can hold list of multiple
     * objects in each node.
     * @param key object to insert
     * @return
     */
    @Override
    public boolean insertSingleKey(T key) {
        return list.add(key);
    }

    /**
     * Returns the number of keys in the tree.
     * @return
     */
    @Override
    public int numKeys() {
        return list.size();
    }

    /**
     * This iterator implements an in-order transversal of the tree.
     * @return
     */
    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    /**
     * Sets the starting point for iterations. Future iterations will start at the
     * starting point or the key closest to it in the tree. This setting is remembered
     * until it is reset. Passing in null disables the starting point.
     * @param startPoint the start point to set for iterations
     */
    @Override
    public void setIterationStartPoint(Comparable<T> startPoint) {

    }

    /**
     * This method inserts the key into the tree.
     * @param data
     * @return
     * @throws NullPointerException if there key is null.
     * @throws IllegalArgumentException if the key is of unacceptable datatype.
     */

    @Override
    public boolean insert(KeyListInterface<T> data) throws NullPointerException, IllegalArgumentException {
        return false;
    }

    /**
     * Checks if the given key is in the tree or not.
     * @param data
     * @return
     */

    @Override
    public boolean contains(Comparable<KeyListInterface<T>> data) {
        return false;
    }

    /**
     * This method returns the size of the tree.
     * @return
     */
    @Override
    public int size() {
        return 0;
    }

    /**
     * This method returns if the tree is empty or not.
     * @return
     */

    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * This method clears the tree.
     */

    @Override
    public void clear() {

    }
}
