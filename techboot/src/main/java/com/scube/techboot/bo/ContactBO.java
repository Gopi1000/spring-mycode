package com.scube.techboot.bo;

import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

public class ContactBO {
	
	private int contactId;

	@NotBlank(message="Your Name can not be empty")
	@Pattern(regexp="^[a-zA-Z\\s]*$",message="Your Name should be a character only")
	private String yourName;
	
	@NotBlank(message="Email Address can not be empty")
	@Email
	@Pattern(regexp = ".+@.+\\.[a-z]+", message = "Email Address is not correct format")
	private String emailAddress;
	
	@NotBlank(message="Message can not be empty")
	private String message;
	
	@NotNull(message = "Contact Number may not be empty")
	@Digits(integer = 10, fraction = 0, message = "Contact Number accept only 10 digits")
	@Range(min = 777777777, message = "contact Number is not valid")
	private Long contactNumber;
	
	private int createdBy;
	private int modifiedBy;
	private boolean isDelete;
	private boolean sending_status;
	private Date createdDate;
	private Date modifiedDate;
	
	
	
	public String getYourName() {
		return yourName;
	}
	public void setYourName(String yourName) {
		this.yourName = yourName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean getisDelete() {
		return isDelete;
	}
	public void setisDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	public boolean getSending_status() {
		return sending_status;
	}
	public void setSending_status(boolean sending_status) {
		this.sending_status = sending_status;
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
	public int getContactId() {
		return contactId;
	}
	public void setContactId(int contactId) {
		this.contactId = contactId;
	}
	public Long getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public int getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

}
