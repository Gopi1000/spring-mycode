package com.scube.techboot.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="contact_details")
public class ContactVO {

	private int contactId;
	private String yourName;
	private String emailAddress;
	private Long contactNumber;
	private String message;
	private boolean isDelete;
	private boolean sending_status;
	private Date createdDate;
	private Date modifiedDate;
	private int createdBy;
	private int modifiedBy;
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="contact_id", unique=true)
	public int getContactId() {
		return contactId;
	}
	public void setContactId(int contactId) {
		this.contactId = contactId;
	}
	@Column(name="your_name", nullable=false)
	public String getYourName() {
		return yourName;
	}
	public void setYourName(String yourName) {
		this.yourName = yourName;
	}
	@Column(name="email_address", nullable=false)
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	@Column(name="message", nullable=false)
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
	@Column(name="contactNumber", nullable=false)
	public Long getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	
	
	

}
