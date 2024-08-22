package com.scube.crm.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.scube.crm.bo.AdminUserBO;
import com.scube.crm.bo.CampaignBO;
import com.scube.crm.bo.LeadsBO;
import com.scube.crm.exception.MyClientsLogger;
import com.scube.crm.exception.MyClientsException;
import com.scube.crm.utils.ErrorCodes;
import com.scube.crm.vo.AccessLogVO;
import com.scube.crm.vo.User;
import com.scube.crm.vo.Campaign;
import com.scube.crm.vo.FollowUp;
import com.scube.crm.vo.Leads;
import com.scube.crm.vo.LeadsFollowup;
import com.scube.crm.vo.LoginStatusVO;

/**
 * Owner : Scube Technologies Created Date: Nov-22-2014 Created by : Vinoth P
 * Description : JobSeekerDAOImpl is a Class which is responsible for storing
 * the data into the database Reviewed by :
 * 
 * 
 */

@Repository("leadsDAOImpl")
public class LeadsDAOImpl implements LeadsDAO {

	public LeadsDAOImpl() throws MyClientsException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Resource(name = "sessionFactory")
	SessionFactory sessionFactory;

	protected Session getSession() {

		return sessionFactory.getCurrentSession();

	}

	private static final MyClientsLogger LOGGER = MyClientsLogger.getLogger(LeadsDAOImpl.class);

	@Override
	public User authendicate(String string, String emailAddress) throws MyClientsException {
		LeadsDAOImpl.LOGGER.entry();
		User user = null;
		final String loginQuery = "FROM User E WHERE E.emailAddress = :emailAddress";
		try {
			Session session = getSession();
			final Query query = session.createQuery(loginQuery);
			session.flush();
			query.setParameter("emailAddress", emailAddress);
			if (null != query.list() && query.list().size() > 0) {
				user = (User) query.list().get(0);
			}

		} catch (final HibernateException he) {
			he.printStackTrace();
			if (LeadsDAOImpl.LOGGER.isDebugEnabled()) {
				LeadsDAOImpl.LOGGER.debug(ErrorCodes.ENTITY_CRE_FAIL + he);
			}
			throw new MyClientsException(ErrorCodes.ENTITY_CRE_FAIL, ErrorCodes.ENTITY_CRE_FAIL_MSG);
		} finally {

			LeadsDAOImpl.LOGGER.exit();
		}

		return user;

	}

	@Override
	public LoginStatusVO editLoginStatus(LoginStatusVO loginStatusVO) {

		Criteria criteria = getSession().createCriteria(LoginStatusVO.class);
		criteria.add(Restrictions.eq("userName", loginStatusVO.getUserName()));
		criteria.add(Restrictions.eq("status", true));
		if (null != criteria.list() && criteria.list().size() > 0) {
			LoginStatusVO statusvo = (LoginStatusVO) criteria.list().get(0);
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, 1);
			Date date = cal.getTime();
			statusvo.setEndTime(date);
			statusvo.setStatus(false);
			statusvo.setActivity("logout");
			getSession().update(statusvo);
			return statusvo;
		}
		return new LoginStatusVO();
	}

	@Override
	public long addLoginStatus(LoginStatusVO loginStatus) {

		long id = 0;
		try {

			Session session = getSession();
			session.saveOrUpdate(loginStatus);
			session.flush();
		} catch (Exception he) {
			he.printStackTrace();
			LOGGER.debug(he.getMessage() + he);
		}

		return id;
	}

	@Override
	public boolean createAccessLog(AccessLogVO logVO) {
		
		LeadsDAOImpl.LOGGER.entry();
		boolean accessLogStatus = false;
		try {
			Session session = getSession();
			long logId = (Long) session.save(logVO);
			session.flush();
			if (0 != logId) {
				accessLogStatus = true;
			}
		} catch (HibernateException he) {
			he.printStackTrace();

		} finally {
			LeadsDAOImpl.LOGGER.exit();
		}

		return accessLogStatus;

	}

	@Override
	public List<AdminUserBO> retrieveUser() {
		AdminUserBO adminuserBO = new AdminUserBO();
		List<AdminUserBO> BOList = new ArrayList<AdminUserBO>();
		List<User> VOList = new ArrayList<User>();
		try {
			int count = 1;
			Criteria cr = getSession().createCriteria(User.class);
			cr.add(Restrictions.eq("isDelete", false));
			if (null != adminuserBO.getName() && !adminuserBO.getName().isEmpty()) {
				cr.add(Restrictions.ilike("name", adminuserBO.getName(), MatchMode.ANYWHERE));
			}
			if (null != adminuserBO.getEmailAddress() && !adminuserBO.getEmailAddress().isEmpty()) {
				cr.add(Restrictions.ilike("emailAddress", adminuserBO.getEmailAddress(), MatchMode.ANYWHERE));
			}
			if (null != adminuserBO.getUserType() && !adminuserBO.getUserType().isEmpty()) {
				cr.add(Restrictions.ilike("userType", adminuserBO.getUserType(), MatchMode.ANYWHERE));
			}
			// cr.add(Restrictions.eq("isActive", true));
			cr.addOrder(Order.desc("created"));
			VOList = cr.list();

			if (null != VOList && !VOList.isEmpty()) {
				int data;
				for (User vo : VOList) {
					data = count++;
					adminuserBO = new AdminUserBO();
					adminuserBO.setId(vo.getId());
					adminuserBO.setActive(vo.isActive());
					adminuserBO.setsNo(data);
					adminuserBO.setName(vo.getName());
					adminuserBO.setMobileNo(vo.getMobileNo());
					adminuserBO.setUserType(vo.getUserType());
					adminuserBO.setPassword(vo.getPassword());
					adminuserBO.setEmailAddress(vo.getEmailAddress());
					adminuserBO.setConfirmPassword(vo.getConfirmpassword());
					if (vo.isActive()) {
						adminuserBO.setStatus("Active");
					} else {
						adminuserBO.setStatus("De-Active");
					}
					BOList.add(adminuserBO);
				}
			}
		} catch (Exception ex) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Retrieve user has failed:" + ex.getMessage());
			}
			LOGGER.info("Retrieve user has failed:" + ex.getMessage());
		}
		return BOList;
	}

	@Override
	public long saveLeads(Leads leads) {
		
		long id = 0;
		try {
			Session session = getSession();
			id = (long) session.save(leads);
			if (0 < id) {
				leads.setLeadsId(id);
			}
		}

		catch (final HibernateException he) {
			he.printStackTrace();
			if (LeadsDAOImpl.LOGGER.isDebugEnabled()) {
				LeadsDAOImpl.LOGGER.debug(ErrorCodes.ENTITY_DELETE_FAIL + he);
			}
		}
		return id;

	}

	@Override
	public List<Leads> getListLeads(LeadsBO leadsBO) {
		
		List<Leads> getList = new ArrayList<Leads>();
		try {
			Session session = getSession();
			Criteria criteria = session.createCriteria(Leads.class);
			criteria.add(Restrictions.eq("isDelete", false));
			criteria.add(Restrictions.eq("convertedCustomer", false));
			if( 0<leadsBO.getLeadsId()){
				criteria.add(Restrictions.eq("leadsId", leadsBO.getLeadsId()));
			}
			if( null!=leadsBO.getAdminLoginBO() && 
					 0< leadsBO.getAdminLoginBO().getId() && 0<leadsBO.getLeadsId()){
				criteria.add(Restrictions.eq("leadeOwner.id", leadsBO.getAdminLoginBO().getId()));
			}
			
			if (null != leadsBO.getFirstName() && !leadsBO.getFirstName().isEmpty()) {
				criteria.add(Restrictions.ilike("firstName", leadsBO.getFirstName(), MatchMode.ANYWHERE));
			}
			if (null != leadsBO.getEmailAddress() && !leadsBO.getEmailAddress().isEmpty()) {
				criteria.add(Restrictions.ilike("emailAddress", leadsBO.getEmailAddress(), MatchMode.ANYWHERE));
			}
			if (null != leadsBO.getMobileNo() && !leadsBO.getMobileNo().isEmpty()) {
				criteria.add(Restrictions.ilike("mobileNo", leadsBO.getMobileNo(), MatchMode.ANYWHERE));
			}
			  if (null != leadsBO && null != leadsBO.getCampaignBO() &&
					  null!=leadsBO.getCampaignBO().getCampaignId()) {
			  criteria.add(Restrictions.eq("campaignVO.campaignId",leadsBO.getCampaignBO().getCampaignId())); 
			  }
			  
			  //Reports search
			  //startdate search all
			  if(null!=leadsBO.getStartDate()  && leadsBO.getProcess().equalsIgnoreCase("all")
						&&null==leadsBO.getEndDate()){
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String startDate=df.format(leadsBO.getStartDate());
					Date fromDate = df.parse(startDate);
					if(null!=fromDate ) {
						criteria.add(Restrictions.eq("created",leadsBO.getStartDate()));
					}
				}
			  //startDate create process
			  if(null!=leadsBO.getStartDate()  && leadsBO.getProcess().equalsIgnoreCase("create")
						&&null==leadsBO.getEndDate()){
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String startDate=df.format(leadsBO.getStartDate());
					Date fromDate = df.parse(startDate);
					if(null!=fromDate ) {
						criteria.add(Restrictions.eq("created",fromDate));
					}
				}
			  //enddate search all 
			  if(null!=leadsBO.getEndDate() &&
			  leadsBO.getProcess().equalsIgnoreCase("all") ){ 
			  SimpleDateFormat df = new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			  String  toDate=df.format(leadsBO.getEndDate()); 
			  Date fromDate =df.parse(toDate);
			  if(null!=fromDate ) {
			  criteria.add(Restrictions.eq("modified",fromDate)); 
			  } }
			  //enddate search process create 
			  if(null!=leadsBO.getEndDate() &&
			  leadsBO.getProcess().equalsIgnoreCase("create") ){ 
			  SimpleDateFormat df = new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			  String  toDate=df.format(leadsBO.getEndDate()); 
			  Date fromDate =df.parse(toDate);
			  if(null!=fromDate ) {
			  criteria.add(Restrictions.eq("modified",fromDate)); 
			  } }
			 
			  
			  //startdate and enddate all
			
			  if(null!=leadsBO.getStartDate() && null!=leadsBO.getEndDate() &&
			  leadsBO.getProcess().equalsIgnoreCase("all")){ 
			  SimpleDateFormat df = new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			  String startDate=df.format(leadsBO.getStartDate()); 
			  String endDate=df.format(leadsBO.getEndDate()); 
			  Date fromDate = df.parse(startDate);
			  Date toDate = df.parse(endDate);
			  
			  if(fromDate.equals(toDate)){
			  //cr.add(Restrictions.ge("created",clientBO.getStarDate()));
			  criteria.add(Restrictions.eq("created", leadsBO.getEndDate())); 
			  }}
			 
			if (1< leadsBO.getUserId()) {
				 criteria.add(Restrictions.eq("leadeOwner.id",leadsBO.getUserId())); 
			}
			getList = criteria.list();
			if(null!=getList && !getList.isEmpty()) {
				return getList;
			}
		}

		catch (final HibernateException he) {
			he.printStackTrace();
			if (LeadsDAOImpl.LOGGER.isDebugEnabled()) {
				LeadsDAOImpl.LOGGER.debug(ErrorCodes.ENTITY_DELETE_FAIL + he);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public List<Campaign> listOfCampaign(CampaignBO campaignBO) {
		
		List<Campaign> pagecampaignlist = new ArrayList<Campaign>();
		try {
			Session session = getSession();
			Criteria criteria = session.createCriteria(Campaign.class);
			criteria.add(Restrictions.eq("isDelete", false));
			if (null != campaignBO && 1< campaignBO.getAdminLoginBO().getId()) {
				criteria.add(Restrictions.eq("user.id", campaignBO.getAdminLoginBO().getId()));
			}
			/*
			 * if(null!=campaignBO.getAdminLoginBO().getName() &&
			 * !campaignBO.getAdminLoginBO().getName().isEmpty()) {
			 * criteria.add(Restrictions.ilike("name",
			 * campaignBO.getAdminLoginBO().getName(),MatchMode.ANYWHERE)); }
			 */
			if (null != campaignBO.getCampaignName() && !campaignBO.getCampaignName().isEmpty()) {
				criteria.add(Restrictions.ilike("campaignName", campaignBO.getCampaignName(), MatchMode.ANYWHERE));
			}
			if (null != campaignBO.getCampaignMode() && !campaignBO.getCampaignMode().isEmpty()) {
				criteria.add(Restrictions.ilike("campaignMode", campaignBO.getCampaignMode(), MatchMode.ANYWHERE));
			}
			if (1< campaignBO.getUserId()) {
				 criteria.add(Restrictions.eq("user.id",campaignBO.getUserId())); 
				 }
			pagecampaignlist = criteria.list();
		}

		catch (Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("MobileNo Tracking History failed:" + e.getMessage());
			}
			LOGGER.info("MobileNo Tracking History failed:" + e.getMessage());

		}
		return pagecampaignlist;
	}

	@Override
	public Leads getLeads(long leadsId) {

		Leads leads = new Leads();
		try {
			Session session = getSession();
			Criteria criteria = session.createCriteria(Leads.class);
			criteria.add(Restrictions.eq("leadsId", Long.valueOf(leadsId)));
			leads = (Leads) criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(e.getMessage());
			}
			LOGGER.info(e.getMessage());

		}

		return leads;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.scube.crm.dao.AdminDAO#updateLead(com.scube.crm.vo.Leads)
	 */
	@Override
	public boolean updateLead(Leads leads) {
		try {
			Session session = getSession();
			session.saveOrUpdate(leads);
			getSession().flush();
		} catch (Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(e.getMessage());
			}
			LOGGER.info(e.getMessage());

		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.scube.crm.dao.AdminDAO#deleteLeads(int)
	 */
	@Override
	public boolean deleteLeads(long leadsid) {
		final String deleteQuery = "UPDATE Leads S set" + " S.isDelete = :isDelete" + " WHERE S.leadsId = :leadsId";
		boolean status = false;
		try {
			int result = 0;
			final Query query = getSession().createQuery(deleteQuery);
			query.setParameter("isDelete", true);
			query.setParameter("leadsId", leadsid);
			result = query.executeUpdate();
			if (0 < result) {
				status = true;
			}
		} catch (final HibernateException he) {
			he.printStackTrace();
			if (LeadsDAOImpl.LOGGER.isDebugEnabled()) {
				LeadsDAOImpl.LOGGER.debug(ErrorCodes.ENTITY_DELETE_FAIL + he);
			}
		}
		return status;

	}

	@Override
	public Leads findByEmailLeads(String string, String emailAddress) {
		LeadsDAOImpl.LOGGER.entry();
		Leads leads = null;
		final String loginQuery = "FROM Leads E WHERE E.emailAddress = :emailAddress";
		try {
			Session session = getSession();
			final Query query = session.createQuery(loginQuery);
			query.setParameter("emailAddress", emailAddress);
			if (null != query.list() && query.list().size() > 0) {
				leads = (Leads) query.list().get(0);
			}
		} catch (final HibernateException he) {
			he.printStackTrace();
			if (LeadsDAOImpl.LOGGER.isDebugEnabled()) {
				LeadsDAOImpl.LOGGER.debug(he, he.getMessage());
			}
		} finally {

			LeadsDAOImpl.LOGGER.exit();
		}
		return leads;
	}

	@Override
	public boolean findByMobilenoLeads(String mobileNo) {
		Leads leads = null;
		try {

			Session session = getSession();
			Criteria criteria = session.createCriteria(Leads.class);
			criteria.add(Restrictions.eq("mobileNo", mobileNo));
			leads = (Leads) criteria.list();
			if(null!=leads.getMobileNo() &&!leads.getMobileNo().isEmpty()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("MobileNo Tracking History failed:" + e.getMessage());
			}
			LOGGER.info("MobileNo Tracking History failed:" + e.getMessage());

		} finally {
			LeadsDAOImpl.LOGGER.exit();
		}
		return false;
	}

	@Override
	public Leads getLeadsId(LeadsBO leadsBO) {
		Session session = getSession();

		Leads leads = (Leads) session.get(Leads.class, leadsBO.getLeadsId());
		return leads;
	}

	@Override
	public List<LeadsFollowup> retrieveTracking(long leadsId) {
	
		List<LeadsFollowup> trackingList=null;
		try {

			Session session = getSession();
			Criteria criteria = session.createCriteria(LeadsFollowup.class);
			criteria.add(Restrictions.eq("leads.leadsId", leadsId));			
			if (null != criteria.list() && criteria.list().size() > 0) {
				trackingList = criteria.list();

			}
		} catch (Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("MobileNo Tracking History failed:" + e.getMessage());
			}
			LOGGER.info("MobileNo Tracking History failed:" + e.getMessage());

		} 
		return trackingList;
	}

	@Override
	public LeadsFollowup saveTracking(LeadsFollowup vO) {
		try{	
			Session session = getSession();
			long id=(long) session.save(vO);
			vO.setLeadsupdateid(id);
		} catch (final HibernateException he) {			
			he.printStackTrace();			
		}
		return vO;
		
	}

	@Override
	public List<LeadsFollowup> searchRetrieveTracking(LeadsFollowup leadsFollowup) {
		
		List<LeadsFollowup> leadsFollowupList=new ArrayList<LeadsFollowup>();
		try {
			Session session = getSession();
			Criteria criteria = session.createCriteria(LeadsFollowup.class);
			//criteria.add(Restrictions.eq("isDelete", false));
			if (null != leadsFollowup && 1< leadsFollowup.getUserVO().getId()) {
				criteria.add(Restrictions.eq("userVO.id", leadsFollowup.getUserVO().getId()));
			}
			//start date created 
			if (null != leadsFollowup.getCreated() ) {
				criteria.add(Restrictions.eq("created",leadsFollowup.getCreated()));
			}
			if (null != leadsFollowup.getModified() ) {
				criteria.add(Restrictions.eq("modified",leadsFollowup.getModified()));
			}
			leadsFollowupList = criteria.list();
		}catch (Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("MobileNo Tracking History failed:" + e.getMessage());
			}
			LOGGER.info("MobileNo Tracking History failed:" + e.getMessage());

		}
		return leadsFollowupList;
	}

	@Override
	public long getAnyAppointment(long leadsId) {
		long count = 0;
		try {
			Session session = getSession();
			Criteria criteria = session.createCriteria(LeadsFollowup.class);
			criteria.add(Restrictions.eq("leads.leadsId", leadsId));	
			count = (long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			if (0<count) {
				return count;
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("No Leads Tracking History failed:" + e.getMessage());
			}
			LOGGER.info("No Leads  Tracking History failed:" + e.getMessage());

		} 
		return count;
	}
}
