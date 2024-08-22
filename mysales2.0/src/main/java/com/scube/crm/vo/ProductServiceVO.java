package com.scube.crm.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="product_service")
public class ProductServiceVO extends BasicEntity {

	private static final long serialVersionUID = 1L;
	private long serviceId;
	private String serviceName;
	private double fees;
	private String duration;
	private Date startDate;
	private Date endDate;
	private String ServiceSpecification;
	private boolean isActive;
	@Transient
	private int recordIndex;
	@Transient
	private int maxRecord;
	@Transient

	public boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	public boolean getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	private boolean isDelete;
	
	private User userVO;
	
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

	@Column(name="start_Date",nullable=false,columnDefinition="DATETIME")
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
	}

	@Column(name="Service_Specification",nullable=false)
	public String getServiceSpecification() {
		return ServiceSpecification;
	}
	public void setServiceSpecification(String serviceSpecification) {
		ServiceSpecification = serviceSpecification;
	}
	
	@ManyToOne
	@JoinColumn(name="user_id")
	public User getUserVO() {
		return userVO;
	}
	public void setUserVO(User userVO) {
		this.userVO = userVO;
	}
	
	@Transient
	public int getRecordIndex() {
		return recordIndex;
	}
	public void setRecordIndex(int recordIndex) {
		this.recordIndex = recordIndex;
	}
	@Transient
	public int getMaxRecord() {
		return maxRecord;
	}
	public void setMaxRecord(int maxRecord) {
		this.maxRecord = maxRecord;
	}


}
