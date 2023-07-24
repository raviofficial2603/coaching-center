package com.awitez.admin.payload;

public class Enrollment {
	private long enrollmentId;
	private long candidateId;
	private long courseId;
	private long batchId;
	private long branchId;
	private float rating;
	private String enrollmentStatus;
	@Override
	public String toString() {
		return "Enrollment [enrollmentId=" + enrollmentId + ", candidateId=" + candidateId + ", courseId=" + courseId
				+ ", batchId=" + batchId + ", branchId=" + branchId + ", rating=" + rating + ", enrollmentStatus="
				+ enrollmentStatus + "]";
	}
	public long getEnrollmentId() {
		return enrollmentId;
	}
	public void setEnrollmentId(long enrollmentId) {
		this.enrollmentId = enrollmentId;
	}
	public long getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(long candidateId) {
		this.candidateId = candidateId;
	}
	public long getCourseId() {
		return courseId;
	}
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
	public long getBatchId() {
		return batchId;
	}
	public void setBatchId(long batchId) {
		this.batchId = batchId;
	}
	public long getBranchId() {
		return branchId;
	}
	public void setBranchId(long branchId) {
		this.branchId = branchId;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public String getEnrollmentStatus() {
		return enrollmentStatus;
	}
	public void setEnrollmentStatus(String enrollmentStatus) {
		this.enrollmentStatus = enrollmentStatus;
	}
	
	}
