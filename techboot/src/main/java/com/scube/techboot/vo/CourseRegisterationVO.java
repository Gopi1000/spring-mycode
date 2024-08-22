package com.scube.techboot.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name="course_register")
public class CourseRegisterationVO {

	private long courseRegisterId;
	private String candidateName;
	private String emailAddress;
	private Long mobileNumber;
	private String professional;
	private  String scheme;
	private Date createdTime;
	private Date modifiedTime;
	private boolean isDelete;
	private boolean isActive;
	private int recordIndex;
	private int maxRecord;
	private int s_No;
	private CourseVO courseVO;
	private CompanyVO companyVO;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="coursereg_id",unique=true)
	public long getCourseRegisterId() {
		return courseRegisterId;
	}
	public void setCourseRegisterId(long courseRegisterId) {
		this.courseRegisterId = courseRegisterId;
	}
	@Column(name="candidatename",nullable=false,length=45)
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	@Column(name="emailaddress",nullable=false)
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	@Column(name="mobileNumber",nullable=false,length=10)
	public Long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	@Column(name="professional",nullable=false)
	public String getProfessional() {
		return professional;
	}
	public void setProfessional(String professional) {
		this.professional = professional;
	}
	@Column(name="scheme",nullable=false)
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
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
	@Column(name="isDelete",nullable=false)
	public boolean getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	@Column(name="isActive",nullable=false)
	public boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	@Transient
	public int getRecordIndex() {
		return recordIndex;
	}
	public void setRecordIndex(int recordIndex) {
		this.recordIndex = recordIndex;
	}
	@Transient
	public int getMaxRecord() {
		return maxRecord;
	}
	public void setMaxRecord(int maxRecord) {
		this.maxRecord = maxRecord;
	}
	@Transient
	public int getS_No() {
		return s_No;
	}
	public void setS_No(int s_No) {
		this.s_No = s_No;
	}
	@OneToOne
	@JoinColumn(name="course_id")
	public CourseVO getCourseVO() {
		return courseVO;
	}
	public void setCourseVO(CourseVO courseVO) {
		this.courseVO = courseVO;
	}
	@OneToOne
	@JoinColumn(name="company_id")
	public CompanyVO getCompanyVO() {
		return companyVO;
	}
	public void setCompanyVO(CompanyVO companyVO) {
		this.companyVO = companyVO;
	}
	
}
