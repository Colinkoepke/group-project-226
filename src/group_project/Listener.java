package group_project;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Brandon Manke
 */
public class Listener {
    // I don't think we need to store anything in this class, but we will see

    public Listener() {
        super();
    }

    /**
     * User input: 'a'
     * This call will send the file name and possible other stuff on to the reader class
     * @param fileName {string} - Name of file to load into the data repository
     * @return {Course} - returns a course object, with all of the information from
     * the csv file to be sent to the repository.
     */
    public Course addFile(String fileName) {
        // TODO: Current issues last name is printed twice (I think) in student object, also '"' in name throw errors
        String[] formattedName = formatFileName(fileName);
        CsvReader reader = new CsvReader();

        Course course = new Course();
        course.setName(formattedName[0]);
        course.setSemester(formattedName[1]);
        course.setYear(Integer.parseInt(formattedName[2]));

        File folder = new File("data/");
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            System.out.println(file.getName());
            if (file.isFile() && (file.getName().equals(fileName))) {
                // wtf
                try {
                    ArrayList<Student> students = new ArrayList<>(reader.read(file));
                    for (Student student : students) {
                        student.setAllAssignments(
                                formatAssignments(student.getAssignments(), reader.getAssignmentHeads())
                        );
                    }
                    System.out.println("Students size: " + students.size());
                    course.addAllStudents(students);
                } catch (IOException e) {
                    System.err.println("File read error");
                    e.getStackTrace();
                }
            }
        }

        if (!fileAlreadyExists(course)) {
            Repository.pushCourses(course);
        } else {
            System.out.println("That file already exists in the repository. \n");
        }
        return course;
    }

    /**
     * User input: 's'
     * This is if the user asks to display all relevant information to a student across all collected files.
     * We then gather all of the info from the repo, and export it to a csv file. This is the job of the Writer class
     * @param userID - {String} - unique student ID tied to the student
     * @param exportFileName {String} - name of file to be created and exported
     * @return {Student} returns a student object possible to be passed somewhere? the return here is not finalized yet
     */
    public void getStudent(String userID, String exportFileName) {
        Writer writer = new Writer(userID, exportFileName);
        try {
			writer.writeToCSV();
		} catch (IOException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}
    }

    /**
     * User input: 'g'
     * This function gets the grade statistics for a specified course (this will probably be the easiest to finish).
     * @param courseNumber {String} - course number
     * @param semester {String} - semester of course
     * @param year {int} - year of course
     * @return {int[]} - Returns an array of integers counting the number of people who received A's, B's, C's, etc.
     * A's represent 0th index, B's the 1st index, and so on.
     */
    public int[] getGrades(String courseNumber, String semester, int year) {
        // TODO
        return null;
    }

    private static String[] formatFileName(String fileName) {
        String temp = fileName.split("\\.")[0]; // removes csv extension from file
        String[] formatted = temp.split("[-\\s]"); // puts remaining file into array so parts can be read
        return formatted;
    }

    private static ArrayList<Assignment> formatAssignments(ArrayList<Assignment> assignments, ArrayList<String> header) {
        for (int i = 0; i < assignments.size(); i++) {
            assignments.get(i).setName(header.get(i)); // maybe this works idfk
        }
        return assignments;
    }


    /**
     * Compares course to be added to all courses currently in repository, if it already exists then it returns true
     * @param course { Course } - course object to compare to courses already in repository
     */
    private static boolean fileAlreadyExists(Course course) {
        for (Course c : Repository.getCourses()) {
            if ((c.getName().equals(course.getName())) &&
                    (c.getSemester().equals(course.getSemester())) &&
                    (c.getYear() == course.getYear())) {
                return true;
            }
        }
        return false;
    }

    /**
     * May or may not end up using this
     * Reads & formats students from CsvReader, since it only returns a list of students
     * We have to format it and put it into a course object
     * @param reader { CsvReader } - Class that reads specified .csv files
     * @param fileName { String } - Name of file to read
     */
    private static Course readAndFormatStudents(CsvReader reader, String fileName) {
        Course course = new Course();

        return course;
    }

}
