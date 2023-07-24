package com.awitez.branch.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.awitez.branch.exception.BranchNotFoundException;




@ControllerAdvice
public class MyControllerAdvice {

	
	@ExceptionHandler(BranchNotFoundException.class)
	public ResponseEntity<String> handleBranchNotFound(BranchNotFoundException branchNotFoundException){
		return new ResponseEntity<String>(branchNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
	}
}
