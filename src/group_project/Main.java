package group_project;

public class Main {

    public static void main(String[] args) {
        CLI program = new CLI();
        try {
            //program.runCLI();
        } catch (Exception e) {
            e.getCause(); // temp exception for now
        }

        // JDK 8 and above
        StreamTest test = new StreamTest();
        test.runTest();
        System.out.println();

        // JDK 7 and above
        FileInputStreamTest test2 = new FileInputStreamTest();
        test2.runTest();
    }
}
