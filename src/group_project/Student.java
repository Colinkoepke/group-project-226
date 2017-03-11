package group_project;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Consider adding first name and last names to this. Depends on how reader reads values.
 * @author Brandon Manke
 */
public class Student {
    private String name;
    private String userID;
    private HashMap<String,Integer> assignments;
    private double totalGrade;
    private char letterGrade;

    public Student(String name, String userID, HashMap<String,Integer> assignments, double totalGrade, char letterGrade) {
        this.name = name;
        this.userID = userID;
        this.assignments = assignments;
        this.totalGrade = totalGrade;
        this.letterGrade = letterGrade;
    }

    public String getName() { return name; }

    public String getUserID() { return userID; }

    // maybe rework this
    public HashMap<String,Integer> getAssignments() { return assignments; }

    public double getTotalGrade() { return totalGrade; }

    public char getLetterGrade() { return letterGrade; }

    public String setName(String name) {
        this.name = name;
        return name;
    }

    public String setUserID(String userID) {
        this.userID = userID;
        return userID;
    }

}
