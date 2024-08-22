package com.scube.techboot.bo;


import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

public class EventRegisterBO extends BaseBo {
	
	private int eventRegisterId;
	
	@NotBlank(message="Candidate Name may not be empty")
	@Pattern(regexp="^[a-zA-Z0-9-\\.\\s]*$", message = "Enter the Candidate Name valid format")
	private String candidateName;
	
	@NotBlank(message="Email Address can not be empty")
	@Email
	@Pattern(regexp = ".+@.+\\.[a-z]+", message = "Email Address is not correct format")
	private String emailAddress;
	
	@NotNull(message = "Mobile Number may not be empty")
	@Digits(integer = 10, fraction = 0, message = "Mobile Number accept only 10 digits")
	@Range(min = 777777777, message = "Mobile Number is not valid")
	private Long mobileNumber;
	
	@NotBlank(message="Qualification can not be empty")
	private String qualification;
	
	@NotBlank(message="Profession can not be empty")
	@Pattern(regexp="^[a-zA-Z.\\s]*$", message = "Enter the Professional valid format")
	private String professional;
	
	private EventsBO eventBo;
	
    private CompanyBO companyBO;

	
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public Long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getProfessional() {
		return professional;
	}
	public void setProfessional(String professional) {
		this.professional = professional;
	}
	public EventsBO getEventBo() {
		return eventBo;
	}
	public void setEventBo(EventsBO eventBo) {
		this.eventBo = eventBo;
	}
	public int getEventRegisterId() {
		return eventRegisterId;
	}
	public void setEventRegisterId(int eventRegisterId) {
		this.eventRegisterId = eventRegisterId;
	}
	public CompanyBO getCompanyBO() {
		return companyBO;
	}

	public void setCompanyBO(CompanyBO companyBO) {
		this.companyBO = companyBO;
	}
	
}
