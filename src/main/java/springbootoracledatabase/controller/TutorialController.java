package springbootoracledatabase.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootoracledatabase.entity.Tutorial;
import springbootoracledatabase.repository.TutorialRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TutorialController {
    private static final Logger logger = LoggerFactory.getLogger(TutorialController.class);

    @Autowired
    TutorialRepository tutorialRepository;


    @GetMapping(value = "/tutorials", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {
        try {
            logger.info("Getting all tutorials...");
            List<Tutorial> tutorials = new ArrayList<>();

            if (title == null)
                tutorialRepository.findAll().forEach(tutorials::add);
            else
                tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);

            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            logger.info("Retrieved {} tutorials.", tutorials.size());
            logger.info("Retrieved {} tutorials.", tutorials.size());

            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error occurred while fetching tutorials.", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/tutorials/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id, Tutorial tutorial) {
        try {
            Optional<Tutorial> tutorialData = tutorialRepository.findById(id);
            logger.info("Getting tutorial by ID: {}", id);

            if (tutorialData.isPresent()) {
                logger.info("Found tutorial with ID: {}", id);
                return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
            } else {
                logger.info("Tutorial with ID {} not found.", id);
                logger.info("Tutorial with ID {} not found.", id);
                logger.info("Tutorial with ID {} not found.", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            logger.error("Error occurred while fetching tutorial with ID: {}", id, e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping(value = "/tutorials", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
        try {
            logger.info("Creating a new tutorial: {}", tutorial.getTitle());

            Tutorial _tutorial = tutorialRepository
                    .save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), false, new Date(), tutorial.getUsername(), tutorial.getPassword(), tutorial.getRoles()));

            logger.info("Tutorial created with ID: {}", _tutorial.getId());

            return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error occurred while creating tutorial: {}", tutorial.getTitle(), e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/tutorials/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
        try {
            logger.info("Updating tutorial with ID: {}", id);

            Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

            if (tutorialData.isPresent()) {
                Tutorial _tutorial = tutorialData.get();
                _tutorial.setTitle(tutorial.getTitle());
                _tutorial.setDescription(tutorial.getDescription());
                _tutorial.setPublished(tutorial.isPublished());

                Tutorial updatedTutorial = tutorialRepository.save(_tutorial);

                logger.info("Tutorial with ID {} updated", id);
                return new ResponseEntity<>(updatedTutorial, HttpStatus.OK);
            } else {
                logger.info("Tutorial with ID {} not found.", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Error occurred while updating tutorial with ID: {}", id, e);

        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @DeleteMapping(value = "/tutorials/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        try {
            logger.info("Deleting tutorial with ID: {}", id);
            tutorialRepository.deleteById(id);
            logger.info("Tutorial with ID {} deleted.", id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.error("Error occurred while deleting tutorial with ID: {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/tutorials", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        try {
            logger.info("Deleting all tutorials");
            tutorialRepository.deleteAll();
            logger.info("All tutorials deleted.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.error("Error occurred while deleting all tutorials", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/tutorials/published", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Tutorial>> findByPublished() {
        try {
            logger.info("Finding tutorials by published status");

            List<Tutorial> tutorials = tutorialRepository.findByPublished(true);

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






