package com.awitez.branch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.awitez.branch.entity.Branch;

public interface BranchRepository extends JpaRepository<Branch, Long>{

}
