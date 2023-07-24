package com.awitez.course.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.awitez.course.entity.Course;
import com.awitez.course.exception.CourseAvailedBranchNotFound;
import com.awitez.course.exception.CourseNotFoundException;
import com.awitez.course.exception.CustomNotFoundException;
import com.awitez.course.exception.DuplicateEntryException;
import com.awitez.course.payload.Branch;
import com.awitez.course.payload.CourseAvailedBranch;
import com.awitez.course.repository.CourseAvailedBranchRepository;
import com.awitez.course.repository.CourseRepository;
import com.awitez.course.service.CourseService;
@Service
public class CourseServiceImpl implements CourseService{

	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private CourseAvailedBranchRepository courseAvailedBranchRepository;
	@Override
	public List<Course> getAllCourses() {
		
		List<Course> repoCourses = courseRepository.findAll();
		return repoCourses.stream().map(course->this.getCourse(course.getCourseId())).collect(Collectors.toList());
	}

	@Override
	public Course getCourse(long id) {
		Course course=courseRepository.findById(id).orElseThrow(()->new CourseNotFoundException("Course not found with id:"+id));
//		List<CourseAvailedBranch> courseAvailedBranches= courseAvailedBranchRepository.findAllByCourseId(id).get();
		List branches = restTemplate.getForObject("http://BRANCH-SERVICE/branch/branches", List.class);
		course.setBranches(branches);
		
 		return course;
	}

	@Override
	public Course createCourse(Course course) {
		return courseRepository.save(course);
	}
 
	@Override
	public Course updateCourse(Course course, long id) {
		Course updatedCourse=courseRepository.findById(id).get();
		updatedCourse.setCourseDescription(course.getCourseDescription());
		updatedCourse.setCourseDuration(course.getCourseDuration());
		updatedCourse.setCourseFee(course.getCourseFee());
		updatedCourse.setCourseName(course.getCourseName());

		return courseRepository.save(updatedCourse);
	}

	@Override
	public void deleteCourse(long id) {
		// TODO Auto-generated method stub
		if(this.isPresent(id)) {
			
		this.deleteCourseBranches(id);
		courseRepository.deleteById(id);
		}
		else 
			throw new CourseNotFoundException("course not found with id:"+id);
	}

	@Override
	public boolean isPresent(long id) {

		Optional<Course> course = courseRepository.findById(id);
		return course.isPresent();
	}

	@Override
	public Course addCourseToBranch(long courseId, long branchId) {
		if(courseAvailedBranchRepository.isAlreadyExists(courseId, branchId)){
			throw new DuplicateEntryException("Course with id:"+courseId+" Already Available in branch:"+branchId);
		}
		if(!restTemplate.getForObject("http://BRANCH-SERVICE/branch/is-present/"+branchId,Boolean.class)) {
			throw new CustomNotFoundException("branch not found with id:"+branchId);
		}
		if(!this.isPresent(courseId)) {
			throw new CustomNotFoundException("course not found with id:"+courseId);
		} 
		CourseAvailedBranch courseAvailedBranch=new CourseAvailedBranch();
		courseAvailedBranch.setBranchId(branchId);
		courseAvailedBranch.setCourseId(courseId); 
		courseAvailedBranchRepository.save(courseAvailedBranch);
		return this.getCourse(courseId);
	}

	@Override
	public void deleteCourseBranch(long courseId, long branchId) {
	
		if(!restTemplate.getForObject("http://BRANCH-SERVICE/branch/is-present/"+branchId,Boolean.class)) {
			throw new CustomNotFoundException("branch not found with id:"+branchId);
		}
		if(!this.isPresent(courseId)) {
			throw new CustomNotFoundException("course not found with id:"+courseId);
		} 
		System.out.println("aksdjflkajds");
		if(courseAvailedBranchRepository.isAlreadyExists(courseId, branchId)){
			CourseAvailedBranch courseAvailedBranch=courseAvailedBranchRepository.findByCourseIdAndBranchId(courseId,branchId);
			courseAvailedBranchRepository.delete(courseAvailedBranch);
		}
		else
			
		throw new CourseAvailedBranchNotFound("Course with id:"+courseId+" Already Available in branch:"+branchId);
	}
	public void deleteCourseBranches(long courseId) {
		
		if(!this.isPresent(courseId)) {
			throw new CustomNotFoundException("course not found with id:"+courseId);
		} 
		if(courseAvailedBranchRepository.findByCourseId(courseId).isPresent()){
		List<CourseAvailedBranch> courseAvailedBranchs = courseAvailedBranchRepository.findAllByCourseId(courseId).get();
		courseAvailedBranchRepository.deleteAll(courseAvailedBranchs);
		}
		
	
	}

	@Override
	public void deleteBranchCourses(long branchId) {
		
		if(!restTemplate.getForObject("http://BRANCH-SERVICE/branch/is-present/"+branchId,Boolean.class)) {
			throw new CustomNotFoundException("branch not found with id:"+branchId);
		}
		
		if(courseAvailedBranchRepository.findByBranchId(branchId).isPresent()){
		List<CourseAvailedBranch> courseAvailedBranchs = courseAvailedBranchRepository.findAllByBranchId(branchId).get();
		courseAvailedBranchRepository.deleteAll(courseAvailedBranchs);
		}
		
	
		}
	
	

}
