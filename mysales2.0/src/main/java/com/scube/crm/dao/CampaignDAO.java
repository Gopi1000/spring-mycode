package com.scube.crm.dao;

import java.util.List;


import com.scube.crm.bo.AdminUserBO;
import com.scube.crm.bo.CampaignBO;
import com.scube.crm.exception.MyClientsException;
import com.scube.crm.vo.AccessLogVO;
import com.scube.crm.vo.User;
import com.scube.crm.vo.Campaign;
import com.scube.crm.vo.LoginStatusVO;

public interface CampaignDAO {

	User authendicate(String string, String emailAddress) throws MyClientsException;

	public boolean createAccessLog(AccessLogVO logVO);

	Campaign saveCompaign(Campaign campaign);

	List<Campaign> listOfCampaign(CampaignBO campaignBO);

	Campaign getObject(int campaignId);

	boolean updateCampaign(Campaign campaign);
	
	boolean deleteCampaign(int id);
	
	Campaign getCampaignId(CampaignBO campaignBO);
	
	long addLoginStatus(LoginStatusVO loginStatus);

	LoginStatusVO editLoginStatus(LoginStatusVO loginStatusVO);

	List<AdminUserBO> retrieveUser();

	User retrieveParticularUser(User user);

}
