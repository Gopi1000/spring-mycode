package com.scube.techboot.bo;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

public class StudentRegisterBO extends BaseBo{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3935130579839528309L;

	private long studentRegisterId;
	
	@NotBlank(message="FirstName may not be empty")
	@Pattern(regexp="^[a-zA-Z0-9-\\.\\s]*$", message = "Enter the First Name valid format")
	private String firstName;
	
	@NotBlank(message="LastName may not be empty")
	@Pattern(regexp="^[a-zA-Z0-9-\\.\\s]*$", message = "Enter the Last Name valid format")
	private String lastName;
	
	@NotNull(message = "Contact Number may not be empty")
	@Digits(integer = 10, fraction = 0, message = "Contact Number accept only 10 digits")
	@Range(min = 777777777, message = "contact Number is not valid")
	private Long mobileNo;
	
	@NotNull(message = "Contact Number may not be empty")
	@Digits(integer = 10, fraction = 0, message = "Contact Number accept only 10 digits")
	@Range(min = 777777777, message = "contact Number is not valid")
	private Long whatsAppNo;
	
	@NotBlank(message = " EmailAddress may not be empty")
	@Pattern(regexp = ".+@.+\\.[a-z]+", message = "Email Address is not correct format")
	private String emailAddress;
	private String confirmemailAddress;
	private String password;
	private String confirmPassword;
	private String address;
	private String gender;
	private String maritalStatus;
	private String country;
	private String state;
	private String city;
	private String profileDescription;
	private String preferredLocation;
	private String language;
	private long pincode;
	private CourseBO courseBO;
	
	public long getStudentRegisterId() {
		return studentRegisterId;
	}
	public void setStudentRegisterId(long studentRegisterId) {
		this.studentRegisterId = studentRegisterId;
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
	public Long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProfileDescription() {
		return profileDescription;
	}
	public void setProfileDescription(String profileDescription) {
		this.profileDescription = profileDescription;
	}
	public String getPreferredLocation() {
		return preferredLocation;
	}
	public void setPreferredLocation(String preferredLocation) {
		this.preferredLocation = preferredLocation;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public Long getWhatsAppNo() {
		return whatsAppNo;
	}
	public void setWhatsAppNo(Long whatsAppNo) {
		this.whatsAppNo = whatsAppNo;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getConfirmemailAddress() {
		return confirmemailAddress;
	}
	public void setConfirmemailAddress(String confirmemailAddress) {
		this.confirmemailAddress = confirmemailAddress;
	}
	public long getPincode() {
		return pincode;
	}
	public void setPincode(long pincode) {
		this.pincode = pincode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public CourseBO getCourseBO() {
		return courseBO;
	}
	public void setCourseBO(CourseBO courseBO) {
		this.courseBO = courseBO;
	
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
