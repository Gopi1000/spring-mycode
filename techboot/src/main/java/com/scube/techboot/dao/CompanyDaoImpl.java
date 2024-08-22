package com.scube.techboot.dao;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scube.techboot.bo.CompanyBO;
import com.scube.techboot.vo.CompanyVO;
import com.scube.techboot.vo.LoginVO;
import com.scube.techboot.vo.UserRoleVO;



@Repository
public class CompanyDaoImpl implements CompanyDao {

	private static final Logger LOGGER=Logger.getLogger(CompanyDaoImpl.class);
	@Autowired
	private SessionFactory SessionFactory;

	@Override
	public CompanyBO savecompanydetails(CompanyVO companyVO) {
		CompanyBO company=new CompanyBO();
		try{
			Session session= SessionFactory.getCurrentSession();
			int companyid= (int) session.save(companyVO);
			if(0!=companyid){
				company.setCompanyId(companyid);
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
		return company;

	}

	@Override
	public List<CompanyVO> retrivecompanydao(CompanyVO companyVO) {
		// TODO Auto-generated method stub

		List<CompanyVO> listvo=new ArrayList<CompanyVO>();
		try {
			Session session=SessionFactory.getCurrentSession();
			Criteria cr=session.createCriteria(CompanyVO.class);
			cr.add(Restrictions.eq("isDelete", companyVO.getIsDelete()));
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
	public CompanyVO getCompanyObject(CompanyVO companyvo) {
		// TODO Auto-generated method stub
		CompanyVO companyVO=new CompanyVO();
		try {
			Session session= SessionFactory.getCurrentSession();
			Criteria cr=session.createCriteria(CompanyVO.class);
			cr.add(Restrictions.eq("companyId",companyvo.getCompanyId()));
			companyVO=(CompanyVO) cr.uniqueResult();

		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return companyVO;
	}

	@Override
	public boolean updatecompany(CompanyVO companyvo) {
		// TODO Auto-generated method stub
		try {
			if(null!=companyvo) {
				Session session=SessionFactory.getCurrentSession();
				session.saveOrUpdate(companyvo);
				return true;
			}
			return false;
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
	public boolean deleteCompany(CompanyVO companyvo) {
		// TODO Auto-generated method stub
		int result;
		int result1;
		try{
			String deleteQuery = "UPDATE CompanyVO E set E.isDelete = :isDelete WHERE E.companyId = :companyId";
			final Query query = SessionFactory.getCurrentSession().createQuery(deleteQuery);
			query.setParameter("isDelete", companyvo.getIsDelete());
			query.setParameter("companyId", companyvo.getCompanyId());
			result = query.executeUpdate();
			if(0!=result){
				LoginVO loginVO=new LoginVO();
				loginVO.setCompanyVO(companyvo);
				loginVO.setIsDelete(true);
				String deleteQuery1 = "UPDATE LoginVO E set E.isDelete = :isDelete WHERE E.companyVO.companyId = :companyid";
				final Query query1 = SessionFactory.getCurrentSession().createQuery(deleteQuery1);
				query1.setParameter("isDelete", loginVO.getIsDelete());
				query1.setParameter("companyid", loginVO.getCompanyVO().getCompanyId());
				result1 = query1.executeUpdate();


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
	public LoginVO loginComapny(LoginVO loginVO) {
		// TODO Auto-generated method stub

		LoginVO login=new LoginVO();
		try{
			Session session=SessionFactory.getCurrentSession();
			session.save(loginVO);
			if(0<loginVO.getLoginId()){
/*				login.setLoginId(loginId);
*/
				System.out.println(loginVO.getLoginId());
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
		return login;
	}

	@Override
	public boolean updateLogine(LoginVO loginVO) {
		// TODO Auto-generated method stub
		try{

			String updateQuery = "UPDATE LoginVO L set L.emailAddress = :emailAddressId , L.password = :passwordId , L.modifiedTime = :modifiedTimeId WHERE L.companyVO.companyId = :companyid";
			final Query query = SessionFactory.getCurrentSession().createQuery(updateQuery);
			query.setParameter("emailAddressId", loginVO.getEmailAddress());
			query.setParameter("passwordId", loginVO.getPassword());
			query.setParameter("companyid", loginVO.getCompanyVO().getCompanyId());
			query.setParameter("modifiedTimeId", loginVO.getModifiedTime());
			int result = query.executeUpdate();
			if(0!=result){
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
	public CompanyVO getCompany(CompanyVO companyvo) {
		CompanyVO campanyVo=new CompanyVO();
		Session session= SessionFactory.openSession();
		try{
			if(0!=companyvo.getCompanyId()){
				Criteria cr=session.createCriteria(CompanyVO.class);
				cr.add(Restrictions.eq("companyId",companyvo.getCompanyId()));
				CompanyVO company=(CompanyVO) cr.uniqueResult();
				if(null!=company){
					campanyVo.setCreatedTime(company.getCreatedTime());
					campanyVo.setImageName(company.getImageName());
					campanyVo.setPassword(company.getPassword());
					campanyVo.setConformPassword(company.getConformPassword());
					return campanyVo;
				}
			}
		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}finally{
			session.flush();
			session.close();
		}
		return campanyVo;
	}

	@SuppressWarnings("unused")
	@Override
	public List<CompanyVO> listOfPageCompany(CompanyVO companyVO) {

		List<CompanyVO> listVO=new ArrayList<CompanyVO>();
		CompanyVO companyVo=null;
		try{
			Session session=SessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(CompanyVO.class);
			criteria.setFirstResult(companyVO.getRecordIndex());
			criteria.setMaxResults(companyVO.getMaxRecord());
			criteria.addOrder(Order.desc("companyId"));
			criteria.add(Restrictions.eq("isDelete", companyVO.getIsDelete()));
			listVO=criteria.list();
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
	public CompanyVO retriveOfCompanyId(CompanyVO companyVO) {
		// TODO Auto-generated method stub
		List<CompanyVO> companyList=new ArrayList<CompanyVO>();
		try{
			Session session=SessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(CompanyVO.class);
			criteria.addOrder(Order.desc("companyId"));
			companyList=criteria.list();
			if(null!=companyList && companyList.size()>0 && !companyList.isEmpty()){
				companyVO=companyList.get(0);
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
		return companyVO;
	}

	@Override
	public int searchCompany(CompanyVO companyVO) {
		// TODO Auto-generated method stub
		List<CompanyVO> companyList=new ArrayList<CompanyVO>();
		int totalCompany=0;
		try{
			Session session=SessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(CompanyVO.class);
			criteria.add(Restrictions.ilike("companyName", companyVO.getCompanyName(),MatchMode.ANYWHERE));
			criteria.add(Restrictions.eq("isDelete", companyVO.getIsDelete()));
			companyList=criteria.list();
			if(null!=companyList && companyList.size()>0 && !companyList.isEmpty()){
				totalCompany=companyList.size();
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
		return totalCompany;
	}

	@Override
	public List<CompanyVO> searchPageCompany(CompanyVO companyVO) {
		// TODO Auto-generated method stub
		List<CompanyVO> companyList=new ArrayList<CompanyVO>();
		try{
			Session session=SessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(CompanyVO.class);
			criteria.add(Restrictions.ilike("companyName", companyVO.getCompanyName(),MatchMode.ANYWHERE));
			criteria.setFirstResult(companyVO.getRecordIndex());
			criteria.setMaxResults(companyVO.getMaxRecord());
			criteria.add(Restrictions.eq("isDelete", companyVO.getIsDelete()));
			companyList=criteria.list();

		}
		catch (Exception e) {
			if(LOGGER.isInfoEnabled()){
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return companyList;
	}

	@Override
	public boolean isValidCompanyDetails(CompanyVO companyVo) {
		// TODO Auto-generated method stub
		try{
			Session session=SessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(CompanyVO.class);
			if(null!=companyVo.getemailAddress()){
				criteria.add(Restrictions.eq("emailAddress",companyVo.getemailAddress()));
			}
			if(null!=companyVo.getCompanyName()){
				criteria.add(Restrictions.eq("companyName",companyVo.getCompanyName()));
			}
			if(null!=companyVo.getCompanyWebSite()){
				criteria.add(Restrictions.eq("companyWebSite",companyVo.getCompanyWebSite()));
			}
			criteria.add(Restrictions.eq("isDelete",companyVo.getIsDelete()));
			CompanyVO company=(CompanyVO) criteria.uniqueResult();
			if(null!=company){
				return true;	
			}
		}catch (Exception e) {
			if(LOGGER.isInfoEnabled()){
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return false;
	}

	@Override
	public UserRoleVO getUserRole(UserRoleVO userRoleVO) {
		// TODO Auto-generated method stub
		UserRoleVO userRole=new UserRoleVO();
		try{
			Session session=SessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(UserRoleVO.class);
			criteria.add(Restrictions.eq("userRoleName", userRoleVO.getUserRoleName()));
			userRole=(UserRoleVO) criteria.uniqueResult();
		}catch (Exception e) {
			if(LOGGER.isInfoEnabled()){
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return userRole;
	}

	@Override
	public boolean companyNameValidations(CompanyVO companyVo) {
		// TODO Auto-generated method stub
		try{
			Session session=SessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(CompanyVO.class);
			criteria.add(Restrictions.eq("companyName",companyVo.getCompanyName()));
			criteria.add(Restrictions.eq("isDelete",companyVo.getIsDelete()));
			CompanyVO companyVO=(CompanyVO) criteria.uniqueResult();
			if(null!=companyVO){
				return true;
			}
		}catch (Exception e) {
			if(LOGGER.isInfoEnabled()){
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return false;
	}

	@Override
	public boolean emailAddressValidations(CompanyVO companyVo) {
		// TODO Auto-generated method stub
		try{
			Session session=SessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(CompanyVO.class);
			criteria.add(Restrictions.eq("emailAddress",companyVo.getemailAddress()));
			criteria.add(Restrictions.eq("isDelete",companyVo.getIsDelete()));
			CompanyVO companyVO=(CompanyVO) criteria.uniqueResult();
			if(null!=companyVO){
				return true;
			}
		}catch (Exception e) {
			if(LOGGER.isInfoEnabled()){
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return false;
	}

	@Override
	public boolean websiteValidations(CompanyVO companyVo) {
		// TODO Auto-generated method stub
		try{
			Session session=SessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(CompanyVO.class);
			criteria.add(Restrictions.eq("companyWebSite",companyVo.getCompanyWebSite()));
			criteria.add(Restrictions.eq("isDelete",companyVo.getIsDelete()));
			CompanyVO companyVO=(CompanyVO) criteria.uniqueResult();
			if(null!=companyVO){
				return true;
			}
		}catch (Exception e) {
			if(LOGGER.isInfoEnabled()){
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return false;
	}

	@Override
	public CompanyVO getCompanyUserObject(CompanyVO companyVO) {
		// TODO Auto-generated method stub
		
		CompanyVO companyvo1=new CompanyVO();
		try {
			Session session= SessionFactory.getCurrentSession();
			Criteria cr=session.createCriteria(CompanyVO.class);
			cr.add(Restrictions.eq("companyId",companyVO.getCompanyId()));
			companyvo1=(CompanyVO) cr.uniqueResult();
 
		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return companyvo1;
		
	}

	@Override
	public CompanyVO getCompanyUser(CompanyVO companyvo) {
		// TODO Auto-generated method stub
		
		CompanyVO campanyVo=new CompanyVO();
		Session session= SessionFactory.openSession();
		try{
			if(0!=companyvo.getCompanyId()){
				Criteria cr=session.createCriteria(CompanyVO.class);
				cr.add(Restrictions.eq("companyId",companyvo.getCompanyId()));
				CompanyVO company=(CompanyVO) cr.uniqueResult();
				if(null!=company){
					campanyVo.setCreatedTime(company.getCreatedTime());
					campanyVo.setImageName(company.getImageName());
					campanyVo.setPassword(company.getPassword());
					campanyVo.setConformPassword(company.getConformPassword());
					return campanyVo;
				}
			}
		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}finally{
			session.flush();
			session.close();
		}
		return campanyVo;
		
	}

	@Override
	public boolean updatecompanyUser(CompanyVO companyvo) {
		// TODO Auto-generated method stub
		
		try {
			if(null!=companyvo) {
				Session session=SessionFactory.getCurrentSession();
				session.saveOrUpdate(companyvo);
				return true;
			}
			return false;
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
	public boolean updateLoginByCompany(LoginVO loginVO) {
		// TODO Auto-generated method stub
		
		try{

			String updateQuery = "UPDATE LoginVO L set L.emailAddress = :emailAddressId , L.password = :passwordId , L.modifiedTime = :modifiedTimeId WHERE L.companyVO.companyId = :companyid";
			final Query query = SessionFactory.getCurrentSession().createQuery(updateQuery);
			query.setParameter("emailAddressId", loginVO.getEmailAddress());
			query.setParameter("passwordId", loginVO.getPassword());
			query.setParameter("companyid", loginVO.getCompanyVO().getCompanyId());
			query.setParameter("modifiedTimeId", loginVO.getModifiedTime());
			int result = query.executeUpdate();
			if(0!=result){
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
	public CompanyVO getCompanyuserObject(CompanyVO companyVO) {
		// TODO Auto-generated method stub
		
		CompanyVO companyvo=new CompanyVO();
		try {
			Session session= SessionFactory.getCurrentSession();
			Criteria cr=session.createCriteria(CompanyVO.class);
			cr.add(Restrictions.eq("companyId",companyVO.getCompanyId()));
			companyvo=(CompanyVO) cr.uniqueResult();

		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return companyvo;
	}

		

}
