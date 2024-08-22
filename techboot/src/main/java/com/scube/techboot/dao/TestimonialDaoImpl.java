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

import com.scube.techboot.vo.CompanyVO;
import com.scube.techboot.vo.TestimonialVO;

@Repository("TestimonialDaoImpl")
public class TestimonialDaoImpl implements TestimonialDao {
	
	private static final Logger LOGGER=Logger.getLogger(TestimonialDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public TestimonialVO saveTestimonial(TestimonialVO testimonialVo) {
		// TODO Auto-generated method stub
		try{
			Session session= sessionFactory.getCurrentSession();
			long testid= (long) session.save(testimonialVo);
			if(0!=testid){
				testimonialVo.setTestimonialId(testid);
				return testimonialVo;
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
		return testimonialVo;
	}

	@Override
	public long retriveOfTestimonial(TestimonialVO testimonialVo) {
		// TODO Auto-generated method stub
		long value=0;
		try{
			Session session=sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(TestimonialVO.class);
			criteria.addOrder(Order.desc("testimonialId"));
			List<TestimonialVO> listVo=criteria.list();
			if(null!=listVo && !listVo.isEmpty() && listVo.size()>0){
				testimonialVo=listVo.get(0);
				long count=(testimonialVo.getTestimonialId());
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
	public List<TestimonialVO> viewListTestimonial(TestimonialVO testimonialVo) {
		// TODO Auto-generated method stub
		List<TestimonialVO>testimonialList =new ArrayList<TestimonialVO>();
		try{
			Session session= sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(TestimonialVO.class);
			criteria.add(Restrictions.eq("isDelete",testimonialVo.getIsDelete()));
			criteria.add(Restrictions.eq("sending_status", testimonialVo.getSending_status()));
			criteria.setFirstResult(testimonialVo.getRecordIndex());
			criteria.setMaxResults(testimonialVo.getMaxRecord());
			criteria.addOrder(Order.desc("testimonialId"));
			testimonialList=criteria.list();
			if(null!=testimonialList && !testimonialList.isEmpty() && testimonialList.size()>0){
				return testimonialList;
			}
		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return testimonialList;
	}
	
	@Override
	public boolean postEditTestimonial(TestimonialVO testimonialVo) {
		// TODO Auto-generated method stub
		try{
			Session session=sessionFactory.getCurrentSession();
			if(null!=testimonialVo){
				session.saveOrUpdate(testimonialVo);
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
	public boolean deleteTestimonial(TestimonialVO testimonialVo) {
		// TODO Auto-generated method stub
		try{
			String deleteQuery="UPDATE TestimonialVO T set T.isDelete = :isDelete,T.sending_status = :sending_status WHERE T.testimonialId = :testimonialId";
			final Query query = sessionFactory.getCurrentSession().createQuery(deleteQuery);
			query.setParameter("isDelete", testimonialVo.getIsDelete());
			query.setParameter("sending_status", testimonialVo.getSending_status());
			query.setParameter("testimonialId", testimonialVo.getTestimonialId());
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
	public long retrieveTestimonialCount(TestimonialVO testimonialVo) {
		// TODO Auto-generated method stub
		try{
			Session session= sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(TestimonialVO.class);
			criteria.add(Restrictions.eq("isDelete",testimonialVo.getIsDelete()));
			criteria.add(Restrictions.eq("sending_status", testimonialVo.getSending_status()));
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
	public TestimonialVO getTestimonialObject(TestimonialVO testimonialVo) {
		// TODO Auto-generated method stub
		try{
			Session session= sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(TestimonialVO.class);
			criteria.add(Restrictions.eq("testimonialId",testimonialVo.getTestimonialId()));
			criteria.add(Restrictions.eq("isDelete",testimonialVo.getIsDelete()));
			TestimonialVO testimonialVO=(TestimonialVO) criteria.uniqueResult();
			if(null!=testimonialVO){
				return testimonialVO;
			}
		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return testimonialVo;
	}

	@Override
	public List<TestimonialVO> viewTestimonial(TestimonialVO testimonialVo) {
		// TODO Auto-generated method stub
		List<TestimonialVO> testingVo=new ArrayList<TestimonialVO>();
		try{
			Session session= sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(TestimonialVO.class);
			criteria.add(Restrictions.eq("isDelete",testimonialVo.getIsDelete()));
			criteria.add(Restrictions.eq("sending_status", testimonialVo.getSending_status()));
			criteria.addOrder(Order.desc("testimonialId"));
			testingVo=criteria.list();
			if(null!=testingVo && !testingVo.isEmpty() && testingVo.size()>0){
				return testingVo;
			}
		}catch(Exception e){
			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return testingVo;
	}

	@Override
	public int searchTestimonial(TestimonialVO testimonialVO) {
		// TODO Auto-generated method stub
		List<TestimonialVO> testimonialList=new ArrayList<TestimonialVO>();
		int totalTestimonial=0;
		try{
			Session session=sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(TestimonialVO.class);
			criteria.add(Restrictions.ilike("name", testimonialVO.getName(),MatchMode.ANYWHERE));
			criteria.add(Restrictions.eq("isDelete", testimonialVO.getIsDelete()));
			testimonialList=criteria.list();
			if(null!=testimonialList && testimonialList.size()>0 && !testimonialList.isEmpty()){
				totalTestimonial=testimonialList.size();
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
		return totalTestimonial;
		
	}
	
	@Override
	public List<TestimonialVO> searchTestimonialData(TestimonialVO testimonialVO) {
		// TODO Auto-generated method stub
		List<TestimonialVO> testimonialList=new ArrayList<TestimonialVO>();
		try{
			Session session=sessionFactory.getCurrentSession();
			Criteria criteria=session.createCriteria(TestimonialVO.class);
			criteria.add(Restrictions.ilike("name", testimonialVO.getName(),MatchMode.ANYWHERE));
			criteria.setFirstResult(testimonialVO.getRecordIndex());
			criteria.setMaxResults(testimonialVO.getMaxRecord());
			criteria.add(Restrictions.eq("isDelete", testimonialVO.getIsDelete()));
			testimonialList=criteria.list();

		}
		catch (Exception e) {
			if(LOGGER.isInfoEnabled()){
				LOGGER.info("FROM INFO: Exception \t"+e);
			}
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("FROM DEBUG : Exception \t"+e);
			}
		}
		return testimonialList;
		
	}

}
