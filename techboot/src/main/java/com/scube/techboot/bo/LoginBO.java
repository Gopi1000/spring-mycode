package com.scube.techboot.bo;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

public class LoginBO extends BaseBo {

	private long loginId;
	@NotBlank(message="emailAddress Name may not be empty")
	@Pattern(regexp = ".+@.+\\.[a-z]+", message = "emailAddress is not correct format")
	private String emailAddress;
	
	@NotBlank(message="password Name may not be empty")
	private String password;
	

	@NotBlank(message="password Name may not be empty")
	private String confirmPassword;
	
	
	

	//@NotBlank(message="userType Name may not be empty")

	private String userType;
	private Boolean isActive;
	
	
	private CompanyBO companyBO;
	
	private StudentRegisterBO  StudentRegisterBO;
	
	@NotBlank(message="userType Name may not be empty")
	private String name;
	
	@NotNull(message = "Mobile Number may not be empty")
	@Digits(integer = 10, fraction = 0, message = "Mobile Number accept only 10 digits")
	@Range(min = 777777777, message = "Mobile Number is not valid")
	private Long mobileNo;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	public CompanyBO getCompanyBO() {
		return companyBO;
	}
	public void setCompanyBO(CompanyBO companyBO) {
		this.companyBO = companyBO;
	}
	public long getLoginId() {
		return loginId;
	}
	public void setLoginId(long loginId) {
		this.loginId = loginId;
	}
	public StudentRegisterBO getStudentRegisterBO() {
		return StudentRegisterBO;
	}
	public void setStudentRegisterBO(StudentRegisterBO studentRegisterBO) {
		StudentRegisterBO = studentRegisterBO;
	}
	
	
}
