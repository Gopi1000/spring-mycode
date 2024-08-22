package com.scube.techboot.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="newsletters")
public class NewslettersVO {
	
	private int newsLettersId;
	private String emailAddress;
	private Date createdDate;
	private Date modifiedDate;
	private boolean isDelete;
	private boolean sendingStatus;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="letters_id")
	public int getNewsLettersId() {
		return newsLettersId;
	}
	public void setNewsLettersId(int newsLettersId) {
		this.newsLettersId = newsLettersId;
	}
	@Column(name="emailAddress")
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	@Column(name="created_date",nullable=false,columnDefinition="DATETIME")
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	@Column(name="modified_date")
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	@Column(name="isDelete")
	public boolean getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	@Column(name="sending_status")
	public boolean getSendingStatus() {
		return sendingStatus;
	}
	public void setSendingStatus(boolean sendingStatus) {
		this.sendingStatus = sendingStatus;
	}

}
