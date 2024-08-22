/**
 * 
 */
package com.scube.crm.bo;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.scube.crm.vo.LeadsFollowup;

public class LeadsBO extends BaseBO{

	private static final long serialVersionUID = -2769612946293091952L;

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
	private boolean isActive;
	
	private String userName;
	private long userId;
	private long adminId;
	private int sNo;
	private AdminLoginBO adminLoginBO;
	private boolean convertedCustomer;
	private List<LeadsBO> leadsList;
	private List<LeadsFollowup> leadsUpdateVOList;
	private long followupId;
	private String date;
	private String description;
	private Long followupCount;
	 private CampaignBO campaignBO;
	 private String nextAppointmentDate;
	 private String createdDate;
	 private String modifiedDate;
	 @DateTimeFormat(pattern = "MM/dd/yyyy")
		private Date startDate ;
		@DateTimeFormat(pattern = "MM/dd/yyyy")
		private Date endDate;
		private String process;
	
	public long getLeadsId() {
		return leadsId;
	}

	public void setLeadsId(long leadsId) {
		this.leadsId = leadsId;
	}

	public CampaignBO getCampaignBO() {
		return campaignBO;
	}

	public void setCampaignBO(CampaignBO campaignBO) {
		this.campaignBO = campaignBO;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getIndustryType() {
		return industryType;
	}

	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * @return the adminLoginBO
	 */
	public AdminLoginBO getAdminLoginBO() {
		return adminLoginBO;
	}

	/**
	 * @param adminLoginBO the adminLoginBO to set
	 */
	public void setAdminLoginBO(AdminLoginBO adminLoginBO) {
		this.adminLoginBO = adminLoginBO;
	}

	/**
	 * @return the sNo
	 */
	public int getsNo() {
		return sNo;
	}

	/**
	 * @param sNo the sNo to set
	 */
	public void setsNo(int sNo) {
		this.sNo = sNo;
	}

	public boolean isConvertedCustomer() {
		return convertedCustomer;
	}

	public void setConvertedCustomer(boolean convertedCustomer) {
		this.convertedCustomer = convertedCustomer;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public List<LeadsBO> getLeadsList() {
		return leadsList;
	}

	public void setLeadsList(List<LeadsBO> leadsList) {
		this.leadsList = leadsList;
	}

	public List<LeadsFollowup> getLeadsUpdateVOList() {
		return leadsUpdateVOList;
	}

	public void setLeadsUpdateVOList(List<LeadsFollowup> leadsUpdateVOList) {
		this.leadsUpdateVOList = leadsUpdateVOList;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getAdminId() {
		return adminId;
	}

	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}

	public Long getFollowupCount() {
		return followupCount;
	}

	public void setFollowupCount(Long followupCount) {
		this.followupCount = followupCount;
	}

	public String getNextAppointmentDate() {
		return nextAppointmentDate;
	}

	public void setNextAppointmentDate(String nextAppointmentDate) {
		this.nextAppointmentDate = nextAppointmentDate;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public long getFollowupId() {
		return followupId;
	}

	public void setFollowupId(long followupId) {
		this.followupId = followupId;
	}

}
