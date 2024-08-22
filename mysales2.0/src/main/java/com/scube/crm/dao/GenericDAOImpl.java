package com.scube.crm.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.scube.crm.exception.MyClientsLogger;
import com.scube.crm.exception.MyClientsException;
import com.scube.crm.service.GenericService;
import com.scube.crm.utils.ErrorCodes;
import com.scube.crm.vo.BODTO;
import com.scube.crm.vo.BasicEntity;

/**
 * This class to be form an common function for this application
 * 
 * @author Scube Technologies
 * @param <E>
 */
@Repository("genericDAO")
public abstract class GenericDAOImpl<T extends BasicEntity> implements
		GenericDAO<T> {

	private static final MyClientsLogger LOGGER = MyClientsLogger
			.getLogger(GenericDAOImpl.class);

	protected abstract GenericService<T> getBasicService();

	@Resource(name="sessionFactory")
	SessionFactory sessionFactory;

	private final Class<BasicEntity> tClass;
	
	
	protected Session getSession() {
	
		return sessionFactory.getCurrentSession();
		
	}

	

	@SuppressWarnings("unchecked")
	public GenericDAOImpl() throws MyClientsException {
		this.tClass = (Class<BasicEntity>) new com.scube.crm.utils.GenericTypeResolver<T>()
				.resolveGenericType(this.getClass());
	}

	/**
	 * This method save the data in data base in any entity
	 * 
	 * @param entity
	 * @throws MyClientsException
	 * @return entity
	 */

	@Override
	public T create(T entity) throws MyClientsException {
		GenericDAOImpl.LOGGER.entry();
		try {

			getSession().save(entity);
			getSession().flush();
		} catch (final HibernateException he) {
			if (GenericDAOImpl.LOGGER.isDebugEnabled()) {
				GenericDAOImpl.LOGGER.debug(ErrorCodes.ENTITY_CRE_FAIL + he);
			}
			throw new MyClientsException(ErrorCodes.ENTITY_CRE_FAIL,
					ErrorCodes.ENTITY_CRE_FAIL_MSG);
		} finally {
			
			
			GenericDAOImpl.LOGGER.exit();
		}

		return entity;
	}

	@Override
	public void create(List<BODTO<T>> list) throws MyClientsException {

	
		if (null == list) {
			// log.error("list must not be null");
			throw new MyClientsException("Input Parametrs Cannot be Empty");
		}
		int i = 0;
		for (final BODTO<T> entity : list) {
			try {
				getSession().save(entity);
				if (i++ % 30 == 0) {
					getSession().flush();

				}
			} catch (final HibernateException he) {
				if (GenericDAOImpl.LOGGER.isDebugEnabled()) {
					GenericDAOImpl.LOGGER
							.debug(ErrorCodes.ENTITY_CRE_FAIL + he);
				}
				throw new MyClientsException(ErrorCodes.ENTITY_CRE_FAIL,
						ErrorCodes.ENTITY_CRE_FAIL_MSG);
			} finally {
				
				
			}
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public void delete(Long id) throws MyClientsException {

		try {
			
			final T entity = (T) getSession().get(this.tClass, id);
			getSession().delete(entity);
			getSession().flush();
		} catch (final HibernateException he) {
			// log.debug(he.getStackTrace());
		}

	}

	public void delete(List<BODTO<T>> list) throws MyClientsException {

		
		if (null == list) {
			// log.error("list must not be null");
			throw new MyClientsException("Input Parametrs Cannot be Empty");
		}
		int i = 0;
		for (final BODTO<T> to : list) {
			try {
				getSession().delete(to);
				if (i++ % 30 == 0) {
					getSession().flush();
					
				}
			} catch (final IllegalArgumentException iaex) {
				// log.error(iaex.getMessage(), iaex);
				throw new MyClientsException(" aruguments are not valid!");
			} catch (final Throwable t) {
				// log.error(t.getMessage(), t);
				throw new MyClientsException(" aruguments are not valid!");
			}
		}

	}

	@Override
	public void update(T entity) throws MyClientsException {

		if (entity == null) {
			// log.error("entity must not be null");
			throw new MyClientsException("Input Parameters Cannot be Empty ");
		}
		
		getSession().update(entity);
		getSession().flush();
	}

	@Override
	public void update(List<BODTO<T>> list) throws MyClientsException {

	
		if (null == list) {
			// log.error("list must not be null");
			throw new MyClientsException("Input Parametrs Cannot be Empty");
		}
		int i = 0;
		for (final BODTO<T> to : list) {
			try {
				getSession().update(to);
				if (i++ % 30 == 0) {
					getSession().flush();
					
				}
			} catch (final IllegalArgumentException iaex) {
				// log.error(iaex.getMessage(), iaex);
				throw new MyClientsException(" aruguments are not valid!");
			} catch (final Throwable t) {
				// log.error(t.getMessage(), t);
				throw new MyClientsException(" aruguments are not valid!");
			}

		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByDate(Date fDate, Date tDate)
			throws MyClientsException {

		
		final Criteria cr = getSession().createCriteria(this.tClass);
		cr.add(Restrictions.between("createdDate", fDate, tDate));
		final List<T> entityResponse = cr.list();

		return entityResponse;
	}

	@Override
	public T findById(Long id) throws MyClientsException {

		
		@SuppressWarnings("unchecked")
		final T entity = (T) getSession().get(this.tClass, id);

		return entity;
	}

	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public T findByParam(String entityParam, String entityParamValue)
			throws MyClientsException {
		GenericDAOImpl.LOGGER.entry();

		
		T entityResponse = null;
		try {
			final Criteria cr = getSession().createCriteria(this.tClass);
			if (cr == null) {
				entityResponse = (T) cr;
			}
			cr.add(Restrictions.eq(entityParam, entityParamValue));
			if (0 != cr.list().size()) {

				entityResponse = (T) cr.list().get(0);
			}
			if (null == entityResponse) {
				GenericDAOImpl.LOGGER
						.error(" No Entity exist for given param!");
			}
		} catch (final IllegalArgumentException iaex) {
			if (GenericDAOImpl.LOGGER.isDebugEnabled()) {
				GenericDAOImpl.LOGGER.debug(ErrorCodes.LOGIN_FAIL + iaex);
			}
			throw new MyClientsException(ErrorCodes.LOGIN_FAIL,
					ErrorCodes.LOGIN_FAIL_MSG);
		} catch (final HibernateException he) {
			he.printStackTrace();
			if (GenericDAOImpl.LOGGER.isDebugEnabled()) {
				GenericDAOImpl.LOGGER.debug(ErrorCodes.LOGIN_FAIL + he);
			}
			throw new MyClientsException(ErrorCodes.LOGIN_FAIL,
					ErrorCodes.LOGIN_FAIL_MSG);
		} catch (final Exception e) {
			e.printStackTrace();
			if (GenericDAOImpl.LOGGER.isDebugEnabled()) {
				GenericDAOImpl.LOGGER.debug(ErrorCodes.LOGIN_FAIL + e);
			}
			throw new MyClientsException(ErrorCodes.LOGIN_FAIL,
					ErrorCodes.LOGIN_FAIL_MSG);
		}
		GenericDAOImpl.LOGGER.exit();
		return entityResponse;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> retrieveAll() throws MyClientsException {		
		final Criteria cr = getSession().createCriteria(this.tClass);
		cr.add(Restrictions.eq("isActive", true));
		final List<T> ul = cr.list();
		return ul;
	}

	

	

}
