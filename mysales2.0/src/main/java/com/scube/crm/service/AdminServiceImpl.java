package com.scube.crm.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scube.crm.bo.AccessLogBO;
import com.scube.crm.bo.AdminLoginBO;
import com.scube.crm.bo.AdminUserBO;
import com.scube.crm.bo.BaseBO;
import com.scube.crm.bo.CampaignBO;
import com.scube.crm.bo.ClientBO;
import com.scube.crm.bo.LeadsBO;
import com.scube.crm.bo.RoleBO;
import com.scube.crm.dao.AdminDAO;
import com.scube.crm.exception.MyClientsLogger;
import com.scube.crm.exception.MyClientsException;
import com.scube.crm.model.EmailModel;
import com.scube.crm.utils.EncryptAndDecrypt;
import com.scube.crm.utils.SendEmailServiceImpl;
import com.scube.crm.utils.SuccessMsg;
import com.scube.crm.vo.AccessLogVO;
import com.scube.crm.vo.User;
import com.scube.crm.vo.Campaign;
import com.scube.crm.vo.Contact;
import com.scube.crm.vo.EmailAccess;
import com.scube.crm.vo.Leads;
import com.scube.crm.vo.LoginStatusVO;
import com.scube.crm.vo.RoleVO;

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

	static final MyClientsLogger LOGGER = MyClientsLogger.getLogger(AdminServiceImpl.class);
	// DAo layer annotations
	@Autowired
	private AdminDAO adminDAO;
	static boolean isApproval = true;
	EmailModel model = new EmailModel();

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private SendEmailServiceImpl emailManager;

	@Override
	public AdminLoginBO authendicate(AdminLoginBO adminLoginBO) throws MyClientsException {
		AdminServiceImpl.LOGGER.entry();

		final AdminLoginBO adminLogin = new AdminLoginBO();
		try {
			final User user = this.adminDAO.authendicate("emailAddress", adminLoginBO.getEmailAddress());

			/*
			 * if (null != adminLoginVO) { final String password =
			 * EncryptAndDecrypt.decrypt( adminLoginVO.getPassword(),
			 * EncryptAndDecrypt.TOKEN);
			 */
			if (adminLoginBO.getPassword().equals(user.getPassword())) {
				if (user.isActive()) {
					BeanUtils.copyProperties(user, adminLogin);
					String userName = adminLoginBO.getEmailAddress();
					addLoginStatus(userName);
					adminLogin.setActive(true);
				} else {
					adminLogin.setActive(false);
				}
			} else {
				adminLogin.setActive(false);
			}
			// adminLogin.setPassword(password);

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
	public boolean addLoginStatus(String username) throws MyClientsException {

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

	@Override
	public AdminUserBO createuser(AdminUserBO adminBO) throws MyClientsException {

		User adminVO = new User();
		try {
			BeanUtils.copyProperties(adminBO, adminVO);
			adminVO.setActive(true);
			adminVO.setDelete(false);
			adminVO.setConfirmpassword(EncryptAndDecrypt.encrypt(adminBO.getConfirmPassword()));
			adminVO.setPassword(EncryptAndDecrypt.encrypt(adminBO.getPassword()));
			long id = adminDAO.createuser(adminVO);
			if (id > 0) {
				adminBO.setId(id);
			}

		} catch (Exception ex) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Add user has failed:" + ex.getMessage());
			}
			LOGGER.info("Add user has failed:" + ex.getMessage());
		}

		return adminBO;

	}

	@Override
	public List<AdminUserBO> retrieveUser() throws MyClientsException {
		List<AdminUserBO> BOList = new ArrayList<AdminUserBO>();
		BOList = adminDAO.retrieveUser();
		return BOList;
	}

	
@Override
	public AdminUserBO updateuser(AdminUserBO adminBO) {
		try {
			User loginVO = new User();

		//	loginVO = adminDAO.getuserId(adminBO);

			loginVO.setMobileNo(adminBO.getMobileNo());
			loginVO.setName(adminBO.getName());
			loginVO.setEmailAddress(adminBO.getEmailAddress());
			loginVO.setPassword(EncryptAndDecrypt.encrypt(adminBO.getPassword()));
			loginVO.setConfirmpassword(EncryptAndDecrypt.encrypt(adminBO.getConfirmPassword()));
			loginVO.setUserType(adminBO.getUserType());
			loginVO.setModified(new Date());
			loginVO.setModifiedBy(adminBO.getUserId());
			loginVO.setId(adminBO.getId());
			loginVO.setActive(true);
			loginVO = adminDAO.updateuser(loginVO);

			if (0 != loginVO.getId()) {
				adminBO.setId(loginVO.getId());
			} else {
				adminBO = new AdminUserBO();
			}
		} catch (Exception ex) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Update user has failed:" + ex.getMessage());
			}
			LOGGER.info("Update user has failed:" + ex.getMessage());
		}
		return adminBO;

	}

	
	
	public static Date getDateWithoutTime(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.scube.crm.service.AdminService#deleteCampaign(java.lang.String)
	 */
	
	
	@Override
	public boolean userStatus(AdminUserBO userBO) throws MyClientsException {
		boolean loginChanged = false;

		User userVO = new User();

		if (0 != userBO.getId()) {
			userVO.setId(userBO.getId());
			userVO.setActive(userBO.isActive());
			userVO = adminDAO.userStatus(userVO);
			if (0 != userVO.getId()) {
				loginChanged = true;
			}
		}
		return loginChanged;

	}

	@Override
	public AdminUserBO deleteProfile(AdminUserBO deleteProfile) throws MyClientsException {
		AdminServiceImpl.LOGGER.entry();

		final User VO = new User();
		int result;
		try {
			VO.setId(deleteProfile.getId());
			VO.setDelete(true);
			result = adminDAO.deleteProfile(VO);
			if (result != 0) {
				deleteProfile.setResponse(SuccessMsg.DELETE_SUCCESS);
			}
		} catch (MyClientsException e) {
			
			e.printStackTrace();
		}

		return deleteProfile;
	}

	@Override
	public AdminUserBO retrieveusers(long userId) throws MyClientsException {

		AdminUserBO adminBO = new AdminUserBO();

		adminBO = adminDAO.retrieveusers(userId);
		return adminBO;

	}

	@Override
	public boolean findByEmail(String emailAddress) {
		try {
			final User loginVO = adminDAO.findByEmail("emailAddress", emailAddress);

			if (loginVO != null) {
				return true;
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	
	
	
	

	@Override
	public long campaignCount(AdminLoginBO adminLoginBO) {
			return adminDAO.campaignCount(adminLoginBO);
	}
	
	
	 @Override public boolean findByMobilenoEmployee(String mobileNo) { return
			  adminDAO.findByMobilenoEmployee(mobileNo); }
	 @Override public List<AdminUserBO> searchEmployeeList(AdminUserBO adminBO) {
		 
		  List<AdminUserBO> adminUserBOList = new ArrayList<AdminUserBO>();
		  List<User>  adminUserList = new ArrayList<User>(); 
		  adminUserList = adminDAO.searchEmployeeList(adminBO);
		  int sno = 1; 
		  for (User user : adminUserList) { 
			  AdminUserBO adminUserBO = new AdminUserBO();
		  adminUserBO.setAdminId(user.getId());
		 adminUserBO.setEmailAddress(user.getEmailAddress());
		  adminUserBO.setConfirmPassword(user.getConfirmpassword());
		  adminUserBO.setPassword(user.getPassword());
		  adminUserBO.setMobileNo(user.getMobileNo());
		  adminUserBO.setUserType(user.getUserType());
		  adminUserBO.setsNo(sno++);
		  
		  AdminLoginBO adminLoginVO = null;
			/*
			 * if(adminLoginVO.isActive()){ ((BaseBO) adminUserBOList).setStatus("Active");
			 * } else{ ((BaseBO) adminUserBOList).setStatus("De-Active"); }
			 */
		  
		  adminUserBOList.add(adminUserBO); 
		  } 
		  return adminUserBOList; 
		  }
	 
	
	 @Override public long employeesCount(AdminLoginBO adminLoginBO) { return
			  adminDAO.employeesCount(adminLoginBO); }
			  @Override public long leadsCount(AdminLoginBO adminLoginBO) { return
			  adminDAO.leadsCount(adminLoginBO); }
			  
			  @Override public long customerCount(AdminLoginBO adminLoginBO) { return
			  adminDAO.customerCount(adminLoginBO); }
	
			  @Override
				public boolean sendClientMail(ClientBO employerRegisterBO) {
					boolean alertStatus = false;
					try {
						EmailModel model = new EmailModel();

						final String[] toaddress = employerRegisterBO.getEmailAddress().split(",");
						final String subject = messageSource.getMessage("Validate.RegisterConfirm",null,null);
						for (int i = 0; i < toaddress.length; i++) {
							String bodycontent = "employerVerificationMail";
							model.setUrl("www.myjobkart.com/find-jobs.html?companyId=");
							model.setFirstname(employerRegisterBO.getFirstName());
							model.setEmail(employerRegisterBO.getEmailAddress());
							alertStatus = emailManager.sendEmail(toaddress[i], subject, bodycontent, model);
						}
						if (alertStatus) {
							EmailAccess accessVO = new EmailAccess();
							accessVO.setDate(new Date());
							accessVO.setEmailAddress(employerRegisterBO.getEmailAddress());
							accessVO.setMailedBy(employerRegisterBO.getEmployerId());
							accessVO.setStatus(alertStatus);
							accessVO.setMailTO(employerRegisterBO.getId());
							List<EmailAccess> accessVOList = new ArrayList<EmailAccess>();
							accessVOList.add(accessVO);

							if (null != accessVOList || accessVOList.size() > 0) {
								adminDAO.saveEmailList(accessVOList);
							}
						}

					} catch (final Exception ex) {
					}

					return alertStatus;
				}
			  
			  public long getCurrentActionCustomers(AdminLoginBO adminLoginBO) {
				return adminDAO.getCurrentActionCustomers(adminLoginBO);
				  
			  }

			@Override
			public long productCount(AdminLoginBO adminLoginBO) {
				return adminDAO.productCount(adminLoginBO);
			}
			
			/*public RoleBO insertrole(RoleBO rolebo) {
				
				RoleVO rolevo=new RoleVO();
				
				rolevo.setId(rolebo.getId());
				rolevo.setRolename(rolebo.getRolename());	
				
				rolevo=adminDAO.insertrole(rolevo);
				
				return rolebo;
			}
			
			public List<RoleBO> retrieverole(List<RoleBO> rolebo) {
				
				List<RoleBO> rolebo1=new ArrayList<RoleBO>();
				List<RoleVO> rolevo=new ArrayList<RoleVO>();
				
				rolevo=adminDAO.retrieverole();
				
				int sno = 1;
				for (RoleVO rolevo1 : rolevo) {
					
					RoleBO bo=new RoleBO();
					
					bo.setId(rolevo1.getId());
					bo.setRolename(rolevo1.getRolename());
					bo.setSno(sno++);
					
					rolebo1.add(bo);
				}
				
				return rolebo1;
			}*/

}
