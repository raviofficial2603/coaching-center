package com.awitez.mentor.payload;

import java.util.Objects;

public class MentorResponse {
	private long mentorId;
	private String firstName;
	private String lastName;
	private String fullName;
	private String qualification;
	private String skills;
	private long experience;
	private String email;
	private String mobileNumber;
	private String gender;
	private String address;
	private Branch branch;
	@Override
	public String toString() {
		return "MentorResponse [mentorId=" + mentorId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", fullName=" + fullName + ", qualification=" + qualification + ", skills=" + skills + ", experience="
				+ experience + ", email=" + email + ", mobileNumber=" + mobileNumber + ", gender=" + gender
				+ ", address=" + address + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(address, branch, email, experience, firstName, fullName, gender, lastName, mentorId,
				mobileNumber, qualification, skills);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MentorResponse other = (MentorResponse) obj;
		return Objects.equals(address, other.address) && Objects.equals(branch, other.branch)
				&& Objects.equals(email, other.email) && experience == other.experience
				&& Objects.equals(firstName, other.firstName) && Objects.equals(fullName, other.fullName)
				&& Objects.equals(gender, other.gender) && Objects.equals(lastName, other.lastName)
				&& mentorId == other.mentorId && Objects.equals(mobileNumber, other.mobileNumber)
				&& Objects.equals(qualification, other.qualification) && Objects.equals(skills, other.skills);
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
	public void setExperience(long l) {
		this.experience = l;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
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
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	
}
