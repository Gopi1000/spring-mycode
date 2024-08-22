package com.scube.crm.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;

import org.apache.commons.lang.WordUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.OrderBy;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.SearchException;
import org.hibernate.search.bridge.BridgeException;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.scube.crm.bo.AdminLoginBO;
import com.scube.crm.bo.EmployerBO;
import com.scube.crm.bo.EmployerRegisterBO;
import com.scube.crm.exception.JLogger;
import com.scube.crm.exception.MyJobKartException;
import com.scube.crm.service.EmployerService;
import com.scube.crm.service.GenericService;
import com.scube.crm.utils.DateHelper;
import com.scube.crm.utils.ErrorCodes;
import com.scube.crm.utils.MapSorting;
import com.scube.crm.vo.AdminLoginVO;
import com.scube.crm.vo.BODTO;
import com.scube.crm.vo.BasicEntity;
import com.scube.crm.vo.ClientUpdateVO;
import com.scube.crm.vo.EmailAccessVO;
import com.scube.crm.vo.EmployerVO;



/**
 * Owner : Scube Technologies Created Date: Nov-22-2014 Created by : Vinoth P
 * Description : JobSeekerDAOImpl is a Class which is responsible for storing
 * the data into the database Reviewed by :
 * 
 * j
 */

@Repository("jobSeekerDAOImpl")
public class EmployerDAOImpl  implements EmployerDAO {


	@Resource(name="sessionFactory")
	SessionFactory sessionFactory;


	protected Session getSession() {

		return sessionFactory.getCurrentSession();

	}

	public EmployerDAOImpl() throws MyJobKartException {
		super();
		// TODO Auto-generated constructor stub
	}

	SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
	public static final JLogger LOGGER = JLogger
			.getLogger(EmployerDAOImpl.class);
	EmployerRegisterBO profileBO;



	public static Date getDateWithoutTime(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	/**
	 * 
	 */

	@Override
	public EmployerRegisterBO retriveJobseeker(
			EmployerRegisterBO jobseekerProfileBO) throws MyJobKartException,
			SerialException, SQLException {
		EmployerDAOImpl.LOGGER.entry();
		EmployerRegisterBO profileBO = new EmployerRegisterBO();
		EmployerRegisterBO jobProfile = new EmployerRegisterBO();
		List<ClientUpdateVO>updateVOList=new ArrayList<ClientUpdateVO>();
		List<EmailAccessVO>accessVOList=new ArrayList<EmailAccessVO>();
		List<String>emaildateList=new ArrayList<String>();
		List<String>descriptionList=new ArrayList<String>();

		try {
			long sno = 1;
			List<EmployerVO> employerProfilesList = new ArrayList<EmployerVO>();
			Session session = getSession();
			final Criteria cr = session.createCriteria(EmployerVO.class);
			if( 0!= jobseekerProfileBO.getId()){
				cr.add(Restrictions.eq("id", jobseekerProfileBO.getId()));
			}
			if (null != jobseekerProfileBO.getFirstName() && !jobseekerProfileBO.getFirstName().isEmpty()) {
				cr.add(Restrictions.ilike("firstName",jobseekerProfileBO.getFirstName(),MatchMode.ANYWHERE));
			}
			if(null!=jobseekerProfileBO.getMobileNo() && !jobseekerProfileBO.getMobileNo().isEmpty()){
				cr.add(Restrictions.eq("mobileNumber",jobseekerProfileBO.getMobileNo()));
			}

			if(null !=jobseekerProfileBO.getEmailAddress() && !jobseekerProfileBO.getEmailAddress().isEmpty()){
				cr.add(Restrictions.ilike("emailAddress",jobseekerProfileBO.getEmailAddress(),MatchMode.ANYWHERE));
			}

			if(null !=jobseekerProfileBO.getCompanyName() && !jobseekerProfileBO.getCompanyName().isEmpty()){
				cr.add(Restrictions.ilike("companyName",jobseekerProfileBO.getCompanyName(),MatchMode.ANYWHERE));
			}
			if(null !=jobseekerProfileBO.getIndustryType() && !jobseekerProfileBO.getIndustryType().isEmpty()){
				cr.add(Restrictions.ilike("industryType",jobseekerProfileBO.getIndustryType(),MatchMode.ANYWHERE));
			}

			//created By 

			if(0!=jobseekerProfileBO.getCreatedBy() && (null!=jobseekerProfileBO.getStarDate() && null!=jobseekerProfileBO.getEndDate())){
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String startDate=df.format(jobseekerProfileBO.getStarDate());
				String endDate=df.format(jobseekerProfileBO.getEndDate());
				Date fromDate = df.parse(startDate);
				Date toDate = df.parse(endDate);
				if(fromDate.equals(toDate)){
					cr.add(Restrictions.eq("created",fromDate));

				}else{
					cr.add(Restrictions.between("created",fromDate,toDate));	
				}
				cr.add(Restrictions.eq("createdBy",jobseekerProfileBO.getCreatedBy()));
			}else if(0!=jobseekerProfileBO.getCreatedBy()){
				cr.add(Restrictions.eq("createdBy",jobseekerProfileBO.getCreatedBy()));
			}

			if(0!=jobseekerProfileBO.getModifiedBy() && (null!=jobseekerProfileBO.getStarDate() && null!=jobseekerProfileBO.getEndDate())){

				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String startDate=df.format(jobseekerProfileBO.getStarDate());
				String endDate=df.format(jobseekerProfileBO.getEndDate());
				Date fromDate = df.parse(startDate);
				Date toDate = df.parse(endDate);
				if(fromDate.equals(toDate)){

					cr.add(Restrictions.eq("modified",fromDate));	
				}else{
					cr.add(Restrictions.between("modified",fromDate,toDate));
				}
				cr.add(Restrictions.eq("modifiedBy",jobseekerProfileBO.getModifiedBy()));

			}else if(0!=jobseekerProfileBO.getModifiedBy()){
				cr.add(Restrictions.eq("modifiedBy",jobseekerProfileBO.getModifiedBy()));
			}

			if(0!=jobseekerProfileBO.getAdminId() && (0==jobseekerProfileBO.getModifiedBy()) && (0==jobseekerProfileBO.getCreatedBy())){


				//Search By Created and  Modified Id 
				cr.add(Restrictions.or(Restrictions.eq("createdBy",jobseekerProfileBO.getAdminId())
						,Restrictions.eq("modifiedBy",jobseekerProfileBO.getAdminId()))); 

			} //else if((null!=jobseekerProfileBO.getStarDate() && null!=jobseekerProfileBO.getEndDate()) ){ //|| (0==jobseekerProfileBO.getModifiedBy()) && (0==jobseekerProfileBO.getCreatedBy())){
			/*Calendar fromDate=Calendar.getInstance();
				fromDate.setTime(jobseekerProfileBO.getStarDate());
				Calendar toDate=Calendar.getInstance();
				toDate.setTime(jobseekerProfileBO.getEndDate());*/

			if(null!=jobseekerProfileBO.getStarDate() && null!=jobseekerProfileBO.getEndDate() && jobseekerProfileBO.getProcess().equalsIgnoreCase("all")){


				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String startDate=df.format(jobseekerProfileBO.getStarDate());
				String endDate=df.format(jobseekerProfileBO.getEndDate());
				Date fromDate = df.parse(startDate);
				Date toDate = df.parse(endDate);

				if(fromDate.equals(toDate)){
					//cr.add(Restrictions.ge("created",jobseekerProfileBO.getStarDate()));
					cr.add(Restrictions.eq("created", jobseekerProfileBO.getEndDate()));	
				}else{
					cr.add(Restrictions.between("created",fromDate,toDate));	
				}

			}
			if(jobseekerProfileBO.getEmployerId() != 1){
				cr.add(Restrictions.or(Restrictions.eq("assigned",Long.valueOf(1)),Restrictions.eq("assigned",jobseekerProfileBO.getEmployerId())));
			}
			cr.add(Restrictions.eq("isDelete",false));
			cr.add(Restrictions.eq("isActive", true));
			cr.addOrder(Order.desc("id"));
			if (null != jobseekerProfileBO.getPagination()
					&& !jobseekerProfileBO.getPagination().isEmpty()) {
				cr.setFirstResult(jobseekerProfileBO.getRecordIndex());
				cr.setMaxResults(jobseekerProfileBO.getMaxRecord());

			}
			employerProfilesList = cr.list();
			//employerProfilesList.addAll(cr1.list());
			if(0 !=jobseekerProfileBO.getRecordIndex()){
				sno= jobseekerProfileBO.getRecordIndex() + 1;
			}
			if (null != employerProfilesList
					&& 0 != employerProfilesList.size()
					&& null != employerProfilesList) {

				SimpleDateFormat simpleDate= new SimpleDateFormat("dd/mm/yyyy");

				List<EmployerRegisterBO> jobseekerprofileList = new ArrayList<EmployerRegisterBO>();
				for (EmployerVO profileVO : employerProfilesList) {
					final Criteria criteria = session.createCriteria(ClientUpdateVO.class);
					if( 0!= jobseekerProfileBO.getId()){
						cr.add(Restrictions.eq("profileVO.id", jobseekerProfileBO.getId()));
						updateVOList = criteria.list();
					}

					profileBO = new EmployerRegisterBO();
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

					AdminLoginVO adminLoginVO=(AdminLoginVO) session.
							get(AdminLoginVO.class,profileVO.getAssigned());

					if(null !=adminLoginVO.getName()){
						profileBO.setAssignedname(adminLoginVO.getName());
					}

					/*	if(0 !=profileVO.getAssigned()){
						profileBO.setEmployerId(profileVO.getAssigned());*/



					profileBO.setCreatedBy(profileVO.getCreatedBy());
					profileBO.setModifiedBy(profileVO.getModifiedBy());



					if(null !=updateVOList && 0 !=updateVOList.size()){

						for(ClientUpdateVO VO:updateVOList){
							if(null !=VO.getVO()&&0!= VO.getVO().getId()){
								if(profileVO.getId()==VO.getVO().getId()){
									if(null != VO.getDescription() && null != VO.getDate()){
										String description = VO.getDescription() +":" +VO.getDate();
										descriptionList.add(description);
										profileBO.setDescriptionList(descriptionList);
									}
								}
							}
						}

					}

					jobseekerprofileList.add(profileBO);

					jobProfile.setJobseekerProfileList(jobseekerprofileList);
				}
			}
			if( 0!= jobseekerProfileBO.getId()){
				Criteria criteria=session.createCriteria(EmailAccessVO.class);
				criteria.add(Restrictions.eq("mailTO", jobseekerProfileBO.getId()));
				accessVOList = criteria.list();
			}
			if(null !=accessVOList && 0 !=accessVOList.size()){
				for(EmailAccessVO accessVO :accessVOList){
					if (null != accessVO.getDate()){	
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						String empdate = sdf.format(accessVO.getDate());
						emaildateList.add(empdate);

					}
				}
				jobProfile.setEmaildateList(emaildateList);
			}


		} catch (Exception he) {
			he.printStackTrace();
			EmployerDAOImpl.LOGGER.debug(he, he.getMessage());
		} finally {

			EmployerDAOImpl.LOGGER.exit();
		}
		return jobProfile;
	}




	@Override
	public EmployerRegisterBO retriveJobseekerById(
			EmployerRegisterBO employerRegisterBO) throws MyJobKartException,
			SerialException, SQLException {
		EmployerDAOImpl.LOGGER.entry();
		EmployerRegisterBO registerBO = new EmployerRegisterBO();
		try {
			long sno = 1;
			List<EmployerVO> employerList = new ArrayList<EmployerVO>();
			Session session = getSession();
			final Criteria cr = session.createCriteria(EmployerVO.class);
			if( 0!= employerRegisterBO.getId()){
				EmployerVO employerVO=(EmployerVO) session.get(EmployerVO.class,employerRegisterBO.getId(),new LockOptions(LockMode.PESSIMISTIC_WRITE));
				employerList.add(employerVO);
			}else{
				if (null != employerRegisterBO.getPagination()
						&& !employerRegisterBO.getPagination().isEmpty()) {
					cr.setFirstResult(employerRegisterBO.getRecordIndex());
					cr.setMaxResults(employerRegisterBO.getMaxRecord());
				}
				employerList = cr.list();
			}

			if (null != employerList
					&& 0 != employerList.size()
					&& null != employerList) {

				for (EmployerVO profileVO : employerList) {

					registerBO = new EmployerRegisterBO();
					registerBO.setsNo(sno++);
					registerBO.setCreatedDate(format.format(profileVO
							.getCreated()));
					registerBO.setCreated(profileVO.getCreated());
					registerBO.setId(profileVO.getId());
					if(null !=profileVO.getFirstName()){
						registerBO.setFirstName(profileVO.getFirstName());
					}
					if(null !=profileVO.getEmailAddress()){
						registerBO.setEmailAddress(profileVO.getEmailAddress());
					}
					if(null !=profileVO.getLastName()){
						registerBO.setLastName(profileVO.getLastName());	
					}
					if(null !=profileVO.getPassword()){
						registerBO.setPassword(profileVO.getPassword());	
					}
					if(null !=profileVO.getConfirmPassword()){
						registerBO.setConfirmPassword(profileVO.getConfirmPassword());
					}
					if(null !=profileVO.getMobileNumber()){
						registerBO.setMobileNo(profileVO.getMobileNumber());
					}
					if(null !=profileVO.getContactNumber()){
						registerBO.setContactNo(profileVO.getContactNumber());
					}
					if(null !=profileVO.getConfirmEmailAddress()){
						registerBO.setConfirmEmailAddress(profileVO.getConfirmEmailAddress());
					}
					if(null !=profileVO.getWebSite()){
						registerBO.setWebsite(profileVO.getWebSite());
					}
					if(null !=profileVO.getCompanyName()){
						registerBO.setCompanyName(profileVO.getCompanyName());
					}
					if(null !=profileVO.getIndustryType()){
						registerBO.setIndustryType(profileVO.getIndustryType());
					}
					if(0 !=profileVO.getId()){
						registerBO.setId(profileVO.getId());
					}
					if(null !=profileVO.getAddress()){
						registerBO.setAddress(profileVO.getAddress());
					}
					if(null !=profileVO.getStatus()){
						registerBO.setStatus(profileVO.getStatus());
					}
				}
			}
		} catch (Exception he) {
			he.printStackTrace();
			EmployerDAOImpl.LOGGER.debug(he, he.getMessage());
		} finally {

			EmployerDAOImpl.LOGGER.exit();
		}
		return registerBO;
	}


	@Override
	public EmployerVO createEmployer(EmployerVO employerRegVO) throws MyJobKartException {
		Session session= getSession();
		session.saveOrUpdate(employerRegVO);
		return employerRegVO;
	}
	@Override
	public EmployerVO getuserId(EmployerRegisterBO registerBO) throws MyJobKartException {
		Session session = getSession();
		EmployerVO employerVO =new EmployerVO();
		try {
			employerVO = (EmployerVO) session.get(
					EmployerVO.class, registerBO.getId());
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return employerVO;
	}

	/* (non-Javadoc)
	 * @see com.scube.crm.dao.JobSeekerDAO#updateuser(com.scube.crm.vo.EmployerVO)
	 */
	@Override
	public EmployerVO updateEmployer(EmployerVO employerVO) throws MyJobKartException {
		EmployerDAOImpl.LOGGER.entry();
		try {
			Session session = getSession();
			session.saveOrUpdate(employerVO);
			getSession().flush();
		} catch (final HibernateException he) {
			he.printStackTrace();
			if (EmployerDAOImpl.LOGGER.isDebugEnabled()) {
				EmployerDAOImpl.LOGGER
				.debug(ErrorCodes.ENTITY_UPDATE_FAIL + he);
			}

		}
		return employerVO;
	}

	/* (non-Javadoc)
	 * @see com.scube.crm.dao.JobSeekerDAO#deleteProfile(com.scube.crm.vo.EmployerVO)
	 */
	@Override
	public EmployerVO deleteProfile(EmployerVO vo)  throws MyJobKartException {
		Session session = getSession();
		EmployerVO employerVO = (EmployerVO) session.get(
				EmployerVO.class, vo.getId());

		try{
			employerVO.setIsDelete(true);
			session.saveOrUpdate(employerVO);

		} catch (final HibernateException he) {
			employerVO = null;
			he.printStackTrace();
			EmployerDAOImpl.LOGGER.debug(he, he.getMessage());
		} finally {
			EmployerDAOImpl.LOGGER.exit();
		}
		return employerVO;

	}


	/* (non-Javadoc)
	 * @see com.scube.crm.dao.JobSeekerDAO#employerCount(com.scube.crm.bo.EmployerRegisterBO)
	 */
	@Override
	public long employerCount(EmployerRegisterBO registerBO) {
		if(null ==registerBO.getMobileNo()){
			registerBO.setMobileNo(null);

		}
		long jobseekerProfileCount = 0;
		try{
			Session session = getSession();
			if(null !=registerBO.getFirstName()&& !registerBO.getFirstName().isEmpty()
					||null !=registerBO.getCompanyName() && !registerBO.getCompanyName().isEmpty()
					||null !=registerBO.getEmailAddress() && !registerBO.getEmailAddress().isEmpty()
					||null !=registerBO.getIndustryType() && ! registerBO.getIndustryType().isEmpty()
					||null !=registerBO.getMobileNo()){
				Criteria cr = session.createCriteria(EmployerVO.class);
				if(null!=registerBO.getCompanyName() && !registerBO.getCompanyName().isEmpty()){
					cr.add(Restrictions.ilike("companyName", registerBO.getCompanyName(),MatchMode.ANYWHERE));
				}

				if(null!=registerBO.getFirstName() && !registerBO.getFirstName().isEmpty()){
					cr.add(Restrictions.ilike("firstName", registerBO.getFirstName(),MatchMode.ANYWHERE));
				}
				if(null!=registerBO.getEmailAddress() &&! registerBO.getEmailAddress().isEmpty()){
					cr.add(Restrictions.ilike("emailAddress", registerBO.getEmailAddress(),MatchMode.ANYWHERE));
				}
				if(null!=registerBO.getIndustryType() && !registerBO.getIndustryType().isEmpty()){
					cr.add(Restrictions.ilike("industryType", registerBO.getIndustryType(),MatchMode.ANYWHERE));
				}

				if(null !=registerBO.getMobileNo() && !registerBO.getMobileNo().isEmpty())
				{
					cr.add(Restrictions.eq("mobileNumber",registerBO.getMobileNo()));
				}
				cr.add(Restrictions.eq("isDelete", false));
				cr.add(Restrictions.eq("isActive", true));
				cr.setProjection(Projections.rowCount());
				jobseekerProfileCount = (long)cr.uniqueResult();
			}
			else{

				Criteria cr = session.createCriteria(EmployerVO.class);
				if(0!=registerBO.getAdminId()){
					cr.add(Restrictions.eq("loginVO.id", registerBO.getAdminId()));
				}


				if(null!=registerBO.getStarDate() && null!=registerBO.getEndDate()){
					/*Calendar fromDate=Calendar.getInstance();
				fromDate.setTime(jobseekerProfileBO.getStarDate());
				Calendar toDate=Calendar.getInstance();
				toDate.setTime(jobseekerProfileBO.getEndDate());*/


					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String startDate=df.format(registerBO.getStarDate());
					String endDate=df.format(registerBO.getEndDate());
					Date fromDate = df.parse(startDate);
					Date toDate = df.parse(endDate);

					if(fromDate.equals(toDate)){
						cr.add(Restrictions.eq("created",fromDate));
					}else{
						cr.add(Restrictions.between("modified",fromDate,toDate));
					}



					/*Date fromDate = getDateWithoutTime(jobseekerProfileBO.getStarDate());
				Date toDate = getDateWithoutTime(jobseekerProfileBO.getEndDate());

				cr.add(Restrictions.eq("modified",jobseekerProfileBO.getStarDate()));
				cr.add(Restrictions.ge("modified", jobseekerProfileBO.getEndDate()));*/

				}

				if(0!=registerBO.getCreatedBy()){
					cr.add(Restrictions.eq("loginVO.id",registerBO.getCreatedBy()));
				}
				if(0!=registerBO.getModifiedBy()){
					cr.add(Restrictions.eq("loginVO.id",registerBO.getModifiedBy()));
				}

				if(0!=registerBO.getAdminId()){
					cr.add(Restrictions.eq("loginVO.id",registerBO.getAdminId()));
				}
				cr.add(Restrictions.eq("isDelete", false));
				cr.add(Restrictions.eq("isActive", true));

				cr.setProjection(Projections.rowCount());
				jobseekerProfileCount=(long) cr.uniqueResult();
				/*jobseekerProfileCount = (long) getSession().createCriteria(EmployerVO.class)
				.setProjection(Projections.rowCount()).uniqueResult();*/
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return jobseekerProfileCount;

	}




	/* (non-Javadoc)
	 * @see com.scube.crm.dao.JobSeekerDAO#retriveRecords(com.scube.crm.bo.JobseekerBO)
	 */


	@Override
	public List<EmployerRegisterBO> retriveAccessRecords(EmployerRegisterBO recordBO) {
		EmployerDAOImpl.LOGGER.entry();
		List<EmployerRegisterBO> recordsList = new ArrayList<EmployerRegisterBO>();
		try {
			long sno = 0;
			List<EmployerVO> accessRecordsList = new ArrayList<EmployerVO>();
			Session session = getSession();
			final Criteria cr = session.createCriteria(EmployerVO.class);

			if (null != recordBO.getPagination()
					&& !recordBO.getPagination().isEmpty()) {
				cr.setFirstResult(recordBO.getRecordIndex());
				cr.setMaxResults(recordBO.getMaxRecord());
				cr.addOrder(Order.desc("modified"));
			}
			cr.add(Restrictions.eq("isDelete",true));

			/*if(null != recordBO.getModified()){
				cr.add(Restrictions.ge("modified", recordBO.getModified()));
			}
			if(null != recordBO.getModified()){
				cr.add(Restrictions.le("modified", recordBO.getModified()));
			}*/

			if(null != recordBO.getFirstName() && !recordBO.getFirstName().isEmpty()){
				cr.createCriteria("adminLogin").add(Restrictions.ilike("name", recordBO.getFirstName(),MatchMode.ANYWHERE));
			}

			if(0 != recordBO.getRecordIndex()){
				sno = recordBO.getRecordIndex();
			}
			accessRecordsList = cr.list();

			if(null != accessRecordsList && 0 != accessRecordsList.size()){

				for(EmployerVO employerRecordVO:accessRecordsList){
					sno = sno + 1;
					recordBO = new EmployerRegisterBO();
					recordBO.setsNo(sno); 
					recordBO.setCreated(employerRecordVO.getCreated());
					recordBO.setModified(employerRecordVO.getModified());
					recordBO.setMobileNo(employerRecordVO.getMobileNumber());
					recordBO.setModifiedBy(employerRecordVO.getModifiedBy());
					recordBO.setAddress(employerRecordVO.getAddress());
					recordBO.setCompanyName(employerRecordVO.getCompanyName());
					recordBO.setAddress(employerRecordVO.getConfirmEmailAddress());
					recordBO.setEmailAddress(employerRecordVO.getEmailAddress());
					recordBO.setContactNo(employerRecordVO.getContactNumber());
					recordBO.setCreated(employerRecordVO.getCreated());
					recordBO.setCreatedBy(employerRecordVO.getCreatedBy());

					/*recordBO.setSNo(sno);
					recordBO.setAccessDate(format.format(vo.getAccessDate()));
					recordBO.setStartdate(format.format(vo.getAccessDate()));
					recordBO.setEnddate(format.format(vo.getAccessDate()));
					recordBO.setAccessStatus(vo.getAccessStatus());
					recordBO.setFirstName(vo.getAdminLogin().getName());
					recordBO.setPhone((vo.getPhone()));
					recordBO.setId(vo.getId());*/
					recordsList.add(recordBO);
				}
			}
		}catch (Exception he) {
			he.printStackTrace();
			EmployerDAOImpl.LOGGER.debug(he, he.getMessage());
		} 
		return recordsList;
	}


	/* (non-Javadoc)
	 * @see com.scube.crm.dao.JobSeekerDAO#getAccessRecordCount(com.scube.crm.bo.JobseekerBO)
	 */
	@Override
	public long getAccessRecordCount(EmployerRegisterBO recordBO) {
		// TODO Auto-generated method stub
		long adminAccessCount = 0;
		Session session = getSession();


		Criteria cr = session.createCriteria(EmployerVO.class);
		if(null != recordBO.getFirstName() && !recordBO.getFirstName().isEmpty()){
			cr.createCriteria("adminLogin").add(Restrictions.ilike("name", recordBO.getFirstName(),MatchMode.ANYWHERE));
		}
		/*	if(null != recordBO.getStartDate()){
			cr.add(Restrictions.ge("accessDate", recordBO.getStartDate()));
		}
		if(null != recordBO.getEndDate()){
			cr.add(Restrictions.le("accessDate", recordBO.getEndDate()));
		}*/
		adminAccessCount =  (long) cr.setProjection(Projections.rowCount()).uniqueResult();
		return adminAccessCount;
	}

	/* (non-Javadoc)
	 * @see com.scube.crm.dao.JobSeekerDAO#adddescription(com.scube.crm.vo.ClientUpdateVO)
	 */
	@Override
	public ClientUpdateVO adddescription(ClientUpdateVO vo) {
		try {
			Session session= getSession();
			session.saveOrUpdate(vo);		
			long ids = vo.getAdminVO().getId();
			int result=0;
			if(vo.getAdminVO().getId() != 0){
				final String updatequery= "UPDATE EmployerVO S set "
						+ "S.assigned= :assigned ,"
						+ "S.status= :status "
						+"WHERE S.id = :profileUploadId";

				final Query query = getSession().createQuery(updatequery);
				query.setParameter("assigned",ids);
				query.setParameter("status", vo.getVO().getStatus());
				query.setParameter("profileUploadId",vo.getVO().getId());
				result = query.executeUpdate();

				if(0 == result){
					vo = null;
				}
			}
		} catch (final HibernateException he) {
			vo = null;
			he.printStackTrace();
			EmployerDAOImpl.LOGGER.debug(he, he.getMessage());
		} finally {
			EmployerDAOImpl.LOGGER.exit();
		}

		return vo;	
	}

	/* (non-Javadoc)
	 * @see com.scube.crm.dao.EmployerDAO#updateMigrationstatus(com.scube.crm.vo.EmployerVO)
	 */
	@Override
	public EmployerVO updateMigrationstatus(EmployerVO employerVO) {
		LOGGER.entry();
		try{
		int result = 0 ;
		if(employerVO.getId() != 0){
			final String updatequery= "UPDATE EmployerVO E set "
					+ " E.migrationStatus = :migration"  
					+" WHERE E.id = :id";

			final Query query = getSession().createQuery(updatequery);
			query.setParameter("migration",true);
			query.setParameter("id",employerVO.getId());
			result = query.executeUpdate();
		}
	} catch(final HibernateException he) {
		employerVO = null;
		he.printStackTrace();
	} finally {
		LOGGER.exit();
	}
return employerVO;
}
}
