//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    This class models PriorityCareAdmissions objects and implements a minheap queue.
//
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


import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Array-based min-heap implementation of a priority queue storing PatientRecords. Guarantees the
 * min-heap invariant, so that the PatientRecord at the root should be the smallest PatientRecord,
 * which corresponds to the element having the highest priority to be dequeued first, and children
 * always are greater than their parent. We rely on the PatientRecord.compareTo() method to compare
 * PatientRecords.
 * The root of a non-empty queue is always at index 0 of this array-heap.
 */
public class PriorityCareAdmissions {
    private PatientRecord[] queue; // array min-heap of PatientRecords representing this priority
    // queue
    private int size; // size of this priority queue


    /**
     * Creates a new empty PriorityCareAdmissions queue with the given capacity
     *
     * @param capacity Capacity of this PriorityCareAdmissions queue
     * @throws IllegalArgumentException with a descriptive error message if the capacity is not a
     *                                  positive integer
     */
    public PriorityCareAdmissions(int capacity) {
        if (capacity < 0) {//checks if the capacity is negative.
            throw new IllegalArgumentException("Invalid capacity");
        }
        this.queue = new PatientRecord[capacity]; //creates a queue with the given capacity.
    }

    /**
     * Checks whether this PriorityCareAdmissions queue is empty
     *
     * @return {@code true} if this PriorityCareAdmissions queue is empty
     */
    public boolean isEmpty() {
        return this.size == 0;//checks if the queue is empty.
    }

    /**
     * Returns the size of this PriorityCareAdmissions queue
     *
     * @return the total number of PatientRecords stored in this PriorityCareAdmissions queue
     */
    public int size() {
        return this.size;//returns the size of the queue/
    }

    /**
     * Returns the capacity of this PriorityCareAdmissions queue
     *
     * @return the capacity of this PriorityCareAdmissions queue
     */
    public int capacity() {
        return this.queue.length;//returns the capacity of the queue.
    }


    /**
     * Removes all the elements from this PriorityCareAdmissions queue
     */
    public void clear() {
        for (int i = 0; i < queue.length; i++) {
            queue[i] = null;
        }
        size = 0;//clears the queue.
    }

    /**
     * Returns the PatientRecord at the root of this PriorityCareAdmissions queue, i.e. the
     * PatientRecord having the highest priority.
     *
     * @return the PatientRecord at the root of this PriorityCareAdmissions queue
     * @throws NoSuchElementException with the exact error message "Warning: Empty Admissions Queue!"
     *                                if this PriorityCareAdmissions queue is empty
     */
    public PatientRecord peek() {
        if (this.isEmpty())//checks if the queue is empty.
            throw new NoSuchElementException("Warning: Empty Admissions Queue!");

        return queue[0];//returns the first element in the queue.
    }


    /**
     * Adds the given PatientRecord to this PriorityCareAdmissions queue at the correct position based
     * on the min-heap ordering. This queue should maintain the min-heap invariant, so that the
     * PatientRecord at each index is less than or equal to than the PatientRecords in its child
     * nodes. PatientRecords should be compared using the PatientRecord.compareTo() method.
     *
     * @param p PatientRecord to add to this PriorityCareAdmissions queue
     * @throws NullPointerException  if the given PatientRecord is null
     * @throws IllegalStateException with a the exact error message "Warning: Full Admissions Queue!"
     *                               if this PriorityCareAdmissions queue is full
     */
    public void addPatient(PatientRecord p) {
        if (size == capacity()) {//checks if the queue is full and throws the appropriate exception.
            throw new IllegalStateException("Warning: Full Admissions Queue!");
        }
        if (p == null) {//Checks if the given record is null and throws the appropriate exception.
            throw new NullPointerException("the given record is null");
        }
        this.queue[size] = p;//updates the last element to the given record.
        size++;//increases the size.
        this.percolateUp(size - 1);
    }

    /**
     * Recursive implementation of percolateUp() method. Restores the min-heap invariant of this
     * priority queue by percolating a leaf up the heap. If the element at the given index does not
     * violate the min-heap invariant (it is greater than its parent), then this method does not
     * modify the heap. Otherwise, if there is a heap violation, swap the element with its parent and
     * continue percolating the element up the heap.
     *
     * @param i index of the element in the heap to percolate upwards
     * @throws IndexOutOfBoundsException if index is out of bounds (out of the range 0..size()-1
     *                                   inclusive)
     */
    protected void percolateUp(int i) {

        if (i < 0 || i > size - 1) {//throws an exception if the i is out of range.
            throw new IndexOutOfBoundsException("The index is out of the range of the queue");
        }
        if (i == 0)
            return;
        int parentIndex = parent(i);
        if (queue[i].compareTo(queue[parentIndex]) < 0) {
            swap(i, parentIndex);//swaps the index with the parent index.
            percolateUp(parentIndex);
        }
    }

    /**
     * This ia private helper method to find the parent of the queue.
     *
     * @param i
     * @return
     */
    private int parent(int i) {
        return (i - 1) / 2;//the parent of the given node.
    }

    /**
     * the left child of the given node.
     *
     * @param i
     * @return
     */
    private int leftChild(int i) {
        return (2 * i) + 1;//the left child of the given node.
    }

    /**
     * the right child of the given node.
     *
     * @param i
     * @return
     */
    private int rightChild(int i) {
        return (2 * i) + 2;//the right child of the given node.
    }

    /**
     * This is a private helper method to swap the parent and child values.
     *
     * @param i
     * @param j
     */
    private void swap(int i, int j) {
        PatientRecord temp = queue[i];//a local variable to store queue[i].
        queue[i] = queue[j];
        queue[j] = temp;//swaps queue[i] with queue[j].
    }

    /**
     * Removes and returns the PatientRecord at the root of this PriorityCareAdmissions queue, i.e.
     * the PatientRecord having the highest priority (the minimum one).
     *
     * @return the PatientRecord in this PriorityCareAdmissions queue at the root of this priority
     * queue.
     * @throws NoSuchElementException with the exact error message "Warning: Empty Admissions Queue!"
     *                                if this PriorityCareAdmissions queue is empty
     */
    public PatientRecord removeBestRecord() {
        if (isEmpty()) {
            throw new NoSuchElementException("Warning: Empty Admissions Queue!");
        }
        PatientRecord root = queue[0];
        queue[0] = queue[size - 1];//swap last leaf
        queue[size - 1] = null;
        size--;
        if (size > 0) {
            percolateDown(0);
        }
        return root; // default return statement added to resolve compile errors
    }


    /**
     * Recursive implementation of percolateDown() method. Restores the min-heap of the priority queue
     * by percolating its root down the tree. If the element at the given index does not violate the
     * min-heap ordering property (it is smaller than its smallest child), then this method does not
     * modify the heap. Otherwise, if there is a heap violation, then swap the element with the
     * correct child and continue percolating the element down the heap.
     *
     * @param i index of the element in the heap to percolate downwards
     * @throws IndexOutOfBoundsException if index is out of bounds (out of the range 0..size()-1
     *                                   inclusive)
     */
    protected void percolateDown(int i) {
        //throws an error for i out of range.
        if (i < 0) {
            throw new IndexOutOfBoundsException("");
        }
        if (i > size - 1) {
            throw new IndexOutOfBoundsException("");
        }

        int leftChild;//a local variable to store the left child.
        int rightChild;//a local variable to store the right child.

        leftChild = leftChild(i);
        rightChild = rightChild(i);
        //checks for node with no children.
        if (leftChild > size || queue[leftChild] == null && queue[rightChild] == null) {
            return;
        }

        //checks for nodes with only left child.
        if (queue[leftChild] != null && queue[rightChild] == null) {
            if (queue[leftChild].compareTo(queue[i]) < 0) {
                PatientRecord temp = queue[leftChild];
                queue[leftChild] = queue[i];
                queue[i] = temp;
                percolateDown(leftChild);
            }
        }
        //checks for node with two children.
        if (queue[leftChild] != null && queue[rightChild] != null) {
            //where the left child is greater.
            if (queue[leftChild].compareTo(queue[rightChild]) > 0) {
                if (queue[rightChild].compareTo(queue[i]) < 0) {
                    PatientRecord temp1 = queue[rightChild];
                    queue[rightChild] = queue[i];
                    queue[i] = temp1;
                    percolateDown(rightChild);//percolates down
                }
            }
            if (queue[rightChild].compareTo(queue[leftChild]) > 0) {
                //right child is greater
                if (queue[leftChild].compareTo(queue[i]) < 0) {
                    PatientRecord temp1 = queue[leftChild];
                    queue[leftChild] = queue[i];
                    queue[i] = temp1;
                    percolateDown(leftChild);//percolates down.
                }
            }
        }
    }


    /**
     * Returns a deep copy of this PriorityCareAdmissions queue containing all of its elements in the
     * same order. This method does not return the deepest copy, meaning that you do not need to
     * duplicate PatientRecords. Only the instance of the heap (including the array and its size) will
     * be duplicated.
     *
     * @return a deep copy of this PriorityCareAdmissions queue. The returned new priority care
     * admissions queue has the same length and size as this queue.
     */
    public PriorityCareAdmissions deepCopy() {
        PriorityCareAdmissions deepCopy = new PriorityCareAdmissions(this.capacity());
        deepCopy.queue = Arrays.copyOf(this.queue, this.queue.length);
        deepCopy.size = this.size;
        return deepCopy;
    }

    /**
     * Returns a deep copy of the array-heap of this PriorityCareAdmissions queue <BR/>
     * <p>
     * This method can be used for testing purposes.
     *
     * @return a deep copy of the array-heap storing the ParientRecords in this queue
     */
    protected PatientRecord[] arrayHeapCopy() {
        return Arrays.copyOf(this.queue, this.queue.length);

    }

    /**
     * Returns a String representing this PriorityCareAdmissions queue, where each element
     * (PatientRecord) of the queue is listed on a separate line, in order from smallest to greatest.
     *
     * @return a String representing this PriorityCareAdmissions queue, and an empty String "" if this
     * queue is empty.
     */
    public String toString() {
        String result = "";
        PriorityCareAdmissions deepCopy = this.deepCopy(); // Create a deep copy of the queue
        // Iterate over the deep copy, removing the best patient record
        // and appending it to the result string
        while (!deepCopy.isEmpty()) {
            result += deepCopy.removeBestRecord() + "\n";
        }
        return result;
    }
}
