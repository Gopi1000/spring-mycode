package com.scube.techboot.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CheckingStatus {

	public static boolean status(String emailAddress,String conformEmailAddress){
		if(emailAddress.equalsIgnoreCase(conformEmailAddress)){
			return true;
		}
		return false;
	}
	/*public static boolean status1(String password,String conformPassword){
		if(password.equalsIgnoreCase(conformPassword)){
			return true;
		}
		return false;
	}*/
	//login checking
	public static String checkSession(HttpServletRequest request, HttpSession session){
		String CurentUrl=request.getRequestURI();
		String value=null;
		if(null!=CurentUrl && null==session.getAttribute("userType")){
			value="redirect:/sign-in";
		}
		return value;
	}
}
