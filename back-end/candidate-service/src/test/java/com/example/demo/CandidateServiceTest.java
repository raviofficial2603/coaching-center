package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.timeout;
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

import com.awitez.candidate.entity.Candidate;
import com.awitez.candidate.repository.CandidateRepository;
import com.awitez.candidate.serviceimpl.CandidateServiceImpl;
@SpringBootTest(classes = {CandidateServiceTest.class})
class CandidateServiceTest {

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}
	@Mock
	private RestTemplate restTemplate;
	@Mock
	private CandidateRepository candidateRepository;
	@InjectMocks
	private CandidateServiceImpl candidateService;
	@Test
	@DisplayName("create candidate")
	void createCandidate() {
		Candidate candidate=new Candidate();
		candidate.setPassword("ooo");
		when(candidateRepository.save(candidate)).thenReturn(candidate);
		assertThat(candidate).isEqualTo(candidateService.createCandidate(candidate));
	}
	
	@Test
	@DisplayName("get all candidates")
	void getAllCandidates() {
		when(candidateRepository.findAll()).thenReturn(List.of(new Candidate(),new Candidate()));
		List<Candidate> candidates=candidateService.getAllCandidates();
		assertThat(candidates).isNotNull();
		assertThat(2).isEqualTo(candidates.size());
		
	}
	@Test
	@DisplayName("get candidate by id")
	void getCandidate() {
		Candidate candidate = new Candidate();
		when(candidateRepository.findById(0l)).thenReturn(Optional.of(candidate));
		when(restTemplate.getForObject("http://ENROLLMENT-SERVICE/enrollment/getEnrollmentByCandidateId/"+0l, List.class)).thenReturn(List.of());
		assertThat(candidate).isEqualTo(candidateService.getCandidate(0l));		
	}
	@Test
	@DisplayName("update candidate by id")
	void updateCandidate() {
		Candidate candidate = new Candidate();
		when(candidateRepository.save(candidate)).thenReturn(candidate);
		when(candidateRepository.findById(any())).thenReturn(Optional.of(candidate));
		assertThat(candidate).isEqualTo(candidateService.updateCandidate(candidate, 0));
	}
	@Test
	@DisplayName("delete candidate by id")
	void deleteCandidate() {
		Candidate candidate = new Candidate();
		when(candidateRepository.findById(any())).thenReturn(Optional.of(candidate));
		candidateService.deleteCandidate(0);
		verify(candidateRepository,times(1)).deleteById(0l);
	}
	@Test
	@DisplayName("check candidate by id")
	void isPresent() {
		Candidate candidate = new Candidate();
		when(candidateRepository.findById(any())).thenReturn(Optional.of(candidate));
		assertThat(true).isEqualTo(candidateService.isPresent(0l));
	}
}
