import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

// Demonstrates the Arrange-Act-Assert pattern with setup and teardown

public class CalculatorAAATest {

    private Calculator calculator;

    // Runs before every test method
    @Before
    public void setUp() {
        calculator = new Calculator();
    }

    // Runs after every test method
    @After
    public void tearDown() {
        calculator = null;
    }

    @Test
    public void testAdd() {
        // Arrange
        int a = 4;
        int b = 6;

        // Act
        int result = calculator.add(a, b);

        // Assert
        assertEquals(10, result);
    }

    @Test
    public void testSubtract() {
        // Arrange
        int a = 10;
        int b = 4;

        // Act
        int result = calculator.subtract(a, b);

        // Assert
        assertEquals(6, result);
    }
}
