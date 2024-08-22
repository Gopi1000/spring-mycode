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
@Table(name="login")
public class LoginVO  extends BasicEntity {

	private static final long serialVersionUID = 9129984125215437204L;
	private long loginId;
	private String emailAddress;
	private String password;
	private String userType;
	private Boolean isActive;
	private UserRoleVO userRoleVO;
	private StudentRegisterVO student;
	private CompanyVO companyVO;

	


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="login_id")
	public long getLoginId() {
		return loginId;
	}
	public void setLoginId(long loginId) {
		this.loginId = loginId;
	}

	@Column(name = "email_address",length=50,nullable=false)
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Column(name = "password",nullable=false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "user_type",nullable=false)
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Column(name = "is_active",columnDefinition="TINYINT",nullable=false)
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	@OneToOne
	@JoinColumn(name = "user_role_id")
	public UserRoleVO getUserRoleVO() {
		return userRoleVO;
	}
	public void setUserRoleVO(UserRoleVO userRoleVO) {
		this.userRoleVO = userRoleVO;
		
	}
	@OneToOne
	@JoinColumn(name = "student_id")
	public StudentRegisterVO getStudent() {
		return student;
	}
	public void setStudent(StudentRegisterVO student) {
		this.student = student;
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
