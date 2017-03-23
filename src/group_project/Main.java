package group_project;

/**
 * @author Brandon Manke, Colin Koepke, Ben Dworkin
 */
public class Main {

    public static void main(String[] args) {
        CLI program = new CLI();
        try {
            program.runCLI();
        } catch (Exception e) {
            e.getCause(); // temp exception for now
        }
    }
}
