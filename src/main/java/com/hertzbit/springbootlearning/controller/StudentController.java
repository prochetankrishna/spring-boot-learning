package com.hertzbit.springbootlearning.controller;

import com.hertzbit.springbootlearning.model.Student;
import com.hertzbit.springbootlearning.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<?> getAllStudents() {
        List<Student> studentList = this.studentService.getAllStudents();
        for (Student student : studentList) {
            String studentId = student.getStudentId();
            Link selfLink = linkTo(StudentController.class).slash(studentId).withSelfRel();
            student.add(selfLink);
        }
        Link link = linkTo(StudentController.class).withSelfRel();
        CollectionModel<Student> result = CollectionModel.of(studentList, link);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<?> getStudentById(@PathVariable ("studentId") String studentId) {
        Student student = this.studentService.getStudentById(studentId);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createNewStudent(@RequestBody Student studentRequest) {
        Student createdStudent = this.studentService.createNewStudent(studentRequest);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<?> updateStudent(@PathVariable ("studentId") String studentId,
                                           @RequestBody Student studentRequest) {
        Student updatedStudent = this.studentService.updateStudent(studentId, studentRequest);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<?> deleteStudentById(@PathVariable ("studentId") String studentId) {
        this.studentService.deleteStudentById(studentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
