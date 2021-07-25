package com.jarida.server.jaridaserver.tutorialApi.controller;

import com.jarida.server.jaridaserver.exception.ResourceInternalServerException;
import com.jarida.server.jaridaserver.exception.ResourceNoContentFoundException;
import com.jarida.server.jaridaserver.exception.ResourceNotFoundException;
import com.jarida.server.jaridaserver.tutorialApi.model.Tutorial;
import com.jarida.server.jaridaserver.tutorialApi.repository.TutorialRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v2")
@Validated
@Api(tags = "Tutorial API v2")
@SwaggerDefinition(tags = {
        @Tag(name = "Tutorial", description = "This is for getting  Tutorial Api")
})
public class TutorialController {

    @Autowired
    TutorialRepository tutorialRepository;

    @GetMapping("/tutorials")
    public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {
        try {
            List<Tutorial> tutorials = new ArrayList<>();

            if (title == null)
                tutorials.addAll(tutorialRepository.findAll());
            else
                tutorials.addAll(tutorialRepository.findByTitleContaining(title));

            if (tutorials.isEmpty()) {
                throw new ResourceNotFoundException("No Content Found");
                // return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceNotFoundException("No Contents Found");
            // return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
        Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

        if (tutorialData.isPresent()) {
            return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Not Found");
            // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/tutorials")
    public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
        try {
            Tutorial _tutorial = tutorialRepository
                    .save(new Tutorial(
                            tutorial.getTitle(),
                            tutorial.getDescription(),
                            false));
            return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResourceInternalServerException("Server Error");
            // return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
        Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

        if (tutorialData.isPresent()) {
            Tutorial _tutorial = tutorialData.get();
            _tutorial.setTitle(tutorial.getTitle());
            _tutorial.setDescription(tutorial.getDescription());
            _tutorial.setPublished(tutorial.isPublished());
            return new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Not Found");
           // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/tutorials/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        try {
            tutorialRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Not Found");
        }
    }

    @DeleteMapping("/tutorials")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        try {
            tutorialRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Not Found");
        }

    }

    @GetMapping("/tutorials/published")
    public ResponseEntity<List<Tutorial>> findByPublished() {
        try {
            List<Tutorial> tutorials = tutorialRepository.findByPublished(true);

            if (tutorials.isEmpty()) {
                throw new ResourceNoContentFoundException("Not Found");
            }
            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
