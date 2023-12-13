package springbootoracledatabase.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import springbootoracledatabase.entity.BookStore;
import springbootoracledatabase.repository.BookRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TutorialService {

    private final BookRepository bookRepository;
    private static final Logger logger = LoggerFactory.getLogger(TutorialService.class);

    @Autowired
    public TutorialService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public ResponseEntity<List<BookStore>> getAllTutorials(String title) {
        try {
            logger.info("Fetching all tutorials...");

            List<BookStore> bookStores = title == null ? bookRepository.findAll() : bookRepository.findByTitleContaining(title);

            if (bookStores.isEmpty()) {
                logger.info("No tutorials found.");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            logger.info("Retrieved {} tutorials.", bookStores.size());
            return new ResponseEntity<>(bookStores, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error occurred while fetching tutorials.", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<BookStore> getBookStoreById(long id) {
        try {
            logger.info("Fetching BookStore by ID: {}", id);

            Optional<BookStore> tutorialData = bookRepository.findById(id);

            return tutorialData.map(BookStore -> {
                logger.info("Found BookStore with ID: {}", id);
                return new ResponseEntity<>(BookStore, HttpStatus.OK);
            }).orElseGet(() -> {
                logger.info("BookStore with ID {} not found.", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            });

        } catch (Exception e) {
            logger.error("Error occurred while fetching BookStore with ID: {}", id, e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<BookStore> createTutorial(BookStore BookStore) {
        try {
            logger.info("Creating a new BookStore: {}", BookStore.getTitle());

            BookStore _BookStore = bookRepository.save(new BookStore(BookStore.getTitle(), BookStore.getDescription(), false, LocalDate.now(), BookStore.getUsername(), BookStore.getPassword(), BookStore.getRoles()));

            logger.info("BookStorecreated with ID: {}", _BookStore.getId());
            return new ResponseEntity<>(_BookStore, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error occurred while creating BookStore: {}", BookStore.getTitle(), e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<BookStore> updateBookStore(long id, BookStore updatedBookStore) {
        try {
            logger.info("Updating BookStore with ID: {}", id);

            Optional<BookStore> optionalBookStore = bookRepository.findById(id);

            if (optionalBookStore.isPresent()) {
                BookStore existingBookStore = optionalBookStore.get();

                existingBookStore.setTitle(updatedBookStore.getTitle());
                existingBookStore.setDescription(updatedBookStore.getDescription());
                existingBookStore.setPublished(updatedBookStore.isPublished());

                BookStore savedBookStore = bookRepository.save(existingBookStore);

                logger.info("BookStore with ID {} updated", id);
                return new ResponseEntity<>(savedBookStore, HttpStatus.OK);
            } else {
                logger.info("BookStore with ID {} not found.", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            logger.error("Error occurred while updating BookStore with ID: {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<HttpStatus> deleteTutorial(long id) {
        try {
            logger.info("Deleting BookStorewith ID: {}", id);

            bookRepository.deleteById(id);
            logger.info("BookStorewith ID {} deleted.", id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.error("Error occurred while deleting BookStorewith ID: {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        try {
            logger.info("Deleting all tutorials");

            bookRepository.deleteAll();
            logger.info("All tutorials deleted.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.error("Error occurred while deleting all tutorials", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<BookStore>> findByPublished() {
        try {
            logger.info("Finding tutorials by published status");

            List<BookStore> tutorials = bookRepository.findByPublished(true);

            if (tutorials.isEmpty()) {
                logger.info("No published tutorials found.");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            logger.info("Retrieved {} published tutorials.", tutorials.size());
            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error occurred while finding published tutorials.", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
