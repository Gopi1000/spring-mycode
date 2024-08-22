package com.scube.crm.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scube.crm.vo.AccessVO;

@Repository
public class AccessDaoImpl implements AccessDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {

		return sessionFactory.getCurrentSession();

	}

	
	public AccessVO insertaccess(AccessVO vo) {
		Session session = getSession();
		session.save(vo);
		return vo;
	}
	
	public List<AccessVO> retrieveaccess() {
		
		List<AccessVO> rolevo = new ArrayList<>();
		try {
			Session session = getSession();
			Criteria criteria = session.createCriteria(AccessVO.class);
			
			rolevo = criteria.list();
			
			if (null != rolevo && !rolevo.isEmpty() && rolevo.size() > 0) {
				return rolevo;
			}
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		return rolevo;
	}
	
	public List<AccessVO> listaccess(AccessVO accessvo) {
		List<AccessVO> listVO = new ArrayList<AccessVO>();

		try {

			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(AccessVO.class);

			if (null != accessvo.getAccessname() && !accessvo.getAccessname().isEmpty()) {
				criteria.add(Restrictions.ilike("accessname", accessvo.getAccessname(), MatchMode.ANYWHERE));
			}

			listVO = criteria.list();

		} catch (Exception e) {

		}
		return listVO;
	}
	
	public AccessVO getParticularaccess(long editId) {
		AccessVO VO = new AccessVO();

		try {

			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(AccessVO.class);
			criteria.add(Restrictions.eq("id", editId));
			VO = (AccessVO) criteria.uniqueResult();

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		return VO;
	}
	
	public String updateaccess(AccessVO accessvo) {
		try {

			Session session = sessionFactory.getCurrentSession();
			session.update(accessvo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public String deleteaccess(AccessVO accessvo) {
		try {

			Session session = sessionFactory.getCurrentSession();
			session.delete(accessvo);

			return "deleted";
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;

	}

}
