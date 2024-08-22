package com.scube.techboot.bo;

import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

public class CustomerBO  extends BaseBo{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2517395637456235698L;
	
	private int  customerId;
	
	@NotBlank(message="FirstName may not be empty")
	@Pattern(regexp="^[a-zA-Z\\s]*$", message = "Enter the FirstName valid format")
	private String firstName;
	
	@NotBlank(message="Last Name can not be empty")
	@Pattern(regexp="^[a-zA-Z\\s]*$",message="Last Name should be a character only")
	private String lastName;
	
	@NotBlank(message="Email Address can not be empty")
	@Email
	@Pattern(regexp = ".+@.+\\.[a-z]+", message = "Email Address is not correct format")
	private String emailId;
	
	@NotBlank(message="Address can not be empty")
	private String address;
	
	@NotNull(message = "Mobile Number may not be empty")
	@Digits(integer = 10, fraction = 0, message = "Mobile Number accept only 10 digits")
	@Range(min = 777777777, message = "Mobile Number is not valid")
	private Long mobileNumber;
	
	@NotNull(message = "WhatsApp Number can not be empty")
	@Digits(integer = 10, fraction = 0, message = "WhatsApp Number accept only 10 digits")
	@Range(min = 777777777, message = "WhatsApp Number is not valid")
	private Long whatsappNumber;
	
	@NotNull(message="Date Of Birth can not be empty")
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date dateOfBirth;
	
	/*
	 * @NotBlank(message = "Customer Type can not be empty")
	 * 
	 * @Pattern(regexp="^[a-zA-Z\\s]*$",
	 * message="Customer Type should be a character only") private String
	 * customerType;
	 */
	private String dob;
	
	/*
	 * @NotBlank(message="Specialization can not be empty") private String
	 * specialization;
	 */
	
	@NotBlank(message="Industry can not be empty")
	private String industry;
    private CompanyBO companyBO;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Long getWhatsappNumber() {
		return whatsappNumber;
	}

	public void setWhatsappNumber(Long whatsappNumber) {
		this.whatsappNumber = whatsappNumber;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/*
	 * public String getCustomerType() { return customerType; }
	 * 
	 * public void setCustomerType(String customerType) { this.customerType =
	 * customerType; }
	 */
	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	/*
	 * public String getSpecialization() { return specialization; }
	 * 
	 * public void setSpecialization(String specialization) { this.specialization =
	 * specialization; }
	 */
	public CompanyBO getCompanyBO() {
		return companyBO;
	}

	public void setCompanyBO(CompanyBO companyBO) {
		this.companyBO = companyBO;
	}
	

}
