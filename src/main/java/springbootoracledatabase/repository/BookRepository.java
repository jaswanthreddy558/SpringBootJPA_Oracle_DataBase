package springbootoracledatabase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springbootoracledatabase.entity.BookStore;

import java.util.List;
import java.util.Optional;
@Repository
public interface BookRepository extends JpaRepository<BookStore, Long> {
    List<BookStore> findByPublished(boolean published);
    List<BookStore> findByTitleContaining(String title);

    Optional<BookStore> findByUsername(String username);

}
