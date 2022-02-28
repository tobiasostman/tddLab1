import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class StringCalculatorTest {

    StringCalculator stringCalculator = new StringCalculator();

    @Test
    @DisplayName("Empty string should return zero")
    public void emptyStringShouldReturnZero() {
        int result = stringCalculator.add("");
        assertEquals(0, result);
    }

    @Test
    @DisplayName("One plus nothing should return one")
    public void OneShouldReturnOne(){
        int result = stringCalculator.add("1");
        assertEquals(1,result);
    }

    @Test
    @DisplayName("Two plus one should return 3")
    public void TwoPlusOneShouldReturnThree() {
        int result = stringCalculator.add("2,1");
        assertEquals(3,result);
    }

}
