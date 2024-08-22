/**
 * 
 */
package com.scube.crm.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.scube.crm.dao.GenericDAO;
import com.scube.crm.exception.MyJobKartException;
import com.scube.crm.vo.BODTO;

/**
 * @author SCUBE
 *
 */
public class GenericServiceImpl<E> implements GenericService<E> {
	
	@Autowired
	private GenericDAO genericDAO;

	/* (non-Javadoc)
	 * @see com.scube.crm.service.GenericService#create(java.lang.Object)
	 */
	@Override
	public E create(E entity) throws MyJobKartException {
		
		return (E) genericDAO.create(entity);
	}

	/* (non-Javadoc)
	 * @see com.scube.crm.service.GenericService#create(java.util.List)
	 */
	/*@Override
	public void create(List<BODTO<E>> list) throws MyJobKartException {
		// TODO Auto-generated method stub
		
	}*/

	/* (non-Javadoc)
	 * @see com.scube.crm.service.GenericService#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) throws MyJobKartException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.scube.crm.service.GenericService#deleteAll(java.util.List)
	 */
	@Override
	public void deleteAll(List<E> entityList) throws MyJobKartException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.scube.crm.service.GenericService#update(java.lang.Object)
	 */
	@Override
	public void update(E entity) throws MyJobKartException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.scube.crm.service.GenericService#update(java.util.List)
	 */
	/*@Override
	public void update(List<BODTO<E>> list) throws MyJobKartException {
		// TODO Auto-generated method stub
		
	}
*/
	/* (non-Javadoc)
	 * @see com.scube.crm.service.GenericService#findByCriteria(java.lang.Object)
	 */
	@Override
	public E findByCriteria(E entity) throws MyJobKartException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.scube.crm.service.GenericService#findByParam(java.lang.String, java.lang.String)
	 */
	@Override
	public E findByParam(String entityParam, String entityParamValue)
			throws MyJobKartException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.scube.crm.service.GenericService#findByDate(java.util.Date, java.util.Date)
	 */
	@Override
	public List<E> findByDate(Date fDate, Date tDate) throws MyJobKartException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.scube.crm.service.GenericService#findById(java.lang.Long)
	 */
	@Override
	public E findById(Long long1) throws MyJobKartException {
		// TODO Auto-generated method stub
		return null;
	}

}
