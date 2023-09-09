package springbootoracledatabase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springbootoracledatabase.entity.Tutorial;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<Tutorial,String> {
    Optional<Tutorial> findByUsername(String username);

}
