package com.hertzbit.springbootlearning.repository;

import com.hertzbit.springbootlearning.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, String> {
}
