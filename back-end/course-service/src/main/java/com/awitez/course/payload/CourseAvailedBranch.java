package com.awitez.course.payload;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "courseId", "branchId" }) })
public class CourseAvailedBranch {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long courseId;
	private long branchId;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCourseId() {
		return courseId;
	}
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
	public long getBranchId() {
		return branchId;
	}
	public void setBranchId(long branchId) {
		this.branchId = branchId;
	}
	@Override
	public String toString() {
		return "CourseAvailedBranch [id=" + id + ", courseId=" + courseId + ", branchId=" + branchId + "]";
	}
	
}
