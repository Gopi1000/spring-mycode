package com.scube.techboot.bo;

import java.util.Date;

public class NewslettersBO {

	private int newsLettersId;
	private String emailAddress;
	private Date createdDate;
	private Date modifiedDate;
	private boolean isDelete;
	private boolean sendingStatus;
	
	
	public int getNewsLettersId() {
		return newsLettersId;
	}
	public void setNewsLettersId(int newsLettersId) {
		this.newsLettersId = newsLettersId;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
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
	public void setIsDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	public boolean getSendingStatus() {
		return sendingStatus;
	}
	public void setSendingStatus(boolean sendingStatus) {
		this.sendingStatus = sendingStatus;
	}
}
