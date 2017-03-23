package group_project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    public Course addFile(String fileName) throws FileNotFoundException {
        File folder = new File("data/");
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles)
            if (!file.isFile() && !(fileName.equals(file.getName())))
                throw new FileNotFoundException();

        String[] formattedName = formatFileName(fileName);
        CsvReader reader = new CsvReader();

        Course course = new Course();
        course.setName(formattedName[0]);
        course.setSemester(formattedName[1]);
        course.setYear(Integer.parseInt(formattedName[2]));

        for (File file : listOfFiles) {
            if (file.isFile() && (file.getName().equals(fileName))) {
                try {
                    List<List<String>> input = reader.read(file);
                    List<Student> students = reader.parseData(input);
                    ArrayList<String> assignmentHeaders = reader.getAssignmentHeads();
                    for (Student student : students) {
                        ArrayList<Assignment> temp = new ArrayList<>();
                        ArrayList<Assignment> oldAssignments = student.getAssignments();
                        for (int i = 0; i < oldAssignments.size(); i++) {
                            temp.add(new Assignment(assignmentHeaders.get(i), oldAssignments.get(i).getGrade()));
                        }
                        student.setAllAssignments(temp);
                    }
                    course.addAllStudents((ArrayList<Student>) students);
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
    public void getStudent(String userID, String exportFileName) throws IOException {
        Writer writer = new Writer(userID, exportFileName);
        try {
			writer.writeToCSV();
		} catch (IOException e) {
			System.out.println("File not found");
			e.printStackTrace();
            throw e;
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
    public int[] getGrades(String courseNumber, String semester, String year) throws Exception {
        int[] grades = null;
        courseNumber = courseNumber.toLowerCase();
        semester = semester.toLowerCase();
        year = year.toLowerCase();

        if (courseNumber.equals("none")) {
            for (Course c : Repository.getCourses()) {
                if (semester.equals(c.getSemester()) && year.equals(Integer.toString(c.getYear()))) {
                    try {
                        grades = calculateGrades(c);
                    } catch (Exception e) {
                        throw e;
                    }
                } else {
                    System.out.println("Class not found, sorry. Try adding it to the repository.");
                }
            }
        } else if (semester.equals("none") || year.equals("none")) {
            for (Course c : Repository.getCourses()) {
                if (courseNumber.equals(c.getName())) {
                    try {
                        grades = calculateGrades(c);
                    } catch (Exception e) {
                        throw e;
                    }
                } else {
                    System.out.println("Class not found, sorry. Try adding it to the repository.");
                }
            }
        } else {
            for (Course c : Repository.getCourses()) {
                if (courseNumber.equals(c.getName()) && semester.equals(c.getSemester()) && year.equals(Integer.toString(c.getYear()))) {
                    try {
                        grades = calculateGrades(c);
                    } catch (Exception e) {
                        throw e;
                    }
                } else {
                    System.out.println("Class not found, sorry. Try adding it to the repository.");
                }
            }
        }

        if (grades != null)
            return grades;
        else
            throw new NullPointerException();
    }

    private static int[] calculateGrades(Course course) {
        int[] grades = new int[5];
        for (int i = 0; i < grades.length; i++) {
            grades[i] = 0;
        }

        for (Student s : course.getStudents()) {
            if (s.getLetterGrade() == 'A' || s.getLetterGrade() == 'a') {
                grades[0]++;
            } else if (s.getLetterGrade() == 'B' || s.getLetterGrade() == 'b') {
                grades[1]++;
            } else if (s.getLetterGrade() == 'C' || s.getLetterGrade() == 'c') {
                grades[2]++;
            } else if (s.getLetterGrade() == 'D' || s.getLetterGrade() == 'd') {
                grades[3]++;
            } else if (s.getLetterGrade() == 'F' || s.getLetterGrade() == 'f') {
                grades[4]++;
            }
        }
        for (int i : grades) {
            System.out.println(i);
        }
        return grades;
    }

    private static String[] formatFileName(String fileName) {
        String temp = fileName.split("\\.")[0]; // removes csv extension from file
        String[] formatted = temp.split("[-\\s]"); // puts remaining file into array so parts can be read
        return formatted;
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

}
