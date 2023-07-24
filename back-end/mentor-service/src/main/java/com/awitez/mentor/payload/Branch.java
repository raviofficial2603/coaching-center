package com.awitez.mentor.payload;

import java.util.Objects;

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
	@Override
	public int hashCode() {
		return Objects.hash(branchId, location, mobileNumber);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Branch other = (Branch) obj;
		return branchId == other.branchId && Objects.equals(location, other.location)
				&& Objects.equals(mobileNumber, other.mobileNumber);
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
