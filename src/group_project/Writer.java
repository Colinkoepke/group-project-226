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

	//student variables
	private String studentID;
	private String fileName;
	private Course course;
	private Student student;

	//student course variables
	private String courseName;
	private String semester;
	private Integer year;

	//assignment variable
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
		String assignmentName = null;
		
		
		File file = new File("data/" + fileName + ".csv");
		//FileWriter fileWriter = new FileWriter(file);
		//PrintWriter outFile = new PrintWriter(fileWriter);
		//ArrayList<String> outputList = new ArrayList<>();

		//if file is not found
		try {
			writer = new FileWriter(file);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
		} catch (IOException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}

		if(!listOfCourses.isEmpty()){
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
						writer.append(NEW_LINE_SEPARATOR);
						writer.append(studentID);
						writer.append(COMMA_DELIMITER);
						writer.append(courseName);
						writer.append(COMMA_DELIMITER);
						writer.append(semester);
						writer.append(COMMA_DELIMITER);
						writer.append(year.toString());
						writer.append(COMMA_DELIMITER);
						for (int k = 0; k < assignments.size(); k++) {
							writer.append(assignments.get(k).getName());
							writer.append(COMMA_DELIMITER);
							writer.append(Integer.toString(assignments.get(k).getGrade()));
							writer.append(NEW_LINE_SEPARATOR);
						}

					} 
				
				}
			}

		}
		/**if(!listOfCourses.isEmpty()){
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
					outputList.add(NEW_LINE_SEPARATOR);
					outputList.add(studentID);
					outputList.add(COMMA_DELIMITER);
					outputList.add(courseName);
					outputList.add(COMMA_DELIMITER);
					outputList.add((semester));
					outputList.add(COMMA_DELIMITER);
					outputList.add(year.toString());
					outputList.add(COMMA_DELIMITER);
					for (int k = 0; k < assignments.size(); k++) {
						outputList.add(assignments.get(k).getName());
						outputList.add(COMMA_DELIMITER);
						outputList.add(Integer.toString(assignments.get(k).getGrade()));
						outputList.add(NEW_LINE_SEPARATOR);
					}

				} 
			
			}
		}
		for(int i = 0; i < outputList.size(); i++){
			outFile.print(outputList.get(i));
		}

	}*/
	}

}
