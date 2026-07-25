import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

// Sample test class to be grouped into the suite
public class StringTests {

    @Test
    public void testConcatenation() {
        assertEquals("HelloWorld", "Hello" + "World");
    }
}
