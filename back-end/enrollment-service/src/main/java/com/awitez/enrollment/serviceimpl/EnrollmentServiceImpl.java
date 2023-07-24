package com.awitez.enrollment.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.awitez.enrollment.entity.Enrollment;
import com.awitez.enrollment.exception.CustomNotFoundException;
import com.awitez.enrollment.exception.EnrollmentNotFoundException;
import com.awitez.enrollment.payload.Batch;
import com.awitez.enrollment.payload.Branch;
import com.awitez.enrollment.payload.Course;
import com.awitez.enrollment.payload.EnrollmentResponse;
import com.awitez.enrollment.repository.EnrollmentRepository;
import com.awitez.enrollment.service.EnrollmentService;
@Service
public class EnrollmentServiceImpl implements EnrollmentService {

	@Autowired
	private EnrollmentRepository enrollmentRepository;
	@Autowired
	private RestTemplate restTemplate;
	@Override
	public Enrollment createEnrollment(Enrollment enrollment) {
		long batchId = enrollment.getBatchId();
		long branchId = enrollment.getBranchId();
		long candidateId = enrollment.getCandidateId();
		long courseId = enrollment.getCourseId();
		
		if(!restTemplate.getForObject("http://BRANCH-SERVICE/branch/is-present/"+branchId,Boolean.class)) {
			throw new CustomNotFoundException("Branch not found with id:"+branchId);
		}
		else if(!restTemplate.getForObject("http://CANDIDATE-SERVICE/candidate/is-present/"+candidateId,Boolean.class)) {
			throw new CustomNotFoundException("Candidate not found with id:"+candidateId);
		}
		else if(!restTemplate.getForObject("http://COURSE-SERVICE/course/is-present/"+courseId,Boolean.class)) {
			throw new CustomNotFoundException("Course not found with id:"+courseId);
		}
		else
		return enrollmentRepository.save(enrollment);
	}

	@Override
	public List<Enrollment> getAllEnrollments() {
		return enrollmentRepository.findAll();
	}

	@Override
	public Enrollment getEnrollment(long id) {
		return enrollmentRepository.findById(id).orElseThrow(()->new EnrollmentNotFoundException("Enrollment not found with id:"+id));
	}

	@Override
	public Enrollment updateEnrollment(Enrollment enrollment, long id) {
		Enrollment updatedEnrollment=this.getEnrollment(id);
		long batchId = enrollment.getBatchId();
		long branchId = enrollment.getBranchId();
		long candidateId = enrollment.getCandidateId();
		long courseId = enrollment.getCourseId();
		
		if(!restTemplate.getForObject("http://BATCH-SERVICE/batch/is-present/"+batchId,Boolean.class)) {
			throw new CustomNotFoundException("Batch not found with id:"+batchId);
		}
		else if(!restTemplate.getForObject("http://BRANCH-SERVICE/branch/is-present/"+branchId,Boolean.class)) {
			throw new CustomNotFoundException("Branch not found with id:"+branchId);
		}
		else if(!restTemplate.getForObject("http://CANDIDATE-SERVICE/candidate/is-present/"+candidateId,Boolean.class)) {
			throw new CustomNotFoundException("Candidate not found with id:"+candidateId);
		}
		else if(!restTemplate.getForObject("http://COURSE-SERVICE/course/is-present/"+courseId,Boolean.class)) {
			throw new CustomNotFoundException("Course not found with id:"+courseId);
		}
	
		updatedEnrollment.setBatchId(enrollment.getBatchId());
		updatedEnrollment.setBranchId(enrollment.getBranchId());
		updatedEnrollment.setCandidateId(enrollment.getCandidateId());
		updatedEnrollment.setCourseId(enrollment.getCourseId());

		updatedEnrollment.setEnrollmentStatus(enrollment.getEnrollmentStatus());
		updatedEnrollment.setRating(enrollment.getRating());
		
		return enrollmentRepository.save(updatedEnrollment);
	}

	@Override
	public void deleteEnrollment(long id) {
		this.getEnrollment(id);
		enrollmentRepository.deleteById(id);

	}

	@Override
	public boolean isPresent(long id) {
		Optional<Enrollment> enrollment = enrollmentRepository.findById(id);
		return enrollment.isPresent();
	}

	@Override
	public List<EnrollmentResponse> getEnrollmentsByCandidateId(long candidateId) {		// TODO Auto-generated method stub
		List<Enrollment> listOfEnrollments = enrollmentRepository.findAllByCandidateId(candidateId);
		return listOfEnrollments.stream().map(enrollment->{
			EnrollmentResponse enrollmentResponse=new EnrollmentResponse();
			enrollmentResponse.setId(enrollment.getEnrollmentId());
			if(restTemplate.getForObject("http://BATCH-SERVICE/batch/is-present/"+enrollment.getBatchId(), Boolean.class)) {
				System.out.println(enrollment.getBatchId());
				
				enrollmentResponse.setBatch(restTemplate.getForObject("http://BATCH-SERVICE/batch/batches/"+enrollment.getBatchId(), Batch.class));
			}
			enrollmentResponse.setBranch(restTemplate.getForObject("http://BRANCH-SERVICE/branch/branches/"+enrollment.getBranchId(), Branch.class));
			enrollmentResponse.setCourse(restTemplate.getForObject("http://COURSE-SERVICE/course/courses/"+enrollment.getCourseId(), Course.class));
			return enrollmentResponse;
		}).collect(Collectors.toList());
	}
	@Override
	public List<EnrollmentResponse> getEnrollmentsByBranchId(long branchId) {		// TODO Auto-generated method stub
		List<Enrollment> listOfEnrollments = enrollmentRepository.findAllByBranchId(branchId);
		EnrollmentResponse enrollmentResponse=new EnrollmentResponse();
		return listOfEnrollments.stream().map(enrollment->{
			enrollmentResponse.setId(enrollment.getEnrollmentId());
			enrollmentResponse.setBatch(restTemplate.getForObject("http://BATCH-SERVICE/batch/batches/"+enrollment.getBatchId(), Batch.class));
			enrollmentResponse.setBranch(restTemplate.getForObject("http://BRANCH-SERVICE/branch/branches/"+enrollment.getBranchId(), Branch.class));
			enrollmentResponse.setCourse(restTemplate.getForObject("http://COURSE-SERVICE/course/courses/"+enrollment.getCourseId(), Course.class));
			return enrollmentResponse;
		}).collect(Collectors.toList());
		
	}
	@Override
	public List<EnrollmentResponse> getEnrollmentsByCourseId(long courseId) {		// TODO Auto-generated method stub
		List<Enrollment> listOfEnrollments = enrollmentRepository.findAllByCourseId(courseId);
		EnrollmentResponse enrollmentResponse=new EnrollmentResponse();
		return listOfEnrollments.stream().map(enrollment->{
			enrollmentResponse.setId(enrollment.getEnrollmentId());
			enrollmentResponse.setBatch(restTemplate.getForObject("http://BATCH-SERVICE/batch/batches/"+enrollment.getBatchId(), Batch.class));
			enrollmentResponse.setBranch(restTemplate.getForObject("http://BRANCH-SERVICE/branch/branches/"+enrollment.getBranchId(), Branch.class));
			enrollmentResponse.setCourse(restTemplate.getForObject("http://COURSE-SERVICE/course/courses/"+enrollment.getCourseId(), Course.class));
			return enrollmentResponse;
		}).collect(Collectors.toList());
		
		
	}

}
