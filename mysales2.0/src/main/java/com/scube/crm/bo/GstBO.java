package com.scube.crm.bo;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class GstBO extends BaseBO{

	private static final long serialVersionUID = 1L;

	private long gstId;
	private String sgst;
	private String cgst;
	private long sNo;
	private String gstMode;
	@NotNull
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date startDate;
	private String beginDate;
	private AdminLoginBO adminLoginBO; 
	
	public long getGstId() {
		return gstId;
	}

	public void setGstId(long gstId) {
		this.gstId = gstId;
	}

	public String getSgst() {
		return sgst;
	}

	public void setSgst(String sgst) {
		this.sgst = sgst;
	}

	public String getCgst() {
		return cgst;
	}

	public void setCgst(String cgst) {
		this.cgst = cgst;
	}

	public AdminLoginBO getAdminLoginBO() {
		return adminLoginBO;
	}

	public void setAdminLoginBO(AdminLoginBO adminLoginBO) {
		this.adminLoginBO = adminLoginBO;
	}

	public long getsNo() {
		return sNo;
	}

	public void setsNo(long sNo) {
		this.sNo = sNo;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getGstMode() {
		return gstMode;
	}

	public void setGstMode(String gstMode) {
		this.gstMode = gstMode;
	}

}
