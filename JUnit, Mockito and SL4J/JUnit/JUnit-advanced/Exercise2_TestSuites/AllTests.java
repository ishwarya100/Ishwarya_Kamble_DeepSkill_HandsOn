import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

// Groups multiple test classes into a single suite
@Suite
@SelectClasses({MathTests.class, StringTests.class})
public class AllTests {
}
