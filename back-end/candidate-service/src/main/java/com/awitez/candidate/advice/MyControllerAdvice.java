package com.awitez.candidate.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.awitez.candidate.exception.CandidateNotFoundException;




@ControllerAdvice
public class MyControllerAdvice {

	
	@ExceptionHandler(CandidateNotFoundException.class)
	public ResponseEntity<String> handleBatchNotFound(CandidateNotFoundException candidateNotFoundException){
		return new ResponseEntity<String>(candidateNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
	}
}
