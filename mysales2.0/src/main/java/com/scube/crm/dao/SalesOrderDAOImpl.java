package com.scube.crm.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.scube.crm.bo.ClientBO;
import com.scube.crm.bo.GstBO;
import com.scube.crm.bo.SalesOrderBO;
import com.scube.crm.vo.GstVO;
import com.scube.crm.vo.SalesOrderVO;


@Repository
public class SalesOrderDAOImpl implements SalesOrderDAO {
	@Autowired
	SessionFactory sessionFactory;

Session getSession() {
	return sessionFactory.getCurrentSession();
	
}
	
	
	public List<SalesOrderVO> retriveSalesOrders() {
		
		Session session=getSession();
		Criteria cr=session.createCriteria(SalesOrderVO.class);
		/*
		 * cr.setProjection(Projection.) cr.addOrder(Order.asc("salesOrderNo"));
		 * cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		 */
	
		 
		// ProjectionList projectionList = Projections.projectionList();
		  //  projectionList.add(Projections.groupProperty("salesOrderNo"));
		/* projectionList.add(Projections.rowCount()); */
		   // cr.setProjection(projectionList);
		    List <SalesOrderVO> salesOrderVO = cr.list();
		    
	//	List <SalesOrderBO> salesOrderBO= (List<SalesOrderBO>) cr.list().stream().collect(Collectors.toList());
		if(null!=salesOrderVO&&!salesOrderVO.isEmpty()&&salesOrderVO.size()>0) {
			return salesOrderVO;}
		return salesOrderVO;
			
		
	}
	
	public List<SalesOrderVO> getSalesOrderList(SalesOrderBO salesOrderNo) {
		List<SalesOrderVO> salesList=new ArrayList<>();
		Session session=getSession();
		Criteria cr=session.createCriteria(SalesOrderVO.class);
		cr.add(Restrictions.eq("salesOrderNo", salesOrderNo.getSalesOrderNo()));
		  salesList=cr.list();
			if(null!=salesList&&!salesList.isEmpty()&&salesList.size()>0) {
				return salesList;
			}
		return salesList;
	}
	
	public GstBO getGstById(long gstId) {
		Session session=getSession();
		Criteria cr=session.createCriteria(GstVO.class);
		cr.add(Restrictions.eq("gstId",gstId));
		GstBO gstBO=(GstBO) cr.uniqueResult();
		if(null!=gstBO) {
			return gstBO;
		}
		return gstBO;
		
		
	}
}
