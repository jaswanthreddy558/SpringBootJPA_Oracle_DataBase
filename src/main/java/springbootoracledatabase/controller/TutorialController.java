package springbootoracledatabase.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootoracledatabase.entity.BookStore;
import springbootoracledatabase.exception.ResourceNotFoundException;
import springbootoracledatabase.service.TutorialService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/oracle/database")
public class TutorialController {

    private static final Logger logger = LoggerFactory.getLogger(TutorialController.class);

    @Autowired
    private TutorialService tutorialService;

    @GetMapping("/api/BookStores")
    public ResponseEntity<List<BookStore>> getAllTutorials(@RequestParam(required = false) String title) throws ResourceNotFoundException {
        logger.info("Request received to get all tutorials.");
        return tutorialService.getAllTutorials(title);
    }


    @PostMapping("/api/tutorials")
    public ResponseEntity<BookStore> createTutorial(@RequestBody BookStore tutorial) {
        logger.info("Request received to create a new tutorial.");
        return tutorialService.createTutorial(tutorial);
    }

    @PutMapping("/api/tutorials/{id}")
    public ResponseEntity<BookStore> updateTutorial(@PathVariable("id") long id, @RequestBody BookStore tutorial) {
        logger.info("Request received to update tutorial with ID: {}", id);
        return tutorialService.updateBookStore(id, tutorial);
    }

    @DeleteMapping("/api/tutorials/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        logger.info("Request received to delete tutorial with ID: {}", id);
        return tutorialService.deleteTutorial(id);
    }

    @DeleteMapping("/api/tutorials")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        logger.info("Request received to delete all tutorials.");
        return tutorialService.deleteAllTutorials();
    }

    @GetMapping("/api/tutorials/published")
    public ResponseEntity<List<BookStore>> findByPublished() {
        logger.info("Request received to find tutorials by published status.");
        return tutorialService.findByPublished();
    }

    @GetMapping("/getIpAddress")
    public String getClientIpAddress(HttpServletRequest request) {
        String ipAddress = request.getRemoteAddr();
        return "Client IP Address: " + ipAddress;
    }

    @GetMapping("/newString")
    public String sampleString(HttpServletRequest request) {
        return "Hello VHS APP";
    }
}