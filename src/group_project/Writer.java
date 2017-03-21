package group_project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * @author Colin Koepke
 */
public class Writer {

	// student variables
	private String studentID;
	private String fileName;

	// student course variables
	private String courseName;
	private String semester;
	private Integer year;

	// assignment variable
	private ArrayList<Assignment> assignments;

	// Delimiter used in CSV filE
	private final String COMMA_DELIMITER = ",";
	private final String NEW_LINE_SEPARATOR = "\n";
	FileWriter writer;

	/**
	 * 
	 * this is the constructor to be called to initialize the variables
	 * 
	 * @param studentID
	 *            student ID that we want to find
	 * @param fileName
	 *            file name where the data should be exported
	 */
	public Writer(String studentID, String fileName) {

		this.studentID = studentID;
		this.fileName = fileName;
	}

	/**
	 * iterates through the arraylist of all the courses that have been added
	 * then iterates through all students in the course to see if the student id
	 * is found then executes function to get student ID if that is found
	 * 
	 * @throws IOException
	 */
	public void writeToCSV() throws IOException {
		ArrayList<Course> listOfCourses = Repository.getCourses();
		Course courseCounter = null;
		ArrayList<Student> studentsListed = new ArrayList<>();
		Student student = null;
		String testStudentID = null;
		BufferedWriter bWriter = null;

		StringBuilder s = new StringBuilder();
		s.append("data/");
		s.append(fileName);

		File file = new File(s.toString());

		// if file is not found
		try {
			writer = new FileWriter(file);
			bWriter = new BufferedWriter(writer);
		} catch (IOException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}

		if (!listOfCourses.isEmpty()) {
			for (int i = 0; i < listOfCourses.size(); i++) {
				courseCounter = listOfCourses.get(i);
				courseName = courseCounter.getName();
				semester = courseCounter.getSemester();
				year = (Integer) courseCounter.getYear();
				studentsListed = courseCounter.getStudents();

				for (int j = 0; j < studentsListed.size(); j++) {
					student = studentsListed.get(j);
					testStudentID = student.getUserID();
					assignments = student.getAssignments();

					if (testStudentID.equals(studentID)) {
						// bWriter.append(NEW_LINE_SEPARATOR);
						bWriter.append(studentID);
						bWriter.append(COMMA_DELIMITER);
						bWriter.append(courseName);
						bWriter.append(COMMA_DELIMITER);
						bWriter.append(semester);
						bWriter.append(COMMA_DELIMITER);
						bWriter.append(year.toString());
						bWriter.append(COMMA_DELIMITER);
						for (int k = 0; k < assignments.size(); k++) {
							bWriter.append(assignments.get(k).getName());
							bWriter.append(COMMA_DELIMITER);
							bWriter.append(Integer.toString(assignments.get(k).getGrade()));
							bWriter.append(NEW_LINE_SEPARATOR);
						}
						bWriter.flush();
						System.out.println("FIle has been written");
					}

				}
			}

		}

	}

}
