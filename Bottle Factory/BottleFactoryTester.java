//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    This is a tester file that checks all the methods
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
 * This utility class implements tester methods to check the correctness of the implementation of
 * classes defined in p08 Bottle Factory program.
 */
public class BottleFactoryTester {

    /**
     * Ensures the correctness of the constructor and methods defined in the Bottle class
     *
     * @return true if the tester verifies a correct functionality and false if at least one bug is
     * detected
     */
    public static boolean bottleTester() {

        Bottle.resetBottleCounter();//resets the counter so that the tester can run independently.
        //(1)tests for a valid input such that no exception should be thrown.
        {
            try {
                Bottle test1 = new Bottle("Red");
                //checks the value of the fields of the constructor.
                if (test1.isCapped())
                    return false;
                if (test1.isFilled())
                    return false;
                test1.fillBottle();
                test1.sealBottle();
                if (!test1.isCapped())
                    return false;
                if (!test1.isFilled())
                    return false;

            } catch (Exception e) {
                return false;
            }
        }

        //(2) test equals method
        {
            Bottle.resetBottleCounter();
            Bottle test1 = new Bottle("Blue");
            Bottle.resetBottleCounter();
            Bottle test2 = new Bottle("Blue");
            boolean expected = true;
            boolean actual = test1.equals(test2);
            if (expected != actual)
                return false;

        }
        //(3) test toString method
        {
            Bottle test1 = new Bottle("Green");
            test1.fillBottle();
            test1.sealBottle();
            String expected = "SN2Green:Filled:Capped";
            String actual = test1.toString();
            if (!expected.equals(actual))
                return false;

        }
        //(4) Checking for invalid inputs
        {
            try {
                Bottle test1 = new Bottle("");//invalid colour.
            } catch (IllegalArgumentException e) {
                if (e.getMessage() == null || e.getMessage().isBlank())
                    return false;
            } catch (Exception e) {
                return false;//incorrect exception type.
            }
        }

        return true;
    }

    /**
     * Ensures the correctness of the constructor and methods defined in the LinkedBottleQueue class
     *
     * @return true if the tester verifies a correct functionality and false if at least one bug is
     * detected
     */
    public static boolean linkedBottleQueueTester() {
        //tests the constructor and verifies fields when the capacity is valid.
        {
            try {
                LinkedBottleQueue test1 = new LinkedBottleQueue(1);
                if (!test1.isEmpty())
                    return false;
                if (test1.size() != 0)
                    return false;
            } catch (Exception e) {
                return false;
            }
        }
        // test constructor - verify fields and exception behavior (when capacity is invalid)
        {
            try {
                LinkedBottleQueue test1 = new LinkedBottleQueue(-1);
            } catch (IllegalArgumentException e) {
                if (e.getMessage() == null || e.getMessage().isBlank())
                    return false;
            } catch (Exception e) {
                return false;
            }
        }

        /* test enqueue, dequeue and peek methods using different scenarios
         * 1) all methods on an empty queue
         * 2) all methods on a full queue
         * 3) all methods on a partially filled queue
         * 4) Verify queue contents (using peek and size) after a sequence of
         *    enqueue-dequeue and dequeue-enqueue
         * 5) Enqueue until queue is full and dequeue until queue is empty
         */

        {
            LinkedBottleQueue test1 = new LinkedBottleQueue(3);
            //checks if  the queue is empty or not.

            boolean expected = true;
            boolean actual = test1.isEmpty();
            if (expected != actual)
                return false;

            //checks the enqueue method on an empty queue.
            {
                Bottle b1 = new Bottle("Green");
                try {
                    test1.enqueue(b1);
                    if (test1.isEmpty())
                        return false;
                } catch (Exception e) {
                    return false;
                }
            }

            //checks the dequeue method on an empty queue.
            {
                try {
                    test1.dequeue();
                } catch (NoSuchElementException e) {
                    if (e.getMessage() == null || e.getMessage().isBlank())
                        return false;
                } catch (Exception e) {
                    return false;
                }
            }

            //checks the peek method on an empty queue.
            {
                try {
                    if (test1.peek() == null)
                        return false;
                } catch (NoSuchElementException e) {
                    if (e.getMessage() == null || e.getMessage().isBlank())
                        return false;
                } catch (Exception e) {
                    return false;
                }
            }
        }

        {
            LinkedBottleQueue test2 = new LinkedBottleQueue(3);
            Bottle b1 = new Bottle("Green");
            Bottle b2 = new Bottle("Red");
            Bottle b3 = new Bottle("Yellow");
            test2.enqueue(b1);
            test2.enqueue(b2);
            test2.enqueue(b3);

            //Checks the isFull method on a full queue.
            if (!test2.isFull())
                return false;
            //Checks the peek method on full queue.

            try {
                if (test2.peek() == null || test2.peek() != b1) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
            //Checks the enqueue on a full queue.
            {
                try {
                    test2.enqueue(new Bottle("Green"));
                } catch (IllegalStateException e) {
                    if (e.getMessage() == null || e.getMessage().isBlank())
                        return false;
                } catch (Exception e) {
                    return false;
                }
            }

            //checks the dequeue method on a full queue.

            try {
                test2.dequeue();
                if (test2.isFull())
                    return false;
            } catch (Exception e) {
                return false;
            }

            //checks the peek method on a full queue.

            try {
                if (test2.peek() == null || test2.peek() != b2) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        }
        {
            //Creating a partially filled queue.
            LinkedBottleQueue test = new LinkedBottleQueue(5);
            Bottle b1 = new Bottle("Green");
            Bottle b2 = new Bottle("Red");
            Bottle b3 = new Bottle("Yellow");
            test.enqueue(b1);
            test.enqueue(b2);
            test.enqueue(b3);
            if (test.isEmpty())
                return false;

            if (test.isFull())
                return false;

            //checks the peek method on a partial queue.
            try {
                if (test.peek() == null || test.peek() != b1) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }

            //Checks the enqueue method on a partial queue.
            {
                try {
                    test.enqueue(new Bottle("Red"));
                    if (test.isFull())
                        return false;
                } catch (Exception e) {
                    return false;
                }
            }

            //Checks the dequeue method on a partial queue.
            {
                try {
                    test.dequeue();
                    if (test.isEmpty())
                        return false;
                    if (test.isFull())
                        return false;
                } catch (Exception e) {
                    return false;
                }
            }
        }

        //4) Verify queue contents (using peek and size) after a sequence on enqueue-dequeue and dequeue-enqueue
        {
            LinkedBottleQueue test = new LinkedBottleQueue(5);
            Bottle b1 = new Bottle("Green");
            Bottle b2 = new Bottle("Red");
            Bottle b3 = new Bottle("Yellow");
            test.enqueue(b1);
            test.enqueue(b2);
            test.enqueue(b3);

            //checks the peek value on the queue before dequeue.
            try {
                if (test.peek() == null || test.peek() != b1) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
            //checks the size before dequeue.
            if (test.size() != 3)
                return false;

            test.dequeue(); //removes the oldest data in the queue.
            //checks the peek value on the queue after dequeue.
            try {
                if (test.peek() == null || test.peek() != b2) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
            //checks the size after dequeue.
            if (test.size() != 2)
                return false;

            test.dequeue();
            //checks the peek value on the queue before queue.
            try {
                if (test.peek() == null || test.peek() != b3) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
            //checks the size before queue.
            if (test.size() != 1)
                return false;

            test.enqueue(b1);

            //checks the peek value on the queue after queue.
            try {
                if (test.peek() == null || test.peek() != b3) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
            //checks the size after queue.
            if (test.size() != 2)
                return false;


        }

        //5) Enqueue until queue is full and dequeue until queue is empty
        {
            LinkedBottleQueue test = new LinkedBottleQueue(5);
            Bottle b1 = new Bottle("Green");
            Bottle b2 = new Bottle("Red");
            Bottle b3 = new Bottle("Yellow");
            test.enqueue(b1);
            test.enqueue(b2);
            test.enqueue(b3);

            if (test.size() != 3)
                return false;

            try {
                test.enqueue(new Bottle("Green"));
                if (test.isFull())
                    return false;
                if (test.size() != 4)
                    return false;
            } catch (Exception e) {
                return false;
            }

            try {
                test.enqueue(new Bottle("Blue"));
                if (!test.isFull())
                    return false;
                if (test.size() != 5)
                    return false;
            } catch (Exception e) {
                return false;
            }

            try {
                test.enqueue(new Bottle("Green"));
                return false;
            } catch (IllegalStateException e) {
                if (e.getMessage() == null || e.getMessage().isBlank())
                    return false;
            } catch (Exception e) {
                return false;
            }
            try {
                test.dequeue();
                if (test.isEmpty())
                    return false;
                if (test.size() != 4)
                    return false;
            } catch (Exception e) {
                return false;
            }

            try {
                test.dequeue();
                if (test.isEmpty())
                    return false;
                if (test.size() != 3)
                    return false;
            } catch (Exception e) {
                return false;
            }

            try {
                test.dequeue();
                if (test.isEmpty())
                    return false;
                if (test.size() != 2)
                    return false;
            } catch (Exception e) {
                return false;
            }
            try {
                test.dequeue();
                if (test.isEmpty())
                    return false;
                if (test.size() != 1)
                    return false;
            } catch (Exception e) {
                return false;
            }

            try {
                test.dequeue();
                if (!test.isEmpty())
                    return false;
                if (test.size() != 0)
                    return false;
            } catch (Exception e) {
                return false;
            }

            try {
                test.dequeue();
                return false;
            } catch (NoSuchElementException e) {
                if (e.getMessage() == null || e.getMessage().isBlank())
                    return false;
            } catch (Exception e) {
                return false;
            }
        }
        //checking enqueue for invalid Bottle object.
        {
            Bottle bottle = null;
            LinkedBottleQueue test = new LinkedBottleQueue(5);
            try {
                test.enqueue(bottle);
                return false;
            } catch (NullPointerException e) {
                if (e.getMessage() == null || e.getMessage().isBlank())
                    return false;
            } catch (Exception e) {
                return false;
            }
        }
        // test copy method
        {

            LinkedBottleQueue test = new LinkedBottleQueue(3);
            test.enqueue(new Bottle("Pink"));
            QueueADT<Bottle> queueCopy = test.copy();
            Bottle b1 = new Bottle("Green");
            test.enqueue(b1);
            //verifying that modifying one queue didn't modify the second one.

            if (test.size() == queueCopy.size())
                return false;
        }

        // test toString method
        {
            Bottle.resetBottleCounter();
            LinkedBottleQueue test = new LinkedBottleQueue(5);
            Bottle b1 = new Bottle("Green");
            Bottle b2 = new Bottle("Red");
            Bottle b3 = new Bottle("Yellow");
            test.enqueue(b1);
            test.enqueue(b2);
            test.enqueue(b3);

            String expected = "SN1Green:Empty:Open" + "\n" + "SN2Red:Empty:Open" +
                    "\n" + "SN3Yellow:Empty:Open";
            String actual = test.toString();
            if (!expected.equals(actual))
                return false;

        }
        return true;
    }


    /**
     * Ensures the correctness of the constructor and methods defined in the
     * CircularBottleQueue class.
     *
     * @return true if the tester verifies a correct functionality and false if at least one bug is
     * detected
     */
    public static boolean circularBottleQueueTester() {

        // test constructor - verify fields and exception behavior
        {
            //(1) verifies constructor with valid inputs
            try {
                CircularBottleQueue test = new CircularBottleQueue(3);
                if (test.size() != 0)
                    return false;
                if (!test.isEmpty())
                    return false;
            } catch (Exception e) {
                return false;
            }
            //(2)verifies the constructor with invalid inputs.

            try {
                CircularBottleQueue invalid = new CircularBottleQueue(-1);
                return false;
            } catch (IllegalArgumentException e) {
                if (e.getMessage() == null || e.getMessage().isBlank())
                    return false;
            }
        }

        /* test enqueue, dequeue and peek methods using different scenarios
         * 1) all 3 methods on an empty queue
         * 2) all 3 methods on a full queue
         * 3) all 3 methods on a partially filled queue
         * 4) Verify queue contents and size after a sequence of enqueue-dequeue and
         *    dequeue-enqueue
         * 5) Enqueue until queue is full and dequeue until queue is empty
         */

        {
            //(1)testing all methods on an empty queue.
            CircularBottleQueue test = new CircularBottleQueue(5);

            if (!test.isEmpty())
                return false;
            {
                //testing enqueue.
                try {
                    test.enqueue(new Bottle("Pink"));
                    if (test.isEmpty())
                        return false;
                } catch (Exception e) {
                    return false;
                }
            }
            //testing dequeue
            {
                try {
                    test.dequeue();
                } catch (NoSuchElementException e) {
                    if (e.getMessage() == null || e.getMessage().isBlank())
                        return false;
                } catch (Exception e) {
                    return false;
                }
            }
            //testing peek method
            {
                try {
                    if (test.peek() == null)
                        return false;
                } catch (NoSuchElementException e) {
                    if (e.getMessage() == null || e.getMessage().isBlank())
                        return false;
                } catch (Exception e) {
                    return false;
                }
            }
        }
        //testing all 3 methods on a full queue.
        {
            CircularBottleQueue test = new CircularBottleQueue(3);
            Bottle b1 = new Bottle("Green");
            Bottle b2 = new Bottle("Red");
            Bottle b3 = new Bottle("Yellow");
            test.enqueue(b1);
            test.enqueue(b2);
            test.enqueue(b3);

            //Checks the isFull method on a full queue.
            if (!test.isFull())
                return false;
            //Checks the peek method on full queue.

            try {
                if (test.peek() == null || test.peek() != b1) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
            //Checks the enqueue on a full queue.
            try {
                test.enqueue(new Bottle("Green"));
            } catch (IllegalStateException e) {
                if (e.getMessage() == null || e.getMessage().isBlank())
                    return false;
            } catch (Exception e) {
                return false;
            }

            //checks the dequeue method on a full queue.
            try {
                test.dequeue();
                if (test.isFull())
                    return false;
            } catch (Exception e) {
                return false;
            }
            //checks the peek method on a full queue.
            try {
                if (test.peek() == null || test.peek() != b2) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        }
        {
            //Creating a partially filled queue.
            CircularBottleQueue test = new CircularBottleQueue(5);
            Bottle b1 = new Bottle("Green");
            Bottle b2 = new Bottle("Red");
            Bottle b3 = new Bottle("Yellow");
            test.enqueue(b1);
            test.enqueue(b2);
            test.enqueue(b3);
            if (test.isEmpty())
                return false;

            if (test.isFull())
                return false;

            //checks the peek method on a partial queue.
            try {
                if (test.peek() == null || test.peek() != b1) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
            //Checks the enqueue method on a partial queue.
            try {
                test.enqueue(new Bottle("Red"));
                if (test.isFull())
                    return false;
            } catch (Exception e) {
                return false;
            }
            //Checks the dequeue method on a partial queue.
            try {
                test.dequeue();
                if (test.isEmpty())
                    return false;
                if (test.isFull())
                    return false;
            } catch (Exception e) {
                return false;
            }
        }
        //4) Verify queue contents after a sequence on enqueue-dequeue and dequeue-enqueue
        {
            CircularBottleQueue test = new CircularBottleQueue(5);
            Bottle b1 = new Bottle("Green");
            Bottle b2 = new Bottle("Red");
            Bottle b3 = new Bottle("Yellow");
            test.enqueue(b1);
            test.enqueue(b2);
            test.enqueue(b3);

            //checks the peek value on the queue before dequeue.
            try {
                if (test.peek() == null || test.peek() != b1) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
            //checks the size before dequeue.
            if (test.size() != 3)
                return false;

            test.dequeue(); //removes the oldest data in the queue.
            //checks the peek value on the queue after dequeue.
            try {
                if (test.peek() == null || test.peek() != b2) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
            //checks the size after dequeue.
            if (test.size() != 2)
                return false;

            test.dequeue();
            //checks the peek value on the queue before queue.
            try {
                if (test.peek() == null || test.peek() != b3) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
            //checks the size before queue.
            if (test.size() != 1)
                return false;

            test.enqueue(b1);

            //checks the peek value on the queue after queue.
            try {
                if (test.peek() == null || test.peek() != b3) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
            //checks the size after queue.
            if (test.size() != 2)
                return false;
        }
        //5) Enqueue until queue is full and dequeue until queue is empty
        {
            LinkedBottleQueue test = new LinkedBottleQueue(5);
            Bottle b1 = new Bottle("Green");
            Bottle b2 = new Bottle("Red");
            Bottle b3 = new Bottle("Yellow");
            test.enqueue(b1);
            test.enqueue(b2);
            test.enqueue(b3);

            if (test.size() != 3)
                return false;

            try {
                test.enqueue(new Bottle("Green"));
                if (test.isFull())
                    return false;
                if (test.size() != 4)
                    return false;
            } catch (Exception e) {
                return false;
            }

            try {
                test.enqueue(new Bottle("Blue"));
                if (!test.isFull())
                    return false;
                if (test.size() != 5)
                    return false;
            } catch (Exception e) {
                return false;
            }

            try {
                test.enqueue(new Bottle("Green"));
                return false;
            } catch (IllegalStateException e) {
                if (e.getMessage() == null || e.getMessage().isBlank())
                    return false;
            } catch (Exception e) {
                return false;
            }
            try {
                test.dequeue();
                if (test.isEmpty())
                    return false;
                if (test.size() != 4)
                    return false;
            } catch (Exception e) {
                return false;
            }
            try {
                test.dequeue();
                if (test.isEmpty())
                    return false;
                if (test.size() != 3)
                    return false;
            } catch (Exception e) {
                return false;
            }
            try {
                test.dequeue();
                if (test.isEmpty())
                    return false;
                if (test.size() != 2)
                    return false;
            } catch (Exception e) {
                return false;
            }
            try {
                test.dequeue();
                if (test.isEmpty())
                    return false;
                if (test.size() != 1)
                    return false;
            } catch (Exception e) {
                return false;
            }
            try {
                test.dequeue();
                if (!test.isEmpty())
                    return false;
                if (test.size() != 0)
                    return false;
            } catch (Exception e) {
                return false;
            }
            try {
                test.dequeue();
                return false;
            } catch (NoSuchElementException e) {
                if (e.getMessage() == null || e.getMessage().isBlank())
                    return false;
            } catch (Exception e) {
                return false;
            }
        }
        //checking enqueue for invalid Bottle object.
        {
            Bottle bottle = null;
            CircularBottleQueue test = new CircularBottleQueue(5);
            try {
                test.enqueue(bottle);
                return false;
            } catch (NullPointerException e) {
                if (e.getMessage() == null || e.getMessage().isBlank())
                    return false;
            } catch (Exception e) {
                return false;
            }
        }
        // test copy method
        {

            CircularBottleQueue test = new CircularBottleQueue(3);
            test.enqueue(new Bottle("Pink"));
            QueueADT<Bottle> queueCopy = test.copy();
            Bottle b1 = new Bottle("Green");
            queueCopy.enqueue(b1);
            //verifying that modifying one queue didn't modify the second one.
            if (test.size() != 1)
                return false;
        }

        // test toString method

        {
            Bottle.resetBottleCounter();
            CircularBottleQueue test1 = new CircularBottleQueue(20);
            Bottle b1 = new Bottle("Green");
            Bottle b2 = new Bottle("Red");
            Bottle b3 = new Bottle("Yellow");
            test1.enqueue(b1);
            test1.enqueue(b2);
            test1.enqueue(b3);
            String expected = "SN1Green:Empty:Open" + "\n" + "SN2Red:Empty:Open" +
                    "\n" + "SN3Yellow:Empty:Open";
            String actual = test1.toString();
            if (!expected.equals(actual))
                return false;
        }
        return true;
    }


    /**
     * Ensures the correctness of the constructor and methods defined
     * in the BottleQueueIterator class
     *
     * @return true if the tester verifies a correct functionality and false if at least one bug is
     * detected
     */
    public static boolean bottleQueueIteratorTester() {

        //checks the constructor of BottleQueueIterator class with a null queue.
        {
            try {
                BottleQueueIterator test = new BottleQueueIterator(null);
                return false;
            } catch (IllegalArgumentException e) {
                if (e.getMessage() == null || e.getMessage().isBlank())
                    return false;
            } catch (Exception e) {
                return false;
            }
        }

        /*
         *  test 01: Create a LinkedBottleQueue with at least n bottles and
         *  use the bottleQueueIterator to traverse the queue. Verify if all
         *  the bottles are returned correctly
         */
        {
            Bottle.resetBottleCounter();
            LinkedBottleQueue test = new LinkedBottleQueue(3);
            Bottle b1 = new Bottle("Green");
            Bottle b2 = new Bottle("Purple");
            Bottle b3 = new Bottle("Yellow");
            test.enqueue(b1);
            test.enqueue(b2);
            test.enqueue(b3);
            Iterator<Bottle> iterator = test.iterator();
            //checks the iterator methods for the first element.
            if (!iterator.hasNext())
                return false;
            if (!(iterator.next().equals(b1)))
                return false;

            //checks the iterator methods for the second element.
            if (!iterator.hasNext())
                return false;
            if (!(iterator.next().equals(b2)))
                return false;
            //checks the iterator method for thr third element.
            if (!iterator.hasNext())
                return false;
            if (!(iterator.next().equals(b3)))
                return false;
            //checks the iterator method for an invalid element.
            if (iterator.hasNext())
                return false;
            try {
                iterator.next();
                return false;
            } catch (NoSuchElementException e) {
                if (e.getMessage() == null || e.getMessage().isBlank())
                    return false;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        /*
         *  test 02: Create a CircularBottleQueue with at least n bottles and
         *  use the bottleQueueIterator to traverse the queue. Verify if all
         *  the bottles are returned correctly
         */

        {
            Bottle.resetBottleCounter();//resets the bottle counter.
            try {
                //checks the iterator on an empty queue.
                CircularBottleQueue test = new CircularBottleQueue(3);
                Iterator<Bottle> iterator = test.iterator();
                if (iterator.hasNext())
                    return false;
                try {
                    Bottle bottle = iterator.next();
                    return false;
                } catch (NoSuchElementException e) {
                    if (e.getMessage() == null || e.getMessage().isBlank())
                        return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

        }
        //checks the iterator on a non-empty queue with n bottles in it.
        {
            CircularBottleQueue test = new CircularBottleQueue(3);
            Bottle b1 = new Bottle("Green");
            Bottle b2 = new Bottle("Purple");
            Bottle b3 = new Bottle("Yellow");
            test.enqueue(b1);
            test.enqueue(b2);
            test.enqueue(b3);
            Iterator<Bottle> iterator = test.iterator();
            if (test.isEmpty())
                return false;

            //checks the iterator methods for the first element.
            if (!iterator.hasNext())
                return false;
            if (!(iterator.next().equals(b1)))
                return false;

            //checks the iterator methods for the second element.
            if (!iterator.hasNext())
                return false;
            if (!(iterator.next().equals(b2)))
                return false;
            //checks the iterator method for thr third element.
            if (!iterator.hasNext())
                return false;
            if (!(iterator.next().equals(b3)))
                return false;
            //checks the iterator method for an invalid element.
            if (iterator.hasNext())
                return false;
            try {
                iterator.next();
                return false;
            } catch (NoSuchElementException e) {
                if (e.getMessage() == null || e.getMessage().isBlank())
                    return false;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            if (test.isEmpty())
                return false;
        }
        return true;
    }

    /**
     * Runs all the tester methods defined in this class.
     *
     * @return true if no bugs are detected.
     */
    public static boolean runAllTests() {
        System.out.println("bottleTester: " + (bottleTester() ? "Pass" : "Failed!"));
        System.out.println("bottleQueueIterator: " +
                (bottleQueueIteratorTester() ? "Pass" : "Failed!"));
        System.out
                .println("linkedBottleQueueTester: " +
                        (linkedBottleQueueTester() ? "Pass" : "Failed!"));
        System.out.println(
                "circularBottleQueueTester: " +
                        (circularBottleQueueTester() ? "Pass" : "Failed!"));

        return bottleTester() && bottleQueueIteratorTester() && linkedBottleQueueTester()
                && circularBottleQueueTester();
    }

    /**
     * Main method to run this tester class.
     *
     * @param args list of input arguments if any
     */
    public static void main(String[] args) {
        System.out.println("runAllTests: " + (runAllTests() ? "Pass" : "Failed!"));
    }

}
