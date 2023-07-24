package com.awitez.mentor.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.awitez.mentor.entity.Mentor;
import com.awitez.mentor.exception.CustomNotFoundException;
import com.awitez.mentor.exception.MentorNotFoundException;
import com.awitez.mentor.payload.Branch;
import com.awitez.mentor.payload.MentorResponse;
import com.awitez.mentor.repository.MentorRepository;
import com.awitez.mentor.service.MentorService;
@Service
public class MentorServiceImpl implements MentorService {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private MentorRepository mentorRepository;
	@Override
	public Mentor createMentor(Mentor mentor) {

		return mentorRepository.save(mentor);
		
	}

	@Override
	public List<Mentor> getAllMentors() {
		return mentorRepository.findAll();
	}

	@Override
	public Mentor getMentor(long id) {
		return mentorRepository.findById(id).orElseThrow(()->new MentorNotFoundException("Mentor not found with id:"+id));
	}

	@Override
	public Mentor updateMentor(Mentor mentor, long id) {
		Mentor updatedMentor=mentorRepository.findById(id).get();
		updatedMentor.setEmail(mentor.getEmail());
		updatedMentor.setExperience(mentor.getExperience());
		updatedMentor.setFirstName(mentor.getFirstName());
		updatedMentor.setFullName(mentor.getFullName());
		updatedMentor.setLastName(mentor.getLastName());
		updatedMentor.setMobileNumber(mentor.getMobileNumber());;
		updatedMentor.setQualification(mentor.getQualification());
		updatedMentor.setSkills(mentor.getSkills());
		if(!restTemplate.getForObject("http://BRANCH-SERVICE/branch/is-present/"+mentor.getBranchId(),Boolean.class)) {
			throw new CustomNotFoundException("Branch not found with id:"+mentor.getBranchId());
		}
		updatedMentor.setBranchId(mentor.getBranchId());
		return mentorRepository.save(updatedMentor);
		
	}

	@Override
	public void deleteMentor(long id) {
		this.getMentor(id);
		mentorRepository.deleteById(id);
		
	}

	@Override
	public boolean isPresent(long id) {
		Optional<Mentor> mentor = mentorRepository.findById(id);
		System.out.println(""+mentor.isPresent()+id);
		return mentor.isPresent();
	}

	@Override
	public MentorResponse getMentorDetails(long id) {
		Mentor mentor=this.getMentor(id);
		Branch branch=restTemplate.getForObject("http://BRANCH-SERVICE/branch/branches/"+mentor.getBranchId(), Branch.class);
		MentorResponse mentorResponse=new MentorResponse();
		mentorResponse.setMentorId(id);
		mentorResponse.setAddress(mentor.getAddress());
		mentorResponse.setBranch(branch);
		mentorResponse.setEmail(mentor.getEmail());
		mentorResponse.setExperience(mentor.getExperience());
		mentorResponse.setFirstName(mentor.getFirstName());
		mentorResponse.setFullName(mentor.getFullName());
		mentorResponse.setGender(mentor.getGender());
		mentorResponse.setLastName(mentor.getLastName());
		mentorResponse.setMobileNumber(mentor.getMobileNumber());
		mentorResponse.setQualification(mentor.getQualification());
		mentorResponse.setSkills(mentor.getSkills());
		
		
		
		return mentorResponse;
	}

	@Override
	public List<Mentor> getMentorsByBranchId(long branchId) {
		if(restTemplate.getForObject("http://BRANCH-SERVICE/branch/is-present/"+branchId, Boolean.class)) {
			
			return mentorRepository.findAllByBranchId(branchId);
		}
		else
			throw new CustomNotFoundException("Branch not found with id:"+branchId);
	}
	
	

}
