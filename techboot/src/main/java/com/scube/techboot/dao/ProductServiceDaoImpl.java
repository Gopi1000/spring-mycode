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

import com.scube.techboot.bo.ProductServiceBO;
import com.scube.techboot.vo.CompanyVO;
import com.scube.techboot.vo.ProductServiceVO;

@Repository
public class ProductServiceDaoImpl implements ProductServiceDao{

	private static final Logger LOGGER=Logger.getLogger(ProductServiceDaoImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public ProductServiceBO createServices(ProductServiceVO productServiceVO) {
		// TODO Auto-generated method stub
		ProductServiceBO companyServiceBo=new ProductServiceBO();
		ProductServiceBO companyService=new ProductServiceBO();
		Session session= sessionFactory.getCurrentSession();
		try{
			if(null!=productServiceVO){
				long serviceid=(long) session.save(productServiceVO);
				if(0!=serviceid){
					companyServiceBo.setServiceName(productServiceVO.getServiceName());
					companyServiceBo.setServiceId(serviceid);
					return companyServiceBo;
				}
				return companyService;
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
		return companyService;
	}

	@Override
	public List<ProductServiceVO> listViewService(ProductServiceVO productServiceVO) {
		// TODO Auto-generated method stub

		List<ProductServiceVO> listVO=new ArrayList<ProductServiceVO>();
		try{
			//SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
			Session session= sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(ProductServiceVO.class);
			if(null!=productServiceVO.getCompanyVO()){
				criteria.add(Restrictions.eq("isDelete", productServiceVO.getIsDelete()));
				criteria.add(Restrictions.eq("companyVO.companyId", productServiceVO.getCompanyVO().getCompanyId()));
			}
			else{
				criteria.add(Restrictions.eq("isDelete", productServiceVO.getIsDelete()));
			}

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
	public ProductServiceVO getServiceObject(ProductServiceVO productServiceVO) {
		// TODO Auto-generated method stub
		ProductServiceVO productService=new ProductServiceVO();
		ProductServiceVO productServices=new ProductServiceVO();
		CompanyVO ref=new CompanyVO();
		try{
			if(null!=productServiceVO && null!=productServiceVO.getCompanyVO() && 0<productServiceVO.getCompanyVO().getCompanyId()){
				Session session= sessionFactory.getCurrentSession();
				Criteria criteria=session.createCriteria(CompanyVO.class);
				criteria.add(Restrictions.eq("companyId", productServiceVO.getCompanyVO().getCompanyId()));
				ref=(CompanyVO) criteria.uniqueResult();

			}else {
				Session session= sessionFactory.getCurrentSession();
				Criteria criteria=session.createCriteria(ProductServiceVO.class);
				criteria.add(Restrictions.eq("serviceId", productServiceVO.getServiceId()));
				productService = (ProductServiceVO) criteria.uniqueResult();
			}
			if(null!=ref) {
			productServices.setCompanyVO(ref);
		}
			if(null!=productService) {
				return productService;
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
		return productServices;
	}

	@Override
	public Boolean serviceUpdateDao(ProductServiceVO productServiceVo) {
		// TODO Auto-generated method stub

		try{
			Session session= sessionFactory.getCurrentSession();
			if(null!=productServiceVo){
				session.update(productServiceVo);
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
	public Boolean deleteService(ProductServiceVO productServiceVo) {

		int result;
		try{
			String deleteQuery = "UPDATE ProductServiceVO E set E.isDelete = :isDelete WHERE E.serviceId = :serviceId";
			final Query query = sessionFactory.getCurrentSession().createQuery(deleteQuery);
			query.setParameter("isDelete", productServiceVo.getIsDelete());
			query.setParameter("serviceId", productServiceVo.getServiceId());
			result = query.executeUpdate();
			if(0!=result){

				return true;
			}
		}catch (Exception e) {
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
	public ProductServiceVO retrieveService(ProductServiceVO productServiceVo) {

		ProductServiceVO service=new ProductServiceVO();
		Session session= sessionFactory.openSession();
		try{
			if(0!=productServiceVo.getServiceId()){
				Criteria criteria=session.createCriteria(ProductServiceVO.class);
				criteria.add(Restrictions.eq("serviceId", productServiceVo.getServiceId()));
				ProductServiceVO productService=(ProductServiceVO) criteria.uniqueResult();
				if(null!=productService){
					service.setCreatedTime(productService.getCreatedTime());
					service.setCreatedBy(productService.getCreatedBy());
					service.setIsDelete(productService.getIsDelete());
					service.setSending_status(productService.getSending_status());
					return service;
				}
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
		return service;
	}

	@Override

	public List<ProductServiceVO> listofpageservice(ProductServiceVO productServiceVO) {
		// TODO Auto-generated method stub

		List<ProductServiceVO> ServiceList=new ArrayList<ProductServiceVO>();
		try {
			Session session= sessionFactory.getCurrentSession();
			Criteria cr=session.createCriteria(ProductServiceVO.class);
			//company
			if(null!=productServiceVO.getCompanyVO()){
				cr.add(Restrictions.eq("isDelete", productServiceVO.getIsDelete()));
				cr.add(Restrictions.eq("companyVO.companyId", productServiceVO.getCompanyVO().getCompanyId()));
				cr.setFirstResult(productServiceVO.getRecordIndex());
				cr.setMaxResults(productServiceVO.getMaxRecord());
				cr.addOrder(Order.desc("serviceId"));
			}
			 if(null!=productServiceVO.getServiceName()){
				cr.add(Restrictions.eq("isDelete", productServiceVO.getIsDelete()));
				cr.add(Restrictions.ilike("serviceName", productServiceVO.getServiceName(),MatchMode.ANYWHERE));
				cr.setFirstResult(productServiceVO.getRecordIndex());
				cr.setMaxResults(productServiceVO.getMaxRecord());
				cr.addOrder(Order.desc("serviceId"));
			}
			
			//admin
			else {
				cr.add(Restrictions.eq("isDelete", productServiceVO.getIsDelete()));
				cr.setFirstResult(productServiceVO.getRecordIndex());
				cr.setMaxResults(productServiceVO.getMaxRecord());
				cr.addOrder(Order.desc("serviceId"));
			}
			
			ServiceList=cr.list();

		}	
		catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}

		return ServiceList;
	}

	@Override
	public int searchPageService(ProductServiceVO productServiceVO) {
		// TODO Auto-generated method stub
		List<ProductServiceVO> ServiceList=new ArrayList<ProductServiceVO>();
		int serviceId=0;
		try{
			Session session= sessionFactory.getCurrentSession();
			Criteria cr=session.createCriteria(ProductServiceVO.class);
			if(null!=productServiceVO.getCompanyVO() && 0!=productServiceVO.getCompanyVO().getCompanyId()){
				cr.add(Restrictions.eq("companyVO.companyId",productServiceVO.getCompanyVO().getCompanyId()));
				}
			cr.add(Restrictions.eq("isDelete", productServiceVO.getIsDelete()));
			cr.add(Restrictions.ilike("serviceName", productServiceVO.getServiceName(),MatchMode.ANYWHERE));
			ServiceList=cr.list();
			if(null!=ServiceList && ServiceList.size()>0 && !ServiceList.isEmpty()){
				serviceId=ServiceList.size();
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
		return serviceId;
	}

	@Override
	public boolean isValidServiceName(ProductServiceVO productServiceVO) {
		// TODO Auto-generated method stub
		try{
			Session session= sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(ProductServiceVO.class);
			criteria.add(Restrictions.eq("serviceName", productServiceVO.getServiceName()));
			criteria.add(Restrictions.eq("companyVO.companyId", productServiceVO.getCompanyVO().getCompanyId()));
			criteria.add(Restrictions.eq("isDelete", productServiceVO.getIsDelete()));
			List<ProductServiceVO> ServiceList=criteria.list();
			if(null!=ServiceList && !ServiceList.isEmpty() && ServiceList.size()>0){
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
	public ProductServiceVO getserviceObject(ProductServiceVO productServiceVO) {
		// TODO Auto-generated method stub
		
		ProductServiceVO productService=new ProductServiceVO();
				ProductServiceVO productServices=new ProductServiceVO();
				CompanyVO ref=new CompanyVO();
				try{
					if(null!=productServiceVO && null!=productServiceVO.getCompanyVO() && 0<productServiceVO.getCompanyVO().getCompanyId()){
						Session session= sessionFactory.getCurrentSession();
						Criteria criteria=session.createCriteria(CompanyVO.class);
						criteria.add(Restrictions.eq("companyId", productServiceVO.getCompanyVO().getCompanyId()));
						ref=(CompanyVO) criteria.uniqueResult();

					}
					else {
						Session session= sessionFactory.getCurrentSession();
						Criteria criteria=session.createCriteria(ProductServiceVO.class);
						criteria.add(Restrictions.eq("serviceId", productServiceVO.getServiceId()));
					 productService = (ProductServiceVO) criteria.uniqueResult();
					}
					if(null!=ref) {
					productServices.setCompanyVO(ref);
				}
					
					if(null!=productService) {
						return productService;
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
				return productServices;
			}


		
	}



