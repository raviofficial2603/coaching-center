package com.awitez.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.awitez.course.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{
	
}
