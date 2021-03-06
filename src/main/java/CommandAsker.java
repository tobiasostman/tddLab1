import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class CommandAsker {

    private final Scanner scanner;
    private final PrintStream printStream;

    public CommandAsker(InputStream in, PrintStream printStream) {
        this.scanner = new Scanner(in);
        this.printStream = printStream;
    }

    public String ask(String message) {
        printStream.println(message);
        String input = scanner.nextLine();
        if (input.contains("]") && scanner.hasNextLine()) {
            input += "\n";
            input += scanner.nextLine();
        }
        return input;
    }


}