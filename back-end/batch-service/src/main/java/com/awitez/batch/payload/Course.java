package com.awitez.batch.payload;

public class Course {

	private long courseId;
	private String courseName;
	private String courseDescription;
	private float courseDuration;
	private float courseFee;
	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", courseDescription="
				+ courseDescription + ", courseDuration=" + courseDuration + ", courseFee=" + courseFee + "]";
	}
	public long getCourseId() {
		return courseId;
	}
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseDescription() {
		return courseDescription;
	}
	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}
	public float getCourseDuration() {
		return courseDuration;
	}
	public void setCourseDuration(float courseDuration) {
		this.courseDuration = courseDuration;
	}
	public float getCourseFee() {
		return courseFee;
	}
	public void setCourseFee(float courseFee) {
		this.courseFee = courseFee;
	}
}
