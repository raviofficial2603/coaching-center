package com.awitez.candidate.payload;

public class Candidate {

	private long candidateId;
	private String firstName;
	private String lastName;
	private String fullName;
	private String mobileNumber;
	private String email;
	private String gender;
	private String address;
	@Override
	public String toString() {
		return "Candidate [candidateId=" + candidateId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", fullName=" + fullName + ", mobileNumber=" + mobileNumber + ", email=" + email + ", gender="
				+ gender + ", address=" + address + "]";
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
}
