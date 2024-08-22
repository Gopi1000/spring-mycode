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
import com.scube.crm.vo.LoginStatusVO;


public interface CampaignService {

	AdminLoginBO authendicate(AdminLoginBO adminLoginBO) throws  MyClientsException;
	  
	  boolean sendClientMail (ClientBO employerRegisterBO); 
	  
	  boolean  addLoginStatus(String username) throws MyClientsException;
	 
	  public boolean createAccessLog(AccessLogBO logBO);
	 
	 boolean editLoginStatus(LoginStatusVO loginStatusVO);
	 
	 List<AdminUserBO> retrieveUser() throws MyClientsException;
	 
	 CampaignBO saveCompaign(CampaignBO campaignBO);
	 
	 List<CampaignBO> listOfCampaign(CampaignBO campaignBO);
	 
	 CampaignBO getObject(String campaignId);
	 
	 boolean updateCampaign(CampaignBO campaignBO);
	 
	 boolean deleteCampaign(String campaignId);

	AdminUserBO retrieveParticularUser(long loginId);

}
