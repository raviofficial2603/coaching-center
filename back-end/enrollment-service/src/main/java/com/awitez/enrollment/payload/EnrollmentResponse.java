package com.awitez.enrollment.payload;

public class EnrollmentResponse {
	private long id;
	private Course course;
	private Batch batch;

	private Branch branch;
	@Override
	public String toString() {
		return "EnrollmentResponse [id=" + id + ", course=" + course + ", batch=" + batch + ", branch=" + branch + "]";
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public Batch getBatch() {
		return batch;
	}
	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
}
