package com.scube.techboot.vo;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="course_details")
public class CourseDetailsVO extends BasicEntity{

	private int courseDetailsId;
	private  String description;
	private String designed;
	private String curriculum;
	private String classesSchedule;
	private String keyfeatures;
	private float price;
/*	private Date createdTime;
	private Date modifiedTime;
	private boolean isDelete;
	private long createdBy;
	private long modifiedBy;
	private boolean sending_status;*/

	private int recordIndex;

	private int maxRecord;

	private CourseVO courseVO;
	private CompanyVO companyVO;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="course_detailsId")
	public int getCourseDetailsId() {
		return courseDetailsId;
	}
	public void setCourseDetailsId(int courseDetailsId) {
		this.courseDetailsId = courseDetailsId;
	}

	@Column(name="description",columnDefinition="Text")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="designed",columnDefinition="Text")
	public String getDesigned() {
		return designed;
	}
	public void setDesigned(String designed) {
		this.designed = designed;
	}
	@Column(name="curriculum",columnDefinition="Text")
	public String getCurriculum() {
		return curriculum;
	}
	public void setCurriculum(String curriculum) {
		this.curriculum = curriculum;
	}
	@Column(name="classes_schedule",columnDefinition="Text")
	public String getClassesSchedule() {
		return classesSchedule;
	}
	public void setClassesSchedule(String classesSchedule) {
		this.classesSchedule = classesSchedule;
	}
	@Column(name="keyfeatures",columnDefinition="Text")
	public String getKeyfeatures() {
		return keyfeatures;
	}
	public void setKeyfeatures(String keyfeatures) {
		this.keyfeatures = keyfeatures;
	}
	/*@Column(name="price")
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}*/
	/*@Column(name="createdTime")
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
	@Column(name="isDelete")
	public boolean getisDelete() {
		return isDelete;
	}
	public void setisDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	@Column(name="createdBy")
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
	}*/

	/*@Column(name="sending_status")
	public boolean getSending_status() {
		return sending_status;
	}
	public void setSending_status(boolean sending_status) {
		this.sending_status = sending_status;
	}*/
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
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
}
