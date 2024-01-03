//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    This is the main class for this dancing badgers project that deals with GUI
// Course:   CS 300 Spring 2023
//
// Author:   Swetha Vaidyanathan
// Email:    vaidyanatha4@wisc.edu
// Lecturer: Professor Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Anushka Dwivedi
// Partner Email:   adwivedi6@wisc.edu
// Partner Lecturer's Name: Professor Mouna Kacem
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment. X
//   ___ We have both read and understand the course Pair Programming Policy.  X
//   ___ We have registered our team prior to the team registration deadline.  X
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         NONE
// Online Sources:  NONE
///////////////////////////////////////////////////////////////////////////////

import processing.core.PApplet;
import processing.core.PImage;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

/**
 * Main class for dancing badgers project that mainly deals with GUI
 */
public class DancingBadgers extends PApplet {

    private static processing.core.PImage backgroundImage;
    private static int badgersCountMax;
    private boolean danceShowOn;
    private static Random randGen;

    private static ArrayList<Thing> things;

    /**
     * empty constructor of the class DancingBadgers
     */
    public DancingBadgers() {

    }

    // array storing badgers dance show steps
    private static DanceStep[] badgersDanceSteps = new DanceStep[]{DanceStep.LEFT,
            DanceStep.RIGHT, DanceStep.RIGHT, DanceStep.LEFT, DanceStep.DOWN,
            DanceStep.LEFT, DanceStep.RIGHT, DanceStep.RIGHT, DanceStep.LEFT, DanceStep.UP};

    // array storing the positions of the dancing badgers at the start of the dance show
    private static float[][] startDancePositions =
            new float[][]{{300, 250}, {364, 250}, {428, 250}, {492, 250}, {556, 250}};


    /**
     * Sets the size of the display window of this graphic application
     */
    @Override
    public void settings() {
        this.size(800, 600);
    }

    /**
     * Defines initial environment properties of this graphic application.
     * This method initializes all the data fields defined in this class.
     */
    @Override
    public void setup() {

        Thing.setProcessing(this);
        Badger.setProcessing(this);
        Basketball.setProcessing(this);
        this.getSurface().setTitle("P5 Dancing Badgers"); // displays the title of the screen
        this.textAlign(3, 3); // sets the alignment of the text
        this.imageMode(3); // interprets the x and y position of an image to its center
        this.focused = true; // confirms that this screen is "focused", meaning
        // it is active and will accept mouse and keyboard input.
        randGen = new Random();
        backgroundImage = loadImage("images" + File.separator + "background.png");
        badgersCountMax = 5;
        things = new ArrayList<Thing>();
        //Adding objects to the things arraylist.
        things.add(new Thing(50, 50, "target.png"));
        things.add(new Thing(750, 550, "target.png"));
        things.add(new Thing(750, 50, "shoppingCounter.png"));
        things.add(new Thing(50, 550, "shoppingCounter.png"));
        StarshipRobot robot1 = new StarshipRobot(things.get(2), things.get(0), 3);
        StarshipRobot robot2 = new StarshipRobot(things.get(3), things.get(1), 5);
        things.add(robot1);
        things.add(robot2);
        things.add(new Basketball(50, 300));
        things.add(new Basketball(750, 300));
    }

    /**
     * Callback method that draws and updates the application display window.
     * This method runs in an infinite loop until the program exits.
     */
    @Override
    public void draw() {
        background(color(255, 218, 185));//sets the background colour.
        image(backgroundImage, width / 2, height / 2);//sets the image of the window.
        //this code loops through things and draws the objects.
        for (int i = 0; i < things.size(); i++) {
            things.get(i).draw();
        }
    }

    /**
     * Callback method called each time the user presses the mouse.
     * This method iterates through the list of things.
     * If the mouse is over a Clickable object, it calls its mousePressed method, then returns.
     */
    @Override
    public void mousePressed() {
        for (int i = 0; i < things.size(); i++) {
            //checks if things is an instance of Clickable and calls the mousePressed() method.
            if (things.get(i) instanceof Clickable && things.get(i).isMouseOver()) {
                ((Clickable) things.get(i)).mousePressed();
            }
        }
    }

    /**
     * Callback method called each time the mouse is released.
     * This method calls the mouseReleased() method on every
     * Clickable object stored in the things list.
     */
    @Override
    public void mouseReleased() {
        for (int i = 0; i < things.size(); i++) {
            //checks if things is an instance of Clickable and calls the mouseReleased() method.
            if (things.get(i) instanceof Clickable) {
                ((Clickable) things.get(i)).mouseReleased();

            }
        }
    }

    /**
     * Gets the number of Badger objects present in the basketball arena
     *
     * @return the number of Badger objects present in the basketball arena
     */
    public int badgersCount() {
        int badgersCount = 0;
        for (int i = 0; i < things.size(); i++) {
            //checks if things is an instance of Badger and increments the badgerCount.
            if (things.get(i) instanceof Badger) {
                badgersCount++;
            }
        }
        return badgersCount;
    }

    /**
     * Sets the badgers start dance positions
     */
    private void setStartDancePositions() {
        //traverse the arraylist things for badgers.
        int badgerIndex = 0;
        for (int i = 0; i < things.size(); i++) {
            //checks if things is an instance of Badger and sets the x and y positions.
            if (things.get(i) instanceof Badger) {
                things.get(i).x = startDancePositions[badgerIndex][0];
                things.get(i).y = startDancePositions[badgerIndex][1];
                badgerIndex++;

            }
        }
    }

    /**
     * Callback method called each time the user presses a key.Key functions are as follows:
     * b-add new badger
     * c-danceShow terminated
     * d-start dance show
     * r-to remove badger
     * s-to stop the dancing badgers
     */
    @Override
    public void keyPressed() {
        switch (Character.toUpperCase(key)) {
            case 'B': // add new badgers as long as the maximum numbers of badgers allowed to be
                // present in the field is not reached.
                if (badgersCount() != badgersCountMax && danceShowOn == false) {
                    //adds Badger objects in random places.
                    things.add(new Badger(randGen.nextInt(width), randGen.nextInt(height)
                            , badgersDanceSteps));
                }
                break;

            case 'C':// terminates the danceShow and all MovingThings are removed.
                danceShowOn = false;
                for (int i = 0; i < things.size(); i++) {
                    //checks if things is an instance of MovingThing.
                    // removes the object in index i.
                    if (things.get(i) instanceof MovingThing) {
                        things.remove(i);
                        i--;
                    }
                }
                break;

            case 'D'://Starts the danceShow and calls the startDancing() method.
                if (badgersCount() >= 1) {
                    if (!danceShowOn) {
                        danceShowOn = true;
                        setStartDancePositions();
                    }
                }
                if (danceShowOn) {
                    for (int i = 0; i < things.size(); i++) {
                        //checks if things is an instance of Badger.
                        // calls the startDancing() method.
                        if (things.get(i) instanceof Badger) {
                            ((Badger) things.get(i)).startDancing();
                        }
                    }
                }
                break;

            case 'R'://Removes the badgers of the danceShow is not on.
                if (danceShowOn == false) {
                    for (int i = 0; i < things.size(); i++) {
                        //checks if things is an instance of Badger.
                        //removes the Badger objects at index i if the mouse is over it.
                        if (things.get(i) instanceof Badger) {
                            if ((things.get(i)).isMouseOver()) {
                                things.remove(i);
                                break;

                            }
                        }
                    }
                }
                break;
            case 'S'://Terminates the danceShow.
                danceShowOn = false;
                for (int i = 0; i < things.size(); i++) {
                    //checks if things is an instance of Badger.
                    //calls the stopDancing() method.
                    if (things.get(i) instanceof Badger) {
                        ((Badger) things.get(i)).stopDancing();
                    }
                }
        }
    }

    /**
     * Driver method to run this graphic application
     *
     * @param args list of input arguments is any present
     */
    public static void main(String[] args) {
        PApplet.main("DancingBadgers");

    }
}
