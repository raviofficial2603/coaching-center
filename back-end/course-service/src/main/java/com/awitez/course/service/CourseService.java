package com.awitez.course.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.awitez.course.entity.Course;
import com.awitez.course.payload.CourseAvailedBranch;
@Service
public interface CourseService {

	public List<Course> getAllCourses();
	public Course getCourse(long id);
	public Course createCourse(Course course);
	public Course updateCourse(Course course,long id);
	public void deleteCourse(long id);
	public boolean isPresent(long id);
	public Course addCourseToBranch(long courseId, long branchId);
	public void deleteCourseBranch(long courseId, long branchId);
	public void deleteCourseBranches(long courseId);
	public void deleteBranchCourses(long branchId);
}
