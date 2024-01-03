//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    This class models Basketball objects. When clicked, the basketball rotate.
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

/**
 * This class models Basketball objects. When clicked, the basketball rotate.
 */
public class Basketball extends Thing implements Clickable {
    public float rotation;
    private int rotations;

    /**
     * Creates a new Basketball object located at (x,y) position whose image filename is "basketball.png",
     * and sets its rotation angle to PApplet.PI/2.
     *
     * @param x x-position of this Basketball object in the display window
     * @param y y-position of this Basketball object in the display window
     */
    public Basketball(float x, float y) {
        super(x, y, "basketball.png");
        rotations = 0;
        this.rotation = PApplet.PI / 2;
    }

    /**
     * Draws this rotating Basketball object to the display window.
     */
    @Override
    public void draw() {
        // draw this rotating Basketball object at its current position
        processing.pushMatrix();
        processing.translate(x, y);
        processing.rotate(this.rotations * rotation);
        processing.image(image(), 0.0f, 0.0f);
        processing.popMatrix();

    }

    /**
     * Defines the behavior of this basketball when the mouse is pressed.
     * The basketball rotates when it is clicked
     */

    public void mousePressed() {
        //checks if the mouse is over the basketball and calls the rotate method.
        if (isMouseOver()) {
            rotate();
        }
    }

    /**
     * Called when the mouse is released. A basketball object does
     * nothing when the mouse is released. This is a method with an empty body.
     */
    public void mouseReleased() {

    }

    /**
     * This method rotates this basketball object by incrementing the number of its rotations by one.
     */
    public void rotate() {
        this.rotations++;
    }
}
