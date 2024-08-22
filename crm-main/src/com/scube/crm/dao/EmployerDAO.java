package com.scube.crm.dao;

import java.sql.SQLException;
import java.util.List;
import javax.sql.rowset.serial.SerialException;

import com.scube.crm.bo.EmployerRegisterBO;
import com.scube.crm.exception.MyJobKartException;
import com.scube.crm.vo.ClientUpdateVO;
import com.scube.crm.vo.EmployerVO;




/**
 * @author Administrator
 * @param <T>
 * 
 */
public interface EmployerDAO {
	
	EmployerRegisterBO retriveJobseeker(
			EmployerRegisterBO jobseekerProfileBO) throws MyJobKartException,
			SerialException, SQLException;

	EmployerRegisterBO retriveJobseekerById(
			EmployerRegisterBO jobseekerProfileBO) throws MyJobKartException,
			SerialException, SQLException;

	/**
	 * @param employerRegVO
	 * @return
	 */
	EmployerVO createEmployer(EmployerVO employerRegVO) throws MyJobKartException;

	/**
	 * @param registerBO
	 * @return
	 */

	EmployerVO getuserId(EmployerRegisterBO registerBO) throws MyJobKartException;

	/**
	 * @param employerVO
	 * @return
	 */
	EmployerVO updateEmployer(EmployerVO employerVO) throws MyJobKartException;

	/**
	 * @param vO
	 * @return
	 */
	EmployerVO 	 deleteProfile(EmployerVO vo)  throws MyJobKartException;

	/**
	 * @param reteriveprofile
	 * @return
	 */
	long employerCount(EmployerRegisterBO reteriveprofile);

	/**
	 * @param reteriveprofile
	 * @return
	 */
// long getJobseekerProfileCount(EmployerRegisterBO reteriveprofile) throws MyJobKartException;
List<EmployerRegisterBO> retriveAccessRecords(EmployerRegisterBO recordBO);

/**
 * @param recordBO
 * @return
 */
long getAccessRecordCount(EmployerRegisterBO recordBO);

/**
 * @param vO
 * @return
 */
ClientUpdateVO adddescription(ClientUpdateVO vO);

/**
 * @param employerVO
 * @return
 */
EmployerVO updateMigrationstatus(EmployerVO employerVO);

/**
 * @param employerVO
 * @return
 */


/**
 * @param recordBO
 * @return
 */
// long getAccessedCount(EmployerRegisterBO recordBO);
	


}
