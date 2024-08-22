package com.scube.techboot.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scube.techboot.bo.CampaignSmsTrakingBO;
import com.scube.techboot.bo.CompanyBO;
import com.scube.techboot.bo.ContactBO;
import com.scube.techboot.bo.LoginBO;
import com.scube.techboot.bo.NewslettersBO;
import com.scube.techboot.bo.StudentRegisterBO;
import com.scube.techboot.bo.TestimonialBO;
import com.scube.techboot.dao.AdminDao;
import com.scube.techboot.utils.EncryptAndDecrypt;
import com.scube.techboot.vo.CampaignSmsTrackingVO;
import com.scube.techboot.vo.CompanyVO;
import com.scube.techboot.vo.ContactVO;
import com.scube.techboot.vo.CourseVO;
import com.scube.techboot.vo.LoginVO;
import com.scube.techboot.vo.NewslettersVO;
import com.scube.techboot.vo.StudentEnrollment;
import com.scube.techboot.vo.StudentRegisterVO;
import com.scube.techboot.vo.TestimonialVO;
import com.scube.techboot.vo.UserRoleVO;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao admindao;

	public LoginBO logincheck(LoginBO adminLoginBO) throws Exception {
		LoginVO loginVO = new LoginVO();
		loginVO.setEmailAddress(adminLoginBO.getEmailAddress());
		loginVO.setPassword(adminLoginBO.getPassword());
		loginVO.setIsDelete(false);
		String password=EncryptAndDecrypt.encrypt(adminLoginBO.getPassword(),
				EncryptAndDecrypt.TOKEN);
		loginVO.setPassword(password);

		adminLoginBO= admindao.logincheck(loginVO);
		return adminLoginBO;
	}

	@Override
	public Boolean saveContact(ContactBO contactBo) {
		// TODO Auto-generated method stub
		ContactVO contactVO=new ContactVO();
		BeanUtils.copyProperties(contactBo, contactVO);
		contactVO.setCreatedDate(new Date());
		contactVO.setisDelete(false);
		contactVO.setSending_status(true);
		return admindao.saveContact(contactVO);
	}

	@Override
	public List<ContactBO> viewContact(ContactBO contactBo) {
		// TODO Auto-generated method stub
		ContactVO contactVo=new ContactVO();
		List<ContactBO> contactlist=new ArrayList<ContactBO>();
		contactVo.setisDelete(contactBo.getisDelete());
		contactVo.setSending_status(contactBo.getSending_status());
		List<ContactVO> listVo=admindao.viewContact(contactVo);
		if(null!=listVo && !listVo.isEmpty() && listVo.size()>0){
			listVo.forEach(contactVO->{
				ContactBO contactBO=new ContactBO();
				contactBO.setContactId(contactVO.getContactId());
				contactBO.setYourName(contactVO.getYourName());
				contactBO.setContactNumber(contactVO.getContactNumber());
				contactBO.setEmailAddress(contactVO.getEmailAddress());
				contactBO.setMessage(contactVO.getMessage());
				contactlist.add(contactBO);
			});
		}
		return contactlist;
	}

	@Override
	public ContactBO getContactDetails(ContactBO contactBo) {
		// TODO Auto-generated method stub
		ContactVO contactVo=new ContactVO();
		contactVo.setContactId(contactBo.getContactId());
		contactVo.setisDelete(false);
		contactVo.setSending_status(true);
		contactVo=admindao.getContactDetails(contactVo);
		if(null!=contactVo){
			ContactBO contactBO=new ContactBO();
			contactBO.setContactId(contactVo.getContactId());
			contactBO.setYourName(contactVo.getYourName());
			contactBO.setEmailAddress(contactVo.getEmailAddress());
			contactBO.setContactNumber(contactVo.getContactNumber());
			contactBO.setMessage(contactVo.getMessage());
			return contactBO;
		}
		return contactBo;
	}

	@Override
	public long retrieveContactCount(ContactBO contactBo) {
		// TODO Auto-generated method stub
		ContactVO contactVo=new ContactVO();
		contactVo.setisDelete(false);
		contactVo.setSending_status(true);
		return admindao.retrieveContactCount(contactVo);
	}



	@Override
	public List<CampaignSmsTrakingBO> viewSmsTracking(CampaignSmsTrakingBO campaignSmsTrakingBO) {
		// TODO Auto-generated method stub

		List<CampaignSmsTrakingBO> campaignSmsTrakingListBO=new ArrayList<CampaignSmsTrakingBO>();
		List<CampaignSmsTrackingVO> campaignSmsTrakingList=new ArrayList<CampaignSmsTrackingVO>();
		CampaignSmsTrackingVO campaignSmsTrackingVO=new CampaignSmsTrackingVO();
		campaignSmsTrackingVO.setSmsTrackingId(campaignSmsTrakingBO.getTrackinId());
		campaignSmsTrackingVO.setCurentDate(campaignSmsTrakingBO.getTrackingDate());
		campaignSmsTrakingList=admindao.viewSmsTracking(campaignSmsTrackingVO);
		if(null!=campaignSmsTrakingList && !campaignSmsTrakingList.isEmpty() && campaignSmsTrakingList.size()>0){
			for(CampaignSmsTrackingVO CampaignSmsTracking:campaignSmsTrakingList){
				CampaignSmsTrakingBO campaignSmsTraking=new CampaignSmsTrakingBO();
				campaignSmsTraking.setTrackingDate(CampaignSmsTracking.getCurentDate());
				campaignSmsTraking.setTrackinId(CampaignSmsTracking.getSmsTrackingId());
				campaignSmsTraking.setMessage(CampaignSmsTracking.getMessage());
				campaignSmsTraking.setCustomerName(CampaignSmsTracking.getCustomerName());
				campaignSmsTraking.setMobileNumber(CampaignSmsTracking.getMobileNumber());
				campaignSmsTrakingListBO.add(campaignSmsTraking);
			}

		}


		return campaignSmsTrakingListBO;
	}

	@Override
	public CampaignSmsTrakingBO smsTrackingDetails(CampaignSmsTrakingBO campaignSmsTrakingBO) {
		// TODO Auto-generated method stub
		CampaignSmsTrackingVO campaignSmsTrackingVO=new CampaignSmsTrackingVO();
		CampaignSmsTrakingBO CampaignSmsTrakingBO=new CampaignSmsTrakingBO();
		campaignSmsTrackingVO.setSmsTrackingId(campaignSmsTrakingBO.getTrackinId());
		campaignSmsTrackingVO=admindao.smsTrackingDetails(campaignSmsTrackingVO);
		if(null!=campaignSmsTrackingVO){
			CampaignSmsTrakingBO.setTrackinId(campaignSmsTrackingVO.getSmsTrackingId());
			CampaignSmsTrakingBO.setTrackingDate(campaignSmsTrackingVO.getCurentDate());
			CampaignSmsTrakingBO.setCustomerName(campaignSmsTrackingVO.getCustomerName());
			CampaignSmsTrakingBO.setMessage(campaignSmsTrackingVO.getMessage());
			CampaignSmsTrakingBO.setMobileNumber(campaignSmsTrackingVO.getMobileNumber());

		}


		return CampaignSmsTrakingBO;
	}

	@Override
	public boolean findByEmail(String emailAddress) {
		// TODO Auto-generated method stub
		NewslettersVO newslettersVO=new NewslettersVO();
		newslettersVO.setEmailAddress(emailAddress);
		NewslettersVO newslettersVo= admindao.findByEmailNews(newslettersVO);
		if(null!=newslettersVo){
			return true;
		}
		return false;
	}

	@Override
	public NewslettersBO newsLettersCreations(NewslettersBO newslettersBO) {
		// TODO Auto-generated method stub
		NewslettersVO newslettersVO=new NewslettersVO();
		BeanUtils.copyProperties(newslettersBO, newslettersVO);
		newslettersVO = admindao.newsLettersCreations(newslettersVO);
		if(null!=newslettersVO && 0!=newslettersVO.getNewsLettersId()){
			newslettersBO.setNewsLettersId(newslettersVO.getNewsLettersId());
			return newslettersBO;
		}
		return newslettersBO;
	}

	@Override
	public int retriveUser(LoginBO loginBO) {
		// TODO Auto-generated method stub
		int loginListCount=0;
		LoginVO loginVO=new LoginVO();
		loginListCount=admindao.retriveUser(loginVO);
		return loginListCount;
	}

	@Override
	public List<LoginBO> listOfUsers(LoginBO loginBo) throws Exception {
		// TODO Auto-generated method stub
		List<LoginVO> loginList=new ArrayList<LoginVO>();
		List<LoginBO> loginListBO=new ArrayList<LoginBO>();
		LoginVO loginVO=new LoginVO();
		loginVO.setMaxRecord(loginBo.getMaxRecord());
		loginVO.setRecordIndex(loginBo.getRecordIndex());
		loginVO.setIsDelete(false);
		int sNo=loginBo.getRecordIndex();
		loginList=admindao.listOfUsers(loginVO);

		if(null!=loginList && loginList.size()>0 && !loginList.isEmpty()){
			for(LoginVO login:loginList){
				LoginBO loginBO=new LoginBO();
				CompanyBO company=new CompanyBO();
				StudentRegisterBO student=new StudentRegisterBO();
				loginBO.setLoginId(login.getLoginId());
				loginBO.setEmailAddress(login.getEmailAddress());
				String password=EncryptAndDecrypt.decrypt(login.getPassword(), EncryptAndDecrypt.TOKEN);
				loginBO.setPassword(password);
				loginBO.setUserType(login.getUserType());
				loginBO.setIsActive(login.getIsActive());
				loginBO.setIsDelete(login.getIsDelete());

				if(null!=login.getCompanyVO())
				{
					company.setCompanyId(login.getCompanyVO().getCompanyId());
					company.setCompanyName(login.getCompanyVO().getCompanyName());
					loginBO.setCompanyBO(company);
					loginBO.setName(login.getCompanyVO().getCompanyName());
					loginBO.setMobileNo(login.getCompanyVO().getContectNumber());
				}
				if(null!=login.getStudent())
				{
					student.setStudentRegisterId(login.getStudent().getStudentId());
					student.setFirstName(login.getStudent().getFirstName());
					loginBO.setStudentRegisterBO(student);		
					loginBO.setName(login.getStudent().getFirstName());
					loginBO.setMobileNo(login.getStudent().getMobileNo());
				}
				loginBO.setS_No(++sNo);
				loginListBO.add(loginBO);
			}

		}
		return loginListBO;

	}

	@Override
	public int searchUser(LoginBO loginBO) {
		// TODO Auto-generated method stub
		LoginVO loginVO=new LoginVO ();
		StudentRegisterVO studentVO=new StudentRegisterVO();
		CompanyVO companyVO=new CompanyVO();
		if(null !=loginBO.getEmailAddress()&&loginBO.getEmailAddress()!="")
		{
			loginVO.setEmailAddress(loginBO.getEmailAddress());

		}

		if(null!=loginBO.getName()&&loginBO.getName()!="")
		{
			studentVO.setFirstName(loginBO.getName());
			loginVO.setStudent(studentVO);
			companyVO.setCompanyName(loginBO.getName());
			loginVO.setCompanyVO(companyVO);

		}
		if(null!=loginBO.getMobileNo())
		{
			studentVO.setMobileNo(loginBO.getMobileNo());
			loginVO.setStudent(studentVO);
			companyVO.setContectNumber(loginBO.getMobileNo());
			loginVO.setCompanyVO(companyVO); 
		}

		int totalUser=admindao.searchUser(loginVO);
		return totalUser;

	}

	@Override
	public List<LoginBO> searchLoginData(LoginBO loginBO) throws Exception {
		// TODO Auto-generated method stub
		List<LoginVO> loginListVO=new ArrayList<LoginVO>();
		List<LoginBO> loginListBO=new ArrayList<LoginBO>();
		//List<LoginBO> listLoginBO=new ArrayList<LoginBO>();
		LoginVO loginVO=new LoginVO();
		StudentRegisterVO studentVO=new StudentRegisterVO();
		CompanyVO companyVO=new CompanyVO();
		loginVO.setRecordIndex(loginBO.getRecordIndex());
		loginVO.setMaxRecord(loginBO.getMaxRecord());
		loginVO.setUserType(loginBO.getUserType());

		if(null!=loginBO.getEmailAddress()&&loginBO.getEmailAddress()!=""){
			loginVO.setEmailAddress(loginBO.getEmailAddress());

		}

		if(null!=loginBO.getName()&&loginBO.getName()!="")
		{
			studentVO.setFirstName(loginBO.getName());
			loginVO.setStudent(studentVO);
			companyVO.setCompanyName(loginBO.getName());
			loginVO.setCompanyVO(companyVO);

		}
		if(null!=loginBO.getMobileNo())
		{
			studentVO.setMobileNo(loginBO.getMobileNo());
			loginVO.setStudent(studentVO);
			companyVO.setContectNumber(loginBO.getMobileNo());
			loginVO.setCompanyVO(companyVO); 
		}

		loginListVO=admindao.searchLoginData(loginVO);
		if(null!=loginListVO && loginListVO.size()>0 && !loginListVO.isEmpty()){
			int sNo=loginBO.getRecordIndex();
			for(LoginVO login:loginListVO){
				LoginBO loginbo=new LoginBO();
				CompanyBO company=new CompanyBO();
				StudentRegisterBO student=new StudentRegisterBO();
				loginbo.setLoginId(login.getLoginId());
				loginbo.setEmailAddress(login.getEmailAddress());
				loginbo.setUserType(login.getUserType());
				loginbo.setIsActive(login.getIsActive());
				loginbo.setIsDelete(login.getIsDelete());
				String password=EncryptAndDecrypt.decrypt(login.getPassword(), EncryptAndDecrypt.TOKEN);
				loginbo.setPassword(password);

				if(null!=login.getCompanyVO())
				{
					company.setCompanyId(login.getCompanyVO().getCompanyId());
					company.setCompanyName(login.getCompanyVO().getCompanyName());
					loginbo.setCompanyBO(company);
					loginbo.setName(login.getCompanyVO().getCompanyName());
					loginbo.setMobileNo(login.getCompanyVO().getContectNumber());
				}
				if(null!=login.getStudent())
				{
					student.setStudentRegisterId(login.getStudent().getStudentId());
					student.setFirstName(login.getStudent().getFirstName());
					loginBO.setStudentRegisterBO(student);		
					loginbo.setName(login.getStudent().getFirstName());
					loginbo.setMobileNo(login.getStudent().getMobileNo());
				}
				loginbo.setS_No(++sNo);
				loginListBO.add(loginbo);
			}

		}
		return loginListBO;

	}

	@Override
	public LoginBO editUser(LoginBO loginBo) throws Exception {
		// TODO Auto-generated method stub
		LoginVO loginVo=new LoginVO();
		LoginBO loginBO=new LoginBO();
		StudentRegisterBO student=new StudentRegisterBO();
		CompanyBO company=new CompanyBO();
		int editCount=0;
		loginVo.setLoginId(loginBo.getLoginId());

		loginVo=admindao.editUserCount(loginVo);
		if(null!=loginVo){
			loginBO.setLoginId(loginVo.getLoginId());
			loginBO.setUserType(loginVo.getUserType());
			loginBO.setEmailAddress(loginVo.getEmailAddress());
			String password=EncryptAndDecrypt.decrypt(loginVo.getPassword(), EncryptAndDecrypt.TOKEN);
			loginBO.setPassword(password);

			if(null!=loginVo.getCompanyVO())
			{
				company.setCompanyName(loginVo.getCompanyVO().getCompanyName());
				loginBO.setName(company.getCompanyName());
				company.setContectNumber(loginVo.getCompanyVO().getContectNumber());
				loginBO.setMobileNo(company.getContectNumber());
				loginBO.setCompanyBO(company);
			}
			if(null!=loginVo.getStudent())
			{
				student.setFirstName(loginVo.getStudent().getFirstName());
				loginBO.setName(student.getFirstName());
				student.setMobileNo(loginVo.getStudent().getMobileNo());
				loginBO.setMobileNo(student.getMobileNo());
				loginBO.setStudentRegisterBO(student);
			}

		}
		return loginBO;
	}

	@Override
	public boolean editUserData(LoginBO loginBo) throws Exception {
		// TODO Auto-generated method stub
		LoginVO loginVO=new LoginVO();
		StudentRegisterVO student=new StudentRegisterVO();
		CompanyVO company=new CompanyVO();
		//LoginBO loginBO=new LoginBO();
		boolean status=false;
		boolean status1=false;
		loginVO.setLoginId(loginBo.getLoginId());
		loginVO=admindao.editUserCount(loginVO);



		String password=EncryptAndDecrypt.encrypt(loginBo.getPassword(),EncryptAndDecrypt.TOKEN);
		loginVO.setPassword(password);
		loginVO.setModifiedBy(1);
		loginVO.setModifiedTime(new Date());
		loginVO.setIsActive(true);
		status=admindao.editUserData(loginVO); 
		if(status=true)
		{
			if(loginBo.getUserType().equals("student"))
			{
				student.setModifiedTime(new Date());
				student.setModifiedBy(1);
				student.setPassword(password);
				student.setStudentId(loginVO.getStudent().getStudentId());

				//loginVO.setStudent(student);
				status1=admindao.editUserData(student);
			}
			if(loginBo.getUserType().equals("company"))
			{
				company.setModifiedTime(new Date());
				company.setModifiedBy(1);
				company.setPassword(password);
				company.setCompanyId(loginVO.getCompanyVO().getCompanyId());

				//loginVO.setCompanyVO(company);
				status1=admindao.editUserData(company);
			}
		}

		return status1;
	}

	@Override
	public LoginBO getAdminProfile(long adminId) {
		// TODO Auto-generated method stub
		LoginVO loginVO=new LoginVO();
		LoginBO loginBO=new LoginBO();
		//loginVO.setLoginId(adminId);
		loginVO=admindao.getAdminProfile(adminId);
		loginBO.setLoginId(loginVO.getLoginId());
		loginBO.setEmailAddress(loginVO.getEmailAddress());
		loginBO.setUserType(loginVO.getUserType());
		return loginBO;
	}

	@Override
	public boolean deleteUser(LoginBO loginBo) {
		// TODO Auto-generated method stub
		LoginVO loginVO=new LoginVO();
		loginVO.setLoginId(loginBo.getLoginId());
		loginVO=admindao.editUserCount(loginVO);

		StudentRegisterVO student=new StudentRegisterVO();
		CompanyVO company=new CompanyVO();
		boolean status;
		boolean status1=false;

		loginVO.setIsDelete(true);
		loginVO.setSending_status(false);
		status=admindao.deleteUser(loginVO);
		if(status==true)
		{
			if(null!=loginVO.getCompanyVO())
			{
				company.setCompanyId(loginVO.getCompanyVO().getCompanyId());
				company.setIsDelete(true);
				company.setSending_status(false);
				status1=admindao.deleteCompany(company);
			}
			else
			{
				student.setStudentId(loginVO.getStudent().getStudentId());
				student.setIsDelete(true);
				student.setSending_status(false);
				status1=admindao.deleteStudent(student);
			}
		}

		return status1;

	}


	@Override
	public int deletecontact(int id) {
		// TODO Auto-generated method stub
		int deleteid=admindao.deletecontact(id);
		return 0;


	}

	/*@Override
	public ContactBO editcontact(String i) {
		// TODO Auto-generated method stub
		ContactBO editid=admindao.editcontact(i);
		return editid ;

	}*/



	@Override
	public ContactBO editcontact(int i) {
		// TODO Auto-generated method stub
		ContactBO editid=admindao.editcontact(i);
		return editid ;

	}



	@Override
	public int updatecontact(ContactBO bo1) {
		// TODO Auto-generated method stub
		int updateid=admindao.updatecontact(bo1);
		return updateid;


	}

	@Override
	public LoginBO viewUser(LoginBO loginBo) {
		// TODO Auto-generated method stub
		LoginVO loginVO=new LoginVO();
		loginVO.setLoginId(loginBo.getLoginId());
		loginVO=admindao.editUserCount(loginVO);
		if(null!=loginVO && loginVO.getUserType().equalsIgnoreCase("student")){
			loginBo.setUserType(loginVO.getUserType());
			StudentRegisterBO studentBO=new StudentRegisterBO();
			studentBO.setStudentRegisterId(loginVO.getStudent().getStudentId());
			loginBo.setStudentRegisterBO(studentBO);
		}
		else if(null!=loginVO && loginVO.getUserType().equalsIgnoreCase("company")){
			loginBo.setUserType(loginVO.getUserType());
			CompanyBO companyBO=new CompanyBO();
			companyBO.setCompanyId(loginVO.getCompanyVO().getCompanyId());
			loginBo.setCompanyBO(companyBO);
		}
		else if(null!=loginVO && loginVO.getUserType().equalsIgnoreCase("admin")){
			loginBo.setUserType(loginVO.getUserType());
			loginBo.setLoginId(loginVO.getLoginId());
			loginBo.setEmailAddress(loginVO.getEmailAddress());
			loginBo.setUserType(loginVO.getUserType());
		}
				
		return loginBo;
	}


}





