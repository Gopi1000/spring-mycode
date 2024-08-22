/**
 * 
 */
package com.scube.crm.dao;


import com.scube.crm.model.EmployerLoginVO;
import com.scube.crm.model.EmployerVO;
import com.scube.crm.vo.CompanyVO;

/**
 * @author User
 *
 */
public interface EmployerclientDao {

	/**
	 * @param vO
	 * @return
	 */
	EmployerVO clientTomyjobkartRegister(EmployerVO VO);

	/**
	 * @param loginVO
	 * @return
	 */
	EmployerLoginVO signinTomyjobkart(EmployerLoginVO loginVO);

	/**
	 * @param company 
	 * @return
	 */
	CompanyVO retrieveCompany(String company);

	

	/**
	 * @param companyVO
	 * @return
	 */
	long addnewcompany(CompanyVO companyVO);
	
	
	

}
