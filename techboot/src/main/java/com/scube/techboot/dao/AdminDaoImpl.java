package com.scube.techboot.dao;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scube.techboot.bo.CompanyBO;
import com.scube.techboot.bo.ContactBO;
import com.scube.techboot.bo.LoginBO;
import com.scube.techboot.vo.CampaignSmsTrackingVO;
import com.scube.techboot.vo.CompanyVO;
import com.scube.techboot.vo.ContactVO;
import com.scube.techboot.vo.CustomerVO;
import com.scube.techboot.vo.LoginVO;
import com.scube.techboot.vo.NewslettersVO;
import com.scube.techboot.vo.StudentRegisterVO;
import com.scube.techboot.vo.TestimonialVO;


@Repository("adminDaoImpl")
public class AdminDaoImpl implements AdminDao{

	private static final Logger LOGGER=Logger.getLogger(AdminDaoImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	public LoginBO logincheck(LoginVO loginvo) {
		LoginBO loginBO = new LoginBO();
		try{
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(LoginVO.class);
			criteria.add(Restrictions.eq("isDelete",loginvo.getIsDelete()));
			criteria.add(Restrictions.eq("emailAddress",loginvo.getEmailAddress()));
			criteria.add(Restrictions.eq("password",loginvo.getPassword()));
			List<LoginVO> loginList=criteria.list();
			/*String hql = "from LoginVO a where a.emailAddress='" + loginvo.getEmailAddress()
			+ "'and a.password='" + loginvo.getPassword() + "'and a.isDelete='" + loginvo.getIsDelete() +"'";
			Query query = session.createQuery(hql);*/
			//List<LoginVO> loginList = query.list();
			if (null!=loginList&&loginList.size()>0) {
				for (LoginVO login : loginList) {
					if(null!=login.getCompanyVO()){
						CompanyBO companyBO=new CompanyBO();
						int companyId=login.getCompanyVO().getCompanyId();
						companyBO.setCompanyId(companyId);
						loginBO.setCompanyBO(companyBO);
					}
					loginBO.setLoginId(login.getLoginId());
					loginBO.setEmailAddress(login.getEmailAddress());
					loginBO.setPassword(login.getPassword());
					loginBO.setIsActive(login.getIsActive());
					loginBO.setUserType(login.getUserType());
				}
			}

		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}

		return loginBO;
	}

	@Override
	public Boolean saveContact(ContactVO contactVO) {
		// TODO Auto-generated method stub
		try{
			Session session = sessionFactory.getCurrentSession();
			int contactid=(int) session.save(contactVO);
			if(0!=contactid){
				return true;
			}
		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return false;
	}

	@Override
	public List<ContactVO> viewContact(ContactVO contactVo) {
		// TODO Auto-generated method stub
		List<ContactVO> contactlistVo=new ArrayList<ContactVO>();
		try{
			Session session= sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(ContactVO.class);
			criteria.add(Restrictions.eq("isDelete",contactVo.getisDelete()));
			criteria.add(Restrictions.eq("sending_status", contactVo.getSending_status()));
			contactlistVo=criteria.list();
			if(null!=contactlistVo && !contactlistVo.isEmpty() && contactlistVo.size()>0){
				return contactlistVo;
			}
		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return contactlistVo;
	}

	@Override
	public LoginVO findByEmail(String email) {
		// TODO Auto-generated method stub
		LoginVO loginVO =new LoginVO();
		loginVO.setEmailAddress(email);
		loginVO.setIsDelete(false);
		try{
			Session session=sessionFactory.getCurrentSession();
			Criteria cr=session.createCriteria(LoginVO.class);
			cr.add(Restrictions.eq("isDelete", loginVO.getIsDelete()));
			cr.add(Restrictions.eq("emailAddress", loginVO.getEmailAddress()));
			loginVO=(LoginVO) cr.uniqueResult();

		}
		catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return loginVO;
	}

	@Override
	public ContactVO getContactDetails(ContactVO contactVo) {
		// TODO Auto-generated method stub
		try{
			Session session= sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(ContactVO.class);
			criteria.add(Restrictions.eq("contactId",contactVo.getContactId()));
			criteria.add(Restrictions.eq("isDelete",contactVo.getisDelete()));
			criteria.add(Restrictions.eq("sending_status", contactVo.getSending_status()));
			ContactVO contactVO=(ContactVO) criteria.uniqueResult();
			if(null!=contactVO){
				return contactVO;
			}
		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return contactVo;
	}

	@Override
	public long retrieveContactCount(ContactVO contactVo) {
		// TODO Auto-generated method stub
		try{
			Session session= sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(ContactVO.class);
			criteria.add(Restrictions.eq("isDelete",contactVo.getisDelete()));
			criteria.add(Restrictions.eq("sending_status", contactVo.getSending_status()));
			criteria.setProjection(Projections.rowCount());
			long count=(long) criteria.uniqueResult();
			if(0!=count){
				return count;
			}
		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return 0;
	}

	@Override
	public List<CampaignSmsTrackingVO> viewSmsTracking(CampaignSmsTrackingVO campaignSmsTrackingVO) {

		List<CampaignSmsTrackingVO> campaignSmsTrackingList=new ArrayList<CampaignSmsTrackingVO>();
		try{
			Session session= sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(CampaignSmsTrackingVO.class);
			criteria.add(Restrictions.eq("curentDate",campaignSmsTrackingVO.getCurentDate()));
			campaignSmsTrackingList=criteria.list();
			if(null!=campaignSmsTrackingList && !campaignSmsTrackingList.isEmpty() && campaignSmsTrackingList.size()>0){
				return campaignSmsTrackingList;
			}
		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		
		return campaignSmsTrackingList;
	}

	@Override
	public CampaignSmsTrackingVO smsTrackingDetails(CampaignSmsTrackingVO campaignSmsTrackingVO) {
		// TODO Auto-generated method stub
		CampaignSmsTrackingVO campaignSmsTracking=new CampaignSmsTrackingVO();
		try{
			Session session= sessionFactory.getCurrentSession();
			Criteria cr=session.createCriteria(CampaignSmsTrackingVO.class);
			cr.add(Restrictions.eq("smsTrackingId",campaignSmsTrackingVO.getSmsTrackingId()));
			campaignSmsTracking=(CampaignSmsTrackingVO) cr.uniqueResult();
			
		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		
		
		return campaignSmsTracking;
	}

	@Override
	public NewslettersVO findByEmailNews(NewslettersVO newslettersVO) {
		// TODO Auto-generated method stub
		NewslettersVO newsletters=new NewslettersVO();
		try{
			Session session= sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(NewslettersVO.class);
			criteria.add(Restrictions.eq("emailAddress", newslettersVO.getEmailAddress()));
			newsletters=(NewslettersVO) criteria.uniqueResult();
			if(null!=newsletters){
				return newsletters;
			}
		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return newsletters;
	}

	@Override
	public NewslettersVO newsLettersCreations(NewslettersVO newslettersVO) {
		// TODO Auto-generated method stub
		try{
			Session session = sessionFactory.getCurrentSession();
			int id=(int) session.save(newslettersVO);
			if(0!=id){
				newslettersVO.setNewsLettersId(id);
				return newslettersVO;
			}
		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return newslettersVO;
	}

	@Override
	public int retriveUser(LoginVO loginVO) {
		// TODO Auto-generated method stub
		List<LoginVO> listVo=new ArrayList<LoginVO>();
		int count=0;
		try {
			Session session=sessionFactory.getCurrentSession();
			Criteria cr=session.createCriteria(LoginVO.class);
			listVo=cr.list();
			if(null!=listVo && !listVo.isEmpty() && listVo.size()>0){
				count=listVo.size();
				return count;
		}
		}
		catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return count;
		
		
	}

	@Override
	public List<LoginVO> listOfUsers(LoginVO loginVO) {
		// TODO Auto-generated method stub
		List<LoginVO> listvo=new ArrayList<LoginVO>();
		try {
			Session session=sessionFactory.getCurrentSession();
			Criteria cr=session.createCriteria(LoginVO.class);
			cr.setFirstResult(loginVO.getRecordIndex());
			cr.setMaxResults(loginVO.getMaxRecord());
			cr.addOrder(Order.desc("loginId"));
			listvo=cr.list();
		}
		catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return listvo;
		
	}

	@Override
	public int searchUser(LoginVO loginVO) {
		// TODO Auto-generated method stub
				List<LoginVO> userList=new ArrayList<LoginVO>();
				List<LoginVO> userList1=new ArrayList<LoginVO>();
				int totaluser=0;
				int totaluser1=0;
				try{
					Session session=sessionFactory.getCurrentSession();
					Criteria criteria=session.createCriteria(LoginVO.class);
					Criteria criteria1=session.createCriteria(LoginVO.class);
					if(null!=loginVO.getEmailAddress())
					{
						criteria.add(Restrictions.eq("emailAddress", loginVO.getEmailAddress()));
					}
					else{
						 if(null!=loginVO.getStudent().getFirstName())
						  {
							 criteria.createCriteria("student","name")
							.add(Restrictions.ilike("name.firstName", loginVO.getStudent().getFirstName(),MatchMode.ANYWHERE));	
						  
						  }
						 if(null!=loginVO.getStudent().getMobileNo())
						 {
							 criteria.createCriteria("student","mobile")
							 .add(Restrictions.eq("mobile.mobileNo", loginVO.getStudent().getMobileNo()));	
						 }
						 if(null!=loginVO.getCompanyVO().getContectNumber())
						 {
							 criteria1.createCriteria("companyVO","number")
							  .add(Restrictions.eq("number.contectNumber", loginVO.getCompanyVO().getContectNumber()));
						 }
						 if(null!=loginVO.getCompanyVO().getCompanyName())
						  {
							 criteria1.createCriteria("companyVO","company")
							 .add(Restrictions.ilike("company.companyName", loginVO.getCompanyVO().getCompanyName(),MatchMode.ANYWHERE));	  
						  }
				    }
					userList=criteria.list();
					if(null!=userList && userList.size()>0 && !userList.isEmpty()){
						totaluser=userList.size();
						return totaluser;
					}
					else{
						userList1=criteria1.list();
						if(null!=userList1 && userList1.size()>0 && !userList1.isEmpty()){
							totaluser1=userList1.size();
							return totaluser1;
						}
					}
					
				}
				catch (Exception e) {
					if(LOGGER.isInfoEnabled()){
						LOGGER.info("FROM INFO: Exception \t"+e);
					}
					if(LOGGER.isDebugEnabled()){
						LOGGER.debug("FROM DEBUG : Exception \t"+e);
					}
				}
				return 0;
	}
				

	@Override
	public List<LoginVO> searchLoginData(LoginVO loginVO) {
		// TODO Auto-generated method stub
		List<LoginVO> userList=new ArrayList<LoginVO>();
		List<LoginVO> userList1=new ArrayList<LoginVO>();
		
		try{
			Session session=sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(LoginVO.class);
			Criteria criteria1=session.createCriteria(LoginVO.class);
			if(null!=loginVO.getEmailAddress())
			{
				criteria.add(Restrictions.eq("emailAddress", loginVO.getEmailAddress()));
			}
			else{
				 if(null!=loginVO.getStudent().getFirstName())
				  {
					 criteria.createCriteria("student","name")
					.add(Restrictions.ilike("name.firstName", loginVO.getStudent().getFirstName(),MatchMode.ANYWHERE));	
				  
				  }
				 if(null!=loginVO.getStudent().getMobileNo())
				 {
					 criteria.createCriteria("student","mobile")
					 .add(Restrictions.eq("mobile.mobileNo", loginVO.getStudent().getMobileNo()));	
				 }
				 if(null!=loginVO.getCompanyVO().getContectNumber())
				 {
					 criteria1.createCriteria("companyVO","number")
					  .add(Restrictions.eq("number.contectNumber", loginVO.getCompanyVO().getContectNumber()));
				 }
				 if(null!=loginVO.getCompanyVO().getCompanyName())
				  {
					 criteria1.createCriteria("companyVO","company")
					 .add(Restrictions.ilike("company.companyName", loginVO.getCompanyVO().getCompanyName(),MatchMode.ANYWHERE));	  
				  }
		    }
			criteria.setFirstResult(loginVO.getRecordIndex());
			criteria.setMaxResults(loginVO.getMaxRecord());
			userList=criteria.list();
			if(null!=userList && userList.size()>0 && !userList.isEmpty()){
				return userList;
			}
			else{
				criteria1.setFirstResult(loginVO.getRecordIndex());
				criteria1.setMaxResults(loginVO.getMaxRecord());
				userList1=criteria1.list();
				if(null!=userList1 && userList1.size()>0 && !userList1.isEmpty()){
					return userList1;
				}
			}
		}
		catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return userList;
		
	}

	@Override
	public LoginVO editUserCount(LoginVO loginVo) {
		// TODO Auto-generated method stub
		int count=0;
		LoginVO loginVO=new LoginVO();
		try{
			Session session= sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(LoginVO.class);
			criteria.add(Restrictions.eq("loginId",loginVo.getLoginId()));
			loginVO= (LoginVO) criteria.uniqueResult();
			if(null!=loginVO){
				return loginVO;
			}
		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return loginVO;
	}

	@Override
	public boolean editUserData(LoginVO loginVO) {
		// TODO Auto-generated method stub
		try{
			String editQuery="UPDATE LoginVO C set C.isActive = :isActive,C.password = :password,"
					+ "C.modifiedBy = :modifiedBy, C.modifiedTime = :modifiedTime WHERE C.loginId = :loginId";
			final Query query = sessionFactory.getCurrentSession().createQuery(editQuery);
			query.setParameter("isActive", loginVO.getIsActive());
			query.setParameter("password", loginVO.getPassword());
			query.setParameter("modifiedBy", loginVO.getModifiedBy());
			query.setParameter("modifiedTime", loginVO.getModifiedTime());
			query.setParameter("loginId", loginVO.getLoginId());
			
			int result = query.executeUpdate();
			if(0!=result){
				return true;
			}
		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return false;
	}

	@Override
	public boolean editUserData(CompanyVO company) {
		// TODO Auto-generated method stub
		try{
			String editQuery="UPDATE CompanyVO C set C.password = :password,"
					+ "C.modifiedBy = :modifiedBy, C.modifiedTime = :modifiedTime WHERE C.companyId = :companyId";
			final Query query = sessionFactory.getCurrentSession().createQuery(editQuery);
			query.setParameter("password", company.getPassword());
			query.setParameter("modifiedBy", company.getModifiedBy());
			query.setParameter("modifiedTime", company.getModifiedTime());
			query.setParameter("companyId", company.getCompanyId());
			int result = query.executeUpdate();
			if(0!=result){
				return true;
			}
		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return false;
	}

	@Override
	public boolean editUserData(StudentRegisterVO student) {
		// TODO Auto-generated method stub
		try{
			String editQuery="UPDATE StudentRegisterVO C set C.password = :password,"
					+ "C.modifiedBy = :modifiedBy, C.modifiedTime = :modifiedTime WHERE C.studentId = :studentId";
			final Query query = sessionFactory.getCurrentSession().createQuery(editQuery);
			query.setParameter("password", student.getPassword());
			query.setParameter("modifiedBy", student.getModifiedBy());
			query.setParameter("modifiedTime", student.getModifiedTime());
			query.setParameter("studentId", student.getStudentId());
			int result = query.executeUpdate();
					return true;
			
		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return false;
	}

	@Override
	public LoginVO getAdminProfile(long adminId) {
		// TODO Auto-generated method stub
		LoginVO loginVO=new LoginVO();
		try {
			Session session=sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(LoginVO.class);
			criteria.add(Restrictions.eq("loginId",adminId));
			loginVO= (LoginVO) criteria.uniqueResult();	
		}
		catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}		
		return loginVO;
	}

	@Override
	public boolean deleteUser(LoginVO loginVo) {
		// TODO Auto-generated method stub
		try{
			String deleteQuery="UPDATE LoginVO T set T.isDelete = :isDelete,T.sending_status = :sending_status WHERE T.loginId = :loginId";
			final Query query = sessionFactory.getCurrentSession().createQuery(deleteQuery);
			query.setParameter("isDelete", loginVo.getIsDelete());
			query.setParameter("sending_status", loginVo.getSending_status());
			query.setParameter("loginId", loginVo.getLoginId());
			int result = query.executeUpdate();
			if(0!=result){
				return true;
			}
		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return false;
	}

	@Override
	public boolean deleteCompany(CompanyVO company) {
		// TODO Auto-generated method stub
		try{
			String deleteQuery="UPDATE CompanyVO T set T.isDelete = :isDelete,T.sending_status = :sending_status WHERE T.companyId = :companyId";
			final Query query = sessionFactory.getCurrentSession().createQuery(deleteQuery);
			query.setParameter("isDelete", company.getIsDelete());
			query.setParameter("sending_status", company.getSending_status());
			query.setParameter("companyId", company.getCompanyId());
			int result = query.executeUpdate();
			if(0!=result){
				return true;
			}
		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return false;
	}

	@Override
	public boolean deleteStudent(StudentRegisterVO student) {
		// TODO Auto-generated method stub
		try{
			String deleteQuery="UPDATE StudentRegisterVO T set T.isDelete = :isDelete,T.sending_status = :sending_status WHERE T.studentId = :studentId";
			final Query query = sessionFactory.getCurrentSession().createQuery(deleteQuery);
			query.setParameter("isDelete", student.getIsDelete());
			query.setParameter("sending_status", student.getSending_status());
			query.setParameter("studentId", student.getStudentId());
			int result = query.executeUpdate();
			if(0!=result){
				return true;
			}
		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return false;
	}
	
	@Override
	public int deletecontact(int id) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		ContactVO ContactVO1 = (ContactVO) session.get(ContactVO.class, id);
		session.delete(ContactVO1);
		return 0;
	}

	/*@Override
	public ContactBO editcontact(String i) {
		// TODO Auto-generated method stub
		ContactBO ContactBO1=new ContactBO();
		try{
			Session session=sessionFactory.getCurrentSession();
			Criteria cr=session.createCriteria(ContactVO.class);
			cr.add(Restrictions.eq("contactId",i));
			List results = cr.list();
			for (Iterator iterator =  results.iterator(); iterator.hasNext();){
				 ContactVO   ContactVO1 = (ContactVO) iterator.next();
				 ContactBO1.setEmailAddress(ContactVO1.getEmailAddress());
				 ContactBO1.setMessage(ContactVO1.getMessage());
				 ContactBO1.setYourName(ContactVO1.getYourName());
				 ContactBO1.setContactNumber(ContactVO1.getContactNumber());
				 ContactBO1.setContactId(ContactVO1.getContactId());
					
			}
			}	
				catch(Exception e){
					System.out.println(e);
			}
		return   ContactBO1;

}
*/
	
	@Override
	public ContactBO editcontact(int i) {
		// TODO Auto-generated method stub
		ContactBO ContactBO1=new ContactBO();
		try{
			Session session=sessionFactory.getCurrentSession();
			Criteria cr=session.createCriteria(ContactVO.class);
			cr.add(Restrictions.eq("contactId",i));
			List results = cr.list();
			for (Iterator iterator =  results.iterator(); iterator.hasNext();){
				 ContactVO   ContactVO1 = (ContactVO) iterator.next();
				 ContactBO1.setEmailAddress(ContactVO1.getEmailAddress());
				 ContactBO1.setMessage(ContactVO1.getMessage());
				 ContactBO1.setYourName(ContactVO1.getYourName());
				 ContactBO1.setContactNumber(ContactVO1.getContactNumber());
				 ContactBO1.setContactId(ContactVO1.getContactId());
					
			}
			}	
				catch(Exception e){
					System.out.println(e);
			}
		return   ContactBO1;

}


	
	

	@Override
	public int updatecontact(ContactBO bo1) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		
		ContactVO  ContactVO1 = (ContactVO) session.get(ContactVO.class, bo1.getContactId());
	//	Criteria cr=session.createCriteria(ContactVO.class);
		//cr.add(Restrictions.eq("contactId", bo1.getContactId()));
		// ContactVO  ContactVO1 =(ContactVO) cr.uniqueResult();
		 ContactVO1.setContactId(bo1.getContactId());
		 ContactVO1.setContactNumber(bo1.getContactNumber());
		 ContactVO1.setEmailAddress(bo1.getEmailAddress());
		 ContactVO1.setMessage(bo1.getMessage());
		 ContactVO1.setYourName(bo1.getYourName());
		 session.update(ContactVO1);
			
		return 0;
	}
}
