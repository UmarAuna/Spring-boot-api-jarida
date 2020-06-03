package com.jarida.server.jaridaserver.tutor_one_2_many.controller;

import com.jarida.server.jaridaserver.jarida.exception.ErrorDetails;
import com.jarida.server.jaridaserver.jarida.model.Jarida;
import com.jarida.server.jaridaserver.tutor_one_2_many.exception.ResourceNotFoundException;
import com.jarida.server.jaridaserver.tutor_one_2_many.model.Course;
import com.jarida.server.jaridaserver.tutor_one_2_many.repository.CourseRepository;
import com.jarida.server.jaridaserver.tutor_one_2_many.repository.InstructorRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @GetMapping("/instructors/{instructorId}/courses")
    public List<Course> getCoursesByInstructor(@PathVariable(value = "instructorId") Long instructorId) throws ResourceNotFoundException {
       return courseRepository.findByInstructorId(instructorId);
    }

    @GetMapping("/instructors/{instructorId}/courses/{courseId}")
    public ResponseEntity<Course> getCoursesByInstructorId(@PathVariable(value = "instructorId") Long instructorId,
                                                           @PathVariable(value = "courseId") Long coursesId) throws ResourceNotFoundException {
        if(!instructorRepository.existsById(instructorId)){
            throw new ResourceNotFoundException("instructorId not found");
        }

        Course course = courseRepository.findById(coursesId).orElseThrow(() -> new ResourceNotFoundException("Course not found for this id :: " + coursesId ));
        //return courseRepository.findByInstructorId(coursesId);
        return ResponseEntity.ok().body(course);
    }

    @PostMapping("/instructors/{instructorId}/courses")
    public Course createCourse(@PathVariable(value = "instructorId") Long instructorId,
                               @Valid @RequestBody Course course) throws ResourceNotFoundException{

        return instructorRepository.findById(instructorId).map(instructor -> {
            course.setInstructor(instructor);
            return  courseRepository.save(course);
        }).orElseThrow( () -> new ResourceNotFoundException("instructor not found"));
    }

    @PutMapping("/instructors/{instructorId}/courses/{courseId}")
    public Course updateCourse(@PathVariable(value = "instructorId") Long instructorId,
                               @PathVariable(value = "courseId") Long courseId, @Valid @RequestBody Course courseRequest)
        throws ResourceNotFoundException{

        if(!instructorRepository.existsById(instructorId)){
            throw new ResourceNotFoundException("instructorId not found");
        }

        return courseRepository.findById(courseId).map(course -> {
            course.setTitle(courseRequest.getTitle());
            return courseRepository.save(course);
        }).orElseThrow(() -> new ResourceNotFoundException("course id not found"));
    }


    @DeleteMapping("/instructors/{instructorId}/courses/{courseId}")
    public Map<String, Boolean> removeCountry(@PathVariable Long instructorId, @PathVariable Long courseId) throws ResourceNotFoundException {
        if(!instructorRepository.existsById(instructorId)){
            throw new ResourceNotFoundException("instructorId not found");
        }

        this.courseRepository.deleteById(courseId);
        Map<String,Boolean> response = new HashMap<>();
        response.put("Deleted",Boolean.TRUE );
        return response;
    }
}
