import org.springframework.data.jpa.repository.JpaRepository;

// Repository for User entity
public interface UserRepository extends JpaRepository<User, Long> {
}
