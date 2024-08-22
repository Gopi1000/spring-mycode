package com.scube.techboot.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;




@Entity
@Table(name="campaign_sms_tracking")
public class CampaignSmsTrackingVO {

	private long  smsTrackingId;
	private String companyName;
	private String customerName;
	private String serviceName;
	private Date curentDate;
	private long mobileNumber;
	private String message;
	@Transient
	private int recordIndex;
	@Transient
	private int maxRecord;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Tracking_id")
	public long getSmsTrackingId() {
		return smsTrackingId;
	}
	public void setSmsTrackingId(long smsTrackingId) {
		this.smsTrackingId = smsTrackingId;
	}
	
	@Column(name="curent_date")
	@Temporal(javax.persistence.TemporalType.DATE)
	public Date getCurentDate() {
		return curentDate;
	}
	public void setCurentDate(Date curentDate) {
		this.curentDate = curentDate;
	}
	@Column(name="customer_name")
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	@Column(name="company_name")
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	@Column(name="service_name")
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	@Column(name="mobile_number")
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	@Column(name="message",columnDefinition="Text")
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}


}
