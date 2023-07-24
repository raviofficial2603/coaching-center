package com.awitez.mentor.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.validator.EmailValidator;

import com.awitez.mentor.exception.CustomNotFoundException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Mentor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long mentorId;
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String lastName;
	@Column(nullable = false)
	private String fullName;
	private String qualification;
	private String skills;
	private long experience;
	@Column(nullable = false)
	@Email 
	@NotEmpty
	@NotNull(message="{email.required}")
	@Pattern(regexp = "[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
	        + "[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
	        + "(?:[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?\\.)+[A-Za-z0-9]"
	        + "(?:[A-Za-z0-9-]*[A-Za-z0-9])?",
	        message = "{invalid.email}")
	private String email;
	public Mentor() {
		
	}
	public Mentor(long mentorId, String firstName, String lastName, String fullName, String qualification,
			String skills, int experience, String email, String mobileNumber, String gender, String address,
			long branchId) {
		super();
		this.mentorId = mentorId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.fullName = fullName;
		this.qualification = qualification;
		this.skills = skills;
		this.experience = experience;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.gender = gender;
		this.address = address;
		this.branchId = branchId;
	}
	@Column(nullable = false)
	private String mobileNumber;
	@Column(nullable = false)
	private String gender;
	private String address;
	private long branchId;
	@Override
	public String toString() {
		return "Mentor [mentorId=" + mentorId + ", firstName=" + firstName + ", lastName=" + lastName + ", fullName="
				+ fullName + ", qualification=" + qualification + ", skills=" + skills + ", experience=" + experience
				+ ", email=" + email + ", mobileNumber=" + mobileNumber + ", gender=" + gender + ", address=" + address
				+ ", branchId=" + branchId + "]";
	} 
	public long getMentorId() {
		return mentorId;
	}
	public void setMentorId(long mentorId) {
		this.mentorId = mentorId;
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
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	public long getExperience() {
		return experience;
	}
	public void setExperience(long experience) {
		this.experience = experience;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
//		EmailValidator ev = EmailValidator.getInstance();
//		if(ev.isValid(email))
//		else 
//		throw new CustomNotFoundException("email not valid");
		this.email = email;
	}
	public String getMobileNumber() {
		return mobileNumber; 
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
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
	public long getBranchId() {
		return branchId;
	}
	public void setBranchId(long branchId) {
		this.branchId = branchId;
	}
}
