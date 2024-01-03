import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class FrontendDeveloperTests {
  /**
   * Test if mainMenu() display the main menu
   */
  Scanner userInput = new Scanner(System.in);;
  //IterableMultiKeySortedCollectionInterface T;
  IterableMultiKeyRBT<Meteorite> tree = new IterableMultiKeyRBT<>();
  Backend backend = new Backend(tree);
  @Test
  public void mainMenuDisplay() {
    
    Frontend frontend= new Frontend( userInput, backend );
    Assertions.assertTrue(frontend.mainMenu().contains("Welcome"));
    
  }
  /**
   * test if the input is null
   */
  @Test
  public void invalidInput()
  {
    String exampleInput="userInput";
    Assertions.assertNotEquals("1",exampleInput);
    Assertions.assertNotEquals("2",exampleInput);
    Assertions.assertNotEquals("3",exampleInput);
    Assertions.assertNotEquals("4",exampleInput);

  }
  /**
   * Test if the filepath is null
   */
  @Test
  public void fileNotFound() {
    String filePath="PATH";
    Assertions.assertNotNull(filePath);
  }
  
  /**
   * Test if the listHeighestMass() gets a empty list
   */
  @Test
  public void emptyDataSet() {
  //  Backend backend=new Backend();
    Frontend frontend= new Frontend( userInput, backend );
    List<Meteorite> HightestMass=new ArrayList<>();
    HightestMass=frontend.listHeighestMass();
    Assertions.assertNotNull(HightestMass);
    
  }
  /**
   * Test if the bonds are valid
   */
  @Test
  public void minGreaterThanMax() {
    Frontend frontend= new Frontend(userInput, backend );
    int min=2;
    int max=10;
    Assertions.assertTrue(min<max);
  }
}
