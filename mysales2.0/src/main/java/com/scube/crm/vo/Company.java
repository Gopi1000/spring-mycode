package com.scube.crm.vo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "company")
public class Company extends BasicEntity {

	private static final long serialVersionUID = 1L;
	private long companiesId;
	private String companiesName;
	private boolean isActiveInvitation = false;
	private String email;
	private boolean isDeleted = false;
	private Long contactNo;
	private Long mobileNo;
	/*private Blob companyLogo;*/
	private String companyProfile;
	private int rank;
	private boolean adminChecked = false;
	private String imageName;
	

	/**
	 * @return the email
	 */
	@Column(name = "email_address")
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the companiesId
	 */
	@Id
	@GeneratedValue
	public long getCompaniesId() {
		return companiesId;
	}

	/**
	 * @param companiesId
	 *            the companiesId to set
	 */
	public void setCompaniesId(long companiesId) {
		this.companiesId = companiesId;
	}

	/**
	 * @return the companiesName
	 */
	/**
	 * @return the companiesName
	 */
	@Column(name = "companies_name")
	public String getCompaniesName() {
		return companiesName;
	}

	/**
	 * @param companiesName
	 *            the companiesName to set
	 */
	public void setCompaniesName(String companiesName) {
		this.companiesName = companiesName;
	}

	@Column(name = "is_active_invitation")
	public boolean getIsActiveInvitation() {
		return isActiveInvitation;
	}

	/**
	 * @param isActiveInvitation
	 *            the isActiveInvitation to set
	 */
	public void setIsActiveInvitation(boolean isActiveInvitation) {
		this.isActiveInvitation = isActiveInvitation;
	}

	/**
	 * @return the isDeleted
	 */
	public boolean getIsDeleted() {
		return isDeleted;
	}

	/**
	 * @param isDeleted the isDeleted to set
	 */
	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	/**
	 * @return the contactNo
	 */
	@Column(name = "contact_no")
	public Long getContactNo() {
		return contactNo;
	}

	/**
	 * @param contactNo the contactNo to set
	 */
	public void setContactNo(Long contactNo) {
		this.contactNo = contactNo;
	}

	/**
	 * @return the mobileNo
	 */
	@Column(name = "mobile_no")
	public Long getMobileNo() {
		return mobileNo;
	}

	/**
	 * @param mobileNo the mobileNo to set
	 */
	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}

	/**
	 * @return the companyLogo
	 */
	/*@Column(name = "company_logo")
	public Blob getCompanyLogo() {
		return companyLogo;
	}

	*//**
	 * @param companyLogo the companyLogo to set
	 *//*
	public void setCompanyLogo(Blob companyLogo) {
		this.companyLogo = companyLogo;
	}*/

	/**
	 * @return the companyProfile
	 */
	@Column(name = "company_profile", columnDefinition = "TEXT")
	public String getCompanyProfile() {
		return companyProfile;
	}

	/**
	 * @param companyProfile the companyProfile to set
	 */
	public void setCompanyProfile(String companyProfile) {
		this.companyProfile = companyProfile;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Column(name = "adminChecked")
	public boolean isAdminChecked() {
		return adminChecked;
	}

	public void setAdminChecked(boolean adminChecked) {
		this.adminChecked = adminChecked;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}	
}
