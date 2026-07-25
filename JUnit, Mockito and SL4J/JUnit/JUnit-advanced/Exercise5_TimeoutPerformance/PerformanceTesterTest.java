import org.junit.jupiter.api.Test;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertTimeout;

// Tests that performTask completes within the specified time limit
public class PerformanceTesterTest {

    private final PerformanceTester performanceTester = new PerformanceTester();

    @Test
    public void testPerformTask_completesWithinTimeLimit() {
        assertTimeout(Duration.ofMillis(500), performanceTester::performTask);
    }
}
