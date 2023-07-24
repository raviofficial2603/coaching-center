package com.awitez.enrollment.service;

import java.util.List;



import com.awitez.enrollment.entity.Enrollment;
import com.awitez.enrollment.payload.EnrollmentResponse;

public interface EnrollmentService {

	public Enrollment createEnrollment(Enrollment enrollment);
	public java.util.List<Enrollment> getAllEnrollments();
	public Enrollment getEnrollment(long id);
	public Enrollment updateEnrollment(Enrollment enrollment, long id);
	public void deleteEnrollment(long id);
	public boolean isPresent(long id);

	public List<EnrollmentResponse> getEnrollmentsByCandidateId(long candidateId);
	List<EnrollmentResponse> getEnrollmentsByCourseId(long courseId);
	List<EnrollmentResponse> getEnrollmentsByBranchId(long branchId);
}
