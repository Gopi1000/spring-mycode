package com.scube.crm.dao;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scube.crm.bo.AdminLoginBO;
import com.scube.crm.bo.AdminUserBO;
import com.scube.crm.bo.CampaignBO;
import com.scube.crm.bo.LeadsBO;
import com.scube.crm.exception.MyClientsLogger;
import com.scube.crm.exception.MyClientsException;
import com.scube.crm.utils.DateHelper;
import com.scube.crm.utils.EncryptAndDecrypt;
import com.scube.crm.utils.ErrorCodes;
import com.scube.crm.vo.AccessLogVO;
import com.scube.crm.vo.User;
import com.scube.crm.vo.Campaign;
import com.scube.crm.vo.Contact;
import com.scube.crm.vo.Customer;
import com.scube.crm.vo.EmailAccess;
import com.scube.crm.vo.FollowUp;
import com.scube.crm.vo.Leads;
import com.scube.crm.vo.LeadsFollowup;
import com.scube.crm.vo.LoginStatusVO;
import com.scube.crm.vo.ProductServiceVO;
import com.scube.crm.vo.RoleVO;


@Repository("adminDAOImpl")
public class AdminDAOImpl implements AdminDAO {

	public AdminDAOImpl() throws MyClientsException {
		super();
		// TODO Auto-generated constructor stub
	}


	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {

		return sessionFactory.getCurrentSession();

	}

	private static final MyClientsLogger LOGGER = MyClientsLogger.getLogger(AdminDAOImpl.class);

	@Override
	public User authendicate(String string, String emailAddress) throws MyClientsException {
		AdminDAOImpl.LOGGER.entry();
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
			if (AdminDAOImpl.LOGGER.isDebugEnabled()) {
				AdminDAOImpl.LOGGER.debug(ErrorCodes.ENTITY_CRE_FAIL + he);
			}
			throw new MyClientsException(ErrorCodes.ENTITY_CRE_FAIL, ErrorCodes.ENTITY_CRE_FAIL_MSG);
		} finally {

			AdminDAOImpl.LOGGER.exit();
		}

		return user;

	}

	@Override
	public User authendicate(String emailAddress) throws MyClientsException {
		AdminDAOImpl.LOGGER.entry();
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
			if (AdminDAOImpl.LOGGER.isDebugEnabled()) {
				AdminDAOImpl.LOGGER.debug(ErrorCodes.ENTITY_CRE_FAIL + he);
			}
			throw new MyClientsException(ErrorCodes.ENTITY_CRE_FAIL, ErrorCodes.ENTITY_CRE_FAIL_MSG);
		} finally {

			AdminDAOImpl.LOGGER.exit();
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

		AdminDAOImpl.LOGGER.entry();
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
			AdminDAOImpl.LOGGER.exit();
		}

		return accessLogStatus;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.scube.crm.dao.AdminDAO#createuser(com.scube.crm.vo.AdminUserVO)
	 */
	@Override
	public long createuser(User adminVO) {
		AdminDAOImpl.LOGGER.entry();

		try {
			Session session = getSession();
			long logId = (Long) session.save(adminVO);
			session.flush();
			if (0 != logId) {
				return logId;
			}
		} catch (HibernateException he) {
			he.printStackTrace();

		} finally {
			AdminDAOImpl.LOGGER.exit();
		}
		return 0;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.scube.crm.dao.AdminDAO#userStatus(com.scube.crm.vo.AdminUserVO)
	 */
	@Override
	public User userStatus(User userVO) {
		final String changeLoginQuery = "UPDATE User E set E.isActive = :isActive WHERE E.id= :id";
		try {
			// session = getSession();
			final Query query = getSession().createQuery(changeLoginQuery);
			query.setParameter("isActive", userVO.isActive());
			query.setParameter("id", userVO.getId());
			final int result = query.executeUpdate();
			if (0 != result) {
				return userVO;
			}
		} catch (final HibernateException he) {
			he.printStackTrace();
			if (AdminDAOImpl.LOGGER.isDebugEnabled()) {
				AdminDAOImpl.LOGGER.debug(he.getMessage() + he);
			}
		} finally {

			AdminDAOImpl.LOGGER.exit();
		}
		return null;

	}


	@Override
	public int deleteProfile(User vo) {
		AdminDAOImpl.LOGGER.entry();
		int result = 0;

		final String deleteQuery = "UPDATE User S set" + " S.isDelete = :isDelete" + " WHERE S.id = :id";
		try {

			final Query query = getSession().createQuery(deleteQuery);
			query.setParameter("isDelete", vo.isDelete());
			query.setParameter("id", vo.getId());
			result = query.executeUpdate();
		} catch (final HibernateException he) {
			he.printStackTrace();
			if (AdminDAOImpl.LOGGER.isDebugEnabled()) {
				AdminDAOImpl.LOGGER.debug(ErrorCodes.ENTITY_DELETE_FAIL + he);
			}

		}

		return result;
	}


	@Override
	public User updateuser(User loginVO) throws MyClientsException {

		AdminDAOImpl.LOGGER.entry();
		try {
			Session session = getSession();
			session.saveOrUpdate(loginVO);
			getSession().flush();
		} catch (final HibernateException he) {
			he.printStackTrace();
			if (AdminDAOImpl.LOGGER.isDebugEnabled()) {
				AdminDAOImpl.LOGGER.debug(ErrorCodes.ENTITY_UPDATE_FAIL + he);
			}
			throw new MyClientsException(ErrorCodes.ENTITY_UPDATE_FAIL, ErrorCodes.ENTITY_UPDATE_FAIL_MSG);
		} finally {

			AdminDAOImpl.LOGGER.exit();
		}
		return loginVO;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.scube.crm.dao.AdminDAO#retrieveusers(long)
	 */
	@Override
	public AdminUserBO retrieveusers(long userId) {
		AdminUserBO adminuserBO = new AdminUserBO();
		List<AdminUserBO> BOList = new ArrayList<AdminUserBO>();
		List<User> VOList = new ArrayList<User>();
		try {
			int count = 0;
			Criteria cr = getSession().createCriteria(User.class);
			cr.add(Restrictions.eq("isDelete", false));
			// cr.add(Restrictions.eq("isActive", true));
			cr.add(Restrictions.eq("id", userId));
			cr.addOrder(Order.desc("created"));
			VOList = cr.list();

			if (null != VOList && !VOList.isEmpty()) {
				for (User vo : VOList) {
					count = count++;
					adminuserBO = new AdminUserBO();
					adminuserBO.setId(vo.getId());
					adminuserBO.setActive(vo.isActive());
					adminuserBO.setName(vo.getName());
					adminuserBO.setMobileNo(vo.getMobileNo());
					adminuserBO.setPassword(EncryptAndDecrypt.decrypt(vo.getPassword()));
					adminuserBO.setEmailAddress(vo.getEmailAddress());
					adminuserBO.setUserType(vo.getUserType());
					adminuserBO.setConfirmPassword(EncryptAndDecrypt.decrypt(vo.getConfirmpassword()));
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

		return adminuserBO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.scube.crm.dao.AdminDAO#getuserId(com.scube.crm.vo.User)
	 */
	@Override
	public User getuserId(AdminUserBO loginBO) {
		Session session = getSession();

		User user = (User) session.get(User.class, loginBO.getId());
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.scube.crm.dao.AdminDAO#findByEmail(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public User findByEmail(String string, String emailAddress) {
		AdminDAOImpl.LOGGER.entry();
		User loginVO = null;
		final String loginQuery = "FROM User E WHERE E.emailAddress = :emailAddress";
		try {
			Session session = getSession();
			final Query query = session.createQuery(loginQuery);
			query.setParameter("emailAddress", emailAddress);
			if (null != query.list() && query.list().size() > 0) {
				loginVO = (User) query.list().get(0);
			}
		} catch (final HibernateException he) {
			he.printStackTrace();
			if (AdminDAOImpl.LOGGER.isDebugEnabled()) {
				AdminDAOImpl.LOGGER.debug(he, he.getMessage());
			}
		} finally {

			AdminDAOImpl.LOGGER.exit();
		}
		return loginVO;
	}


	@Override
	public List<String> getImageName() {
		List<String> imageNameList = new ArrayList<String>();
		try {
			String imageName = null;
			String sqlQuery = "SELECT imageName,companiesId FROM company WHERE isDeleted = false and adminChecked=true"
					+ " ORDER BY rank ASC LIMIT 60";
			Query query = getSession().createSQLQuery(sqlQuery);
			List<Object[]> rows = query.list();
			if (null != rows && !rows.isEmpty()) {
				for (Object[] obj : rows) {
					if (null != obj) {
						imageName = obj[0].toString() + "," + obj[1];
					} else {
						imageName = "companylogo.jpg";
					}

					if (null != imageName && !imageName.isEmpty()) {
						imageNameList.add(imageName);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return imageNameList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.scube.crm.dao.AdminDAO#saveEmailList(java.util.List)
	 */
	@Override
	public EmailAccess saveEmailList(List<EmailAccess> accessVOList) {
		AdminDAOImpl.LOGGER.entry();
		EmailAccess accessVO = new EmailAccess();
		try {
			long sendId = 0;
			Session session = getSession();
			for (EmailAccess emailaccessVO : accessVOList) {
				sendId = (Long) session.save(emailaccessVO);
				session.flush();
				session.clear();
			}
			accessVO.setSendId(sendId);
		} catch (Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Email Tracking History failed:" + e.getMessage());
			}
			LOGGER.info("Email Tracking History failed:" + e.getMessage());

		} finally {
			AdminDAOImpl.LOGGER.exit();
		}
		return accessVO;
	}


	@Override
	public List<User> searchEmployeeList(AdminUserBO adminBO) {
		List<User> VOList = new ArrayList<User>();
		try {
			int count = 1;
			Criteria cr = getSession().createCriteria(User.class);
			cr.add(Restrictions.eq("isDelete", false));
			cr.setFirstResult(adminBO.getRecordIndex());
			cr.setMaxResults(adminBO.getMaxRecord());
			if (null != adminBO.getName() && !adminBO.getName().isEmpty()) {
				cr.add(Restrictions.ilike("name", adminBO.getName(), MatchMode.ANYWHERE));
			}
			if (null != adminBO.getEmailAddress() && !adminBO.getEmailAddress().isEmpty()) {
				cr.add(Restrictions.ilike("emailAddress", adminBO.getEmailAddress(), MatchMode.ANYWHERE));
			}
			if (null != adminBO.getUserType() && !adminBO.getUserType().isEmpty()) {
				cr.add(Restrictions.ilike("userType", adminBO.getUserType(), MatchMode.ANYWHERE));
			}
			// cr.add(Restrictions.eq("isActive", true));
			cr.addOrder(Order.desc("created"));
			VOList = cr.list();


		} catch (final HibernateException he) {
			he.printStackTrace();
			if (AdminDAOImpl.LOGGER.isDebugEnabled()) {
				AdminDAOImpl.LOGGER.debug(ErrorCodes.ENTITY_DELETE_FAIL + he);
			}
		}
		return VOList;
	}


	@Override
	public boolean findByMobilenoEmployee(String mobileNo) {
		User user = null;
		try {

			Session session = getSession();
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("mobileNo", mobileNo));
			/*
			 * long recordCount= (long) criteria.uniqueResult(); if(0!=recordCount ) {
			 * return true; }
			 */
			if (null != criteria.list() && criteria.list().size() > 0) {
				user = (User) criteria.list().get(0);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("MobileNo Tracking History failed:" + e.getMessage());
			}
			LOGGER.info("MobileNo Tracking History failed:" + e.getMessage());

		} finally {
			AdminDAOImpl.LOGGER.exit();
		}
		return false;
	}


	@Override
	public long campaignCount(AdminLoginBO adminLoginBO) {
		long count = 0;
		try {
			Session session = getSession();
			Criteria criteria = session.createCriteria(Campaign.class);
			criteria.add(Restrictions.eq("isDelete", false));
			if(adminLoginBO.getUserType().equals("ROLE_CAMPAIGN")&&(0!=adminLoginBO.getId())) {
				criteria.add(Restrictions.eq("user.id", adminLoginBO.getId()));
			}

			if (1< adminLoginBO.getId()) {
				criteria.add(Restrictions.eq("user.id",adminLoginBO.getId())); 
			}
			count = (long) criteria.setProjection(Projections.rowCount()).uniqueResult();
		} catch (final HibernateException he) {
			he.printStackTrace();
			if (AdminDAOImpl.LOGGER.isDebugEnabled()) {
				AdminDAOImpl.LOGGER.debug(ErrorCodes.ENTITY_DELETE_FAIL + he);
			}
		}
		return count;
	}

	@Override
	public long leadsCount(AdminLoginBO adminLoginBO) {
		long leadsCount = 0;
		try {
			Session session = getSession();
			Criteria cri = session.createCriteria(Leads.class);
			cri.add(Restrictions.eq("isDelete", false));
			cri.add(Restrictions.eq("convertedCustomer", false));
			cri.setProjection(Projections.rowCount());
			if (1< adminLoginBO.getId()) {
				cri.add(Restrictions.eq("leadeOwner.id",adminLoginBO.getId()));
			}
			leadsCount = (long) cri.uniqueResult();

		} catch (final HibernateException he) {
			he.printStackTrace();
			if (AdminDAOImpl.LOGGER.isDebugEnabled()) {
				AdminDAOImpl.LOGGER.debug(ErrorCodes.ENTITY_DELETE_FAIL + he);
			}
		}
		return leadsCount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.scube.crm.dao.AdminDAO#customerCount(com.scube.crm.bo.ClientBO)
	 */
	@Override
	public long customerCount(AdminLoginBO adminLoginBO) {

		long customersCount = 0;
		try {
			Session session = getSession();
			Criteria cri2 = session.createCriteria(Customer.class);
			cri2.add(Restrictions.eq("isDelete", false));
			cri2.add(Restrictions.eq("isActive", true));
			cri2.setProjection(Projections.rowCount());
			if (1< adminLoginBO.getId()) {
				cri2.add(Restrictions.eq("loginVO.id",adminLoginBO.getId()));
			}
			customersCount = (long) cri2.uniqueResult();
		} catch (final HibernateException he) {
			he.printStackTrace();
			if (AdminDAOImpl.LOGGER.isDebugEnabled()) {
				AdminDAOImpl.LOGGER.debug(ErrorCodes.ENTITY_DELETE_FAIL + he);
			}
		}
		return customersCount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.scube.crm.dao.AdminDAO#employeesCount(com.scube.crm.bo.AdminUserBO)
	 */
	@Override
	public long employeesCount(AdminLoginBO adminLoginBO) {

		long employeeCount = 0;
		try {
			Session session = getSession();
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("isDelete", false));
			criteria.add(Restrictions.eq("isActive", true));
			criteria.setProjection(Projections.rowCount());
			employeeCount = (long) criteria.uniqueResult();
		} catch (final HibernateException he) {
			he.printStackTrace();
			if (AdminDAOImpl.LOGGER.isDebugEnabled()) {
				AdminDAOImpl.LOGGER.debug(ErrorCodes.ENTITY_DELETE_FAIL + he);
			}
		}
		return employeeCount;
	}

	@Override
	public boolean findEmployerEmail(String emailAddress) {

		return false;
	}

	@Override
	public boolean mobileNoVerification(String mobileNo) {

		return false;
	}
	public long getCurrentActionCustomers(AdminLoginBO adminLoginBO) {

		long employeeCount = 0;
		try {
			Session session = getSession();
			Criteria criteria = session.createCriteria(LeadsFollowup.class);
			DateHelper.beginningOfDay(new Date());
			Date date=	getDateWithoutTime(new Date());
			SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			String startDate=df.format(date);
			criteria.add(Restrictions.eq("nextAppointmentDate",startDate));
			/*
			 * criteria.add(Restrictions.eq("isDelete", false));
			 * criteria.add(Restrictions.eq("isActive", true));
			 */
			criteria.setProjection(Projections.rowCount());
			employeeCount = (long) criteria.uniqueResult();
		} catch (final HibernateException he) {
			he.printStackTrace();
			if (AdminDAOImpl.LOGGER.isDebugEnabled()) {
				AdminDAOImpl.LOGGER.debug(ErrorCodes.ENTITY_DELETE_FAIL + he);
			}
		}
		return employeeCount;

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
	public long productCount(AdminLoginBO adminLoginBO) {
		long productCount = 0;
		try {
			Session session = getSession();
			Criteria criteria = session.createCriteria(ProductServiceVO.class);
			criteria.add(Restrictions.eq("isDelete", false));
			criteria.setProjection(Projections.rowCount());
			productCount = (long) criteria.uniqueResult();
		} catch (final HibernateException he) {
			he.printStackTrace();
			if (AdminDAOImpl.LOGGER.isDebugEnabled()) {
				AdminDAOImpl.LOGGER.debug(ErrorCodes.ENTITY_DELETE_FAIL + he);
			}
		}
		return productCount;
	}
	
	
	/*public RoleVO insertrole(RoleVO rolevo) {
		Session session = getSession();
		session.save(rolevo);
		return rolevo;
	}
	
	
	public List<RoleVO> retrieverole() {
		List<RoleVO> rolevo = new ArrayList<>();
		 try { 
				Session session = getSession();
				Criteria criteria = session.createCriteria(RoleVO.class);
				rolevo = criteria.list();
				if (null != rolevo && !rolevo.isEmpty() && rolevo.size()>0) {
					return rolevo;
				}
			} catch (Exception e) {
				System.out.println(e); 
				return null;
			}
			return rolevo;
		}*/
}
