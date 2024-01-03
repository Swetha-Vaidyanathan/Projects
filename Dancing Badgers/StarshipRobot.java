/////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    This class models StarshipRobot objects which can be triggered
//           to move or do some actions.
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
 * This class models StarshipRobot objects which can be triggered to move or do some actions.
 */
public class StarshipRobot extends MovingThing {
    private Thing destination;
    private Thing source;

    /**
     * Creates a new StarshipRobot and sets its source, destination, and speed
     *
     * @param source-Thing      object representing the start point of this StarshipRobot
     * @param destination-Thing object representing the destination point of this StarshipRobot
     * @param speed             movement speed of this StarshipRobot
     */
    public StarshipRobot(Thing source, Thing destination, int speed) {
        super(source.x, source.y, speed, "starshipRobot.png");
        this.source = source;
        this.destination = destination;
        this.speed = speed;
        if (source.x < destination.x) {
            isFacingRight = !isFacingRight;
        }
    }

    /**
     * Draws this StarshipRobot to the display window while it is in motion delivering food.
     */
    @Override
    public void draw() {
        //prompts this StarshipRobot to go
        this.go();
        //partially overrides the method from MovingThing.
        super.draw();

    }

    /**
     * Checks whether this StarshipRobot is over a specific Thing
     *
     * @param thing a given Thing object
     * @return true of this StarshipRobot is over the Thing object
     * passed as input, otherwise returns false
     */
    public boolean isOver(Thing thing) {
        //x1, x2, x3, x4 stores the difference in the x  positions.
        //y1, y2, y3, y4 stores the difference in the y  positions.
        float x1 = thing.x - thing.image().width / 2;
        float x2 = thing.x + thing.image().width / 2;
        float y1 = thing.y - thing.image().height / 2;
        float y2 = thing.y + thing.image().height / 2;

        float x3 = this.x - this.image().width / 2;
        float x4 = this.x + this.image().width / 2;
        float y3 = this.y - this.image().height / 2;
        float y4 = this.y + this.image().height / 2;

        return (x1 < x4) && (x3 < x2) && (y1 < y4) && (y3 < y2);
    }

    /**
     * Helper method to move this StarshipRobot towards its destination
     */
    private void moveTowardsDestination() {
        //dx stores the difference of distance of x.
        float dx = destination.x - this.x; // x-move towards destination
        //dy stores the difference of distance of y.
        float dy = destination.y - this.y; // y-move towards destination
        int d = (int) Math.sqrt(dx * dx + dy * dy); // distance to destination
        //checks that the distance is not 0.
        if (d != 0) { // move!
            this.x += speed * dx / d;
            this.y += speed * dy / d;
        }
    }

    /**
     * Implements the action of this StarshipRobot, if starship robot is over its
     * destination it switches source and destination and the value of isFacingRight
     * to the opposite
     */
    public void go() {
        //calls the moveTowardsDestination() method.
        moveTowardsDestination();
        // switch source and destination if this StarshipRobot reached its destination
        if (this.isOver(this.destination)) {
            Thing d = destination;
            destination = source;
            source = d;
            isFacingRight = !isFacingRight;
        }
    }
}

