package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
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

import com.awitez.mentor.entity.Mentor;
import com.awitez.mentor.payload.Branch;
import com.awitez.mentor.payload.MentorResponse;
import com.awitez.mentor.repository.MentorRepository;
import com.awitez.mentor.serviceimpl.MentorServiceImpl;

@SpringBootTest(classes= {MentorServiceTest.class})
class MentorServiceTest {

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Mock
	private MentorRepository mentorRepository;
	@Mock
	private RestTemplate restTemplate;
	@InjectMocks	
	private MentorServiceImpl mentorService;
	
	@Test
	@DisplayName("add mentor")
	void createMentor() {
		Mentor mentor=new Mentor();
		mentor.setAddress("guntur");
		mentor.setBranchId(1);
		mentor.setEmail("raviofficial@gmail.com");
		mentor.setExperience(5);
		mentor.setFirstName("asjd");
		mentor.setLastName("kasdf");
		mentor.setFullName("asjdfkasjdfkl");
		mentor.setGender("male");
		mentor.setMentorId(1);
		mentor.setMobileNumber("234567898765");
		mentor.setQualification("asjdfkjaswd");
		mentor.setSkills("jsdkksa");
		
		when(restTemplate.getForObject(anyString(),any())).thenReturn(true);
		when(mentorRepository.save(mentor)).thenReturn(mentor);
		assertThat(mentor).isEqualTo(mentorService.createMentor(mentor));
		
}
	@Test
	@DisplayName("list of mentors")
	void getAllMentors() {
		when(mentorRepository.findAll()).thenReturn(List.of(new Mentor(),new Mentor()));
		List<Mentor> mentors = mentorService.getAllMentors();
		assertThat(mentors).isNotNull();
		assertThat(2).isEqualTo(mentors.size());
		
	}
	
	@Test
	@DisplayName("get mentor by id")
	void getMentor() {
		Mentor mentor=new Mentor();
		mentor.setAddress("guntur");
		mentor.setBranchId(1);
		mentor.setEmail("raviofficial@gmail.com");
		mentor.setExperience(5);
		mentor.setFirstName("asjd");
		mentor.setLastName("kasdf");
		mentor.setFullName("asjdfkasjdfkl");
		mentor.setGender("male");
		mentor.setMentorId(1);
		mentor.setMobileNumber("234567898765");
		mentor.setQualification("asjdfkjaswd");
		mentor.setSkills("jsdkksa");
		when(mentorRepository.findById(any())).thenReturn(Optional.of(mentor));
		Mentor retrievedMentor=mentorService.getMentor(0);
		assertThat(mentor).isEqualTo(retrievedMentor);
	}
	@Test
	@DisplayName("update mentor by id")
	void updateMentor() {
		Mentor mentor=new Mentor();
		mentor.setAddress("guntur");
		mentor.setBranchId(1);
		mentor.setEmail("raviofficial@gmail.com");
		mentor.setExperience(5);
		mentor.setFirstName("asjd");
		mentor.setLastName("kasdf");
		mentor.setFullName("asjdfkasjdfkl");
		mentor.setGender("male");
		mentor.setMentorId(0);
		mentor.setMobileNumber("234567898765");
		mentor.setQualification("asjdfkjaswd");
		mentor.setSkills("jsdkksa");
		when(restTemplate.getForObject(anyString(),any())).thenReturn(true);
		when(mentorRepository.save(mentor)).thenReturn(mentor);
		when(mentorRepository.findById(any())).thenReturn(Optional.of(mentor));
		Mentor updatedMentor=mentorService.updateMentor(mentor, 0);	
		assertThat(mentor).isEqualTo(updatedMentor);
	}
	@Test
	@DisplayName("delete mentor by id")
	void deleteMentor() {
		Mentor mentor=new Mentor();
		
		when(mentorRepository.findById(any())).thenReturn(Optional.of(mentor));
		mentorService.deleteMentor(0);
		verify(mentorRepository,times(1)).deleteById(0l);
	}
	@Test
	@DisplayName("check mentor by id")
	void isPresent() {
		
		when( mentorRepository.findById(any())).thenReturn(Optional.of(new Mentor()));
		assertThat(true).isEqualTo(mentorService.isPresent(0l));
	}
	@Test
	@DisplayName("details of mentor by id")
	void getMentorDetails() {
		Branch branch = new Branch();
		when(restTemplate.getForObject(anyString(),any())).thenReturn(new Branch());
		MentorResponse mentorResponse=new MentorResponse();
		mentorResponse.setAddress("guntur");
		mentorResponse.setBranch(branch);
		mentorResponse.setEmail("raviofficial@gmail.com");
		mentorResponse.setExperience(5);
		mentorResponse.setFirstName("asjd");
		mentorResponse.setLastName("kasdf");
		mentorResponse.setFullName("asjdfkasjdfkl");
		mentorResponse.setGender("male");
		mentorResponse.setMentorId(0l);
		mentorResponse.setMobileNumber("234567898765");
		mentorResponse.setQualification("asjdfkjaswd");
		mentorResponse.setSkills("jsdkksa");


		Mentor mentor=new Mentor();
		mentor.setAddress("guntur");
		mentor.setBranchId(1);
		mentor.setEmail("raviofficial@gmail.com");
		mentor.setExperience(5);
		mentor.setFirstName("asjd");
		mentor.setLastName("kasdf");
		mentor.setFullName("asjdfkasjdfkl");
		mentor.setGender("male");
		mentor.setMentorId(1);
		mentor.setMobileNumber("234567898765");
		mentor.setQualification("asjdfkjaswd");
		mentor.setSkills("jsdkksa");
		
		when(mentorRepository.findById(any())).thenReturn(Optional.of(mentor));
		assertThat(mentorResponse).isEqualTo(mentorService.getMentorDetails(0l));
	}
	@Test
	@DisplayName("list of mentors by branch id")
	void getMentorsByBranchId() {
		when(restTemplate.getForObject(anyString(),any())).thenReturn(true);
		when(mentorRepository.findAllByBranchId(anyLong())).thenReturn(List.of(new Mentor(),new Mentor()));
		List<Mentor> mentors = mentorService.getMentorsByBranchId(0l);
		assertThat(mentors).isNotNull();
		assertThat(2).isEqualTo(mentors.size());
	}
	}
