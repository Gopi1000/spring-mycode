/**
 * 
 */
package com.scube.crm.service;

import java.util.List;

import com.scube.crm.bo.AccessLogBO;
import com.scube.crm.bo.AdminLoginBO;
import com.scube.crm.bo.AdminUserBO;
import com.scube.crm.bo.ClientBO;
import com.scube.crm.bo.RoleBO;
import com.scube.crm.exception.MyClientsException;
import com.scube.crm.vo.LoginStatusVO;


public interface AdminService {

	AdminLoginBO authendicate(AdminLoginBO adminLoginBO) throws MyClientsException;

	boolean sendClientMail(ClientBO employerRegisterBO);

	boolean addLoginStatus(String username) throws MyClientsException;

	public boolean createAccessLog(AccessLogBO logBO);

	boolean editLoginStatus(LoginStatusVO loginStatusVO);

	AdminUserBO createuser(AdminUserBO adminBO) throws MyClientsException;

	boolean findByEmail(String emailAddress);

	boolean findByMobilenoEmployee(String mobileNo);

	List<AdminUserBO> retrieveUser() throws MyClientsException;

	List<AdminUserBO> searchEmployeeList(AdminUserBO adminBO);

	boolean userStatus(AdminUserBO userBO) throws MyClientsException;

	AdminUserBO deleteProfile(AdminUserBO deleteProfile) throws MyClientsException;

	AdminUserBO retrieveusers(long userId) throws MyClientsException;

	AdminUserBO updateuser(AdminUserBO adminBO);

	long employeesCount(AdminLoginBO adminLoginBO);

	long campaignCount(AdminLoginBO adminLoginBO);

	long customerCount(AdminLoginBO adminLoginBO);

	long leadsCount(AdminLoginBO adminLoginBO);

	long getCurrentActionCustomers(AdminLoginBO adminLoginBO);

	long productCount(AdminLoginBO adminLoginBO);

	/*RoleBO insertrole(RoleBO rolebo);

	List<RoleBO> retrieverole(List<RoleBO> rolebo);*/

	
}
