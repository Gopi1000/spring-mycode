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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.scube.crm.bo.AdminBO;
import com.scube.crm.bo.AdminUserBO;
import com.scube.crm.exception.JLogger;
import com.scube.crm.exception.MyJobKartException;
import com.scube.crm.utils.ErrorCodes;
import com.scube.crm.vo.AccessLogVO;
import com.scube.crm.vo.AdminLoginVO;
import com.scube.crm.vo.AdminVO;
import com.scube.crm.vo.EmailAccessVO;
import com.scube.crm.vo.EmployerVO;
import com.scube.crm.vo.LoginStatusVO;


/**
 * Owner : Scube Technologies Created Date: Nov-22-2014 Created by : Vinoth P
 * Description : JobSeekerDAOImpl is a Class which is responsible for storing
 * the data into the database Reviewed by :
 * 
 * 
 */

@Repository("adminDAOImpl")
public class AdminDAOImpl implements AdminDAO {

	public AdminDAOImpl() throws MyJobKartException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	@Resource(name="sessionFactory")
	SessionFactory sessionFactory;

	
	protected Session getSession() {
	
		return sessionFactory.getCurrentSession();
		
	}

	private static final JLogger LOGGER = JLogger.getLogger(AdminDAOImpl.class);

	@Override
	public AdminLoginVO authendicate(String string, String emailAddress)
			throws MyJobKartException {
		AdminDAOImpl.LOGGER.entry();
		AdminLoginVO adminLoginVO = null;
		final String loginQuery = "FROM AdminLoginVO E WHERE E.emailAddress = :emailAddress";
		try {
			Session session = getSession();
			final Query query = session.createQuery(loginQuery);
			session.flush();
			query.setParameter("emailAddress", emailAddress);
			if (null != query.list() && query.list().size() > 0) {
				adminLoginVO = (AdminLoginVO) query.list().get(0);
			}

		} catch (final HibernateException he) {
			he.printStackTrace();
			if (AdminDAOImpl.LOGGER.isDebugEnabled()) {
				AdminDAOImpl.LOGGER.debug(ErrorCodes.ENTITY_CRE_FAIL + he);
			}
			throw new MyJobKartException(ErrorCodes.ENTITY_CRE_FAIL,
					ErrorCodes.ENTITY_CRE_FAIL_MSG);
		} finally {

			AdminDAOImpl.LOGGER.exit();
		}

		return adminLoginVO;

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
		// TODO Auto-generated method stub
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

	/* (non-Javadoc)
	 * @see com.scube.crm.dao.AdminDAO#createuser(com.scube.crm.vo.AdminUserVO)
	 */
	@Override
	public long createuser(AdminLoginVO adminVO) {
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
	
	public int createcustomer(AdminVO adminVO) throws MyJobKartException {
		AdminDAOImpl.LOGGER.entry();

		try {
			Session session = getSession();
			long logId = (Long) session.save(adminVO);
			session.flush();
			if (0 != logId) {
				return (int) logId;
			}
		} catch (HibernateException he) {
			he.printStackTrace();

		} finally {
			AdminDAOImpl.LOGGER.exit();
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.scube.crm.dao.AdminDAO#getusercount(com.scube.crm.bo.AdminUserBO)
	 */
	@Override
	public long getusercount(AdminUserBO bo) {
		long usercount = 0;
		Session session = getSession();
		/*usercount = (long) getSession().createCriteria(AdminUserVO.class)
				.setProjection(Projections.rowCount()).uniqueResult();*/

		return usercount;

	}

	/* (non-Javadoc)
	 * @see com.scube.crm.dao.AdminDAO#retrieveUser()
	 */
	@Override
	public List<AdminUserBO> retrieveUser() {
		AdminUserBO adminuserBO;
		List<AdminUserBO>BOList=new ArrayList<AdminUserBO>();
		List<AdminLoginVO>VOList=new ArrayList<AdminLoginVO>();
		try{
			int count=1;
			Criteria cr=getSession().createCriteria(AdminLoginVO.class);
			cr.add(Restrictions.eq("isDelete", false));
			//cr.add(Restrictions.eq("isActive", true));
			cr.addOrder(Order.desc("created"));
			VOList=cr.list();

			if(null !=VOList &&! VOList.isEmpty()){
				int data;
				for(AdminLoginVO vo:VOList){
					data=count++;
					adminuserBO=new AdminUserBO();
					adminuserBO.setId(vo.getId());
					adminuserBO.setActive(vo.isActive());
					adminuserBO.setsNo(data);
					adminuserBO.setName(vo.getName());
					adminuserBO.setMobileNo(vo.getMobileNo());
					adminuserBO.setUserType(vo.getUserType());
					adminuserBO.setPassword(vo.getPassword());
					adminuserBO.setEmailAddress(vo.getEmailAddress());
					adminuserBO.setConfirmPassword(vo.getConfirmpassword());
					if(vo.isActive()){
						adminuserBO.setStatus("Active");
					}else{
						adminuserBO.setStatus("De-Active");
					}
					BOList.add(adminuserBO);	  
				}
			}
		}
		catch (Exception ex) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Retrieve user has failed:" + ex.getMessage());
			}
			LOGGER.info("Retrieve user has failed:" + ex.getMessage());
		}	
		return BOList;
	}

	

	public List<AdminBO> retrieveCustomer() {
		AdminBO adminBO = new AdminBO();
		List<AdminBO>BOList=new ArrayList<AdminBO>();
		List<AdminVO>VOList=new ArrayList<AdminVO>();
		try{
			int count=0;
			Criteria cr=getSession().createCriteria(AdminLoginVO.class);
			cr.add(Restrictions.eq("isDelete", false));
			//cr.add(Restrictions.eq("isActive", true));
			//cr.add(Restrictions.eq("id",adminId));
			cr.addOrder(Order.desc("created"));
			VOList=cr.list();

			if(null !=VOList &&! VOList.isEmpty()){
				for(AdminVO vo:VOList){
					count=count++;
					adminBO=new AdminBO();
					adminBO.setAdminId(vo.getAdminId());
					adminBO.setIsActive(vo.isActive());
					adminBO.setIsDelete(vo.isDelete());
					adminBO.setVersion(vo.getVersion());
					adminBO.setClientName(vo.getClientName());
					adminBO.setPrimaryContactNumber(vo.getPrimaryContactNumber());
					adminBO.setSecondaryContactNumber(vo.getSecondaryContactNumber());
					adminBO.setFinalContactNumber(vo.getFinalContactNumber());
					adminBO.setPrimaryAddress(vo.getPrimaryAddress());
					adminBO.setSecondaryAddress(vo.getSecondaryAddress());
					adminBO.setPermanentAddress(vo.getPermanentAddress());
					if(vo.isActive()){
						adminBO.setStatus("Active");
					}else{
						adminBO.setStatus("De-Active");
					}
					BOList.add(adminBO);	  
				}
			}
		}
		catch (Exception ex) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Retrieve user has failed:" + ex.getMessage());
			}
			LOGGER.info("Retrieve user has failed:" + ex.getMessage());
		}	
		return BOList;
	}

	/* (non-Javadoc)
	 * @see com.scube.crm.dao.AdminDAO#userStatus(com.scube.crm.vo.AdminUserVO)
	 */
	@Override
	public AdminLoginVO userStatus(AdminLoginVO userVO) {
		final String changeLoginQuery = "UPDATE AdminLoginVO E set E.isActive = :isActive WHERE E.id= :id";
		try {
			// session = getSession();
			final Query query = getSession().createQuery(changeLoginQuery);
			query.setParameter("isActive",userVO.isActive());
			query.setParameter("id",userVO.getId());
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
	
	/*public AdminVO customerStatus(AdminVO userVO)throws MyJobKartException {
		final String changeLoginQuery = "UPDATE AdminVO E set E.isActive = :isActive WHERE E.adminId= :adminId";
		try {
			// session = getSession();
			final Query query = getSession().createQuery(changeLoginQuery);
			query.setParameter("isActive",userVO.isActive());
			query.setParameter("id",userVO.getAdminId());
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
	}*/

	/* (non-Javadoc)
	 * @see com.scube.crm.dao.AdminDAO#DeleteProfile(com.scube.crm.vo.AdminUserVO)
	 */
	@Override
	public int deleteProfile(AdminLoginVO vo) {
		AdminDAOImpl.LOGGER.entry();
		int result = 0;
		/*"UPDATE EmployerProfileVO S set"
		+ " S.isDelete = :isDelete," + "S.deletedDate = :deletedDate,"
		+ "S.deleteBy = :deleteBy," + "S.modifiedBy = :modifiedBy,"
		+ "S.modified=:modified" + " WHERE S.profileId = :profileId";*/
		final String deleteQuery = "UPDATE AdminLoginVO S set"
				+ " S.isDelete = :isDelete" 
				+ " WHERE S.id = :id";
		try {

			final Query query = getSession().createQuery(deleteQuery);
			query.setParameter("isDelete", vo.isDelete());
			query.setParameter("id", vo.getId());
			result = query.executeUpdate();
		} catch (final HibernateException he) {
			he.printStackTrace();
			if (EmployerDAOImpl.LOGGER.isDebugEnabled()) {
				EmployerDAOImpl.LOGGER.debug(ErrorCodes.ENTITY_DELETE_FAIL
						+ he);
			}

		} 

		return result;
	}




	/* (non-Javadoc)
	 * @see com.scube.crm.dao.AdminDAO#updateContactus(com.scube.crm.vo.AdminLoginVO)
	 */
	@Override
	public AdminLoginVO updateuser(AdminLoginVO loginVO) throws MyJobKartException {

		AdminDAOImpl.LOGGER.entry();
		try {
			Session session = getSession();
			session.saveOrUpdate(loginVO);
			getSession().flush();
		} catch (final HibernateException he) {
			he.printStackTrace();
			if (AdminDAOImpl.LOGGER.isDebugEnabled()) {
				AdminDAOImpl.LOGGER
				.debug(ErrorCodes.ENTITY_UPDATE_FAIL + he);
			}
			throw new MyJobKartException(ErrorCodes.ENTITY_UPDATE_FAIL,
					ErrorCodes.ENTITY_UPDATE_FAIL_MSG);
		} finally {

			AdminDAOImpl.LOGGER.exit();
		}
		return loginVO;

	}
	
	public AdminVO updatecustomer(AdminVO loginVO) throws MyJobKartException {
		
		AdminDAOImpl.LOGGER.entry();
		try {
			Session session = getSession();
			session.saveOrUpdate(loginVO);
			getSession().flush();
		} catch (final HibernateException he) {
			he.printStackTrace();
			if (AdminDAOImpl.LOGGER.isDebugEnabled()) {
				AdminDAOImpl.LOGGER
				.debug(ErrorCodes.ENTITY_UPDATE_FAIL + he);
			}
			throw new MyJobKartException(ErrorCodes.ENTITY_UPDATE_FAIL,
					ErrorCodes.ENTITY_UPDATE_FAIL_MSG);
		} finally {

			AdminDAOImpl.LOGGER.exit();
		}
		return loginVO;

	}

	/* (non-Javadoc)
	 * @see com.scube.crm.dao.AdminDAO#retrieveusers(long)
	 */
	@Override
	public AdminUserBO retrieveusers(long userId) {
		AdminUserBO adminuserBO = new AdminUserBO();
		List<AdminUserBO>BOList=new ArrayList<AdminUserBO>();
		List<AdminLoginVO>VOList=new ArrayList<AdminLoginVO>();
		try{
			int count=0;
			Criteria cr=getSession().createCriteria(AdminLoginVO.class);
			cr.add(Restrictions.eq("isDelete", false));
			//cr.add(Restrictions.eq("isActive", true));
			cr.add(Restrictions.eq("id", userId));
			cr.addOrder(Order.desc("created"));
			VOList=cr.list();

			if(null !=VOList &&! VOList.isEmpty()){
				for(AdminLoginVO vo:VOList){
					count=count++;
					adminuserBO=new AdminUserBO();
					adminuserBO.setId(vo.getId());
					adminuserBO.setActive(vo.isActive());
					adminuserBO.setName(vo.getName());
					adminuserBO.setMobileNo(vo.getMobileNo());
					adminuserBO.setPassword(vo.getPassword());
					adminuserBO.setEmailAddress(vo.getEmailAddress());
					adminuserBO.setUserType(vo.getUserType());
					adminuserBO.setConfirmPassword(vo.getConfirmpassword());
					if(vo.isActive()){
						adminuserBO.setStatus("Active");
					}else{
						adminuserBO.setStatus("De-Active");
					}
					BOList.add(adminuserBO);	  
				}
			}
		}
		catch (Exception ex) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Retrieve user has failed:" + ex.getMessage());
			}
			LOGGER.info("Retrieve user has failed:" + ex.getMessage());
		}	

		return adminuserBO;
	}
	
	
	public AdminBO retrieveCustomers(int userId) {
		AdminBO adminBO = new AdminBO();
		List<AdminBO>BOList=new ArrayList<AdminBO>();
		List<AdminVO>VOList=new ArrayList<AdminVO>();
		try{
			int count=0;
			Criteria cr=getSession().createCriteria(AdminLoginVO.class);
			cr.add(Restrictions.eq("isDelete", false));
			//cr.add(Restrictions.eq("isActive", true));
			cr.add(Restrictions.eq("id", userId));
			cr.addOrder(Order.desc("created"));
			VOList=cr.list();

			if(null !=VOList &&! VOList.isEmpty()){
				for(AdminVO vo:VOList){
					count=count++;
					adminBO=new AdminBO();
					adminBO.setAdminId(vo.getAdminId());
					adminBO.setIsActive(vo.isActive());
					adminBO.setIsDelete(vo.isDelete());
					adminBO.setVersion(vo.getVersion());
					adminBO.setClientName(vo.getClientName());
					adminBO.setPrimaryContactNumber(vo.getPrimaryContactNumber());
					adminBO.setSecondaryContactNumber(vo.getSecondaryContactNumber());
					adminBO.setFinalContactNumber(vo.getFinalContactNumber());
					adminBO.setPrimaryAddress(vo.getPrimaryAddress());
					adminBO.setSecondaryAddress(vo.getSecondaryAddress());
					adminBO.setPermanentAddress(vo.getPermanentAddress());
					if(vo.isActive()){
						adminBO.setStatus("Active");
					}else{
						adminBO.setStatus("De-Active");
					}
					BOList.add(adminBO);	  
				}
			}
		}
		catch (Exception ex) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Retrieve user has failed:" + ex.getMessage());
			}
			LOGGER.info("Retrieve user has failed:" + ex.getMessage());
		}	

		return adminBO;
	}
	
	
	
	/*@Override
	public AdminBO retrieveCustomer(long adminId) {
		AdminBO adminBO = new AdminBO();
		List<AdminBO>BOList=new ArrayList<AdminBO>();
		List<AdminVO>VOList=new ArrayList<AdminVO>();
		try{
			int count=0;
			Criteria cr=getSession().createCriteria(AdminLoginVO.class);
			cr.add(Restrictions.eq("isDelete", false));
			//cr.add(Restrictions.eq("isActive", true));
			cr.add(Restrictions.eq("id",adminId));
			cr.addOrder(Order.desc("created"));
			VOList=cr.list();

			if(null !=VOList &&! VOList.isEmpty()){
				for(AdminVO vo:VOList){
					count=count++;
					adminBO=new AdminBO();
					adminBO.setAdminId(vo.getAdminId());
					adminBO.setIsActive(vo.isActive());
					adminBO.setIsDelete(vo.isDelete());
					adminBO.setClientName(vo.getClientName());
					adminBO.setPrimaryContactNumber(vo.getPrimaryContactNumber());
					adminBO.setSecondaryContactNumber(vo.getSecondaryContactNumber());
					adminBO.setFinalContactNumber(vo.getFinalContactNumber());
					adminBO.setPrimaryAddress(vo.getPrimaryAddress());
					adminBO.setSecondaryAddress(vo.getSecondaryAddress());
					adminBO.setPermanentAddress(vo.getPermanentAddress());
					if(vo.isActive()){
						adminBO.setStatus("Active");
					}else{
						adminBO.setStatus("De-Active");
					}
					BOList.add(adminBO);	  
				}
			}
		}
		catch (Exception ex) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Retrieve user has failed:" + ex.getMessage());
			}
			LOGGER.info("Retrieve user has failed:" + ex.getMessage());
		}	
		return adminBO;
	}*/

	/* (non-Javadoc)
	 * @see com.scube.crm.dao.AdminDAO#getuserId(com.scube.crm.vo.AdminLoginVO)
	 */
	@Override
	public AdminLoginVO getuserId(AdminUserBO loginBO) {
		Session session = getSession();
		
		AdminLoginVO adminLoginVO = (AdminLoginVO) session.get(
				AdminLoginVO.class, loginBO.getId());
		return adminLoginVO;
	}
	
	public AdminVO getadminId(AdminBO adminBO) throws MyJobKartException {
        Session session = getSession();
		
		AdminVO adminLoginVO = (AdminVO) session.get(
				AdminVO.class, adminBO.getAdminId());
		return adminLoginVO;
	}

	/* (non-Javadoc)
	 * @see com.scube.crm.dao.AdminDAO#findByEmail(java.lang.String, java.lang.String)
	 */
	@Override
	public AdminLoginVO findByEmail(String string, String emailAddress) {
		AdminDAOImpl.LOGGER.entry();
		AdminLoginVO loginVO = null;
		final String loginQuery = "FROM AdminLoginVO E WHERE E.emailAddress = :emailAddress";
		try {
			Session session = getSession();
			final Query query = session.createQuery(loginQuery);
			query.setParameter("emailAddress", emailAddress);
			if (null != query.list() && query.list().size() > 0) {
				loginVO = (AdminLoginVO) query.list().get(0);
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
	public AdminLoginVO findByMobileNo(String string, String mobileNo) {
		AdminDAOImpl.LOGGER.entry();
		AdminLoginVO loginVO = null;
		final String loginQuery = "FROM AdminLoginVO E WHERE E.mobileNo = :mobileNo";
		try {
			Session session = getSession();
			final Query query = session.createQuery(loginQuery);
			query.setParameter("mobileNo", mobileNo);
			if (null != query.list() && query.list().size() > 0) {
				loginVO = (AdminLoginVO) query.list().get(0);
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

	/* (non-Javadoc)
	 * @see com.scube.crm.dao.AdminDAO#findEmployerEmail(java.lang.String)
	 */
	@Override
	public boolean findEmployerEmail(String emailAddress) {
		// TODO Auto-generated method stub
		
		
		Session session= getSession();
		Criteria criteria=session.createCriteria(EmployerVO.class);
		criteria.add(Restrictions.eq("emailAddress", emailAddress));
		criteria.add(Restrictions.eq("isDelete", false));
		List<EmployerVO> employerRegisteration=criteria.list();
		long emailvalidation=0;
		boolean validation;
		if(null!=employerRegisteration){
			emailvalidation=emailvalidation=employerRegisteration.size();
		}
		if(emailvalidation!=0){
			validation=true;
			return validation;
		}else{
			validation=false;
			return validation;
		}
		
		
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
					if(null != obj){
						imageName = obj[0].toString() +"," +obj[1];
					}else{
						imageName = "companylogo.jpg";
					}

					if (null != imageName && ! imageName.isEmpty()) {
						imageNameList.add(imageName);
					}
				}			
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return imageNameList;
	}

	/* (non-Javadoc)
	 * @see com.scube.crm.dao.AdminDAO#saveEmailList(java.util.List)
	 */
	@Override
	public EmailAccessVO saveEmailList(List<EmailAccessVO> accessVOList) {
		AdminDAOImpl.LOGGER.entry();
		EmailAccessVO accessVO=new EmailAccessVO();
		try {
			long sendId=0;
			Session session=getSession();
			for(EmailAccessVO emailaccessVO :accessVOList){
				sendId=(Long) session.save(emailaccessVO);
				session.flush();
				session.clear();
			}
			accessVO.setSendId(sendId);
		}catch(Exception e){
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Email Tracking History failed:"
						+ e.getMessage());
			}
			LOGGER.info("Email Tracking History failed:" + e.getMessage());
			
		}
		finally{
			AdminDAOImpl.LOGGER.exit();	
		}
		return accessVO;
	}

	/* (non-Javadoc)
	 * @see com.scube.crm.dao.AdminDAO#saveEmailList(java.util.List)
	 */
	






}
