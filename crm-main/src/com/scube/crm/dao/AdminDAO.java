package com.scube.crm.dao;

import java.util.List;

import com.scube.crm.bo.AdminBO;
import com.scube.crm.bo.AdminUserBO;
import com.scube.crm.exception.MyJobKartException;
import com.scube.crm.vo.AccessLogVO;
import com.scube.crm.vo.AdminLoginVO;
import com.scube.crm.vo.AdminVO;
import com.scube.crm.vo.EmailAccessVO;
import com.scube.crm.vo.LoginStatusVO;

/**
 * @author Administrator
 * @param <T>
 * 
 */
public interface AdminDAO {

	AdminLoginVO authendicate(String string, String emailAddress)
			throws MyJobKartException;

	public boolean createAccessLog(AccessLogVO logVO);

	/**
	 * @param adminVO
	 * @return
	 */
	long createuser(AdminLoginVO adminVO) throws MyJobKartException;
	
	int createcustomer(AdminVO adminVO) throws MyJobKartException;

	/**
	 * @param bo
	 * @return
	 */
	long getusercount(AdminUserBO bo)throws MyJobKartException;

	/**
	 * @return
	 */
	List<AdminUserBO> retrieveUser()throws MyJobKartException;
	
	//List<AdminBO> retrieveCustomer()throws MyJobKartException;

	/**
	 * @param userVO
	 * @return
	 */
	AdminLoginVO userStatus(AdminLoginVO userVO)throws MyJobKartException;
	
	//AdminVO customerStatus(AdminVO userVO)throws MyJobKartException;

	/**
	 * @param vO
	 * @return
	 */
	int deleteProfile(AdminLoginVO vo)throws MyJobKartException;

	/**
	 * @param userBO
	 * @return
	 */
	

	/**
	 * @param loginVO
	 * @return
	 */
	AdminLoginVO updateuser(AdminLoginVO loginVO) throws MyJobKartException;
	
	AdminVO updatecustomer(AdminVO loginVO) throws MyJobKartException; 
	
	AdminLoginVO getuserId(AdminUserBO adminBO) throws MyJobKartException;
	
	AdminVO getadminId(AdminBO adminBO) throws MyJobKartException;

	/**
	 * @param userId
	 * @return
	 */
	AdminUserBO retrieveusers(long userId);
	
	
	AdminBO retrieveCustomers(int userId);
	
	//List<AdminBO> retrieveCustomer(long adminId);
	List<AdminBO> retrieveCustomer();

	/**
	 * @param string
	 * @param emailAddress
	 * @return
	 */
	AdminLoginVO findByEmail(String string, String emailAddress);

	/**
	 * @param emailAddress
	 * @return
	 */
	boolean findEmployerEmail(String emailAddress);

	/**
	 * @param accessVOList
	 */
	EmailAccessVO saveEmailList(List<EmailAccessVO> accessVOList);

	/**
	 * @return
	 */
	List<String> getImageName();

	/**
	 * @param loginStatusVO
	 * @return
	 */
	LoginStatusVO editLoginStatus(LoginStatusVO loginStatusVO);

	/**
	 * @param loginStatus
	 * @return
	 */
	long addLoginStatus(LoginStatusVO loginStatus);

	/**
	 * @param string
	 * @param mobileNo
	 * @return
	 */
	AdminLoginVO findByMobileNo(String string, String mobileNo);

	/**
	 * @param loginVO
	 * @return
	 */
	

	/**
	 * @param adminBO
	 * @return
	 */
	

	/**
	 * @param userVO
	 * @return
	 */
	

	/**
	 * @param userId
	 * @return
	 */
	

	/**
	 * @param adminVO
	 * @return
	 */
	

	/**
	 * @return
	 */
	

	/**
	 * @return
	 */
	

	/**
	 * @return
	 */


	/**
	 * @return
	 */
	

	/**
	 * @return
	 */
	

	/**
	 * @param loginVO
	 * @param id
	 * @return
	 */
	//AdminLoginVO updateuser(AdminLoginVO loginVO, long id) throws MyJobKartException;
	
}
