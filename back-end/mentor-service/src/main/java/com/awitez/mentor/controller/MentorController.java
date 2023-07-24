package com.awitez.mentor.controller;

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

import com.awitez.mentor.entity.Mentor;
import com.awitez.mentor.payload.MentorResponse;
import com.awitez.mentor.service.MentorService;
@RestController
@RequestMapping("/mentor")
public class MentorController {

	@Autowired
	private MentorService mentorService;
	
	@PostMapping("/create")
	public Mentor createMentor(@RequestBody Mentor mentor)
	{
		return mentorService.createMentor(mentor);
	}
	
	@GetMapping("/mentors")
	public List<Mentor> getAllMentors(){
		return mentorService.getAllMentors();
	}
	
	@GetMapping("/mentors/{id}")
	public Mentor getMentor(@PathVariable long id) {
		return mentorService.getMentor(id);
	}
	@PutMapping("/update/{id}")
	public Mentor updateMentor(@RequestBody Mentor mentor,@PathVariable long id) {
		return mentorService.updateMentor(mentor, id);
	}
	@DeleteMapping("/delete/{id}")
	public void deleteMentor(@PathVariable long id) {
		mentorService.deleteMentor(id);
	}
	@GetMapping("/is-present/{id}")
	public boolean isPresent(@PathVariable long id) {
		System.out.println("In mentorService");
		return mentorService.isPresent(id);
	}
	@GetMapping("/get-mentor-details/{id}")
	public MentorResponse getMentorDetails(@PathVariable long id) {
		return mentorService.getMentorDetails(id);
	}
	@GetMapping("/get-mentors-by-branchId/{branchId}")
	public List<Mentor> getMentorsByBranchId(@PathVariable long branchId){
		return mentorService.getMentorsByBranchId(branchId);
	}
}
