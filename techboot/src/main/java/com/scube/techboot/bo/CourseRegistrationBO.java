package com.scube.techboot.bo;

import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

public class CourseRegistrationBO {

	private long courseRegisterId;
	
	@NotBlank(message="Candidate Name may not be empty")
	@Pattern(regexp="^[a-zA-Z0-9-\\.\\s]*$", message = "Enter the Candidate Name valid format")
	private String candidateName;
	
	@NotBlank(message="Email Address can not be empty")
	@Email
	@Pattern(regexp = ".+@.+\\.[a-z]+", message = "Email Address is not correct format")
	private String emailAddress;
	
	@NotNull(message = "Mobile Number may not be empty")
	@Digits(integer = 10, fraction = 0, message = "Mobile Number accept only 10 digits")
	@Range(min = 777777777, message = "Mobile Number is not valid")
	private Long mobileNumber;
	
	@NotBlank(message="Profession can not be empty")
	@Pattern(regexp="^[a-zA-Z.\\s]*$", message = "Enter the Professional valid format")
	private String professional;
	private  String scheme;
	private Date createdTime;
	private Date modifiedTime;
	private boolean isDelete;
	private boolean isActive;
	private int recordIndex;
	private int maxRecord;
	private int s_No;
	
	private CourseBO courseBO;
	private CompanyBO companyBO;
	
	public long getCourseRegisterId() {
		return courseRegisterId;
	}
	public void setCourseRegisterId(long courseRegisterId) {
		this.courseRegisterId = courseRegisterId;
	}
	
	public String getcandidateName() {
		return candidateName;
	}
	public void setcandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public Long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getProfessional() {
		return professional;
	}
	public void setProfessional(String professional) {
		this.professional = professional;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public boolean isDelete() {
		return isDelete;
	}
	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	public int getRecordIndex() {
		return recordIndex;
	}
	public void setRecordIndex(int recordIndex) {
		this.recordIndex = recordIndex;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public int getMaxRecord() {
		return maxRecord;
	}
	public void setMaxRecord(int maxRecord) {
		this.maxRecord = maxRecord;
	}
	public int getS_No() {
		return s_No;
	}
	public void setS_No(int s_No) {
		this.s_No = s_No;
	}
	public CourseBO getCourseBO() {
		return courseBO;
	}
	public void setCourseBO(CourseBO courseBO) {
		this.courseBO = courseBO;
	}
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	public CompanyBO getCompanyBO() {
		return companyBO;
	}
	public void setCompanyBO(CompanyBO companyBO) {
		this.companyBO = companyBO;
	}
}
