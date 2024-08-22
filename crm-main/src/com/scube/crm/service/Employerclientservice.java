/**
 * 
 */
package com.scube.crm.service;

import com.scube.crm.bo.EmployerRegisterBO;
import com.scube.crm.model.EmployerLoginVO;
import com.scube.crm.vo.EmployerVO;

/**
 * @author User
 *
 */
public interface Employerclientservice {

	
	EmployerRegisterBO clientTomyjobkartRegister(EmployerRegisterBO employerRegisterBO);

	/**
	 * @param bO
	 * @return
	 */
	EmployerLoginVO signinTomyjobkart(EmployerRegisterBO BO);

	/**
	 * @param employerRegisterBO
	 */
	
	
	

}
