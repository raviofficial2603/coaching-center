package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.awitez.batch.entity.Batch;
import com.awitez.batch.payload.BatchResponse;
import com.awitez.batch.payload.Branch;
import com.awitez.batch.payload.Course;
import com.awitez.batch.payload.Mentor;
import com.awitez.batch.repository.BatchRepository;
import com.awitez.batch.serviceimpl.BatchServiceImpl;
@SpringBootTest(classes = {BatchserviceTest.class})
class BatchserviceTest {

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Mock
	private BatchRepository batchRepository;
	@Mock
	private RestTemplate restTemplate;
	@InjectMocks
	private BatchServiceImpl batchService;
	@Test
	@DisplayName("create batch")
	void createBatch() {
		Batch batch=new Batch();
		when(restTemplate.getForObject("http://MENTOR-SERVICE/mentor/is-present/"+0l,Boolean.class)).thenReturn(true);
		when(restTemplate.getForObject("http://BRANCH-SERVICE/branch/is-present/"+0l,Boolean.class)).thenReturn(true);
		when(restTemplate.getForObject("http://COURSE-SERVICE/course/is-present/"+0l,Boolean.class)).thenReturn(true);
		when( batchRepository.save(batch)).thenReturn(batch);
		assertThat(batch).isEqualTo(batchService.createBatch(batch)); 
	}
	@Test
	@DisplayName("get all batches")
	void getAllBatches() {
		when(batchRepository.findAll()).thenReturn(List.of(new Batch(),new Batch()));
		Batch batch=new Batch();
		when(batchRepository.findById(any())).thenReturn(Optional.of(batch));
		when(restTemplate.getForObject("http://BRANCH-SERVICE/branch/branches/"+batch.getBranchId(),Branch.class)).thenReturn(null);
		when(restTemplate.getForObject("http://COURSE-SERVICE/course/courses/"+batch.getCourseId(),Course.class)).thenReturn(null);
		when(restTemplate.getForObject("http://MENTOR-SERVICE/mentor/mntors/"+batch.getAssignedMentorId(),Mentor.class)).thenReturn(null);
		assertThat(batchService.getAllBatchs()).isNotNull();
		assertThat(2).isEqualTo(batchService.getAllBatchs().size());
	}
	@Test
	@DisplayName("get batch details")
	void getBatchDetails() {
		Batch batch=new Batch();
		when(batchRepository.findById(any())).thenReturn(Optional.of(batch));
		when(restTemplate.getForObject("http://BRANCH-SERVICE/branch/branches/"+batch.getBranchId(),Branch.class)).thenReturn(null);
		when(restTemplate.getForObject("http://COURSE-SERVICE/course/courses/"+batch.getCourseId(),Course.class)).thenReturn(null);
		when(restTemplate.getForObject("http://MENTOR-SERVICE/mentor/mentors/"+batch.getAssignedMentorId(),Mentor.class)).thenReturn(null);
		BatchResponse expectedBatchResponse=new BatchResponse();
		assertThat(expectedBatchResponse.getId()).isEqualTo(batchService.getBatchDetails(0l).getId());
	}
	@Test
	@DisplayName("get batch by id")
	void getBatch() {
		Batch batch=new Batch();
		when(batchRepository.findById(any())).thenReturn(Optional.of(batch));
		assertThat(batch).isEqualTo(batchService.getBatch(0));
	}
	@Test
	@DisplayName("check batch by id")
	void isPresent() {
		Batch batch=new Batch();
		when(batchRepository.findById(any())).thenReturn(Optional.of(batch));
		assertThat(true).isEqualTo(batchService.isPresent(0));
	}
	
	@Test
	@DisplayName("delete batch by id")
	void deleteBatch() {
		Batch batch=new Batch();
		when(batchRepository.findById(any())).thenReturn(Optional.of(batch));
		batchService.deleteBatch(0l);
		verify(batchRepository,times(1)).deleteById(0l);
	}
	@Test
	@DisplayName("udpate batch by id")
	void updateBatch() {
		Batch batch=new Batch();
		when(batchRepository.findById(any())).thenReturn(Optional.of(batch));
		when(restTemplate.getForObject("http://MENTOR-SERVICE/mentor/is-present/"+0l,Boolean.class)).thenReturn(true);
		when(restTemplate.getForObject("http://BRANCH-SERVICE/branch/is-present/"+0l,Boolean.class)).thenReturn(true);
		when(restTemplate.getForObject("http://COURSE-SERVICE/course/is-present/"+0l,Boolean.class)).thenReturn(true);
		when( batchRepository.save(batch)).thenReturn(batch);
		assertThat(batch).isEqualTo(batchService.updateBatch(batch, 0));
	}
}
