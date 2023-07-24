package com.awitez.enrollment.payload;

public class Branch {

	private long branchId;
	private String location;
	private String mobileNumber;
	@Override
	public String toString() {
		return "Branch [branchId=" + branchId + ", location=" + location + ", mobileNumber=" + mobileNumber + "]";
	}
	public long getBranchId() {
		return branchId;
	}
	public void setBranchId(long branchId) {
		this.branchId = branchId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
}
