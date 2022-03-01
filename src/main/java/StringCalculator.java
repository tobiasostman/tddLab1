import java.util.Arrays;

public class StringCalculator {
    public int add(String numbers) {

        if(!numbers.isEmpty()){

            String delimiterArr[] = getDelimiterAndNewString(numbers);
            String delimiter = delimiterArr[0];
            String strNums = delimiterArr[1];
            return Arrays.stream(strNums.replace("\n", delimiter).split(delimiter))
                    .map(number -> Integer.parseInt(number)).reduce(Integer::sum).orElse(0);
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
}
