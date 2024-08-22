/**
 * 
 */
package com.scube.crm.bo;

import java.util.Date;

import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;



/**
 * @author arun
 *
 */
public class CampaignBO extends BaseBO {


	private static final long serialVersionUID = 1L;

	private Integer campaignId;
	@NotBlank(message="Campaign Name can not be empty")
	@Pattern(regexp="^[a-zA-Z\\s]*$",message="Campaign Name should be a character only")
	private String campaignName;

	@NotBlank(message="Campaign Mode can not be empty")
	private String campaignMode;
	@Transient
	@NotBlank(message="Campaign Owner can not be empty")
	private String campaignOwner;

	@NotNull(message = "Start Date can not be Empty")
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date startedTime;


	@NotNull(message = "End Date can not be Empty")
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date endTime;

	private String stDate;
	private String edDate;
	private int sNo;
	/*
	 * @NotBlank(message="Producte Name Mode can not be empty") private String
	 * producteName;
	 */
	
	private AdminLoginBO adminLoginBO;
	private ProductServiceBO productServiceBO;
	
	private String userName;
	private long userId;


	public Date getStartedTime() {
		return startedTime;
	}
	public void setStartedTime(Date startedTime) {
		this.startedTime = startedTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getCampaignId() {
		return campaignId;
	}
	public void setCampaignId(Integer campaignId) {
		this.campaignId = campaignId;
	}
	
	public String getCampaignName() {
		return campaignName;
	}
	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}
	
	public String getStDate() {
		return stDate;
	}
	public void setStDate(String stDate) {
		this.stDate = stDate;
	}
	public String getEdDate() {
		return edDate;
	}
	public void setEdDate(String edDate) {
		this.edDate = edDate;
	}

	/*
	 * public String getProducteName() { return producteName; } public void
	 * setProducteName(String producteName) { this.producteName = producteName; }
	 */
	public String getCampaignMode() {
		return campaignMode;
	}
	public void setCampaignMode(String campaignMode) {
		this.campaignMode = campaignMode;
	}
	@Transient
	public String getCampaignOwner() {
		return campaignOwner;
	}
	public void setCampaignOwner(String campaignOwner) {
		this.campaignOwner = campaignOwner;
	}
	public AdminLoginBO getAdminLoginBO() {
		return adminLoginBO;
	}
	public void setAdminLoginBO(AdminLoginBO adminLoginBO) {
		this.adminLoginBO = adminLoginBO;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
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
	public ProductServiceBO getProductServiceBO() {
		return productServiceBO;
	}
	public void setProductServiceBO(ProductServiceBO productServiceBO) {
		this.productServiceBO = productServiceBO;
	}



}
