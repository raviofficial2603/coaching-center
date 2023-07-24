package com.awitez.course.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.awitez.course.entity.Course;
import com.awitez.course.payload.CourseAvailedBranch;

public interface CourseAvailedBranchRepository extends JpaRepository<CourseAvailedBranch, Long> {

	
	@Query("select c from CourseAvailedBranch c where courseId=?1 AND branchId=?2")
	CourseAvailedBranch findByCourseIdAndBranchId(long courseId,long branchId);
	
	@Query("select case when count(c)>0 then true else false end from CourseAvailedBranch c where c.courseId=?1 AND c.branchId=?2")
	boolean isAlreadyExists(long courseId,long branchId);
	Optional<List<CourseAvailedBranch>> findAllByCourseId(long courseId);
	Optional<List<CourseAvailedBranch>> findAllByBranchId(long branchId);
	Optional<CourseAvailedBranch> findByCourseId(long courseId);
	Optional<CourseAvailedBranch> findByBranchId(long branchId);
	
	

}
