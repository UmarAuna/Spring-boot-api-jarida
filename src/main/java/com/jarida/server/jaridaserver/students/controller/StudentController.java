package com.jarida.server.jaridaserver.students.controller;


import com.jarida.server.jaridaserver.students.model.Student;
import com.jarida.server.jaridaserver.students.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Validated
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    @ResponseStatus(HttpStatus.OK)
    public List<Student> getStudents() {
        return studentService.getStudent();
    }

    @PostMapping("/students")
    public void registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @DeleteMapping("/students/{studentId}")
    public void deleteStudent(@PathVariable(value = "studentId") Long studentId) {
        studentService.deleteStudent(studentId);
    }

    @PutMapping("/students/{studentId}")
    public void updateStudent(
            @PathVariable(value = "studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        studentService.updateStudent(studentId, name, email);
    }
}
