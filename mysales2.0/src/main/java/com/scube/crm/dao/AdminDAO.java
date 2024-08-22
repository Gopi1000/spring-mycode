package com.scube.crm.dao;

import java.util.List;

import com.scube.crm.bo.AdminLoginBO;
import com.scube.crm.bo.AdminUserBO;
import com.scube.crm.exception.MyClientsException;
import com.scube.crm.vo.AccessLogVO;
import com.scube.crm.vo.User;
import com.scube.crm.vo.EmailAccess;
import com.scube.crm.vo.LoginStatusVO;
import com.scube.crm.vo.RoleVO;

/**
 * @author Administrator
 * @param <T>
 * 
 */
public interface AdminDAO {

	User authendicate(String string, String emailAddress) throws MyClientsException;

	User authendicate(String emailAddress) throws MyClientsException;
	
	LoginStatusVO editLoginStatus(LoginStatusVO loginStatusVO);
	
	long addLoginStatus(LoginStatusVO loginStatus);
	
	 public boolean createAccessLog(AccessLogVO logVO);

	 long createuser(User adminVO) throws MyClientsException;
	 
	 AdminUserBO retrieveusers(long userId);
	 
	 List<AdminUserBO> retrieveUser()throws MyClientsException;
	 
	 User userStatus(User userVO)throws MyClientsException;
	 
	 int deleteProfile(User vo)throws MyClientsException;
	 
	 User getuserId(AdminUserBO adminBO) throws MyClientsException;
	 
	 User updateuser(User loginVO) throws MyClientsException;
	 
	 User findByEmail(String string, String emailAddress);
	 
	 EmailAccess saveEmailList(List<EmailAccess> accessVOList);
	 
	 long campaignCount(AdminLoginBO adminLoginBO);
	 
	 long leadsCount(AdminLoginBO adminLoginBO);
	 
	 long customerCount(AdminLoginBO adminLoginBO);
	 
	 long employeesCount(AdminLoginBO adminLoginBO);
	 
	 List<User> searchEmployeeList(AdminUserBO adminBO);
	 
	 boolean findByMobilenoEmployee(String mobileNo);

	List<String> getImageName();

	boolean findEmployerEmail(String emailAddress);

	boolean mobileNoVerification(String mobileNo);

	long getCurrentActionCustomers(AdminLoginBO adminLoginBO);

	long productCount(AdminLoginBO adminLoginBO);

	/*RoleVO insertrole(RoleVO rolevo);

	List<RoleVO> retrieverole();*/
	 
}
