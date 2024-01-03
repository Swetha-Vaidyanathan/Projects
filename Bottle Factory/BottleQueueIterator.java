//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    This class models an iterator to iterate over a queue of Bottle objects.
// Course:   CS 300 Spring 2023
//
// Author:   Swetha Vaidyanathan
// Email:    vaidyanatha4@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:   N/A
// Partner Email:   N/A
// Partner Lecturer's Name: N/A
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:        N/A
// Online Sources:  N/A
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * his class models an iterator to iterate over a queue of Bottle objects.
 */
public class BottleQueueIterator implements Iterator<Bottle> {
    QueueADT<Bottle> bottleQueue;// defines the queue of bottles to be iterated over

    /**
     * Initializes the instance fields.
     *
     * @param queue - The queue to iterate over.
     * @throws IllegalArgumentException
     */

    public BottleQueueIterator(QueueADT<Bottle> queue) throws IllegalArgumentException {
        if (queue == null) {
            throw new IllegalArgumentException("Input is null");
            //throws an exception if the queue is null.
        }
        this.bottleQueue = queue.copy();//initializes the queue to a deep copy.
    }

    /**
     * Returns true if there is the iteration is not yet exhausted,
     * meaning at least one bottle is not iterated over
     *
     * @return true if there is the iteration is not yet exhausted
     */
    @Override
    public boolean hasNext() {
        return !bottleQueue.isEmpty();//checks if the queue has a next element.
    }

    /**
     * Returns the next bottle in the iteration
     *
     * @return Bottle The next bottle in the iteration
     */
    @Override
    public Bottle next() {
        if (!hasNext()) {
            throw new NoSuchElementException("no more elements are present");
            //throws a new exception if the queue doesn't have a next element.
        }

        return bottleQueue.dequeue();//returns the top element of the queue.
    }
}
