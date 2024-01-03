//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    This class models a Badger object
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

/**
 * This class models a Badger object in Dancing Badger project
 */
public class Badger extends MovingThing implements Clickable {
    private DanceStep[] danceSteps;
    private boolean isDancing;
    private boolean isDragging;
    private float[] nextDancePosition;
    private static int oldMouseX;
    private static int oldMouseY;
    private int stepIndex;

    /**
     * Creates a new Badger object positioned at a specific position
     * of the display window and whose moving speed is 2.
     *
     * @param x          x position of this Badger object within the display window
     * @param y          y position of this Badger object within the display window
     * @param danceSteps perfect size array storing the dance steps of this badger
     */
    public Badger(float x, float y, DanceStep[] danceSteps) {
        super(x, y, 2, "badger.png");

        this.danceSteps = danceSteps;
        stepIndex = 1;
    }

    /**
     * Draws this badger to the display window,
     * calls the drag() behaviour if this Badger is dragging,
     * calls the dance() behaviour if this Badger is dancing
     */
    @Override
    public void draw() {
        if (isDragging) drag();
        if (isDancing) dance();
        //calls the overridden draw method of class MovingThing.
        super.draw();
    }

    /**
     * Checks whether this badger is being dragged
     *
     * @return true if the badger is being dragged else false
     */
    public boolean isDragging() {
        if (this.isDragging) return true;
        return false;

    }

    /**
     * Helper method to drag this Badger object to follow the mouse moves
     */
    private void drag() {
        //dx stores the difference of the x values.
        int dx = processing.mouseX - oldMouseX;
        //dy stores the difference of the y values
        int dy = processing.mouseY - oldMouseY;
        x += dx;
        y += dy;

        //This code assigns the minimum value of x and y positions.
        if (x > 0)
            x = Math.min(x, processing.width);
        else
            x = 0;
        if (y > 0)
            y = Math.min(y, processing.height);
        else
            y = 0;

        oldMouseX = processing.mouseX;
        oldMouseY = processing.mouseY;

    }

    /**
     * Starts dragging this badger
     */
    public void startDragging() {
        oldMouseX = processing.mouseX;
        oldMouseY = processing.mouseY;
        this.isDragging = true;
        drag();

    }

    /**
     * Stops dragging this Badger object
     */
    public void stopDragging() {
        this.isDragging = false;

    }

    /**
     * Defines the behavior of this Badger when it is clicked
     */
    public void mousePressed() {
        if (this.isMouseOver() && this.isDancing == false) {
            this.startDragging();
        }

    }

    /**
     * Defines the behavior of this Badger when the mouse is released.
     * If the mouse is released, this badger stops dragging.
     */
    public void mouseReleased() {
        this.stopDragging();
    }

    /**
     * This helper method moves this badger one speed towards its nextDancePosition.
     * Then, it checks whether this Badger is facing right and updates the isFacingRight
     * data field accordingly.
     *
     * @return true is the Badger reached its next dance position else false
     */
    private boolean makeMoveDance() {

        //dx and dy stores the difference of the x and y positions respectively.
        float dx = nextDancePosition[0] - this.x;//xy positions of badger will be the first position
        float dy = nextDancePosition[1] - this.y;
        //calculates the distance
        int d = (int) Math.sqrt(dx * dx + dy * dy);
        if (d != 0) { // move!
            this.x += speed * dx / d;
            this.y += speed * dy / d;
        }
        //this code checks if the badger is facing right and updates the field.

        if (dx > 0) {

            this.isFacingRight = true;
        } else {
            this.isFacingRight = false;
        }
        return d < 2 * speed;

    }

    /**
     * Implements the dance behavior of this Badger. This method prompts the Badger to make one move dance.
     * If the makeMoveDance method call returns true this method MUST:
     * - update its next dance position
     * - increment the stepIndex.(danceSteps array is a circular indexing array)
     */
    private void dance() {
        if (makeMoveDance()) {
            //update its next dance position
            this.nextDancePosition = this.danceSteps[stepIndex].getPositionAfter
                    (nextDancePosition[0], nextDancePosition[1]);
            //increment the stepIndex using circular indexing.
            stepIndex = (stepIndex + 1) % danceSteps.length;

        }
    }

    /**
     * Prompts this badger to start dancing. This method:
     * - updates the isDancing data field
     * - stops dragging this badger
     * - sets stepIndex to zero
     * - Resets the nextDancePosition
     */

    public void startDancing() {
        this.isDancing = true;
        this.isDragging = false;
        this.stepIndex = 0;
        //resets the nextDancePosition.
        this.nextDancePosition = danceSteps[stepIndex].getPositionAfter(x, y);

    }

    /**
     * Prompts this badger to stop dancing. Sets the isDancing data field to false.
     */
    public void stopDancing() {
        this.isDancing = false;
    }
}

