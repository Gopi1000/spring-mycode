package com.scube.techboot.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name ="company")
public class CompanyVO extends BasicEntity{


	private int companyId;
	private String companyName;
	private String companyPersonName;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String conformEmailAddress;
	private String password;
	private String conformPassword;
	private String companyWebSite;
	private String companyType;
	private Long contectNumber;
	private String address;
	/*private Boolean isDelete;
	private Boolean sending_status;
	private Date createdTime;
	private Date modifiedTime;
	private int version;
	private long createdBy;
	private long modifiedBy;*/
	private String imageName;
	@Transient
	private int s_No;
	@Transient
	private int recordIndex;
	@Transient
	private int maxRecord;

	private String industry;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="companyId")
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	
	@Column(name="companyName",nullable=false,length=50)
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	@Column(name="companyPersonName",nullable=false,length=50)
	public String getCompanyPersonName() {
		return companyPersonName;
	}
	public void setCompanyPersonName(String companyPersonName) {
		this.companyPersonName = companyPersonName;
	}
	
	@Column(name="firstName",nullable=false,length=50)
	public String getfirstName() {
		return firstName;
	}
	public void setfirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Column(name="lastName",nullable=false,length=50)
	public String getlastName() {
		return lastName;
	}
	public void setlastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name="emailAddress",nullable=false,length=50)
	public String getemailAddress() {
		return emailAddress;
	}
	public void setemailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	@Column(name="conformEmailAddress",nullable=false,length=50)
	public String getConformEmailAddress() {
		return conformEmailAddress;
	}
	public void setConformEmailAddress(String conformEmailAddress) {
		this.conformEmailAddress = conformEmailAddress;
	}
	
	@Column(name="password",nullable=false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name="conformPassword",nullable=false)
	public String getConformPassword() {
		return conformPassword;
	}
	public void setConformPassword(String conformPassword) {
		this.conformPassword = conformPassword;
	}
	
	@Column(name="companyWebSite",nullable=false)
	public String getCompanyWebSite() {
		return companyWebSite;
	}
	public void setCompanyWebSite(String companyWebSite) {
		this.companyWebSite = companyWebSite;
	}
	
	@Column(name="companyType",nullable=false)
	public String getCompanyType() {
		return companyType;
	}
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	
	@Column(name="contectNumber",nullable=false,length=10,columnDefinition="BIGINT")
	public Long getContectNumber() {
		return contectNumber;
	}
	public void setContectNumber(Long contectNumber) {
		this.contectNumber = contectNumber;
	}
	
	@Column(name="address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	/*
	@Column(name="isDelete",columnDefinition="TINYINT")
	public Boolean getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	
	@Column(name="sending_status",columnDefinition="TINYINT")
	public Boolean getSending_status() {
		return sending_status;
	}
	public void setSending_status(Boolean sending_status) {
		this.sending_status = sending_status;
	}


	@Column(name="created_time",columnDefinition="DATETIME")
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Column(name="modified_time",columnDefinition="DATETIME")
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	@Column(name="version",columnDefinition="INTEGER")
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}*/
	@Column(name="industry",nullable=false)
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	/*
	@Column(name="createdBy",columnDefinition="BIGINT")
	public long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name="modifiedBy",columnDefinition="BIGINT")
	public long getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}*/
	
	@Column(name="imageName")
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	/*@Transient
	public int getS_No() {
		return s_No;
	}
	public void setS_No(int s_No) {
		this.s_No = s_No;
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
	}*/
	
	
}
