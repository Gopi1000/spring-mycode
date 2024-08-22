package com.scube.crm.service;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.serial.SerialException;

import com.scube.crm.bo.ClientUpdateBO;
import com.scube.crm.bo.EmployerBO;
import com.scube.crm.bo.EmployerRegisterBO;
import com.scube.crm.exception.MyJobKartException;

/**
 * Owner : Scube Technologies Created Date: Nov-22-2014 Created by :
 * Sathishkumar.s Description : JobSeekerServiceImpl Class is a Class which is
 * responsible for access the data from Controller then convert it into
 * persistent Object then sent it into DAO class. Reviewed by :
 */
public interface EmployerService{
	
	
	EmployerRegisterBO retriveJobseeker(EmployerRegisterBO reteriveprofile)
			throws MyJobKartException, SerialException, SQLException;
	
	  EmployerRegisterBO updateProfile(EmployerRegisterBO updateProfile) throws MyJobKartException;
	
	EmployerRegisterBO retriveJobseekerById(EmployerRegisterBO reteriveprofile)
			throws MyJobKartException, SerialException, SQLException;

	/**
	 * @param profileBO
	 */
	EmployerRegisterBO createEmployer(EmployerRegisterBO profileBO)  throws MyJobKartException;
	
	EmployerRegisterBO retriveEmployer(EmployerRegisterBO registerBO) throws MyJobKartException;

	/**
	 * @param registerBO
	 * @return
	 */
	EmployerRegisterBO updateEmployer(EmployerRegisterBO registerBO)  throws MyJobKartException;

	/**
	 * @param deleteProfile
	 * @return
	 */
	EmployerRegisterBO deleteProfile(EmployerRegisterBO deleteProfile)  throws MyJobKartException;

	/**
	 * @param reteriveprofile
	 * @return
	 */
	long employerCount(EmployerRegisterBO reteriveprofile);
	
	
	

	
	// long getJobseekerProfileCount(EmployerRegisterBO reteriveprofile) throws MyJobKartException;
	
List<EmployerRegisterBO> retrieveAccessRecords(EmployerRegisterBO recordBO);

	long getAccessRecordCount(EmployerRegisterBO recordBO);

	/**
	 * @param registerBO
	 * @return
	 */
	EmployerRegisterBO adddescription(EmployerRegisterBO registerBO);

	/**
	 * @param employerRegisterBO
	 * @return
	 */
	boolean migrationstatus(EmployerRegisterBO employerRegisterBO) throws Exception;
	

	/**
	 * @param bO
	 * @return
	 */
	

	/**
	 * @param bO
	 * @return
	 */
	

	/**
	 * @param recordBO
	 * @return
	 */
	
	
//	long getAccessedCount(EmployerRegisterBO recordBO);

	/**
	 * @param list
	 * @throws MyJobKartException
	 */
	

	/*boolean findByEmail(String emailAddress) throws MyJobKartException;

	JobSeekerLoginBO jobseekerApproved(JobSeekerLoginBO jobseekerLoginBO)
			throws MyJobKartException;

	List<JobseekerBO> getAllJobseekers();
	
	

	List<JobseekerBO> getjobseeker(JobseekerBO jobSeekerRegistrationBO);
	
	List<JobseekerActivityBO> retriveJobseekerActivity(JobseekerActivityBO jobseekerActivityBO);
	
	List<FollowerBO> retrievedFollowerList(FollowerBO followerBO);
	
	long getJobseekerProfileCount(JobseekerProfileBO jobseekerprofileBO);

	*//**
	 * This Method Used To Job seeker Login
	 * 
	 * @param jobSeekerLoginBO
	 * @return
	 * @throws MyJobKartException
	 *//*
	JobSeekerLoginBO jobseekerSignin(JobSeekerLoginBO jobSeekerLoginBO)
			throws MyJobKartException;
	
	JobseekerBO createJobseeker(JobseekerBO jobSeekerBO)throws MyJobKartException;

	JobSeekerLoginBO active(JobSeekerLoginBO jobSeekerLoginBO)
			throws MyJobKartException;

	boolean jobseekerForgetPassword(JobSeekerLoginBO jobSeekerLoginBO)
			throws MyJobKartException, FileNotFoundException;

	*//**
	 * This Method Used To Perform change password after login.
	 * 
	 * @param changePassword
	 * @return
	 *//*

	boolean jobseekerChangePasswordAfterLogin(JobSeekerLoginBO changePassword);

	boolean jobseekerActivation(String inputParam) throws MyJobKartException;
	*//**
	 * This Method used to perform the job seeker create profile.
	 * 
	 * @param profile
	 * @return
	 *//*

	JobseekerProfileBO jobseekerCreateProfile(JobseekerProfileBO profile);
	
	
	FollowerBO addFellower(FollowerBO followerBO);
	
	JobAlertBO createJobAlert(JobAlertBO alert) throws MyJobKartException;
	
	
	

	*//**
	 * This Method Used To perform the job seeker delete profile.
	 * 
	 * @param deleteProfile
	 * @return
	 * @throws MyJobKartException
	 *//*
	JobseekerProfileBO jobseekerDeleteProfile(JobseekerProfileBO deleteProfile)
			throws MyJobKartException;
	*//**
	 * This method used to perform the delete the saved job details.
	 * 
	 * @param savejobBO
	 * @return
	 * @throws MyJobKartException
	 *//*
	SavejobBO jobseekerDeleteSavedJobs(SavejobBO savejobBO)
			throws MyJobKartException;

	*//**
	 * This method used to perform the delete the applied jobs.
	 * 
	 * @param appliedJobBO
	 * @return
	 * @throws MyJobKartException
	 *//*
	AppliedJobBO jobseekerDeleteAppliedJobs(AppliedJobBO appliedJobBO)
			throws MyJobKartException;

	
	
	List<JobseekerProfileBO> searchJobseeker(JobseekerProfileBO reteriveprofile);
	
	JobseekerProfileBO eduProfile(JobseekerProfileBO updateProfile)
			throws MyJobKartException;
	
	JobseekerProfileBO expDetails(JobseekerProfileBO updateProfile)
			throws MyJobKartException;

	
	
	
	

	JobPostBO jobSearch(JobPostBO jobPostBO) throws MyJobKartException;
	

	
	JobPostBO jobTitleCount(JobPostBO jobPostBO) throws MyJobKartException;
	
	JobPostBO joblocationCount(JobPostBO jobPostBO) throws MyJobKartException;

	EmployerProfileBO companyDetails(long employerId) throws MyJobKartException;

	JobseekerProfileBO retriveJobseeker() throws MyJobKartException;

	JobPostBO searchJob(JobPostBO jobPostBO) throws MyJobKartException;

	boolean profileStatus(JobseekerProfileBO jobseekerProfileBO);
	
	boolean profileActivation(JobseekerProfileBO jobseekerProfileBO);
	
	boolean followerStatus(FollowerBO followerBO);

	JobPostBO refineResult(JobPostBO jobPostBO) throws MyJobKartException;

	JobPostBO jobsearchlist(JobPostBO jobPostBO) throws MyJobKartException;

	
	
	*//**
	 * This Method used to perform the job seeker applied jobs.
	 * 
	 * @param appliedJobBO
	 * @param saveJobId
	 * @return
	 *//*
	boolean jobseekerAppliedJob(AppliedJobBO appliedJobBO, long saveJobId);
	boolean checkProfileId(long jobseekerId);

	boolean savedJob(SavejobBO savejobBO);

	*//**
	 * This Method used to perform the retrieve the saved job details.
	 * 
	 * @param savejobBO
	 * @return
	 * @throws MyJobKartException
	 *//*
	List<SavejobBO> reteriveSavedJobs(SavejobBO savejobBO) throws MyJobKartException;

	AppliedJobBO reteriveAppliedJobs(AppliedJobBO appliedJobBO)
			throws MyJobKartException;

	boolean savejob(long id) throws MyJobKartException;

	boolean applied(long id) throws MyJobKartException;

	AppliedJobBO jobSeekerApplied(AppliedJobBO appliedJobBO);

	SavejobBO jobseekerSavedJob(AppliedJobBO appliedJobBO);

	JobseekerBO retriveRegisteredJobseeker() throws MyJobKartException;
	
	JobseekerBO retriveRegisteredJobseeker(long registerId)
			throws MyJobKartException;

	JobseekerBO updateJobseeker(JobseekerBO jobseekerBO)
			throws MyJobKartException;

	JobseekerBO deleteJobseeker(JobseekerBO deleteProfile)
			throws MyJobKartException;

	JobseekerProfileBO retrieveJobseekerProfile(JobseekerProfileBO jobseekerProfileBO) throws MyJobKartException;

	JobPostBO appliedJobSearchDate(JobPostBO jobPostBO)
			throws MyJobKartException;

	JobPostBO appliedJobSearch(JobPostBO jobPostBO) throws MyJobKartException;

	SavejobBO reteriveSavedJob(SavejobBO savejobBO) throws MyJobKartException;

	JobseekerBO renewalRegisteredJobseeker() throws MyJobKartException;

	boolean profileStatus1(JobseekerProfileBO jobseekerProfileBO);
	
	boolean activeJobseeker (JobSeekerLoginBO jobseekerProfileBO);

	EmployerProfileBO employeeDetail(ViewJobseekerBO empdetail);
	
	//PaymentBO jobseekerAddPayment(PaymentBO paymentBO);

	JobseekerBO renewalJobseekerAlert(String email);
	
	PaymentBO productsEnrolledList(long registerId);

	PaymentBO deleteJobseekerEnrolledDetails(PaymentBO savejobBO);

	PaymentBO lastMonthPaymentList(long registerId);

	PaymentBO retriveJobseekerPayment() throws MyJobKartException;

	SavejobBO retriveJobseekersSavedJobs() throws MyJobKartException;

	AppliedJobBO retriveJobseekersAppliedJobs() throws MyJobKartException;

    JobAlertBO createAlert(JobAlertBO jobalert) throws MyJobKartException;

	*//**
	 * @param jobSeekerProfileBO
	 * @return
	 *//*
	JobseekerProfileBO getJobSeekerProfile(JobseekerProfileBO jobSeekerProfileBO);

	*//**
	 * @param viewJobseekerBO
	 * @return
	 *//*
	List<ViewJobseekerBO> viewJobSeekerHistory(ViewJobseekerBO viewJobseekerBO);
	JobseekerBO getJobseekerRegistration(JobseekerBO jobSeekerBO);

	*//**
	 * @param login
	 * @return
	 *//*
	boolean activeJobseekerStatus(JobSeekerLoginBO login);

	*//**
	 * @param updateProfile 
	 * @return
	 * @throws MyJobKartException 
	 *//*
	JobseekerProfileBO retrieveJobseekerResume(JobseekerProfileBO updateProfile) throws MyJobKartException;


	*//**
	 * @param retrivealert
	 * @return
	 *//*
	List<JobAlertBO> retriveJobAlert(JobAlertBO retrivealert);

	*//**
	 * @param deleteAlert
	 * @return
	 *//*
	JobAlertBO deleteAlert(JobAlertBO deleteAlert) throws MyJobKartException;

	*//**
	 * @param statusBO
	 * @return
	 *//*
	boolean jobalertStatus(JobAlertBO statusBO);

	*//**
	 * @param alertBO
	 * @return
	 *//*
	JobAlertBO retriveAlert(JobAlertBO alertBO) throws MyJobKartException;

	*//**
	 * @param updateJobAlert
	 * @return
	 *//*
	JobAlertBO updateAlert(JobAlertBO updateJobAlert) throws MyJobKartException;

	*//**
	 * @param reteriveprofile
	 * @return
	 *//*
	JobAlertBO retriveJobAlert(JobseekerProfileBO reteriveprofile);

	
	boolean emailVerifications(String email);

	*//**
	 * @param email
	 * @return
	 *//*
	boolean Mobile_verification(String mobileNo);
	
	JobseekerProfileBO retriveJobseekerBO(long companyId);

	*//**
	 * @param jobPostBO
	 * @return
	 * @throws MyJobKartException
	 *//*
	JobPostBO JobByTitle(JobPostBO jobPostBO) throws MyJobKartException;
	
	
	boolean addLoginStatus(String username) throws MyJobKartException;
	
	Map<String, Integer> jobseekerProfileDescCount(long companyId, String designation);
	
	boolean jobseekerEmailUpdate(long regisrationId, String mailId);
*/
}

	