package com.scube.crm.dao;

import java.util.Date;
import java.util.List;

import com.scube.crm.exception.MyClientsException;
import com.scube.crm.vo.BODTO;


/**
 * This class to be form an common function for this application
 * 
 * @author Scube Technologies
 * @param <E>
 * 
 */

public interface GenericDAO<E> {

	/**
	 * Creates a new entity and copies properties of given entity.
	 * 
	 * @param entity
	 * @throws MyClientsException
	 *             ;
	 */
	E create(E entity) throws MyClientsException;

	/**
	 * Create list of new entity and copies properties of given entity.
	 * 
	 * @param entity
	 * @throws MyClientsException
	 *             ;
	 */
	void create(List<BODTO<E>> list) throws MyClientsException;

	/**
	 * Deletes given entity from database
	 * 
	 * @param entity
	 * @throws MyClientsException
	 */
	void delete(Long id) throws MyClientsException;

	/**
	 * Deletes given list of entity from database
	 * 
	 * @param entity
	 * @throws MyClientsException
	 *             ;
	 */
	void deleteAll(List<BODTO<E>> entityList) throws MyClientsException;

	/**
	 * Updates given entity to the database
	 * 
	 * @param entity
	 * @throws MyClientsException
	 */
	void update(E entity) throws MyClientsException;

	/**
	 * Updates given list of entity to the database
	 * 
	 * @param entity
	 * @throws MyClientsException
	 */
	void update(List<BODTO<E>> list) throws MyClientsException;

	/**
	 * Retrieve entity with given criteria.
	 * 
	 * @param entity
	 * @throws MyClientsException
	 *             ;
	 */

	E findByCriteria(E entity) throws MyClientsException;

	/**
	 * Retrieve entity with given name String.
	 * 
	 * @param entity
	 * @throws MyClientsException
	 *             ;
	 */

	E findByParam(String entityParam, String entityParamValue)
			throws MyClientsException;

	/**
	 * Retrieve entity with given date.
	 * 
	 * @param entity
	 * @throws MyClientsException
	 *             ;
	 */

	List<E> findByDate(Date fDate, Date tDate) throws MyClientsException;

	/**
	 * Retrieve entity with given id.
	 * 
	 * @param entity
	 * @throws MyClientsException
	 *             ;
	 */

	E findById(Long id) throws MyClientsException;

	/**
	 * Retrieve All entity from the database.
	 * 
	 * @param entity
	 * @throws MyClientsException
	 *             ;
	 */
	List<E> retrieveAll() throws MyClientsException;



}
