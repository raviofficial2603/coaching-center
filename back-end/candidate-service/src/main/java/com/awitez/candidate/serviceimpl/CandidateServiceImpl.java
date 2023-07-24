package com.awitez.candidate.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.awitez.candidate.entity.Candidate;
import com.awitez.candidate.exception.CandidateNotFoundException;
import com.awitez.candidate.payload.AuthRequest;
import com.awitez.candidate.repository.CandidateRepository;
import com.awitez.candidate.service.CandidateService;
import com.awitez.entity.UserInfo;
import org.springframework
.security
.crypto
.bcrypt
.BCrypt;
@Service
public class CandidateServiceImpl implements CandidateService {
	public static String Password_Hash(
	        String password)
	    {
	        return BCrypt.hashpw(
	            password, BCrypt.gensalt());
	    }
	public static boolean Verify_Password(
	        String password,
	        String hashed_password)
	    {
	        return BCrypt.checkpw(
	            password, hashed_password);
	    }
	@Autowired
	private CandidateRepository candidateRepository;
	@Autowired
    private PasswordEncoder passwordEncoder;
	@Autowired
	private RestTemplate restTemplate;
	@Override
	public Candidate createCandidate(Candidate candidate) {
		candidate.setPassword(Password_Hash(candidate.getPassword()));
		return candidateRepository.save(candidate);
	}
	@Override
	public List<Candidate> getAllCandidates() {
		return candidateRepository.findAll();
		
	}
	@Override
	public Candidate getCandidate(long id) {
		Candidate candidate= candidateRepository.findById(id).orElseThrow(()->new CandidateNotFoundException("Candidate not found with id:"+id));
		candidate.setEnrollmentResponses(restTemplate.getForObject("http://ENROLLMENT-SERVICE/enrollment/get-enrollment-by-candidateId/"+id, List.class));
		return candidate; 
		
	}
	@Override
	public Candidate updateCandidate(Candidate candidate ,long id) {
		Candidate updatedCandidate = candidateRepository.findById(id).get();
		updatedCandidate.setFirstName(candidate.getFirstName());
		updatedCandidate.setLastName(candidate.getLastName());
		updatedCandidate.setFullName(candidate.getFullName());
		updatedCandidate.setEmail(candidate.getAddress());
		updatedCandidate.setGender(candidate.getGender());
		updatedCandidate.setAddress(candidate.getAddress());
		updatedCandidate.setMobileNumber(candidate.getMobileNumber());
		return candidateRepository.save(updatedCandidate);
	}
	@Override
	public void deleteCandidate(long id) {
		this.getCandidate(id);
		candidateRepository.deleteById(id);
	}
	@Override
	public boolean isPresent(long id) {
		Optional<Candidate> candidate = candidateRepository.findById(id);
		return candidate.isPresent();
	}
	@Override
	public String authUser(AuthRequest authRequest) { 
		System.out.println(authRequest.getUsername());
		Candidate candidate = candidateRepository.findByEmail(authRequest.getUsername());
//		System.out.println(candidate.getPassword());
		if(candidate==null)
			return "failed";
		System.out.println(passwordEncoder.encode(authRequest.getPassword()));
		if(Verify_Password(authRequest.getPassword(),candidate.getPassword()))
			return ""+candidate.getCandidateId();
		return "failed";
		
	}
	@Override
	public boolean isEmailExist(String email) {
		// TODO Auto-generated method stub
		Candidate candidate = candidateRepository.findByEmail(email);
		if(candidate==null)
			return false;
		else
		return true;
	}
	

}
