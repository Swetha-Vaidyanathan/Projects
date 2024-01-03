//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    This class models moving thing objects. A moving thing is defined by its
//           speed and to which direction it is facing (right or left).
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
 * This class models moving thing objects. A moving thing is defined by
 * its speed and to which direction it is facing (right or left).
 */
public class MovingThing extends Thing implements Comparable<MovingThing> {
    protected boolean isFacingRight;
    protected int speed;

    /**
     * Creates a new MovingThing and sets its speed, image file, and initial x and y position.
     * Sets isFacingRight as true
     *
     * @param x             starting x-position of this MovingThing
     * @param y             starting y-position of this MovingThing
     * @param speed         movement speed of this MovingThing
     * @param imageFileName filename of the image of this MovingThing
     */
    public MovingThing(float x, float y, int speed, String imageFileName) {
        super(x, y, imageFileName);
        this.speed = speed;
        isFacingRight = true;

    }

    /**
     * Draws this MovingThing at its current position.
     */
    @Override
    public void draw() {
        // draw this MovingThing at its current position
        processing.pushMatrix();
        processing.rotate(0.0f);
        processing.translate(x, y);
        if (!isFacingRight) {
            processing.scale(-1.0f, 1.0f);
        }
        processing.image(image(), 0.0f, 0.0f);
        processing.popMatrix();
    }

    /**
     * Compares this object with the specified MovingThing for order,
     * in the increasing order of their speeds.
     *
     * @param other the object to be compared.
     * @return zero of the object and other have the same speed,
     * a negative integer og speed of this moving object is less than speed
     * and a positive integer otherwise.
     */
    public int compareTo(MovingThing other) {
        //this code compares the speed of the object with the moving thing.
        if (this.speed < other.speed) {
            //returns a negative value if the speed of the thing is lesser than the other speed.
            return -1;
        }
        if (this.speed > other.speed) {
            //returns a positive value if the speed of the thing is lesser than the other speed.
            return 1;
        }
        //returns 0 if the speeds are equal.
        return 0;

    }

}
