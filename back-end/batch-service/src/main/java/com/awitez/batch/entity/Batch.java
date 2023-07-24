package com.awitez.batch.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class Batch {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long batchId;
	@Column(nullable = false)
	private long courseId;
	private long assignedMentorId;
	private long branchId;
	
	private boolean isCompleted;

	@Override
	public String toString() {
		return "Batch [batchId=" + batchId + ", courseId=" + courseId + ", assignedMentorId=" + assignedMentorId
				+ ", branchId=" + branchId + ", isCompleted=" + isCompleted + "]";
	}

	public long getBatchId() {
		return batchId;
	}

	public void setBatchId(long batchId) {
		this.batchId = batchId;
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public long getAssignedMentorId() {
		return assignedMentorId;
	}

	public void setAssignedMentorId(long assignedMentorId) {
		this.assignedMentorId = assignedMentorId;
	}

	public long getBranchId() {
		return branchId;
	}

	public void setBranchId(long branchId) {
		this.branchId = branchId;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

}
