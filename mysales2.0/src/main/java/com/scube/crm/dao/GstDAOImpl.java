package com.scube.crm.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scube.crm.bo.GstBO;
import com.scube.crm.bo.ProductServiceBO;
import com.scube.crm.vo.GstVO;
import com.scube.crm.vo.ProductServiceVO;

@Repository
public class GstDAOImpl implements GstDAO{

	private static final Logger LOGGER = Logger.getLogger(ProductServiceDaoImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public GstBO createGstValues(GstVO gstVO) {
		GstBO gstBO=new GstBO();
		Session session = sessionFactory.getCurrentSession();
		try {
			if (null != gstVO) {
				long gstId = (long) session.save(gstVO);
				if (0 < gstId) {
					gstBO.setSgst(gstVO.getSgst());
					gstBO.setCgst(gstVO.getCgst());
					gstBO.setGstId(gstId);
					gstBO.setStartDate(gstVO.getStartDate());
				}
			}
		} catch (Exception e) {
			if (LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t" + e);
			}
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t" + e);
			}
		}
		return gstBO;
	}

	@Override
	public List<GstVO> getListGst(GstVO gstVO) {

		List<GstVO> listVO = new ArrayList<GstVO>();
		try {
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(GstVO.class);
			criteria.add(Restrictions.eq("isDelete", gstVO.getIsDelete()));
			criteria.add(Restrictions.eq("isActive", gstVO.getIsActive()));
			if(null!=gstVO.getCgst()&& !gstVO.getCgst().isEmpty()) {
				criteria.add(Restrictions.ilike("cgst",gstVO.getCgst(), MatchMode.ANYWHERE));
			}
			if(null!=gstVO.getSgst()&& !gstVO.getSgst().isEmpty()) {
				criteria.add(Restrictions.ilike("sgst",gstVO.getSgst(), MatchMode.ANYWHERE));
			}
			if(null!=gstVO.getStartDate() ) {
				criteria.add(Restrictions.eq("startDate",gstVO.getStartDate()));
			}
			listVO = criteria.list();
		} catch (Exception e) {
			if (LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t" + e);
			}
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t" + e);
			}
		}
		return listVO;
	}

	@Override
	public GstVO getGstValues(GstVO gstVO) {

		GstVO gstVo = new GstVO();
		try {
			if (0<gstVO.getGstId()) {
				Session session = sessionFactory.getCurrentSession();
				Criteria criteria = session.createCriteria(GstVO.class);
				criteria.add(Restrictions.eq("gstId", gstVO.getGstId()));
				gstVo = (GstVO) criteria.uniqueResult();

			}
		} catch (Exception e) {
			if (LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t" + e);
			}
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t" + e);
			}
		}
		return gstVo;
	}

	@Override
	public boolean gstUpdateValues(GstVO gstVO) {
		try {
			Session session = sessionFactory.getCurrentSession();
			if (null != gstVO) {
				session.update(gstVO);
				return true;
			}
		} catch (Exception e) {
			if (LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t" + e);
			}
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t" + e);
			}
		}
		return false;
	}

	@Override
	public Boolean deleteGstValues(GstVO gstVO) {
		int result;
		try {
			String deleteQuery = "UPDATE GstVO E set E.isDelete = :isDelete,E.isActive= :isActive"
					                                                                                        + "  WHERE E.gstId = :gstId";
			final Query query = sessionFactory.getCurrentSession().createQuery(deleteQuery);
			query.setParameter("isDelete", gstVO.getIsDelete());
			query.setParameter("isActive", gstVO.getIsActive());
			query.setParameter("gstId", gstVO.getGstId());
			result = query.executeUpdate();
			if (0 != result) {
				return true;
			}
		} catch (Exception e) {
			if (LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t" + e);
			}
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t" + e);
			}
		}
		return false;
	
	}
}
