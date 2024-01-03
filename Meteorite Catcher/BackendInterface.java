import java.io.FileNotFoundException;
import java.util.List;
import java.io.IOException;

    //This is the constructor for the backend class
    /* public Backend (IterableMultiKeySortedCollection<MeteoriteInterface> collection)
     * {
     *     this.RBT = (RedBlackTree<MeteoriteInterface>) collection;
     * }
     */

public interface BackendInterface {
    // Note: Dataset includes mistakes including that the lat and long 
    // is sometimes 0.0 when it shouldn't be.

    /**
     * This method takes a path to a csv file, and adds
     * the data into to a Red-Black Tree that frontend can access.
     */
    public void readCSV(String filepath) throws FileNotFoundException;

    /**
     * This methods can be called by the front end without input and returns
     * a list of Meteorites (which may be empty if no Meteorites are in RBT).
     */
    public List<Meteorite> maxMeteorMass();

    /**
     * This method can be called by the front end with an input of min and max mass.
     * Any meteorite of mass inclusive of the bounds is returned in a list (which may
     * be empty).
     * Which bound is lower and which is upper is determined within the method.
     */
    public List<Meteorite> betweenMeteorMass(double boundOne, double boundTwo);
}
