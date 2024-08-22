package com.scube.techboot.bo;

import java.sql.Blob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

public class CompanyBO extends BaseBo{

	
	private int companyId;
	@NotBlank(message="Company Name may not be empty")
	@Pattern(regexp="^[a-zA-Z0-9-\\.\\s]*$", message = "Enter the Company Name valid format")
	private String companyName;
	
	@NotBlank(message="ChairPersonName may not be empty")
	@Pattern(regexp="^[a-zA-Z0-9-\\.\\s]*$", message = "Enter the ChairPerson Name valid format")
	private String companyPersonName;
	
	@NotBlank(message="FirstName may not be empty")
	@Pattern(regexp="^[a-zA-Z0-9-\\.\\s]*$", message = "Enter the First Name valid format")
	private String firstName;
	
	@NotBlank(message="LastName may not be empty")
	@Pattern(regexp="^[a-zA-Z0-9-\\.\\s]*$", message = "Enter the Last Name valid format")
	private String lastName;
	
	@NotBlank(message = " EmailAddress may not be empty")
	@Pattern(regexp = ".+@.+\\.[a-z]+", message = "Email Address is not correct format")
	private String emailAddress;
	
	@NotBlank(message = " ConformEmailAddress may not be empty")
	@Pattern(regexp = ".+@.+\\.[a-z]+", message = "Conform Email Address is not correct format")
	private String conformEmailAddress;
	
	//@NotBlank(message = " Password may not be empty")
	private String password;
	
	//@NotBlank(message = " Conform Password may not be empty")
	private String conformPassword;
	
	@NotBlank(message = " CompanyWebSite may not be empty")
	private String companyWebSite;
	
	@NotBlank(message = " CompanyType may not be empty")
	private String companyType;
	
	@NotNull(message = "Contact Number may not be empty")
	@Digits(integer = 10, fraction = 0, message = "Contact Number accept only 10 digits")
	@Range(min = 777777777, message = "contact Number is not valid")
	private Long contectNumber;
	
	@NotBlank(message = "Address not be empty")
	private String address;
	
	private Blob companyLogo;
	
	private String imageName;
	@NotBlank(message = "Industry not be empty")
	private String industry;
	
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyPersonName() {
		return companyPersonName;
	}
	public void setCompanyPersonName(String companyPersonName) {
		this.companyPersonName = companyPersonName;
	}
	public String getfirstName() {
		return firstName;
	}
	public void setfirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getlastName() {
		return lastName;
	}
	public void setlastName(String lastName) {
		this.lastName = lastName;
	}
	public String getemailAddress() {
		return emailAddress;
	}
	public void setemailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getConformEmailAddress() {
		return conformEmailAddress;
	}
	public void setConformEmailAddress(String conformEmailAddress) {
		this.conformEmailAddress = conformEmailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConformPassword() {
		return conformPassword;
	}
	public void setConformPassword(String conformPassword) {
		this.conformPassword = conformPassword;
	}
	public String getCompanyWebSite() {
		return companyWebSite;
	}
	public void setCompanyWebSite(String companyWebSite) {
		this.companyWebSite = companyWebSite;
	}
	public String getCompanyType() {
		return companyType;
	}
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	public Long getContectNumber() {
		return contectNumber;
	}
	public void setContectNumber(Long contectNumber) {
		this.contectNumber = contectNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public Blob getCompanyLogo() {
		return companyLogo;
	}
	public void setCompanyLogo(byte[] companyLogo) 
			throws SerialException,
			SQLException {
		if (null != companyLogo) {
			this.companyLogo = new SerialBlob(companyLogo);
		} else {
			this.companyLogo = null;
		}

	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
}


