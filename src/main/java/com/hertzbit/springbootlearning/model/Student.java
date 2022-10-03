package com.hertzbit.springbootlearning.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity(name = "students")
public class Student extends RepresentationModel<Student> implements Serializable{

    @Id
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name = "uuid-generator", strategy = "uuid")
    private String studentId;
    private String studentFirstName;
    private String studentLastName;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }
}
