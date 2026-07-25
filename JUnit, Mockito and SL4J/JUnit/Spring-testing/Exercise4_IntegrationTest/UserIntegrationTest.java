import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import static org.junit.jupiter.api.Assertions.assertEquals;

// Full integration test covering controller, service, repository and database
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testGetUser_fullFlow() {
        // Arrange, save a user directly through the repository
        User user = new User();
        user.setId(1L);
        user.setName("John");
        userRepository.save(user);

        // Act, call the real endpoint
        User response = restTemplate.getForObject(
                "http://localhost:" + port + "/users/1", User.class);

        // Assert
        assertEquals("John", response.getName());
    }
}
