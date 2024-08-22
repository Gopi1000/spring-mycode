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
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scube.techboot.vo.CampaignVO;



@Repository
public class CampaignDaoImpl  implements CampaignDao{

	private static final Logger LOGGER=Logger.getLogger(CampaignDaoImpl.class);
	@Autowired
	private SessionFactory sessionFactory;


	@Override
	public CampaignVO saveCompaignDao(CampaignVO campaignVO) {
		// TODO Auto-generated method stub

		CampaignVO campaign=new CampaignVO();
		try{
			Session session=sessionFactory.getCurrentSession();
			int compaigId=(int) session.save(campaignVO);
			
			
			if(0!=compaigId){
				campaignVO.setCampaignId(compaigId);
				/*Criteria criteria=session.createCriteria(CampaignVO.class);
				criteria.add(Restrictions.eq("campaignId", campaignVO.getCampaignId()));
				campaign=(CampaignVO) criteria.uniqueResult();*/
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
		return campaignVO;
	}


	@Override
	public long getListCampaignDao(CampaignVO campaignVO) {
		// TODO Auto-generated method stub
		try{

			Session session=sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(CampaignVO.class);
			criteria.add(Restrictions.eq("isDelete", campaignVO.getIsDelete()));
			criteria.add(Restrictions.eq("sending_status", campaignVO.getSending_status()));
			criteria.setProjection(Projections.rowCount());
			long count=(long) criteria.uniqueResult();
			if(0!=count){
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
		return 0;
	}


	@Override
	public CampaignVO getCampaignObject(CampaignVO campaignVo) {

		CampaignVO campaignVO=new CampaignVO();
		try{
			if(0!=campaignVo.getCampaignId()){
				Session session=sessionFactory.getCurrentSession();
				Criteria criteria=session.createCriteria(CampaignVO.class);
				criteria.add(Restrictions.eq("campaignId",campaignVo.getCampaignId()));
				campaignVO=(CampaignVO) criteria.uniqueResult();
				return campaignVO;
			}
		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return campaignVO;

	}


	@Override
	public boolean updateCampaign(CampaignVO campaignVo) {

		try{
			Session session=sessionFactory.getCurrentSession();
			if(null!=campaignVo){
				session.update(campaignVo);
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
	public boolean deleteCampaign(CampaignVO campaignVo) {
		try{
			String deleteQuery = "UPDATE CampaignVO C set C.isDelete = :isDelete,C.sending_status = :sending_status WHERE C.campaignId = :campaignId";
			final Query query = sessionFactory.getCurrentSession().createQuery(deleteQuery);
			query.setParameter("isDelete", campaignVo.getIsDelete());
			query.setParameter("sending_status", campaignVo.getSending_status());
			query.setParameter("campaignId", campaignVo.getCampaignId());
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
	public boolean checkCampaign(CampaignVO campaignVo) {
		try{
			if(0!=campaignVo.getProductService().getServiceId() && null!=campaignVo.getIsDelete() 
					&& null!=campaignVo.getSending_status()){
				Session session=sessionFactory.getCurrentSession();
				Criteria criteria=session.createCriteria(CampaignVO.class);
				criteria.add(Restrictions.eq("productService.serviceId",campaignVo.getProductService().getServiceId()));
				criteria.add(Restrictions.eq("isDelete", campaignVo.getIsDelete()));
				CampaignVO campaign=(CampaignVO) criteria.uniqueResult();
				if(null!=campaign){
					return true;
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
		return false;
	}


	@Override
	public long getListOfCompanyCampaign(CampaignVO campaignVO) {
		// TODO Auto-generated method stub
		try{
			Session session=sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(CampaignVO.class);
			criteria.add(Restrictions.eq("isDelete", campaignVO.getIsDelete()));
			criteria.add(Restrictions.eq("sending_status",campaignVO.getSending_status()));
			criteria.add(Restrictions.eq("companyVO.companyId", campaignVO.getCompanyVO().getCompanyId()));
			criteria.setProjection(Projections.rowCount());
			long count= (long) criteria.uniqueResult();
			if(0!=count){
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

		return 0;
	}
	@Override
	public List<CampaignVO> listOfCampaign(CampaignVO campaignVo) {
		// TODO Auto-generated method stub

		List<CampaignVO> listVo=new ArrayList<CampaignVO>();
		try{
			Session session=sessionFactory.getCurrentSession();
			Criteria cr=session.createCriteria(CampaignVO.class);
			//company
			if(null!=campaignVo.getCompanyVO()){
				cr.add(Restrictions.eq("companyVO.companyId",campaignVo.getCompanyVO().getCompanyId()));
				cr.add(Restrictions.eq("isDelete",campaignVo.getIsDelete()));
				cr.add(Restrictions.eq("sending_status",campaignVo.getSending_status()));
				cr.setFirstResult(campaignVo.getRecordIndex());
				cr.setMaxResults(campaignVo.getMaxRecord());
				cr.addOrder(Order.desc("campaignId"));
			}//admin
			else{
				cr.add(Restrictions.eq("isDelete",campaignVo.getIsDelete()));
				cr.add(Restrictions.eq("sending_status",campaignVo.getSending_status()));
				cr.setFirstResult(campaignVo.getRecordIndex());
				cr.setMaxResults(campaignVo.getMaxRecord());
				cr.addOrder(Order.desc("campaignId"));
			}
			listVo=cr.list();
			if(null!=listVo && !listVo.isEmpty() && listVo.size()>0){
				return listVo;

			}
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
	public List<CampaignVO> searchCampaign(CampaignVO campaignVo) {
		// TODO Auto-generated method stub
		List<CampaignVO> listVo=new ArrayList<CampaignVO>();
		try{
			if(null!=campaignVo.getCampaignName()){
				Session session=sessionFactory.getCurrentSession();
				Criteria cr=session.createCriteria(CampaignVO.class);
				//company
				if(null!=campaignVo.getCompanyVO()){
					if(null!=campaignVo.getCampaignName()&&!campaignVo.getCampaignName().isEmpty()){
						cr.add(Restrictions.ilike("campaignName", campaignVo.getCampaignName(),MatchMode.ANYWHERE));
					}
					if(null!=campaignVo.getCategory()&&!campaignVo.getCategory().isEmpty()){
						cr.add(Restrictions.ilike("category", campaignVo.getCategory(),MatchMode.ANYWHERE));	
					}
					cr.add(Restrictions.eq("companyVO.companyId",campaignVo.getCompanyVO().getCompanyId()));
					cr.add(Restrictions.eq("isDelete",campaignVo.getIsDelete()));
					cr.add(Restrictions.eq("sending_status",campaignVo.getSending_status()));
					cr.setFirstResult(campaignVo.getRecordIndex());
					cr.setMaxResults(campaignVo.getMaxRecord());
				}
				//admin
				else{
					if(null!=campaignVo.getCampaignName()&&!campaignVo.getCampaignName().isEmpty()){
						cr.add(Restrictions.ilike("campaignName", campaignVo.getCampaignName(),MatchMode.ANYWHERE));
					}
					if(null!=campaignVo.getCategory()&&!campaignVo.getCategory().isEmpty()){
						cr.add(Restrictions.ilike("category", campaignVo.getCategory(),MatchMode.ANYWHERE));	
					}
					cr.add(Restrictions.eq("isDelete",campaignVo.getIsDelete()));
					cr.add(Restrictions.eq("sending_status",campaignVo.getSending_status()));
					cr.setFirstResult(campaignVo.getRecordIndex());
					cr.setMaxResults(campaignVo.getMaxRecord());
					//cr.addOrder(Order.desc("campaignId"));
				}
				listVo=cr.list();
			}
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
	public long listOfCampaignCount(CampaignVO campaignVO) {
		// TODO Auto-generated method stub
		long count=0;
		try{
			if(null!=campaignVO){
				Session session=sessionFactory.getCurrentSession();
				Criteria criteria=session.createCriteria(CampaignVO.class);
				if(null!=campaignVO.getCampaignName()&&!campaignVO.getCampaignName().isEmpty()){
					criteria.add(Restrictions.ilike("campaignName", campaignVO.getCampaignName(),MatchMode.ANYWHERE));
				}
				if(null!=campaignVO.getCategory()&&!campaignVO.getCategory().isEmpty()){
					criteria.add(Restrictions.ilike("category", campaignVO.getCategory(),MatchMode.ANYWHERE));
				}
				/*if(null!=campaignVO.getProductService()&&!campaignVO.getProductService().getServiceName().isEmpty()){
				criteria.add(Restrictions.ilike("productService.serviceName", campaignVO.getProductService().getServiceName(),MatchMode.ANYWHERE));
			//	criteria.createCriteria("productService").createCriteria("serviceName").add(Restrictions.ilike("productService.serviceName", campaignVO.getProductService().getServiceName()));
			}*/
				criteria.add(Restrictions.eq("isDelete", campaignVO.getIsDelete()));
				criteria.add(Restrictions.eq("sending_status", campaignVO.getSending_status()));
				criteria.setProjection(Projections.rowCount());
				count=(long) criteria.uniqueResult();
			}
			return count;
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
	public long listOfCompanyCampaign(CampaignVO campaignVO) {
		// TODO Auto-generated method stub
		long count=0;
		try{
			if(null!=campaignVO){
				Session session=sessionFactory.getCurrentSession();
				Criteria criteria=session.createCriteria(CampaignVO.class);
				if(null!=campaignVO.getCampaignName()&&!campaignVO.getCampaignName().isEmpty()){
					criteria.add(Restrictions.ilike("campaignName", campaignVO.getCampaignName(),MatchMode.ANYWHERE));
				}
				if(null!=campaignVO.getCategory()&&!campaignVO.getCategory().isEmpty()){
					criteria.add(Restrictions.ilike("category", campaignVO.getCategory(),MatchMode.ANYWHERE));
				}
				criteria.add(Restrictions.eq("companyVO.companyId", campaignVO.getCompanyVO().getCompanyId()));
				criteria.add(Restrictions.eq("isDelete", campaignVO.getIsDelete()));
				criteria.add(Restrictions.eq("sending_status", campaignVO.getSending_status()));
				criteria.setProjection(Projections.rowCount());
				count=(long) criteria.uniqueResult();

			}
			return count;
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
	public long lastObject() {
		// TODO Auto-generated method stub
		List<CampaignVO> listVo=new ArrayList<CampaignVO>();
		long id=0;
		try{
			Session session=sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(CampaignVO.class).addOrder(Order.desc("campaignId"));;
			CampaignVO campaignVO=(CampaignVO) criteria.list().get(0);
			if(null!=campaignVO){
				id=campaignVO.getCampaignId();
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


		return id;
	}

/*
	@Override
	public boolean saveManageSms(ManageSms manageSms) {
		// TODO Auto-generated method stub
		try{
			Session session=sessionFactory.getCurrentSession();
			int smsId=(int) session.save(manageSms);
			if(0!=smsId){
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
	}*/
}
