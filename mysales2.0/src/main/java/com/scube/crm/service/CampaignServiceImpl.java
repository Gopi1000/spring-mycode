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
import com.scube.crm.bo.CampaignBO;
import com.scube.crm.bo.ClientBO;
import com.scube.crm.bo.LeadsBO;
import com.scube.crm.bo.ProductServiceBO;
import com.scube.crm.dao.AdminDAO;
import com.scube.crm.dao.CampaignDAO;
import com.scube.crm.exception.MyClientsLogger;
import com.scube.crm.exception.MyClientsException;
import com.scube.crm.model.EmailModel;
import com.scube.crm.utils.SendEmailServiceImpl;
import com.scube.crm.utils.SuccessMsg;
import com.scube.crm.vo.AccessLogVO;
import com.scube.crm.vo.User;
import com.scube.crm.vo.Campaign;
import com.scube.crm.vo.EmailAccess;
import com.scube.crm.vo.Leads;
import com.scube.crm.vo.LoginStatusVO;
import com.scube.crm.vo.ProductServiceVO;


@Service("campaignService")
@Transactional
public class CampaignServiceImpl implements CampaignService {

	static final MyClientsLogger LOGGER = MyClientsLogger.getLogger(CampaignServiceImpl.class);
	// DAo layer annotations
	@Autowired
	private AdminDAO adminDAO;
	static boolean isApproval = true;
	EmailModel model = new EmailModel();

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private SendEmailServiceImpl emailManager;

	@Autowired
	private CampaignDAO campaignDAO;

	@Override
	public AdminLoginBO authendicate(AdminLoginBO adminLoginBO) throws MyClientsException {
		CampaignServiceImpl.LOGGER.entry();

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
	public List<AdminUserBO> retrieveUser() throws MyClientsException {
		List<AdminUserBO> BOList = new ArrayList<AdminUserBO>();
		BOList = adminDAO.retrieveUser();
		return BOList;
	}

	@Override
	public boolean sendClientMail(ClientBO employerRegisterBO) {
		boolean alertStatus = false;
		try {
			EmailModel model = new EmailModel();

			final String[] toaddress = employerRegisterBO.getEmailAddress().split(",");
			final String subject = messageSource.getMessage("Validate.RegisterConfirm", null, null);
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

	@Override
	public CampaignBO saveCompaign(CampaignBO campaignBO) {

		User user = new User();
		Campaign campaign = new Campaign();
		campaign.setCampaignName(campaignBO.getCampaignName());
		campaign.setStartedTime(campaignBO.getStartedTime());
		campaign.setEndTime(campaignBO.getEndTime());
		campaign.setCampaignMode(campaignBO.getCampaignMode());
		//campaign.setProductName(campaignBO.getProducteName());
		campaign.setIsDelete(false);
		campaign.setCampaignOwner(campaignBO.getCampaignOwner());
		if (null != campaignBO.getAdminLoginBO()) {
			user.setId(campaignBO.getAdminLoginBO().getId());
			campaign.setUser(user);
		}
		//product dropdown
		if (null != campaignBO.getProductServiceBO() && !campaignBO.getProductServiceBO() .getServiceName().isEmpty()) {
			String productIds= campaignBO.getProductServiceBO() .getServiceName();
			long id = 0;
			if (null != productIds && !productIds.isEmpty()) {
				id = Long.parseLong(productIds);
			}
			ProductServiceVO productServiceVO = new ProductServiceVO();
			productServiceVO.setServiceId(id);
			campaign.setProductServiceVO(productServiceVO);
		}
		Campaign campaigns =campaignDAO.saveCompaign(campaign);
		if (0 < campaign.getCampaignId()) {
			campaignBO.setCampaignId(campaigns.getCampaignId());
		}

		return campaignBO;
	}

	@Override
	public List<CampaignBO> listOfCampaign(CampaignBO campaignBO) {


		List<Campaign> pagecampaignlist = new ArrayList<Campaign>();
		List<CampaignBO> pagecampaignlistBO = new ArrayList<CampaignBO>();
		pagecampaignlist = campaignDAO.listOfCampaign(campaignBO);
		int sno = 1;
		for (Campaign ampaignVO : pagecampaignlist) {
			CampaignBO sampaignBO = new CampaignBO();
			sampaignBO.setCampaignName(ampaignVO.getCampaignName());
			sampaignBO.setStartedTime(ampaignVO.getStartedTime());
			sampaignBO.setEndTime(ampaignVO.getEndTime());
			sampaignBO.setCampaignMode(ampaignVO.getCampaignMode());
			//sampaignBO.setProducteName(ampaignVO.getProductName());
			sampaignBO.setCampaignId(ampaignVO.getCampaignId());
			sampaignBO.setCampaignOwner(ampaignVO.getCampaignOwner());
			sampaignBO.setsNo(sno++);
			if (null != ampaignVO.getUser()) {
				sampaignBO.setUserName(ampaignVO.getUser().getName());
				sampaignBO.setUserId(ampaignVO.getUser().getId());
			}
			if (null != ampaignVO.getProductServiceVO()) {
				ProductServiceBO productServiceBO=new ProductServiceBO(); 
				productServiceBO.setServiceName(ampaignVO.getProductServiceVO().getServiceName());
				sampaignBO.setProductServiceBO(productServiceBO);
			}
			pagecampaignlistBO.add(sampaignBO);
		}

		return pagecampaignlistBO;
	}

	@Override
	public CampaignBO getObject(String campaignId) {

		int id = 0;
		Campaign ampaignVO = new Campaign();
		CampaignBO sampaignBO = new CampaignBO();
		if (null != campaignId) {
			id = Integer.parseInt(campaignId);
		}
		ampaignVO = campaignDAO.getObject(id);
		if (null != ampaignVO) {
			sampaignBO.setCampaignName(ampaignVO.getCampaignName());
			sampaignBO.setStartedTime(ampaignVO.getStartedTime());
			sampaignBO.setEndTime(ampaignVO.getEndTime());
			sampaignBO.setCampaignMode(ampaignVO.getCampaignMode());
			//sampaignBO.setProducteName(ampaignVO.getProductName());
			sampaignBO.setCampaignId(ampaignVO.getCampaignId());
			sampaignBO.setCampaignOwner(ampaignVO.getCampaignOwner());
			if (null != ampaignVO.getUser()) {
				AdminLoginBO adminLoginBO = new AdminLoginBO();
				adminLoginBO.setId(ampaignVO.getUser().getId());
				adminLoginBO.setFirstName(ampaignVO.getUser().getName());
				sampaignBO.setUserId(ampaignVO.getUser().getId());
				sampaignBO.setUserName(ampaignVO.getUser().getName());
				sampaignBO.setAdminLoginBO(adminLoginBO);
			}
			if (null != ampaignVO.getProductServiceVO()) {
				ProductServiceBO productServiceBO=new ProductServiceBO();
				productServiceBO.setServiceId(ampaignVO.getProductServiceVO().getServiceId());
				productServiceBO.setServiceName(ampaignVO.getProductServiceVO().getServiceName());
				sampaignBO.setProductServiceBO(productServiceBO);
			}
		}
		return sampaignBO;
	}

	@Override
	public boolean updateCampaign(CampaignBO campaignBO) {

		Campaign campaign = new Campaign();
		User user = new User();
		campaign = campaignDAO.getCampaignId(campaignBO);
		if (0 != campaignBO.getCampaignId()) {
			campaign.setCampaignName(campaignBO.getCampaignName());
			campaign.setStartedTime(campaignBO.getStartedTime());
			campaign.setEndTime(campaignBO.getEndTime());
			campaign.setCampaignMode(campaignBO.getCampaignMode());
			//campaign.setProductName(campaignBO.getProducteName());
			// campaignVO.setCampaignId(campaignBO.getCampaignId());
			campaign.setCampaignOwner(campaignBO.getCampaignOwner());
			campaign.setIsDelete(false);
			campaign.setModified(getDateWithoutTime(new Date()));
			campaign.setModifiedBy(campaignBO.getModifiedBy());
			if (null != campaignBO.getAdminLoginBO()) {
				user.setId(campaignBO.getAdminLoginBO().getId());
				campaign.setUser(user);
			}
			if (null != campaignBO.getProductServiceBO() && 0<campaignBO.getProductServiceBO().getServiceId()) {
				ProductServiceVO productServiceVO = new ProductServiceVO();
				productServiceVO.setServiceId(campaignBO.getProductServiceBO().getServiceId());
				campaign.setProductServiceVO(productServiceVO);
			}
		}
		boolean status = campaignDAO.updateCampaign(campaign);

		return status;
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

	@Override
	public boolean deleteCampaign(String campaignId) {

		int id = 0;
		if (null != campaignId) {
			id = Integer.parseInt(campaignId);
		}

		boolean status = campaignDAO.deleteCampaign(id);

		return status;
	}

	public AdminUserBO retrieveParticularUser(long id)
	{
		User user=new User();
		user.setId(id);
		user= campaignDAO.retrieveParticularUser(user);
		AdminUserBO AdminUserBO=new AdminUserBO();
		if(null!=user&&null!=user.getName()) {
			AdminUserBO.setName(user.getName());
			AdminUserBO.setId(user.getId());

		}
		return AdminUserBO;




	}
}
