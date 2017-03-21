package group_project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class CsvReader {

	private ArrayList<String> assignmentHeads = new ArrayList<String>();
	
	public List<Student> read(File csvFile) throws IOException {
		
		List<Student> parsedList = new ArrayList<Student>();
		List<List<String>> data = new ArrayList<List<String>>();//Holds each row of data 
		BufferedReader reader = new BufferedReader(new FileReader(csvFile));
		String line = null;
		int passHeader = 0; 

		//Reading Each line of the file
		while ((line = reader.readLine()) != null) {
			String [] temp = line.split(","); //Splitting up the line of read data
			for (String s : temp) {
				String str = s.replaceAll("^\"|\"$", "");
				s = str;
				//System.out.println(s);
			}
			List<String> row = new ArrayList<String>();//Allows an undefined num of elements in the row
			
			//Adding the read row of elements to the list of data
			row = Arrays.asList(temp);
			data.add(row);

            if(passHeader == 0) {
                assignmentHeads.addAll(parseHeaders(row));
				passHeader++;
			} else {
				parsedList.add(parseData(data, row));
			}
			
		}
		reader.close();
		data.clear();
		return parsedList;
	}
	
	public List<String> parseHeaders(List<String> row) {
		Iterator <String> listRow = row.iterator();
		List<String> parsedHeadingRow = new ArrayList<String>();
		String hGrade = " ", hId = " ", hName = " ", hTotal = " ";
		List<String> assignmentName = new ArrayList<String>();

        /**
        *Taking data from file and assigning universal headers for easy data manipulation
        */
		while (listRow.hasNext()) {
            String nextLine = listRow.next();
			if (nextLine.toLowerCase().contains("name")) {
				String hFName, hLName;
				if (nextLine.toLowerCase().equals("student name") || nextLine.toLowerCase().equals("name")) {
					hName = nextLine;
					parsedHeadingRow.add(hName);
				} else if (nextLine.toLowerCase().contains("first")){
					hFName = nextLine;
					hLName = nextLine;
					hName = hFName + hLName;
					parsedHeadingRow.add(hName);
				} else if (nextLine.toLowerCase().contains("last")) {
					hLName = nextLine;
					hFName = nextLine;
					hName = hFName + hLName;
					parsedHeadingRow.add(hName);
				}
			} else if (nextLine.toLowerCase().contains("id")) {
				hId = nextLine;
				parsedHeadingRow.add(hId);
			} else if(nextLine.toLowerCase().contains("grade")) {
				hGrade = nextLine;
				parsedHeadingRow.add(hGrade);
			} else if(nextLine.toLowerCase().contains("total")) {
				hTotal = nextLine;
				parsedHeadingRow.add(hTotal);
			} else {
				assignmentName.add(nextLine);
			}
		}
		return assignmentName;
	}

	/**
	 * This method is intended to make sure the data is stored
	 * to the in the same column as the parsed headers as well as converting
	 * string values to needed data fields
	 */
	public Student parseData(List<List<String>> data, List<String> row) {
		 String name = "", id = "";
		 ArrayList<Assignment> assignments = new ArrayList<>();
		 Student student = null;
		 double totGrade = 0;
		 char letGrade = '\0';
		 
		 Iterator <List<String>> dataIter = data.iterator();//to get the read data
         List<String> dataLine = dataIter.next();
		 Iterator <String> headerRowIter = dataLine.iterator();//to iterate through the headerRow
	
		 while (headerRowIter.hasNext()) {
			 Iterator <String> rowIter = row.iterator();//Iterate through the row being read in csv file
			 while (rowIter.hasNext()) {
                 String headerLine = headerRowIter.next();
                 String line = rowIter.next();
				 if (headerLine.toLowerCase().contains("name")) {
					 String fName = " ", lName = " ";
					 if (headerLine.toLowerCase().equals("student name") || headerLine.toLowerCase().equals("name")) {
							name = line;
						} else if (headerLine.toLowerCase().contains("first")) {
							fName = line;
							lName = line;
							name = fName + lName;
						} else if(headerLine.toLowerCase().contains("last")) {
							lName = line;
							fName = line;
							name = fName + lName;
						}
				 } else if(headerLine.toLowerCase().contains("user")) {
					 id = line;
				 } else if (headerLine.toLowerCase().contains("grade")) {
					 letGrade = line.charAt(0);
				 } else if (headerLine.toLowerCase().contains("total")) {
					 totGrade = Double.parseDouble(line);
				 } else {
					 Assignment assignment = new Assignment();
					 int grade = Integer.parseInt(line);
					 assignment.setGrade(grade);
					 assignments.add(assignment);
				 }
			 }
		 }
		 student = new Student(name, id, assignments, totGrade, letGrade);
		 return student;
	}

    public ArrayList<String> getAssignmentHeads() {
        return assignmentHeads;
    }

    public ArrayList<String> formatAssignmentHeaders() {
        ArrayList<String> projectNames = new ArrayList<>();
        for (String str : assignmentHeads) {
            String temp = str.toLowerCase();
            // checks if it's not an assignment name
            if (temp.contains("name") || temp.contains("total") ||
                    temp.contains("id") || temp.contains("grade")) {
                projectNames.add(str);
            }
        }
        return projectNames;
    }
	
}
