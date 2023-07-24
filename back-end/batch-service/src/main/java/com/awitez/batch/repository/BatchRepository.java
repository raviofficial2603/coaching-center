package com.awitez.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.awitez.batch.entity.Batch;

public interface BatchRepository extends JpaRepository<Batch, Long>{

	
}
