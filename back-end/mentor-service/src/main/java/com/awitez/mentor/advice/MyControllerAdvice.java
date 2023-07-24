package com.awitez.mentor.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.awitez.mentor.exception.CustomNotFoundException;
import com.awitez.mentor.exception.MentorNotFoundException;

@ControllerAdvice
public class MyControllerAdvice {
	@ExceptionHandler(MentorNotFoundException.class)
	public ResponseEntity<String> handleMentorNotFound(MentorNotFoundException mentorNotFoundException){
		return new ResponseEntity<String>(mentorNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(CustomNotFoundException.class)
	public ResponseEntity<String> handleCustomNotFound(CustomNotFoundException customNotFoundException){
		return new ResponseEntity<String>(customNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
	}
}
