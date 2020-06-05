package com.jarida.server.jaridaserver.tutor_one_2_many.controller;

import com.jarida.server.jaridaserver.jarida.model.Jarida;
import com.jarida.server.jaridaserver.tutor_one_2_many.exception.ResourceNotFoundException;
import com.jarida.server.jaridaserver.tutor_one_2_many.model.Instructor;
import com.jarida.server.jaridaserver.tutor_one_2_many.model.InstructorList;
import com.jarida.server.jaridaserver.tutor_one_2_many.repository.InstructorRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class InstructorController {

    @Autowired
    private InstructorRepository instructorRepository;


    @GetMapping("/instructors")
    public ResponseEntity<InstructorList> getInstructors() throws ResourceNotFoundException {
        List<Instructor> instructors = new ArrayList<>(instructorRepository.findAll());

        if(instructors.isEmpty()){
            throw new ResourceNotFoundException("No Instructors Found");
        }
        InstructorList instructorList = new InstructorList(instructors);
        return new ResponseEntity<InstructorList>(instructorList, HttpStatus.OK);
       //return instructorRepository.findAll();
    }

    @GetMapping("/instructors/{id}")
    public ResponseEntity<Instructor> getInstructorById(
            @PathVariable(value = "id") Long instructorId) throws ResourceNotFoundException{

        Instructor user = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found :: " + instructorId ));
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/instructors")
    public Instructor createUser(@Valid @RequestBody Instructor instructor){
        return instructorRepository.save(instructor);
    }

    @PutMapping("/instructors/{id}")
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
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long instructorId) throws ResourceNotFoundException{

        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found :: " + instructorId));

        instructorRepository.delete(instructor);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
