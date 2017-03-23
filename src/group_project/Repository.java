package group_project;

import java.util.ArrayList;

/**
 * This is a simple repository class to store our constant list of courses
 * There is probably a better way of handling this but whatever, it works
 * @author Brandon Manke, Colin Koepke, Ben Dworkin
 */
public class Repository {
    // constant, because there is only one set of courses
    private static final ArrayList<Course> courses = new ArrayList<>();

    public static ArrayList<Course> getCourses() { return courses; }

    public static void pushCourses(Course course) {
        if (course != null)
            courses.add(course);
        else
            throw new IllegalArgumentException();
    }
}
