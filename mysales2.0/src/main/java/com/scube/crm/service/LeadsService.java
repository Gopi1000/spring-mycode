/**
 * 
 */
package com.scube.crm.service;

import java.util.List;

import com.scube.crm.bo.AccessLogBO;
import com.scube.crm.bo.AdminLoginBO;
import com.scube.crm.bo.AdminUserBO;
import com.scube.crm.bo.CampaignBO;
import com.scube.crm.bo.ClientBO;
import com.scube.crm.bo.LeadsBO;
import com.scube.crm.exception.MyClientsException;
import com.scube.crm.vo.LeadsFollowup;
import com.scube.crm.vo.LoginStatusVO;

/**
 * @author Administrator
 * 
 */
public interface LeadsService {

	AdminLoginBO authendicate(AdminLoginBO adminLoginBO) throws MyClientsException;

	boolean sendClientMail(ClientBO employerRegisterBO);

	boolean addLoginStatus(String username) throws MyClientsException;

	public boolean createAccessLog(AccessLogBO logBO);

	boolean editLoginStatus(LoginStatusVO loginStatusVO);

	List<CampaignBO> listOfCampaign(CampaignBO campaignBO);

	List<LeadsBO> getListLeads(LeadsBO leadsBO) throws MyClientsException;

	LeadsBO getLeads(int leadsId)throws MyClientsException;

	boolean updateLeads(LeadsBO leadsBO) throws MyClientsException;

	long saveLeads(LeadsBO leadsBO);

	boolean findByMobilenoLeads(String mobileNo);

	boolean findByEmailLeads(String emailAddress);

	List<AdminUserBO> retrieveUser()throws MyClientsException;

	LeadsBO saveTracking(LeadsBO bO);

	boolean deleteLeads(Integer leadsId)throws MyClientsException;

	List<LeadsBO> searchRetrieveTracking(LeadsBO listLeadsBO);

	long getAnyAppointment(long leadsId);
}
