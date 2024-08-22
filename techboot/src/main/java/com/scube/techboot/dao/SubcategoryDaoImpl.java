package com.scube.techboot.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scube.techboot.bo.CategoryBO;
import com.scube.techboot.bo.SubcategoryBO;
import com.scube.techboot.vo.CategoryVO;
import com.scube.techboot.vo.SubcategoryVO;


@Repository
public class SubcategoryDaoImpl implements SubcategoryDao {
	
	private static final Logger LOGGER=Logger.getLogger(SubcategoryDaoImpl.class);
	@Autowired
	private SessionFactory SessionFactory;
	
	@Override
	public SubcategoryBO savesubcategory(SubcategoryVO subcategoryVO) {
		// TODO Auto-generated method stub
		SubcategoryBO subcategory=new SubcategoryBO();
			int priority=0;
			int updatedpriority=0;
			int id=0;
			if(null!=subcategoryVO)
			{
			try{
				Session session= SessionFactory.getCurrentSession();			
				
				
				Criteria criteria=session.createCriteria(SubcategoryVO.class,"subcategory");
				criteria.addOrder(Order.desc("priority"));
				//criteria.setMaxResults(1);
				List<SubcategoryVO> subcategoryPriority=criteria.list();
				if(null!=subcategoryPriority && subcategoryPriority.size()>0){
				updatedpriority=subcategoryPriority.get(0).getPriority()+1;
				}
				
				if(updatedpriority==0){
					updatedpriority=1;}							
					
								
				subcategoryVO.setPriority(updatedpriority);
				id= (int) session.save(subcategoryVO);				
				
			
			if(0!=id){
				subcategory.setSubcategoryId(id);
			}
		}
		catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}}
		return subcategory;

	
	}

	@Override
	public List<SubcategoryBO> getSubcategoryobject() {
		// TODO Auto-generated method stub
		List<SubcategoryBO> subcategorybo=new ArrayList<SubcategoryBO>();
		try {
			Session session= SessionFactory.getCurrentSession();
			Criteria cr=session.createCriteria(SubcategoryVO.class);
			List results= cr.list();
			AtomicInteger incre=new AtomicInteger(0);
			
		for(Iterator iterator=results.iterator();iterator.hasNext();){
			SubcategoryVO subcategoryvo= (SubcategoryVO) iterator.next();
			SubcategoryBO subcategorybo1=new SubcategoryBO();
			
			subcategorybo1.setSubcategoryId(subcategoryvo.getSubcategoryId());
			subcategorybo1.setSubcategory(subcategoryvo.getSubcategory());
			subcategorybo1.setS_No(incre.incrementAndGet());
						
			CategoryBO categorybo=new CategoryBO();
			categorybo.setCategory(subcategoryvo.getCategoryVO().getCategory());
			subcategorybo1.setCategoryBO(categorybo);
			subcategorybo.add(subcategorybo1);
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
		return subcategorybo;

}

	@Override
	public List<SubcategoryVO> search(String subcategory) {
		// TODO Auto-generated method stub
		List<SubcategoryBO>listbo=new ArrayList<SubcategoryBO>();
		List<SubcategoryVO>listvo=new ArrayList<SubcategoryVO>();
		try
		{
			
		Session session=SessionFactory.getCurrentSession();
		Criteria cr=session.createCriteria(SubcategoryVO.class);
		cr.add(Restrictions.eq("subcategory",subcategory));
		 listvo=cr.list();
		
		
		
	}
	
	catch(Exception e){
		System.out.println(e);
	}

	return listvo;
		
		
	}

	@Override
	public SubcategoryVO editsubcategory(SubcategoryVO subcategoryVO) {
		// TODO Auto-generated method stub
		SubcategoryVO subcategory=new SubcategoryVO();
		try
		{
			
		Session session=SessionFactory.getCurrentSession();
		Criteria cr=session.createCriteria(SubcategoryVO.class);
		cr.add(Restrictions.eq("subcategoryId",subcategoryVO.getSubcategoryId()));
		 subcategory=(SubcategoryVO) cr.uniqueResult();
		
		
		
	}
	
	catch(Exception e){
		System.out.println(e);
	}
		
		
		return subcategory;
	}

	@Override
	public SubcategoryVO retrievesubcategory(SubcategoryVO subcategoryVO) {
		// TODO Auto-generated method stub
		SubcategoryVO subcategoryvo=new SubcategoryVO();
		Session session= SessionFactory.openSession();

		try
		{
			
		//Session session=SessionFactory.getCurrentSession();
		if(0!=subcategoryVO.getSubcategoryId()){
		Criteria cr=session.createCriteria(SubcategoryVO.class);
		cr.add(Restrictions.eq("subcategoryId",subcategoryVO.getSubcategoryId()));
		 SubcategoryVO subcategory=(SubcategoryVO) cr.uniqueResult();
		if(null!=subcategory){
			subcategoryvo.setCreatedBy(subcategory.getCreatedBy());
			subcategoryvo.setCreatedTime(subcategory.getCreatedTime());
			subcategoryvo.setIsDelete(subcategory.getIsDelete());
			subcategoryvo.setSending_status(subcategory.getSending_status());
		}
		
		}
	}
	
	catch(Exception e){
		System.out.println(e);
	}
		finally{
			session.flush();
			session.close();
		}
		
		return subcategoryvo;
	}

	@Override
	public Boolean subcategoryUpdate(SubcategoryVO subcategoryVO) {
		// TODO Auto-generated method stub
		try{
			Session session= SessionFactory.getCurrentSession();
			if(null!=subcategoryVO){
				session.update(subcategoryVO);
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
	public List<SubcategoryVO> search(SubcategoryVO subcategoryVO) {
		// TODO Auto-generated method stub
		List<SubcategoryVO>listvo=new ArrayList<SubcategoryVO>();
		List<CategoryVO> categoryVOList=new ArrayList<CategoryVO>();
		try
		{
			
		Session session=SessionFactory.getCurrentSession();
		Criteria cr=session.createCriteria(SubcategoryVO.class);
		if(null!=subcategoryVO.getSubcategory())
				{
			cr.add(Restrictions.eq("subcategory",subcategoryVO.getSubcategory()));
			cr.add(Restrictions.eq("isDelete",subcategoryVO.getIsDelete()));
				}
		else if(0!=subcategoryVO.getSubcategoryId()){
			cr.add(Restrictions.eq("subcategoryId", subcategoryVO.getSubcategoryId()));
			cr.add(Restrictions.eq("isDelete", subcategoryVO.getIsDelete()));
			
		}
		else{
			Criteria cr2=session.createCriteria(CategoryVO.class);
			cr2.add(Restrictions.eq("category", subcategoryVO.getCategoryVO().getCategory()));
			cr2.add(Restrictions.eq("isDelete",subcategoryVO.getIsDelete()));
			categoryVOList=cr2.list();
			if(null!=categoryVOList && categoryVOList.size()>0){
			cr.add(Restrictions.eq("categoryVO.categoryId", categoryVOList.get(0).getCategoryId()));
			cr.add(Restrictions.eq("isDelete",subcategoryVO.getIsDelete()));
			}
		
		}	
			
			 listvo=cr.list();
				}
		catch(Exception e){
			System.out.println(e);
		}

		return listvo;
			
			
				
	}

	@Override
	public boolean isExixstsCategory(SubcategoryVO subcategoryVO) {
		// TODO Auto-generated method stub
		try{
			Session session = SessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(SubcategoryVO.class);
			criteria.add(Restrictions.eq("subcategory", subcategoryVO.getSubcategory()));
			criteria.add(Restrictions.eq("isDelete", subcategoryVO.getIsDelete()));
			SubcategoryVO subcategoryVo = (SubcategoryVO) criteria.uniqueResult();
			if(null!=subcategoryVo){
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
	public long retrievesubcategoryCount(SubcategoryVO subcategoryVO) {
		// TODO Auto-generated method stub
		
		try{
			Session session= SessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(SubcategoryVO.class);
			criteria.add(Restrictions.eq("isDelete",subcategoryVO.getIsDelete()));
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
	public List<SubcategoryVO> viewsubcategory(SubcategoryVO subcategoryVO) {
		// TODO Auto-generated method stub
		List<SubcategoryVO> subcategoryList=new ArrayList<SubcategoryVO>();
		try{
			Session session=SessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(SubcategoryVO.class);
			criteria.add(Restrictions.eq("isDelete",subcategoryVO.getIsDelete()));
			criteria.addOrder(Order.desc("subcategoryId"));
			criteria.setFirstResult(subcategoryVO.getRecordIndex());
			criteria.setMaxResults(subcategoryVO.getMaxRecord());
			subcategoryList=	criteria.list();
			if(null!=subcategoryList && !subcategoryList.isEmpty()&& subcategoryList.size()>0){
				return subcategoryList;
			}
		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}

		}
		return subcategoryList;
		
	}
}
