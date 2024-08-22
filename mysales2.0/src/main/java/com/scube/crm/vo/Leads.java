
package com.scube.crm.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;




/**
 * @author Scube
 *
 */
@Entity
@Table(name = "leads")
public class Leads extends BasicEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long leadsId;
	private String firstName; 
	private String lastName;
	private String companyName;
	private String industryType;
	private String website;
	private String address;
	private String mobileNo;
	private String contactNo;
	private String emailAddress;
	private boolean isDelete;
	private Campaign campaignVO;
	private String status;

	private User user; 

	private boolean convertedCustomer;

	private User leadeOwner;



	/**
	 * @return the leadsId
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="leads_id")
	public long getLeadsId() {
		return leadsId;
	}
	/**
	 * @param leadsId the leadsId to set
	 */
	public void setLeadsId(long leadsId) {
		this.leadsId = leadsId;
	}

	/**
	 * @return the firstName
	 */
	@Column(name="first_name")
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	@Column(name="last_name")
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the companyName
	 */
	@Column(name="company_name")
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * @return the industryType
	 */
	@Column(name="industry_type")
	public String getIndustryType() {
		return industryType;
	}
	/**
	 * @param industryType the industryType to set
	 */
	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}
	/**
	 * @return the website
	 */
	@Column(name="website")
	public String getWebsite() {
		return website;
	}
	/**
	 * @param website the website to set
	 */
	public void setWebsite(String website) {
		this.website = website;
	}
	/**
	 * @return the address
	 */
	@Column(name="address")
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the mobileNo
	 */
	@Column(name="mobileNo")
	public String getMobileNo() {
		return mobileNo;
	}
	/**
	 * @param mobileNo the mobileNo to set
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	/**
	 * @return the contactNo
	 */
	@Column(name="contactNo")
	public String getContactNo() {
		return contactNo;
	}
	/**
	 * @param contactNo the contactNo to set
	 */
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	/**
	 * @return the emailAddress
	 */
	@Column(name="emailAddress")
	public String getEmailAddress() {
		return emailAddress;
	}
	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}


	@Column(name="isDelete")
	public boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}


	/*
	 * public Campaign getCampaignVO() { return campaign; } public void
	 * setCampaignVO(Campaign campaign) { this.campaign = campaign; }
	 */
	/**
	 * @return the user
	 */
	@Transient
	public User getAdminLoginVO() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setAdminLoginVO(User user) {
		this.user = user;
	}


	@ManyToOne
	@JoinColumn(name="lead_owner")
	public User getLeadeOwner() {
		return leadeOwner;
	}
	public void setLeadeOwner(User leadeOwner) {
		this.leadeOwner = leadeOwner;
	}


	@Column(name="converted_cusotmer")
	public boolean isConvertedCustomer() {
		return convertedCustomer;
	}
	public void setConvertedCustomer(boolean convertedCustomer) {
		this.convertedCustomer = convertedCustomer;
	}

	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	@OneToOne
	@JoinColumn(name="campaign_id")
	public Campaign getCampaignVO() {
		return campaignVO;
	}
	public void setCampaignVO(Campaign campaignVO) {
		this.campaignVO = campaignVO;
	}





}
