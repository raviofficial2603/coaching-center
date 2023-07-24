package com.awitez.course.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.awitez.course.exception.CourseAvailedBranchNotFound;
import com.awitez.course.exception.CourseNotFoundException;
import com.awitez.course.exception.CustomNotFoundException;
import com.awitez.course.exception.DuplicateEntryException;




@ControllerAdvice
public class MyControllerAdvice {

	@ExceptionHandler(CustomNotFoundException.class)
	public ResponseEntity<String> handleCustomNotFound(CustomNotFoundException customNotFoundException){
		return new ResponseEntity<String>(customNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(CourseNotFoundException.class)
	public ResponseEntity<String> handleCourseNotFound(CourseNotFoundException courseNotFoundException){
		return new ResponseEntity<String>(courseNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(CourseAvailedBranchNotFound.class)
	public ResponseEntity<String> handleCourseAvailedBranchNotFound(CourseAvailedBranchNotFound courseAvailedBranchNotFound){
		return new ResponseEntity<String>(courseAvailedBranchNotFound.getMessage(), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(DuplicateEntryException.class)
	public ResponseEntity<String> handleDuplicateEntry(DuplicateEntryException duplicateEntryException){
		return new ResponseEntity<String>(duplicateEntryException.getMessage(), HttpStatus.CONFLICT);
	}
}
