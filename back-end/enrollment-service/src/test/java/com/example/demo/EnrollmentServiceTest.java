package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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
import org.springframework.web.client.RestTemplate;

import com.awitez.enrollment.entity.Enrollment;
import com.awitez.enrollment.payload.EnrollmentResponse;
import com.awitez.enrollment.repository.EnrollmentRepository;
import com.awitez.enrollment.serviceimpl.EnrollmentServiceImpl;



class EnrollmentServiceTest {
	@Mock
	private RestTemplate restTemplate;
	@Mock
	private EnrollmentRepository enrollmentRepository;
	@InjectMocks 
	private EnrollmentServiceImpl enrollmentService;
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	@DisplayName("create enrollment")
	void createEnrollment() {
		Enrollment enrollment= new Enrollment(1l,1l,1l,1l,1l,1.1f,"rewajsd");
		when(restTemplate.getForObject("http://BATCH-SERVICE/batch/is-present/"+1,Boolean.class)).thenReturn(true);
		when(restTemplate.getForObject("http://BRANCH-SERVICE/branch/is-present/"+1,Boolean.class)).thenReturn(true);
		when(restTemplate.getForObject("http://CANDIDATE-SERVICE/candidate/is-present/"+1,Boolean.class)).thenReturn(true);
		when(restTemplate.getForObject("http://COURSE-SERVICE/course/is-present/"+1,Boolean.class)).thenReturn(true);
		when(enrollmentRepository.save(enrollment)).thenReturn(enrollment);
		assertThat(enrollment).isEqualTo(enrollmentService.createEnrollment(enrollment));
	}
	
	@Test
	@DisplayName("list of enrollments")
	void getAllMentors() {
		when(enrollmentRepository.findAll()).thenReturn(List.of(new Enrollment(),new Enrollment()));
		List<Enrollment> enrollments = enrollmentService.getAllEnrollments();
		assertThat(enrollments).isNotNull();
		assertThat(2).isEqualTo(enrollments.size());
		
	}
	@Test
	@DisplayName("get enrollment by id")
	void getEnrollmentById() {
		Enrollment enrollment= new Enrollment(1l,1l,1l,1l,1l,1.1f,"rewajsd");
		when( enrollmentRepository.findById(any())).thenReturn(Optional.of(enrollment));
		assertThat(enrollment).isEqualTo(enrollmentService.getEnrollment(0));
		
	}
	@Test
	@DisplayName("update enrollment by id")
	void updateEnrollment() {
		Enrollment enrollment= new Enrollment(1l,1l,1l,1l,1l,1.1f,"rewajsd");
		when(restTemplate.getForObject("http://BATCH-SERVICE/batch/is-present/"+1,Boolean.class)).thenReturn(true);
		when(restTemplate.getForObject("http://BRANCH-SERVICE/branch/is-present/"+1,Boolean.class)).thenReturn(true);
		when(restTemplate.getForObject("http://CANDIDATE-SERVICE/candidate/is-present/"+1,Boolean.class)).thenReturn(true);
		when(restTemplate.getForObject("http://COURSE-SERVICE/course/is-present/"+1,Boolean.class)).thenReturn(true);
		when(enrollmentRepository.save(enrollment)).thenReturn(enrollment);
		assertThat(enrollment).isEqualTo(enrollmentService.createEnrollment(enrollment));
	}
	@Test
	@DisplayName("delete enrollment by id")
	void deleteMentor() {
		Enrollment enrollment= new Enrollment(1l,1l,1l,1l,1l,1.1f,"rewajsd");
		
		when(enrollmentRepository.findById(any())).thenReturn(Optional.of(enrollment));
		enrollmentService.deleteEnrollment(0);
		verify(enrollmentRepository,times(1)).deleteById(0l);
	}
	@Test
	@DisplayName("check enrollment by id")
	void isPresent() {
		
		when( enrollmentRepository.findById(any())).thenReturn(Optional.of(new Enrollment()));
		assertThat(true).isEqualTo(enrollmentService.isPresent(0l));
	}
	@Test
	@DisplayName("check enrollment by candidate id")
	void getEnrollmentsByCandidateId() {
		when(enrollmentRepository.findAllByCandidateId(anyLong())).thenReturn(List.of(new Enrollment(),new Enrollment()));
		List<EnrollmentResponse> enrollments = enrollmentService.getEnrollmentsByCandidateId(0);
		assertThat(enrollments).isNotNull();
		assertThat(2).isEqualTo(enrollments.size());
		
	}
	@Test
	@DisplayName("check enrollment by branch id")
	void getEnrollmentsByBranchId() {
		when(enrollmentRepository.findAllByBranchId(anyLong())).thenReturn(List.of(new Enrollment(),new Enrollment()));
		List<EnrollmentResponse> enrollments = enrollmentService.getEnrollmentsByBranchId(0);
		assertThat(enrollments).isNotNull();
		assertThat(2).isEqualTo(enrollments.size());
		
	}
}
