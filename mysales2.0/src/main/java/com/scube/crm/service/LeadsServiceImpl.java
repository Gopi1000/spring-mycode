package com.scube.crm.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scube.crm.bo.AccessLogBO;
import com.scube.crm.bo.AdminLoginBO;
import com.scube.crm.bo.AdminUserBO;
import com.scube.crm.bo.CampaignBO;
import com.scube.crm.bo.ClientBO;
import com.scube.crm.bo.LeadsBO;
import com.scube.crm.dao.CustomerDAOImpl;
import com.scube.crm.dao.LeadsDAO;
import com.scube.crm.exception.MyClientsException;
import com.scube.crm.exception.MyClientsLogger;
import com.scube.crm.model.EmailModel;
import com.scube.crm.vo.AccessLogVO;
import com.scube.crm.vo.Campaign;
import com.scube.crm.vo.FollowUp;
import com.scube.crm.vo.Leads;
import com.scube.crm.vo.LeadsFollowup;
import com.scube.crm.vo.LoginStatusVO;
import com.scube.crm.vo.User;

@Service("leadsService")
@Transactional
public class LeadsServiceImpl implements LeadsService {

	static final MyClientsLogger LOGGER = MyClientsLogger.getLogger(LeadsServiceImpl.class);
	// DAo layer annotations
	@Autowired
	private LeadsDAO leadsDAO;
	static boolean isApproval = true;
	EmailModel model = new EmailModel();

	@Override
	public AdminLoginBO authendicate(AdminLoginBO adminLoginBO) throws MyClientsException {
		LeadsServiceImpl.LOGGER.entry();

		final AdminLoginBO adminLogin = new AdminLoginBO();
		try {
			final User user = this.leadsDAO.authendicate("emailAddress", adminLoginBO.getEmailAddress());

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
		leadsDAO.editLoginStatus(loginStatusVO);
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
		leadsDAO.addLoginStatus(loginStatus);

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
			accessStatus = leadsDAO.createAccessLog(logVO);
		} catch (Exception e) {

		}
		return accessStatus;
	}

	@Override
	public List<CampaignBO> listOfCampaign(CampaignBO campaignBO) {


		List<Campaign> pagecampaignlist = new ArrayList<Campaign>();
		List<CampaignBO> pagecampaignlistBO = new ArrayList<CampaignBO>();
		pagecampaignlist = leadsDAO.listOfCampaign(campaignBO);
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

			pagecampaignlistBO.add(sampaignBO);
		}

		return pagecampaignlistBO;
	}

	@Override
	public List<LeadsBO> getListLeads(LeadsBO leadsBOs) throws MyClientsException {
		LeadsServiceImpl.LOGGER.entry();
		List<LeadsBO> listLeadsBo = new ArrayList<LeadsBO>();
		try {
		
		List<Leads> listLeadsVO = leadsDAO.getListLeads(leadsBOs);
		if(null!=listLeadsVO && listLeadsVO.size()>0 ) {
			int sNo = 0;
			for (Leads leads : listLeadsVO) {
				List<LeadsBO> listLeadsBO = new ArrayList<LeadsBO>();
				CampaignBO campaignBO = new CampaignBO();
				LeadsBO leadsBO = new LeadsBO();
				SimpleDateFormat simpleformat = new SimpleDateFormat("dd-MM-yyyy");
				leadsBO.setCreatedDate(simpleformat.format(leads	.getCreated()));
				leadsBO.setModifiedDate(simpleformat.format(leads.getModified()));
				leadsBO.setCreated(leads.getCreated());
				leadsBO.setLeadsId(leads.getLeadsId());
				leadsBO.setFirstName(leads.getFirstName());
				leadsBO.setLastName(leads.getLastName());
				leadsBO.setEmailAddress(leads.getEmailAddress());
				leadsBO.setCompanyName(leads.getCompanyName());
				leadsBO.setIndustryType(leads.getIndustryType());
				leadsBO.setWebsite(leads.getWebsite());
				leadsBO.setAddress(leads.getAddress());
				leadsBO.setMobileNo(leads.getMobileNo());
				leadsBO.setContactNo(leads.getContactNo());
				leadsBO.setsNo(++sNo);
				if (null != leads.getCampaignVO()) {
					int campid = leads.getCampaignVO().getCampaignId();
					campaignBO.setCampaignId(campid);
					campaignBO.setCampaignName(leads.getCampaignVO().getCampaignName());
					leadsBO.setCampaignBO(campaignBO);
				}
				if(null!=leads.getLeadeOwner()) {
					AdminLoginBO adminUserBO=new AdminLoginBO();
					adminUserBO.setName(leads.getLeadeOwner().getName());
					adminUserBO.setId(leads.getLeadeOwner().getId());
					leadsBO.setAdminLoginBO(adminUserBO);
				}
					if(null!=leadsBO) {
						//retrieve leads tracking details
						List<LeadsFollowup> trackingList= leadsDAO.retrieveTracking(leadsBO.getLeadsId());
						leadsBO.setLeadsUpdateVOList(trackingList);
					}
				
				listLeadsBO.add(leadsBO);
				listLeadsBo.addAll(listLeadsBO);
			}
		}
			LeadsServiceImpl.LOGGER.exit();
		} catch(Exception ex){
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("Update user has failed:" + ex.getMessage());
			}
			LOGGER.info("Update user has failed:" + ex.getMessage());
		}
		return listLeadsBo;
	}

	@Override
	public boolean deleteLeads(Integer leadsId) {
		long leadsid = 0;
		if (null != leadsId) {
			leadsid = Long.valueOf(leadsId);
		}
		boolean status = leadsDAO.deleteLeads(leadsid);
		return status;
	}

	@Override
	public LeadsBO getLeads(int leadsId) {		
		Leads leads = new Leads();
		LeadsBO leadsBO = new LeadsBO();
		
		leads = leadsDAO.getLeads(leadsId);
		if (null != leads) {
			leadsBO.setLeadsId(leads.getLeadsId());
			leadsBO.setFirstName(leads.getFirstName());
			leadsBO.setLastName(leads.getLastName());
			leadsBO.setEmailAddress(leads.getEmailAddress());
			leadsBO.setCompanyName(leads.getCompanyName());
			leadsBO.setIndustryType(leads.getIndustryType());
			leadsBO.setWebsite(leads.getWebsite());
			leadsBO.setAddress(leads.getAddress());
			leadsBO.setMobileNo(leads.getMobileNo());
			leadsBO.setContactNo(leads.getContactNo());
			if(null!=leads.getLeadeOwner()&&0!=leads.getLeadeOwner().getId()) {
				AdminLoginBO adminLoginBO=new AdminLoginBO();
				adminLoginBO.setId(leads.getLeadeOwner().getId());
				adminLoginBO.setName(leads.getLeadeOwner().getName());
				leadsBO.setAdminLoginBO(adminLoginBO);
			}

			if (null != leads.getCampaignVO()) {
				CampaignBO campaignBO=new CampaignBO();
				campaignBO.setCampaignId(leads.getCampaignVO().getCampaignId());
				campaignBO.setCampaignName(leads.getCampaignVO().getCampaignName());
				leadsBO.setCampaignBO(campaignBO);
			}
			
			if(null!=leadsBO) {
				//retrieve leads tracking details
				List<LeadsFollowup> trackingList= leadsDAO.retrieveTracking(leadsBO.getLeadsId());
				leadsBO.setLeadsUpdateVOList(trackingList);
			}
		}
		return leadsBO;
	}

	@Override
	public boolean updateLeads(LeadsBO leadsBO) throws MyClientsException {
		Leads leads = null;
		User user = new User();
		boolean status;
		leads = leadsDAO.getLeadsId(leadsBO);
		if (null!=leads) {
			leads.setFirstName(leadsBO.getFirstName());
			leads.setLastName(leadsBO.getLastName());
			leads.setEmailAddress(leadsBO.getEmailAddress());
			leads.setCompanyName(leadsBO.getCompanyName());
			leads.setIndustryType(leadsBO.getIndustryType());
			leads.setWebsite(leadsBO.getWebsite());
			leads.setAddress(leadsBO.getAddress());
			leads.setMobileNo(leadsBO.getMobileNo());
			leads.setContactNo(leadsBO.getContactNo());
			leads.setConvertedCustomer(leadsBO.isConvertedCustomer());
			// leadsVO.setCampaignVO(leadsBO.getCampaignBO().getCampaignName());
			leads.setIsDelete(false);
			leads.setModified(getDateWithoutTime(new Date()));
			leads.setModifiedBy(leadsBO.getModifiedBy());
			if (null != leadsBO.getAdminLoginBO()) {
				user.setId(leadsBO.getAdminLoginBO().getId());
				leads.setAdminLoginVO(user);
			}
			if (null != leadsBO.getUserName()) {
				String id = leadsBO.getUserName();
				long id1 = 0;
				if (null != id && !id.isEmpty()) {
					id1 = Long.parseLong(id);
				}
				user.setId(id1);
				leads.setLeadeOwner(user);
			}
			if (null != leadsBO.getCampaignBO() && null!=leadsBO.getCampaignBO().getCampaignId()) {
				
				Campaign campaign = new Campaign();
				campaign.setCampaignId(leadsBO.getCampaignBO().getCampaignId());
				leads.setCampaignVO(campaign);
			}
		}
		status = leadsDAO.updateLead(leads);
		return status;

	}

	public static Date getDateWithoutTime(Date date) { 
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0); 
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0); 
		cal.set(Calendar.MILLISECOND, 0); 
		return  cal.getTime(); 
	}

	@Override
	public long saveLeads(LeadsBO leadsBO) {
		User user=new User();
		Leads leads = new Leads();

		if (null != leadsBO) {
			leads.setFirstName(leadsBO.getFirstName());
			leads.setLastName(leadsBO.getLastName());
			leads.setEmailAddress(leadsBO.getEmailAddress());
			leads.setCompanyName(leadsBO.getCompanyName());
			leads.setIndustryType(leadsBO.getIndustryType());
			leads.setWebsite(leadsBO.getWebsite());
			leads.setAddress(leadsBO.getAddress());
			leads.setMobileNo(leadsBO.getMobileNo());
			leads.setContactNo(leadsBO.getContactNo());
			//user.setId(leadsBO.getAdminLoginBO().getId());
			//user.setActive(true);
			//	leads.setAdminLoginVO(user);
			leads.setIsDelete(false);
			
			
			  if (null != leadsBO.getAdminLoginBO()) { 
			 user.setId(leadsBO.getAdminLoginBO().getId());
			  leads.setLeadeOwner(user); 
			  }
			 
			
			if (null != leadsBO.getUserName()) {
				String id = leadsBO.getUserName();
				long id1 = 0;
				if (null != id && !id.isEmpty()) {
					id1 = Long.parseLong(id);
				}
				user.setId(id1);
				leads.setLeadeOwner(user);
			}
			if (null != leadsBO.getCampaignBO() && !leadsBO.getCampaignBO().getCampaignName().isEmpty()) {
				String campids = leadsBO.getCampaignBO().getCampaignName();
				int id = 0;
				if (null != campids && !campids.isEmpty()) {
					id = Integer.parseInt(campids);
				}
				Campaign campaign = new Campaign();
				campaign.setCampaignId(id);
				leads.setCampaignVO(campaign);
			}

		}
		return leadsDAO.saveLeads(leads);
	}

	@Override
	public boolean findByMobilenoLeads(String mobileNo) {
		return leadsDAO.findByMobilenoLeads(mobileNo);
	}

	@Override
	public boolean findByEmailLeads(String emailAddress) {
		try {
			Leads leads = leadsDAO.findByEmailLeads("emailAddress", emailAddress);

			if (null!=leads.getEmailAddress() && !leads.getEmailAddress().isEmpty()) {
				return true;
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<AdminUserBO> retrieveUser() throws MyClientsException {
		List<AdminUserBO> BOList = new ArrayList<AdminUserBO>();
		BOList = leadsDAO.retrieveUser();
		return BOList;
	}

	@Override
	public boolean sendClientMail(ClientBO employerRegisterBO) {

		return false;
	}

	@Override
	public LeadsBO saveTracking(LeadsBO bO) {
		LeadsFollowup VO=new LeadsFollowup();
		Leads leadsVO=new Leads();
		User loginVO=new User();

		leadsVO.setLeadsId(bO.getLeadsId());
		VO.setLeads(leadsVO);

		loginVO.setId(bO.getAdminLoginBO().getId());
		VO.setUserVO(loginVO);

		if(null !=bO.getDescription() && !bO.getDescription().isEmpty()){
			VO.setDescription(bO.getDescription());
		}
		if(null !=bO.getDate()){
			VO.setDate(bO.getDate());
		}
		if(null !=bO.getNextAppointmentDate()){
			VO.setNextAppointmentDate(bO.getNextAppointmentDate());
		}
		if(null !=bO.getStatus()){
			leadsVO.setStatus(bO.getStatus());
			VO.setLeads(leadsVO);
		}
		VO=leadsDAO.saveTracking(VO);
		if(0 != VO.getLeadsupdateid()){
			bO.setLeadsId(VO.getLeads().getLeadsId());
		}else{
			bO = null;
		}
		return bO;	
	}

	@Override
	public List<LeadsBO> searchRetrieveTracking(LeadsBO listLeadsBO) {
		List<LeadsFollowup> leadsFollowuplist = new ArrayList<LeadsFollowup>();
		LeadsFollowup leadsFollowup=new LeadsFollowup();
		List<LeadsBO> leadsListBO = new ArrayList<LeadsBO>();
		User userVO=new User();
		try {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(null!=listLeadsBO.getStartDate()) {
		String startDate=df.format(listLeadsBO.getStartDate());
		Date  toDate = df.parse(startDate);
		leadsFollowup.setCreated(toDate);
		leadsFollowup.setModified(null);
		}if(null!=listLeadsBO.getEndDate()) {
			leadsFollowup.setCreated(null);
			String endDate=df.format(listLeadsBO.getEndDate());
			Date  toDate = df.parse(endDate);
			leadsFollowup.setModified(toDate);
		}
		userVO.setId(listLeadsBO.getAdminLoginBO().getId());
		leadsFollowup.setUserVO(userVO);
		leadsFollowuplist=leadsDAO.searchRetrieveTracking(leadsFollowup);
		AtomicInteger sNo=new AtomicInteger(0);
		if (null != leadsFollowuplist && !leadsFollowuplist.isEmpty() && 0<leadsFollowuplist.size()) {
				leadsFollowuplist.forEach(leadsFollow->{
				AdminLoginBO adminLoginBO=new AdminLoginBO();
				CampaignBO campaignBO = new CampaignBO();
				LeadsBO leadsBO = new LeadsBO();
				leadsBO.setLeadsId(leadsFollow.getLeads().getLeadsId());
				leadsBO.setFirstName(leadsFollow.getLeads().getFirstName());
				leadsBO.setEmailAddress(leadsFollow.getLeads().getEmailAddress());
				if(0<leadsFollow.getLeads().getLeadeOwner().getId()) {
				adminLoginBO.setId(leadsFollow.getLeads().getLeadeOwner().getId());
				leadsBO.setAdminLoginBO(adminLoginBO);
				}
				if(0<leadsFollow.getLeads().getCampaignVO().getCampaignId()) {
					campaignBO.setCampaignId(leadsFollow.getLeads().getCampaignVO().getCampaignId());
					campaignBO.setCampaignName(campaignBO.getCampaignName());
					leadsBO.setCampaignBO(campaignBO);
				}
				SimpleDateFormat simpleformat = new SimpleDateFormat("dd-MM-yyyy");
				leadsBO.setCreatedDate(simpleformat.format(leadsFollow.getLeads().getCreated()));
				leadsBO.setModifiedDate(simpleformat.format(leadsFollow.getLeads().getModified()));
				leadsBO.setCreated(leadsFollow.getCreated());
				leadsBO.setsNo(sNo.incrementAndGet());
				leadsListBO.add(leadsBO);
				});
			}
		}catch (Exception he) {
			he.printStackTrace();
			LeadsServiceImpl.LOGGER.debug(he, he.getMessage());
        }
		return leadsListBO;
		}

	@Override
	public long getAnyAppointment(long leadsId) {
		return leadsDAO.getAnyAppointment(leadsId);
	}
}