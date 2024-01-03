import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

interface IndividualBackendInterface {

/**
 * This interface implements the functionality of the Meteorites and extends Comparable.
 * @param <T> generic datatype
 */

    /**
     * This method reads Meteorite data from the csv file.
     * @param filePath
     * @throws IOException
     * @throws FileNotFoundException
     */
    //something to read data
    //throws IOException for invalid data in the file.
    public void readData(String filePath) throws IOException, FileNotFoundException;

    /**
     * Gets the Meteorite with the maximum mass from the file.
     * @return the Meteorite with the maximum mass in grams.
     */
    //To get the Meteorite with the maximum mass.
    public float getMaxMass();


    /** Gets the list of Meteorites with maximum mass from the file.
     * @return the list of Meteorites with maximum mass in grams.
     */
    //to get a list of meteorites with a range of mass
    public List<Meteorite> listOfMeteorites();

    /**
     * Gets the list of Meteorites with mass in range of 503-504 g.
     * @return the list of Meteorites with the range of a threshold mass.
     */
    //to get a list of meteorites within the range of mass.
    public List<Meteorite> getMeteoritesInRange();

}
