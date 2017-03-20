package group_project;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Consider adding first name and last names to this. Depends on how reader reads values.
 * @author Brandon Manke
 */
public class Student {
    private String name;
    private String userID;
    private ArrayList<Assignment> assignments;
    private double totalGrade;
    private char letterGrade;

    public Student(String name, String userID, ArrayList<Assignment> assignments, double totalGrade, char letterGrade) {
        this.name = name;
        this.userID = userID;
        this.assignments = assignments;
        this.totalGrade = totalGrade;
        this.letterGrade = letterGrade;
    }

    public String getName() { return name; }

    public String getUserID() { return userID; }

    public ArrayList<Assignment> getAssignments() { return assignments; }

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

    public ArrayList<Assignment> pushAssignments(Assignment assignment) {
        assignments.add(assignment);
        return assignments;
    }

    public ArrayList<Assignment> removeAssignment(int index) {
        assignments.remove(index);
        return assignments;
    }

    public ArrayList<Assignment> setAllAssignments(ArrayList<Assignment> assignments) {
        this.assignments = assignments;
        return assignments;
    }
}
