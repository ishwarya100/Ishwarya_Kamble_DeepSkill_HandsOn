import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

// Controls the order in which test methods are executed
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderedTests {

    @Test
    @Order(1)
    public void firstTest() {
        System.out.println("Running first test");
    }

    @Test
    @Order(2)
    public void secondTest() {
        System.out.println("Running second test");
    }

    @Test
    @Order(3)
    public void thirdTest() {
        System.out.println("Running third test");
    }
}
