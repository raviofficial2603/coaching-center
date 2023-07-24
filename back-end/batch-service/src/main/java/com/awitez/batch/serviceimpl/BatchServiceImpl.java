package com.awitez.batch.serviceimpl;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.awitez.batch.entity.Batch;
import com.awitez.batch.exception.BatchNotFoundException;
import com.awitez.batch.exception.CustomNotFoundException;
import com.awitez.batch.payload.BatchResponse;
import com.awitez.batch.payload.Branch;
import com.awitez.batch.payload.Course;
import com.awitez.batch.payload.Mentor;
import com.awitez.batch.repository.BatchRepository;
import com.awitez.batch.service.BatchService;
@Service
public class BatchServiceImpl implements BatchService {

	@Autowired
	private BatchRepository batchRepository;
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public Batch createBatch(Batch batch) {
		
		long assignedMentorId = batch.getAssignedMentorId();
		long branchId = batch.getBranchId();
		long courseId = batch.getCourseId();
		
		if(!restTemplate.getForObject("http://MENTOR-SERVICE/mentor/is-present/"+assignedMentorId,Boolean.class)) {
			throw new CustomNotFoundException("Mentor not found with id:"+assignedMentorId);
		}
		if(!restTemplate.getForObject("http://BRANCH-SERVICE/branch/is-present/"+branchId,Boolean.class)) {
			throw new CustomNotFoundException("Branch not found with id:"+branchId);
		}
		if(!restTemplate.getForObject("http://COURSE-SERVICE/course/is-present/"+courseId,Boolean.class)) {
			throw new CustomNotFoundException("Course not found with id:"+courseId);
		}
				
		return batchRepository.save(batch);
	}

	@Override
	public List<BatchResponse> getAllBatchs() {

		List<Batch> batches = batchRepository.findAll();
		return batches.stream().map(batch->this.getBatchDetails(batch.getBatchId())).collect(Collectors.toList());

	}

	@Override
	public Batch getBatch(long id) {
		return batchRepository.findById(id).orElseThrow(()->new BatchNotFoundException("No Batch found with id:"+id));
	}

	@Override
	public Batch updateBatch(Batch batch, long id) {
		
		Batch updatedBatch=this.getBatch(id);
		long assignedMentorId = batch.getAssignedMentorId();
		long branchId = batch.getBranchId();
		long courseId = batch.getCourseId();
		
		if(!restTemplate.getForObject("http://MENTOR-SERVICE/mentor/is-present/"+assignedMentorId,Boolean.class)) {
			throw new CustomNotFoundException("Mentor not found with id:"+assignedMentorId);
		}
		else if(!restTemplate.getForObject("http://BRANCH-SERVICE/branch/is-present/"+branchId,Boolean.class)) {
			throw new CustomNotFoundException("Branch not found with id:"+branchId);
		}
		else if(!restTemplate.getForObject("http://COURSE-SERVICE/course/is-present/"+courseId,Boolean.class)) {
			throw new CustomNotFoundException("Course not found with id:"+courseId);
		}
		
		updatedBatch.setBranchId(batch.getBranchId());
		updatedBatch.setAssignedMentorId(batch.getAssignedMentorId());
		updatedBatch.setCourseId(batch.getCourseId());
		updatedBatch.setCompleted(batch.isCompleted());
		System.out.println(batch);
		return batchRepository.save(updatedBatch);
	}

	@Override
	public void deleteBatch(long id) {
		this.getBatch(id);
		batchRepository.deleteById(id);
	}

	@Override
	public boolean isPresent(long id) {
		Optional<Batch> batch = batchRepository.findById(id);
		return batch.isPresent();
		
	}

	@Override
	public BatchResponse getBatchDetails(long id) {
		Batch batch=this.getBatch(id);
		
		Branch branch=restTemplate.getForObject("http://BRANCH-SERVICE/branch/branches/"+batch.getBranchId(),Branch.class);
		Course course=restTemplate.getForObject("http://COURSE-SERVICE/course/courses/"+batch.getCourseId(),Course.class);
		Mentor mentor=restTemplate.getForObject("http://MENTOR-SERVICE/mentor/mentors/"+batch.getAssignedMentorId(),Mentor.class);
		return new BatchResponse(batch.getBatchId(),course,mentor,branch,batch.isCompleted());
	}

}
