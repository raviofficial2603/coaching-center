package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.awitez.admin.payload.Batch;
import com.awitez.admin.payload.Branch;
import com.awitez.admin.payload.Candidate;
import com.awitez.admin.payload.Course;
import com.awitez.admin.payload.Enrollment;
import com.awitez.admin.payload.Mentor;
import com.awitez.admin.serviceimpl.AdminServiceImpl;
@SpringBootTest(classes = AdminServiceTests.class)
class AdminServiceTests {

	@Mock
	private RestTemplate restTemplate;
	
	@InjectMocks
	private AdminServiceImpl adminService;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void  createBatch() {
		Batch batch = new Batch();
		when(restTemplate.getForObject("http://MENTOR-SERVICE/mentor/is-present/"+0l,Boolean.class)).thenReturn(true);
		when(restTemplate.getForObject("http://BRANCH-SERVICE/branch/is-present/"+0l,Boolean.class)).thenReturn(true);
		when(restTemplate.getForObject("http://COURSE-SERVICE/course/is-present/"+0l,Boolean.class)).thenReturn(true);
		when(restTemplate.postForObject("http://BATCH-SERVICE/batch/create",batch , Batch.class)).thenReturn(batch);
		assertThat(batch).isEqualTo(adminService.createBatch(batch));		
		}
	@Test
	public void getAllBatches() {
		List list = new ArrayList<>();
		when(restTemplate.getForObject("http://BATCH-SERVICE/batch/batches", List.class)).thenReturn(list);
		assertThat(list).isEqualTo(adminService.getAllBatches());
	}
	@Test
	public void getBatch() {
		Batch batch = new Batch();
		when(restTemplate.getForObject("http://BATCH-SERVICE/batch/is-present/"+0l, Boolean.class)).thenReturn(true);
		when(restTemplate.getForObject("http://BATCH-SERVICE/batch/batches/"+0l,Batch.class)).thenReturn(batch);
		assertThat(batch).isEqualTo(adminService.getBatch(0));
	}
	@Test
	public void updateBatch() {
		when(restTemplate.getForObject("http://BATCH-SERVICE/batch/is-present//"+0l, Boolean.class)).thenReturn(true);
		when(restTemplate.getForObject("http://MENTOR-SERVICE/mentor/is-present/"+0l,Boolean.class)).thenReturn(true);
		when(restTemplate.getForObject("http://BRANCH-SERVICE/branch/is-present/"+0l,Boolean.class)).thenReturn(true);
		when(restTemplate.getForObject("http://COURSE-SERVICE/course/is-present/"+0l,Boolean.class)).thenReturn(true);
		Batch batch = new Batch();
		adminService.updateBatch(batch, 0l);
		verify(restTemplate,times(1)).put("http://BATCH-SERVICE/batch/update/"+0l, batch,Batch.class);
	}
	@Test
	public void deleteBatch() {
		when(restTemplate.getForObject("http://BATCH-SERVICE/batch/is-present/"+0l, Boolean.class)).thenReturn(true);
		adminService.deleteBatch(0);
		verify(restTemplate,times(1)).delete("http://BATCH-SERVICE/batch/delete/"+0l);
	}
	@Test
	public void getAllBranches() {
		ArrayList list = new  ArrayList<>();
		when(restTemplate.getForObject("http://BRANCH-SERVICE/branch/branches", List.class)).thenReturn(list);
		assertThat(list).isEqualTo(adminService.getAllBranches());
	}
	@Test
	public void getBranch() {
		Branch branch = new Branch();
		when(restTemplate.getForObject("http://BRANCH-SERVICE/branch/is-present/"+0l, Boolean.class)).thenReturn(true);
		when(restTemplate.getForObject("http://BRANCH-SERVICE/branch/branches/"+0l,Branch.class)).thenReturn(branch);
		assertThat(branch).isEqualTo(adminService.getBranch(0));
	}
	@Test
	public void createBranch() {
		Branch branch = new Branch();
		when(restTemplate.postForObject("http://BRANCH-SERVICE/branch/create", branch, Branch.class)).thenReturn(branch);
		assertThat(branch).isEqualTo(adminService.createBranch(branch));
		
	}
	@Test
	public void updateBranch() {
		Branch branch = new Branch();
		when(restTemplate.getForObject("http://BRANCH-SERVICE/branch/is-present/"+0l, Boolean.class)).thenReturn(true);
		adminService.updateBranch(branch, 0);
		verify(restTemplate,times(1)).put("http://BRANCH-SERVICE/branch/update/"+0l, branch, Branch.class);
	}
	@Test
	public void deleteBranch() {
		when(restTemplate.getForObject("http://BRANCH-SERVICE/branch/is-present/"+0l, Boolean.class)).thenReturn(true);
		adminService.deleteBranch(0);
		verify(restTemplate,times(1)).delete("http://BRANCH-SERVICE/branch/delete/"+0);
	}
	@Test
	public void getAllCandidates() {
		ArrayList<Object> list = new ArrayList<>();
		when(restTemplate.getForObject("http://CANDIDATE-SERVICE/candidate/candidates", List.class)).thenReturn(list);
		assertThat(list).isEqualTo(adminService.getAllCandidates());
	}
	@Test
	public void getCandidate() {
		Candidate candidate = new Candidate();
		when(restTemplate.getForObject("http://CANDIDATE-SERVICE/candidate/is-present/"+0l, Boolean.class)).thenReturn(true);
		when(restTemplate.getForObject("http://CANDIDATE-SERVICE/candidate/candidates/"+0l,Candidate.class)).thenReturn(candidate);
		adminService.getCandidate(0);
		assertThat(candidate).isEqualTo(adminService.getCandidate(0l));
	}
	@Test
	public void deleteCandidate() {
		when(restTemplate.getForObject("http://CANDIDATE-SERVICE/candidate/is-present/"+0l, Boolean.class)).thenReturn(true);
		adminService.deleteCandidate(0);
		verify(restTemplate, times(1)).delete("http://CANDIDATE-SERVICE/candidate/delete/"+0l);
	}
	@Test
	public void getAllCourses() {
		ArrayList<Object> list = new ArrayList<>();
		when(restTemplate.getForObject("http://COURSE-SERVICE/course/courses", List.class)).thenReturn(list);
		assertThat(list).isEqualTo(adminService.getAllCourses());
	}
	@Test
	public void getCourse() {
		Course course = new Course(); 
		when(restTemplate.getForObject("http://COURSE-SERVICE/course/is-present/"+0l, Boolean.class)).thenReturn(true);
		when(restTemplate.getForObject("http://COURSE-SERVICE/course/courses/"+0l,Course.class)).thenReturn(course);
		assertThat(course).isEqualTo(adminService.getCourse(0));
	}
	@Test
	public void createCourse() {
		Course course = new Course();
		when(restTemplate.postForObject("http://COURSE-SERVICE/course/create", course, Course.class)).thenReturn(course);
		assertThat(course).isEqualTo(adminService.createCourse(course));
	}
	@Test
	public void updateCourse() {
		Course course = new Course();
		when(restTemplate.getForObject("http://COURSE-SERVICE/course/is-present/"+0l, Boolean.class)).thenReturn(true);
		adminService.updateCourse(course, 0);
		verify(restTemplate,times(1)).put("http://COURSE-SERVICE/course/update/"+0l, course, Course.class);
	}
	@Test
	public void deleteCourse() {
		when(restTemplate.getForObject("http://COURSE-SERVICE/course/is-present/"+0l, Boolean.class)).thenReturn(true);
		adminService.deleteCourse(0);
		verify(restTemplate,times(1)).delete("http://COURSE-SERVICE/course/delete/"+0l);
	}
	@Test
	public void getAllEnrollments() {
		List list=new ArrayList<>();
		when(restTemplate.getForObject("http://ENROLLMENT-SERVICE/enrollment/enrollments", List.class)).thenReturn(list);
		assertThat(list).isEqualTo(adminService.getAllEnrollments());
	}
	@Test
	public void getEnrollment() {
		Enrollment enrollment = new Enrollment();
		when(restTemplate.getForObject("http://ENROLLMENT-SERVICE/enrollment/is-present/"+0l, Boolean.class)).thenReturn(true);
		when(restTemplate.getForObject("http://ENROLLMENT-SERVICE/enrollment/enrollments/"+0l,Enrollment.class)).thenReturn(enrollment);
		assertThat(enrollment).isEqualTo(adminService.getEnrollment(0));
	}
	@Test
	public void updateEnrollment() {
		Enrollment enrollment = new Enrollment();
		when(restTemplate.getForObject("http://ENROLLMENT-SERVICE/enrollment/is-present/"+0l, Boolean.class)).thenReturn(true);
		when(restTemplate.getForObject("http://BATCH-SERVICE/batch/is-present/"+0l,Boolean.class)).thenReturn(true);
		when(restTemplate.getForObject("http://BRANCH-SERVICE/branch/is-present/"+0l,Boolean.class)).thenReturn(true);
		when(restTemplate.getForObject("http://CANDIDATE-SERVICE/candidate/is-present/"+0l,Boolean.class)).thenReturn(true);
		when(restTemplate.getForObject("http://COURSE-SERVICE/course/is-present/"+0l,Boolean.class)).thenReturn(true);
		adminService.updateEnrollment(enrollment, 0);
		verify(restTemplate,times(1)).put("http://ENROLLMENT-SERVICE/enrollment/update/"+0l, enrollment, Enrollment.class);
	}
	@Test
	public void deleteEnrollment() {
		when(restTemplate.getForObject("http://ENROLLMENT-SERVICE/enrollment/is-present/"+0l, Boolean.class)).thenReturn(true);
		adminService.deleteEnrollment(0);
		verify(restTemplate,times(1)).delete("http://ENROLLMENT-SERVICE/enrollment/delete/"+0l);
		
	}
	@Test
	public void createMentor() {
		Mentor mentor = new Mentor();
		when(restTemplate.postForObject("http://MENTOR-SERVICE/mentor/create", mentor, Mentor.class)).thenReturn(mentor);
		assertThat(mentor).isEqualTo(adminService.createMentor(mentor));
	}
	@Test
	public void getAllMentors() {
		List list=new ArrayList<>();
		when(restTemplate.getForObject("http://MENTOR-SERVICE/mentor/mentors", List.class)).thenReturn(list);
		assertThat(list).isEqualTo(adminService.getAllMentors());
	}
	@Test
	public void getMentor() {
		Mentor mentor = new Mentor();
	
		when(restTemplate.getForObject("http://MENTOR-SERVICE/mentor/is-present/"+0l, Boolean.class)).thenReturn(true);
		when(restTemplate.getForObject("http://MENTOR-SERVICE/mentor/mentors/"+0l,Mentor.class)).thenReturn(mentor);
		assertThat(mentor).isEqualTo(adminService.getMentor(0));
	}
	@Test
	public void updateMentor() {
		Mentor mentor = new Mentor();
		mentor.setBranchId(0l);
		when(restTemplate.getForObject("http://MENTOR-SERVICE/mentor/is-present/"+0l, Boolean.class)).thenReturn(true);
		when(restTemplate.getForObject("http://BRANCH-SERVICE/branch/is-present/"+0l,Boolean.class)).thenReturn(true);
		adminService.updateMentor(mentor, 0);
		verify(restTemplate,times(1)).put("http://MENTOR-SERVICE/mentor/update/"+0l, mentor, Mentor.class);
		
	}
	@Test
	public void deleteMentor() {
		when(restTemplate.getForObject("http://MENTOR-SERVICE/mentor/is-present/"+0l, Boolean.class)).thenReturn(true);
		adminService.deleteMentor(0);
		verify(restTemplate,times(1)).delete("http://MENTOR-SERVICE/mentor/delete/"+0l);
	}
	
}
