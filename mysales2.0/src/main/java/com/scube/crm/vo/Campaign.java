/**
 * 
 */
package com.scube.crm.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;



/**
 * @author arun
 *
 */
@Entity
@Table(name="campaign")
public class Campaign extends BasicEntity {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int campaignId;
	private String campaignName;
	private String campaignMode;
	
	private Date startedTime;
	private Date endTime;
	
	//private String productName;
	private boolean isDelete;
	
	private String campaignOwner;
	
	private User user;
	
	private ProductServiceVO productServiceVO;

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="campaignId")
	public int getCampaignId() {
		return campaignId;
	}
	public void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
	}
	@Column(name="campaign_Name" , nullable=false, length=50)
	public String getCampaignName() {
		return campaignName;
	}
	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}
	
	
	@Column(name="started_Time" , nullable=false ,columnDefinition="DATETIME")
	public Date getStartedTime() {
		return startedTime;
	}
	public void setStartedTime(Date startedTime) {
		this.startedTime = startedTime;
	}
	@Column(name="end_Time" , nullable=false ,columnDefinition="DATETIME")
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	
	
	
	
	@Column(name="isDelete")
	public boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	
	/*
	 * @Column(name="product_name") public String getProductName() { return
	 * productName; } public void setProductName(String productName) {
	 * this.productName = productName; }
	 */
	
	@Column(name="campaign_mode")
	public String getCampaignMode() {
		return campaignMode;
	}
	public void setCampaignMode(String campaignMode) {
		this.campaignMode = campaignMode;
	}
	@Column(name="campaign_owner")
	public String getCampaignOwner() {
		return campaignOwner;
	}
	public void setCampaignOwner(String campaignOwner) {
		this.campaignOwner = campaignOwner;
	}
	
    @OneToOne
	@JoinColumn(name="loginId")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@OneToOne
	@JoinColumn(name="product_id")
	public ProductServiceVO getProductServiceVO() {
		return productServiceVO;
	}
	public void setProductServiceVO(ProductServiceVO productServiceVO) {
		this.productServiceVO = productServiceVO;
	}

}
