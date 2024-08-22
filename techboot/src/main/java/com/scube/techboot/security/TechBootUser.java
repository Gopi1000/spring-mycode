package com.scube.techboot.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.scube.techboot.vo.CompanyVO;

public class TechBootUser extends User {

	private static final long serialVersionUID = 1889142454240168794L;	

	private long loginId;
	private CompanyVO company;
	private long studentId;
	
	public TechBootUser(String emailAddress, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, long ld, CompanyVO company,long studentId) {
		super(emailAddress, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.loginId=ld;
		this.company=company;
		this.studentId=studentId;
	}
	public long getLoginId() {
		return loginId;
	}
	public void setLoginId(long loginId) {
		this.loginId = loginId;
	}
	public CompanyVO getCompany() {
		return company;
	}
	public void setCompany(CompanyVO company) {
		this.company = company;
	}
	public long getStudentId() {
		return studentId;
	}
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	
	
	

}
