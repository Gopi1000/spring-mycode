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
@Table(name="RecentNews")
public class RecentNewsVO {
	
	private int newsId;
	private String titleName;
	private String descriptions;
	private String imageName;
	private Date date;
	private boolean isDelete;
	private boolean sending_status;
	private Date createdTime;
	private Date modifiedTime;
	private long createdBy;
	private long modifiedBy;
	private int recordIndex;
	private int maxRecord;
	private int s_No;
	private String authorName;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="news_id")
	public int getNewsId() {
		return newsId;
	}
	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}
    @Column(name="titlename", nullable=false)
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	@Column(name="descriptions", nullable=false)
	public String getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}
	@Column(name="imageName",nullable=false)
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	@Column(name="isDelete", nullable=false)
	public boolean getisDelete() {
		return isDelete;
	}
	public void setisDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	@Column(name="sending_status", nullable=false)
	public boolean getSending_status() {
		return sending_status;
	}
	public void setSending_status(boolean sending_status) {
		this.sending_status = sending_status;
	}
	@Column(name="createdTime")
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
    @Column(name="date",nullable=false)
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Column(name="authorname", nullable=false)
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

}
