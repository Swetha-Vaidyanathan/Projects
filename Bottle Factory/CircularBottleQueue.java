//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    This class models a circular-indexing array queue which stores elements of type Bottle.
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
 * This class models an circular-indexing array queue which stores elements of type Bottle.
 */
public class CircularBottleQueue implements QueueADT<Bottle>, Iterable<Bottle> {

    private Bottle[] bottles; //the array of bottles
    private int front; //earliest added bottle
    private int back; //recently added bottle
    private int size; // number of bottles in the array

    /**
     * Constructs a CircularBottleQueue object, initializing its data fields
     *
     * @param capacity defining the number of bottles the queue can hold
     * @throws IllegalArgumentException when capacity is not positive
     */
    public CircularBottleQueue(int capacity) throws IllegalArgumentException {

        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity is negative");
            //throws an exception if the capacity is negative.
        }
        //assigns default values of fields.
        this.bottles = new Bottle[capacity];
        this.size = 0;
        this.front = -1;
        this.back = -1;
    }

    /**
     * Returns an iterator to traverse the queue
     *
     * @return An Iterator instance to traverse a deep copy of this CircularBottleQueue
     */
    @Override
    public Iterator<Bottle> iterator() {
        return new BottleQueueIterator(this);
    }

    /**
     * Add a bottle to the end of the queue
     *
     * @param bottle element of type Bottle to add to queue
     */
    @Override
    public void enqueue(Bottle bottle) {
        if (bottle == null) {
            throw new NullPointerException("Bottle is null");
            //throws an exception if the bottle is null.
        }
        if (isFull()) {
            throw new IllegalStateException("The queue has reached it's capacity");
            //throws an exception if the queue is full.
        }
        if (isEmpty()) { //checking for an empty array
            front = 0;
            back = 0;
        } else {
            back = (back + 1) % bottles.length;
            //using circular indexing to change the contents of the array.
        }
        bottles[back] = bottle;
        size++;

    }

    /**
     * Removes and returns the first bottle in the queue
     *
     * @return Top/First bottle in the queue
     */
    @Override
    public Bottle dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty, nothing to remove");
        }

        Bottle temp = bottles[front];
        bottles[front] = null;

        if (front == back) {//checks if the queue is empty
            front = -1;
            back = -1;
        } else {
            front = (front + 1) % bottles.length;
            //uses circular indexing to change the value of front.
        }
        size--;
        return temp;//returns the front bottle of the queue.
    }

    /**
     * Returns the first bottle in the queue without removing it
     *
     * @return Top/First bottle in the queue
     */
    @Override
    public Bottle peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
            //throws an exception if the queue is empty.
        }
        return bottles[front];//returns the first bottle element of the queue.
    }

    /**
     * Checks and returns true if the queue is empty
     *
     * @return true if the queue is empty
     */
    @Override
    public boolean isEmpty() {
        return size == 0;//returns if the queue is empty or not.
    }

    /**
     * Checks and returns true if the queue is full
     *
     * @return true is the queue is full
     */
    @Override
    public boolean isFull() {
        return size == bottles.length;//returns if the queue is full or not.
    }

    /**
     * Returns the number of bottles in the queue
     *
     * @return size of the queue
     */
    @Override
    public int size() {
        return this.size;//returns the size of the queue.
    }

    /**
     * Returns a string representation of the queue from the front to its back
     * with the string representation of each Bottle in a separate line
     *
     * @return String in expected format, empty string when queue is empty
     */
    @Override
    public String toString() {
        String c = "";//a local variable to store the string.
        for (Bottle bottle : this) {
            c += bottle.toString() + "\n";//adds all the bottle elements to the local variable.
        }
        return c.trim();//trims the last newline.
    }

    /**
     * Returns a deep copy of this Queue
     *
     * @return a deep copy of the queue
     */
    @Override
    public QueueADT<Bottle> copy() {
        QueueADT<Bottle> copy = new CircularBottleQueue(bottles.length);
        //creates a new CircularBottleQueue object.
        int count = 0;//temp variable to store the count.
        for (int i = front; i < bottles.length; i++) {
            if (bottles == null)//checks of the queue is null.
                break;
            if (count == size)//checks if the queue is full.
                break;
            copy.enqueue(bottles[i]);//adds all the bottle elements to the queue.
            count++;
            if (i == bottles.length - 1) {
                i = 0;
            }
        }
        return copy;//returns a deep copy of the queue.
    }
}
