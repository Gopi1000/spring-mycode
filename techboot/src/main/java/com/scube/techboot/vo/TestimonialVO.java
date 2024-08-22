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
@Table(name="testimonial")
public class TestimonialVO {
	
	private long testimonialId;
	private String name;
	private String titleName;
	private String message;
	private String imageName;
	private String profession;
	private boolean isDelete;
	private boolean sending_status;
	private Date createdTime;
	private Date modifiedTime;
	private long createdBy;
	private long modifiedBy;
	private int recordIndex;
	private int maxRecord;
	private int s_No;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	@Column(name="test_id")
	public long getTestimonialId() {
		return testimonialId;
	}
	public void setTestimonialId(long testimonialId) {
		this.testimonialId = testimonialId;
	}
	
	@Column(name="Name", nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="Title_Name", nullable=false)
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	@Column(name="imageName", nullable=false)
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	@Column(name="profession", nullable=false)
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	@Column(name="isDelete", nullable=false)
	public boolean getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	@Column(name="sending_status", nullable=false)
	public boolean getSending_status() {
		return sending_status;
	}
	public void setSending_status(boolean sending_status) {
		this.sending_status = sending_status;
	}
	@Column(name="message", nullable=false,columnDefinition="Text")
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
	@Column(name="createdTime" ,nullable=false)
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	@Column(name="modifiedTime")
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	@Column(name="createdBy",nullable=false)
	public long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}
	@Column(name="modifiedBy")
	public long getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	
	

}
