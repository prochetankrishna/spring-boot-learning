package com.hertzbit.springbootlearning.exceptions;

public class StudentBadRequestException extends RuntimeException {

    public StudentBadRequestException(String errorMessage) {
        super(errorMessage);
    }
}
