package com.scube.techboot.vo;


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
@Table(name="event_register")
public class EventRegisterVO extends BasicEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 157860243694515482L;
	private int eventRegisterId;
	private String candidateName;
	private String emailAddress;
	private Long mobileNumber;
    private String qualification;
	private String professional;
	private EventVO eventVo;
	private CompanyVO companyVO;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="eventreg_id",unique=true)
	public int getEventRegisterId() {
		return eventRegisterId;
	}
	public void setEventRegisterId(int eventRegisterId) {
		this.eventRegisterId = eventRegisterId;
	}
	@Column(name="candidatename",nullable=false,length=45)
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	@Column(name="emailaddress",nullable=false)
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	@Column(name="mobileNumber",nullable=false,length=10)
	public Long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	@Column(name="qualification",nullable=false)
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	@Column(name="professional",nullable=false)
	public String getProfessional() {
		return professional;
	}
	public void setProfessional(String professional) {
		this.professional = professional;
	}
	@OneToOne
	@JoinColumn(name="event_id")
	public EventVO getEventVo() {
		return eventVo;
	}
	public void setEventVo(EventVO eventVo) {
		this.eventVo = eventVo;
	}
	@OneToOne
	@JoinColumn(name="company_id")
	public CompanyVO getCompanyVO() {
		return companyVO;
	}
	public void setCompanyVO(CompanyVO companyVO) {
		this.companyVO = companyVO;
	}
}
