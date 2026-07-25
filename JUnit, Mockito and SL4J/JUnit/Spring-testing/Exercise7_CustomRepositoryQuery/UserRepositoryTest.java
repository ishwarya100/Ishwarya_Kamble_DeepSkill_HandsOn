import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

// Tests the custom findByName query using an in memory test database
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByName() {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setName("John");
        userRepository.save(user);

        // Act
        List<User> result = userRepository.findByName("John");

        // Assert
        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getName());
    }
}
