package com.scube.techboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
@ComponentScan(basePackages="com.scube.techboot.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	@Qualifier("customUserDetailsService")
	UserDetailsService userDetailsService;



	@Autowired
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
		return authenticationProvider;
	}	

	/*@Bean
	public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
		StrictHttpFirewall firewall = new StrictHttpFirewall();
		firewall.setAllowUrlEncodedSlash(true);    
		return firewall;
	}*/

	/*@Bean
	public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
		StrictHttpFirewall fireWall = new StrictHttpFirewall();
		fireWall.setAllowUrlEncodedSlash(true);    
		return fireWall;
	}*/
	/*
	@Override
	public void configure(WebSecurity web) throws Exception {
		// add it 
		web.httpFirewall(allowUrlEncodedSlashHttpFirewall());
	}*/

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests().antMatchers("/home-dashBoard").authenticated()
		.and()
		.formLogin()
		.loginPage("/sign-in")
		.loginProcessingUrl("/sign-in").usernameParameter("emailAddress").passwordParameter("password").permitAll()
		.defaultSuccessUrl("/home-dashBoard")
		.failureUrl("/sign-in")	
		.and()
		.logout().logoutSuccessUrl("/logouts")
		.permitAll()
		.and().exceptionHandling().accessDeniedPage("/sign-in");
	}
}


