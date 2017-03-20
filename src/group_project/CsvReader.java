package group_project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;



public class CsvReader
{
	public List<List<Student>> read (File csvFile) throws IOException{
		
		List<List<Student>> parsedList = new ArrayList<List<Student>>();
		List<List<String>> data = new ArrayList<List<String>>();//Holds each row of data 
		BufferedReader reader = new BufferedReader(new FileReader(csvFile));
		String line = null;
		
		//Reading Each line of the file 
		while((line = reader.readLine())!= null){
			String [] temp = line.split(",");//Splitting up the line of read data 
			List<String> row = new ArrayList<String>();//Allows an undefined num of elements in the row
			
			//Adding the read row of elements to the list of data
			row = Arrays.asList(temp);
			data.add(row);
			
			if(parsedList.isEmpty()){
				parsedList.add(parseHeaders(row));
			}else{
				parsedList.add(parseData(data, row));
			}
			
		}
		reader.close();
		data.clear();
		return parsedList;
		
		
	}
	
	public List<Student> parseHeaders(List<String> row){
		Iterator <String> listRow = row.iterator();
		List<Student> parsedHeadingRow = new ArrayList<Student>();
		Student stu = null;
		String hName = " ", hId= " ", hGrade= " ", hTotal = " ";
		ArrayList<String> assignmentName = new ArrayList<String>();
		
		/**
		*Taking data from file and assigning universal headers for easy data manipulation 
		*/
		while(listRow.hasNext()){
			if(listRow.next().toLowerCase().contains("name")){
				String hFName, hLName;
					
				if(listRow.next().toLowerCase().equals("student name") || listRow.next().toLowerCase().equals("name")){
					hName = listRow.next();
				}
				else if(listRow.next().toLowerCase().contains("first")){
					hFName = listRow.next();
					hLName = listRow.next();
					hName = hFName + hLName;
				}
				else if(listRow.next().toLowerCase().contains("last")){
					hLName = listRow.next();
					hFName = listRow.next();
					hName = hFName + hLName;
				}
			}
			else if(listRow.next().toLowerCase().contains("id")){
				hId = listRow.next();
			}
			else if(listRow.next().toLowerCase().contains("grade")){
				hGrade = listRow.next();
			}
			else if(listRow.next().toLowerCase().contains("total")){
				hTotal = listRow.next();
			}else{
				assignmentName.add(listRow.next());		
			}
		}
		stu = new Student(hName, hId, assignmentName, hTotal, hGrade);
		parsedHeadingRow.add(stu);
		return parsedHeadingRow;
	}
	
	public List<Student> parseData(List<List<String>> data, List<String> row){
		/**
		 * This method is intended to make sure the data is stored 
		 * to the in the same column as the parsed headers as well as converting
		 * string values to needed data fields
		 */
		
		 List<Student> parsedDataRow = new ArrayList<Student>();
		 String name = "", id = " ";
		 ArrayList<Integer> assignments = new ArrayList<Integer>();
		 Student stud = null;
		 double totGrade = 0;
		 char letGrade = '\0';
		 
		 Iterator <List<String>> dataIter = data.iterator();//to get the read data
		 Iterator <String> headerRowIter = dataIter.next().iterator();//to iterate through the headerRow
	
		 while(headerRowIter.hasNext()){
			 Iterator <String> rowIter = row.iterator();//Iterate through the row being read in csv file
			 while(rowIter.hasNext()){
				 if(headerRowIter.next().toLowerCase().contains("name")){
					 String fName = " ", lName = " ";
					 if(headerRowIter.next().toLowerCase().equals("student name") || headerRowIter.next().toLowerCase().equals("name")){
							name = rowIter.next();
						}
						else if(headerRowIter.next().toLowerCase().contains("first")){
							fName = rowIter.next();
							lName = rowIter.next();
							name = fName + lName;
						}
						else if(headerRowIter.next().toLowerCase().contains("last")){
							lName = rowIter.next();
							fName = rowIter.next();
							name = fName + lName;
						}
				 }
				 else if(headerRowIter.next().toLowerCase().contains("id")){
					 id = rowIter.next();
				 }
				 else if (headerRowIter.next().toLowerCase().contains("grade")){
					 letGrade = rowIter.next().charAt(0);
				 }
				 else if (headerRowIter.next().toLowerCase().contains("total")){
					 totGrade = Double.parseDouble(rowIter.next());
				 }else {
					 assignments.add(Integer.parseInt(rowIter.next()));	
				 }
			 }
		 }
		 stud = new Student(name, id, assignments, totGrade, letGrade);
		 parsedDataRow.add(stud);
		 return parsedDataRow;
	}
	
}
