package com.awitez.admin.controller;

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

import com.awitez.admin.payload.AuthRequest;
import com.awitez.admin.payload.Batch;
import com.awitez.admin.payload.BatchResponse;
import com.awitez.admin.payload.Branch;
import com.awitez.admin.payload.Candidate;
import com.awitez.admin.payload.Course;

import com.awitez.admin.payload.Enrollment;
import com.awitez.admin.payload.Mentor;
import com.awitez.admin.service.AdminService;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/batch/create")
	public Batch createBatch(@RequestBody Batch batch) {
		System.out.println("In admin batch create");
		return adminService.createBatch(batch);
	}
	@GetMapping("/batch/batches")
	public java.util.List<BatchResponse> getAllBatches(){
		return adminService.getAllBatches();
	}
	@GetMapping("/batch/batches/{id}")
	public Batch getBatch(@PathVariable long id) {
		return adminService.getBatch(id);
	}
	@PutMapping("/batch/update/{id}")
	public void updateBatch(@RequestBody Batch batch,@PathVariable long id) {
		adminService.updateBatch(batch, id);
	}
	@DeleteMapping("/batch/delete/{id}")
	public void deleteBatch(@PathVariable long id) {
		adminService.deleteBatch(id);
	}
	@GetMapping("/branch/branches")
	public List<Branch> getAllBranches(){
		return adminService.getAllBranches();
	}
	@GetMapping("/branch/branches/{id}")
	public Branch getBranch(@PathVariable long id) {
		return adminService.getBranch(id);
	}
	@PostMapping("/branch/create")
	public Branch createBranch(@RequestBody Branch branch) {
		return adminService.createBranch(branch);
	}
	@PutMapping("/branch/update/{id}")
	public void updateBranch(@RequestBody Branch branch,@PathVariable long id) {
		 adminService.updateBranch(branch, id);
	}
	@DeleteMapping("/branch/delete/{id}")
	public void deleteBranch(@PathVariable long id) {
		adminService.deleteBranch(id);
	}
	
	@GetMapping("/candidate/candidates")
	public java.util.List<Candidate> getAllCandidates(){
		return adminService.getAllCandidates();
	}
	@GetMapping("/candidate/candidates/{id}")
	public Candidate getCandidate(@PathVariable long id) {
		return adminService.getCandidate(id);
	}
	
	@DeleteMapping("/candidate/delete/{id}")
	public void deleteCandidate(@PathVariable long id) {
		adminService.deleteCandidate(id);
	}
	@GetMapping("/course/courses")
	public List<Course> getAllCourses(){
		return adminService.getAllCourses();
	}
	@GetMapping("/course/courses/{id}")
	public Course getCourse(@PathVariable long id) {
		return adminService.getCourse(id);
	}
	@PostMapping("/course/create")
	public Course createCourse(@RequestBody Course course) {
		return adminService.createCourse(course);
	}
	@PutMapping("/course/update/{id}")
	public void updateCourse(@RequestBody Course course,@PathVariable long id) {
		adminService.updateCourse(course, id);
	}
	@DeleteMapping("/course/delete/{id}")
	public void deleteCourse(@PathVariable long id) {
		adminService.deleteCourse(id);
	}
	
	@GetMapping("/enrollment/enrollments")
	public java.util.List<Enrollment> getAllEnrollments(){
		return adminService.getAllEnrollments();
	}
	
	@GetMapping("/enrollment/enrollments/{id}")
	public Enrollment getEnrollment(@PathVariable long id) {
		return adminService.getEnrollment(id);
	}
	@PutMapping("/enrollment/update/{id}")
	public void updateEnrollment(@RequestBody Enrollment enrollment,@PathVariable long id) {
		System.out.println("In update enrollment"+enrollment.getBranchId());
		
		adminService.updateEnrollment(enrollment, id);
	}
	@DeleteMapping("/enrollment/delete/{id}")
	public void deleteEnrollment(@PathVariable long id) {
		adminService.deleteEnrollment(id);
	}
	@PostMapping("/mentor/create")
	public Mentor createMentor(@RequestBody Mentor mentor) {
		return adminService.createMentor(mentor);
	}
	@GetMapping("/mentor/mentors")
	public java.util.List<Mentor> getAllMentors(){
		return adminService.getAllMentors();
	}
	@GetMapping("/get-mentors-by-branchid/{branchId}")
	public List<Mentor> getMentorsByBranchId(@PathVariable long branchId){
		
		return adminService.getMentorsByBranchId(branchId);
	}
	@DeleteMapping("/delete-course-branch/{courseId}/{branchId}")
	public void deleteCourseBranch(@PathVariable long  courseId,@PathVariable long branchId) {
		adminService.deleteCourseBranch(courseId,branchId);
	}
	@GetMapping("/mentor/mentors/{id}")
	public Mentor getMentor(@PathVariable long id) {
		return adminService.getMentor(id);
	}
	@PutMapping("/mentor/update/{id}")
	public void updateMentor(@RequestBody Mentor mentor,@PathVariable long id) {
		 adminService.updateMentor(mentor, id);
	}
	@DeleteMapping("/mentor/delete/{id}")
	public void deleteMentor(@PathVariable long id) {
		adminService.deleteMentor(id);
	}
	
	@PutMapping("/update-completion/{isCompleted}/{batchId}")
	public void updateIsCompleted(@PathVariable boolean isCompleted,@PathVariable long batchId) {
		adminService.updateIsCompleted(isCompleted, batchId);
	}
	@PutMapping("/assign-batch/{batchId}/{enrollmentId}")
	public void assignBatchToEnrollment(@PathVariable long batchId,@PathVariable long enrollmentId) {
		adminService.assignBatchToEnrollment(batchId, enrollmentId);
	}
	@PutMapping("/update-enrollment-status/{enrollmentStatus}/{enrollmentId}")
	public void updateEnrollmentStatus(@PathVariable String enrollmentStatus,@PathVariable long enrollmentId) {
		adminService.updateEnrollmentStatus(enrollmentStatus, enrollmentId);
	}
	@PostMapping("/admin-auth")
	public boolean adminAuth(@RequestBody AuthRequest authRequest) {
		return adminService.adminAuth(authRequest);
	}
	
}
