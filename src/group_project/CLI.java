package group_project;

import java.util.Scanner;

/**
 * Simple Command Line Interface for project.
 * TODO:
 * Some of these functions mainly getStudentInfo() & getGrades() could be refactored
 * since they are pretty long. If time permits it, I will organize this further.
 * There are also still some small logic issue, mainly just formatting
 * @author Brandon Manke
 */
public class CLI {
    private Scanner scanner = new Scanner(System.in);
    private boolean running = true;
    private Listener listener = new Listener();

    public CLI() { super(); }

    // if you want to specify a scanner
    public CLI(Scanner scanner) { this.scanner = scanner; }

    /**
     * Main CLI loop, handles all of the commands and passes them onto
     * their respected functions.
     */
    public void runCLI() {
        System.out.println("Running!");
        running = true;

        while (running) {
            String input = getInput();

            while (input.isEmpty()) {
                System.err.println("Invalid input, please try again.");
                input = getInput();
            }

            if (input.equals("help")) {
                printHelp();
            } else if (input.equals("e")) {
                System.out.println("Exiting program...");
                running = false;
            } else if ((Character.toString(input.charAt(0))).equals("a")) { // check for a in statement
                addFile(input);
            } else if ((Character.toString(input.charAt(0))).equals("s")) { // check for s in statement
                studentInfo(input);
            } else if ((Character.toString(input.charAt(0))).equals("g")) { // check for g in statement
                getGrades(input);
            } else {
                System.out.println();
                System.err.println("Invalid input, please try again.");
            }
        }
    }

    private String getInput() {
        System.out.println("Type help for more options");
        System.out.println("Please enter a command:");
        String input = scanner.nextLine();
        return input.toLowerCase();
    }

    /**
     * a <filename> - to add filename
     * s <studentid> - to display info for student based on id
     * g <coursenumber> - &/or <semester&year>
     * e - exit
     */
    private void printHelp() {
        System.out.println("List of commands:");
        System.out.println(
                        "a <filename> - to read and add file to data repo\n" +
                        "s <studentid> - to display info for student based on id\n" +
                        "g <coursenumber> AND/OR <semester&year> - to display grades for specified course\n" +
                        "e - exit program");
    }

    /**
     * Might need to change return type but we will see
     * @param input {String} - Scanner input
     */
    private void addFile(String input) {
        String[] split = input.split("\\s+"); // separates string by spaces
        String fileName;
        if (split[0].equals("a")) { // checking that the format a *space* "filename" etc.
            if (split.length == 2) {
                fileName = split[1];
                System.out.println("Reading: " + fileName);
                listener.addFile(fileName);
            } else if (split.length == 1) {
                System.out.println("Please enter the file name:");
                fileName = scanner.nextLine();
                System.out.println("Reading: " + fileName);
                listener.addFile(fileName);
            } else {
                System.out.println();
                System.err.println("Invalid input, please try again.");
            }
        }
    }

    /**
     * Need validation checks for studentID, maybe handle this in the function
     * that actually pulls the data idk.
     * @param input {String} - Scanner input
     */
    private void studentInfo(String input) {
        String[] split = input.split("\\s+"); // separates string by spaces
        String studentID;
        String fileName;
        if (split[0].equals("s")) { // checking format s *space* studentID ect.
            if (split.length == 3) {
                studentID = split[1];
                fileName = split[2];
                System.out.println("Exporting student info..");
                // get student info function:
                // ...
            } else if (split.length == 2) {
                studentID = split[1];
                do {
                    System.out.println("Please enter the name of the export file:");
                    fileName = scanner.nextLine();
                } while (fileName.isEmpty());
                System.out.println("Exporting student info..");
                // get student info function:
                // ...
            } else if (split.length == 1) {
                do {
                    System.out.println("Please enter the students id:");
                    studentID = scanner.nextLine();
                    studentID = studentID.toLowerCase();
                } while (studentID.isEmpty());

                do {
                    System.out.println("Please enter the name of the export file:");
                    fileName = scanner.nextLine();
                } while (fileName.isEmpty());
                System.out.println("Exporting student info..");
                // get student info function:
                // ...
            } else {
                System.out.println();
                System.err.println("Invalid input, please try again.");
            }
        } else {
            System.out.println();
            System.err.println("Invalid input, please try again.");
        }
    }

    /**
     * We can check for "none" inputs - when we pass the
     * params { courseNumber, semester, year } into the get grades function.
     * @param input {String} - Scanner input
     */
    private void getGrades(String input) {
        String[] split = input.split("\\s+"); // separates string by spaces
        String courseNumber;
        String semester;
        String year;
        if (split[0].equals("g")) {
            if (split.length == 4) {
                courseNumber = split[1];
                semester = split[2];
                year = split[3];
                if (courseNumber.equals("none") && (semester.equals("none") || year.equals("none"))) {
                    return;
                }
                System.out.println("Getting grade info..");
                // pass into function
                // ...
            } else if (split.length == 2) {
                courseNumber = split[1];
                do {
                    System.out.println("Please enter the semester of the class: (enter 'none' if unknown)"); // need input validation
                    semester = scanner.nextLine();
                    semester = semester.toLowerCase();
                } while (semester.isEmpty());

                do {
                    System.out.println("Please enter the year of the class: (enter 'none' if unknown)");
                    year = scanner.nextLine();
                    year = year.toLowerCase();
                } while (year.isEmpty());

                if (courseNumber.equals("none") && (semester.equals("none") || year.equals("none"))) {
                    return;
                }
                System.out.println("Getting grade info..");
                // pass into function
                // ...
            } else if (split.length == 1) {
                do {
                    System.out.println("Please enter the course number of the class: (enter 'none' if unknown)"); // need input validation
                    courseNumber = scanner.nextLine();
                    courseNumber = courseNumber.toLowerCase();
                } while (courseNumber.isEmpty());

                do {
                    System.out.println("Please enter the semester of the class: (enter 'none' if unknown)"); // need input validation
                    semester = scanner.nextLine();
                    semester = semester.toLowerCase();
                } while (semester.isEmpty());

                do {
                    System.out.println("Please enter the year of the class: (enter 'none' if unknown)");
                    year = scanner.nextLine();
                    year = year.toLowerCase();
                } while (year.isEmpty());

                if (courseNumber.equals("none") && (semester.equals("none") || year.equals("none"))) {
                    return;
                }
                System.out.println("Getting grade info..");
                // pass into function
                // ...
            } else {
                System.out.println();
                System.err.println("Invalid input, please try again.");
            }
        } else {
            System.out.println();
            System.err.println("Invalid input, please try again.");
        }
    }
}
