package com.jarida.server.jaridaserver.tutor_one_2_many.controller;

import com.jarida.server.jaridaserver.tutor_one_2_many.exception.ResourceNotFoundException;
import com.jarida.server.jaridaserver.tutor_one_2_many.model.Instructor;
import com.jarida.server.jaridaserver.tutor_one_2_many.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class InstructorController {

    @Autowired
    private InstructorRepository instructorRepository;


    @GetMapping("/instructors")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Instructor>> getInstructors() throws ResourceNotFoundException {
        List<Instructor> instructors = new ArrayList<>(instructorRepository.findAll());

        if(instructors.isEmpty()){
            throw new ResourceNotFoundException("No Instructors Found");
        }
        //InstructorList instructorList = new InstructorList(instructors);
        return new ResponseEntity(instructors, HttpStatus.OK);
       //return instructorRepository.findAll();
    }

    @GetMapping("/instructors/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Instructor> getInstructorById(
            @PathVariable(value = "id") Long instructorId) throws ResourceNotFoundException{

        Instructor user = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found :: " + instructorId ));
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/instructors")
    @ResponseStatus(HttpStatus.CREATED)
    public Instructor createUser(@Valid @RequestBody Instructor instructor){

        return instructorRepository.save(instructor);
    }

    @PutMapping("/instructors/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Instructor> updateUser(
            @PathVariable(value = "id")Long instructorId,
            @Valid @RequestBody Instructor userDetails) throws ResourceNotFoundException{

        Instructor user = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found :: " + instructorId));

        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());

        final Instructor updateUser = instructorRepository.save(user);
        return ResponseEntity.ok(updateUser);
    }

    @DeleteMapping("/instructors/{id}")
    public Map<String, String> deleteUser(@PathVariable(value = "id") Long instructorId) throws ResourceNotFoundException{

        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found :: " + instructorId));

        instructorRepository.delete(instructor);
        Map<String,String> response = new HashMap<>();
        response.put("timestamp", new SimpleDateFormat("dd, MMMM, yyyy - hh:mm aa").format(Calendar.getInstance().getTime()));
        response.put("message","Deleted Successfully");
        return response;
    }

}
