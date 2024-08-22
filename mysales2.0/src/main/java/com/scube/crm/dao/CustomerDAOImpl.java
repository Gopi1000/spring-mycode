package com.scube.crm.dao;

import java.sql.SQLException;
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
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.scube.crm.bo.AdminLoginBO;
import com.scube.crm.bo.AdminUserBO;
import com.scube.crm.bo.ClientBO;
import com.scube.crm.bo.ContactBO;
import com.scube.crm.bo.ProductServiceBO;
import com.scube.crm.exception.MyClientsLogger;
import com.scube.crm.exception.MyClientsException;
import com.scube.crm.utils.ErrorCodes;
import com.scube.crm.vo.AccessLogVO;
import com.scube.crm.vo.Campaign;
import com.scube.crm.vo.Contact;
import com.scube.crm.vo.User;

import com.scube.crm.vo.Customer;
import com.scube.crm.vo.EmailAccess;
import com.scube.crm.vo.FollowUp;
import com.scube.crm.vo.GstVO;
import com.scube.crm.vo.LeadsFollowup;
import com.scube.crm.vo.LoginStatusVO;
import com.scube.crm.vo.ProductServiceVO;
import com.scube.crm.vo.SalesOrderVO;

/**
 * Owner : Scube Technologies Created Date: Nov-22-2014 Created by : Vinoth P
 * Description : JobSeekerDAOImpl is a Class which is responsible for storing
 * the data into the database Reviewed by :
 * 
 * 
 */

@Repository("customerDAOImpl")
public class CustomerDAOImpl implements CustomerDAO {

	public CustomerDAOImpl() throws MyClientsException {
		super();
		// TODO Auto-generated constructor stub
	}

	SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

	@Resource(name = "sessionFactory")
	SessionFactory sessionFactory;

	protected Session getSession() {

		return sessionFactory.getCurrentSession();

	}

	private static final MyClientsLogger LOGGER = MyClientsLogger.getLogger(CustomerDAOImpl.class);

	@Override
	public User authendicate(String string, String emailAddress) throws MyClientsException {
		CustomerDAOImpl.LOGGER.entry();
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
			if (CustomerDAOImpl.LOGGER.isDebugEnabled()) {
				CustomerDAOImpl.LOGGER.debug(ErrorCodes.ENTITY_CRE_FAIL + he);
			}
			throw new MyClientsException(ErrorCodes.ENTITY_CRE_FAIL, ErrorCodes.ENTITY_CRE_FAIL_MSG);
		} finally {

			CustomerDAOImpl.LOGGER.exit();
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

		CustomerDAOImpl.LOGGER.entry();
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
			CustomerDAOImpl.LOGGER.exit();
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
		CustomerDAOImpl.LOGGER.entry();

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
			CustomerDAOImpl.LOGGER.exit();
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

	@Override
	public User updateuser(User loginVO) throws MyClientsException {

		CustomerDAOImpl.LOGGER.entry();
		try {
			Session session = getSession();
			session.saveOrUpdate(loginVO);
			getSession().flush();
		} catch (final HibernateException he) {
			he.printStackTrace();
			if (CustomerDAOImpl.LOGGER.isDebugEnabled()) {
				CustomerDAOImpl.LOGGER.debug(ErrorCodes.ENTITY_UPDATE_FAIL + he);
			}
			throw new MyClientsException(ErrorCodes.ENTITY_UPDATE_FAIL, ErrorCodes.ENTITY_UPDATE_FAIL_MSG);
		} finally {
			CustomerDAOImpl.LOGGER.exit();
		}
		return loginVO;

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
			if (CustomerDAOImpl.LOGGER.isDebugEnabled()) {
				CustomerDAOImpl.LOGGER.debug(he.getMessage() + he);
			}
		} finally {

			CustomerDAOImpl.LOGGER.exit();
		}
		return null;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.scube.crm.dao.AdminDAO#DeleteProfile(com.scube.crm.vo.AdminUserVO)
	 */
	@Override
	public int deleteCustomer(Customer Customer) {
		CustomerDAOImpl.LOGGER.entry();
		int result = 0;

		final String deleteQuery = "UPDATE Customer S set" + " S.isDelete = :isDelete" + " WHERE S.id = :id";
		try {

			final Query query = getSession().createQuery(deleteQuery);
			query.setParameter("isDelete", Customer.getIsDelete());
			query.setParameter("id", Customer.getId());
			result = query.executeUpdate();
		} catch (final HibernateException he) {
			he.printStackTrace();
			if (CustomerDAOImpl.LOGGER.isDebugEnabled()) {
				CustomerDAOImpl.LOGGER.debug(ErrorCodes.ENTITY_DELETE_FAIL + he);
			}

		}

		return result;
	}

	@Override
	public User getuserId(AdminUserBO loginBO) {
		Session session = getSession();

		User user = (User) session.get(User.class, loginBO.getId());
		return user;
	}

	@Override
	public boolean findEmployerEmail(String emailAddress) {
		try {
			Customer customerVO = new Customer();
			Session session = getSession();
			Criteria criteria = session.createCriteria(Customer.class);
			criteria.add(Restrictions.eq("emailAddress", emailAddress));
			if (null != criteria.list() && criteria.list().size() > 0) {
				customerVO = (Customer) criteria.list().get(0);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("MobileNo Tracking History failed:" + e.getMessage());
			}
			LOGGER.info("MobileNo Tracking History failed:" + e.getMessage());

		} finally {
			CustomerDAOImpl.LOGGER.exit();
		}
		return false;
		/* List<Customer> employerRegisteration = criteria.list(); */
		/*
		 * long emailvalidation = 0; boolean validation; if (null !=
		 * employerRegisteration) { emailvalidation = employerRegisteration.size(); } if
		 * (emailvalidation != 0) { validation = true; return validation; } else {
		 * validation = false; return validation; }
		 */

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
		CustomerDAOImpl.LOGGER.entry();
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
			CustomerDAOImpl.LOGGER.exit();
		}
		return accessVO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.scube.crm.dao.AdminDAO#mobileNoVerification(java.lang.String)
	 */
	@Override
	public boolean mobileNoVerification(String mobileNo) {
		Customer employerVO = null;
		try {

			Session session = getSession();
			Criteria criteria = session.createCriteria(Customer.class);
			criteria.add(Restrictions.eq("mobileNumber", mobileNo));
			/*
			 * long recordCount= (long) criteria.uniqueResult(); if(0!=recordCount ) {
			 * return true; }
			 */
			if (null != criteria.list() && criteria.list().size() > 0) {
				employerVO = (Customer) criteria.list().get(0);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("MobileNo Tracking History failed:" + e.getMessage());
			}
			LOGGER.info("MobileNo Tracking History failed:" + e.getMessage());

		} finally {
			CustomerDAOImpl.LOGGER.exit();
		}
		return false;
	}

	@Override
	public Customer createCustomer(Customer Customer) {
		Session session = getSession();
		session.saveOrUpdate(Customer);
		return Customer;
	}

	@Override
	public ClientBO retriveCustomerById(ClientBO registerBO) throws MyClientsException, SQLException {
		CustomerDAOImpl.LOGGER.entry();
		ClientBO clientBO = new ClientBO();
		try {
			long sno = 1;
			List<Customer> employerList = new ArrayList<Customer>();
			Session session = getSession();
			final Criteria cr = session.createCriteria(Customer.class);
			if (0 != registerBO.getId()) {
				/*
				 * Customer employerVO=(Customer)
				 * session.get(Customer.class,clientBO.getId(),new
				 * LockOptions(LockMode.PESSIMISTIC_WRITE));
				 */
				cr.add((Restrictions.eq("id", registerBO.getId()))).uniqueResult();
				Customer employerVO = (Customer) cr.uniqueResult();
				employerList.add(employerVO);
			} else {
				if (null != registerBO.getPagination() && !registerBO.getPagination().isEmpty()) {
					cr.setFirstResult(registerBO.getRecordIndex());
					cr.setMaxResults(registerBO.getMaxRecord());
				}
				employerList = cr.list();
			}

			if (null != employerList && 0 != employerList.size() && null != employerList) {

				for (Customer profileVO : employerList) {

					registerBO = new ClientBO();
					clientBO.setsNo(sno++);
					clientBO.setCreatedDate(format.format(profileVO.getCreated()));
					clientBO.setCreated(profileVO.getCreated());
					clientBO.setId(profileVO.getId());
					if (null != profileVO.getFirstName()) {
						clientBO.setFirstName(profileVO.getFirstName());
					}
					if (null != profileVO.getEmailAddress()) {
						clientBO.setEmailAddress(profileVO.getEmailAddress());
					}
					if (null != profileVO.getLastName()) {
						clientBO.setLastName(profileVO.getLastName());
					}
					/*
					 * if(null !=profileVO.getPassword()){
					 * registerBO.setPassword(profileVO.getPassword()); }
					 */
					/*
					 * if(null !=profileVO.getConfirmPassword()){
					 * registerBO.setConfirmPassword(profileVO.getConfirmPassword()); }
					 */
					if (null != profileVO.getMobileNumber()) {
						clientBO.setMobileNo(profileVO.getMobileNumber());
					}
					if (null != profileVO.getContactNumber()) {
						clientBO.setContactNo(profileVO.getContactNumber());
					}
					/*
					 * if(null !=profileVO.getConfirmEmailAddress()){
					 * registerBO.setConfirmEmailAddress(profileVO.getConfirmEmailAddress()); }
					 */
					if (null != profileVO.getWebSite()) {
						clientBO.setWebsite(profileVO.getWebSite());
					}
					if (null != profileVO.getCompanyName()) {
						clientBO.setCompanyName(profileVO.getCompanyName());
					}
					if (null != profileVO.getIndustryType()) {
						clientBO.setIndustryType(profileVO.getIndustryType());
					}
					if (0 != profileVO.getId()) {
						clientBO.setsNo(profileVO.getId());
					}
					if (null != profileVO.getAddress()) {
						clientBO.setAddress(profileVO.getAddress());
					}
					if (null != profileVO.getStatus()) {
						clientBO.setStatus(profileVO.getStatus());
					}

					if (null != profileVO.getLoginVO()) {
						AdminLoginBO adminLoginBO = new AdminLoginBO();
						adminLoginBO.setId(profileVO.getLoginVO().getId());
						adminLoginBO.setFirstName(profileVO.getLoginVO().getName());
						clientBO.setUserId(profileVO.getLoginVO().getId());
						clientBO.setUserName(profileVO.getLoginVO().getName());
						clientBO.setLoginBO(adminLoginBO);
					}
				}
			}
		} catch (Exception he) {
			he.printStackTrace();
			CustomerDAOImpl.LOGGER.debug(he, he.getMessage());
		} finally {
			CustomerDAOImpl.LOGGER.exit();
		}
		return clientBO;
	}

	@Override
	public ClientBO retriveCustomer(ClientBO clientBO) {
		CustomerDAOImpl.LOGGER.entry();
		ClientBO profileBO = new ClientBO();
		ClientBO clientRes = new ClientBO();

		try {
			long sno = 1;
			List<Customer> employerProfilesList = new ArrayList<Customer>();
			Session session = getSession();
			final Criteria cr = session.createCriteria(Customer.class);
			if (null != clientBO.getFirstName() && !clientBO.getFirstName().isEmpty()) {
				cr.add(Restrictions.ilike("firstName",clientBO.getFirstName(),MatchMode.ANYWHERE));
			}
			if(null!=clientBO.getMobileNo() && !clientBO.getMobileNo().isEmpty()){
				cr.add(Restrictions.eq("mobileNumber",clientBO.getMobileNo()));
			}
			if(null !=clientBO.getEmailAddress() && !clientBO.getEmailAddress().isEmpty()){
				cr.add(Restrictions.ilike("emailAddress",clientBO.getEmailAddress(),MatchMode.ANYWHERE));
			}
			if(null !=clientBO.getCompanyName() && !clientBO.getCompanyName().isEmpty()){
				cr.add(Restrictions.ilike("companyName",clientBO.getCompanyName(),MatchMode.ANYWHERE));
			}
			if(null !=clientBO.getIndustryType() && !clientBO.getIndustryType().isEmpty()){
				cr.add(Restrictions.ilike("industryType",clientBO.getIndustryType(),MatchMode.ANYWHERE));
			}
			
			//user based on reterive customer
			if(null!= clientBO.getAssignedTo()){
				  cr.add(Restrictions.eq("loginVO.id",clientBO.getAssignedTo())); 
				  }

			//created By 

			if(0<clientBO.getCreatedBy() && (null!=clientBO.getStarDate() && null!=clientBO.getEndDate())){
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String startDate=df.format(clientBO.getStarDate());
				String endDate=df.format(clientBO.getEndDate());
				Date fromDate = df.parse(startDate);
				Date toDate = df.parse(endDate);
				if(fromDate.equals(toDate)){
					cr.add(Restrictions.eq("created",fromDate));

				}else{
					cr.add(Restrictions.between("created",fromDate,toDate));	
				}
				cr.add(Restrictions.eq("createdBy",clientBO.getCreatedBy()));
			}
			//start date created 
			else if(0<clientBO.getCreatedBy() && null!=clientBO.getStarDate()  
					&& clientBO.getProcess().equalsIgnoreCase("create") ){
				cr.add(Restrictions.eq("createdBy",clientBO.getCreatedBy()));
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String startDate=df.format(clientBO.getStarDate());
				Date fromDate = df.parse(startDate);
				if(null!=fromDate ) {
					cr.add(Restrictions.eq("created",clientBO.getStarDate()));
				}
			}
			//end date created 
			else if(0<clientBO.getCreatedBy() && null!=clientBO.getEndDate()  
					&& clientBO.getProcess().equalsIgnoreCase("create") ){
				cr.add(Restrictions.eq("createdBy",clientBO.getCreatedBy()));
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String endDate=df.format(clientBO.getEndDate());
				Date toDate = df.parse(endDate);
				if(null!=toDate ) {
					cr.add(Restrictions.eq("created",clientBO.getEndDate()));
				}
			}
			if(0!=clientBO.getModifiedBy() && (null!=clientBO.getStarDate() && null!=clientBO.getEndDate())){
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String startDate=df.format(clientBO.getStarDate());
				String endDate=df.format(clientBO.getEndDate());
				Date fromDate = df.parse(startDate);
				Date toDate = df.parse(endDate);
				if(fromDate.equals(toDate)){

					cr.add(Restrictions.eq("modified",fromDate));	
				}else{
					cr.add(Restrictions.between("modified",fromDate,toDate));
				}
				cr.add(Restrictions.eq("modifiedBy",clientBO.getModifiedBy()));

			}
			//end date modified edit
			else if(0<clientBO.getModifiedBy() && null!=clientBO.getEndDate() 
					&& clientBO.getProcess().equalsIgnoreCase("edit")){
				cr.add(Restrictions.eq("modifiedBy",clientBO.getModifiedBy()));
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String endDate=df.format(clientBO.getEndDate());
				Date  toDate = df.parse(endDate);
				if(null!=toDate){
					cr.add(Restrictions.eq("modified", clientBO.getEndDate()));	
				}
			}

			if(0!=clientBO.getAdminId() && (0==clientBO.getModifiedBy()) && (0==clientBO.getCreatedBy())){
				cr.add(Restrictions.or(Restrictions.eq("createdBy",clientBO.getAdminId())
						,Restrictions.eq("modifiedBy",clientBO.getAdminId()))); 

			} 

			if(null!=clientBO.getStarDate() && null!=clientBO.getEndDate() && clientBO.getProcess().equalsIgnoreCase("all")){
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String startDate=df.format(clientBO.getStarDate());
				String endDate=df.format(clientBO.getEndDate());
				Date fromDate = df.parse(startDate);
				Date toDate = df.parse(endDate);

				if(fromDate.equals(toDate)){
					//cr.add(Restrictions.ge("created",clientBO.getStarDate()));
					cr.add(Restrictions.eq("created", clientBO.getEndDate()));	
				}
				else if(null!=clientBO.getStarDate() && null!=clientBO.getEndDate() && clientBO.getProcess().equalsIgnoreCase("create")){
					cr.add(Restrictions.between("created",fromDate,toDate));	
				}
				else if(null!=clientBO.getStarDate() && null!=clientBO.getEndDate() && clientBO.getProcess().equalsIgnoreCase("edit")) {
					cr.add(Restrictions.eq("created", clientBO.getEndDate()));	
				}
			}

			//start date all 
			if(null!=clientBO.getStarDate()  && clientBO.getProcess().equalsIgnoreCase("all")
					&&null==clientBO.getEndDate()){
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String startDate=df.format(clientBO.getStarDate());
				Date fromDate = df.parse(startDate);
				if(null!=fromDate ) {
					cr.add(Restrictions.eq("created",clientBO.getStarDate()));
				}
			}
			//end date  all
			else if(null!=clientBO.getEndDate() && clientBO.getProcess().equalsIgnoreCase("all")){
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String endDate=df.format(clientBO.getEndDate());
				Date  toDate = df.parse(endDate);
				if(null!=toDate){
					cr.add(Restrictions.eq("created", clientBO.getEndDate()));	
				}
			}
			//start date modified 
			else if(0<clientBO.getModifiedBy() && null!=clientBO.getStarDate() 
					&& clientBO.getProcess().equalsIgnoreCase("edit")){
				cr.add(Restrictions.eq("modifiedBy",clientBO.getModifiedBy()));
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String startDate=df.format(clientBO.getStarDate());
				Date  toDate = df.parse(startDate);
				if(null!=toDate){
					cr.add(Restrictions.eq("modified", clientBO.getStarDate()));	
				}
			}
					 
			cr.add(Restrictions.eq("isDelete",false));
			cr.add(Restrictions.eq("isActive", true));
			cr.addOrder(Order.desc("id"));

			if (null!=clientBO.getLoginBO() && 1< clientBO.getLoginBO().getId()) {
				 cr.add(Restrictions.eq("loginVO.id", clientBO.getLoginBO().getId())); 
				 }
			employerProfilesList = cr.list();

			if(0 !=clientBO.getRecordIndex()){
				sno= clientBO.getRecordIndex() + 1;
			}
			if (null != employerProfilesList &&  employerProfilesList.size()>0) {

				List<ClientBO> customerList = new ArrayList<ClientBO>();
				for (Customer profileVO : employerProfilesList) {
					profileBO = new ClientBO();
					profileBO.setsNo(sno++);
					profileBO.setCreatedDate(format.format(profileVO
							.getCreated()));
					profileBO.setModifiedDate(format.format(profileVO.getModified()));
					profileBO.setCreated(profileVO.getCreated());
					profileBO.setId(profileVO.getId());
					if(null !=profileVO.getFirstName()){
						profileBO.setFirstName(profileVO.getFirstName());
					}
					if(null !=profileVO.getEmailAddress()){
						profileBO.setEmailAddress(profileVO.getEmailAddress());
					}
					if(null !=profileVO.getMobileNumber()){
						profileBO.setMobileNo(profileVO.getMobileNumber());
					}
					if(null !=profileVO.getCompanyName()){
						profileBO.setCompanyName(profileVO.getCompanyName());
					}
					if(null !=profileVO.getAddress()){
						profileBO.setAddress(profileVO.getAddress());
					}
					if(null !=profileVO.getContactNumber()){
						profileBO.setContactNo(profileVO.getContactNumber());
					}
					if(null !=profileVO.getWebSite()){
						profileBO.setWebsite(profileVO.getWebSite());
					}
					if(null !=profileVO.getIndustryType()){
						profileBO.setIndustryType(profileVO.getIndustryType());
					}
					if(null !=profileVO.getStatus()){
						profileBO.setStatus(profileVO.getStatus());
					}

					if(null!=profileVO.getLoginVO()) {
						AdminUserBO adminUserBO=new AdminUserBO();
						adminUserBO.setName(profileVO.getLoginVO().getName());
						adminUserBO.setUserId(profileVO.getLoginVO().getId());
						profileBO.setAdminUserBO(adminUserBO);

					}
					profileBO.setCreatedBy(profileVO.getCreatedBy());
					profileBO.setModifiedBy(profileVO.getModifiedBy());

					customerList.add(profileBO);					
				}
				clientRes.setCustomersList(customerList);
			}

		} catch (Exception he) {
			he.printStackTrace();
			CustomerDAOImpl.LOGGER.debug(he, he.getMessage());
		} finally {

			CustomerDAOImpl.LOGGER.exit();
		}
		return clientRes;
	}
	@Override
	public Customer getuserId(ClientBO registerBO) {

		return null;
	}

	@Override
	public Customer updateEmployer(Customer employerVO) {
		CustomerDAOImpl.LOGGER.entry();
		try {
			Session session = getSession();
			session.saveOrUpdate(employerVO);
			getSession().flush();
		} catch (final HibernateException he) {
			he.printStackTrace();
			if (CustomerDAOImpl.LOGGER.isDebugEnabled()) {
				CustomerDAOImpl.LOGGER.debug(ErrorCodes.ENTITY_UPDATE_FAIL + he);
			}

		}
		return employerVO;
	}

	@Override
	public Customer deleteProfile(Customer vo) throws MyClientsException {
		Session session = getSession();
		Customer employerVO = (Customer) session.get(Customer.class, vo.getId());

		try {
			employerVO.setIsDelete(true);
			employerVO.setIsActive(false);
			session.saveOrUpdate(employerVO);

		} catch (final HibernateException he) {
			employerVO = null;
			he.printStackTrace();
			CustomerDAOImpl.LOGGER.debug(he, he.getMessage());
		} finally {
			CustomerDAOImpl.LOGGER.exit();
		}
		return employerVO;

	}

	@Override
	public FollowUp saveTracking(FollowUp vO) {
		try {
			Session session = getSession();
			long id = (long) session.save(vO);
			vO.setUpdateid(id);
		} catch (final HibernateException he) {
			he.printStackTrace();
		}
		return vO;
	}

	@Override
	public List<FollowUp> retrieveTracking(long customerId) {
		//public List<Contact> retrieveTracking(long customerId) {

		List<FollowUp> trackingList = null;
		try {

			Session session = getSession();
			Criteria criteria = session.createCriteria(FollowUp.class);
			criteria.add(Restrictions.eq("customer.id", customerId));
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
	public List<FollowUp> searchRetrieveTracking(FollowUp leadsFollowup) {
		List<FollowUp> leadsFollowupList=new ArrayList<FollowUp>();
		try {
			Session session = getSession();
			Criteria criteria = session.createCriteria(FollowUp.class);
			//criteria.add(Restrictions.eq("isDelete", false));
			if (null != leadsFollowup && 1< leadsFollowup.getUser().getId()) {
				criteria.add(Restrictions.eq("user.id", leadsFollowup.getUser().getId()));
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
	/*
	 * public List<ProductServiceVO> getProductList(){ List<ProductServiceVO>
	 * productServiceVO=new ArrayList<>(); try {
	 * 
	 * Session session = getSession(); Criteria criteria =
	 * session.createCriteria(ProductServiceVO.class);
	 * productServiceVO=criteria.list(); if (null != productServiceVO
	 * &&!productServiceVO.isEmpty()&&productServiceVO.size()>0) { productServiceVO
	 * = criteria.list();
	 * 
	 * } } catch (Exception e) { e.printStackTrace(); if (LOGGER.isDebugEnabled()) {
	 * LOGGER.debug("Product List failed:"+ e.getMessage()); }
	 * LOGGER.info("Product List failed:"+ e.getMessage());
	 * 
	 * } return productServiceVO; }
	 */
	
	public ProductServiceVO getProductPrice(long productId) {
			ProductServiceVO productServiceVO=new ProductServiceVO();
	try {

		Session session = getSession();
		Criteria criteria = session.createCriteria(ProductServiceVO.class);
		criteria.add(Restrictions.eq("serviceId", productId));
		productServiceVO=(ProductServiceVO) criteria.uniqueResult();
		if (null != productServiceVO) {
			return productServiceVO;
		}
	} catch (Exception e) {
		e.printStackTrace();
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Product List failed:"+ e.getMessage());
		}
		LOGGER.info("Product List failed:"+ e.getMessage());

	} 
	return productServiceVO;
}

	public List<ProductServiceVO> getProductList(){
		List<ProductServiceVO> productServiceVO=new ArrayList<>();
		try {

			Session session = getSession();
			Criteria criteria = session.createCriteria(ProductServiceVO.class);
			productServiceVO=criteria.list();
			if (null != productServiceVO &&!productServiceVO.isEmpty()&&productServiceVO.size()>0) {
				productServiceVO = criteria.list();

			}
		} catch (Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Product List failed:"+ e.getMessage());
			}
			LOGGER.info("Product List failed:"+ e.getMessage());

		} 
		return productServiceVO;
	}
	
	/*
	 * public ProductServiceVO getProductPrice(long productId) { ProductServiceVO
	 * productServiceVO=new ProductServiceVO(); try {
	 * 
	 * Session session = getSession(); Criteria criteria =
	 * session.createCriteria(ProductServiceVO.class);
	 * criteria.add(Restrictions.eq("serviceId", productId));
	 * productServiceVO=(ProductServiceVO) criteria.uniqueResult(); if (null !=
	 * productServiceVO) { return productServiceVO; } } catch (Exception e) {
	 * e.printStackTrace(); if (LOGGER.isDebugEnabled()) {
	 * LOGGER.debug("Product List failed:"+ e.getMessage()); }
	 * LOGGER.info("Product List failed:"+ e.getMessage());
	 * 
	 * } return productServiceVO; }
	 */
	
	public GstVO getGstValues() {
		List<GstVO> gstVO=new ArrayList<>();
		GstVO gstObj=new GstVO();
		try {
		Session session = getSession();
		Criteria criteria = session.createCriteria(GstVO.class);
		criteria.addOrder(Order.desc("gstId"));
		gstVO=(List<GstVO>) criteria.list();
		if (null != gstVO && gstVO.size() > 0&&!gstVO.isEmpty()) {
			gstObj=gstVO.get(0);
		}
		return gstObj;}
		catch (Exception e) {
		e.printStackTrace();
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Gst List failed:"+ e.getMessage());
		}
		LOGGER.info("Gst List failed:"+ e.getMessage());

	} 
		return gstObj;
	}
	public long getProductServiceId(String serviceName) {
		Session session = getSession();
		Criteria criteria = session.createCriteria(ProductServiceVO.class);
		criteria.add(Restrictions.ilike("serviceName",serviceName,MatchMode.ANYWHERE));
		criteria.uniqueResult();
		return 0;
		
	}
	public long createSalesOrder(SalesOrderVO salesOrderVO) {
		
	//Session session = sessionFactory().get;
		Session sessions = sessionFactory.openSession();
		
	
		
	sessions.save(salesOrderVO);
	sessions.clear();
	sessions.flush();
	sessions.close();
	return 0;
	}
	
	public String getSalesOrderNo() {
		String sNo="SONo1";
		Session session = getSession();
		Criteria criteria = session.createCriteria(SalesOrderVO.class);
		criteria.addOrder(Order.desc("salesOrderId"));
		
		List<SalesOrderVO> abc=criteria.list();
		if (null!=abc && abc.size()>0){
		SalesOrderVO salesOrderVO=abc.get(0);
		
		//SalesOrderVO salesOrderVO=(SalesOrderVO) criteria.list().get(0);
		if (null != salesOrderVO&&null!=salesOrderVO.getSalesOrderNo()) {
			long add=1;
			String ss=salesOrderVO.getSalesOrderNo();
		    String s[]=	ss.split("o");
	        String val=s[1];
		    long merge=Long.parseLong(val)+add;
			sNo="SONo"+String.valueOf(merge);
				return sNo;
		}
		}
		return sNo;
		
	}
	
	public List<ClientBO> retriveClients() {
		List<ClientBO> clientBO=null;
		Session session = getSession();
		Criteria criteria = session.createCriteria(Customer.class);
		 clientBO=criteria.list();
		
			if (null != clientBO &&!clientBO.isEmpty()&&clientBO.size()>0) {
			return  clientBO;
		}
			return clientBO;
		
	}
	
	
	
	/*public List<ClientBO> retrieveCustomer()throws MyClientsException {
		ClientBO adminuserBO = new ClientBO();
		List<ClientBO> BOList = new ArrayList<ClientBO>();
		List<Customer> VOList = new ArrayList<Customer>();
		try {
			int count = 1;
			Criteria cr = getSession().createCriteria(Customer.class);
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
				for (Customer vo : VOList) {
					data = count++;
					adminuserBO = new ClientBO();
					adminuserBO.setId(vo.getId());
					adminuserBO.setActive(vo.getIsActive());
					adminuserBO.setsNo(data);
					adminuserBO.setFirstName(vo.getFirstName());
					adminuserBO.setLastName(vo.getLastName());
					adminuserBO.setEmailAddress(vo.getEmailAddress());
					adminuserBO.setCompanyName(vo.getCompanyName());
					adminuserBO.setIndustryType(vo.getIndustryType());
					adminuserBO.setWebsite(vo.getWebSite());
					adminuserBO.setAddress(vo.getAddress());
					if (vo.getIsActive()) {
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
	}*/
	
	
	
	
	/*public User retrieveParticularCustomer(User user) {
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
	}*/
	
	
	public List<Customer> listOfCustomer(ClientBO clientbo) {
		
		
		List<Customer> pagecampaignlist = new ArrayList<Customer>();
		try {
			Session session = getSession();
			Criteria criteria = session.createCriteria(Customer.class);
			criteria.add(Restrictions.eq("isDelete", false));
			/*if (null != clientbo && 0< clientbo.getLoginBO().getId()) {
				criteria.add(Restrictions.eq("user.id", clientbo.getLoginBO().getId()));
			}*/
			/*
			 * if(null!=campaignBO.getAdminLoginBO().getName() &&
			 * !campaignBO.getAdminLoginBO().getName().isEmpty()) {
			 * criteria.add(Restrictions.ilike("name",
			 * campaignBO.getAdminLoginBO().getName(),MatchMode.ANYWHERE)); }
			 */
			if (null != clientbo.getFirstName() && !clientbo.getFirstName().isEmpty()) {
				criteria.add(Restrictions.ilike("campaignName", clientbo.getFirstName(), MatchMode.ANYWHERE));
			}
			
			/*if (null != clientbo.getCampaignMode() && !clientbo.getCampaignMode().isEmpty()) {
				criteria.add(Restrictions.ilike("campaignMode", clientbo.getCampaignMode(), MatchMode.ANYWHERE));
			}*/
			
			if (1< clientbo.getUserId()) {
				 criteria.add(Restrictions.eq("user.id",clientbo.getUserId())); 
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
	
	
	
	
	// mine
	
	public Contact insertCustomers(Contact contactvo) {
		Session session = getSession();
		session.save(contactvo);
		return contactvo; 
	}
	
	
	

	public List<Contact> retriveContact() {
		List<Contact> contactvo = new ArrayList<>();

	  try { 
			Session session = getSession();
			Criteria criteria = session.createCriteria(Contact.class);
			contactvo = criteria.list();
			if (null != contactvo && !contactvo.isEmpty() && contactvo.size()>0) {
				return contactvo;
			}
		} catch (Exception e) {
			System.out.println(e); 
			return null;
		}
		return contactvo;
	}
	
	
	
	

	public Contact getParticularValue(long Id) {

		Contact contactvo = new Contact();

		try {

			Session session = getSession();
			Criteria criteria = session.createCriteria(Contact.class);
			criteria.add(Restrictions.eq("id", Id));
			contactvo = (Contact) criteria.uniqueResult();
		} catch (Exception e) {
			System.out.println(e); 
			return null;
		}
		return contactvo;
	}
	
	
	
	

	public Contact getParticularContact(long editId) {

		Contact contact = new Contact();

		try {

			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Contact.class);
			criteria.add(Restrictions.eq("contactid", editId));
			contact = (Contact) criteria.uniqueResult(); 

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		return contact;
	}
	
	
	
	

	public Contact retrieveContact(Contact contactvo) {
		Session session = getSession();
		List<Contact> contactvolist = new ArrayList<Contact>();
		Criteria criteria = session.createCriteria(Contact.class);
		criteria.add(Restrictions.eq("customerVO", contactvo.getCustomerVO()));
		contactvolist = criteria.list();
		return contactvo;
	}
	
	
	

	public String updateContact(Contact contactvo) {

		try {

			Session session = sessionFactory.getCurrentSession();
			
			session.flush();
			session.update(contactvo);
			//getSession().flush();
		} catch (Exception e) {
			System.out.println(e); 
		}
		return null;
	}

	
	
	
	public String deleteContact(Contact contactvo) {

		try {

			Session session = sessionFactory.getCurrentSession();
			session.delete(contactvo);

			return "deleted";
		} catch (Exception e) {
			System.out.println(e); 
		}
		return null;

	}
	
	
}