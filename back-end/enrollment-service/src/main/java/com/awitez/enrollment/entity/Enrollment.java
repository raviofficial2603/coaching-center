package com.awitez.enrollment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Enrollment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long enrollmentId;
	@Column(nullable = false)
	private long candidateId;
	@Column(nullable = false)
	private long courseId;
	
	private long batchId;
	private long branchId;
	private float rating;
	private String enrollmentStatus;
	
	public Enrollment() {
		super();
	}
	public Enrollment(long enrollmentId, long candidateId, long courseId, long batchId, long branchId, float rating,
			String enrollmentStatus) {
		super();
		this.enrollmentId = enrollmentId;
		
		this.candidateId = candidateId;
		this.courseId = courseId;
		this.batchId = batchId;
		this.branchId = branchId;
		this.rating = rating;
		this.enrollmentStatus = enrollmentStatus;
	}
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
