package group_project;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Simple Test
 * JDK 8 and above
 */
public class StreamTest {
    private List<String> studentsArr = new ArrayList<String>();
    private List<String> headersArr = new ArrayList<String>();
    private boolean isFirst = true;
    private File folder = new File("data/");

    public void testStream(String fileName) {
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            if (isFirst) {
                headersArr = stream.collect(Collectors.toList());
                isFirst = false;
            } else {
                studentsArr = stream.collect(Collectors.toList());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        studentsArr.forEach(System.out::println);
    }

    public List<String> getHeadersArr() {
        return headersArr;
    }

    public List<String> getStudentsArr() {
        return studentsArr;
    }

    public void runTest() {
        // This doesn't currently print 226 unless I hard code it in
        //testStream("data/226-fall-1996.csv");
        for (File f : folder.listFiles()) {
            System.out.println("\n" + f.getName() + "\n");
            testStream(f.getPath());
        }
    }
}
