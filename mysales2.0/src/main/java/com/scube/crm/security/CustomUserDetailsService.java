package com.scube.crm.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.transaction.Transactional;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.scube.crm.dao.AdminDAO;
import com.scube.crm.utils.EncryptAndDecrypt;
import com.scube.crm.vo.User;


@Service("customUserDetailsService")
@Transactional
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private AdminDAO dao;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

	String salt = "this is a simple clear salt";
	private final Logger LOGGER = Logger.getLogger(CustomUserDetailsService.class);


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			User user=null;
	
		if(null!=username){
			try {
						user=dao.authendicate(username);
						if(null!=user){
							return new MySalesUser(user.getEmailAddress(),getPwd(user.getPassword()), true, true, true, 
									true, getAuthorities(user.getUserType()),user.getId(),user.getName(),user.getUserType());
						}						
					
					
				
			}catch(Exception he){
				if(LOGGER.isInfoEnabled()) {
					LOGGER.info("FROM INFO: Exception \t"+he);
				}
				if(LOGGER.isDebugEnabled()) {
					LOGGER.debug("FROM DEBUG : Exception \t"+he);
				}

			}
		}

		return new org.springframework.security.core.userdetails.User(null,null, null);
	}

	
	private String getPwd(String pwd) throws Exception {
		String decrypted=EncryptAndDecrypt.decrypt(pwd);
		System.out.println(decrypted);
		return passwordEncoder().encode(decrypted);
	}

	private Collection<? extends GrantedAuthority> getAuthorities(String userRole) {
		return getGrantedAuthorities(getPrivileges(userRole));
	}

	private List<String> getPrivileges(String userRole) {
		List<String> privileges = new ArrayList<>();
		privileges.add(userRole);
		return privileges;
	}

	private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (String privilege : privileges) {
			authorities.add(new SimpleGrantedAuthority(privilege));
		}
		return authorities;
	}

}
