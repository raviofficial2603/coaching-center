package com.awitez.branch.controller;

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
import org.springframework.web.client.RestTemplate;

import com.awitez.branch.entity.Branch;
import com.awitez.branch.service.BranchService;

@RestController
@RequestMapping("/branch")
public class BranchController {
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private BranchService branchService;
	@GetMapping("/")
	public String responder() {
		
		String s = restTemplate.getForObject("http://MENTOR-SERVICE/", String.class);
		return "From Venue controller"+s;
	}
	@GetMapping("/branches")
	public List<Branch> getAllBranches(){
		return branchService.getAllBranches();
//		return branchRepository.findAll();
	}
	@GetMapping("/branches/{id}")
	public Branch getBranch(@PathVariable long id) {
		return branchService.getBranch(id);
//		return branchRepository.findById(id).get();
	}
	@PostMapping("/create")
	public Branch createBranch(@RequestBody Branch branch) {
		return branchService.createBranch(branch);
//		return branchRepository.save(branch);
	}
	@PutMapping("/update/{id}")
	public Branch updateBranch(@RequestBody Branch branch,@PathVariable long id) {
//		Branch updatedBranch = branchRepository.findById(id).get();
//		updatedBranch.setLocation(branch.getLocation());
//		updatedBranch.setMobileNumber(branch.getMobileNumber());
//		return branchRepository.save(updatedBranch);
		return branchService.updateBranch(branch, id);
	}
	@DeleteMapping("/delete/{id}")
	public void deleteBranch(@PathVariable long id) {
//		System.out.println("in batchcontroller"+id);
//		branchRepository.deleteById(id);
		branchService.deleteBranch(id);
	}
	@GetMapping("/is-present/{id}")
	public boolean isPresent(@PathVariable long id) {
		System.out.println(id+"in BranchController");
		return branchService.isPresent(id);
	}
}
