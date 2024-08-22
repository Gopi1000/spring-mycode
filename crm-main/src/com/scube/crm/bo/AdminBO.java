package com.scube.crm.bo;

import java.util.Date;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

//import javax.validation.constraints.NotEmpty;

public class AdminBO {
	
	private int adminId;
	
	@NotEmpty
	@Pattern(regexp = "^[a-zA-Z\\s]*$", message = "First Name Should be a Character")
	@Size(min = 3, max = 20)
	private String ClientName;
	
	@NotEmpty
	@Range(min = 1111111111, message = "Mobile Number must be a 10 Digits")
	private String PrimaryContactNumber;
	
	@NotEmpty
	@Range(min = 1111111111, message = "Mobile Number must be a 10 Digits")
    private String SecondaryContactNumber;
	
	@NotEmpty
	@Range(min = 1111111111, message = "Mobile Number must be a 10 Digits")
	private String FinalContactNumber;
	
	@NotEmpty
	private String PrimaryAddress;
	
	@NotEmpty
    private String SecondaryAddress;
	
	@NotEmpty
    private String PermanentAddress;
	
	private Date created = new Date();
	private Date modified = new Date();
	private int version;
	private Boolean isActive;
	private Boolean isDelete;
	
	
	
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getClientName() {
		return ClientName;
	}
	public void setClientName(String clientName) {
		ClientName = clientName;
	}
	public String getPrimaryContactNumber() {
		return PrimaryContactNumber;
	}
	public void setPrimaryContactNumber(String primaryContactNumber) {
		PrimaryContactNumber = primaryContactNumber;
	}
	public String getSecondaryContactNumber() {
		return SecondaryContactNumber;
	}
	public void setSecondaryContactNumber(String secondaryContactNumber) {
		SecondaryContactNumber = secondaryContactNumber;
	}
	public String getFinalContactNumber() {
		return FinalContactNumber;
	}
	public void setFinalContactNumber(String finalContactNumber) {
		FinalContactNumber = finalContactNumber;
	}
	public String getPrimaryAddress() {
		return PrimaryAddress;
	}
	public void setPrimaryAddress(String primaryAddress) {
		PrimaryAddress = primaryAddress;
	}
	public String getSecondaryAddress() {
		return SecondaryAddress;
	}
	public void setSecondaryAddress(String secondaryAddress) {
		SecondaryAddress = secondaryAddress;
	}
	public String getPermanentAddress() {
		return PermanentAddress;
	}
	public void setPermanentAddress(String permanentAddress) {
		PermanentAddress = permanentAddress;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getModified() {
		return modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Boolean getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * @param string
	 */
	public void setStatus(String string) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * @return
	 */
	public Object getErrorMessage() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * @param object
	 */
	public void setErrorMessage(Object object) {
		// TODO Auto-generated method stub
		
	}

	

	
	

}
