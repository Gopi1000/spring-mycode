package com.scube.crm.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scube.crm.bo.AccessLogBO;
import com.scube.crm.bo.AdminBO;
import com.scube.crm.bo.AdminLoginBO;
import com.scube.crm.bo.AdminUserBO;
import com.scube.crm.bo.EmployerRegisterBO;
import com.scube.crm.dao.AdminDAO;
import com.scube.crm.exception.JLogger;
import com.scube.crm.exception.MyJobKartException;
import com.scube.crm.model.EmailModel;
import com.scube.crm.utils.MyjobkartResourceBundle;
import com.scube.crm.utils.SendEmailServiceImpl;
import com.scube.crm.utils.SuccessMsg;
import com.scube.crm.vo.AccessLogVO;
import com.scube.crm.vo.AdminLoginVO;
import com.scube.crm.vo.AdminVO;
import com.scube.crm.vo.EmailAccessVO;
import com.scube.crm.vo.LoginStatusVO;


/**
 * Owner : Scube Technologies Created Date: Nov-22-2014 Created by :
 * Sathishkumar.s Description : JobSeekerServiceImpl Class is a Class which is
 * responsible for access the data from Controller then convert it into
 * persistent Object then sent it into DAO class. Reviewed by :
 * 
 * 
 */

@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService {

	static final JLogger LOGGER = JLogger.getLogger(AdminServiceImpl.class);
	// DAo layer annotations
	@Autowired
	private AdminDAO adminDAO;
	static boolean isApproval = true;
	EmailModel model = new EmailModel();

	@Autowired
	private SendEmailServiceImpl emailManager;

	@Override
	public AdminLoginBO authendicate(AdminLoginBO adminLoginBO)
			throws MyJobKartException {
		AdminServiceImpl.LOGGER.entry();

		final AdminLoginBO adminLogin = new AdminLoginBO();
		try {
			final AdminLoginVO adminLoginVO = this.adminDAO.authendicate(
					"emailAddress", adminLoginBO.getEmailAddress());

			/*if (null != adminLoginVO) {
				final String password = EncryptAndDecrypt.decrypt(
						adminLoginVO.getPassword(), EncryptAndDecrypt.TOKEN);*/
			if (adminLoginBO.getPassword().equals(adminLoginVO.getPassword())) {
				if(adminLoginVO.isActive()){
					BeanUtils.copyProperties(adminLoginVO, adminLogin);
					String userName = adminLoginBO.getEmailAddress();
					addLoginStatus(userName);
					adminLogin.setActive(true);
				}
				else{
					adminLogin.setActive(false);
				}
			}
			else {
				adminLogin.setActive(false);
			}
			//adminLogin.setPassword(password);

			/* */
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return adminLogin;
	}


	@Override
	public boolean editLoginStatus(LoginStatusVO loginStatusVO) {
		adminDAO.editLoginStatus(loginStatusVO);
		return false;

	}
	@Override
	public boolean addLoginStatus(String username) throws MyJobKartException {

		LoginStatusVO loginStatus = new LoginStatusVO();
		loginStatus.setUserName(username);
		loginStatus.setType("Admin");
		loginStatus.setStatus(true);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		Date date = cal.getTime();
		loginStatus.setStartTime(date);
		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.MONTH, 1);
		Date date1 = cal.getTime();
		loginStatus.setEndTime(date1);
		loginStatus.setActivity("login");
		adminDAO.addLoginStatus(loginStatus);

		return false;
	}



	@Override
	public boolean createAccessLog(AccessLogBO logBO) {
		boolean accessStatus = false;
		try {
			AccessLogVO logVO = new AccessLogVO();
			logVO.setAccessId(logBO.getAccessId());
			logVO.setAccessDate(logBO.getAccessDate());
			logVO.setClientIP(logBO.getClientIP());
			logVO.setSessionId(logBO.getSessionId());
			accessStatus = adminDAO.createAccessLog(logVO);
		} catch (Exception e) {

		}
		return accessStatus;
	}


	/* (non-Javadoc)
	 * @see com.scube.crm.service.AdminService#createuser(com.scube.crm.bo.AdminUserBO)
	 */
	@Override
	public AdminUserBO createuser(AdminUserBO adminBO)throws MyJobKartException {


		AdminLoginVO adminVO=new AdminLoginVO();
		try{
			BeanUtils.copyProperties(adminBO, adminVO);
			adminVO.setActive(true);
			adminVO.setDelete(false);
			adminVO.setConfirmpassword(adminBO.getConfirmPassword());
			long id=adminDAO.createuser(adminVO);
			if(id>0){
				adminBO.setId(id);
			}


		}catch (Exception ex) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Add user has failed:" + ex.getMessage());
			}
			LOGGER.info("Add user has failed:" + ex.getMessage());
		}

		return adminBO;

	}
	
	public AdminBO createcustomer(AdminBO adminBO)throws MyJobKartException {
		AdminVO adminVO=new AdminVO();
		try{
			BeanUtils.copyProperties(adminBO, adminVO);
			adminVO.setActive(true);
			adminVO.setDelete(false);
			//adminVO.setConfirmpassword(adminBO.getConfirmPassword());
			int id=adminDAO.createcustomer(adminVO);
			if(id>0){
				adminBO.setAdminId(id);
			}


		}catch (Exception ex) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Add user has failed:" + ex.getMessage());
			}
			LOGGER.info("Add user has failed:" + ex.getMessage());
		}

		return adminBO;
	}


	/* (non-Javadoc)
	 * @see com.scube.crm.service.AdminService#getusercount(com.scube.crm.bo.AdminUserBO)
	 */
	/*	@Override
	public long getusercount(AdminUserBO bo) throws MyJobKartException {
		return adminDAO.getusercount(bo);
	}*/


	/* (non-Javadoc)
	 * @see com.scube.crm.service.AdminService#retrieveUser()
	 */
	@Override
	public List<AdminUserBO> retrieveUser() throws MyJobKartException {
		List<AdminUserBO>BOList=new ArrayList<AdminUserBO>();
		BOList = adminDAO.retrieveUser();
		return BOList;
	}
	
	@Override
	public List<AdminBO> retrieveCustomer() throws MyJobKartException {
		List<AdminBO>BOList=new ArrayList<AdminBO>();
		BOList = adminDAO.retrieveCustomer();
		return BOList;
	}


	/* (non-Javadoc)
	 * @see com.scube.crm.service.AdminService#userStatus(com.scube.crm.bo.AdminUserBO)
	 */
	@Override
	public boolean userStatus(AdminUserBO userBO) throws MyJobKartException {
		boolean loginChanged = false;

		AdminLoginVO userVO = new AdminLoginVO();

		if (0 != userBO.getId()) {
			userVO.setId(userBO.getId());
			userVO.setActive(userBO.isActive());
			userVO =adminDAO.userStatus(userVO);
			if (0 != userVO.getId()) {
				loginChanged = true;
			}
		}
		return loginChanged;


	}
	
	/*public boolean customerStatus(AdminBO userBO)throws MyJobKartException {
		boolean loginChanged = false;

		AdminVO userVO = new AdminVO();

		if (0 != userBO.getAdminId()) {
			userVO.setAdminId(userBO.getAdminId());
			userVO.setActive(userBO.getIsActive());
			userVO =adminDAO.customerStatus(userVO);
			if (0 != userVO.getAdminId()) {
				loginChanged = true;
			}
		}
		return loginChanged;


	}*/

	/* (non-Javadoc)
	 * @see com.scube.crm.service.AdminService#DeleteProfile(com.scube.crm.bo.AdminUserBO)
	 */
	@Override
	public AdminUserBO deleteProfile(AdminUserBO deleteProfile) throws MyJobKartException{
		EmployerServiceImpl.LOGGER.entry();

		final AdminLoginVO VO = new AdminLoginVO();
		int result;
		try {
			VO.setId(deleteProfile.getId());
			VO.setDelete(true);
			result = adminDAO.deleteProfile(VO);
			if (result != 0) {
				deleteProfile.setResponse(SuccessMsg.DELETE_SUCCESS);
			}
		} catch (MyJobKartException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return deleteProfile;
	}


	/* (non-Javadoc)
	 * @see com.scube.crm.service.AdminService#retrieveusers(com.scube.crm.bo.AdminUserBO)
	 */
	@Override
	public AdminUserBO retrieveusers(long userId) throws  MyJobKartException{

		AdminUserBO adminBO = new AdminUserBO();

		adminBO = adminDAO.retrieveusers(userId);
		return adminBO;

	}


	public AdminBO retrieveCustomers(int userId)throws MyJobKartException {
		AdminBO adminBO = new AdminBO();

		adminBO = adminDAO.retrieveCustomers(userId);
		return adminBO;

	}

	


	/* (non-Javadoc)
	 * @see com.scube.crm.service.AdminService#getusercount(com.scube.crm.bo.AdminUserBO)
	 */
	@Override
	public long getusercount(AdminUserBO bo) {
		// TODO Auto-generated method stub
		return 0;
	}


	/* (non-Javadoc)
	 * @see com.scube.crm.service.AdminService#updateuser(com.scube.crm.bo.AdminUserBO)
	 */
	@Override
	public AdminUserBO updateuser(AdminUserBO adminBO) {
		try{
			AdminLoginVO loginVO=new AdminLoginVO();

			loginVO = adminDAO.getuserId(adminBO);

			loginVO.setMobileNo(adminBO.getMobileNo());
			loginVO.setName(adminBO.getName());
			loginVO.setEmailAddress(adminBO.getEmailAddress());
			loginVO.setPassword(adminBO.getPassword());
			loginVO.setConfirmpassword(adminBO.getConfirmPassword());
			loginVO.setUserType(adminBO.getUserType());
			loginVO.setModified(new Date());
			loginVO.setModifiedBy(adminBO.getUserId());

			loginVO=adminDAO.updateuser(loginVO);

			if(0 != loginVO.getId()){
				adminBO.setId(loginVO.getId());
			}else{
				adminBO = new AdminUserBO();
			}
		} catch(Exception ex){
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("Update user has failed:" + ex.getMessage());
			}
			LOGGER.info("Update user has failed:" + ex.getMessage());
		}
		return  adminBO;

	}
	
	
	public AdminBO updatecustomer(AdminBO adminBO) {
		try{
			AdminVO loginVO=new AdminVO();

			loginVO = adminDAO.getadminId(adminBO);

			loginVO.setAdminId(adminBO.getAdminId());
			loginVO.setClientName(adminBO.getClientName());
			loginVO.setPrimaryContactNumber(adminBO.getPrimaryContactNumber());
			loginVO.setSecondaryContactNumber(adminBO.getSecondaryContactNumber());
			loginVO.setFinalContactNumber(adminBO.getFinalContactNumber());
			loginVO.setPrimaryAddress(adminBO.getPrimaryAddress());
			loginVO.setSecondaryAddress(adminBO.getSecondaryAddress());
			loginVO.setPermanentAddress(adminBO.getPermanentAddress());
			
			loginVO.setModified(new Date());
			loginVO.setModified(new Date());
			
			loginVO.setActive(true);
			loginVO.setActive(false);
			
			loginVO.setVersion(adminBO.getVersion());

			loginVO=adminDAO.updatecustomer(loginVO);

			if(0 != loginVO.getAdminId()){
				adminBO.setAdminId(loginVO.getAdminId());
			}else{
				adminBO = new AdminBO();
			}
		} catch(Exception ex){
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("Update user has failed:" + ex.getMessage());
			}
			LOGGER.info("Update user has failed:" + ex.getMessage());
		}
		return  adminBO;

	}


	/* (non-Javadoc)
	 * @see com.scube.crm.service.AdminService#findByEmail(java.lang.String)
	 */
	@Override
	public boolean findByEmail(String emailAddress) {
		try {
			final AdminLoginVO loginVO = adminDAO.findByEmail(
					"emailAddress", emailAddress);

			if (loginVO != null) {
				return true;
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	
	@Override
	public boolean findByMobileNo(String mobileNo) {
		
		try {
			final AdminLoginVO loginVO = adminDAO.findByMobileNo(
					"mobileNo", mobileNo);

			if (loginVO != null) {
				return true;
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	/* (non-Javadoc)
	 * @see com.scube.crm.service.AdminService#fineEmployerEmail(java.lang.String)
	 */
	@Override
	public boolean findEmployerEmail(String emailAddress) {
		// TODO Auto-generated method stub

		boolean validationEmail =adminDAO.findEmployerEmail(emailAddress);
		return validationEmail;
	}


	/* (non-Javadoc)
	 * @see com.scube.crm.service.AdminService#sendClientMail(com.scube.crm.bo.EmployerRegisterBO)
	 */
	@Override
	public boolean sendClientMail(EmployerRegisterBO employerRegisterBO) {
		boolean alertStatus=false;
		try {
			EmailModel model = new EmailModel();

			final String[] toaddress = employerRegisterBO.getEmailAddress().split(",");
					final String subject = MyjobkartResourceBundle
					.getValue("Validate.RegisterConfirm");
			for(int i=0;i<toaddress.length;i++){
				String bodycontent = "employerVerificationMail";
				model.setUrl("www.myjobkart.com/find-jobs.html?companyId=");
				model.setFirstname(employerRegisterBO.getFirstName());
				model.setEmail(employerRegisterBO.getEmailAddress());
				alertStatus=emailManager.sendEmail(toaddress[i], subject, bodycontent, model);
			}
			if(alertStatus){
				EmailAccessVO accessVO=new EmailAccessVO();
				accessVO.setDate(new Date());
				accessVO.setEmailAddress(employerRegisterBO.getEmailAddress());
				accessVO.setMailedBy(employerRegisterBO.getEmployerId());
				accessVO.setStatus(alertStatus);
				accessVO.setMailTO(employerRegisterBO.getId());
				List<EmailAccessVO>accessVOList=new ArrayList<EmailAccessVO>();
				accessVOList.add(accessVO);

				if(null!=accessVOList || accessVOList.size() >0){
					adminDAO.saveEmailList(accessVOList);
				}
			}

		} catch (final Exception ex) {
		}

		return alertStatus;
	}

}
