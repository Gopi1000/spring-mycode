package com.scube.crm.dao;

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
import com.scube.crm.bo.ProductServiceBO;
import com.scube.crm.vo.ProductServiceVO;

@Repository
public class ProductServiceDaoImpl implements ProductServiceDao {

	private static final Logger LOGGER = Logger.getLogger(ProductServiceDaoImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public ProductServiceBO createServices(ProductServiceVO productServiceVO) {
		// TODO Auto-generated method stub
		ProductServiceBO productServiceBo = new ProductServiceBO();
		ProductServiceBO companyService = new ProductServiceBO();
		Session session = sessionFactory.getCurrentSession();
		try {
			if (null != productServiceVO) {
				long serviceid = (long) session.save(productServiceVO);
				if (0 != serviceid) {
					productServiceBo.setServiceName(productServiceVO.getServiceName());
					productServiceBo.setServiceId(serviceid);
					return productServiceBo;
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
		return companyService;
	}

	@Override
	public List<ProductServiceVO> listViewService(ProductServiceVO productServiceVO) {
		// TODO Auto-generated method stub

		List<ProductServiceVO> listVO = new ArrayList<ProductServiceVO>();
		try {
			// SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(ProductServiceVO.class);
			criteria.add(Restrictions.eq("isDelete", productServiceVO.getIsDelete()));
			// criteria.add(Restrictions.eq("isActive", productServiceVO.getIsActive()));
			if (null != productServiceVO.getServiceName() && !productServiceVO.getServiceName().isEmpty()) {
				criteria.add(Restrictions.ilike("serviceName", productServiceVO.getServiceName(), MatchMode.ANYWHERE));
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
	public ProductServiceVO getServiceObject(ProductServiceVO productServiceVO) {
		// TODO Auto-generated method stub
		ProductServiceVO productService = new ProductServiceVO();
		try {
			if (0 != productServiceVO.getServiceId()) {
				Session session = sessionFactory.getCurrentSession();
				Criteria criteria = session.createCriteria(ProductServiceVO.class);
				criteria.add(Restrictions.eq("serviceId", productServiceVO.getServiceId()));
				productService = (ProductServiceVO) criteria.uniqueResult();

			}
		} catch (Exception e) {
			if (LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t" + e);
			}
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t" + e);
			}
		}
		return productService;
	}

	@Override
	public Boolean serviceUpdateDao(ProductServiceVO productServiceVo) {
		// TODO Auto-generated method stub

		try {
			Session session = sessionFactory.getCurrentSession();
			if (null != productServiceVo) {
				session.update(productServiceVo);
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
	public Boolean deleteService(ProductServiceVO productServiceVo) {

		int result;
		try {
			String deleteQuery = "UPDATE ProductServiceVO E set E.isDelete = :isDelete WHERE E.serviceId = :serviceId";
			final Query query = sessionFactory.getCurrentSession().createQuery(deleteQuery);
			query.setParameter("isDelete", productServiceVo.getIsDelete());
			// query.setParameter("isActive", productServiceVo.getIsActive());
			query.setParameter("serviceId", productServiceVo.getServiceId());
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

	@Override
	public ProductServiceVO retrieveService(ProductServiceVO productServiceVo) {

		ProductServiceVO service = new ProductServiceVO();
		Session session = sessionFactory.openSession();
		try {
			if (0 != productServiceVo.getServiceId()) {
				Criteria criteria = session.createCriteria(ProductServiceVO.class);
				criteria.add(Restrictions.eq("serviceId", productServiceVo.getServiceId()));
				ProductServiceVO productService = (ProductServiceVO) criteria.uniqueResult();
				if (null != productService) {
					service.setIsDelete(productService.getIsDelete());
					service.setIsActive(productService.getIsActive());
					return service;
				}
			}
		} catch (Exception e) {
			if (LOGGER.isInfoEnabled()) {
				LOGGER.info("FROM INFO: Exception \t" + e);
			}
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("FROM DEBUG : Exception \t" + e);
			}
		} finally {
			session.flush();
			session.close();
		}
		return service;
	}

	@Override
	public boolean isValidServiceName(ProductServiceVO productServiceVO) {
		// TODO Auto-generated method stub
		try {
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(ProductServiceVO.class);
			criteria.add(Restrictions.eq("serviceName", productServiceVO.getServiceName()));
			criteria.add(Restrictions.eq("isDelete", productServiceVO.getIsDelete()));
			criteria.add(Restrictions.eq("isActive", productServiceVO.getIsActive()));
			List<ProductServiceVO> ServiceList = criteria.list();
			if (null != ServiceList && !ServiceList.isEmpty() && ServiceList.size() > 0) {
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
