package com.scube.crm.dao;

import java.util.List;

import com.scube.crm.bo.AdminLoginBO;
import com.scube.crm.bo.AdminUserBO;
import com.scube.crm.bo.CampaignBO;
import com.scube.crm.bo.LeadsBO;
import com.scube.crm.exception.MyClientsException;
import com.scube.crm.vo.AccessLogVO;
import com.scube.crm.vo.User;
import com.scube.crm.vo.Campaign;
import com.scube.crm.vo.EmailAccess;
import com.scube.crm.vo.Leads;
import com.scube.crm.vo.LeadsFollowup;
import com.scube.crm.vo.LoginStatusVO;

/**
 * @author Administrator
 * @param <T>
 * 
 */
public interface LeadsDAO {

	User authendicate(String string, String emailAddress) throws MyClientsException;

	public boolean createAccessLog(AccessLogVO logVO);

	long saveLeads(Leads leads);

	List<Leads> getListLeads(LeadsBO leadsBO);

	List<Campaign> listOfCampaign(CampaignBO campaignBO);

	LoginStatusVO editLoginStatus(LoginStatusVO loginStatusVO);

	long addLoginStatus(LoginStatusVO loginStatus);

	boolean deleteLeads(long leadsid);

	Leads getLeads(long id);

	Leads getLeadsId(LeadsBO leadsBO);

	boolean updateLead(Leads leads);

	boolean findByMobilenoLeads(String mobileNo);

	Leads findByEmailLeads(String string, String emailAddress);

	List<AdminUserBO> retrieveUser() throws MyClientsException;

	List<LeadsFollowup> retrieveTracking(long id);

	LeadsFollowup saveTracking(LeadsFollowup vO);

	List<LeadsFollowup> searchRetrieveTracking(LeadsFollowup leadsFollowup);

	long getAnyAppointment(long leadsId);

}
