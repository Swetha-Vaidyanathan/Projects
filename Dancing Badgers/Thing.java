//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    This class models a graphic Thing which can be drawn at a give (x,y)
//           position within the display window of a graphic application.
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

import java.io.File;

/**
 * This class models a graphic Thing which can be drawn at a give (x,y)
 * position within the display window of a graphic application.
 */
public class Thing {
    private processing.core.PImage image;
    protected static processing.core.PApplet processing;
    protected float x;
    protected float y;

    /**
     * Creates a new graphic Thing located at a specific (x, y) position of the display window
     *
     * @param x             x-position of this thing in the display window
     * @param y             y-position of this thing in the display window
     * @param imageFilename filename of the image of this image
     */
    public Thing(float x, float y, String imageFilename) {
        this.x = x;
        this.y = y;
        this.image = processing.loadImage("images" + File.separator + imageFilename);
    }

    /**
     * Draws this thing to the display window at its current (x,y) position
     */
    public void draw() {
        // draw the thing at its current position
        processing.image(this.image, this.x, this.y);

    }

    /**
     * Sets the PApplet object display window where this Thing object will be drawn
     *
     * @param processing PApplet object that represents the display window
     */
    public static void setProcessing(processing.core.PApplet processing) {
        Thing.processing = processing;

    }

    /**
     * Returns a reference to the image of this thing
     *
     * @return the image of type PImage of the thing object
     */
    public processing.core.PImage image() {
        return this.image;

    }

    /**
     * Checks if the mouse is over this Thing object
     *
     * @return true if the mouse is over this Thing, otherwise returns false.
     */
    public boolean isMouseOver() {
        //badgerWidth stores the width of the Thing object.
        int badgerWidth = this.image.width;
        //badgerHeight stores the height of the Thing object.
        int badgerHeight = this.image.height;

        //this code returns true if the mouse is over the object else returns false.
        return processing.mouseX >= this.x - badgerWidth / 2
                && processing.mouseX <= this.x + badgerWidth / 2
                && processing.mouseY >= this.y - badgerHeight / 2
                && processing.mouseY <= this.y + badgerHeight / 2;
    }
}
