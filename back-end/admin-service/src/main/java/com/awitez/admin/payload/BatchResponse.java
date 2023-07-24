package com.awitez.admin.payload;

public class BatchResponse {
	private Course course;
	private Mentor mentor;
	private Branch branch;
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
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
}
