package com.scube.crm.service;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scube.crm.bo.EmployerRegisterBO;
import com.scube.crm.controller.ApplicationContextProvider;
import com.scube.crm.dao.AdminDAO;
import com.scube.crm.dao.EmployerDAO;
import com.scube.crm.exception.JLogger;
import com.scube.crm.exception.MyJobKartException;
import com.scube.crm.model.EmailModel;
import com.scube.crm.model.EmployerLoginVO;
import com.scube.crm.utils.DateHelper;
import com.scube.crm.utils.EncryptAndDecrypt;
import com.scube.crm.utils.SendEmailService;
import com.scube.crm.utils.SuccessMsg;
import com.scube.crm.vo.AdminLoginVO;
import com.scube.crm.vo.ClientUpdateVO;
import com.scube.crm.vo.EmployerVO;

/**
 * Owner : Scube Technologies Created Date: Nov-22-2014 Created by :
 * Sathishkumar.s Description : JobSeekerServiceImpl Class is a Class which is
 * responsible for access the data from Controller then convert it into
 * persistent Object then sent it into DAO class. Reviewed by :
 * 
 * 
 */

@Service("employerService")
@Transactional
public class EmployerServiceImpl implements EmployerService {

	static final JLogger LOGGER = JLogger.getLogger(EmployerServiceImpl.class);

	// DAo layer annotations
	@Autowired
	private EmployerDAO employerDAO;

	@Autowired
	private SendEmailService sendemailservice;

	private Employerclientservice clientservice;

	public Employerclientservice getClientservice() {
		return clientservice;
	}
	public void setClientservice(Employerclientservice clientservice) {
		this.clientservice = clientservice;
	}


	@Autowired
	private AdminDAO adminDAO;

	/**
	 * This method used to perform the job seeker registration function
	 * 
	 * @param jobseekerRegistrationBO
	 * @return jobSeekerRegistrationBO
	 */

	@Override
	public EmployerRegisterBO retriveJobseeker(
			EmployerRegisterBO reteriveprofile) throws MyJobKartException,
			SerialException, SQLException {
		EmployerServiceImpl.LOGGER.entry();
		EmployerRegisterBO reterive = null;
		try {
			reterive = employerDAO.retriveJobseeker(reteriveprofile);
		} catch (final MyJobKartException je) {
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(je.getMessage() + je);
			}
			reterive = new EmployerRegisterBO();
			reterive.setErrorCode(je.getErrorCode());
			reterive.setErrorMessage(je.getErrorMessage());
		}
		EmployerServiceImpl.LOGGER.exit();
		return reterive;
	}


	@Override
	public EmployerRegisterBO updateProfile(EmployerRegisterBO profile)
			throws MyJobKartException {
		EmployerServiceImpl.LOGGER.entry();
		try {
			EmployerVO employerRegister= new EmployerVO();
			employerRegister=employerRegisterBOtoVO(profile);

			employerRegister = employerDAO.createEmployer(employerRegister);

			/*if(null != profile.getCollege() && !profile.getCollege().isEmpty()){
				vo.setCollege(profile.getCollege());
				}
				if(null != profile.getDepartment() && !profile.getDepartment().isEmpty()){
				vo.setDepartment(profile.getDepartment());
				}
				if(null != profile.getDegree() && !profile.getDegree().isEmpty()){
				vo.setDegree(profile.getDegree());
				}
				if(null != profile.getYearOfPassing() && !profile.getYearOfPassing().isEmpty()){
				vo.setYearOfPassing(profile.getYearOfPassing());
				}
				if(null != profile.getGrade() && !profile.getGrade().isEmpty()){
				vo.setGrade(profile.getGrade());
				}
				if(null != profile.getPercentage() && !profile.getPercentage().isEmpty()){
				vo.setPercentage(profile.getPercentage());
				}

					DateFormat format = new SimpleDateFormat("MM/dd/yyyy");

					if(null != profile.getCompanyName() && !profile.getCompanyName().isEmpty()){
						vo.setCompanyName(profile.getCompanyName());
					}
					if(null != profile.getDesignation() && !profile.getDesignation().isEmpty()){
						vo.setDesignation(profile.getDesignation());
					}
					if(null != profile.getCompanyType() && !profile.getCompanyType().isEmpty()){
						vo.setCompanyType(profile.getCompanyType());
					}
					if(null != profile.getExpStartDate() &&  null != profile.getExpEndDate()){
						vo.setExpEndDate(profile
								.getExpStartDate());

						vo.setExpStartDate(profile
								.getExpEndDate());
					}
					if(null != profile.getLastSalary() && !profile.getLastSalary().isEmpty()){
						vo.setLastSalary(profile.getLastSalary());
					}
					vo.setExpStatus(profile.getExpStatus());



					if(null != profile.getNoOfExperience() && !profile.getNoOfExperience().isEmpty()){
						vo.setNoOfExperience(profile.getNoOfExperience());
					}

					if(null != profile.getFirstName() && !profile.getFirstName().isEmpty()){
					vo.setFirstName(profile.getFirstName());
					}

					if(null != profile.getEmailAddress() && !profile.getEmailAddress().isEmpty()){
					vo.setEmailAddress(profile.getEmailAddress());
					}

					if(0 != profile.getMobileNo()){
					vo.setPhone(profile.getMobileNo());
					}
					if(null != profile.getLastName() && !profile.getLastName().isEmpty()){
					vo.setLastName(profile.getLastName());
					}

					if(null !=profile.getMaritalStatus() && !profile.getMaritalStatus().isEmpty()){
					vo.setMaritalStatus(profile.getMaritalStatus());
					}
					if(null !=profile.getGender() && !profile.getGender().isEmpty()){
					vo.setGender(profile.getGender());
					}
					if(null !=profile.getAddress() && !profile.getAddress().isEmpty()){
					vo.setAddress(profile.getAddress());
					}
					if( 0 != profile.getPincode()){
					vo.setPincode(profile.getPincode());
					}
					if(null !=profile.getPreferredLocation() && !profile.getPreferredLocation().isEmpty()){
					vo.setPreferredLocation(profile.getPreferredLocation());
					}
					if(null !=profile.getLocation() && !profile.getLocation().isEmpty()){
					vo.setLocation(profile.getLocation());
					}
					if(null != profile.getState()  && !profile.getState().isEmpty()){
					vo.setState(profile.getState());
					}

					if(null != profile.getNationality()  && !profile.getNationality().isEmpty()){
					vo.setNationality(profile.getNationality());
					}
					if(null != profile.getJobType()  && !profile.getJobType().isEmpty()){
					vo.setJobType(profile.getJobType());
					}
					if(null != profile.getKeySkills()  && !profile.getKeySkills().isEmpty()){
					vo.setKeySkills(profile.getKeySkills());
					}
					if(null != profile.getDomainSkills()  && !profile.getDomainSkills().isEmpty()){
					vo.setDomainSkills(profile.getDomainSkills());
					}

					if(null !=profile.getFunction() && !profile.getFunction().isEmpty()){
					vo.setFunctionalArea(profile.getFunction());
					}
					if(null != profile.getCurrentSalary()){
					vo.setCurrentSalary(profile.getCurrentSalary());
					}

					if(null != profile.getCurrentIndustry() && !profile.getCurrentIndustry().isEmpty()){
					vo.setCurrentIndustry(profile.getCurrentIndustry());
					}
					if(null != profile.getExpectedCtc()){
					vo.setExpectedCtc(profile.getExpectedCtc());
					}
					if(null != profile.getProfiledescription() && !profile.getProfiledescription().isEmpty()){
					vo.setProfiledescription(profile.getProfiledescription());
					}
					if(null != profile.getLanguagesKnown() && !profile.getLanguagesKnown().isEmpty()){
					vo.setLanguagesKnown(profile.getLanguagesKnown());
					}
					if(null != profile.getPreferredIndustry() && !profile.getPreferredIndustry().isEmpty()){
					vo.setCurrentIndustry(profile.getPreferredIndustry());
					}
					if(null != profile.getPreferredLocation() && !profile.getPreferredLocation().isEmpty()){
					vo.setPreferredLocation(profile.getPreferredLocation());
					}
					if(null != profile.getNoOfExperience()&& !profile.getNoOfExperience().isEmpty()){
					vo.setNoOfExperience(profile.getNoOfExperience());
					}
					if(null != profile.getJobseekerType() && !profile.getJobseekerType().isEmpty()){
					vo.setJobseekerType(profile.getJobseekerType());
					}*/

			/*			vo = this.jobSeekerDAO.updateProfile(vo,profile.getId());
			if(null != vo){
			profile.setResponse(SuccessMsg.UPDATE_SUCCESS);
			}*/
		} catch (final Exception jb) {
			jb.printStackTrace();
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(jb.getMessage() + jb);
			}
		}
		return profile;
	}


	@Override
	public EmployerRegisterBO retriveJobseekerById(
			EmployerRegisterBO reteriveprofile) throws MyJobKartException,
			SerialException, SQLException {
		EmployerServiceImpl.LOGGER.entry();
		EmployerRegisterBO reterive = null;
		try {
			reterive = employerDAO.retriveJobseekerById(reteriveprofile);
		} catch (final MyJobKartException je) {
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(je.getMessage() + je);
			}
			reterive = new EmployerRegisterBO();
			reterive.setErrorCode(je.getErrorCode());
			reterive.setErrorMessage(je.getErrorMessage());
		}
		EmployerServiceImpl.LOGGER.exit();
		return reterive;
	}




	private EmployerVO employerRegisterBOtoVO(EmployerRegisterBO profileBO){
		EmployerVO employerRegVO= new EmployerVO();
		employerRegVO.setCompanyName(profileBO.getCompanyName());
		employerRegVO.setFirstName(
				profileBO.getFirstName());
		employerRegVO.setLastName(
				profileBO.getLastName());
		employerRegVO.setWebSite(
				profileBO.getWebsite());
		employerRegVO.setConfirmEmailAddress(
				profileBO.getConfirmEmailAddress());
		employerRegVO.setEmailAddress(
				profileBO.getEmailAddress());
		employerRegVO.setMobileNumber(profileBO.getMobileNo());
		employerRegVO.setContactNumber(profileBO.getContactNo());
		employerRegVO.setPassword(profileBO.getPassword());
		employerRegVO.setConfirmPassword(
				profileBO.getConfirmPassword());
		employerRegVO.setAddress(
				profileBO.getAddress());
		employerRegVO.setIndustryType(
				profileBO.getIndustryType());
		return employerRegVO;
	}
	/* (non-Javadoc)
	 * @see com.scube.crm.service.JobSeekerService#createEmployer(com.scube.crm.bo.EmployerRegisterBO)
	 */
	@Override
	public EmployerRegisterBO createEmployer(EmployerRegisterBO profileBO) throws MyJobKartException {
		EmployerVO employerRegVO= new EmployerVO();


		if(null != profileBO.getCompanyName() && !profileBO.getCompanyName().isEmpty()){
			employerRegVO.setCompanyName(profileBO.getCompanyName());
		}
		if(null != profileBO.getFirstName() && !profileBO.getFirstName().isEmpty()){
			employerRegVO.setFirstName(
					profileBO.getFirstName());
		}
		if(null != profileBO.getLastName() && !profileBO.getLastName().isEmpty()){
			employerRegVO.setLastName(
					profileBO.getLastName());
		}
		if(null != profileBO.getWebsite() && !profileBO.getWebsite().isEmpty()){
			employerRegVO.setWebSite(
					profileBO.getWebsite());
		}
		if(null != profileBO.getConfirmEmailAddress() && !profileBO.getConfirmEmailAddress().isEmpty()){
			employerRegVO.setConfirmEmailAddress(
					profileBO.getConfirmEmailAddress());
		}
		if(null != profileBO.getEmailAddress() && !profileBO.getEmailAddress().isEmpty()){
			employerRegVO.setEmailAddress(
					profileBO.getEmailAddress());
		}
		if(null != profileBO.getMobileNo()){
			employerRegVO.setMobileNumber(profileBO.getMobileNo());
		}
		if(null != profileBO.getContactNo()){
			employerRegVO.setContactNumber(		
					profileBO.getContactNo());
		}
		if(null != profileBO.getPassword() && !profileBO.getPassword().isEmpty()){
			employerRegVO.setPassword(profileBO.getPassword());
		}
		if(null != profileBO.getConfirmPassword() && !profileBO.getConfirmPassword().isEmpty()){
			employerRegVO.setConfirmPassword(
					profileBO.getConfirmPassword());
		}
		if(null != profileBO.getAddress() && !profileBO.getAddress().isEmpty()){
			employerRegVO.setAddress(
					profileBO.getAddress());
		}
		if(null != profileBO.getIndustryType() && !profileBO.getIndustryType().isEmpty()){
			employerRegVO.setIndustryType(
					profileBO.getIndustryType());
		}
		if(0 !=profileBO.getEmployerId()){
			employerRegVO.setAssigned(profileBO.getEmployerId());
		}


		AdminLoginVO adminLogin = new AdminLoginVO();
		adminLogin.setId(profileBO.getAdminId());
		employerRegVO.setLoginVO(adminLogin);
		employerRegVO.setCreatedBy(profileBO.getCreatedBy());
		employerRegVO.setModifiedBy(profileBO.getModifiedBy());
		employerRegVO.setIsActive(profileBO.isActive());
		employerRegVO.setIsDelete(profileBO.isDelete());
		employerRegVO.setStatus("opened");
		//employerRegVO.setMigrationStatus(false);

		employerRegVO.setCreated(DateHelper.beginningOfDay(new Date()));
		employerRegVO.setModified(DateHelper.beginningOfDay(new Date()));

		employerRegVO.setIsActive(profileBO.isActive());
		employerRegVO = employerDAO.createEmployer(employerRegVO);
		if(null!=employerRegVO){
			BeanUtils.copyProperties(employerRegVO, profileBO);
		}

		return profileBO;
	}




	/**
	 * @param active
	 */
	private void setIsActive(boolean active) {
		// TODO Auto-generated method stub

	}


	/* (non-Javadoc)
	 * @see com.scube.crm.service.JobSeekerService#retriveEmployer(com.scube.crm.bo.EmployerRegisterBO)
	 */
	@Override
	public EmployerRegisterBO retriveEmployer(EmployerRegisterBO registerBO) throws MyJobKartException  {
		EmployerServiceImpl.LOGGER.entry();
		EmployerRegisterBO reterive = new EmployerRegisterBO() ;
		try {
			reterive = employerDAO.retriveJobseekerById(registerBO);
		} catch (final Exception je) {
			if (EmployerServiceImpl.LOGGER.isDebugEnabled()) {
				EmployerServiceImpl.LOGGER.debug(je.getMessage() + je);
			}

			EmployerServiceImpl.LOGGER.exit();


		}
		return reterive;
	}

	public static Date getDateWithoutTime(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	/* (non-Javadoc)
	 * @see com.scube.crm.service.JobSeekerService#updateEmployer(com.scube.crm.bo.EmployerRegisterBO)
	 */
	@Override
	public EmployerRegisterBO updateEmployer(EmployerRegisterBO registerBO) {
		try{
			EmployerVO employerVO=new EmployerVO();
			employerVO = employerDAO.getuserId(registerBO);
			if(null !=registerBO.getFirstName()){
				employerVO.setFirstName(registerBO.getFirstName());
			}
			if(null !=registerBO.getLastName()){
				employerVO.setLastName(registerBO.getLastName());
			}
			if(null  !=registerBO.getMobileNo()){
				employerVO.setMobileNumber(registerBO.getMobileNo());
			}
			if(null !=registerBO.getEmailAddress()){
				employerVO.setEmailAddress(registerBO.getEmailAddress());
			}
			if(null !=registerBO.getConfirmEmailAddress()){
				employerVO.setConfirmEmailAddress(registerBO.getConfirmEmailAddress());
			}
			if(null !=registerBO.getAddress()){
				employerVO.setAddress(registerBO.getAddress());
			}
			if(null !=registerBO.getPassword()){
				employerVO.setPassword(registerBO.getPassword());
			}
			if(null !=registerBO.getCompanyName()){
				employerVO.setCompanyName(registerBO.getCompanyName());
			}
			if(null !=registerBO.getConfirmPassword()){
				employerVO.setConfirmPassword(registerBO.getConfirmPassword());
			}
			if(null !=registerBO.getIndustryType()){
				employerVO.setIndustryType(registerBO.getIndustryType());
			}
			if(null !=registerBO.getWebsite()){
				employerVO.setWebSite(registerBO.getWebsite());
			}

			if(null  !=registerBO.getContactNo()){
				employerVO.setContactNumber(registerBO.getContactNo());
			}

			if(null !=registerBO.getStatus()){
				employerVO.setStatus(registerBO.getStatus());
			}
			employerVO.setModifiedBy(registerBO.getModifiedBy());
			employerVO.setModified(getDateWithoutTime(new Date()));
			employerVO=employerDAO.updateEmployer(employerVO);

			if(0 != employerVO.getId()){
				registerBO.setId(employerVO.getId());
			}else{
				registerBO = new EmployerRegisterBO();
			}

			if(null != employerVO){
				registerBO.setResponse(SuccessMsg.UPDATE_SUCCESS);
			}
		} catch(Exception ex){
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("Update user has failed:" + ex.getMessage());
			}
			LOGGER.info("Update user has failed:" + ex.getMessage());
		}
		return  registerBO;

	}


	/* (non-Javadoc)
	 * @see com.scube.crm.service.JobSeekerService#deleteProfile(com.scube.crm.bo.EmployerRegisterBO)
	 */
	@Override
	public EmployerRegisterBO deleteProfile(EmployerRegisterBO deleteProfile) throws MyJobKartException{
		EmployerServiceImpl.LOGGER.entry();

		EmployerVO VO = new EmployerVO();
		int result;
		try {
			VO.setId(deleteProfile.getId());
			VO.setIsDelete(true);
			VO = employerDAO.deleteProfile(VO);
			if (VO.getIsDelete()){
				deleteProfile.setResponse(SuccessMsg.DELETE_SUCCESS);
			}
		} catch (MyJobKartException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return deleteProfile;
	}


	/* (non-Javadoc)
	 * @see com.scube.crm.service.JobSeekerService#employerCount(com.scube.crm.bo.EmployerRegisterBO)
	 */
	@Override
	public long employerCount(EmployerRegisterBO reteriveprofile) {

		return employerDAO.employerCount(reteriveprofile);
	}
	/*
	@Override
	public long getJobseekerProfileCount(EmployerRegisterBO reteriveprofile) throws MyJobKartException {
		return	jobSeekerDAO.getJobseekerProfileCount(reteriveprofile);  
	} */
	@Override
	public List<EmployerRegisterBO> retrieveAccessRecords(EmployerRegisterBO recordBO) {
		return employerDAO.retriveAccessRecords(recordBO);
	}
	/* (non-Javadoc)
	 * @see com.scube.crm.service.JobSeekerService#getAccessRecordCount(com.scube.crm.bo.JobseekerBO)
	 */


	@Override
	public long getAccessRecordCount(EmployerRegisterBO recordBO) {
		return employerDAO.getAccessRecordCount(recordBO);
	}


	/* (non-Javadoc)
	 * @see com.scube.crm.service.JobSeekerService#adddescription(com.scube.crm.bo.EmployerRegisterBO)
	 */
	@Override
	public EmployerRegisterBO adddescription(EmployerRegisterBO bo) {
		ClientUpdateVO VO=new ClientUpdateVO();
		EmployerVO employerVO=new EmployerVO();
		AdminLoginVO loginVO=new AdminLoginVO();

		employerVO.setId(bo.getId());
		VO.setVO(employerVO);

		loginVO.setId(bo.getAdminId());
		VO.setAdminVO(loginVO);

		if(null !=bo.getDescription() && !bo.getDescription().isEmpty()){
			VO.setDescription(bo.getDescription());
		}
		if(null !=bo.getDate()){
			VO.setDate(bo.getDate());
		}
		if(null !=bo.getStatus()){
			employerVO.setStatus(bo.getStatus());
			VO.setVO(employerVO);
		}
		VO=employerDAO.adddescription(VO);
		if(0 != VO.getUpdateid()){
			bo.setId(VO.getVO().getId());
		}else{
			bo = null;
		}
		return bo;
	}


	/* (non-Javadoc)
	 * @see com.scube.crm.service.EmployerService#migrationstatus(com.scube.crm.bo.EmployerRegisterBO)
	 */
	@Override
	public boolean migrationstatus(EmployerRegisterBO employerRegisterBO) throws Exception {
		LOGGER.entry();
		EmployerRegisterBO registerBO=new EmployerRegisterBO();
		EmployerVO employerVO = new  EmployerVO();
		EmployerLoginVO loginVO= new EmployerLoginVO();
		try {

			Employerclientservice clientservice =(Employerclientservice)ApplicationContextProvider.getBean("clientservice");
			employerVO = employerDAO.getuserId(employerRegisterBO);
			//if (!employerVO.isMigrationStatus()){
				if(null !=employerVO.getFirstName()){
					employerRegisterBO.setFirstName(employerVO.getFirstName());
				}
				if(null !=employerVO.getLastName()){
					employerRegisterBO.setLastName(employerVO.getLastName());
				}
				String[] EmailId=employerVO.getEmailAddress().split(",");
				if(null !=employerVO.getEmailAddress()){
					employerRegisterBO.setEmailAddress(EmailId[0]);
				}
				String []email=employerVO.getConfirmEmailAddress().split(",");
				if(null !=employerVO.getConfirmEmailAddress()){
					employerRegisterBO.setConfirmEmailAddress(email[0]);
				}
				if(null !=employerVO.getCompanyName()){
					employerRegisterBO.setCompanyName(employerVO.getCompanyName());
				}
				if (null !=employerVO.getMobileNumber()){
					employerRegisterBO.setMobileNo(employerVO.getMobileNumber());
				}
				if(null !=employerVO.getWebSite()){
					employerRegisterBO.setWebsite(employerVO.getWebSite());
				}
				if(null !=employerVO.getContactNumber()){
					employerRegisterBO.setContactNo(employerVO.getContactNumber());
				}
				employerRegisterBO.setConfirmPassword(EncryptAndDecrypt.encrypt(email[0].substring(0, 4)+"2017".toLowerCase(), EncryptAndDecrypt.TOKEN));
				employerRegisterBO.setPassword(EncryptAndDecrypt.encrypt(EmailId[0].substring(0, 4)+"2017".toLowerCase(), EncryptAndDecrypt.TOKEN));
				registerBO = clientservice.clientTomyjobkartRegister(employerRegisterBO);

				if(null !=registerBO){
					loginVO=clientservice.signinTomyjobkart(registerBO);
				}
			//}
			if(null !=loginVO){
				EmailModel model = new EmailModel();
				String bodyContent = "Employer-Registration-Confirmation";
				String subject = "Myjobkart: Registration ";	
				String[] Email=employerVO.getEmailAddress().split(",");
				String toaddress=Email[0];
				model.setFirstname(employerRegisterBO.getFirstName());
				boolean status = sendemailservice.sendloginmail(bodyContent,subject,toaddress,model);	
				//if(status && !employerVO.isMigrationStatus()){
					employerVO=employerDAO.updateMigrationstatus(employerVO);
					if ( null != employerVO){
						employerRegisterBO.setResponse(SuccessMsg.EM_REG_SUCCESS);
					}
				}
				return true;
				
			//}

		} catch (MyJobKartException e) {
			e.printStackTrace();
		}
		finally
		{
			LOGGER.exit();
		}
		return false;
	}
}


