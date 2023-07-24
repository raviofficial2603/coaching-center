package com.awitez.enrollment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.awitez.enrollment.entity.Enrollment;
import com.awitez.enrollment.payload.EnrollmentResponse;
import com.awitez.enrollment.service.EnrollmentService;
@RestController
@RequestMapping("/enrollment")
@CrossOrigin
public class EnrollmentController {
	
	@Autowired
	private EnrollmentService enrollmentService;
	
	@PostMapping("/create")
	public Enrollment createEnrollment(@RequestBody Enrollment enrollment)
	{
		
		return enrollmentService.createEnrollment(enrollment);
	}
	
	@GetMapping("/enrollments")
	public List<Enrollment> getAllEnrollments(){
		return enrollmentService.getAllEnrollments();
	}
	
	@GetMapping("/enrollments/{id}")
	public Enrollment getEnrollment(@PathVariable long id) {
		return enrollmentService.getEnrollment(id);
	}
	@PutMapping("/update/{id}")
	public Enrollment updateEnrollment(@RequestBody Enrollment enrollment,@PathVariable long id) {
		
		return enrollmentService.updateEnrollment(enrollment, id);
	}
	@DeleteMapping("/delete/{id}")
	public void deleteEnrollment(@PathVariable long id) {
		enrollmentService.deleteEnrollment(id);
	}
	@GetMapping("/is-present/{id}")
	public boolean isPresent(@PathVariable long id) {
		return enrollmentService.isPresent(id);
	}
	@GetMapping("/get-enrollment-by-candidateId/{candidateId}") 
	public List<EnrollmentResponse> getEnrollmentByCandidateId(@PathVariable long candidateId){
		return enrollmentService.getEnrollmentsByCandidateId(candidateId);
	}
	@GetMapping("/get-enrollment-by-branchId/{branchId}") 
	public List<EnrollmentResponse> getEnrollmentByBranchId(@PathVariable long branchId){
		return enrollmentService.getEnrollmentsByBranchId(branchId);
	}
	@GetMapping("/get-enrollment-by-courseId/{courseId}") 
	public List<EnrollmentResponse> getEnrollmentByCourseId(@PathVariable long courseId){
		return enrollmentService.getEnrollmentsByCandidateId(courseId);
	}
}
