/**
 * 
 */
package com.scube.crm.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;


import com.scube.crm.model.EmployerLoginVO;
import com.scube.crm.model.EmployerVO;
import com.scube.crm.utils.ErrorCodes;
import com.scube.crm.vo.CompanyVO;

/**
 * @author User
 *
 */
public class EmployerclientDaoImpl implements EmployerclientDao {

	SessionFactory  myjobkartSessionFactory;


	/**
	 * @return the myjobkartSessionFactory
	 */
	public SessionFactory getMyjobkartSessionFactory() {
		return myjobkartSessionFactory;
	}


	/**
	 * @param myjobkartSessionFactory the myjobkartSessionFactory to set
	 */
	public void setMyjobkartSessionFactory(SessionFactory myjobkartSessionFactory) {
		this.myjobkartSessionFactory = myjobkartSessionFactory;
	}


	/* (non-Javadoc)
	 * @see com.scube.myjobkart.dao.EmployerclientDao#register(com.scube.crm.vo.EmployerVO)
	 */
	@Override
	public EmployerVO clientTomyjobkartRegister(EmployerVO VO) {
		try {
			Session session= getMyjobkartSessionFactory().openSession();
			session.saveOrUpdate(VO);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return VO;
	}


	/* (non-Javadoc)
	 * @see com.scube.myjobkart.dao.EmployerclientDao#signin(com.scube.myjobkart.vo.EmployerLoginVO)
	 */
	@Override
	public EmployerLoginVO signinTomyjobkart(EmployerLoginVO loginVO) {
		try{
			Session session= getMyjobkartSessionFactory().openSession();
			session.saveOrUpdate(loginVO);
		}catch(Exception e){
			e.printStackTrace();
		}
		return loginVO;
	}


	/* (non-Javadoc)
	 * @see com.scube.myjobkart.dao.EmployerclientDao#company()
	 */
	@Override
	public CompanyVO retrieveCompany(String company) {
		String companyname=company;
		CompanyVO companyVO= new CompanyVO();
		Session session=getMyjobkartSessionFactory().openSession();
		try{
			Criteria cr = session.createCriteria(CompanyVO.class);
			cr.add(Restrictions.ilike("companiesName", companyname,MatchMode.ANYWHERE));
			List<CompanyVO>VOList=cr.list();
		 VOList.get(0).getCompaniesId();
		 companyVO.setCompaniesId( VOList.get(0).getCompaniesId());
		}catch(Exception e){
			e.printStackTrace();
		}
		return companyVO;
	}


	/* (non-Javadoc)
	 * @see com.scube.myjobkart.dao.EmployerclientDao#addnewcompany(com.scube.myjobkart.vo.CompanyVO)
	 */
	@Override
	public long addnewcompany(CompanyVO companyVO) {
		try {
			Session session =getMyjobkartSessionFactory().openSession();
			long id = (Long)session.save(companyVO);
			return id;
		} catch (HibernateException he) {
			he.printStackTrace();
		}
		return 0;
	}

}
