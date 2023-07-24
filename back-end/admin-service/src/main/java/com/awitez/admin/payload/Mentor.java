package com.awitez.admin.payload;

public class Mentor {
	private long mentorId;
	private String firstName;
	private String lastName;
	private String fullName;
	private String qualification;
	private String skills;
	private int experience;
	private String email;
	private String mobileNumber;
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
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
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
	public long getBranchId() {
		return branchId;
	}
	public void setBranchId(long branchId) {
		this.branchId = branchId;
	}
}
