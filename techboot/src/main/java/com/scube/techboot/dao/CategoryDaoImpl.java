package com.scube.techboot.dao;


import java.util.ArrayList;
import java.util.Iterator;
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

import com.scube.techboot.bo.CategoryBO;
import com.scube.techboot.vo.CategoryVO;
@Repository
public class CategoryDaoImpl implements CategoryDao {
	private static final Logger LOGGER=Logger.getLogger(CategoryDaoImpl.class);
@Autowired
SessionFactory sessionFactory;
	
	
	@Override
	public int saveCategory(CategoryVO categoryVo) {
		

		// TODO Auto-generated method stub
		int id=0;
		if(null!=categoryVo){
			try{
			Session session=sessionFactory.getCurrentSession();
			id=(int) session.save(categoryVo);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return id;
	}


	@Override
	public boolean isExixstsCategory(CategoryVO categoryVO) {
		try{
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(CategoryVO.class);
			criteria.add(Restrictions.eq("category", categoryVO.getCategory()));
			criteria.add(Restrictions.eq("isDelete", categoryVO.getIsDelete()));
			CategoryVO categoryVo = (CategoryVO) criteria.uniqueResult();
			if(null!=categoryVo){
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
	public boolean deleteCategory(CategoryVO categoryVo) {
		if(null!=categoryVo){
			int result = 0;

			String deleteQuery ="Update CategoryVO T set T.isDelete=:isDelete where categoryId=:categoryId";
			
			try{
					Query query = sessionFactory.getCurrentSession().createQuery(deleteQuery);
					query.setParameter("categoryId", categoryVo.getCategoryId());
					query.setParameter("isDelete",categoryVo.getIsDelete());
					result = query.executeUpdate();
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
		}
		return false;
	}


	@Override
	public CategoryVO updateCategory(CategoryVO categoryVo) {
		if(null!=categoryVo){
			try{
				Session session = sessionFactory.getCurrentSession();
				Criteria cr = session.createCriteria(CategoryVO.class);
				cr.add(Restrictions.eq("categoryId",categoryVo.getCategoryId()));
				cr.add(Restrictions.eq("isDelete",categoryVo.getIsDelete()));
				categoryVo	=(CategoryVO) cr.uniqueResult();
			}catch(Exception e){
				e.printStackTrace();	
			}	
		}
		return categoryVo;
	}


	@Override
	public boolean updateSaveCategory(CategoryVO categoryVo) {
		if(null!=categoryVo){
			try{
				Session session = sessionFactory.getCurrentSession();
				CategoryVO category=(CategoryVO) session.load(CategoryVO.class,categoryVo.getCategoryId());
				category.setCategory(categoryVo.getCategory());
				category.setModifiedTime(categoryVo.getModifiedTime());
				category.setModifiedBy(categoryVo.getModifiedBy());
				session.update(category);
				return true;
			}catch(Exception e){
				if(LOGGER.isInfoEnabled()) {
					LOGGER.info("FROM INFO: Exception \t"+e);
				}
				if(LOGGER.isDebugEnabled()) {
					LOGGER.debug("FROM DEBUG : Exception \t"+e);
				}
			}
		}
		return false;
	}


	@Override
	public long retrievecategoryCount(CategoryVO categoryVO) {
		try{
			Session session= sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(CategoryVO.class);
			criteria.add(Restrictions.eq("isDelete",categoryVO.getIsDelete()));
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
	public List<CategoryVO> viewCatogery(CategoryVO categoryVO) {
		List<CategoryVO> categoryList=new ArrayList<CategoryVO>();
		try{
			Session session=sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(CategoryVO.class);
			criteria.add(Restrictions.eq("isDelete",categoryVO.getIsDelete()));
			criteria.addOrder(Order.desc("categoryId"));
			criteria.setFirstResult(categoryVO.getRecordIndex());
			criteria.setMaxResults(categoryVO.getMaxRecord());
			categoryList=	criteria.list();
			if(null!=categoryList && !categoryList.isEmpty()&& categoryList.size()>0){
				return categoryList;
			}
		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}

		}
		return categoryList;
	
	}


	@Override
	public int searchCategory(CategoryVO categoryVO) {
		List<CategoryVO> categoryList=new ArrayList<CategoryVO>();
		int totalCategory=0;
		try{
			Session session=sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(CategoryVO.class);
			criteria.add(Restrictions.ilike("category", categoryVO.getCategory(),MatchMode.ANYWHERE));
			criteria.add(Restrictions.eq("isDelete",categoryVO.getIsDelete()));
			categoryList=criteria.list();
			if(null!=categoryList && categoryList.size()>0 && !categoryList.isEmpty()){
				totalCategory=categoryList.size();
			}
		}
		catch (Exception e) {
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}

		}
		return totalCategory;
	}



	@Override
	public List<CategoryVO> searchPageCategory(CategoryVO categoryVO) {
		List<CategoryVO> categoryList=new ArrayList<CategoryVO>();
		try{
			Session session=sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(CategoryVO.class);
			criteria.add(Restrictions.ilike("category",categoryVO.getCategory(),MatchMode.ANYWHERE));
			criteria.setFirstResult(categoryVO.getRecordIndex());
			criteria.setMaxResults(categoryVO.getMaxRecord());
			criteria.add(Restrictions.eq("isDelete",categoryVO.getIsDelete()));
			categoryList=criteria.list();

		}
		catch (Exception e) {
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return categoryList;
	}


	@Override
	public List<CategoryBO> viewcat() {
		// TODO Auto-generated method stub
		List<CategoryBO> bo=new ArrayList<CategoryBO>();
		try{
			Session session=sessionFactory.getCurrentSession();
			Criteria cr=session.createCriteria(CategoryVO.class);
			List results = cr.list();
			for (Iterator iterator =  results.iterator(); iterator.hasNext();){
				CategoryVO  category = (CategoryVO) iterator.next();
				CategoryBO categoryBO1=new CategoryBO();
				categoryBO1.setCategory(category.getCategory());
				categoryBO1.setCategoryId(category.getCategoryId());
				
				bo.add(categoryBO1);
				
			}}
		catch(Exception e){
			System.out.println(e);
	}
	
		return bo;
}


		
	}


