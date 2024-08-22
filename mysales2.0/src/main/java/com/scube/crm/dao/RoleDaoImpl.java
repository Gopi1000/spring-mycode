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
import com.scube.crm.vo.ProductServiceVO;
import com.scube.crm.vo.RoleVO;


@Repository
public class RoleDaoImpl implements RoleDao {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {

		return sessionFactory.getCurrentSession();

	}

	public RoleVO insertrole(RoleVO rolevo) {
		Session session = getSession();
		session.save(rolevo);
		return rolevo;
	}

	public List<RoleVO> retrieverole() {
		List<RoleVO> rolevo = new ArrayList<>();
		try {
			Session session = getSession();
			Criteria criteria = session.createCriteria(RoleVO.class);
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

	public RoleVO getParticularrole(long editId) {
		RoleVO ROLEVO = new RoleVO();

		try {

			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(RoleVO.class);
			
			criteria.add(Restrictions.eq("roleid", editId));
			ROLEVO = (RoleVO) criteria.uniqueResult();

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		return ROLEVO;
	}

	public String updateRole(RoleVO rolevo) {
		try {

			Session session = sessionFactory.getCurrentSession();
			session.update(rolevo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public String deleteRole(RoleVO rolevo) {
		try {

			Session session = sessionFactory.getCurrentSession();
			session.delete(rolevo);

			return "deleted";
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;

	}
	
	
	public String deleteRoleprivilege(RoleVO rolevo) {
		try {

			Session session = sessionFactory.getCurrentSession();
			session.delete(rolevo.getPrivilegelist());

			return "deleted";
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;

	}

	public List<RoleVO> listrole(RoleVO rolevo) {

		List<RoleVO> listVO = new ArrayList<RoleVO>();

		try {

			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(RoleVO.class);

			if (null != rolevo.getRolename() && !rolevo.getRolename().isEmpty()) {
				criteria.add(Restrictions.ilike("rolename", rolevo.getRolename(), MatchMode.ANYWHERE));
			}

			listVO = criteria.list();

		} catch (Exception e) {

		}
		return listVO;
	}
	
	public List<RoleVO> listRole(RoleVO rOLEVO) {
		List<RoleVO> listVO = new ArrayList<RoleVO>();

		try {

			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(RoleVO.class);

			if (null != rOLEVO.getRolename() && !rOLEVO.getRolename().isEmpty()) {
				criteria.add(Restrictions.ilike("rolename", rOLEVO.getRolename(), MatchMode.ANYWHERE));
			}
			
			listVO = criteria.list();

		} catch (Exception e) {

		}
		return listVO;
	}
	
	
	public List<PrivilegeVO> listPrivilege(PrivilegeVO privilegeVO) {
		List<PrivilegeVO> listVO = new ArrayList<PrivilegeVO>();

		try {

			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(PrivilegeVO.class);

			if (null != privilegeVO.getPrivilegename() && !privilegeVO.getPrivilegename().isEmpty()) {
				criteria.add(Restrictions.ilike("privilegename", privilegeVO.getPrivilegename(), MatchMode.ANYWHERE));
			}
			
			listVO = criteria.list();

		} catch (Exception e) {

		}
		return listVO;
	}
	
	
	public RoleVO insertroleprivilege(RoleVO rpvo) {
		Session session = getSession();
		session.saveOrUpdate(rpvo);
		session.flush();
		return rpvo;
	}
	
	
	
		
	public List<RoleVO> retrieveroleprivilege() {
		List<RoleVO> rolevo = new ArrayList<>();
		try {

			Session session = getSession();
			Criteria criteria = session.createCriteria(RoleVO.class);
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

	
	public RoleVO getParticularroleprivilege(long editId) {
		RoleVO ROLEVO = new RoleVO();

		try {

			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(RoleVO.class);
			
			criteria.add(Restrictions.eq("roleid", editId));
			ROLEVO = (RoleVO) criteria.uniqueResult();

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		return ROLEVO;
	}
	
}
