package com.hertzbit.springbootlearning.service;

import com.hertzbit.springbootlearning.exceptions.StudentBadRequestException;
import com.hertzbit.springbootlearning.exceptions.StudentNotFoundException;
import com.hertzbit.springbootlearning.model.Student;
import com.hertzbit.springbootlearning.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StudentService {

    private HashMap<String, Student> studentLocalDB;
    private AtomicInteger studentCounter;

    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
        this.studentLocalDB = new HashMap<>();
        this.studentCounter = new AtomicInteger();

        Student studentOne = new Student();
        //studentOne.setStudentId(String.valueOf(studentCounter.incrementAndGet()));
        studentOne.setStudentFirstName("Chetan");
        studentOne.setStudentLastName("Krishna");

        Student studentTwo = new Student();
        //studentTwo.setStudentId(String.valueOf(studentCounter.incrementAndGet()));
        studentTwo.setStudentFirstName("John");
        studentTwo.setStudentLastName("Doe");

        //studentLocalDB.put(studentOne.getStudentId(), studentOne);
        //studentLocalDB.put(studentTwo.getStudentId(), studentTwo);

        this.studentRepository.save(studentOne);
        this.studentRepository.save(studentTwo);
    }

    public List<Student> getAllStudents() {
        Iterable<Student> studentIterable = this.studentRepository.findAll();
        List<Student> studentList = new ArrayList<>();
        for (Student eachStudent : studentIterable) {
            studentList.add(eachStudent);
        }
        return studentList;
    }

    public Student getStudentById(String studentId) {
        Optional<Student> result = this.studentRepository.findById(studentId);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new StudentNotFoundException("Student with studentId : " + studentId +
                    " doesn't exist.");
        }
    }

    public Student createNewStudent(Student studentRequest) {
        if (studentRequest.getStudentFirstName() != null && studentRequest.getStudentLastName() != null) {
            if (!studentRequest.getStudentFirstName().isEmpty()) {
                if (!studentRequest.getStudentLastName().isEmpty()) {
                    studentRequest.setStudentId(String.valueOf(studentCounter.incrementAndGet()));
                    this.studentLocalDB.put(studentRequest.getStudentId(), studentRequest);
                    return studentRequest;
                } else {
                    throw new StudentBadRequestException("Last Name cannot be empty string.");
                }
            } else {
                throw new StudentBadRequestException("First Name cannot be empty string.");
            }
        }
        throw new StudentBadRequestException("First Name and Last Name are mandatory.");
    }

    public Student updateStudent(String studentId, Student studentRequest) {

        if (this.studentLocalDB.containsKey(studentId)) {
            if (studentRequest.getStudentFirstName() != null && studentRequest.getStudentLastName() != null) {
                if (!studentRequest.getStudentFirstName().isEmpty()) {
                    if (!studentRequest.getStudentLastName().isEmpty()) {
                        studentRequest.setStudentId(studentId);
                        this.studentLocalDB.put(studentId, studentRequest);
                        return studentRequest;
                    } else {
                        throw new StudentBadRequestException("Last Name cannot be empty string.");
                    }
                } else {
                    throw new StudentBadRequestException("First Name cannot be empty string.");
                }
            }
            throw new StudentBadRequestException("First Name and Last Name are mandatory.");
        } else {
            throw new StudentNotFoundException("Student with studentId : " + studentId +
                    " doesn't exist.");
        }
    }

    public void deleteStudentById(String studentId) {
        if (this.studentLocalDB.containsKey(studentId)) {
            this.studentLocalDB.remove(studentId);
        } else {
            throw new StudentNotFoundException("Student with studentId : " + studentId +
                    " doesn't exist.");
        }
    }
}















