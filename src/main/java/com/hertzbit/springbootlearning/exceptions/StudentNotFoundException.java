package com.hertzbit.springbootlearning.exceptions;

public class StudentNotFoundException extends RuntimeException{

    public StudentNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
