package com.scube.crm.dao;

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
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.scube.crm.bo.AdminUserBO;
import com.scube.crm.bo.CampaignBO;
import com.scube.crm.exception.MyClientsLogger;
import com.scube.crm.exception.MyClientsException;
import com.scube.crm.utils.ErrorCodes;
import com.scube.crm.vo.AccessLogVO;
import com.scube.crm.vo.User;
import com.scube.crm.vo.Campaign;
import com.scube.crm.vo.LoginStatusVO;

@Repository("campaignDAOImpl")
public class CampaignDAOImpl implements CampaignDAO {

	public CampaignDAOImpl() throws MyClientsException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Resource(name = "sessionFactory")
	SessionFactory sessionFactory;

	protected Session getSession() {

		return sessionFactory.getCurrentSession();

	}

	private static final MyClientsLogger LOGGER = MyClientsLogger.getLogger(CampaignDAOImpl.class);

	@Override
	public User authendicate(String string, String emailAddress) throws MyClientsException {
		CampaignDAOImpl.LOGGER.entry();
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
			if (CampaignDAOImpl.LOGGER.isDebugEnabled()) {
				CampaignDAOImpl.LOGGER.debug(ErrorCodes.ENTITY_CRE_FAIL + he);
			}
			throw new MyClientsException(ErrorCodes.ENTITY_CRE_FAIL, ErrorCodes.ENTITY_CRE_FAIL_MSG);
		} finally {

			CampaignDAOImpl.LOGGER.exit();
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
		
		CampaignDAOImpl.LOGGER.entry();
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
			CampaignDAOImpl.LOGGER.exit();
		}

		return accessLogStatus;

	}
	
	
	@Override
	public Campaign getCampaignId(CampaignBO campaignBO) {
		Session session = getSession();

		Campaign campaign = (Campaign) session.get(Campaign.class, campaignBO.getCampaignId());
		return campaign;
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
	public Campaign saveCompaign(Campaign campaign) {

		try {
			Session session = getSession();
			int id = (int) session.save(campaign);
			if (0 < id) {
				campaign.setCampaignId(id);
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("MobileNo Tracking History failed:" + e.getMessage());
			}
			LOGGER.info("MobileNo Tracking History failed:" + e.getMessage());

		}
		return campaign;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.scube.crm.dao.AdminDAO#listOfCampaign(com.scube.crm.bo.CampaignBO)
	 */
	@Override
	public List<Campaign> listOfCampaign(CampaignBO campaignBO) {
		
		List<Campaign> pagecampaignlist = new ArrayList<Campaign>();
		try {
			Session session = getSession();
			Criteria criteria = session.createCriteria(Campaign.class);
			criteria.add(Restrictions.eq("isDelete", false));
			
			if(null!= campaignBO.getUserName()){
				criteria.add(Restrictions.eq("assigned",Long.valueOf(campaignBO.getUserName())));
			}
			if( 0!= campaignBO.getId()){
				criteria.add(Restrictions.eq("id", campaignBO.getId()));
			}
			if (null != campaignBO.getCampaignName() && !campaignBO.getCampaignName().isEmpty()) {
				criteria.add(Restrictions.ilike("campaignName", campaignBO.getCampaignName(), MatchMode.ANYWHERE));
			}
			if (null != campaignBO.getCampaignMode() && !campaignBO.getCampaignMode().isEmpty()) {
				criteria.add(Restrictions.ilike("campaignMode", campaignBO.getCampaignMode(), MatchMode.ANYWHERE));
			}
			
			if (null != campaignBO && null != campaignBO.getProductServiceBO() &&
					  0<campaignBO.getProductServiceBO().getServiceId()) {
			  criteria.add(Restrictions.eq("productServiceVO.serviceId",campaignBO.getProductServiceBO().getServiceId())); 
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.scube.crm.dao.AdminDAO#getObject(int)
	 */
	@Override
	public Campaign getObject(int campaignId) {
		
		Campaign campaign = new Campaign();
		try {
			Session session = getSession();
			Criteria criteria = session.createCriteria(Campaign.class);
			criteria.add(Restrictions.eq("campaignId", campaignId));
			campaign = (Campaign) criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(e.getMessage());
			}
			LOGGER.info(e.getMessage());

		}

		return campaign;
	}

	@Override
	public boolean updateCampaign(Campaign campaign) {
		
		try {
			Session session = getSession();
			session.saveOrUpdate(campaign);
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

	@Override
	public boolean deleteCampaign(int idcam) {
		
		boolean status = false;
		int result = 0;
		final String deleteQuery = "UPDATE Campaign S set" + " S.isDelete = :isDelete"
				+ " WHERE S.campaignId = :campaignId";
		try {

			final Query query = getSession().createQuery(deleteQuery);
			query.setParameter("isDelete", true);
			query.setParameter("campaignId", idcam);
			result = query.executeUpdate();
			if (0 < result) {
				status = true;
			}
		} catch (final HibernateException he) {
			he.printStackTrace();
			if (CampaignDAOImpl.LOGGER.isDebugEnabled()) {
				CampaignDAOImpl.LOGGER.debug(ErrorCodes.ENTITY_DELETE_FAIL + he);
			}
		}
		return status;

	}
	public User retrieveParticularUser(User user) {
		
		User users = new User();
		try {
			Session session = getSession();
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("id", user.getId()));
		 users = (User) criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(e.getMessage());
			}
			LOGGER.info(e.getMessage());

		}

		return users;
	}

	}

