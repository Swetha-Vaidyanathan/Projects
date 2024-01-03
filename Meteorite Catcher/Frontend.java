import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Frontend implements FrontendInterface{
  Scanner userInput;
  //IterableMultiKeySortedCollectionInterface T;
  Backend backend;


   //since the backend will implement RBT,
   public Frontend(Scanner userInput,Backend backend ){
      this.userInput = userInput;
      this.backend=backend;
   }


  //print the main menu
  public String mainMenu() {
      String returnValue="Welcome Meteorite Catcher app\n1. Load Data File\n2. List"
          + " Meteorites with Highest Mass\n3. List Meteorites within Thresholds\n4. Exit";
      return returnValue;

  }
  //starts the main command loop for the user.
  public void commandLoop() throws IOException{
    boolean exit = false;

    while (!exit) {

        int choice = userInput.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Enter file path: ");
                String filePath = userInput.next();
                loadFile(filePath);
                break;
            case 2:
              listHeighestMass();
                break;
            case 3:
                System.out.println("Enter minimum mass: ");
                double minMass = userInput.nextDouble();
                System.out.println("Enter maximum mass: ");
                double maxMass = userInput.nextDouble();
                listWithThresholds(minMass, maxMass);
                break;
            case 4:
                exitLoop(exit);
                exit = true;
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
  }
  //specify (and load) a data file
  public void loadFile(String filepath) throws FileNotFoundException{
    //Frontend frontend=new Frontend(userInput, backend);

      try {
        this.backend.readCSV(filepath);
          System.out.println("File successfully loaded");
      }
     catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  };
  //list the meteorites with the highest mass in the data set,
  public List<Meteorite> listHeighestMass() {
   List<Meteorite> list= new ArrayList<>();
    list=this.backend.maxMeteorMass();
   return list;
  }
  //list all meteorites with a mass between two specified thresholds
  // this method actually return ArrayList<Meteorite>
  public List<Meteorite> listWithThresholds(double min,double max) {

    List<Meteorite> list= new ArrayList<Meteorite>();
    list=backend.betweenMeteorMass(min, max);
    return list;
  }
  //exits loop
  public void exitLoop(Boolean exit) {
    exit=true;}

}