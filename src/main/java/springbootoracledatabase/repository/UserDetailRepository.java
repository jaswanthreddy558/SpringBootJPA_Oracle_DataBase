package springbootoracledatabase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springbootoracledatabase.model.User;

import java.util.Optional;

public interface UserDetailRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String name);
}
