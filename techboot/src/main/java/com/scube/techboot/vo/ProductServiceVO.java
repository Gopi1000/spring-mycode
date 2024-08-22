package com.scube.techboot.vo;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="service")
public class ProductServiceVO extends BasicEntity {


	private long serviceId;
	private String serviceName;
	private double fees;
	private String duration;
	//private Date startDate;
	//private Date endDate;
	private String ServiceSpecification;
	private CompanyVO companyVO;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="service_id")
	public long getServiceId() {
		return serviceId;
	}
	public void setServiceId(long serviceId) {
		this.serviceId = serviceId;
	}

	@Column(name="service_Name",nullable=false)
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	@Column(name="fees",nullable=false,columnDefinition="DOUBLE")
	public double getFees() {
		return fees;
	}
	public void setFees(double fees) {
		this.fees = fees;
	}

	@Column(name="duration",nullable=false)
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}

	/*@Column(name="start_Date",nullable=false,columnDefinition="DATETIME")
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(name="end_Date",nullable=false,columnDefinition="DATETIME")
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}*/

	@Column(name="Service_Specification",nullable=false)
	public String getServiceSpecification() {
		return ServiceSpecification;
	}
	public void setServiceSpecification(String serviceSpecification) {
		ServiceSpecification = serviceSpecification;
	}

	@OneToOne//(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="company_id")
	public CompanyVO getCompanyVO() {
		return companyVO;
	}
	public void setCompanyVO(CompanyVO companyVO) {
		this.companyVO = companyVO;
	}
}
