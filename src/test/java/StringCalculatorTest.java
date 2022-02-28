import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringCalculatorTest {

    StringCalculator stringCalculator = new StringCalculator();

    @Test
    @DisplayName("Empty string should return zero")
    public void emptyStringShouldReturnZero() {
        assertEquals(0, stringCalculator.add(""));
    }

    @Test
    @DisplayName("One plus nothing should return one")
    public void OneShouldReturnOne() {
        assertEquals(1, stringCalculator.add("1"));
    }

    @Test
    @DisplayName("Two plus one should return 3")
    public void TwoPlusOneShouldReturnThree() {
        assertEquals(3, stringCalculator.add("2,1"));
    }

    @Test
    @DisplayName("Add should be able to handle x amount of values")
    public void shouldBeAbleToAddXAmountOfValues() {
        StringBuilder strToAdd = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            strToAdd.append(i).append(",");
        }
        assertEquals(4950, stringCalculator.add(strToAdd.toString()));
    }
}
