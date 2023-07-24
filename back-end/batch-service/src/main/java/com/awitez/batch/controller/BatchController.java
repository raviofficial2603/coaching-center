package com.awitez.batch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.awitez.batch.entity.Batch;
import com.awitez.batch.payload.BatchResponse;
import com.awitez.batch.service.BatchService;
@RestController
@RequestMapping("/batch")
public class BatchController {
	
	@Autowired
	private BatchService batchService;
	
	
	@PostMapping("/create")
	public Batch createBatch(@RequestBody Batch batch)
	{
		return batchService.createBatch(batch);
	}
	 
	@GetMapping("/batches")
	public List<BatchResponse> getAllBatchs(){
		
		return batchService.getAllBatchs();
	}
	
	@GetMapping("/batches/{id}")
	public Batch getBatch(@PathVariable long id) {
		System.out.println("in getBatch"+id);
		return batchService.getBatch(id);
	}
	@PutMapping("/update/{id}")
	public Batch updateBatch(@RequestBody Batch batch,@PathVariable long id) {
		return batchService.updateBatch(batch, id);
	}
	@DeleteMapping("/delete/{id}")
	public void deleteBatch(@PathVariable long id) {
		batchService.deleteBatch(id);
	}
	@GetMapping("/is-present/{id}")
	public boolean isPresent(@PathVariable long id) {
		System.out.println("in BatchController"+id);
		return batchService.isPresent(id);
	}
	@GetMapping("/get-batch-details/{id}")
	public BatchResponse getBatchDetails(@PathVariable long id) {
		return batchService.getBatchDetails(id);
	}
	
}
