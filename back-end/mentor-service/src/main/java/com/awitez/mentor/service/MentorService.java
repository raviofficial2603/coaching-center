package com.awitez.mentor.service;

import java.util.List;

import com.awitez.mentor.entity.Mentor;
import com.awitez.mentor.payload.MentorResponse;


public interface MentorService {
	public Mentor createMentor(Mentor mentor);
	public java.util.List<Mentor> getAllMentors();
	public Mentor getMentor(long id);
	public Mentor updateMentor(Mentor mentor, long id);
	public void deleteMentor(long id);
	public boolean isPresent(long id);
	public MentorResponse getMentorDetails(long id);
	public List<Mentor> getMentorsByBranchId(long branchId);
}
