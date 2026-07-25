import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// Repository with a custom query method
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByName(String name);
}
