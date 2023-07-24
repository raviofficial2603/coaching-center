package com.awitez.enrollment.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.awitez.enrollment.exception.CustomNotFoundException;
import com.awitez.enrollment.exception.EnrollmentNotFoundException;



@ControllerAdvice
public class MyControllerAdvice {
	
	@ExceptionHandler(CustomNotFoundException.class)
	public ResponseEntity<String> handleCustomNotFound(CustomNotFoundException customNotFoundException){
		return new ResponseEntity<String>(customNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(EnrollmentNotFoundException.class)
	public ResponseEntity<String> handleEnrollmentNotFound(EnrollmentNotFoundException enrollmentNotFoundException){
		return new ResponseEntity<String>(enrollmentNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
	}
}
