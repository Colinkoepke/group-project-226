package group_project;

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
        // TODO
        return null;
    }

    /**
     * User input: 's'
     * This is if the user asks to display all relevant information to a student across all collected files.
     * We then gather all of the info from the repo, and export it to a csv file. This is the job of the Writer class
     * @param userID - {String} - unique student ID tied to the student
     * @param exportFileName {String} - name of file to be created and exported
     * @return {Student} returns a student object possible to be passed somewhere? the return here is not finalized yet
     */
    public Student getStudent(String userID, String exportFileName) {
        // TODO
        return null;
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
}
