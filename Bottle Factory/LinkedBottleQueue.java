//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    This class implements a linked queue storing objects of type Bottle.
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
 * This class implements a linked queue storing objects of type Bottle
 */
public class LinkedBottleQueue implements QueueADT<Bottle>, Iterable<Bottle> {
    private LinkedNode<Bottle> front;
    //a private instance field for the beginning of the bottle in the linked list.
    private LinkedNode<Bottle> back;
    //a private instance field for the end of the bottle in the linked list.

    private int size;
    //a private instance field of type int, indicating the number of bottles in the queue.

    private int capacity;
    //a private instance field of type int, defining the max number of bottles
    //the queue can hold

    /**
     * Initializes the fields of this queue including its capacity.
     *
     * @param capacity
     */
    public LinkedBottleQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("invalid capacity");
            //throws an exception when capacity is invalid.
        }
        this.capacity = capacity;
        this.front = null;
        this.back = null;
        this.size = 0;
    }

    /**
     * Returns a string representation of the queue from the front to its back with
     * the string representation of each Bottle in a separate line.
     *
     * @return
     */
    @Override
    public String toString() {
        String c = "";
        for (Bottle bottle : this) {//enhanced for loop that prints elements of type Bottle.
            c += bottle.toString() + "\n";
        }
        return c.trim();//trims the last element.
    }

    /**
     * Returns the first bottle in the queue without removing it.
     *
     * @return
     */
    @Override
    public Bottle peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("the queue is empty");
            //throws exception if the queue is empty.
        }
        return front.getData();
    }

    /**
     * Checks and returns true if the queue is empty/
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return front == null; //returns if the queue is empty or not.
    }

    /**
     * Checks and returns true if the queue is full.
     *
     * @return
     */
    @Override
    public boolean isFull() {
        return size == capacity;//returns if the queue is full or not.
    }

    /**
     * Removes and returns the first bottle in the queue.
     *
     * @return
     */
    @Override
    public Bottle dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("the queue is empty");
        }
        Bottle toReturn = front.getData();
        front = front.getNext();//sets the front to the next value.
        size--;//decreases the size.
        return toReturn;
    }

    /**
     * @param bottle element of type T to add to queue
     */
    @Override
    public void enqueue(Bottle bottle) {

        if (isFull()) {
            throw new IllegalStateException("The queue is full");
        }
        if (bottle == null) {
            throw new NullPointerException("Bottle is null");
        }
        LinkedNode newNode = new LinkedNode(bottle);
        if (isEmpty()) {//checks if the queue is empty
            front = newNode;
        } else {
            back.setNext(newNode);
        }//sets back to the next node.
        back = newNode;
        size++;//increases the size.
    }

    /**
     * Returns the number of bottles in the queue.
     *
     * @return the size of the queue.
     */
    @Override
    public int size() {
        return this.size;//returns the size of the queue.
    }

    /**
     * Returns an iterator for traversing the queue's items.
     *
     * @return the iterator for transversing the queue.
     */
    @Override
    public Iterator<Bottle> iterator() {
        return new BottleQueueIterator(this);//returns an iterator for the queue.
    }

    /**
     * Returns a deep copy of this Queue
     *
     * @return a deep copy of the queue
     */
    @Override
    public QueueADT<Bottle> copy() {
        QueueADT<Bottle> toCopy = new LinkedBottleQueue(capacity);
        //creates a new queue of LinkedBottleQueue.
        LinkedNode<Bottle> temp1 = front;//temp variable to store the front of the queue.
        int count = 0;//temp variable to store the count.
        while (count < size) {
            toCopy.enqueue(temp1.getData());//adds the contents of the queue to the copy.
            temp1 = temp1.getNext();
            count++;
        }
        return toCopy;//returns the deep copy of the queue.
    }
}

