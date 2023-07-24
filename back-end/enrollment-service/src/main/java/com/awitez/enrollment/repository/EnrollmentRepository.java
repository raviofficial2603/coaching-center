package com.awitez.enrollment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.awitez.enrollment.entity.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {


	List<Enrollment> findAllByCandidateId(long userId);

	List<Enrollment> findAllByCourseId(long courseId);

	List<Enrollment> findAllByBranchId(long branchId);


}
