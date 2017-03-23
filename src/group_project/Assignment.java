package group_project;

/**
 * Assignment Class
 * @author Brandon Manke, Colin Koepke, Ben Dworkin
 */
public class Assignment {
    private String name;
    private String grade;

    public Assignment(String name, String grade) {
        this.name = name;
        this.grade = grade;
    }

    public Assignment() {
        super();
    }

    public String getName() { return name; }

    public String getGrade() { return grade; }

    public String setName(String name) {
        this.name = name;
        return name;
    }

    public String setGrade(String grade) {
        if (Integer.parseInt(grade) < 0) {
            throw new IllegalArgumentException();
        }
        this.grade = grade;
        return grade;
    }
}
