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
@Table(name="course_category")
public class courseCategoryVO {

	private int courseCategoryId;
	private String courseCategoryName;
	private Boolean isDelete;
	private int createdBy;
	private int modifiedBy;
	private Date createdDate;
	private Date modifiedDate;
	
	private int recordIndex;
	
	private int maxRecord;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="courseCategoryId")
	public int getCourseCategoryId() {
		return courseCategoryId;
	}
	public void setCourseCategoryId(int courseCategoryId) {
		this.courseCategoryId = courseCategoryId;
	}
	@Column(name="courseCategoryName")
	public String getCourseCategoryName() {
		return courseCategoryName;
	}
	public void setCourseCategoryName(String courseCategoryName) {
		this.courseCategoryName = courseCategoryName;
	}
	
	
	
	@Column(name="createdBy")
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	
	@Column(name="modifiedBy")
	public int getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	@Column(name="createdDate")
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	@Column(name="modifiedDate")
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	@Column(name="isDelete")
	public Boolean getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
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

}
