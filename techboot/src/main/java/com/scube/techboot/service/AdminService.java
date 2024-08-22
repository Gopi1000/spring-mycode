package com.scube.techboot.service;

import java.util.List;

import com.scube.techboot.bo.CampaignSmsTrakingBO;
import com.scube.techboot.bo.ContactBO;
import com.scube.techboot.bo.LoginBO;
import com.scube.techboot.bo.NewslettersBO;


public interface AdminService {

	LoginBO logincheck(LoginBO adminLoginBO) throws Exception;

	Boolean saveContact(ContactBO contactBo);

	List<ContactBO> viewContact(ContactBO contactBo);

	ContactBO getContactDetails(ContactBO contactBo);

	long retrieveContactCount(ContactBO contactBo);

	List<CampaignSmsTrakingBO> viewSmsTracking(CampaignSmsTrakingBO campaignSmsTrakingBO);

	CampaignSmsTrakingBO smsTrackingDetails(CampaignSmsTrakingBO campaignSmsTrakingBO);

	boolean findByEmail(String emailAddress);

	NewslettersBO newsLettersCreations(NewslettersBO newslettersBO);

	int retriveUser(LoginBO loginBO);

	List<LoginBO> listOfUsers(LoginBO loginBo) throws Exception;

	int searchUser(LoginBO loginBO);

	List<LoginBO> searchLoginData(LoginBO loginBO) throws Exception;

	LoginBO editUser(LoginBO loginBo) throws Exception;

	boolean editUserData(LoginBO loginBo) throws Exception;

	LoginBO getAdminProfile(long adminId);

	boolean deleteUser(LoginBO loginBo);
	
	public int deletecontact( int id);
	
	ContactBO editcontact(int i);
	
	int updatecontact(ContactBO bo1);

	LoginBO viewUser(LoginBO loginBo);

	
}
