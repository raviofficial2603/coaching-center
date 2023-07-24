package com.awitez.branch.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.awitez.branch.entity.Branch;
import com.awitez.branch.exception.BranchNotFoundException;
import com.awitez.branch.repository.BranchRepository;
import com.awitez.branch.service.BranchService;
@Service
public class BranchServiceImpl implements BranchService{

	@Autowired
	private BranchRepository branchRepository;
	@Autowired 
	private RestTemplate restTemplate;
	@Override
	public List<Branch> getAllBranches() {
		return branchRepository.findAll();
	}

	@Override
	public Branch getBranch(long id) {
		return branchRepository.findById(id).orElseThrow(()->new BranchNotFoundException("Branch not found with id:"+id));
	}

	@Override
	public Branch createBranch(Branch branch) {
		return branchRepository.save(branch);
	}

	@Override
	public Branch updateBranch(Branch branch, long id) {
		
		Branch updatedBranch=this.getBranch(id);
		updatedBranch.setLocation(branch.getLocation());
		updatedBranch.setMobileNumber(branch.getMobileNumber());
		return branchRepository.save(branch);
	}

	@Override
	public void deleteBranch(long id) {
		this.getBranch(id);
		restTemplate.delete("http://COURSE-SERVICE/course/delete-branch-course/"+id);
		branchRepository.deleteById(id); 
		
	}

	@Override
	public boolean isPresent(long id) {
		Optional<Branch> branch = branchRepository.findById(id);
		return branch.isPresent();
	}



}
