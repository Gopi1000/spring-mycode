package com.scube.mycognitiv.bo;

import java.util.List;

public class CompanyRegistrationBO extends BaseBO{

	private static final long serialVersionUID = 1L;
	private int companyRegId;
	private String companyName;
	private String companyEmail;
	private Long companyContactNo;
	private String companyLocation;
	private String companyWebsite;
	private String password;
	private String userType;
	private int serialNo;
	private List<CompanyRegistrationBO> companyList;
	
	public int getCompanyRegId() {
		return companyRegId;
	}
	public void setCompanyRegId(int companyRegId) {
		this.companyRegId = companyRegId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyEmail() {
		return companyEmail;
	}
	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}
	public Long getCompanyContactNo() {
		return companyContactNo;
	}
	public void setCompanyContactNo(Long companyContactNo) {
		this.companyContactNo = companyContactNo;
	}
	public String getCompanyLocation() {
		return companyLocation;
	}
	public void setCompanyLocation(String companyLocation) {
		this.companyLocation = companyLocation;
	}
	public String getCompanyWebsite() {
		return companyWebsite;
	}
	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public int getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}
	public List<CompanyRegistrationBO> getCompanyList() {
		return companyList;
	}
	public void setCompanyList(List<CompanyRegistrationBO> companyList) {
		this.companyList = companyList;
	}
	
	
}
