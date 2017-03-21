package group_project;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * File Input Stream test
 */
public class FileInputStreamTest {
    private File folder = new File("data/");
    private FileInputStream fis;

    private void readFile(File file) {
        try {
            fis = new FileInputStream(file);

            int content;

            while ((content = fis.read()) != -1) {
                System.out.print((char) content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null)
                    fis.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void runTest() {
        for (File f : folder.listFiles()) {
            System.out.println("\n" + f.getName() + "\n");
            readFile(f);
        }
    }
}
