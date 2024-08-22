package com.scube.techboot.bo;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

public class ProductServiceBO extends BaseBo{

	private long serviceId;
	@NotEmpty(message = " serviceName  may not be empty")
	@Pattern(regexp = "^[a-zA-Z0-9-\\.\\s]*$", message = "Enter the serviceName valid format")
	private String serviceName;
	
	private double fees;
	private int rupees;
	
	@NotEmpty(message = " duration  may not be empty")
	@Pattern(regexp = "^[a-zA-Z0-9-\\.\\s]*$", message = "Enter the duration valid format")
	private String duration;
	/*@NotNull
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date startDate;
	@NotNull
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date endDate;*/
	
	@NotEmpty(message = " ServiceSpecification  may not be empty")
	@Pattern(regexp = "^[a-zA-Z0-9-\\.\\s]*$", message = "Enter the ServiceSpecification valid format")
	private String ServiceSpecification;
	
	
	/*private String beginDate;
    private String lastDate;*/

    private CompanyBO companyBO;

	public long getServiceId() {
		return serviceId;
	}
	public void setServiceId(long serviceId) {
		this.serviceId = serviceId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public double getFees() {
		return fees;
	}
	public void setFees(double fees) {
		this.fees = fees;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
/*	public Date getStartDate() {
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

	
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getLastDate() {
		return lastDate;
	}
	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}*/
	public String getServiceSpecification() {
		return ServiceSpecification;
	}
	public void setServiceSpecification(String serviceSpecification) {
		ServiceSpecification = serviceSpecification;
	}
	public int getRupees() {
		return rupees;
	}
	public void setRupees(int rupees) {
		this.rupees = rupees;
	}
	public CompanyBO getCompanyBO() {
		return companyBO;
	}

	public void setCompanyBO(CompanyBO companyBO) {
		this.companyBO = companyBO;
	}
	

}
