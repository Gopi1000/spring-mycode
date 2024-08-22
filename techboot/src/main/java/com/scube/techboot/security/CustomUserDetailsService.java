package com.scube.techboot.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.scube.techboot.dao.AdminDao;
import com.scube.techboot.utils.EncryptAndDecrypt;
import com.scube.techboot.vo.CompanyVO;
import com.scube.techboot.vo.LoginVO;
import com.scube.techboot.vo.UserRoleVO;

@Service("customUserDetailsService")
@Transactional
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	ServletContext servletContext;

	@Autowired
	private AdminDao loginDAO;


	@Transactional
	@Override
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {
     	 LoginVO loginVO = loginDAO.findByEmail(email);
		CompanyVO company=null;
		long studentId=0;
		if(null!=loginVO && null!=loginVO.getCompanyVO()){
			company=loginVO.getCompanyVO();
		}
		if(null!=loginVO && null!=loginVO.getStudent()){
		studentId=loginVO.getStudent().getStudentId();
		}
		
		
		if (null!=loginVO) {
			return new TechBootUser(
					loginVO.getEmailAddress(),getPwd(loginVO.getPassword()), loginVO.getIsActive(), true, true, 
					true, getAuthorities(loginVO.getUserRoleVO()),loginVO.getLoginId(),company,studentId);

		}
		return null;
	}

	private Collection<? extends GrantedAuthority> getAuthorities(
			UserRoleVO roles) {

		return getGrantedAuthorities(getPrivileges(roles));
	}

	private List<String> getPrivileges(UserRoleVO roles) {

		List<String> privileges = new ArrayList<>();
		// List<Privilege> collection = new ArrayList<>();
			privileges.add(roles.getUserRoleName());
		return privileges;
	}

	private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (String privilege : privileges) {
			authorities.add(new SimpleGrantedAuthority(privilege));
		}
		return authorities;
	}


	private String getPwd(String pwd){
		String encodedpwd=null;
		try {
			String password=EncryptAndDecrypt.decrypt(pwd, EncryptAndDecrypt.TOKEN);
			BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
			encodedpwd=encoder.encode(password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encodedpwd;

	}
}
