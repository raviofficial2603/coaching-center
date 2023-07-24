package com.awitez.batch.payload;

public class BatchResponse {
	private long id;
	private Course course;
	private Mentor mentor;
	private Branch branch;
	private boolean isCompleted;
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public BatchResponse() {
		super();
	}
	
	public Mentor getMentor() {
		return mentor;
	}
	public void setMentor(Mentor mentor) {
		this.mentor = mentor;
	}
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	@Override
	public String toString() {
		return "BatchResponse [id=" + id + ", course=" + course + ", mentor=" + mentor + ", branch=" + branch + "]";
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public boolean isCompleted() {
		return isCompleted;
	}
	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
	
	public BatchResponse(long id, Course course, Mentor mentor, Branch branch, boolean isCompleted) {
		super();
		this.id = id;
		this.course = course;
		this.mentor = mentor;
		this.branch = branch;
		this.isCompleted = isCompleted;
	}
}
