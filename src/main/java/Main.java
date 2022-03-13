import java.util.Objects;

public class Main {

    public static void main(String[] args) {
        Logger logger = new Logger();
        CommandAsker commandAsker = new CommandAsker(System.in, System.out);
        StringCalculator stringCalculator = new StringCalculator(logger);
        System.out.println("Welcome to string calculator 6000");
        System.out.println("To calculate a string type \"scalc '1,2,3'\"");
        System.out.println("To exit type: \"exit\"");
        boolean exit = false;
        while (!exit) {

            String command = getCommandFromUser(commandAsker);

            if (Objects.equals(command, "") || Objects.equals(command, "exit")) {
                exit = true;
            }
            if (command.startsWith("scalc")) {
                String[] splitResult = command.split("'");
                int result = 0;
                try {
                    result = stringCalculator.add(splitResult[1]);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                System.out.println("Result is: " + result);
            }
        }
    }

    public static String getCommandFromUser(CommandAsker asker) {
        return asker.ask("Enter a command: ");
    }
}
