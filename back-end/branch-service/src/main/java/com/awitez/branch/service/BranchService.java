package com.awitez.branch.service;

import java.util.List;

import com.awitez.branch.entity.Branch;

public interface BranchService {

	public List<Branch> getAllBranches();
	public Branch getBranch(long id);
	public Branch createBranch(Branch branch);
	public Branch updateBranch(Branch branch,long id);
	public void deleteBranch(long id);
	public boolean isPresent(long id); 
	
	
}
