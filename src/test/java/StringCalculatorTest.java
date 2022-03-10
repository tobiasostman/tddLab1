import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class StringCalculatorTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    StringCalculator stringCalculator;
    ILogger mockLogger;

    @BeforeEach
    void init() {
        mockLogger = mock(LoggerStub.class);
        stringCalculator = new StringCalculator(mockLogger);
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void destroy() {
        System.setOut(originalOut);
    }


    @Test
    @DisplayName("Empty string should return zero")
    public void emptyStringShouldReturnZero() throws Exception {
        assertEquals(0, stringCalculator.add(""));
    }

    @Test
    @DisplayName("One plus nothing should return one")
    public void OneShouldReturnOne() throws Exception {
        assertEquals(1, stringCalculator.add("1"));
    }

    @Test
    @DisplayName("Two plus one should return 3")
    public void TwoPlusOneShouldReturnThree() throws Exception {
        assertEquals(3, stringCalculator.add("2,1"));
    }

    @Test
    @DisplayName("Add should be able to handle x amount of values")
    public void shouldBeAbleToAddXAmountOfValues() throws Exception {
        StringBuilder strToAdd = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            strToAdd.append(i).append(",");
        }
        assertEquals(4950, stringCalculator.add(strToAdd.toString()));
    }

    @Test
    @DisplayName("Should be able to handle new line character")
    public void shouldBeAbleToHandleNewLineCharacter() throws Exception {
        assertEquals(3, stringCalculator.add("1\n1,1"));
    }

    @Test
    @DisplayName("Should support delimiter string")
    public void shouldSupportDelimiterString() throws Exception {
        assertEquals(4, stringCalculator.add("//;\n1;1;2"));
    }

    @Test
    @DisplayName("Should throw negative values not allowed")
    public void shouldThrowNegativeValuesNotAllowed() {
        Exception exception = assertThrows(Exception.class, () -> {
            stringCalculator.add("1,-2,3,-4");
        });
        assertEquals("Negative values not allowed: [-2, -4]", exception.getMessage());
    }

    @Test
    @DisplayName("Should log numbers bigger then 1000")
    public void shouldLogNumbersBiggerThen1000() throws Exception {
        stringCalculator.add("1000,10,1000");
        verify(mockLogger, times(2)).log(1000);
    }

    @Test
    @DisplayName("Should greet user")
    public void shouldGreetUser() {
        String[] args = new String[0];
        StringCalculator.main(args);

        String expectedOutPut = "Welcome to string calculator 6000\r\n" + "To calculate a string type \"scalc '1,2,3'\"\r\n" + "To exit type: \"exit\"\r\n";

        assertEquals(expectedOutPut, outContent.toString());
    }
}
