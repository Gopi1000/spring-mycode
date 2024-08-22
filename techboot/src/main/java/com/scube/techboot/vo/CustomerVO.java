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
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "customer")
public class CustomerVO extends BasicEntity{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4510860910979956265L;
	private int  customerId;
	private String firstName;
	private String lastName;
	private String emailId;
	private String address;
	private Long mobileNumber;
	private Long whatsappNumber;
	private CompanyVO companyVO;

	@NotNull
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date dateOfBirth;
	/*
	 * private String customerType; private String specialization;
	 */
	private String industry;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="customer_Id")
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	@Column(name="first_name", nullable=false)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@Column(name="last_name" , nullable=false)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Column(name="email_id" , nullable=false)
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	@Column(name="address", nullable=false)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address ) {
		this.address = address;
	}
	@Column(name="mobile_number" , nullable=false, length=10, columnDefinition="BIGINT")
	public Long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	@Column(name="whatsapp_number" , nullable=false, length=10, columnDefinition="BIGINT")
	public Long getWhatsappNumber() {
		return whatsappNumber;
	}
	public void setWhatsappNumber(Long whatsappNumber) {
		this.whatsappNumber = whatsappNumber;
	}
	
	@Column(name="dateOf_birth" , nullable=false , columnDefinition="DATETIME")
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	/*
	 * @Column(name="customer_type" , nullable=false) public String
	 * getCustomerType() { return customerType; } public void setCustomerType(String
	 * customerType) { this.customerType = customerType; }
	 */
		
	@Column(name="industry", nullable=false)
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	/*
	 * @Column(name="specialization" , nullable=false) public String
	 * getSpecialization() { return specialization; } public void
	 * setSpecialization(String specialization) { this.specialization =
	 * specialization; }
	 */
	
	@OneToOne/*(cascade = CascadeType.ALL,fetch = FetchType.EAGER)*/
	@JoinColumn(name="company_id")
	public CompanyVO getCompanyVO() {
		return companyVO;
	}
	public void setCompanyVO(CompanyVO companyVO) {
		this.companyVO = companyVO;
	}
	
	
	
}
