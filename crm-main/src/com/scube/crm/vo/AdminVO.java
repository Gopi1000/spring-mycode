package com.scube.crm.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customer_login")
public class AdminVO extends BasicEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="admin_Id")
    private int adminId;
	
	@Column(name="Client_Name")
	private String ClientName;
	
	@Column(name="Primary_Contact_Number")
	private String PrimaryContactNumber;
	
	@Column(name="Secondary_Contact_Number")
	private String SecondaryContactNumber;
	
	@Column(name="Final_Contact_Number")
	private String FinalContactNumber;
	
	@Column(name="Primary_Address")
	private String PrimaryAddress;
	
	@Column(name="Secondary_Address")
    private String SecondaryAddress;
	
	@Column(name="Permanent_Address")
    private String PermanentAddress;
	
	@Column(name="isActive")
	private boolean isActive;
	
	@Column(name="isDelete")
	private boolean isDelete;
	
	
	
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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	
	
	
	
	

	
}
