package com.scube.techboot.bo;

import java.util.Date;

public class CampaignSmsTrakingBO {
	private long trackinId;
	private long mobileNumber;
	private String customerName;
	private Date trackingDate;
	private long companyId;
	private String message;
	private int recordIndex;
	private int maxRecord;
	private String pagination = null;
	public long getTrackinId() {
		return trackinId;
	}
	public void setTrackinId(long trackinId) {
		this.trackinId = trackinId;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Date getTrackingDate() {
		return trackingDate;
	}
	public void setTrackingDate(Date trackingDate) {
		this.trackingDate = trackingDate;
	}
	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getRecordIndex() {
		return recordIndex;
	}
	public void setRecordIndex(int recordIndex) {
		this.recordIndex = recordIndex;
	}
	public int getMaxRecord() {
		return maxRecord;
	}
	public void setMaxRecord(int maxRecord) {
		this.maxRecord = maxRecord;
	}
	public String getPagination() {
		return pagination;
	}
	public void setPagination(String pagination) {
		this.pagination = pagination;
	}
	

}
