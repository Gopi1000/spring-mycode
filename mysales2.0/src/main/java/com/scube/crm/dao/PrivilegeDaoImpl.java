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

import com.scube.crm.vo.PrivilegeVO;

@Repository
public class PrivilegeDaoImpl implements PrivilegeDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {

		return sessionFactory.getCurrentSession();

	}
	
	public PrivilegeVO insertprivilege(PrivilegeVO privilegevo) {
		Session session = getSession();
		session.save(privilegevo);
		return privilegevo;
	}

	
	public List<PrivilegeVO> retrievePrivilege() {
		List<PrivilegeVO> privilegevo = new ArrayList<>();
		try {
			Session session = getSession();
			Criteria criteria = session.createCriteria(PrivilegeVO.class);
			privilegevo = criteria.list();
			if (null != privilegevo && !privilegevo.isEmpty() && privilegevo.size() > 0) {
				return privilegevo;
			}
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		return privilegevo;
	}
	
	public List<PrivilegeVO> listprivilege(PrivilegeVO vO) {
		List<PrivilegeVO> listVO = new ArrayList<PrivilegeVO>();

		try {

			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(PrivilegeVO.class);

			if (null != vO.getPrivilegename() && !vO.getPrivilegename().isEmpty()) {
				criteria.add(Restrictions.ilike("privilegename", vO.getPrivilegename(), MatchMode.ANYWHERE));
			}
			
			listVO = criteria.list();
			
		}   catch (Exception e) {

		}
            return listVO;
	}
	
	
	
	public PrivilegeVO getParticularprivilege(long editId) {
		PrivilegeVO VO = new PrivilegeVO();

		try {

			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(PrivilegeVO.class);
			criteria.add(Restrictions.eq("id", editId));
			VO = (PrivilegeVO) criteria.uniqueResult();

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		return VO;
	}
	
	
	public String updateprivilege(PrivilegeVO privilegevo) {
		try {

			Session session = sessionFactory.getCurrentSession();
			session.update(privilegevo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public String deleteprivilege(PrivilegeVO vo) {
		try {

			Session session = sessionFactory.getCurrentSession();
			session.delete(vo);

			return "deleted";
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;

	}

}
