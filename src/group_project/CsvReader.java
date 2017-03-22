package group_project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class CsvReader {

	private ArrayList<String> assignmentHeads = new ArrayList<String>();

	public List<List<String>> read(File csvFile) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(csvFile));
		String line = null;
		List<List<String>> data = new ArrayList<List<String>>();

		while((line = reader.readLine())!= null){
			String [] temp = line.split(",");
			List<String> row = new ArrayList<String>();
			row = Arrays.asList(temp);
			data.add(row);
		}
		reader.close();
		return data;
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
	public static List<Student> parseData(List<List<String>> data){

		List<Student> students = new ArrayList<Student>();
		Student stud = null;
		double totGrade = 0;
		char letGrade = '\0';
		String name = " ", id = " ";
		ArrayList<Assignment> assignments = null;
		ArrayList<String> headers = new ArrayList<String>();

		Iterator <List<String>> dataList = data.iterator();
		Iterator <String> headerRow = dataList.next().iterator();

		while(headerRow.hasNext()){
			headers.add(headerRow.next());

		}

		while(dataList.hasNext()){

			assignments = new ArrayList<Assignment>();
			Iterator <String> dataRow = dataList.next().iterator();
			for(int i = 0; i < headers.size(); i++){
				if(headers.get(i).toLowerCase().contains("name")){
					//System.out.println(i);
					if(headers.get(i).toLowerCase().contains("first")){
						//System.out.println(i);
						String fName = " ", lName = " ";
						fName = dataRow.next();
						i++;
						lName = dataRow.next();
						name = fName + " " + lName;
						//System.out.println(name);
					}else {
						name = dataRow.next() + dataRow.next();
						name = name.replace("\"","");
						name.replaceAll("/[ ]*,[ ]*|[ ]+/g","");
					}
					//else if(!(headers.get(i+1).toLowerCase()))
					//}

				}
				else if(headers.get(i).toLowerCase().equals("user id") || headers.get(i).toLowerCase().equals("student id") ){
					id = dataRow.next();
					//System.out.println(id);
				}
				else if(headers.get(i).toLowerCase().contains("total")){
					totGrade = Double.parseDouble(dataRow.next());
					//System.out.println(totGrade);
				}
				else if(headers.get(i).toLowerCase().contains("grade")){
					letGrade = dataRow.next().charAt(0);
					//System.out.println(letGrade);
				}
				else{
					assignments.add(new Assignment("", dataRow.next()));
				}

			}
			stud = new Student(name, id, assignments, totGrade, letGrade);
			students.add(stud);
		}
		return students;
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
