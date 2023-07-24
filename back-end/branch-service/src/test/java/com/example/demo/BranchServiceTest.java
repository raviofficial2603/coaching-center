package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.awitez.branch.entity.Branch;
import com.awitez.branch.repository.BranchRepository;
import com.awitez.branch.serviceimpl.BranchServiceImpl;

@SpringBootTest(classes = {BranchServiceTest.class})
class BranchServiceTest {

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}
	@Mock
	private BranchRepository branchRepository;
	@Mock
	private RestTemplate restTemplate;
	@InjectMocks
	private BranchServiceImpl branchService;
	@Test
	@DisplayName("get all branches")
	void getAllBranches() {
		when(branchRepository.findAll()).thenReturn(List.of(new Branch(),new Branch()));
		List<Branch> branches=branchService.getAllBranches();
		assertThat(branches).isNotNull();
		assertThat(2).isEqualTo(branches.size());
		
	}
	@Test
	@DisplayName("get branch by id")
	void getBranch() {
		Branch branch= new Branch();
		when(branchRepository.findById(any())).thenReturn(Optional.of(branch));
		assertThat(branch).isEqualTo(branchService.getBranch(0));
	}
	@Test
	@DisplayName("update branch by id")
	void updateBranch() {
		Branch branch= new Branch();
		when(branchRepository.findById(any())).thenReturn(Optional.of(branch));
		when(branchRepository.save(branch)).thenReturn(branch);
		assertThat(branch).isEqualTo(branchService.updateBranch(branch, 0));
	}
	@Test
	@DisplayName("delete branch by id")
	void deleteBranch() {
		Branch branch= new Branch();
		when(branchRepository.findById(any())).thenReturn(Optional.of(branch));
		branchService.deleteBranch(0l);
		verify(branchRepository,times(1)).deleteById(0l);
	}
	@Test
	@DisplayName("is present")
	void isPresent() {
		Branch branch= new Branch();
		when(branchRepository.findById(any())).thenReturn(Optional.of(branch));
		assertThat(true).isEqualTo(branchService.isPresent(0l));
	}
}
