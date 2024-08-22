/**
 * 
 */
package com.scube.crm.service;

import java.util.List;

import com.scube.crm.bo.AccessLogBO;
import com.scube.crm.bo.AdminBO;
import com.scube.crm.bo.AdminLoginBO;
import com.scube.crm.bo.AdminUserBO;
import com.scube.crm.bo.EmployerRegisterBO;
import com.scube.crm.exception.MyJobKartException;
import com.scube.crm.vo.LoginStatusVO;

/**
 * @author Administrator
 * 
 */
public interface AdminService {

	AdminLoginBO authendicate(AdminLoginBO adminLoginBO)
			throws MyJobKartException;
	
	boolean sendClientMail (EmployerRegisterBO employerRegisterBO);
	boolean addLoginStatus(String username) throws MyJobKartException;
	
	public boolean createAccessLog(AccessLogBO logBO);

	boolean editLoginStatus(LoginStatusVO loginStatusVO);
	/**
	 * @param adminBO
	 * @return
	 */
	AdminUserBO createuser(AdminUserBO adminBO) throws MyJobKartException;
	
	AdminBO createcustomer(AdminBO adminBO)throws MyJobKartException;
	/**
	 * @param bo
	 * @return
	 */
	long getusercount(AdminUserBO bo);
	/**
	 * @return
	 */
	List<AdminUserBO> retrieveUser() throws MyJobKartException;
	
	List<AdminBO> retrieveCustomer() throws MyJobKartException;
	/**
	 * @param userBO
	 * @return
	 */
	boolean userStatus(AdminUserBO userBO) throws MyJobKartException;
	
	
	//boolean customerStatus(AdminBO userBO)throws MyJobKartException;
	/**
	 * @param deleteProfile
	 * @return
	 */
	AdminUserBO deleteProfile(AdminUserBO deleteProfile) throws MyJobKartException;
	/**
	 * @param userBO
	 * @return
	 */
	AdminUserBO retrieveusers(long  userId) throws MyJobKartException;
	
	
	AdminBO retrieveCustomers(int userId)throws MyJobKartException;
	/**
	 * @param adminBO
	 * @return
	 */
	AdminUserBO updateuser(AdminUserBO adminBO);
	
	AdminBO updatecustomer(AdminBO adminBO);
	/**
	 * @param emailAddress
	 * @return
	 */
	boolean findByEmail(String emailAddress);
	
    boolean findEmployerEmail(String emailAddress);

/**
 * @param mobileNo
 * @return
 */
boolean findByMobileNo(String mobileNo);

/**
 * @param adminBO
 * @return
 */


/**
 * @param userBO
 * @return
 */


/**
 * @param userId
 * @return
 */


/**
 * @param adminBO
 * @return
 */


/**
 * @return
 */

	
}
