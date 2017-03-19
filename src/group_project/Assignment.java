package group_project;

/**
 * @author Brandon Manke
 */
public class Assignment {
    private String name;
    private int grade;

    public Assignment(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    public String getName() { return name; }

    public int getGrade() { return grade; }

    public String setName(String name) {
        this.name = name;
        return name;
    }

    public int setGrade(int grade) {
        if (grade < 0) {
            throw new IllegalArgumentException();
        }
        this.grade = grade;
        return grade;
    }
}
