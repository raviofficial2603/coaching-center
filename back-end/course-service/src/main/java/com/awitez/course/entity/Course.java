package com.awitez.course.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.awitez.course.payload.Branch;

@Entity
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long courseId;
	@Column(nullable = false)
	private String courseName;
	private String courseDescription;
	private float courseDuration;
	private float courseFee;

	@Transient
	private List<Branch> branches;

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", courseDescription="
				+ courseDescription + ", courseDuration=" + courseDuration + ", courseFee=" + courseFee + ", branches="
				+ branches + "]";
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

	public List<Branch> getBranches() {
		return branches;
	}

	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}

	public Course(long courseId, String courseName, String courseDescription, float courseDuration, float courseFee,
			List<Branch> branches) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseDescription = courseDescription;
		this.courseDuration = courseDuration;
		this.courseFee = courseFee;
		this.branches = branches;
	}

	public Course() {
		super();
	}
}
