package group_project;

import java.util.ArrayList;

/**
 * Student class
 * @author Brandon Manke, Colin Koepke, Ben Dworkin
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

    public String toString() {
        return this.name + " " + this.userID + " " +  this.assignments + " " + this.totalGrade + " " + this.letterGrade;
    }
}
