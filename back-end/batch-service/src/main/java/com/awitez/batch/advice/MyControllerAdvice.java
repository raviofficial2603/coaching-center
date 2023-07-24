package com.awitez.batch.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.awitez.batch.exception.BatchNotFoundException;
import com.awitez.batch.exception.CustomNotFoundException;



@ControllerAdvice
public class MyControllerAdvice {

	@ExceptionHandler(CustomNotFoundException.class)
	public ResponseEntity<String> handleCustomNotFound(CustomNotFoundException customNotFoundException){
		return new ResponseEntity<String>(customNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(BatchNotFoundException.class)
	public ResponseEntity<String> handleBatchNotFound(BatchNotFoundException batchNotFoundException){
		return new ResponseEntity<String>(batchNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
	}
}
