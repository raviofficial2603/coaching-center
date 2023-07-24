package com.awitez.candidate.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.awitez.candidate.payload.EnrollmentResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Candidate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long candidateId;
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String lastName;
	@Column(nullable = false)
	private String fullName;
	@Column(nullable = false)
	private String mobileNumber;
	@Column(unique=true,nullable = false)
	private String email;
	private String password;
	@Column(nullable = false)
	private String gender;
	private String address;
	
	@javax.persistence.Transient
	private List<EnrollmentResponse> enrollmentResponses;
	@Override
	public String toString() {
		return "Candidate [candidateId=" + candidateId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", fullName=" + fullName + ", mobileNumber=" + mobileNumber + ", email=" + email + ", Gender="
				+ gender + ", Address=" + address + "]";
	}
	public long getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(long candidateId) {
		this.candidateId = candidateId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<EnrollmentResponse> getEnrollmentResponses() {
		return enrollmentResponses;
	}
	public void setEnrollmentResponses(List<EnrollmentResponse> enrollmentResponses) {
		this.enrollmentResponses = enrollmentResponses;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	} 
}
