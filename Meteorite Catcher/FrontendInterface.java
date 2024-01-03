import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *an interface for a class that implements the functionality of the fronted for the app
 */
public interface FrontendInterface{



     //since the backend will implement RBT,
//     FrontendInterface(Scanner userInput,Backend backend ){
//       userInput = new Scanner(System.in);
//       this.backend=backend;
//     }
//

    //print the main menu
    public String mainMenu();
    //starts the main command loop for the user.
    public void commandLoop() throws IOException;
    //specify (and load) a data file
    public void loadFile(String filepath) throws FileNotFoundException;
    //list the meteorites with the highest mass in the data set,
    public List<Meteorite> listHeighestMass();
    //list all meteorites with a mass between two specified thresholds
    // this method actually return ArrayList<Meteorite>
    public List<Meteorite> listWithThresholds(double min,double max);
    //exits loop
    public void exitLoop(Boolean exit);
}
