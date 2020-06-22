package com.jarida.server.jaridaserver.tutor_one_2_many.controller;

import com.jarida.server.jaridaserver.jarida.exception.ErrorDetails;
import com.jarida.server.jaridaserver.jarida.model.Jarida;
import com.jarida.server.jaridaserver.tutor_one_2_many.exception.ResourceNotFoundException;
import com.jarida.server.jaridaserver.tutor_one_2_many.model.Course;
import com.jarida.server.jaridaserver.tutor_one_2_many.model.CourseList;
import com.jarida.server.jaridaserver.tutor_one_2_many.model.InstructorList;
import com.jarida.server.jaridaserver.tutor_one_2_many.repository.CourseRepository;
import com.jarida.server.jaridaserver.tutor_one_2_many.repository.InstructorRepository;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<CourseList> getCoursesByInstructor(@PathVariable(value = "instructorId") Long instructorId) throws ResourceNotFoundException {
        List<Course> courses = new ArrayList<>(courseRepository.findByInstructorId(instructorId));

        if(courses.isEmpty()) {
            throw new ResourceNotFoundException("No Courses Found");
        }
            //return courseRepository.findByInstructorId(instructorId);
        CourseList courseList = new CourseList(courses);
        return new ResponseEntity<CourseList>(courseList, HttpStatus.OK);


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
    public Map<String, String> removeCountry(@PathVariable Long instructorId, @PathVariable Long courseId) throws ResourceNotFoundException {
        if(!instructorRepository.existsById(instructorId)){
            throw new ResourceNotFoundException("instructorId not found");
        }

        this.courseRepository.deleteById(courseId);
        Map<String,String> response = new HashMap<>();
        response.put("timestamp", new SimpleDateFormat("dd, MMMM, yyyy - hh:mm aa").format(Calendar.getInstance().getTime()));
        response.put("message","Deleted Successfully");
        return response;
    }
}
