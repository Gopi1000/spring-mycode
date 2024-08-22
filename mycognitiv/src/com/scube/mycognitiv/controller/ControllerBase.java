package com.scube.mycognitiv.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerBase extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

	
	public void checkAuthentication(HttpServletRequest request, HttpServletResponse response) {
    if(null==request.getSession(false) && null==request.getSession(false).getAttribute("userType")) {
			try {
				response.sendRedirect("login");
			} catch (IOException e) {				
				e.printStackTrace();
			}
			
		}
	}

}
