package com.scube.techboot.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scube.techboot.bo.CustomerBO;
import com.scube.techboot.vo.CompanyVO;
import com.scube.techboot.vo.CustomerVO;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	private static final Logger LOGGER=Logger.getLogger(CustomerDaoImpl.class);
	@Autowired
	private SessionFactory SessionFactory;


	@Override
	public CustomerVO saveCustomer(CustomerVO customerVO) {
		// TODO Auto-generated method stub
		CustomerVO customer=new CustomerVO();
		try{
			Session session= SessionFactory.getCurrentSession();
			int customerid= (int) session.save(customerVO);
			if(0!=customerid){
				customer.setCustomerId(customerid);
				return customer;
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
		return customer;

	}

	@Override
	public List<CustomerVO> retriveCustomerDao(CustomerVO customerVO) {
		// TODO Auto-generated method stub
		List<CustomerVO> listVO=new ArrayList<CustomerVO>();
		try{
			Session session=SessionFactory.getCurrentSession();
			Criteria cr=session.createCriteria(CustomerVO.class);
			//company
			if(null!=customerVO.getCompanyVO()){
				cr.add(Restrictions.eq("companyVO.companyId", customerVO.getCompanyVO().getCompanyId()));
				cr.add(Restrictions.eq("isDelete", customerVO.getIsDelete()));
			}//admin
			else{
				cr.add(Restrictions.eq("isDelete", customerVO.getIsDelete()));
			}

			listVO= cr.list();

			if(null!=listVO && !listVO.isEmpty()){
				return listVO;
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
		return listVO;
	}

	@Override
	public CustomerVO editcustomerdetails(CustomerVO customerVO) {
		// TODO Auto-generated method stub
		CustomerVO customer=new CustomerVO();
		try{

			Session session= SessionFactory.getCurrentSession();
			Criteria cr=session.createCriteria(CustomerVO.class);
			cr.add(Restrictions.eq("customerId", customerVO.getCustomerId()));
			customer=(CustomerVO) cr.uniqueResult();
			if(null!=customer){
				return customer;
				//BeanUtils.copyProperties(customer, customerBo);
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
		return customer;
	}

	@Override
	public Boolean updateCustomer(CustomerVO customerVO) {
		// TODO Auto-generated method stub
		try{
			if(null!=customerVO){
				Session session=SessionFactory.getCurrentSession();
				session.update(customerVO);
				return true;
			}
			else{
				return false;
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
		return false;
	}
	@Override
	public Boolean deleteCustomer(CustomerVO customerVO) {
		// TODO Auto-generated method stub

		CustomerVO customer=new CustomerVO();
		customer.setCustomerId(customerVO.getCustomerId());
		customer.setIsDelete(true);
		int result = 0;

		String deleteQuery = "UPDATE CustomerVO E set E.isDelete = :isDelete WHERE E.customerId = :customerId";
		try{
			final Query query = SessionFactory.getCurrentSession().createQuery(deleteQuery);
			query.setParameter("isDelete", customer.getIsDelete());
			query.setParameter("customerId", customer.getCustomerId());

			result = query.executeUpdate();
			if(0!=result){

				return true;
			}
		} catch (Exception e) {
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
	public CustomerBO viewCustomerDetails(CustomerVO customerVO) {
		// TODO Auto-generated method stub
		CustomerVO Customer=new CustomerVO();
		CustomerBO customerBo=new CustomerBO();
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");

		try{
			if(customerVO.getCustomerId()!=0){
				Session session=SessionFactory.getCurrentSession();
				Criteria cr=session.createCriteria(CustomerVO.class);
				cr.add(Restrictions.eq("customerId", customerVO.getCustomerId()));
				Customer=(CustomerVO) cr.uniqueResult();
				if(null!=Customer){
					BeanUtils.copyProperties(Customer, customerBo);
					String Date=sim.format(Customer.getDateOfBirth());
					customerBo.setDob(Date);

				}

				else{
					return customerBo;
				}
			}
			return customerBo;
		}
		catch (Exception e) {
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return customerBo;


	}

	@Override
	public CustomerVO checkCustomer(CustomerVO customerVO) {
		CustomerVO customerVo=new CustomerVO();
		Session session=SessionFactory.openSession();
		try{
			Criteria cr=session.createCriteria(CustomerVO.class);
			cr.add(Restrictions.eq("customerId", customerVO.getCustomerId()));
			CustomerVO customer=(CustomerVO) cr.uniqueResult();
			if(null!=customer){
				CompanyVO companyVO=new CompanyVO();
				customerVo.setCreatedTime(customer.getCreatedTime());
				customerVo.setCreatedBy(customer.getCreatedBy());
				customerVo.setIsDelete(customer.getIsDelete());
				customerVo.setSending_status(customer.getSending_status());
				int id=customer.getCompanyVO().getCompanyId();
				companyVO.setCompanyId(id);
				customerVo.setCompanyVO(companyVO);
				return customerVo;
			}
		}catch (Exception e) {
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		finally{
			session.flush();
			session.close();
		}
		return customerVo;
	}

	@Override
	public List<CustomerVO> listOfPageCustomer(CustomerVO customerVO) {
		// TODO Auto-generated method stub
		List<CustomerVO> customerListVO=new ArrayList<CustomerVO>();
		try{
			Session session=SessionFactory.getCurrentSession();
			Criteria cr=session.createCriteria(CustomerVO.class);
			//company
			if(null!=customerVO.getCompanyVO()){
				cr.add(Restrictions.eq("isDelete", customerVO.getIsDelete()));
				cr.add(Restrictions.eq("companyVO.companyId", customerVO.getCompanyVO().getCompanyId()));
				cr.setFirstResult(customerVO.getRecordIndex());
				cr.setMaxResults(customerVO.getMaxRecord());
				cr.addOrder(Order.desc("customerId"));
			}
			//admin
			else{
				cr.add(Restrictions.eq("isDelete", customerVO.getIsDelete()));
				cr.setFirstResult(customerVO.getRecordIndex());
				cr.setMaxResults(customerVO.getMaxRecord());
				cr.addOrder(Order.desc("customerId"));
			}
			customerListVO=cr.list();

		}
		catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return customerListVO;
	}

	@Override
	public List<CustomerVO> searchCustomer(CustomerVO customerVO) {
		// TODO Auto-generated method stub
		List<CustomerVO> listVo=new ArrayList<CustomerVO>();
		try{
			Session session=SessionFactory.getCurrentSession();
			Criteria cr=session.createCriteria(CustomerVO.class);
			if(null!=customerVO.getFirstName() && !customerVO.getFirstName().isEmpty()){
				cr.add(Restrictions.ilike("firstName", customerVO.getFirstName(),MatchMode.ANYWHERE));
				cr.setFirstResult(customerVO.getRecordIndex());
				cr.setMaxResults(customerVO.getMaxRecord());
				cr.add(Restrictions.eq("isDelete", customerVO.getIsDelete()));
			}
			if(null!=customerVO.getEmailId() && !customerVO.getEmailId().isEmpty()){
				cr.add(Restrictions.eq("emailId", customerVO.getEmailId()));
				cr.add(Restrictions.eq("isDelete", customerVO.getIsDelete()));
			}
			if(null!=customerVO.getMobileNumber()){
				cr.add(Restrictions.eq("mobileNumber", customerVO.getMobileNumber()));
				cr.add(Restrictions.eq("isDelete", customerVO.getIsDelete()));
			}

			listVo=cr.list();

		}
		catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return listVo;
	}

	@Override
	public List<CustomerVO> totalSearchCustomer(CustomerVO customerVO) {
		// TODO Auto-generated method stub
		List<CustomerVO> listVo=new ArrayList<CustomerVO>();
		try{
			Session session=SessionFactory.getCurrentSession();
			Criteria cr=session.createCriteria(CustomerVO.class);
			if(null!=customerVO.getFirstName() && !customerVO.getFirstName().isEmpty()){
				cr.add(Restrictions.ilike("firstName", customerVO.getFirstName(),MatchMode.ANYWHERE));
				cr.add(Restrictions.eq("isDelete", customerVO.getIsDelete()));
			}
			else if(null!=customerVO.getEmailId() && !customerVO.getEmailId().isEmpty()){
				cr.add(Restrictions.eq("emailId", customerVO.getEmailId()));
				cr.add(Restrictions.eq("isDelete", customerVO.getIsDelete()));
			}
			else{
				cr.add(Restrictions.eq("mobileNumber", customerVO.getMobileNumber()));
				cr.add(Restrictions.eq("isDelete", customerVO.getIsDelete()));
			}

			listVo=cr.list();
		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return listVo;
	}

	@Override
	public boolean emailValidations(CustomerVO customerVO) {
		// TODO Auto-generated method stub
		try{
			Session session=SessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(CustomerVO.class);
			if(null!=customerVO.getCompanyVO() && 0!=customerVO.getCompanyVO().getCompanyId()){
			criteria.add(Restrictions.eq("companyVO.companyId",customerVO.getCompanyVO().getCompanyId()));
			}
			criteria.add(Restrictions.eq("emailId",customerVO.getEmailId()));
			criteria.add(Restrictions.eq("isDelete", customerVO.getIsDelete()));
			CustomerVO customerVo=(CustomerVO) criteria.uniqueResult();
			if(null!=customerVo){
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
	public boolean mobileValidations(CustomerVO customerVO) {
		// TODO Auto-generated method stub
		try{
			Session session=SessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(CustomerVO.class);
			
			 if(null!=customerVO.getCompanyVO() &&
			 0!=customerVO.getCompanyVO().getCompanyId()){
			 criteria.add(Restrictions.eq("companyVO.companyId",customerVO.getCompanyVO().
			 getCompanyId())); }
			 
			criteria.add(Restrictions.eq("mobileNumber",customerVO.getMobileNumber()));
			criteria.add(Restrictions.eq("isDelete", customerVO.getIsDelete()));
			CustomerVO customerVo=(CustomerVO) criteria.uniqueResult();
			if(null!=customerVo){
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
	public boolean whatsAppValidations(CustomerVO customerVO) {
		// TODO Auto-generated method stub
		try{
			Session session=SessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(CustomerVO.class);
			
			 if(null!=customerVO.getCompanyVO() &&
		0!=customerVO.getCompanyVO().getCompanyId()){
			 criteria.add(Restrictions.eq("companyVO.companyId",customerVO.getCompanyVO().
			  getCompanyId())); }
			 
			criteria.add(Restrictions.eq("whatsappNumber",customerVO.getWhatsappNumber()));
			criteria.add(Restrictions.eq("isDelete", customerVO.getIsDelete()));
			CustomerVO customerVo=(CustomerVO) criteria.uniqueResult();
			if(null!=customerVo){
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
	public boolean checking(CustomerVO customerVO) {
		// TODO Auto-generated method stub
		try {
			Session session=SessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(CustomerVO.class);
			
			criteria.add(Restrictions.eq("emailId",customerVO.getEmailId()));
			criteria.add(Restrictions.eq("isDelete", customerVO.getIsDelete()));
			/*
			 * criteria.add(Restrictions.eq("mobileNumber",customerVO.getMobileNumber()));
			 * criteria.add(Restrictions.eq("isDelete", customerVO.getIsDelete()));
			 * 
			 * criteria.add(Restrictions.eq("whatsappNumber",customerVO.getWhatsappNumber())
			 * ); criteria.add(Restrictions.eq("isDelete", customerVO.getIsDelete()));
			 */
			CustomerVO customerVo=(CustomerVO) criteria.uniqueResult();
			if(null!=customerVo){
				return true;
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
		return false;
	}

	@Override
	public boolean mobileNumberChecking(CustomerVO customerVO) {
		// TODO Auto-generated method stub
		
		try {
			Session session=SessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(CustomerVO.class);
			
			 criteria.add(Restrictions.eq("mobileNumber",customerVO.getMobileNumber()));
			  criteria.add(Restrictions.eq("isDelete", customerVO.getIsDelete()));
			  
			  CustomerVO customerVo=(CustomerVO) criteria.uniqueResult();
			  if(null!=customerVo){ 
				  return true; 
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
		return false;
	}

	@Override
	public boolean whatsappNumberChecking(CustomerVO customerVO) {
		// TODO Auto-generated method stub
		
		try {
			Session session=SessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(CustomerVO.class);
			
			criteria.add(Restrictions.eq("whatsappNumber",customerVO.getWhatsappNumber()) );
			criteria.add(Restrictions.eq("isDelete", customerVO.getIsDelete()));
			
			 CustomerVO customerVo=(CustomerVO) criteria.uniqueResult();
			  if(null!=customerVo){ 
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
	public boolean emailIdCheck(CustomerVO customerVO) {
		// TODO Auto-generated method stub
		try {
			Session session=SessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(CustomerVO.class);
			
			criteria.add(Restrictions.eq("emailId",customerVO.getEmailId()));
			criteria.add(Restrictions.eq("isDelete",customerVO.getIsDelete() ));
			
			CustomerVO customerVo=(CustomerVO) criteria.uniqueResult();
			if(null!=customerVo) {
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
	public boolean mobileNumberCheck(CustomerVO customerVO) {
		// TODO Auto-generated method stub
		try {
			Session session=SessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(CustomerVO.class);
			
			 criteria.add(Restrictions.eq("mobileNumber",customerVO.getMobileNumber()));
			  criteria.add(Restrictions.eq("isDelete", customerVO.getIsDelete()));
			  
			  CustomerVO customerVo=(CustomerVO) criteria.uniqueResult();
			  if(null!=customerVo){ 
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
	public boolean whatsappNumberCheck(CustomerVO customerVO) {
		// TODO Auto-generated method stub
		try {
			Session session=SessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(CustomerVO.class);
			
			criteria.add(Restrictions.eq("whatsappNumber",customerVO.getWhatsappNumber()) );
			criteria.add(Restrictions.eq("isDelete", customerVO.getIsDelete()));
			
			 CustomerVO customerVo=(CustomerVO) criteria.uniqueResult();
			  if(null!=customerVo){ 
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

	
	
	/*
	 * @Override public boolean mobileChecking(CustomerVO customerVO) { // TODO
	 * Auto-generated method stub try { Session
	 * session=SessionFactory.getCurrentSession(); Criteria
	 * criteria=session.createCriteria(CustomerVO.class);
	 * 
	 * 
	 * criteria.add(Restrictions.eq("mobileNumber",customerVO.getMobileNumber()));
	 * criteria.add(Restrictions.eq("isDelete", customerVO.getIsDelete()));
	 * 
	 * CustomerVO customerVo=(CustomerVO) criteria.uniqueResult();
	 * if(null!=customerVo){ return true; }
	 * 
	 * }catch(Exception e){ if(LOGGER.isInfoEnabled()) {
	 * LOGGER.info("FROM INFO: Exception \t"+e); } if(LOGGER.isDebugEnabled()) {
	 * LOGGER.debug("FROM DEBUG : Exception \t"+e); } }
	 * 
	 * return false; }
	 */

	/*
	 * @Override public boolean whatsappChecking(CustomerVO customerVO) { // TODO
	 * Auto-generated method stub try { Session
	 * session=SessionFactory.getCurrentSession(); Criteria
	 * criteria=session.createCriteria(CustomerVO.class);
	 * 
	 * criteria.add(Restrictions.eq("whatsappNumber",
	 * customerVO.getWhatsappNumber())); criteria.add(Restrictions.eq("isDelete",
	 * customerVO.getIsDelete()));
	 * 
	 * CustomerVO customerVo=(CustomerVO) criteria.uniqueResult();
	 * if(null!=customerVo){ return true; }
	 * 
	 * }catch(Exception e){ if(LOGGER.isInfoEnabled()) {
	 * LOGGER.info("FROM INFO: Exception \t"+e); } if(LOGGER.isDebugEnabled()) {
	 * LOGGER.debug("FROM DEBUG : Exception \t"+e); } }
	 * 
	 * return false; }
	 */
	

/*	@Override
	public List<CustomerVO> getListSpecialization(CustomerVO customerVO) {
		// TODO Auto-generated method stub
		List<CustomerVO> listVO=new ArrayList<CustomerVO>();
		try{
			Session session=SessionFactory.getCurrentSession();
			Criteria cr=session.createCriteria(CustomerVO.class);
			cr.add(Restrictions.eq("specialization", customerVO.getSpecialization()));
			cr.add(Restrictions.eq("isDelete", customerVO.getIsDelete()));
			listVO=cr.list();
		}
		catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return listVO;
	}*/

}
