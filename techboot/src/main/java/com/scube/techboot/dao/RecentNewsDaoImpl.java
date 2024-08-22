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

import com.scube.techboot.vo.RecentNewsVO;

@Repository("RecentNewsDaoImpl")
public class RecentNewsDaoImpl implements RecentNewsDao {
	
	private static final Logger LOGGER=Logger.getLogger(RecentNewsDaoImpl.class);
 
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public long retrieveNewsCount(RecentNewsVO recentNewsVo) {
		// TODO Auto-generated method stub
		long value=0;
		try{
			Session session=sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(RecentNewsVO.class);
			criteria.addOrder(Order.desc("newsId"));
			List<RecentNewsVO> newslist=criteria.list();
			if(null!=newslist && !newslist.isEmpty() && newslist.size()>0){
				recentNewsVo=newslist.get(0);
				long count=(recentNewsVo.getNewsId());
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
		return value;
	}
	@Override
	public RecentNewsVO saveRecentNewsDetails(RecentNewsVO recentNewsVo) {
		// TODO Auto-generated method stub
		try{
			Session session= sessionFactory.getCurrentSession();
			int newsid=(int) session.save(recentNewsVo);
			if(0!=newsid){
				recentNewsVo.setNewsId(newsid);
				return recentNewsVo;
			}

		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return recentNewsVo;
	}
	@Override
	public List<RecentNewsVO> getViewRecentNewsList(RecentNewsVO recentNewsVo) {
		// TODO Auto-generated method stub
		List<RecentNewsVO> newslist=new ArrayList<RecentNewsVO>();
		try{
			Session session= sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(RecentNewsVO.class);
			criteria.add(Restrictions.eq("isDelete",recentNewsVo.getisDelete()));
			criteria.add(Restrictions.eq("sending_status", recentNewsVo.getSending_status()));
			if(null!=recentNewsVo.getTitleName() && !recentNewsVo.getTitleName().isEmpty()){
				criteria.add(Restrictions.ilike("titleName",recentNewsVo.getTitleName(),MatchMode.ANYWHERE));
			}
			 if(null!=recentNewsVo.getAuthorName() && !recentNewsVo.getAuthorName().isEmpty()){
					criteria.add(Restrictions.ilike("authorName",recentNewsVo.getAuthorName(),MatchMode.ANYWHERE));
			}
			 if(null!=recentNewsVo.getDate()){
					criteria.add(Restrictions.eq("date",recentNewsVo.getDate()));
			}
			criteria.setFirstResult(recentNewsVo.getRecordIndex());
			criteria.setMaxResults(recentNewsVo.getMaxRecord());
			criteria.addOrder(Order.desc("newsId"));
			newslist=criteria.list();
			if(null!=newslist && !newslist.isEmpty() && newslist.size()>0){
				return newslist;
			}
		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return newslist;
	}
	@Override
	public RecentNewsVO getRecentNewsObject(RecentNewsVO recentNewsVo) {
		// TODO Auto-generated method stub
		try{
			Session session= sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(RecentNewsVO.class);
			criteria.add(Restrictions.eq("newsId", recentNewsVo.getNewsId()));
			criteria.add(Restrictions.eq("isDelete",recentNewsVo.getisDelete()));
			criteria.add(Restrictions.eq("sending_status", recentNewsVo.getSending_status()));
			RecentNewsVO recentNewsVO=(RecentNewsVO) criteria.uniqueResult();
			if(null!=recentNewsVO){
				return recentNewsVO;
			}
		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return recentNewsVo;
	}
	@Override
	public boolean postEditRecentNews(RecentNewsVO recentNewsVo) {
		// TODO Auto-generated method stub
		try{
			Session session= sessionFactory.getCurrentSession();
			session.saveOrUpdate(recentNewsVo);
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
	public boolean deleteRecentNews(RecentNewsVO recentNewsVo) {
		// TODO Auto-generated method stub
		try{
			String deleteQuery="UPDATE RecentNewsVO R set R.isDelete = :isDelete,R.sending_status = :sending_status WHERE R.newsId = :newsId";
			final Query query = sessionFactory.getCurrentSession().createQuery(deleteQuery);
			query.setParameter("isDelete", recentNewsVo.getisDelete());
			query.setParameter("sending_status", recentNewsVo.getSending_status());
			query.setParameter("newsId", recentNewsVo.getNewsId());
			int result=query.executeUpdate();
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
	public long getRetrieveNewsCount(RecentNewsVO recentNewsVo) {
		// TODO Auto-generated method stub
		List<RecentNewsVO> newslist= new ArrayList<>();
		long count=0;
		try{
			Session session= sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(RecentNewsVO.class);
			criteria.add(Restrictions.eq("isDelete",recentNewsVo.getisDelete()));
			criteria.add(Restrictions.eq("sending_status", recentNewsVo.getSending_status()));
			if(null!=recentNewsVo.getTitleName()  && !recentNewsVo.getTitleName().isEmpty()){
				criteria.add(Restrictions.ilike("titleName",recentNewsVo.getTitleName(),MatchMode.ANYWHERE));
			}
			 if(null!=recentNewsVo.getAuthorName() && !recentNewsVo.getAuthorName().isEmpty()){
				criteria.add(Restrictions.ilike("authorName",recentNewsVo.getAuthorName(),MatchMode.ANYWHERE));
			}
			 if(null!=recentNewsVo.getDate()){
				criteria.add(Restrictions.eq("date",recentNewsVo.getDate()));
			}
			newslist=criteria.list();
			if(null!=newslist && !newslist.isEmpty() && newslist.size()>0){
					count= newslist.size();
			}
			
			/*else{
			criteria.setProjection(Projections.rowCount());
			 count=(long) criteria.uniqueResult();
			}*/
			
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
}
