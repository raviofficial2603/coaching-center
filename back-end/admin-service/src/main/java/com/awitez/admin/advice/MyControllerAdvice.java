package com.awitez.admin.advice;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.awitez.admin.exception.CustomNotFoundException;


@ControllerAdvice
public class MyControllerAdvice {
	
	@ExceptionHandler(CustomNotFoundException.class)
	public ResponseEntity<String> handleCustomNotFound(CustomNotFoundException customNotFoundException){
		return new ResponseEntity<String>(customNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
	}
	

	
}
