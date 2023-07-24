package com.awitez.admin.service;

import java.util.List;

import com.awitez.admin.payload.AuthRequest;
import com.awitez.admin.payload.Batch;
import com.awitez.admin.payload.BatchResponse;
import com.awitez.admin.payload.Branch;
import com.awitez.admin.payload.Candidate;
import com.awitez.admin.payload.Course;

import com.awitez.admin.payload.Enrollment;
import com.awitez.admin.payload.Mentor;



public interface AdminService {

	public Batch createBatch(Batch batch);
	public List<BatchResponse> getAllBatches();
	public Batch getBatch(long id);
	public void updateBatch(Batch batch, long id);
	public void deleteBatch(long id);
	
	public List<Branch> getAllBranches();
	public Branch getBranch(long id);
	public Branch createBranch(Branch branch);
	public void updateBranch(Branch branch,long id);
	public void deleteBranch(long id);
	

	public java.util.List<Candidate> getAllCandidates();
	public Candidate getCandidate(long id);
	public void deleteCandidate(long id);
	
	public List<Course> getAllCourses();
	public Course getCourse(long id);
	public Course createCourse(Course course);
	public void updateCourse(Course course,long id);
	public void deleteCourse(long id);
	
	
	public java.util.List<Enrollment> getAllEnrollments();
	public Enrollment getEnrollment(long id);
	public void updateEnrollment(Enrollment enrollment, long id);
	public void deleteEnrollment(long id);
	
	public Mentor createMentor(Mentor mentor);
	public java.util.List<Mentor> getAllMentors();
	public Mentor getMentor(long id);
	public void updateMentor(Mentor mentor, long id);
	public void deleteMentor(long id);
	
	
	public void updateIsCompleted(boolean isCompleted,long batchId);
	
	public void assignBatchToEnrollment(long batchId,long enrollmentId);
	public void updateEnrollmentStatus(String enrollmentStatus,long enrollmentId);
	
	public Course addCourseToBranch(long courseId, long branchId);
	public List<Mentor> getMentorsByBranchId(long branchId);
	public void deleteCourseBranch(long courseId, long branchId);
	
	public boolean adminAuth(AuthRequest authRequest);
}
