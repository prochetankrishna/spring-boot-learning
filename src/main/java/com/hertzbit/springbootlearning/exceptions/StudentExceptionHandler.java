package com.hertzbit.springbootlearning.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.SimpleDateFormat;
import java.util.Date;

@ControllerAdvice
public class StudentExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String DATE_FORMAT = "yyyy.MM.dd'T'HH.mm.ss.SSSXXX";

    @ExceptionHandler(value = StudentNotFoundException.class)
    public ResponseEntity<?> handleStudentNotFoundException(StudentNotFoundException studentNotFoundException) {

        GenericException genericException = new GenericException();
        genericException.setErrorMessage(studentNotFoundException.getMessage());
        genericException.setErrorStatusCode(404);
        genericException.setErrorTimestamp(new SimpleDateFormat(DATE_FORMAT).format(new Date()));
        return new ResponseEntity<>(genericException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = StudentBadRequestException.class)
    public ResponseEntity<?> handleStudentBadRequestException(StudentBadRequestException studentBadRequestException) {

        GenericException genericException = new GenericException();
        genericException.setErrorMessage(studentBadRequestException.getMessage());
        genericException.setErrorStatusCode(400);
        genericException.setErrorTimestamp(new SimpleDateFormat(DATE_FORMAT).format(new Date()));
        return new ResponseEntity<>(genericException, HttpStatus.BAD_REQUEST);
    }

}
