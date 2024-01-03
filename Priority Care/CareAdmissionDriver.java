//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    This class implements the Driver Application for cs300 spring 2023 p10 Priority Care assignment.
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

import java.util.Scanner;
import java.util.ArrayList;

/**
 * This class implements the Driver Application for cs300 spring 2023 p10 Priority Care assignment
 */
public class CareAdmissionDriver {

    // welcome, good bye, and syntax error messages
    private final String WELCOME_MSG = "--- Welcome to the Priority Care Admissions App! ----";
    private final String GOOD_BYE_MSG = "---------- BYE! Thanks for using our App! ----------";
    private final String SYNTAX_ERROR_MSG = "Syntax Error: Please enter a valid command!";

    private PriorityCareAdmissions queue; // priority queue storing the patient records of unseen
    // patients
    private ArrayList<PatientRecord> seenPatients; // list of seen patients
    private Scanner scanner; // scanner to read user input command lines


    /**
     * Creates and initializes a CareAdmissionDriver object
     *
     * @param capacity capacity of the admission queue
     * @throws IllegalArgumentException if capacity is negative
     */
    public CareAdmissionDriver(int capacity) {
        queue = new PriorityCareAdmissions(capacity);
        scanner = new Scanner(System.in);
        seenPatients = new ArrayList<PatientRecord>();
    }

    /**
     * Main method that launches this driver application
     *
     * @param args list of input arguments if any
     */
    public static void main(String[] args) {
        // create a new CareAdmissionDriver object and start the application
        new CareAdmissionDriver(20).runApplication();
    }


    /**
     * Runs this application
     */
    public void runApplication() {
        System.out.println(WELCOME_MSG); // display welcome message
        // read and process user command lines
        processUserCommands();
        scanner.close();// close the scanner
        System.out.println(GOOD_BYE_MSG);// display good bye message
    }

    /**
     * Prints out the menu of this application
     */
    private void displayMenu() {
        System.out.println("\n==================== MENU ====================");
        System.out.println("Enter one of the following options:");
        System.out.println("[1 <age> <M/F/X> <RED/YELLOW/GREEN>] Add a new patient record");
        System.out.println("[2] Show next patient");
        System.out.println("[3] See next patient");
        System.out.println("[4] List all unseen patient records");
        System.out.println("[5] List seen patients");
        System.out.println("[6] Clear the care admission queue");
        System.out.println("[7] Logout and EXIT");
        System.out.println("----------------------------------------------");
    }

    /**
     * Reads and processes user command line to add a new patient record
     *
     * @param commandLine user command line to add a new Patient Record to the Care Admission Queue
     */
    private void addPatientRecord(String commandLine) {
        String[] commands = commandLine.trim().split(" "); // split user command
        if (commands.length < 4) {
            System.out.println(SYNTAX_ERROR_MSG);
        } else {
            try {
                // read age
                int age = Integer.parseInt(commands[1]);
                // OPTIONAL: you can further check for the validity of age 0 .. 150, for instance
                // read gender
                if (commands[2].length() != 1) {
                    System.out.println(SYNTAX_ERROR_MSG
                            + " Gender can be M (for Male), F (for Female), or X (for Other), only.");
                } else {
                    char gender = commands[2].toUpperCase().charAt(0);
                    // read triage level
                    TriageLevel triage = TriageLevel.valueOf(commands[3]);
                    // TODO
                    // Create a new PatientRecord given age, gender, and triage level values
                    // and add it to the queue of this care admission driver
                    PatientRecord p1 = new PatientRecord(gender, age, triage);
                    queue.addPatient(p1);

                }
            } catch (NumberFormatException e) {
                System.out.println(SYNTAX_ERROR_MSG + " Invalid age!");

            } catch (IllegalArgumentException e) {
                System.out.println(SYNTAX_ERROR_MSG
                        + " Invalid triage level! Should be either RED/YELLOW/GREEN");
            }
        }

    }

    /**
     * Reads and processes user command lines
     */
    private void processUserCommands() {
        // display the main menu
        displayMenu();
        // read user command line
        String promptCommandLine = "ENTER COMMAND: ";
        System.out.print(promptCommandLine);
        String command = scanner.nextLine();

        // read and process user command lines until the user signs out
        while (command.charAt(0) != '7') { // 7 to logout: quit
            try {
                switch (command.charAt(0)) {

                    case '1': // [1 <age> <M/F/X> <RED/YELLOW/GREEN>] Add a new patient record
                        this.addPatientRecord(command);
                        break;
                    case '2': // [2] Show the next patient record
                        // TODO
                        // Print the string representation of the patient record at the root of the priority
                        // queue
                        System.out.println(queue.peek().toString());

                        break;
                    case '3': // [3] See next Patient
                        // TODO

                        // remove the best record from the priority queue
                        PatientRecord nextPatient = queue.removeBestRecord();
                        ;// update this

                        // mark nextPatient to be seen
                        nextPatient.seePatient();
                        // add nextPatient to index 0 of the list of seenPatients by calling ArrayList.add(0,
                        // PatientRecord) method
                        seenPatients.add(0, nextPatient);

                        System.out.println(nextPatient);
                        break;
                    case '4': // [4] Print the list of unseen patient records
                        System.out.println("List of unseen patients:");
                        System.out.println(this.queue.toString());
                        break;
                    case '5': // [5] Print list of seen Patients
                        System.out.println("List of seen patients:");
                        for (PatientRecord p : seenPatients) {
                            System.out.println(p);
                        }
                        break;
                    case '6': // [6] Clear the Care Admission Queue
                        System.out.println("Sorry! We are closed due to out of control circumstances!");
                        queue.clear();
                        break;
                    default:
                        System.out.println(SYNTAX_ERROR_MSG); // Syntax Error

                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            // read next user command line
            displayMenu(); // display the main menu
            System.out.print(promptCommandLine);
            command = scanner.nextLine(); // read user command line
        }
    }

}
