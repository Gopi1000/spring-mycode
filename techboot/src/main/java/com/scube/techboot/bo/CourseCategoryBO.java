package com.scube.techboot.bo;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

public class CourseCategoryBO extends BaseBo{
	
private int courseCategoryId;
	
	@NotBlank(message="Course Name may not be empty")
	private String courseCategoryName;
	private Boolean isDelete;
	private int createdBy;
	private int modifiedBy;
	private Date createdDate;
	private Date modifiedDate;
	private int recordIndex;
	private int maxRecord;
	private String pagination = null;
	
	public long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public boolean getIsDelete() {
		return isDelete;
	}

	
	public int getCourseCategoryId() {
		return courseCategoryId;
	}

	public void setCourseCategoryId(int courseCategoryId) {
		this.courseCategoryId = courseCategoryId;
	}

	public String getCourseCategoryName() {
		return courseCategoryName;
	}

	public void setCourseCategoryName(String courseCategoryName) {
		this.courseCategoryName = courseCategoryName;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public int getRecordIndex() {
		return recordIndex;
	}

	public void setRecordIndex(int recordIndex) {
		this.recordIndex = recordIndex;
	}

	public int getMaxRecord() {
		return maxRecord;
	}

	public void setMaxRecord(int maxRecord) {
		this.maxRecord = maxRecord;
	}

	public String getPagination() {
		return pagination;
	}

	public void setPagination(String pagination) {
		this.pagination = pagination;
	}

	
	

}
