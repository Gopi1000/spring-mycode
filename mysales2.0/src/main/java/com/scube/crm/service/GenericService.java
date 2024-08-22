package com.scube.crm.service;

import java.util.Date;
import java.util.List;

import com.scube.crm.exception.MyClientsException;


public interface GenericService<E> {

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
	/*void create(List<BODTO<E>> list) throws MyClientsException;*/

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
	void deleteAll(List<E> entityList) throws MyClientsException;

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
	/*void update(List<BODTO<E>> list) throws MyClientsException;*/

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

	E findById(Long long1) throws MyClientsException;

}
