package com.scube.techboot.dao;



import java.util.List;

import com.scube.techboot.bo.ContactBO;
import com.scube.techboot.bo.LoginBO;
import com.scube.techboot.vo.CampaignSmsTrackingVO;
import com.scube.techboot.vo.CompanyVO;
import com.scube.techboot.vo.ContactVO;
import com.scube.techboot.vo.LoginVO;
import com.scube.techboot.vo.NewslettersVO;
import com.scube.techboot.vo.StudentRegisterVO;


public interface AdminDao {

	LoginBO logincheck(LoginVO loginvo);

	Boolean saveContact(ContactVO contactVO);

	List<ContactVO> viewContact(ContactVO contactVo);

	LoginVO findByEmail(String email);

	ContactVO getContactDetails(ContactVO contactVo);

	long retrieveContactCount(ContactVO contactVo);

	List<CampaignSmsTrackingVO> viewSmsTracking(CampaignSmsTrackingVO campaignSmsTrackingVO);

	CampaignSmsTrackingVO smsTrackingDetails(CampaignSmsTrackingVO campaignSmsTrackingVO);

	NewslettersVO findByEmailNews(NewslettersVO newslettersVO);

	NewslettersVO newsLettersCreations(NewslettersVO newslettersVO);

	int retriveUser(LoginVO loginVO);

	List<LoginVO> listOfUsers(LoginVO loginVO);

	int searchUser(LoginVO loginVO);

	List<LoginVO> searchLoginData(LoginVO loginVO);

	LoginVO editUserCount(LoginVO loginVo);

	boolean editUserData(LoginVO loginVO);

	boolean editUserData(CompanyVO company);

	boolean editUserData(StudentRegisterVO student);

	LoginVO getAdminProfile(long adminId);

	boolean deleteUser(LoginVO loginVo);

	boolean deleteCompany(CompanyVO company);

	boolean deleteStudent(StudentRegisterVO student);

	public int deletecontact( int id);
	ContactBO editcontact(int i);
	int updatecontact(ContactBO bo1);

}
