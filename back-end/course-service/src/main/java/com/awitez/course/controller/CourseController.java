package com.awitez.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.awitez.course.entity.Course;
import com.awitez.course.service.CourseService;
@RestController
@RequestMapping("/course")
@CrossOrigin(origins ="http://localhost:3000")
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@GetMapping("/courses")
	public List<Course> getAllCourses(){
		return courseService.getAllCourses();
	}
	@GetMapping("/courses/{id}")
	public Course getCourse(@PathVariable long id) {
		return courseService.getCourse(id);
	}
	@PostMapping("/create")
	public Course createCourse(@RequestBody Course course) {
		return courseService.createCourse(course);
		
	}
	@PutMapping("/update/{id}")
	public Course updateCourse(@RequestBody Course course,@PathVariable long id) {
		return courseService.updateCourse(course, id);
	}
	@DeleteMapping("/delete/{id}")
	public void deleteCourse(@PathVariable long id) {
		courseService.deleteCourse(id);
	}
	@GetMapping("/is-present/{id}")
	public boolean isPresent(@PathVariable long id) {
		System.out.println("in CourseController"+id);
		return courseService.isPresent(id);
	}
	@PostMapping("/add-course-to-branch/{courseId}/{branchId}")
	public Course addCourseToBranch(@PathVariable long courseId,@PathVariable long branchId)
	{
		return courseService.addCourseToBranch(courseId,branchId);
	}
	@DeleteMapping("/delete-course-branch/{courseId}/{branchId}")
	public void deleteCourseBranch(@PathVariable long courseId,@PathVariable long branchId){
		
		courseService.deleteCourseBranch(courseId,branchId);
	}
	@DeleteMapping("/delete-course-branch/{courseId}")
	public void deleteCourseBranches(@PathVariable long courseId) {
		courseService.deleteCourseBranches(courseId);
	}
	@DeleteMapping("/delete-branch-course/{branchId}")
	public void deleteBranchCourses(@PathVariable long branchId) {
		courseService.deleteBranchCourses(branchId);
	}
	

}
