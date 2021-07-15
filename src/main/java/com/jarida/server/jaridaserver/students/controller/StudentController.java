package com.jarida.server.jaridaserver.students.controller;


import com.jarida.server.jaridaserver.jarida.exception.ResourceNotFoundException;
import com.jarida.server.jaridaserver.students.model.Student;
import com.jarida.server.jaridaserver.students.service.StudentService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api/v2")
@Validated
@Api(tags = "Students API v2")
@SwaggerDefinition(tags = {
        @Tag(name = "Students", description = "This is for getting  Students")
})
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "This is for getting all the list of Students", response = Student.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 401, message = "Unauthorized: token has expired or is not valid"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Internal server error")
            //@ApiResponse(code = 500, message = "Failure", response = ErrorResource.class)
    }
    )
    public List<Student> getStudents() {
        return studentService.getStudent();
    }

    @GetMapping("/students/{studentId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "This is for getting a single Student")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 401, message = "Unauthorized: token has expired or is not valid"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Internal server error")
            //@ApiResponse(code = 500, message = "Failure", response = ErrorResource.class)
    }
    )
    public ResponseEntity<Student> getStudentById(@PathVariable(value = "studentId") Long studentId) {
        studentService.getStudentById(studentId);
        Optional<Student> studentOptional = Optional.ofNullable(studentService.getStudentById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Student with id :: " + studentId + " does not exists"
                )));
        return ResponseEntity.of(studentOptional);
    }

    @PostMapping("/students")
    @ApiOperation(value = "This is for posting a Student")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 401, message = "Unauthorized: token has expired or is not valid"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    }
    )
    public ResponseEntity<Student> registerNewStudent(@Valid @RequestBody Student student) {
        studentService.addNewStudent(student);
        return ResponseEntity.ok().body(student);
    }

    @DeleteMapping("/students/{studentId}")
    @ApiOperation(value = "This is for deleting a Student", response = Student.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 401, message = "Unauthorized: token has expired or is not valid"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Internal server error")
            //@ApiResponse(code = 500, message = "Failure", response = ErrorResource.class)
    }
    )
    public Map<String, String> deleteStudent(@PathVariable(value = "studentId") Long studentId) {
        studentService.deleteStudent(studentId);
        Map<String,String> response = new HashMap<>();
        response.put("timestamp", new SimpleDateFormat("dd, MMMM, yyyy - hh:mm aa").format(Calendar.getInstance().getTime()));
        response.put("message","Deleted Successfully");
        return response;
    }

    @PutMapping("/students/{studentId}")
    @ApiOperation(value = "This is for the updating a Student", response = Student.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 401, message = "Unauthorized: token has expired or is not valid"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Internal server error")
            //@ApiResponse(code = 500, message = "Failure", response = ErrorResource.class)
    }
    )
    public ResponseEntity<Map<String, String>> updateStudent(
            @PathVariable(value = "studentId") @Valid Long studentId,
            @RequestParam(required = false) @Valid String name,
            @RequestParam(required = false) @Valid  String email) {

          studentService.updateStudent(studentId, name, email);

        Map<String,String> response = new HashMap<>();
        response.put("id",studentId.toString());
        response.put("name",name);
        response.put("email", email);

        return ResponseEntity.ok().body(response);
    }
}
