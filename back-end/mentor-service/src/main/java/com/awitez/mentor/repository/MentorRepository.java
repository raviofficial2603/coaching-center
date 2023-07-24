package com.awitez.mentor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.awitez.mentor.entity.Mentor;

public interface MentorRepository extends JpaRepository<Mentor, Long> {

	List<Mentor> findAllByBranchId(long branchId);

}
