import java.util.Arrays;

public class StringCalculator {
    public int add(String numbers) {
        if (!numbers.isEmpty()) {
            return Arrays.stream(numbers.split(","))
                    .map(number -> Integer.parseInt(number)).reduce(Integer::sum).orElse(0);
        }
        return 0;
    }
}
