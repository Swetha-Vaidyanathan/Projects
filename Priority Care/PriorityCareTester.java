//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    his is a Utility class which contains tester methods to ensure
// the correctness of the code.
// queue at an urgent care service.
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
 * This is a Utility class which contains tester methods to ensure the correctness of the
 * implementation of the main operations defined in cs300 spring 2023 p10 Priority Care.
 */
public class PriorityCareTester {

    /**
     * Tests whether compareTo() method implemented in PatientRecord returns a positive integer when a
     * higher triage level is compared to a lower triage level, regardless of patient order of
     * arrival. Similarly, this method tests whether compareTo() method implemented in PatientRecord
     * returns a negative integer when a lower triage level is compared to a higher triage level,
     * regardless of patient order of arival.
     *
     * @return true if the tester verifies a correct functionality and false if at least one bug is
     * detected
     * @see PatientRecord#compareTo(PatientRecord)
     */
    public static boolean testPatientRecordCompareToDifferentTriage() {
        PatientRecord p1 = new PatientRecord('F', 33, TriageLevel.RED);
        PatientRecord p2 = new PatientRecord('F', 22, TriageLevel.YELLOW);
        PatientRecord p3 = new PatientRecord('F', 33, TriageLevel.GREEN);
        PatientRecord p4 = new PatientRecord('F', 33, TriageLevel.RED);
        PatientRecord p5 = new PatientRecord('M', 24, TriageLevel.YELLOW);
        PatientRecord p6 = new PatientRecord('X', 45, TriageLevel.GREEN);

        // PatientRecord.resetCounter();//resets the counter number to 1
        //(1)Where the triage level is greater than the given triage level.

        if (p1.compareTo(p2) >= 0)
            return false;
        if (p2.compareTo(p3) >= 0)
            return false;

        if (p4.compareTo(p6) >= 0)
            return false;
        if (p6.compareTo(p4) <= 0)
            return false;
        if (p2.compareTo(p5) >= 0)
            return false;

        return true;
    }

    /**
     * Tests whether patients in the same triage level are compared based on their order of arrival.
     * Patients of the same triage level with a lower arrival number compared to patients with a
     * higher arrival number should return a negative integer. The reverse situation should return a
     * positive integer.
     *
     * @return true if the tester verifies a correct functionality and false if at least one bug is
     * detected
     * @see PatientRecord#compareTo(PatientRecord)
     */
    public static boolean testPatientRecordCompareToSameTriageDifferentArrival() {
        PatientRecord p1 = new PatientRecord('F', 33, TriageLevel.RED);
        PatientRecord p2 = new PatientRecord('F', 22, TriageLevel.YELLOW);
        PatientRecord p3 = new PatientRecord('F', 33, TriageLevel.GREEN);
        PatientRecord p4 = new PatientRecord('F', 33, TriageLevel.RED);
        PatientRecord p5 = new PatientRecord('M', 24, TriageLevel.YELLOW);
        PatientRecord p6 = new PatientRecord('X', 45, TriageLevel.GREEN);

        if (p2.compareTo(p5) >= 0)
            return false;
        if (p1.compareTo(p2) >= 0)
            return false;
        if (p2.compareTo(p3) >= 0)
            return false;
        if (p3.compareTo(p4) <= 0)
            return false;
        if (p4.compareTo(p5) >= 0)
            return false;
        if (p5.compareTo(p6) >= 0)
            return false;
        return true;
    }

    /**
     * Tests whether patients in the same triage level and with the same order of arrival are equal
     * (compareTo should return 0). Even though this case will not be possible in your priority queue,
     * it is required for testing the full functionality of the compareTo() method. Hint: you will
     * need to use the resetCounter() to create equivalent PatientRecords.
     *
     * @return true if the tester verifies a correct functionality and false if at least one bug is
     * detected
     * @see PatientRecord#compareTo(PatientRecord)
     */
    public static boolean testPatientRecordCompareToSameTriageSameArrival() {

        PatientRecord.resetCounter();//resets the counter number to 1
        //where the patient has the same case number as the given patient.
        PatientRecord p5 = new PatientRecord('F', 33, TriageLevel.GREEN);
        PatientRecord.resetCounter();//resets the counter number to 1
        PatientRecord test5 = new PatientRecord('F', 33, TriageLevel.GREEN);
        if (p5.compareTo(test5) != 0)
            return false;
        if (test5.compareTo(p5) != 0)
            return false;

        return true;
    }

    /**
     * Tests the functionality of the constructor for PriorityCareAdmissions Should implement at least
     * the following tests:
     * <p>
     * - Calling the PriorityCareAdmissions with an invalid capacity should throw an
     * IllegalArgumentException
     * - Calling the PriorityCareAdmissions with a valid capacity should not throw any errors, and
     * should result in a new PriorityCareAdmissions which is empty, has size 0, a capacity equal to
     * the capacity that was passed as a parameter.
     *
     * @return true if the constructor of PriorityCareAdmissions functions properly, false otherwise
     * @see PriorityCareAdmissions#PriorityCareAdmissions(int)
     */
    public static boolean testConstructor() {
        //(1) trying an invalid capacity value
        try {
            PriorityCareAdmissions test1 = new PriorityCareAdmissions(-1);
            return false;
        } catch (IllegalArgumentException e) {
            if (e.getMessage() == null || e.getMessage().isBlank())
                return false;
        } catch (Exception e) {
            return false;
        }

        //(2)Testing on a valid implementation.
        try {
            PriorityCareAdmissions test2 = new PriorityCareAdmissions(5);
            if (!test2.isEmpty())
                return false;
            if (test2.size() != 0)
                return false;
            if (test2.capacity() != 5)
                return false;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Tests the functionality of peek() method by calling peek on an empty queue and verifying it
     * throws a NoSuchElementException.
     *
     * @return true if PriorityCareAdmissions.peek() exhibits expected behavior, false otherwise.
     */
    public static boolean testPeekEmpty() {
        PatientRecord.resetCounter();
        try {
            PriorityCareAdmissions test1 = new PriorityCareAdmissions(3);
            test1.peek();
            return false;
        } catch (NoSuchElementException e) {
            if (e.getMessage() == null || e.getMessage().isBlank())
                return false;
        }
        return true;
    }

    /**
     * Tests the functionality of peek() method by calling peek on a non-empty queue and verifying it
     * 1) returns the PatientRecord having the highest priority (the minimum) and 2) does not remove
     * the PatientRecord from the queue.
     *
     * @return true if the tester verifies a correct functionality and false if at least one bug is
     * detected
     */
    public static boolean testPeekNonEmpty() {
        PatientRecord.resetCounter();

        PatientRecord p1 = new PatientRecord('M', 23, TriageLevel.RED);
        PatientRecord p2 = new PatientRecord('F', 65, TriageLevel.YELLOW);
        PriorityCareAdmissions test2 = new PriorityCareAdmissions(5);
        try {
            test2.addPatient(p1);
            test2.addPatient(p2);
            if (!test2.peek().equals(p1))
                return false;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Tests the functionality of addPatient() method by calling addPatient() on an empty queue and
     * ensuring the method 1) adds the PatientRecord and 2) increments the size.
     *
     * @return true if PriorityCareAdmissions.addPatient() exhibits expected behavior, false
     * otherwise.
     */
    public static boolean testAddPatientEmpty() {
        PatientRecord.resetCounter();
        PriorityCareAdmissions test1 = new PriorityCareAdmissions(5);
        if (!test1.isEmpty())
            return false;

        PatientRecord p1 = new PatientRecord('X', 34, TriageLevel.YELLOW);
        try {
            test1.addPatient(p1);
            if (!test1.peek().equals(p1)) {
                return false;
            }
            if (test1.size() != 1)
                return false;
        } catch (Exception e) {
            return false;
        }

        return true;
    }


    /**
     * Tests the functionality of addPatient() method by calling addPatient() on a non-empty queue and
     * ensuring the method 1) adds the PatientRecord at the proper position and 2) increments the
     * size. Try add at least 5 PatientRecords.
     *
     * @return true if PriorityCareAdmissions.addPatient() exhibits expected behavior, false otherwise
     */
    public static boolean testAddPatientNonEmpty() {
        PatientRecord.resetCounter();
        PriorityCareAdmissions test1 = new PriorityCareAdmissions(10);
        if (!test1.isEmpty())
            return false;

        PatientRecord p1 = new PatientRecord('X', 34, TriageLevel.YELLOW);
        PatientRecord p2 = new PatientRecord('M', 54, TriageLevel.YELLOW);
        PatientRecord p3 = new PatientRecord('F', 24, TriageLevel.GREEN);
        PatientRecord p4 = new PatientRecord('F', 64, TriageLevel.YELLOW);
        PatientRecord p5 = new PatientRecord('X', 34, TriageLevel.GREEN);
        test1.addPatient(p1);
        test1.addPatient(p2);
        test1.addPatient(p3);
        test1.addPatient(p4);
        test1.addPatient(p5);

        PatientRecord add = new PatientRecord('M', 54, TriageLevel.RED);
        try {
            test1.addPatient(add);
            if (test1.size() != 6)
                return false;
            String expected = add.toString() + "\n" + p1.toString() + "\n" + p2.toString() + "\n" +
                    p4.toString() + "\n" + p3.toString() + "\n" + p5.toString() + "\n";
            String actual = test1.toString();
            //System.out.println(actual);
            if (!expected.equals(actual))
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }


    /**
     * Tests the functionality of addPatient() method by calling addPatient() on a full queue and
     * ensuring the method throws an IllegalStateException.
     *
     * @return true if PriorityCareAdmissions.addPatient() exhibits expected behavior, false
     * otherwise.
     */
    public static boolean testAddPatientFull() {
        PatientRecord.resetCounter();
        PriorityCareAdmissions test1 = new PriorityCareAdmissions(5);
        if (!test1.isEmpty())
            return false;

        PatientRecord p1 = new PatientRecord('X', 34, TriageLevel.YELLOW);
        PatientRecord p2 = new PatientRecord('M', 54, TriageLevel.YELLOW);
        PatientRecord p3 = new PatientRecord('F', 24, TriageLevel.GREEN);
        PatientRecord p4 = new PatientRecord('F', 64, TriageLevel.YELLOW);
        PatientRecord p5 = new PatientRecord('X', 34, TriageLevel.GREEN);
        test1.addPatient(p1);
        test1.addPatient(p2);
        test1.addPatient(p3);
        test1.addPatient(p4);
        test1.addPatient(p5);

        try {
            test1.addPatient(new PatientRecord('x', 54, TriageLevel.RED));
            return false;
        } catch (IllegalStateException e) {
            if (e.getMessage() == null || e.getMessage().isBlank())
                return false;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Tests the functionality of addPatient() method by calling addPatient() with a null
     * PatientRecord and ensuring the method throws a NullPointerException.
     *
     * @return true if PriorityCareAdmissions.addPatient() exhibits expected behavior, false
     * otherwise.
     */
    public static boolean testAddPatientNull() {
        PatientRecord.resetCounter();
        PriorityCareAdmissions test1 = new PriorityCareAdmissions(10);
        if (!test1.isEmpty())
            return false;

        PatientRecord p1 = new PatientRecord('X', 34, TriageLevel.YELLOW);
        PatientRecord p2 = new PatientRecord('M', 54, TriageLevel.YELLOW);
        PatientRecord p3 = new PatientRecord('F', 24, TriageLevel.GREEN);
        PatientRecord p4 = new PatientRecord('F', 64, TriageLevel.YELLOW);
        PatientRecord p5 = new PatientRecord('X', 34, TriageLevel.GREEN);
        test1.addPatient(p1);
        test1.addPatient(p2);
        test1.addPatient(p3);
        test1.addPatient(p4);
        test1.addPatient(p5);
        PatientRecord add = null;
        try {
            test1.addPatient(add);
            return false;
        } catch (NullPointerException e) {
            if (e.getMessage() == null || e.getMessage().isBlank())
                return false;
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     * Tests the functionality of removeBestRecord() method by calling removeBestRecord() on an empty
     * queue.
     *
     * @return true if PriorityCareAdmissions.removeBestRecord() throws a NoSuchElementException,
     * false otherwise
     */
    public static boolean testRemoveBestRecordEmpty() {
        PatientRecord.resetCounter();
        PriorityCareAdmissions test1 = new PriorityCareAdmissions(3);
        if (!test1.isEmpty())
            return false;
        try {
            test1.removeBestRecord();
            return false;
        } catch (NoSuchElementException e) {
            if (e.getMessage() == null || e.getMessage().isBlank())
                return false;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Tests the functionality of removeBestRecord() method by calling removeBestRecord() on a queue
     * of size one.
     *
     * @return true if PriorityCareAdmissions.removeBestRecord() returns the correct PatientRecord and
     * size is 0
     */
    public static boolean testRemoveBestRecordSizeOne() {
        PatientRecord.resetCounter();
        PriorityCareAdmissions test1 = new PriorityCareAdmissions(5);
        if (!test1.isEmpty())
            return false;
        PatientRecord p1 = new PatientRecord('M', 33, TriageLevel.YELLOW);
        test1.addPatient(p1);
        if (test1.size() != 1)
            return false;
        try {
            if (!test1.removeBestRecord().equals(p1))
                return false;
            if (test1.size() != 0)
                return false;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Tests the functionality of removeBestRecord() methods.
     * <p>
     * The removeBestRecord() method must remove, and return the patient record with the highest
     * priority in the queue. The size must be decremented by one, each time the removeBestRecord()
     * method is successfully called.
     * <p>
     * Remove the best record from a queue whose size is at least 6. Consider cases where
     * percolate-down recurses on left and right.
     *
     * @return true if PriorityCareAdmissions.removeBestRecord() returns the correct PatientRecord
     * each time it is called and size is appropriately decremented, false otherwise
     */
    public static boolean testRemoveBestRecordNonEmpty() {
        PatientRecord.resetCounter();
        PatientRecord p1 = new PatientRecord('F', 33, TriageLevel.RED);
        PatientRecord p2 = new PatientRecord('F', 22, TriageLevel.YELLOW);
        PatientRecord p3 = new PatientRecord('F', 56, TriageLevel.GREEN);
        PatientRecord p4 = new PatientRecord('F', 43, TriageLevel.RED);
        PatientRecord p5 = new PatientRecord('M', 24, TriageLevel.YELLOW);
        PatientRecord p6 = new PatientRecord('X', 45, TriageLevel.GREEN);
        PriorityCareAdmissions test1 = new PriorityCareAdmissions(10);
        test1.addPatient(p1);
        test1.addPatient(p2);
        test1.addPatient(p3);
        test1.addPatient(p4);
        test1.addPatient(p5);
        test1.addPatient(p6);

        if (test1.size() != 6)
            return false;
        try {
            if (test1.removeBestRecord().equals(p1.toString()))
                return false;
            if (test1.size() != 5)
                return false;
            String expected = p4.toString() + "\n" + p2.toString() + "\n" + p5.toString() + "\n" + p3.toString()
                    + "\n" + p6.toString() + "\n";
            if (!test1.toString().equals(expected))
                return false;

            if (!test1.removeBestRecord().equals(p4))
                return false;
            if (test1.size() != 4)
                return false;
            String expected1 = p2.toString() + "\n" + p5.toString() + "\n" + p3.toString()
                    + "\n" + p6.toString() + "\n";
            if (!test1.toString().equals(expected1))
                return false;

            if (!test1.removeBestRecord().equals(p2))
                return false;
            if (test1.size() != 3)
                return false;
            String expected2 = p5.toString() + "\n" + p3.toString()
                    + "\n" + p6.toString() + "\n";
            if (!test1.toString().equals(expected2))
                return false;

            if (!test1.removeBestRecord().equals(p5))
                return false;
            if (test1.size() != 2)
                return false;
            String expected3 = p3.toString() + "\n" + p6.toString() + "\n";
            if (!test1.toString().equals(expected3))
                return false;

            if (!test1.removeBestRecord().equals(p3))
                return false;
            if (test1.size() != 1)
                return false;
            String expected4 = p6.toString() + "\n";
            if (!test1.toString().equals(expected4))
                return false;

            if (!test1.removeBestRecord().equals(p6))
                return false;
            if (test1.size() != 0)
                return false;
        } catch (Exception e) {
            return false;
        }

        PriorityCareAdmissions test2 = new PriorityCareAdmissions(10);
        test2.addPatient(p3);
        test2.addPatient(p6);
        test2.addPatient(p4);
        test2.addPatient(p2);
        test2.addPatient(p5);
        test2.addPatient(p1);

        try {
            if (test2.removeBestRecord().equals(p1.toString()))
                return false;
            if (test2.size() != 5)
                return false;
            String expected = p4.toString() + "\n" + p2.toString() + "\n" + p5.toString() + "\n" + p3.toString()
                    + "\n" + p6.toString() + "\n";
            if (!test2.toString().equals(expected))
                return false;

            if (!test2.removeBestRecord().equals(p4))
                return false;
            if (test2.size() != 4)
                return false;
            String expected1 = p2.toString() + "\n" + p5.toString() + "\n" + p3.toString()
                    + "\n" + p6.toString() + "\n";
            if (!test2.toString().equals(expected1))
                return false;

            if (!test2.removeBestRecord().equals(p2))
                return false;
            if (test2.size() != 3)
                return false;
            String expected2 = p5.toString() + "\n" + p3.toString()
                    + "\n" + p6.toString() + "\n";
            if (!test2.toString().equals(expected2))
                return false;

            if (!test2.removeBestRecord().equals(p5))
                return false;
            if (test2.size() != 2)
                return false;
            String expected3 = p3.toString() + "\n" + p6.toString() + "\n";
            if (!test2.toString().equals(expected3))
                return false;

            if (!test2.removeBestRecord().equals(p3))
                return false;
            if (test2.size() != 1)
                return false;
            String expected4 = p6.toString() + "\n";
            if (!test2.toString().equals(expected4))
                return false;

            if (!test2.removeBestRecord().equals(p6))
                return false;
            if (test2.size() != 0)
                return false;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Tests the functionality of the clear() method.
     * Should implement at least the following scenarios:
     * - clear can be called on an empty queue with no errors
     * - clear can be called on a non-empty queue with no errors
     * - After calling clear(), the queue should contain zero PatientRecords.
     * - After calling clear(), the size should be 0
     *
     * @return true if PriorityCareAdmissions.clear() functions properly
     */
    public static boolean testClear() {
        PatientRecord.resetCounter();
        //(1)Testing the clear() method on an empty queue.
        PriorityCareAdmissions test1 = new PriorityCareAdmissions(5);
        try {
            test1.clear();
        } catch (Exception e) {
            return false;
        }
        //(2)Testing the clear() method on a non-empty queue.
        test1.addPatient(new PatientRecord('M', 45, TriageLevel.RED));
        test1.addPatient(new PatientRecord('M', 34, TriageLevel.YELLOW));
        try {
            test1.clear();
            if (!test1.isEmpty())
                return false;
            if (test1.size() != 0)
                return false;
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    /**
     * Tests toString() method of PriorityCareAdmissions class.
     *
     * @return true if the tester verifies a correct functionality and false if at least one bug is
     * detected
     */
    public static boolean testToString() {
        PatientRecord.resetCounter();
        PatientRecord p1 = new PatientRecord('F', 33, TriageLevel.RED);
        PatientRecord p2 = new PatientRecord('F', 22, TriageLevel.YELLOW);
        PatientRecord p3 = new PatientRecord('F', 56, TriageLevel.GREEN);
        PatientRecord p4 = new PatientRecord('F', 43, TriageLevel.RED);
        PatientRecord p5 = new PatientRecord('M', 24, TriageLevel.YELLOW);
        PatientRecord p6 = new PatientRecord('X', 45, TriageLevel.GREEN);
        PriorityCareAdmissions test1 = new PriorityCareAdmissions(10);
        test1.addPatient(p1);
        test1.addPatient(p2);
        test1.addPatient(p3);
        test1.addPatient(p4);
        test1.addPatient(p5);
        test1.addPatient(p6);
        String expected = p1.toString() + "\n" + p4.toString() + "\n" + p2.toString() + "\n" +
                p5.toString() + "\n" + p3.toString() + "\n" + p6.toString() + "\n";
        if (!expected.equals(test1.toString()))
            return false;

        return true;
    }


    /**
     * Runs all the tester methods defined in this class.
     *
     * @return true if no bugs are detected.
     */
    public static boolean runAllTests() {

        return testPatientRecordCompareToDifferentTriage()
                && testPatientRecordCompareToSameTriageDifferentArrival()
                && testPatientRecordCompareToSameTriageSameArrival() && testPeekEmpty()
                && testPeekNonEmpty() && testAddPatientEmpty() && testAddPatientNonEmpty()
                && testAddPatientFull() && testAddPatientNull() && testRemoveBestRecordNonEmpty()
                && testRemoveBestRecordEmpty() && testRemoveBestRecordSizeOne() && testClear()
                && testToString();
    }

    /**
     * Main method to run this tester class.
     *
     * @param args list of input arguments if any
     */
    public static void main(String[] args) {
        System.out.println("runAllTests: " + (runAllTests() ? "Pass" : "Failed!"));
        System.out.println("testPatientRecordCompareToDifferentTriage: "
                + (testPatientRecordCompareToDifferentTriage() ? "Pass" : "Failed!"));
        System.out.println("testPatientRecordCompareToSameTriageDifferentArrival: "
                + (testPatientRecordCompareToSameTriageDifferentArrival() ? "Pass" : "Failed!"));
        System.out.println("testPatientRecordCompareToSameTriageSameArrival: "
                + (testPatientRecordCompareToSameTriageSameArrival() ? "Pass" : "Failed!"));
        System.out.println("testConstructor: " + (testConstructor() ? "Pass" : "Failed!"));
        System.out.println("testPeekEmpty: " + (testPeekEmpty() ? "Pass" : "Failed!"));
        System.out.println("testPeekNonEmpty: " + (testPeekNonEmpty() ? "Pass" : "Failed!"));
        System.out.println("testAddPatientEmpty: " + (testAddPatientEmpty() ? "Pass" : "Failed!"));
        System.out
                .println("testAddPatientNonEmpty: " + (testAddPatientNonEmpty() ? "Pass" : "Failed!"));
        System.out.println("testAddPatientFull: " + (testAddPatientFull() ? "Pass" : "Failed!"));
        System.out.println("testAddPatientNull: " + (testAddPatientNull() ? "Pass" : "Failed!"));
        System.out.println(
                "testRemoveBestRecordNonEmpty: " + (testRemoveBestRecordNonEmpty() ? "Pass" : "Failed!"));
        System.out.println(
                "testRemoveBestRecordEmpty: " + (testRemoveBestRecordEmpty() ? "Pass" : "Failed!"));
        System.out.println(
                "testRemoveBestRecordSizeOne: " + (testRemoveBestRecordSizeOne() ? "Pass" : "Failed!"));
        System.out.println("testClear: " + (testClear() ? "Pass" : "Failed!"));
        System.out.println("testToString: " + (testToString() ? "Pass" : "Failed!"));
    }

}
