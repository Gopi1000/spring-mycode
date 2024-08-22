package com.scube.techboot.vo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="students")
public class StudentRegisterVO extends BasicEntity  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4148263591054179385L;
	
	private Long studentId;
	private String firstName;
	private String lastName;
	private Long mobileNo;
	private Long whatsAppNo;
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
	private List<StudentEnrollment> enrollment;
	private LoginVO login;
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="student_id")
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	
	@Column(name="firstName",nullable=false,length=50)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Column(name="lastName",nullable=false,length=50)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Column(name="mobileNo",nullable=false,length=10,columnDefinition="BIGINT")
	public Long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	@Column(name="whatsAppNo",nullable=false,length=10,columnDefinition="BIGINT")
	public Long getWhatsAppNo() {
		return whatsAppNo;
	}
	public void setWhatsAppNo(Long whatsAppNo) {
		this.whatsAppNo = whatsAppNo;
	}
	
	@Column(name="emailAddress",nullable=false,length=50)
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
	
	@Column(name="password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	@Column(name="address",nullable=false,length=50)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name="gender")
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Column(name="maritalStatus")
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	
	@Column(name="country")
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	@Column(name="state")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Column(name="city")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Column(name="profileDescription")
	public String getProfileDescription() {
		return profileDescription;
	}
	public void setProfileDescription(String profileDescription) {
		this.profileDescription = profileDescription;
	}
	@Column(name="preferredLocation")
	public String getPreferredLocation() {
		return preferredLocation;
	}
	public void setPreferredLocation(String preferredLocation) {
		this.preferredLocation = preferredLocation;
	}
	@Column(name="language")
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
	
	@Column(name="pincode")
	public long getPincode() {
		return pincode;
	}
	public void setPincode(long pincode) {
		this.pincode = pincode;
	}
	
	@OneToOne(mappedBy="student", cascade=CascadeType.ALL)
	//@Transient
	public LoginVO getLogin() {
		return login;
	}
	public void setLogin(LoginVO login) {
		this.login = login;
	}
	
	//@OneToOne(mappedBy="student", cascade=CascadeType.ALL)
	@OneToMany(mappedBy="student", cascade=CascadeType.ALL)
	public List<StudentEnrollment> getEnrollment() {
		return enrollment;
	}
	public void setEnrollment(List<StudentEnrollment> enrollment) {
		this.enrollment = enrollment;
	}
	
	
	
	
	
	
}