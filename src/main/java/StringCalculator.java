import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator {

    private final ILogger logger;
    private String errorMessage = "Negative values not allowed: ";

    public StringCalculator(ILogger logger) {
        this.logger = logger;
    }

    public static void main(String[] args) throws Exception {
        StringCalculator stringCalculator = new StringCalculator(new LoggerStub());

        stringCalculator.add("//[***][%%%]\n1***2%%%2");
    }

    public int add(String numbers) throws Exception {

        if (!numbers.isEmpty()) {
            String[] delimiterArr = getDelimiterAndNewString(numbers);
            String delimiter = delimiterArr[0];
            String strNums = delimiterArr[1];

            List<Integer> nums = Arrays.stream(strNums.replace("\n", delimiter).split(delimiter))
                    .map(Integer::parseInt).collect(Collectors.toList());

            containsNegativeValues(nums);
            logNumbersGreaterThan1000(nums);

            return nums.stream().reduce(Integer::sum).orElse(0);
        }
        return 0;
    }

    private String[] getDelimiterAndNewString(String numbers) {
        if (!numbers.startsWith("//")) return new String[]{",", numbers};

        String[] delimiter = numbers.split("\n", 2);
        delimiter[0] = delimiter[0].replace("//", "");
        String[] delimiters = delimiter[0].split("]");
        delimiter[0] = ",";
        for (String delim : delimiters) {
            String target = delim.replace("[", "");
            delimiter[1] = delimiter[1].replace(target, ",");
        }
        return delimiter;
    }

    private void containsNegativeValues(List<Integer> nums) throws Exception {
        List<Integer> negatives = nums.stream().filter(i -> i < 0).collect(Collectors.toList());
        if (!negatives.isEmpty()) {
            errorMessage += negatives.toString();
            throw new Exception(errorMessage);
        }
    }

    private void logNumbersGreaterThan1000(List<Integer> nums) {
        nums.forEach(num -> {
            if (num >= 1000) {
                logger.log(num);
            }
        });
    }
}
