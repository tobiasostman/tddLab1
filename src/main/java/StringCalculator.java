import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator {

    private String errorMessage = "Negative values not allowed: ";

    public int add(String numbers) throws Exception {

        if(!numbers.isEmpty()){
            String[] delimiterArr = getDelimiterAndNewString(numbers);
            String delimiter = delimiterArr[0];
            String strNums = delimiterArr[1];

            List<Integer> nums = Arrays.stream(strNums.replace("\n", delimiter).split(delimiter))
                    .map(Integer::parseInt).collect(Collectors.toList());

            containsNegativeValues(nums);

            return nums.stream().reduce(Integer::sum).orElse(0);
        }
        return 0;
    }

    private String[] getDelimiterAndNewString(String numbers) {
        if (numbers.startsWith("//")) {
            String delimiter[] = numbers.split("\n",2);
            delimiter[0] = delimiter[0].replace("//", "");
            return delimiter;
        }
        return new String[]{",", numbers};
    }

    private void containsNegativeValues(List<Integer> nums) throws Exception {
        List<Integer> negatives = nums.stream().filter(i -> i < 0).collect(Collectors.toList());
        if(!negatives.isEmpty()){
            errorMessage += negatives.toString();
            throw new Exception(errorMessage);
        }
    }
}
