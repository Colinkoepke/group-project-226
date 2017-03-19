package group_project;

import java.util.ArrayList;

/**
 * Course object class
 * @author Brandon Manke
 */
public class Course {
    private ArrayList<Student> students = new ArrayList<>();
    private String name;
    private String semester;
    private int year;

    public Course(ArrayList<Student> students, String name, String semester, int year) {
        this.students = students;
        this.name = name;
        this.semester = semester;
        this.year = year;
    }

    // getters

    public ArrayList<Student> getStudents() { return students; }

    public String getName() { return name; }

    public String getSemester() { return semester; }

    public int getYear() { return year; }

    // setters - may need to add/remove some of these

    public ArrayList<Student> setStudents(ArrayList<Student> students) {
        this.students = students;
        return students;
    }

    public void pushStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(int index) {
        students.remove(index);
    }

    public String setName(String name) {
        this.name = name
        return name;
    }

    public String setSemester(String semester) {
        this.semester = semester;
        return semester;
    }

    public int setYear(int year) {
        this.year = year;
        return year;
    }
}
